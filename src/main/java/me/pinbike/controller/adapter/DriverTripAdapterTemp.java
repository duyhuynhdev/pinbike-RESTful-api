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
        PollingChannel<PollingDB.Listener> listenerPollingChannel = db.getChannel(PollingChannelName.WAITING_REQUEST);
        //subscribe
        long timeout = listenerPollingChannel.getTimeout();
        boolean changed = false;
        boolean isGoOnline = false;
        while (timeout > 0) {
            changed = listenerPollingChannel.subscribe(request.driverId);
            if (changed)
                break;
            try {
                if (!isGoOnline) {
                    isGoOnline = true;
                    PollingDB.Listener listener = listenerPollingChannel.get(request.driverId);
                    if (listener == null) {
                        listener = new PollingDB.Listener();
                    }
                    listener.isAvailable = true;
                    listenerPollingChannel.changeContent(request.driverId, listener);
                }
                timeout -= listenerPollingChannel.getDelay();
                Thread.sleep(listenerPollingChannel.getDelay());

            } catch (InterruptedException ex) {

            }

        }
        GetRequestFromPassengerAPI.Response response = null;
        if (changed) {
            PollingDB.Listener listener = listenerPollingChannel.get(request.driverId);
            response = new GetRequestFromPassengerAPI.Response();
            response.tripId = listener.tripId;
            response.passengerDetail = getUserDetail();
            response.tripDetail = getTripDetail();
            response.tripDetail.passengerId = listener.passengerId;
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
        return null;
    }

    @Override
    public AcceptPassengerRequestAPI.Response acceptPassengerRequest(AcceptPassengerRequestAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        //change
        AcceptPassengerRequestAPI.Response response = new AcceptPassengerRequestAPI.Response();
        response.isSuccess = false;
        PollingChannel<PollingDB.TripRequest> tripRequestPollingChannel = db.getChannel(PollingChannelName.TRIP_REQUEST);
        PollingDB.TripRequest rq = tripRequestPollingChannel.get(request.tripId);
        if (rq != null && rq.requesterId == request.driverId) {
            rq.accepterId = request.driverId;
            tripRequestPollingChannel.change(request.tripId, rq);
            response.isSuccess = true;
        }
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
        return null;
    }

    @Override
    public GetPassengerUpdatedAPI.Response getPassengerUpdated(GetPassengerUpdatedAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        PollingChannel<PollingDB.UserUpdated> getPassengerUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        long timeout = getPassengerUpdated.getTimeout();
        boolean changed = false;
        while (timeout > 0) {
            changed = getPassengerUpdated.subscribe(SampleData.passengerId);
            if (changed)
                break;
            try {
                timeout -= getPassengerUpdated.getDelay();
                Thread.sleep(getPassengerUpdated.getDelay());
            } catch (InterruptedException ex) {
            }

        }
        GetPassengerUpdatedAPI.Response response = null;
        if (changed) {
            response = new GetPassengerUpdatedAPI.Response(getUpdatedLocation());
            response.type = getPassengerUpdated.get(SampleData.passengerId).type;
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
        return null;
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
        return null;
    }

    @Override
    public RatingTripAPI.Response ratingTrip(RatingTripAPI.Request request) {
        return null;
    }
}
