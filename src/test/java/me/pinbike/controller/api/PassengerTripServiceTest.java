package me.pinbike.controller.api;

import me.pinbike.controller.TestRequester;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.*;
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
public class PassengerTripServiceTest {
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
    public void testCreateTripAPI() throws Exception {
        CreateTripAPI.Request requestContent = factory.getCreateTripAPIRequest(testSet.passengerId);
        RequestWrapper<CreateTripAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.passengerId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(CreateTripAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testCancelTripAPI() throws Exception {
        CancelTripAPI.Request requestContent = factory.getCancelTripAPIRequest(testSet.passengerId, 331);
        RequestWrapper<CancelTripAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.passengerId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(CancelTripAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testRequestDriverAPI() throws Exception {
        RequestDriverAPI.Request requestContent = factory.getRequestDriverAPIRequest(testSet.driverId, testSet.tripId, testSet.passengerId);
        RequestWrapper<RequestDriverAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.passengerId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(RequestDriverAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testGetDriverUpdatedAPI() throws Exception {
        GetDriverUpdatedAPI.Request requestContent = factory.getDriverUpdatedAPIRequest(testSet.driverId, testSet.tripId);
        RequestWrapper<GetDriverUpdatedAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.passengerId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(GetDriverUpdatedAPI.URL, request.toString());
        logger.info(response);
    }
}