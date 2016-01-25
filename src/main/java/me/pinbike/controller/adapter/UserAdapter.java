package me.pinbike.controller.adapter;

import com.pinride.pinbike.config.Const;
import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thrift.TOrganization;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.Converter;
import me.pinbike.controller.adapter.adapter_interface.IUserAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.dao.BikeDao;
import me.pinbike.dao.BroadcastDao;
import me.pinbike.dao.OrganizationDao;
import me.pinbike.dao.UserDao;
import me.pinbike.dao.non_db.ActivationDao;
import me.pinbike.geocoder.search.vietbando.ip.GeoFromIpAddress;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.*;
import me.pinbike.sharedjava.model.base.UpdatedLocation;
import me.pinbike.sharedjava.model.base.UserDetail;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.DateTimeUtils;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.SendMailUtil;
import me.pinbike.util.common.Path;
import me.pinbike.util.sample_data.SampleData;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class UserAdapter implements IUserAdapter {
    @Override
    public GetUserProfileAPI.Response getUserProfile(GetUserProfileAPI.Request request) {
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        OrganizationDao organizationDao = new OrganizationDao();
        TUser user = userDao.get(request.ownerId);
        List<TBike> bikes = bikeDao.getList(user.bikeIds);
        List<TOrganization> organizations = organizationDao.getList(user.organizationIds);
        UserDetail userDetail = new Converter().convertUser(user, bikes, organizations, false);
        return new GetUserProfileAPI.Response(userDetail);
    }

    @Override
    public GetDriverAroundAPI.Response getDriverAround(GetDriverAroundAPI.Request request) {
        UserDao userDao = new UserDao();
        Converter converter = new Converter();
        List<TUser> users = userDao.getDriverAround(request);
        List<UpdatedLocation> drivers = new ArrayList<>();
        for (TUser user : users) {
            drivers.add(converter.convertUpdatedLocation(user.currentLocation));
        }
        GetDriverAroundAPI.Response response = new GetDriverAroundAPI.Response();
        response.drivers = drivers;
        return response;
    }

    @Override
    public UpdateMyLocationAPI.Response updateMyLocation(UpdateMyLocationAPI.Request request) {
        UserDao userDao = new UserDao();
        userDao.updateLocation(request.userId, request.location);
        TUser user = userDao.get(request.userId);
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = new Converter().convertUpdatedLocation(user.currentLocation);
        userUpdated.type = AC.UpdatedStatus.LOCATION;// location;
        getUserUpdated.change(request.userId, userUpdated);
        return null;
    }

    @Override
    public ChangeAvailableStatusAPI.Response changeAvailableStatus(ChangeAvailableStatusAPI.Request request) {
        UserDao userDao = new UserDao();
        TUser user = userDao.get(request.userId);
        user.status = AC.UpdatedStatus.AVAILABLE;
        user.availableDriver = true;
        userDao.update(user);
        return null;
    }

    @Override
    public RegisterAPI.Response register(RegisterAPI.Request request) {
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        OrganizationDao organizationDao = new OrganizationDao();
        TUser user = new TUser();
        user.name = request.givenName;
        user.lastName = request.familyName;
        user.middleName = request.middleName;
        if (request.avatar != null && !request.avatar.isEmpty())
            user.avatar = request.avatar;
        else
            user.avatar = new ModelDataFactory().getFactory().getItem(SampleData.avatars);
        user.birthday = request.birthday;
        user.sex = request.sex;
        user.email = request.email;
        user.phone = request.phone;
        user.password = request.password;
        user.intro = request.intro;
        if (request.socialId != null && !request.socialId.isEmpty()) {
            user.socialId = request.socialId;
            user.socialType = request.socialType;

        } else {
            user.socialId = request.email;
            user.socialType = Const.PinBike.SocialType.EMAIL;
        }
        if (new ActivationDao().checkPhoneNumberStatus(user.phone)) {
            user = userDao.insert(user);
            userDao.updateSocialForUser(user.userId, user.email, Const.PinBike.SocialType.EMAIL);
            if (request.socialId != null && !request.socialId.isEmpty()) {
                userDao.updateSocialForUser(user.userId, user.socialId, user.socialType);
            }
            List<TBike> bikes = null;
            List<TOrganization> organizations = null;
            if (user.bikeIds != null)
                bikes = bikeDao.getList(user.bikeIds);
            if (user.organizationIds != null)
                organizations = organizationDao.getList(user.organizationIds);
            UserDetail userDetail = new Converter().convertUser(user, bikes, organizations, false);
            return new RegisterAPI.Response(userDetail);
        }
        throw new PinBikeException(AC.MessageCode.PHONE_HAVE_NOT_ACTIVATED_YET, "Your phone have not activated yet! Please try over again !");
    }

    @Override
    public LoginByEmailAPI.Response loginByEmail(LoginByEmailAPI.Request request) {
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        BroadcastDao broadcastDao = new BroadcastDao();
        OrganizationDao organizationDao = new OrganizationDao();
        TUser user = userDao.getUserByEmailPassword(request.email, request.password);
        // check device Id
        broadcastDao.updateCurrentBroadcast(user, request.deviceId, request.regId, request.os);
        List<TBike> bikes = null;
        List<TOrganization> organizations = null;
        if (user.bikeIds != null)
            bikes = bikeDao.getList(user.bikeIds);
        if (user.organizationIds != null)
            organizations = organizationDao.getList(user.organizationIds);
        UserDetail userDetail = new Converter().convertUser(user, bikes, organizations, false);
        return new LoginByEmailAPI.Response(userDetail);
    }

    @Override
    public LoginBySocialAPI.Response loginBySocial(LoginBySocialAPI.Request request) {
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        BroadcastDao broadcastDao = new BroadcastDao();
        OrganizationDao organizationDao = new OrganizationDao();
        TUser user = userDao.getUserBySocial(request.socialId, request.socialType);
        // check device Id
        broadcastDao.updateCurrentBroadcast(user, request.deviceId, request.regId, request.os);
        List<TBike> bikes = null;
        List<TOrganization> organizations = null;
        if (user.bikeIds != null)
            bikes = bikeDao.getList(user.bikeIds);
        if (user.organizationIds != null)
            organizations = organizationDao.getList(user.organizationIds);
        UserDetail userDetail = new Converter().convertUser(user, bikes, organizations, false);
        return new LoginBySocialAPI.Response(userDetail);
    }

    @Override
    public GetActivationCodeAPI.Response getActivationCode(GetActivationCodeAPI.Request request) {
        ActivationDao dao = new ActivationDao();
        dao.getActivationCode(request.phoneNumber);
        return null;
    }

    @Override
    public ActivatePhoneNumberAPI.Response activatePhoneNumber(ActivatePhoneNumberAPI.Request request) {
        ActivationDao dao = new ActivationDao();
        dao.activatePhoneNumber(request.phoneNumber, request.activationCode);
        return null;
    }

    @Override
    public LogoutAPI.Response logout(LogoutAPI.Request request) {
        BroadcastDao broadcastDao = new BroadcastDao();
        UserDao userDao = new UserDao();
        TUser user = userDao.get(request.userId);
        broadcastDao.removeCurrentBroadcast(user, request.deviceId);
        return null;
    }

    @Override
    public ForgotPasswordAPI.Response forgotPassword(ForgotPasswordAPI.Request request, String os, String deviceId, String ipAddress) throws IOException {
        UserDao userDao = new UserDao();
        TUser user = userDao.getUserBySocial(request.email, Const.PinBike.SocialType.EMAIL);
        String content = PinBikeConstant.MailLayout.getForgetPasswordLayOut(
                PinBikeConstant.MailLayout.title_forget_password,
                Path.getInstance().getUrlFromPath(user.avatar),
                user.name,
                DateTimeUtils.getHCMFormatDate(new Date().getTime()),
                RandomStringUtils.randomAlphabetic(5),
                os, deviceId, ipAddress, new GeoFromIpAddress().getAddressFromIp(ipAddress), user.email);
        new SendMailUtil(user.email, PinBikeConstant.MailLayout.title_forget_password, content);
        return null;
    }

    @Override
    public ChangePasswordAPI.Response changePassword(ChangePasswordAPI.Request request, String os, String deviceId, String ipAddress) throws IOException {
        UserDao userDao = new UserDao();
        TUser user = userDao.getUserBySocial(request.email, Const.PinBike.SocialType.EMAIL);
        boolean isAccepted = false;
        if (request.deviceId.equals(PinBikeConstant.accesskey))
            isAccepted = true;
        else if (userDao.checkCurrentDeviceId(request.userId, request.deviceId)
                && user.userId == request.userId && user.password.equals(request.currentPassword)) {
            isAccepted = true;
        }
        if (isAccepted) {
            user.password = request.newPassword;
            userDao.update(user);
            String content = PinBikeConstant.MailLayout.getChangePasswordLayOut(
                    PinBikeConstant.MailLayout.title_change_password,
                    Path.getInstance().getUrlFromPath(user.avatar),
                    user.name,
                    DateTimeUtils.getHCMFormatDate(new Date().getTime()),
                    user.password,
                    os, deviceId, ipAddress, new GeoFromIpAddress().getAddressFromIp(ipAddress));
            new SendMailUtil(user.email, PinBikeConstant.MailLayout.title_change_password, content);
        } else {
            throw new PinBikeException(AC.MessageCode.FAIL, "Cannot change password please check parameters again!");
        }
        return null;
    }

    @Override
    public UpdateUserPhoneNumberAPI.Response updateUserPhoneNumber(UpdateUserPhoneNumberAPI.Request request) {
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        ActivationDao activationDao = new ActivationDao();
        OrganizationDao organizationDao = new OrganizationDao();
        TUser user = userDao.get(request.userId);
        activationDao.activatePhoneNumber(request.phoneNumber, request.activationCode);
        user.phone = request.phoneNumber;
        userDao.update(user);
        List<TBike> bikes = null;
        List<TOrganization> organizations = null;
        if (user.bikeIds != null)
            bikes = bikeDao.getList(user.bikeIds);
        if (user.organizationIds != null)
            organizations = organizationDao.getList(user.organizationIds);
        UserDetail userDetail = new Converter().convertUser(user, bikes, organizations, false);
        return new UpdateUserPhoneNumberAPI.Response(userDetail);
    }

    @Override
    public UpdateUserAvatarAPI.Response updateUserAvatarAPI(UpdateUserAvatarAPI.Request request) {
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        OrganizationDao organizationDao = new OrganizationDao();
        TUser user = userDao.get(request.userId);
        user.avatar = request.avatar;
        userDao.update(user);
        List<TBike> bikes = null;
        List<TOrganization> organizations = null;
        if (user.bikeIds != null)
            bikes = bikeDao.getList(user.bikeIds);
        if (user.organizationIds != null)
            organizations = organizationDao.getList(user.organizationIds);
        UserDetail userDetail = new Converter().convertUser(user, bikes, organizations, false);
        return new UpdateUserAvatarAPI.Response(userDetail);
    }


}
