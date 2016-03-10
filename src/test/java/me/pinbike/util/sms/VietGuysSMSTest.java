package me.pinbike.util.sms;

import org.junit.Test;

/**
 * Created by hpduy17 on 3/10/16.
 */
public class VietGuysSMSTest {

    @Test
    public void testSend() throws Exception {
        new VietGuysSMS().send("01692715716","hello%20pinbike");
    }
}