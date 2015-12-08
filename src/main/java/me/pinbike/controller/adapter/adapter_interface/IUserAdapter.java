package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.ChangeAvailableStatusAPI;
import me.pinbike.sharedjava.model.GetDriverAroundAPI;
import me.pinbike.sharedjava.model.GetUserProfileAPI;
import me.pinbike.sharedjava.model.UpdateMyLocationAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IUserAdapter {

    GetUserProfileAPI.Response getUserProfile(GetUserProfileAPI.Request request);

    GetDriverAroundAPI.Response getDriverAround(GetDriverAroundAPI.Request request);

    UpdateMyLocationAPI.Response updateMyLocation(UpdateMyLocationAPI.Request request);

    ChangeAvailableStatusAPI.Response changeAvailableStatus(ChangeAvailableStatusAPI.Request request);

}
