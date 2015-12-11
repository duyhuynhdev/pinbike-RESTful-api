package me.pinbike.geocoder.search.vietbando.common;

/**
 * Created by hpduy17 on 12/11/15.
 */
public class ResponseObject {
    public Error Error;
    public boolean IsSuccess;
    public String ResponseTime;
    public int TotalCount;
    public boolean HasMoreItem;

    public static class Error {
        public String ExceptionType;
        public String Message;
    }
}
