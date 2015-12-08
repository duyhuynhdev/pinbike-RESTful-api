package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.base.Bike;
import me.pinbike.sharedjava.model.base.Location;
import org.junit.Test;

/**
 * Created by hpduy17 on 12/5/15.
 */
public class ModelDataFactoryTest {

    @Test
    public void testGetBike() throws Exception {
        for(int i = 0 ; i < 10; i ++){
            ModelDataFactory factory = new ModelDataFactory();
            Bike bike = factory.getBike();
            System.out.println(bike.toString());
        }

    }

    @Test
    public void testGetLatLng() throws Exception {

    }

    @Test
    public void testGetLocation() throws Exception {
        for(int i = 0 ; i < 10; i ++){
            ModelDataFactory factory = new ModelDataFactory();
            Location bike = factory.getLocation(10.123,106.789,100);
            System.out.println(bike.toString());
        }
    }

    @Test
    public void testGetDescription() throws Exception {

    }

    @Test
    public void testGetOrganization() throws Exception {

    }

    @Test
    public void testGetRating() throws Exception {

    }

    @Test
    public void testGetTripDetail() throws Exception {

    }

    @Test
    public void testGetUpdatedLocation() throws Exception {

    }

    @Test
    public void testGetUpdatedLocation1() throws Exception {

    }

    @Test
    public void testGetUpdatedStatus() throws Exception {

    }

    @Test
    public void testGetUserDetail() throws Exception {
        for(int i = 0 ; i < 1000; i++) {
            System.out.println(new ModelDataFactory().getUserDetail().birthday);
        }

    }

    @Test
    public void testGetUserReason() throws Exception {

    }

    @Test
    public void testJsonToObject() throws Exception {

    }
}