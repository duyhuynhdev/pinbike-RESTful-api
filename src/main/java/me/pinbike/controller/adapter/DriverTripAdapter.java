package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IDriverTripAdapter;
import me.pinbike.sharedjava.model.*;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class DriverTripAdapter implements IDriverTripAdapter {
    @Override
    public GetRequestFromPassengerAPI.Response getRequestFromPassenger(GetRequestFromPassengerAPI.Request request) {
        return null;
    }

    @Override
    public ArrivedPickUpLocationAPI.Response arrivedPickUpLocation(ArrivedPickUpLocationAPI.Request request) {
        return null;
    }

    @Override
    public AcceptPassengerRequestAPI.Response acceptPassengerRequest(AcceptPassengerRequestAPI.Request request) {
        return null;
    }

    @Override
    public DestroyTripAPI.Response destroyTrip(DestroyTripAPI.Request request) {
        return null;
    }

    @Override
    public GetPassengerUpdatedAPI.Response getPassengerUpdated(GetPassengerUpdatedAPI.Request request) {
        return null;
    }

    @Override
    public StartTripAPI.Response startTrip(StartTripAPI.Request request) {
        return null;
    }

    @Override
    public EndTripAPI.Response endTrip(EndTripAPI.Request request) {
        return null;
    }

    @Override
    public RatingTripAPI.Response ratingTrip(RatingTripAPI.Request request) {
        return null;
    }
}
