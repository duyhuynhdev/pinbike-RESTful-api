package me.pinbike.controller.adapter;

import me.pinbike.sharedjava.model.AddBikeAPI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hpduy17 on 12/5/15.
 */
public class BikeAdapterTempTest {

    @Before
    public void setUp(
        ) throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddBike() throws Exception {
        System.out.println(new BikeAdapterTemp().addBike(new AddBikeAPI.Request()).toString());
    }

    @Test
    public void testUpdateMyCurrentBike() throws Exception {

    }
}