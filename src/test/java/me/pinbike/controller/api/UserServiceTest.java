package me.pinbike.controller.api;

import me.pinbike.controller.TestRequester;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.ChangeAvailableStatusAPI;
import me.pinbike.sharedjava.model.GetDriverAroundAPI;
import me.pinbike.sharedjava.model.GetUserProfileAPI;
import me.pinbike.sharedjava.model.UpdateMyLocationAPI;
import me.pinbike.sharedjava.model.base.RequestWrapper;
import me.pinbike.util.LogUtil;
import me.pinbike.util.TestSet;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hpduy17 on 1/5/16.
 */
public class UserServiceTest {
    private TestRequester requester = new TestRequester();
    private Logger logger = LogUtil.getLogger(this.getClass());
    private ModelDataFactory factory = new ModelDataFactory();
    private TestSet testSet = TestSet.getInstance();

    @Before
    public void setUp() throws Exception {
        TestSet response = requester.request(TestSet.URL, "", TestSet.class);
        testSet.fetch(response);
        logger.info(response.toString());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetUserProfileAPI() throws Exception {
        GetUserProfileAPI.Request requestContent = factory.getUserProfileAPIRequest(testSet.driverId, testSet.passengerId);
        RequestWrapper<GetUserProfileAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(GetUserProfileAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testGetDriverAroundAPI() throws Exception {
        GetDriverAroundAPI.Request requestContent = factory.getDriverAroundAPIRequest();
        RequestWrapper<GetDriverAroundAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(GetDriverAroundAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testUpdateMyLocationAPI() throws Exception {
        UpdateMyLocationAPI.Request requestContent = factory.getUpdateMyLocationAPIRequest(testSet.driverId);
        RequestWrapper<UpdateMyLocationAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(UpdateMyLocationAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testChangeAvailableStatusAPI() throws Exception {
        ChangeAvailableStatusAPI.Request requestContent = factory.getChangeAvailableStatusAPIRequest(testSet.driverId, true);
        RequestWrapper<ChangeAvailableStatusAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(ChangeAvailableStatusAPI.URL, request.toString());
        logger.info(response);
    }
}