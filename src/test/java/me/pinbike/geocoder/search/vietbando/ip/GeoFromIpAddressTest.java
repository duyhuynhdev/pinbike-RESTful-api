package me.pinbike.geocoder.search.vietbando.ip;

import org.junit.Test;

/**
 * Created by hpduy17 on 1/23/16.
 */
public class GeoFromIpAddressTest {

    @Test
    public void testGetAddressFromIp() throws Exception {

    }

    @Test
    public void testGetLocation() throws Exception {
        System.out.print(new GeoFromIpAddress().getLocation("118.69.16.149"));
    }
}