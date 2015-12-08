package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IUserAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.sharedjava.model.ChangeAvailableStatusAPI;
import me.pinbike.sharedjava.model.GetDriverAroundAPI;
import me.pinbike.sharedjava.model.GetUserProfileAPI;
import me.pinbike.sharedjava.model.UpdateMyLocationAPI;
import me.pinbike.util.sample_data.SampleData;

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
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = getUpdatedLocation();
        userUpdated.type = SampleData.update_types[0];// location;
        getUserUpdated.change(request.userId,userUpdated);

        UpdateMyLocationAPI.Response response = new UpdateMyLocationAPI.Response();
        return response;
    }

    @Override
    public ChangeAvailableStatusAPI.Response changeAvailableStatus(ChangeAvailableStatusAPI.Request request) {
        ChangeAvailableStatusAPI.Response response = new ChangeAvailableStatusAPI.Response();
        return response;
    }
}
