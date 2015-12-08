package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.*;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IDriverTripAdapter {

    GetRequestFromPassengerAPI.Response getRequestFromPassenger(GetRequestFromPassengerAPI.Request request);

    ArrivedPickUpLocationAPI.Response arrivedPickUpLocation(ArrivedPickUpLocationAPI.Request request);

    AcceptPassengerRequestAPI.Response acceptPassengerRequest(AcceptPassengerRequestAPI.Request request);

    DestroyTripAPI.Response destroyTrip(DestroyTripAPI.Request request);

    GetPassengerUpdatedAPI.Response getPassengerUpdated(GetPassengerUpdatedAPI.Request request);

    StartTripAPI.Response startTrip(StartTripAPI.Request request);

    EndTripAPI.Response endTrip(EndTripAPI.Request request);

    RatingTripAPI.Response ratingTrip(RatingTripAPI.Request request);

}
