package me.pinbike.dao;

import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.base.Bike;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hpduy17 on 1/3/16.
 */
public class BikeDaoTest {
    private BikeDao dao = new BikeDao();
    private UserDao userDao = new UserDao();
    private long bikeId;

    @Before
    public void setUp() throws Exception {
        TUser user = new TUser();
        user = userDao.insert(user);
        TBike bike = new TBike();
        Bike data = new ModelDataFactory().getBike();
        bike.description = data.description;
        bike.licensePlate = data.licensePlate;
        bike.model = data.model;
        bike.userId = user.userId;
        bikeId = dao.insert(bike).bikeId;

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() throws Exception {
        TUser user = new TUser();
        user = userDao.insert(user);
        TBike bike = new TBike();
        Bike data = new ModelDataFactory().getBike();
        bike.description = data.description;
        bike.licensePlate = data.licensePlate;
        bike.model = data.model;
        bike.userId = user.userId;
        TBike response = dao.insert(bike);
        assert bike.description.equals(response.description);
        assert bike.licensePlate.equals(response.licensePlate);
        assert bike.model.equals(response.model);
    }

    @Test
    public void testUpdate() throws Exception {
        TBike bike = dao.get(bikeId);
        bike.description = "update description";
        bike.licensePlate = "update licensePlate";
        bike.model = "update model";
        dao.update(bike);
        assert bike.description.equals("update description");
        assert bike.licensePlate.equals("update licensePlate");
        assert bike.model.equals("update model");
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(bikeId);
        TBike bike = dao.get(bikeId);
        assert bike == null;
    }

    @Test
    public void testGet() throws Exception {
        TBike bike = dao.get(bikeId);
        assert bike != null;
    }

    @Test
    public void testGetList() throws Exception {
        assert dao.getList(new ArrayList<>(Arrays.asList(bikeId))) != null;
        assert !dao.getList(new ArrayList<>(Arrays.asList(bikeId))).isEmpty();
    }
}