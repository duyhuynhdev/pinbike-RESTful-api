package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IUserAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.sharedjava.model.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class UserAdapterTemp extends ModelDataFactory implements IUserAdapter {
    @Override
    public GetUserProfileAPI.Response getUserProfile(GetUserProfileAPI.Request request) {
        GetUserProfileAPI.Response response = new GetUserProfileAPI.Response(getUserDetail());
        return response;
    }

    @Override
    public GetUserRatingsAPI.Response getUserRatings(GetUserRatingsAPI.Request request) {
        return null;
    }

    @Override
    public GetDriverAroundAPI.Response getDriverAround(GetDriverAroundAPI.Request request) {
        GetDriverAroundAPI.Response response = new GetDriverAroundAPI.Response();
        response.drivers = Arrays.asList(getUpdatedLocation(request.lat, request.lng, 1.5)
                , getUpdatedLocation(request.lat, request.lng, 1.5)
                , getUpdatedLocation(request.lat, request.lng, 1.5)
                , getUpdatedLocation(request.lat, request.lng, 1.5));
        return response;
    }

    @Override
    public UpdateMyLocationAPI.Response updateMyLocation(UpdateMyLocationAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.LocationUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_LOCATION_UPDATED);
        PollingDB.LocationUpdated userUpdated = new PollingDB.LocationUpdated();
        userUpdated.location = getUpdatedLocation();
        getUserUpdated.change(request.userId, userUpdated);

        return null;
    }

    @Override
    public ChangeAvailableStatusAPI.Response changeAvailableStatus(ChangeAvailableStatusAPI.Request request) {
        return null;
    }

    @Override
    public RegisterAPI.Response register(RegisterAPI.Request request) {
        RegisterAPI.Response response = new RegisterAPI.Response(getUserDetail());
        return response;
    }

    @Override
    public LoginByEmailAPI.Response loginByEmail(LoginByEmailAPI.Request request) {
        LoginByEmailAPI.Response response = new LoginByEmailAPI.Response(getUserDetail());
        return response;
    }

    @Override
    public LoginBySocialAPI.Response loginBySocial(LoginBySocialAPI.Request request) {
        LoginBySocialAPI.Response response = new LoginBySocialAPI.Response(getUserDetail());
        return response;
    }

    @Override
    public GetActivationCodeAPI.Response getActivationCode(GetActivationCodeAPI.Request request) {
        return null;
    }

    @Override
    public ActivatePhoneNumberAPI.Response activatePhoneNumber(ActivatePhoneNumberAPI.Request request) {
        return null;
    }

    @Override
    public LogoutAPI.Response logout(LogoutAPI.Request request) {
        return null;
    }

    @Override
    public ForgotPasswordAPI.Response forgotPassword(ForgotPasswordAPI.Request request,String os, String deviceId, String ipAddress) {

        return null;
    }

    @Override
    public ChangePasswordAPI.Response changePassword(ChangePasswordAPI.Request request, String os, String deviceId, String ipAddress) throws IOException {
        return null;
    }

    @Override
    public UpdateUserPhoneNumberAPI.Response updateUserPhoneNumber(UpdateUserPhoneNumberAPI.Request request) {
        return null;
    }

    @Override
    public UpdateUserAvatarAPI.Response updateUserAvatarAPI(UpdateUserAvatarAPI.Request request) {
        return null;
    }

    @Override
    public GetLocationUpdatedAPI.Response getLocationUpdated(GetLocationUpdatedAPI.Request request) {
        return null;
    }

    @Override
    public UpdateUserEnglishCommunicateAPI.Response updateUserEnglishCommunicate(UpdateUserEnglishCommunicateAPI.Request request) {
        return null;
    }
}
