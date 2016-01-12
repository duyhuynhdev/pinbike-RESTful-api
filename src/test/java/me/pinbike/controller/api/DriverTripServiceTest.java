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
public class DriverTripServiceTest {
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
    public void testGetRequestFromPassengerAPI() throws Exception {
        GetRequestFromPassengerAPI.Request requestContent = factory.getRequestFromPassengerAPIRequest(testSet.passengerId);
        RequestWrapper<GetRequestFromPassengerAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(GetRequestFromPassengerAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testArrivedPickUpLocationAPI() throws Exception {
        ArrivedPickUpLocationAPI.Request requestContent = factory.getArrivedPickUpLocationAPIRequest(testSet.driverId,testSet.tripId);
        RequestWrapper<ArrivedPickUpLocationAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(ArrivedPickUpLocationAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testAcceptPassengerRequestAPI() throws Exception {
        AcceptPassengerRequestAPI.Request requestContent = factory.getAcceptPassengerRequestAPIRequest(testSet.driverId,testSet.tripId);
        RequestWrapper<AcceptPassengerRequestAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(AcceptPassengerRequestAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testDestroyTripAPI() throws Exception {
        DestroyTripAPI.Request requestContent = factory.getDestroyTripAPIRequest(testSet.tripId,testSet.passengerId);
        RequestWrapper<DestroyTripAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(DestroyTripAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testGetPassengerUpdatedAPI() throws Exception {
        GetPassengerUpdatedAPI.Request requestContent = factory.getPassengerUpdatedAPIRequest(testSet.passengerId, testSet.tripId);
        RequestWrapper<GetPassengerUpdatedAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(GetPassengerUpdatedAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testStartTripAPI() throws Exception {
        StartTripAPI.Request requestContent = factory.getStartTripAPIRequest(testSet.driverId, testSet.tripId);
        RequestWrapper<StartTripAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(StartTripAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testEndTripAPI() throws Exception {
        EndTripAPI.Request requestContent = factory.getEndTripAPIRequest(testSet.driverId, testSet.tripId);
        RequestWrapper<EndTripAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(EndTripAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testRatingTripAPI() throws Exception {
        RatingTripAPI.Request requestContent = factory.getRatingTripAPIRequest(testSet.tripId, testSet.driverId);
        RequestWrapper<RatingTripAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(RatingTripAPI.URL, request.toString());
        logger.info(response);
    }
}