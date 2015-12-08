package me.pinbike.polling;

/**
 * Created by hpduy17 on 12/7/15.
 */
public class PollingObject<T> {
    private boolean isChanged;
    private long changedTimeInSecond;
    private T content;

    public boolean isChanged() {
        return isChanged;
    }

    public void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    public long getChangedTimeInSecond() {
        return changedTimeInSecond;
    }

    public void setChangedTimeInSecond(long changedTimeInSecond) {
        this.changedTimeInSecond = changedTimeInSecond;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
