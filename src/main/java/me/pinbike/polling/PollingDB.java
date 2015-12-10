package me.pinbike.polling;

import me.pinbike.sharedjava.model.base.UpdatedLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hpduy17 on 12/7/15.
 */
public class PollingDB {
    private static PollingDB ourInstance = new PollingDB();

    public static PollingDB getInstance() {
        return ourInstance;
    }

    private PollingDB() {
        db.put(PollingChannelName.WAITING_REQUEST, new PollingChannel<Listener>(30000, 1000)); // KEY DRIVER ID
        db.put(PollingChannelName.TRIP_REQUEST, new PollingChannel<TripRequest>(30000, 1000)); // KEY TRIP ID
        db.put(PollingChannelName.GET_USER_UPDATED, new PollingChannel<UserUpdated>(30000, 1000));// KEY USER ID
    }

    private Map<PollingChannelName, IPollingChannel> db = new HashMap<>();

    public <T> PollingChannel<T> getChannel(PollingChannelName name) {
        if (db.containsKey(name))
            return (PollingChannel<T>) db.get(name);
        return null;
    }

    public static class UserUpdated {
        public int type;
        public UpdatedLocation location;
    }

    public static class TripRequest {
        public long tripId;
        public long accepterId;
        public long requesterId;
    }

    public static class Listener {
        public boolean isAvailable;
        public long tripId;
        public long passengerId;
    }

}
