package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IUserAdapter;
import me.pinbike.sharedjava.model.ChangeAvailableStatusAPI;
import me.pinbike.sharedjava.model.GetDriverAroundAPI;
import me.pinbike.sharedjava.model.GetUserProfileAPI;
import me.pinbike.sharedjava.model.UpdateMyLocationAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class UserAdapter implements IUserAdapter {
    @Override
    public GetUserProfileAPI.Response getUserProfile(GetUserProfileAPI.Request request) {
        return null;
    }

    @Override
    public GetDriverAroundAPI.Response getDriverAround(GetDriverAroundAPI.Request request) {
        return null;
    }

    @Override
    public UpdateMyLocationAPI.Response updateMyLocation(UpdateMyLocationAPI.Request request) {
        return null;
    }

    @Override
    public ChangeAvailableStatusAPI.Response changeAvailableStatus(ChangeAvailableStatusAPI.Request request) {
        return null;
    }
}
