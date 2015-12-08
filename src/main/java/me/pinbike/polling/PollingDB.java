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
        db.put(PollingChannelName.REQUEST_DRIVER, new PollingChannel<Acceptance>(90000, 1000));
        db.put(PollingChannelName.GET_REQUEST_FROM_PASSENGER, new PollingChannel<Request>(90000, 1000));
        db.put(PollingChannelName.ACCEPT_PASSENGER_REQUEST, new PollingChannel<Receipt>(90000, 1000));
        db.put(PollingChannelName.GET_USER_UPDATED, new PollingChannel<UserUpdated>(90000, 1000));
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

    public static class Request {
        public long tripId;
        public long accepterId;
        public long requesterId;
    }

    public static class Acceptance {
        public boolean isAccepted;
    }

    public static class Receipt {
        public boolean isReceived;
    }
}
