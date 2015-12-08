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
        // change
        PollingChannel<PollingDB.Receipt> acceptPassengerRequest = db.getChannel(PollingChannelName.ACCEPT_PASSENGER_REQUEST);
        PollingDB.Receipt receipt = new PollingDB.Receipt();
        receipt.isReceived = false;
        acceptPassengerRequest.change(SampleData.tripId, receipt);
        CancelTripAPI.Response response = new CancelTripAPI.Response();
        return response;
    }

    @Override
    public RequestDriverAPI.Response requestDriver(RequestDriverAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        // change
        RequestDriverAPI.Response response = new RequestDriverAPI.Response();
        PollingChannel<PollingDB.Request> getRequestFromPassengerChannel = db.getChannel(PollingChannelName.GET_REQUEST_FROM_PASSENGER);
        PollingDB.Request rq = getRequestFromPassengerChannel.get(SampleData.tripId);
        if (rq != null && rq.accepterId >= 0) {
        } else {
            if (rq == null)
                rq = new PollingDB.Request();
            rq.tripId = request.tripId;
            rq.requesterId = request.driverId;
            getRequestFromPassengerChannel.change(SampleData.driverId, rq);
        }
        response.driverId = rq.accepterId;
        return response;
    }

    //TODO: REMOVE
    @Override
    public ReceivedDriverAcceptAPI.Response receivedDriverAccept(ReceivedDriverAcceptAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        // change
        PollingChannel<PollingDB.Receipt> acceptPassengerRequest = db.getChannel(PollingChannelName.ACCEPT_PASSENGER_REQUEST);
        PollingDB.Receipt receipt = new PollingDB.Receipt();
        receipt.isReceived = true;
        acceptPassengerRequest.change(SampleData.tripId, receipt);
        ReceivedDriverAcceptAPI.Response response = new ReceivedDriverAcceptAPI.Response();
        return response;
    }

    @Override
    public GetDriverUpdatedAPI.Response getDriverUpdated(GetDriverUpdatedAPI.Request request) {
        PollingDB db = PollingDB.getInstance();
        PollingChannel<PollingDB.UserUpdated> getDriverUpdate = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        long timeout = getDriverUpdate.getTimeout();
        PollingDB.UserUpdated userUpdated = null;
        while (timeout > 0) {
            userUpdated = getDriverUpdate.subscribe(SampleData.driverId);
            if (userUpdated != null)
                break;
            try {
                timeout -= getDriverUpdate.getDelay();
                Thread.sleep(getDriverUpdate.getDelay());
            } catch (InterruptedException ex) {
            }

        }
        GetDriverUpdatedAPI.Response response = null;
        if (userUpdated != null) {
            response = new GetDriverUpdatedAPI.Response(getUpdatedLocation());
            response.type = userUpdated.type;
        }
        return response;
    }
}
