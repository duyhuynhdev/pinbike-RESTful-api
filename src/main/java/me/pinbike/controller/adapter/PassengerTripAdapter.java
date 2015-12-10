package me.pinbike.controller.adapter;

import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thrift.TOrganization;
import com.pinride.pinbike.thrift.TTrip;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.Converter;
import me.pinbike.controller.adapter.adapter_interface.IPassengerTripAdapter;
import me.pinbike.dao.BikeDao;
import me.pinbike.dao.OrganizationDao;
import me.pinbike.dao.TripDao;
import me.pinbike.dao.UserDao;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.CancelTripAPI;
import me.pinbike.sharedjava.model.CreateTripAPI;
import me.pinbike.sharedjava.model.GetDriverUpdatedAPI;
import me.pinbike.sharedjava.model.RequestDriverAPI;
import me.pinbike.sharedjava.model.base.UserDetail;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.DateTimeUtils;
import me.pinbike.util.PinBikeConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class PassengerTripAdapter implements IPassengerTripAdapter {
    @Override
    public CreateTripAPI.Response createTrip(CreateTripAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        OrganizationDao organizationDao = new OrganizationDao();
        //check user exits
        TUser passenger = userDao.get(request.passengerId);
        //create trip
        TTrip trip = new TTrip();
        trip.dateCreated = DateTimeUtils.now();
        trip.distance = request.distance;
        trip.startLocation = request.startLocation.address; // TODO will fix in the future
        trip.endLocation = request.endLocation.address; // TODO will fix in the future
        trip.passengerId = passenger.userId;
        trip.price = request.price;
        trip = tripDao.insert(trip);

        //Get driver around
        Converter converter = new Converter();
        List<TUser> users = userDao.getDriverAround(request.startLocation);
        List<UserDetail> drivers = new ArrayList<>();
        for (TUser user : users) {
            if (drivers.size() == PinBikeConstant.TripConst.NUMBER_OF_DRIVERS)
                break;
            List<TBike> bikes = bikeDao.getList(user.bikeIds);
            List<TOrganization> organizations = organizationDao.getList(user.organizationIds);
            drivers.add(converter.convertUser(user, bikes, organizations));
        }
        CreateTripAPI.Response response = new CreateTripAPI.Response();
        response.drivers = drivers;
        response.tripId = trip.tripId;
        return response;
    }

    @Override
    public CancelTripAPI.Response cancelTrip(CancelTripAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        PollingDB db = PollingDB.getInstance();
        TUser user = userDao.get(request.userId);
        TTrip trip = tripDao.get(request.tripId);
        // remove request
        PollingChannel<PollingDB.TripRequest> tripRequestPollingChannel = db.getChannel(PollingChannelName.TRIP_REQUEST);
        tripRequestPollingChannel.remove(trip.tripId);
        // remove trip
        tripDao.delete(trip.tripId);
        return null;
    }

    @Override
    public RequestDriverAPI.Response requestDriver(RequestDriverAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TUser passenger = userDao.get(request.passengerId);
        TUser driver = userDao.get(request.driverId);
        TTrip trip = tripDao.get(request.tripId);

        RequestDriverAPI.Response response = new RequestDriverAPI.Response();
        PollingDB db = PollingDB.getInstance();

        // trigger driver
        PollingChannel<PollingDB.Listener> listenerPollingChannel = db.getChannel(PollingChannelName.WAITING_REQUEST);
        PollingDB.Listener listener = listenerPollingChannel.get(driver.userId);
        if (listener == null || !listener.isAvailable) {
            throw new PinBikeException(AC.MessageCode.DRIVER_BUSY, "Driver is not available");
        } else {
            listener.isAvailable = false;
            listener.passengerId = passenger.userId;
            listener.tripId = trip.tripId;
            listenerPollingChannel.change(driver.userId, listener);
        }

        // change
        PollingChannel<PollingDB.TripRequest> tripRequestPollingChannel = db.getChannel(PollingChannelName.TRIP_REQUEST);
        PollingDB.TripRequest rq = tripRequestPollingChannel.get(trip.tripId);
        if (rq == null || rq.accepterId <= 0) {
            if (rq == null)
                rq = new PollingDB.TripRequest();
            rq.tripId = trip.tripId;
            rq.requesterId = driver.userId;
            tripRequestPollingChannel.change(trip.tripId, rq);
        }
        response.driverId = rq.accepterId;
        return response;
    }


    @Override
    public GetDriverUpdatedAPI.Response getDriverUpdated(GetDriverUpdatedAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TUser driver = userDao.get(request.driverId);
        TTrip trip = tripDao.get(request.tripId);
        PollingDB db = PollingDB.getInstance();
        PollingChannel<PollingDB.UserUpdated> getDriverUpdate = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        long timeout = getDriverUpdate.getTimeout();
        boolean changed = false;
        while (timeout > 0) {
            changed = getDriverUpdate.subscribe(driver.userId);
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
            response = new GetDriverUpdatedAPI.Response(new Converter().convertUpdatedLocation(driver.currentLocation));
            response.type = getDriverUpdate.get(driver.userId).type;
        }
        return response;
    }
}
