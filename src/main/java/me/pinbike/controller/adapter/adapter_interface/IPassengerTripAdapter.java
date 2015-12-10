package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.*;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IPassengerTripAdapter {

    CreateTripAPI.Response createTrip(CreateTripAPI.Request request);

    CancelTripAPI.Response cancelTrip(CancelTripAPI.Request request);

    RequestDriverAPI.Response requestDriver(RequestDriverAPI.Request request);

    GetDriverUpdatedAPI.Response getDriverUpdated(GetDriverUpdatedAPI.Request request);

}
