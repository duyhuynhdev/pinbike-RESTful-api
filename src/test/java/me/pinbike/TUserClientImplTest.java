/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.pinbike;

import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.framework.util.JSONUtil;
import com.pinride.pinbike.thrift.ErrorCode;
import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thrift.TOrganization;
import com.pinride.pinbike.thrift.TUser;
import com.pinride.pinbike.thriftclient.TBikeClientImpl;
import com.pinride.pinbike.thriftclient.TOrganizationClientImpl;
import com.pinride.pinbike.thriftclient.TUserClientImpl;
import com.pinride.pinbike.utils.TestUitls;
import junit.framework.TestCase;
import org.apache.commons.lang.math.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class TUserClientImplTest extends TestCase {

    String host = "pinride.ddns.net";
    int port = 9000;

    TUserClientImpl instance = TUserClientImpl.getInstance(host, port);

    /**
     * Test of getAll method, of class TUserClientImpl.
     */
    public void testGetAll() {
//        System.out.println("getAll");
//        AdapterResponseValue.ResponseListValue<TUser> expResult = null;
//        AdapterResponseValue.ResponseListValue<TUser> result = instance.getAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of gets method, of class TUserClientImpl.
     */
    public void testGets() {
        System.out.println("gets");
        List<Long> ids = new ArrayList<>();

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());

        ids.add(insert.getValue().getUserId());
        AdapterResponseValue.ResponseValue<TUser> insert1 = instance.insert(TestUitls.CreateUser());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert1.getErrorCode());
        ids.add(insert1.getValue().getUserId());

        AdapterResponseValue.ResponseValue<TUser> insert2 = instance.insert(TestUitls.CreateUser());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert2.getErrorCode());
        ids.add(insert2.getValue().getUserId());

        AdapterResponseValue.ResponseListValue<TUser> result = instance.gets(ids);
        System.out.println(result.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == result.getErrorCode());
        assertTrue(result.getList().size() == 3);
        System.out.println(JSONUtil.Serialize(result));
    }

    /**
     * Test of get method, of class TUserClientImpl.
     */
    public void testGet() {
        System.out.println("get");
        long id = 0L;
        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        id = insert.getValue().getUserId();

        AdapterResponseValue.ResponseValue<TUser> result = instance.get(id);
        assertTrue(result.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result));
    }

    /**
     * Test of remove method, of class TUserClientImpl.
     */
    public void testRemove() {
        System.out.println("remove");
        long id = 0L;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser().setOrganizationIds(new ArrayList<>()));
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        id = insert.getValue().getUserId();

        int expResult = ErrorCode.SUCCESS.getValue();
        int result = instance.remove(id);
        assertTrue(expResult == result);

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(id);
        assertTrue(result1.getErrorCode() == ErrorCode.NOT_EXISTS.getValue());
        System.out.println(JSONUtil.Serialize(result1));
    }

    /**
     * Test of update method, of class TUserClientImpl.
     */
    public void testUpdate() {
        System.out.println("update");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        int expResult = ErrorCode.SUCCESS.getValue();
        long dateChange = System.currentTimeMillis();
        int result = instance.update(item.setDateCreated(dateChange));
        assertTrue(expResult == result);

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));
        assertTrue(result1.getValue().getDateCreated() == dateChange);

    }

    /**
     * Test of insert method, of class TUserClientImpl.
     */
    public void testInsert() {
        System.out.println("insert");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));
    }

    /**
     * Test of updateAvailableDriver method, of class TUserClientImpl.
     */
    public void testUpdateAvailableDriver() {
        System.out.println("updateAvailableDriver");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));

        int result = instance.updateAvailableDriver(item.getUserId(), true);
        assertTrue(result == ErrorCode.SUCCESS.getValue());

        result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));
        assertTrue(result1.getValue().isAvailableDriver());
    }

    /**
     * Test of updateStatus method, of class TUserClientImpl.
     */
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));

        int result = instance.updateStatus(item.getUserId(), 10);
        assertTrue(result == ErrorCode.SUCCESS.getValue());

        result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));
        assertTrue(result1.getValue().getStatus() == 10);

    }

    /**
     * Test of getUserBySocial method, of class TUserClientImpl.
     */
    public void testGetUserBySocial() {
        System.out.println("getUserBySocial");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));

        AdapterResponseValue.ResponseValue<TUser> result = instance.getUserBySocial(item.getSocialId(), item.getSocialType());
        assertTrue(result.getErrorCode() == ErrorCode.SUCCESS.getValue());
        assertTrue(result.getValue().getEmail().equals(result1.getValue().getEmail()));

    }

    /**
     * Test of getUsersByOrganization method, of class TUserClientImpl.
     */
    public void testGetUsersByOrganization() {
        System.out.println("getUsersByOrganization");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));

        AdapterResponseValue.ResponseValue<TOrganization> insertO = TOrganizationClientImpl.getInstance(host, port)
                .insert(TestUitls.CreateOrganization().setUserIds(new ArrayList<>()));
        System.out.println(insertO.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insertO.getErrorCode());
        long organizationId = insertO.getValue().getOrganizationId();

        int addOrganizationForUser = instance.addOrganizationForUser(item.getUserId(), organizationId);
        assertTrue(ErrorCode.SUCCESS.getValue() == addOrganizationForUser);

        AdapterResponseValue.ResponseListValue<TUser> result = instance.getUsersByOrganization(organizationId);
        assertTrue(result.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result));
    }

    /**
     * Test of updateLocation method, of class TUserClientImpl.
     */
    public void testUpdateLocation() {
        System.out.println("updateLocation");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));

        double lat = RandomUtils.nextDouble();
        double lng = RandomUtils.nextDouble();
        int result = instance.updateLocation(item.getUserId(), lat, lng);
        assertTrue(result == ErrorCode.SUCCESS.getValue());

        result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));
        assertTrue(result1.getValue().getCurrentLocation().getLat() == lat);

    }

    /**
     * Test of changeCurrentBikeForUser method, of class TUserClientImpl.
     */
    public void testChangeCurrentBikeForUser() {
        System.out.println("changeCurrentBikeForUser");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));

        AdapterResponseValue.ResponseValue<TBike> insert1 = TBikeClientImpl.getInstance(host, port).insert(TestUitls.CreateBike().setUserId(insert.getValue().getUserId()));
        assertTrue(ErrorCode.SUCCESS.getValue() == insert1.getErrorCode());

        int result = instance.changeCurrentBikeForUser(item.getUserId(), insert1.getValue().getBikeId());
        assertTrue(ErrorCode.SUCCESS.getValue() == result);

        result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));
        assertTrue(result1.getValue().getCurrentBikeId() == insert1.getValue().getBikeId());

    }

    /**
     * Test of getDriverAround method, of class TUserClientImpl.
     */
    public void testGetDriverAround() {
        System.out.println("getDriverAround");
        AdapterResponseValue.ResponseListValue<TUser> result = instance.getDriverAround(0.1, 0.2);
        assertTrue(result.getErrorCode() == ErrorCode.SUCCESS.getValue());
        assertTrue(result.getTotal() != 0);
    }
