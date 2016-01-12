package me.pinbike.controller.api;

import me.pinbike.controller.TestRequester;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.AddBikeAPI;
import me.pinbike.sharedjava.model.UpdateMyCurrentBikeAPI;
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
public class BikeServiceTest {
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
    public void testAddBikeAPI() throws Exception {
        AddBikeAPI.Request requestContent = factory.getAddBikeAPIRequest(testSet.driverId);
        RequestWrapper<AddBikeAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(AddBikeAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testUpdateMyCurrentBikeAPI() throws Exception {
        UpdateMyCurrentBikeAPI.Request requestContent = factory.getUpdateMyCurrentBikeAPIRequest(testSet.driverId, testSet.bikeId);
        RequestWrapper<UpdateMyCurrentBikeAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(UpdateMyCurrentBikeAPI.URL, request.toString());
        logger.info(response.toString());
    }
}