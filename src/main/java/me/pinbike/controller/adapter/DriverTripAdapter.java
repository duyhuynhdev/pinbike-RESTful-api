package me.pinbike.controller.adapter;

import com.pinride.pinbike.thrift.*;
import me.pinbike.controller.adapter.adapter_interface.Converter;
import me.pinbike.controller.adapter.adapter_interface.IDriverTripAdapter;
import me.pinbike.dao.*;
import me.pinbike.polling.PollingChannel;
import me.pinbike.polling.PollingChannelName;
import me.pinbike.polling.PollingDB;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.*;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.DateTimeUtils;

import java.util.List;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class DriverTripAdapter implements IDriverTripAdapter {
    @Override
    public GetRequestFromPassengerAPI.Response getRequestFromPassenger(GetRequestFromPassengerAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        OrganizationDao organizationDao = new OrganizationDao();
        TUser driver = userDao.get(request.driverId);
        PollingDB db = PollingDB.getInstance();
        PollingChannel<PollingDB.Listener> listenerPollingChannel = db.getChannel(PollingChannelName.WAITING_REQUEST);
        //subscribe
        long timeout = listenerPollingChannel.getTimeout();
        boolean changed = false;
        boolean isGoOnline = false;
        while (timeout > 0) {
            changed = listenerPollingChannel.subscribe(driver.userId);
            if (changed) {
                //TODO NEW
                PollingDB.Listener listener = listenerPollingChannel.get(driver.userId);
                if (listener == null) {
                    listener = new PollingDB.Listener();
                }
                listener.isAvailable = false;
                listenerPollingChannel.changeContent(driver.userId, listener);
                break;
            }
            try {
                if (!isGoOnline) {
                    isGoOnline = true;
                    PollingDB.Listener listener = listenerPollingChannel.get(driver.userId);
                    if (listener == null) {
                        listener = new PollingDB.Listener();
                    }
                    listener.isAvailable = true;
                    listenerPollingChannel.changeContent(driver.userId, listener);
                }
                timeout -= listenerPollingChannel.getDelay();
                Thread.sleep(listenerPollingChannel.getDelay());

            } catch (InterruptedException ex) {
            }

        }
        GetRequestFromPassengerAPI.Response response = null;
        if (changed) {
            PollingDB.Listener listener = listenerPollingChannel.get(driver.userId);
            TTrip trip = tripDao.get(listener.tripId);
            TUser passenger = userDao.get(listener.passengerId);
            List<TBike> bikes = null;
            List<TOrganization> organizations = null;
            if (passenger.bikeIds != null)
                bikes = bikeDao.getList(passenger.bikeIds);
            if (passenger.organizationIds != null)
                organizations = organizationDao.getList(passenger.organizationIds);
            response = new GetRequestFromPassengerAPI.Response();
            response.tripId = trip.tripId;
            response.passengerDetail = new Converter().convertUser(passenger, bikes, organizations, false);
            response.tripDetail = new Converter().convertTripDetail(trip);
            return response;
        }
        throw new PinBikeException(AC.MessageCode.FAIL, "Polling time-out");
    }

    @Override
    public ArrivedPickUpLocationAPI.Response arrivedPickUpLocation(ArrivedPickUpLocationAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TTrip trip = tripDao.get(request.tripId);
        TUser driver = userDao.get(request.driverId);
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = new Converter().convertUpdatedLocation(driver.currentLocation);
        userUpdated.type = AC.UpdatedStatus.ARRIVED;
        getUserUpdated.change(driver.userId + "#" + request.tripId, userUpdated);
        // update user status
        driver.status = AC.UpdatedStatus.ARRIVED;
        userDao.update(driver);
        //update trip status
        trip.status = AC.UpdatedStatus.ARRIVED;
        trip.datetimeStartTrip = DateTimeUtils.now();
        tripDao.update(trip);
        return null;
    }

    @Override
    public AcceptPassengerRequestAPI.Response acceptPassengerRequest(AcceptPassengerRequestAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TTrip trip = tripDao.get(request.tripId);
        TUser driver = userDao.get(request.driverId);
        PollingDB db = PollingDB.getInstance();
        //TODO NEW
        //update unavailable
        PollingChannel<PollingDB.Listener> listenerPollingChannel = db.getChannel(PollingChannelName.WAITING_REQUEST);
        PollingDB.Listener listener = listenerPollingChannel.get(driver.userId);
        if (listener == null) {
            listener = new PollingDB.Listener();
        }
        listener.isAvailable = false;
        listenerPollingChannel.changeContent(driver.userId, listener);
        //change
        AcceptPassengerRequestAPI.Response response = new AcceptPassengerRequestAPI.Response();
        response.isSuccess = false;
        PollingChannel<PollingDB.TripRequest> tripRequestPollingChannel = db.getChannel(PollingChannelName.TRIP_REQUEST);
        PollingDB.TripRequest rq = tripRequestPollingChannel.get(trip.tripId);
        if (rq != null && rq.requesterId == driver.userId) {
            rq.accepterId = driver.userId;
            tripRequestPollingChannel.change(trip.tripId, rq);
            response.isSuccess = true;
            //update trip
            // update user status
            driver.status = AC.UpdatedStatus.ON_ROAD;
            userDao.update(driver);
            //update trip status
            trip.driverId = driver.userId;
            trip.status = AC.UpdatedStatus.ON_ROAD;
            tripDao.update(trip);
        }
        return response;
    }

    @Override
    public DestroyTripAPI.Response destroyTrip(DestroyTripAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TTrip trip = tripDao.get(request.tripId);
        TUser user = userDao.get(request.userId);
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = new Converter().convertUpdatedLocation(user.currentLocation);
        userUpdated.type = AC.UpdatedStatus.DESTROYED;
        getUserUpdated.change(user.userId + "#" + request.tripId, userUpdated);
        // update user status
        user.status = AC.UpdatedStatus.DESTROYED;
        userDao.update(user);
        //update trip status
        trip.status = AC.UpdatedStatus.DESTROYED;
        if (request.reason != null)
            trip.reason = request.reason.description;
        trip.datetimeEndTrip = DateTimeUtils.now();
        trip.userDestroyTripId = request.userId;
        tripDao.update(trip);
        return null;
    }

    @Override
    public GetPassengerUpdatedAPI.Response getPassengerUpdated(GetPassengerUpdatedAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TUser passenger = userDao.get(request.passengerId);
        tripDao.get(request.tripId);
        PollingDB db = PollingDB.getInstance();
        PollingChannel<PollingDB.UserUpdated> getPassengerUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        long timeout = getPassengerUpdated.getTimeout();
        boolean changed = false;
        while (timeout > 0) {
            changed = getPassengerUpdated.subscribe(passenger.userId + "#" + request.tripId);
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
            response = new GetPassengerUpdatedAPI.Response(new Converter().convertUpdatedLocation(passenger.currentLocation));
            response.type = getPassengerUpdated.get(passenger.userId + "#" + request.tripId).type;
            return response;
        }
        throw new PinBikeException(AC.MessageCode.FAIL, "Polling time-out");
    }

    @Override
    public StartTripAPI.Response startTrip(StartTripAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TTrip trip = tripDao.get(request.tripId);
        TUser driver = userDao.get(request.driverId);
        TUser passenger = userDao.get(trip.passengerId);
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = new Converter().convertUpdatedLocation(driver.currentLocation);
        userUpdated.type = AC.UpdatedStatus.STARTED;
        getUserUpdated.change(driver.userId + "#" + request.tripId, userUpdated);
        // update user status
        driver.status = AC.UpdatedStatus.STARTED;
        passenger.status = AC.UpdatedStatus.STARTED;
        userDao.update(driver);
        userDao.update(passenger);
        //update trip status
        trip.status = AC.UpdatedStatus.STARTED;
        trip.datetimeStartTrip = DateTimeUtils.now();
        tripDao.update(trip);
        return null;
    }

    @Override
    public EndTripAPI.Response endTrip(EndTripAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        TTrip trip = tripDao.get(request.tripId);
        TUser driver = userDao.get(request.driverId);
        TUser passenger = userDao.get(trip.passengerId);
        PollingDB db = PollingDB.getInstance();
        //change
        PollingChannel<PollingDB.UserUpdated> getUserUpdated = db.getChannel(PollingChannelName.GET_USER_UPDATED);
        PollingDB.UserUpdated userUpdated = new PollingDB.UserUpdated();
        userUpdated.location = new Converter().convertUpdatedLocation(driver.currentLocation);
        userUpdated.type = AC.UpdatedStatus.ENDED;
        getUserUpdated.change(driver.userId + "#" + request.tripId, userUpdated);
        // update user status
        driver.status = AC.UpdatedStatus.ENDED;
        passenger.status = AC.UpdatedStatus.ENDED;
        userDao.update(driver);
        userDao.update(passenger);
        //update trip status
        trip.status = AC.UpdatedStatus.ENDED;
        trip.datetimeEndTrip = DateTimeUtils.now();
        tripDao.update(trip);
        return null;
    }

    @Override
    public RatingTripAPI.Response ratingTrip(RatingTripAPI.Request request) {
        TripDao tripDao = new TripDao();
        UserDao userDao = new UserDao();
        RatingDao ratingDao = new RatingDao();
        TUser user = userDao.get(request.userId);
        TTrip trip = tripDao.get(request.tripId);
        long partnerId = trip.driverId == user.userId ? trip.passengerId : trip.driverId;
        TRating rating = new TRating();
        rating.userId = partnerId;
        rating.ratingUserId = user.userId;
        rating.tripId = trip.tripId;
        rating.score = request.score;
        rating.comment = request.comment;
        rating.dateCreated = DateTimeUtils.now();
        ratingDao.insert(rating);
        //update user status
        user = userDao.get(request.userId);
        user.status = AC.UpdatedStatus.AVAILABLE;
        userDao.update(user);
        return null;
    }
}
