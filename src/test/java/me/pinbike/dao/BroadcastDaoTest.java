package me.pinbike.dao;

import com.pinride.pinbike.config.Const;
import com.pinride.pinbike.thrift.TBroadcast;
import com.pinride.pinbike.thrift.TUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hpduy17 on 1/3/16.
 */
public class BroadcastDaoTest {
    private BroadcastDao dao = new BroadcastDao();
    private UserDao userDao = new UserDao();
    private long broadcastId;

    @Before
    public void setUp() throws Exception {
        TUser user = new TUser();
        user = userDao.insert(user);
        TBroadcast broadcast = new TBroadcast();
        broadcast.userId = user.userId;
        broadcast.deviceId = "deviceId";
        broadcast.active = true;
        broadcast.regId = "regId";
        broadcast.os = Const.PinBike.BroadcastOS.IOS;
        broadcastId = dao.insert(broadcast).broadcastId;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInsert() throws Exception {
        TUser user = new TUser();
        user = userDao.insert(user);
        TBroadcast broadcast = new TBroadcast();
        broadcast.userId = user.userId;
        broadcast.deviceId = "deviceId";
        broadcast.active = true;
        broadcast.regId = "regId";
        broadcast.os = Const.PinBike.BroadcastOS.IOS;
        TBroadcast response = dao.insert(broadcast);
        assert broadcast.userId == response.userId;
        assert broadcast.deviceId.equals(response.deviceId);
        assert broadcast.regId.equals(response.regId);
        assert broadcast.os == response.os;
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(broadcastId);
        assert dao.get(broadcastId) == null;
    }

    @Test
    public void testGet() throws Exception {
        TBroadcast broadcast = dao.get(broadcastId);
        assert broadcast != null;
    }

    @Test
    public void testGetList() throws Exception {
        assert dao.getList(new ArrayList<>(Arrays.asList(broadcastId))) != null;
        assert !dao.getList(new ArrayList<>(Arrays.asList(broadcastId))).isEmpty();
    }
}