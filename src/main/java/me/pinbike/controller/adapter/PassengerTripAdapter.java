package me.pinbike.controller.adapter;

import com.pinride.pinbike.thrift.*;
import me.pinbike.controller.adapter.adapter_interface.Converter;
import me.pinbike.controller.adapter.adapter_interface.IPassengerTripAdapter;
import me.pinbike.dao.BikeDao;
import me.pinbike.dao.OrganizationDao;
import me.pinbike.dao.TripDao;
import me.pinbike.dao.UserDao;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.sharedjava.model.*;
import me.pinbike.sharedjava.model.base.TripReviewSortDetail;
import me.pinbike.sharedjava.model.base.UserDetail;
import me.pinbike.util.DateTimeUtils;
import me.pinbike.util.PinBikeConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        trip.endLatLng = new TLatLng();
        trip.startLatLng = new TLatLng();
        trip.endLatLng.lat = request.endLocation.lat;
        trip.endLatLng.lng = request.endLocation.lng;
        trip.startLatLng.lat = request.startLocation.lat;
        trip.startLatLng.lng = request.startLocation.lng;
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
            List<TBike> bikes = null;
            List<TOrganization> organizations = null;
            if (passenger.bikeIds != null)
                bikes = bikeDao.getList(passenger.bikeIds);
            if (passenger.organizationIds != null)
                organizations = organizationDao.getList(passenger.organizationIds);
            drivers.add(converter.convertUser(user, bikes, organizations, true));
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
        if (listener != null && listener.isAvailable) {
            listener.isAvailable = false;
            listener.passengerId = passenger.userId;
            listener.tripId = trip.tripId;
            listenerPollingChannel.change(driver.userId, listener);
        } else {
//            throw new PinBikeException(AC.MessageCode.DRIVER_BUSY, "Driver is not available");
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

    @Override
    public GetTripHistoryAPI.Response getTripHistory(GetTripHistoryAPI.Request request) {
        UserDao userDao = new UserDao();
        TripDao tripDao = new TripDao();
        TUser user = userDao.get(request.userId);
        List<TripReviewSortDetail> dvt = new ArrayList<>();
        List<TripReviewSortDetail> pst = new ArrayList<>();
        List<TTrip> driverTrips = null;
        try {
            driverTrips = tripDao.getTripByDriver(user.userId);
        } catch (Exception ignored) {

        }
        List<TTrip> passengerTrips = null;
        try {
            passengerTrips = tripDao.getTripByPassneger(user.userId);
        } catch (Exception ignored) {

        }
        if (driverTrips != null) {
            Collections.sort(driverTrips, new Comparator<TTrip>() {
                @Override
                public int compare(TTrip o1, TTrip o2) {
                    if (o1.dateCreated < o2.dateCreated)
                        return -1;
                    return 1;
                }
            });
            for (TTrip t : driverTrips) {
                try {
                    TUser partner = userDao.get(t.passengerId);
                    TripReviewSortDetail trv = new Converter().convertTripReviewSortDetail(t, partner);
                    if (trv != null)
                        dvt.add(trv);
                    if (dvt.size() >= request.numberOfTrips)
                        break;
                } catch (Exception ignored) {
                }
            }
        }
        if (passengerTrips != null) {
            //sort
            Collections.sort(passengerTrips, new Comparator<TTrip>() {
                @Override
                public int compare(TTrip o1, TTrip o2) {
                    if (o1.dateCreated < o2.dateCreated)
                        return -1;
                    return 1;
                }
            });
            //get
            for (TTrip t : passengerTrips) {
                try {
                    TUser partner = userDao.get(t.driverId);
                    TripReviewSortDetail trv = new Converter().convertTripReviewSortDetail(t, partner);
                    if (trv != null)
                        pst.add(trv);
                    if (pst.size() >= request.numberOfTrips)
                        break;
                } catch (Exception ignored) {
                }
            }
        }
        GetTripHistoryAPI.Response response = new GetTripHistoryAPI.Response();
        response.driverTrips = dvt;
        response.passengerTrips = pst;
        return response;
    }


}
