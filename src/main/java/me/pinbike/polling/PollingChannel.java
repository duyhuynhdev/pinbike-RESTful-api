package me.pinbike.polling;

import me.pinbike.util.DateTimeUtils;

import java.util.Hashtable;

/**
 * Created by hpduy17 on 12/7/15.
 */
public class PollingChannel<T> implements IPollingChannel {
    private Hashtable<String, PollingObject<T>> channel = new Hashtable<>();
    private int timeout;
    private int delay;
    private int acceptableTime = 15;

    public PollingChannel(int timeout, int delay) {
        this.timeout = timeout;
        this.delay = delay;
    }

    public PollingChannel(int timeout, int delay, int acceptableTime) {
        this.timeout = timeout;
        this.delay = delay;
        this.acceptableTime = acceptableTime;
    }

    public void change(long key, T content) {
        change(String.valueOf(key), content);
    }

    public T get(long key) {
        return get(String.valueOf(key));
    }

    public T get(String key) {
        PollingObject<T> subscriber = channel.get(key);
        return subscriber.getContent();
    }

    public void change(String key, T content) {
        PollingObject<T> subscriber = channel.get(key);
        if (subscriber == null) {
            subscriber = new PollingObject<>();
            channel.put(key, subscriber);
        }
        subscriber.setIsChanged(true);
        subscriber.setChangedTimeInSecond(DateTimeUtils.now());
        subscriber.setContent(content);
    }

    public T subscribe(long key) {
        return subscribe(String.valueOf(key));
    }

    public T subscribe(String key) {
        if (channel.containsKey(key)) {
            PollingObject<T> object = channel.get(key);
            if (object.isChanged() && (DateTimeUtils.now() - object.getChangedTimeInSecond()) < acceptableTime) {
                object.setIsChanged(false);
                return channel.get(key).getContent();
            }
        }
        return null;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getDelay() {
        return delay;
    }
}
