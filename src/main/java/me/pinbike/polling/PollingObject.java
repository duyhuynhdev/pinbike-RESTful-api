package me.pinbike.polling;

/**
 * Created by hpduy17 on 12/7/15.
 */
public class PollingObject<T> {
    private boolean isChanged;
    private long changedTimeInSecond;
    private T content;

    public synchronized boolean isChanged() {
        return isChanged;
    }

    public synchronized void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    public synchronized long getChangedTimeInSecond() {
        return changedTimeInSecond;
    }

    public synchronized void setChangedTimeInSecond(long changedTimeInSecond) {
        this.changedTimeInSecond = changedTimeInSecond;
    }

    public synchronized T getContent() {
        return content;
    }

    public synchronized void setContent(T content) {
        this.content = content;
    }
}
