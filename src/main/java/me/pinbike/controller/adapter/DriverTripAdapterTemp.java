package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IDriverTripAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.sharedjava.model.*;
import me.pinbike.util.sample_data.SampleData;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class DriverTripAdapterTemp extends ModelDataFactory implements IDriverTripAdapter {
    @Override
    public GetRequestFromPassengerAPI.Response getRequestFromPassenger(GetRequestFromPassengerAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        //subscribe
        PollingChannel<PollingDB.Request> getRequestFromPassengerChannel = db.getChannel(PollingChannelName.GET_REQUEST_FROM_PASSENGER);
        long timeout = getRequestFromPassengerChannel.getTimeout();
        PollingDB.Request rq = null;
        while (timeout > 0) {
            rq = getRequestFromPassengerChannel.subscribe(SampleData.driverId);
            if (rq != null)
                break;
            try {
                timeout -= getRequestFromPassengerChannel.getDelay();
                Thread.sleep(getRequestFromPassengerChannel.getDelay());

            } catch (InterruptedException ex) {

            }

        }
        GetRequestFromPassengerAPI.Response response = null;
        if (rq != null) {
            response = new GetRequestFromPassengerAPI.Response();
            response.tripId = factory.getNumberUpTo(Integer.MAX_VALUE);
            response.passengerDetail = getUserDetail();
            response.tripDetail = getTripDetail();
            response.tripDetail.passengerId = response.passengerDetail.userId;
        }
        return response;
    }

    @Override
    public ArrivedPickUpLocationAPI.Response arrivedPickUpLocation(ArrivedPickUpLocationAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = getUpdatedLocation();
        userUpdated.type = SampleData.update_types[1];// arrived;
        getUserUpdated.change(SampleData.driverId, userUpdated);
        ArrivedPickUpLocationAPI.Response response = new ArrivedPickUpLocationAPI.Response();
        return response;
    }

    @Override
    public AcceptPassengerRequestAPI.Response acceptPassengerRequest(AcceptPassengerRequestAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.Acceptance> requestDriverChannel = db.getChannel(PollingChannelName.REQUEST_DRIVER);
        PollingDB.Acceptance acceptance = new PollingDB.Acceptance();
        acceptance.isAccepted = true;
        requestDriverChannel.change(SampleData.tripId, acceptance);
        // subscribe
        PollingChannel<PollingDB.Receipt> acceptPassengerRequestChannel = db.getChannel(PollingChannelName.ACCEPT_PASSENGER_REQUEST);
        long timeout = acceptPassengerRequestChannel.getTimeout();
        PollingDB.Receipt receipt = null;
        while (timeout > 0) {
            receipt = acceptPassengerRequestChannel.subscribe(SampleData.tripId);
            if (receipt != null)
                break;
            try {
                timeout -= acceptPassengerRequestChannel.getDelay();
                Thread.sleep(acceptPassengerRequestChannel.getDelay());

            } catch (InterruptedException ex) {

            }

        }
        AcceptPassengerRequestAPI.Response response = null;
        if (receipt != null && receipt.isReceived)
            response = new AcceptPassengerRequestAPI.Response();
        return response;
    }

    @Override
    public DestroyTripAPI.Response destroyTrip(DestroyTripAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = getUpdatedLocation();
        userUpdated.type = SampleData.update_types[4];// cancel;
        getUserUpdated.change(request.userId, userUpdated);
        DestroyTripAPI.Response response = new DestroyTripAPI.Response();
        return response;
    }

    @Override
    public GetPassengerUpdatedAPI.Response getPassengerUpdated(GetPassengerUpdatedAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        PollingChannel<PollingDB.UserUpdated> getPassengerUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        long timeout = getPassengerUpdated.getTimeout();
        PollingDB.UserUpdated userUpdated = null;
        while (timeout > 0) {
            userUpdated = getPassengerUpdated.subscribe(SampleData.passengerId);
            if (userUpdated != null)
                break;
            try {
                timeout -= getPassengerUpdated.getDelay();
                Thread.sleep(getPassengerUpdated.getDelay());
            } catch (InterruptedException ex) {
            }

        }
        GetPassengerUpdatedAPI.Response response = null;
        if (userUpdated != null) {
            response = new GetPassengerUpdatedAPI.Response(getUpdatedLocation());
            response.type = userUpdated.type;
        }
        return response;
    }

    @Override
    public StartTripAPI.Response startTrip(StartTripAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = getUpdatedLocation();
        userUpdated.type = SampleData.update_types[2];// start;
        getUserUpdated.change(SampleData.driverId, userUpdated);
        StartTripAPI.Response response = new StartTripAPI.Response();
        return response;
    }

    @Override
    public EndTripAPI.Response endTrip(EndTripAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = getUpdatedLocation();
        userUpdated.type = SampleData.update_types[3];// end;
        getUserUpdated.change(SampleData.driverId, userUpdated);
        EndTripAPI.Response response = new EndTripAPI.Response();
        return response;
    }

    @Override
    public RatingTripAPI.Response ratingTrip(RatingTripAPI.Request request) {
        RatingTripAPI.Response response = new RatingTripAPI.Response();
        return response;
    }
}
