package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IPassengerTripAdapter;
import me.pinbike.sharedjava.model.*;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class PassengerTripAdapter implements IPassengerTripAdapter {
    @Override
    public CreateTripAPI.Response createTrip(CreateTripAPI.Request request) {
        return null;
    }

    @Override
    public CancelTripAPI.Response cancelTrip(CancelTripAPI.Request request) {
        return null;
    }

    @Override
    public RequestDriverAPI.Response requestDriver(RequestDriverAPI.Request request) {
        return null;
    }

    @Override
    public ReceivedDriverAcceptAPI.Response receivedDriverAccept(ReceivedDriverAcceptAPI.Request request) {
        return null;
    }

    @Override
    public GetDriverUpdatedAPI.Response getDriverUpdated(GetDriverUpdatedAPI.Request request) {
        return null;
    }
}
