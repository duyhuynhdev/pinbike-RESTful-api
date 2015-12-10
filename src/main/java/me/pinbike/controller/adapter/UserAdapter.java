package me.pinbike.controller.adapter;

import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thrift.TOrganization;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.Converter;
import me.pinbike.controller.adapter.adapter_interface.IUserAdapter;
import me.pinbike.dao.BikeDao;
import me.pinbike.dao.OrganizationDao;
import me.pinbike.dao.UserDao;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.sharedjava.model.ChangeAvailableStatusAPI;
import me.pinbike.sharedjava.model.GetDriverAroundAPI;
import me.pinbike.sharedjava.model.GetUserProfileAPI;
import me.pinbike.sharedjava.model.UpdateMyLocationAPI;
import me.pinbike.sharedjava.model.base.UpdatedLocation;
import me.pinbike.sharedjava.model.base.UserDetail;
import me.pinbike.sharedjava.model.constanst.AC;

import java.util.ArrayList;
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
        TUser user = userDao.get(request.userId);
        List<TBike> bikes = bikeDao.getList(user.bikeIds);
        List<TOrganization> organizations = organizationDao.getList(user.organizationIds);
        UserDetail userDetail = new Converter().convertUser(user, bikes, organizations);
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
}
