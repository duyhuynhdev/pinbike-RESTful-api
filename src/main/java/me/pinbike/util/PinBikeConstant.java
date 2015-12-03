package me.pinbike.util;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class PinBikeConstant {
    public static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

    public static class BackEndConfig{
        public static final String Host = "192.168.1.96";
        public static final int Port = 15121;
    }

    public static class MessageCode {
        public static final int Successfully = 1;
        public static final int Fail = 2;
        public static final int System_Exception = 3;
        public static final int Interactive_with_backend_fail = 4;
        public static final int Element_is_not_found = 5;
        public static final int Element_is_invalid = 6;
        public static final int Element_is_empty = 7;
        public static final int Element_has_been_used = 8;
        public static final int Element_is_invalid_or_used = 9;
        public static final int Element_expired = 10;
        public static final int Object_is_not_found = 11;
        public static final int Object_is_not_belong_to_you = 12;
        public static final int Request_has_accepted = 13;
        public static final int Request_has_denied = 14;
        public static final int Certificate_verified = 15;
        public static final int Certificate_expired = 16;
        public static final int User_is_busy = 17;
        public static final int User_and_angel_is_not_same_place_and_time = 18;
        public static final int Request_has_been_sent = 19;
    }
}
