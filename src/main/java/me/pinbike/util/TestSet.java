package me.pinbike.util;

import com.google.gson.Gson;
import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thrift.TTrip;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.dao.BikeDao;
import me.pinbike.dao.TripDao;
import me.pinbike.dao.UserDao;
import me.pinbike.sharedjava.model.constanst.AC;

/**
 * Created by hpduy17 on 1/4/16.
 */

public class TestSet {
    public static final String URL = AC.MAIN_SERVER + "GetTestSetAPI";
    public TUser passenger;
    public TUser driver;
    public TBike bike;
    public TTrip trip;

    private static TestSet ourInstance = new TestSet();

    public static TestSet getInstance() {
        return ourInstance;
    }

    private TestSet() {

    }

    public void fetch(TestSet that) {
        this.passenger = that.passenger;
        this.driver = that.driver;
        this.bike = that.bike;
        this.trip = that.trip;

    }

    public void setup() {
        // fill data
        passenger = new ModelDataFactory().getTUser();
        driver = new ModelDataFactory().getTUser();
        bike = new ModelDataFactory().getTBike(driver.userId);
        trip = new ModelDataFactory().getTTrip(passenger.userId);
        // insert
        passenger = new UserDao().insert(passenger);
        driver = new UserDao().insert(driver);
        bike = new BikeDao().insert(bike);
        trip = new TripDao().insert(trip);
    }

    public void removeAll() {
        try {
            new UserDao().delete(passenger.userId);
        } catch (Exception ignored) {
        }
        try {
            new UserDao().delete(driver.userId);
        } catch (Exception ignored) {
        }
        try {

            new BikeDao().delete(bike.bikeId);
        } catch (Exception ignored) {
        }
        try {
            new TripDao().delete(trip.tripId);
        } catch (Exception ignored) {
        }
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
