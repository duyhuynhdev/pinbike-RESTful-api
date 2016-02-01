package me.pinbike.polling;

import com.google.gson.Gson;
import me.pinbike.util.DateTimeUtils;

import java.util.Hashtable;

/**
 * Created by hpduy17 on 12/7/15.
 */
public class PollingChannel<T> implements IPollingChannel {
    private Hashtable<String, PollingObject<T>> channel = new Hashtable<>();
    private int timeout;
    private int delay;
    private int acceptableTime = 30000;
    private String className = "";

    public PollingChannel(int timeout, int delay, String className) {
        this.timeout = timeout;
        this.delay = delay;
        this.className = className;
    }

    public PollingChannel(int timeout, int delay, int acceptableTime) {
        this.timeout = timeout;
        this.delay = delay;
        this.acceptableTime = acceptableTime;
    }

    public void remove(long key) {
        remove(String.valueOf(key));
    }

    public void remove(String key) {
        channel.remove(key);
    }

    public T get(long key) {
        return get(String.valueOf(key));
    }

    public T get(String key) {
        PollingObject<T> subscriber = channel.get(key);
        if (subscriber == null)
            return null;
        return subscriber.getContent();
    }

    public void change(long key, T content) {
        change(String.valueOf(key), content);
    }


    public void change(String key, T content) {
        PollingObject<T> subscriber = channel.get(key);
        if (subscriber == null) {
            subscriber = new PollingObject<>();
            channel.put(key, subscriber);
        }
        synchronized (subscriber) {
            subscriber.setIsChanged(true);
            subscriber.setChangedTimeInSecond(DateTimeUtils.now());
            subscriber.setContent(content);
            System.out.println("#########CHANGE at "+className+" \t" + new Gson().toJson(subscriber));
        }

    }

    public void changeContent(long key, T content) {
        changeContent(String.valueOf(key), content);
    }


    public void changeContent(String key, T content) {
        PollingObject<T> subscriber = channel.get(key);
        if (subscriber == null) {
            subscriber = new PollingObject<>();
            channel.put(key, subscriber);
        }
        subscriber.setContent(content);
    }

    public boolean subscribe(long key) {
        return subscribe(String.valueOf(key));
    }

    public boolean subscribe(String key) {
        if (channel.containsKey(key)) {
            PollingObject<T> subscriber = channel.get(key);
            synchronized (subscriber) {
                if (subscriber.isChanged() && (DateTimeUtils.now() - subscriber.getChangedTimeInSecond()) < acceptableTime) {
                    System.out.println("#########SUBCRIBE "+className+" \t"+ new Gson().toJson(subscriber));
                    subscriber.setIsChanged(false);
                    return true;
                }
            }
        }
        return false;
    }


    public int getTimeout() {
        return timeout;
    }

    public int getDelay() {
        return delay;
    }
}