//
//    /**
//     * Test of getNumberOfRating method, of class TUserClientImpl.
//     */
//    public void testGetNumberOfRating() {
//        System.out.println("getNumberOfRating");
//        long userId = 0L;
//        TUserClientImpl instance = null;
//        AdapterResponseValue.ResponseValue<Long> expResult = null;
//        AdapterResponseValue.ResponseValue<Long> result = instance.getNumberOfRating(userId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getScoreRating method, of class TUserClientImpl.
//     */
//    public void testGetScoreRating() {
//        System.out.println("getScoreRating");
//        long userId = 0L;
//        TUserClientImpl instance = null;
//        AdapterResponseValue.ResponseValue<Double> expResult = null;
//        AdapterResponseValue.ResponseValue<Double> result = instance.getScoreRating(userId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addOrganizationForUser method, of class TUserClientImpl.
     */
    public void testAddOrganizationForUser() {
        System.out.println("addOrganizationForUser");
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));

        AdapterResponseValue.ResponseValue<TOrganization> insertO = TOrganizationClientImpl.getInstance(host, port)
                .insert(TestUitls.CreateOrganization().setUserIds(new ArrayList<>()));
        System.out.println(insertO.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insertO.getErrorCode());
        long organizationId = insertO.getValue().getOrganizationId();

        int addOrganizationForUser = instance.addOrganizationForUser(item.getUserId(), organizationId);
        assertTrue(ErrorCode.SUCCESS.getValue() == addOrganizationForUser);

        AdapterResponseValue.ResponseListValue<TUser> result = instance.getUsersByOrganization(organizationId);
        assertTrue(result.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result));
    }

    /**
     * Test of removeBikeForUser method, of class TUserClientImpl.
     */
    public void testRemoveBikeForUser() {
        TUser item = null;

        AdapterResponseValue.ResponseValue<TUser> insert = instance.insert(TestUitls.CreateUser());
        System.out.println(insert.getErrorCode());
        assertTrue(ErrorCode.SUCCESS.getValue() == insert.getErrorCode());
        item = insert.getValue();

        AdapterResponseValue.ResponseValue<TUser> result1 = instance.get(item.getUserId());
        assertTrue(result1.getErrorCode() == ErrorCode.SUCCESS.getValue());
        System.out.println(JSONUtil.Serialize(result1));

        AdapterResponseValue.ResponseValue<TBike> insert1 = TBikeClientImpl.getInstance(host, port).insert(TestUitls.CreateBike().setUserId(insert.getValue().getUserId()));
        assertTrue(ErrorCode.SUCCESS.getValue() == insert1.getErrorCode());

        int removeBikeForUser = instance.removeBikeForUser(insert.getValue().getUserId(), insert1.getValue().getBikeId());
        assertTrue(removeBikeForUser == ErrorCode.SUCCESS.getValue());

    }

}
