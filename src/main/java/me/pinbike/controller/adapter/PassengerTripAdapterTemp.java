package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IPassengerTripAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.sharedjava.model.*;
import me.pinbike.util.sample_data.SampleData;

import java.util.Arrays;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class PassengerTripAdapterTemp extends ModelDataFactory implements IPassengerTripAdapter {
    @Override
    public CreateTripAPI.Response createTrip(CreateTripAPI.Request request) {
        CreateTripAPI.Response response = new CreateTripAPI.Response();
        response.tripId = factory.getNumberUpTo(Integer.MAX_VALUE);
        response.drivers = Arrays.asList(getUserDetail(), getUserDetail(), getUserDetail(), getUserDetail(), getUserDetail());
        return response;
    }

    @Override
    public CancelTripAPI.Response cancelTrip(CancelTripAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        // remove request
        PollingChannel<PollingDB.TripRequest> tripRequestPollingChannel = db.getChannel(PollingChannelName.TRIP_REQUEST);
        tripRequestPollingChannel.remove(SampleData.tripId);
        return null;
    }

    @Override
    public RequestDriverAPI.Response requestDriver(RequestDriverAPI.Request request) {
        RequestDriverAPI.Response response = new RequestDriverAPI.Response();
        PollingDB db = PollingDB.getInstance();

        // trigger driver
        PollingChannel<PollingDB.Listener> listenerPollingChannel = db.getChannel(PollingChannelName.WAITING_REQUEST);
        PollingDB.Listener listener = listenerPollingChannel.get(request.driverId);
        if (listener != null && listener.isAvailable) {
            listener.isAvailable = false;
            listener.passengerId = request.passengerId;
            listener.tripId = request.tripId;
            listenerPollingChannel.change(request.driverId, listener);
        } else {
//            throw new PinBikeException(AC.MessageCode.DRIVER_BUSY, "Driver is not available");
        }

        // change
        PollingChannel<PollingDB.TripRequest> tripRequestPollingChannel = db.getChannel(PollingChannelName.TRIP_REQUEST);
        PollingDB.TripRequest rq = tripRequestPollingChannel.get(request.tripId);
        if (rq == null || rq.accepterId <= 0) {
            if (rq == null)
                rq = new PollingDB.TripRequest();
            rq.tripId = request.tripId;
            rq.requesterId = request.driverId;
            tripRequestPollingChannel.change(request.tripId, rq);
        }
        response.driverId = rq.accepterId;
        return response;
    }

    @Override
    public GetDriverUpdatedAPI.Response getDriverUpdated(GetDriverUpdatedAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        PollingChannel<PollingDB.UserUpdated> getDriverUpdate = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        long timeout = getDriverUpdate.getTimeout();
        boolean changed = false;
        while (timeout > 0) {
            changed = getDriverUpdate.subscribe(request.driverId);
            if (changed)
                break;
            try {
                timeout -= getDriverUpdate.getDelay();
                Thread.sleep(getDriverUpdate.getDelay());
            } catch (InterruptedException ex) {
            }

        }
        GetDriverUpdatedAPI.Response response = null;
        if (changed) {
            response = new GetDriverUpdatedAPI.Response(getUpdatedLocation());
            response.type = getDriverUpdate.get(request.driverId).type;
        }
        return response;
    }

    @Override
    public GetTripHistoryAPI.Response getTripHistory(GetTripHistoryAPI.Request request) {
        return null;
    }
}
