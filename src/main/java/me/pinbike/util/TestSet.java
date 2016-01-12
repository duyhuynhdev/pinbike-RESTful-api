package me.pinbike.util;

import com.google.gson.Gson;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.dao.BikeDao;
import me.pinbike.dao.TripDao;
import me.pinbike.dao.UserDao;
import me.pinbike.sharedjava.model.constanst.AC;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by hpduy17 on 1/4/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestSet {
    @JsonIgnore
    public static final String URL = AC.MAIN_SERVER + "GetTestSetAPI";
    @JsonIgnore
    public boolean installed = false;

    public long passengerId;
    public long driverId;
    public long bikeId;
    public long tripId;
    private static TestSet ourInstance = new TestSet();

    public static TestSet getInstance() {
        return ourInstance;
    }

    private TestSet() {

    }

    public void fetch(TestSet that) {
        this.passengerId = that.passengerId;
        this.driverId = that.driverId;
        this.bikeId = that.bikeId;
        this.tripId = that.tripId;

    }

    public void setup() {
        // fill data
        if (installed)
            return;
        passengerId = new UserDao().insert(new ModelDataFactory().getTUser()).userId;
        driverId = new UserDao().insert(new ModelDataFactory().getTUser()).userId;
        bikeId = new BikeDao().insert(new ModelDataFactory().getTBike(driverId)).bikeId;
        tripId = new TripDao().insert(new ModelDataFactory().getTTrip(passengerId)).tripId;
        installed = true;
    }

    public void removeAll() {
        try {
            new UserDao().delete(passengerId);
        } catch (Exception ignored) {
        }
        try {
            new UserDao().delete(driverId);
        } catch (Exception ignored) {
        }
        try {

            new BikeDao().delete(bikeId);
        } catch (Exception ignored) {
        }
        try {
            new TripDao().delete(tripId);
        } catch (Exception ignored) {
        }
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


}
