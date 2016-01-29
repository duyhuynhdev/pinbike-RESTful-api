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

    @Test
    public void testGetActivationCodeAPI() throws Exception {
        GetActivationCodeAPI.Request requestContent = factory.getActivationCodeAPIRequest("0908587305");
        RequestWrapper<GetActivationCodeAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(GetActivationCodeAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testActivatePhoneNumberAPI() throws Exception {
        String activationCode = "9249";
        ActivatePhoneNumberAPI.Request requestContent = factory.getActivatePhoneNumberAPIResquest("0908587305",activationCode);
        RequestWrapper<ActivatePhoneNumberAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(ActivatePhoneNumberAPI.URL, request.toString());
        logger.info(response);
    }


    @Test
    public void testRegisterAPI() throws Exception {
        RegisterAPI.Request requestContent = factory.getRegisterAPIForDuyRequest();
        RequestWrapper<RegisterAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(RegisterAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testLoginByEmailAPI() throws Exception {
        LoginByEmailAPI.Request requestContent = factory.getLoginByEmailAPIRequest("hpduy17@gmail.com");
        RequestWrapper<LoginByEmailAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(LoginByEmailAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testLoginBySocialAPI() throws Exception {
        LoginBySocialAPI.Request requestContent = factory.getLoginBySocialAPIRequest();
        RequestWrapper<LoginBySocialAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(LoginBySocialAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testLogoutAPI() throws Exception {

        LogoutAPI.Request requestContent = factory.getLogoutAPIRequest(2855, "badly good should in do else came island is headphones when ends was sidekick on write write came leader this ");
        RequestWrapper<LogoutAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(LogoutAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testForgotPasswordAPI() throws Exception {
        ForgotPasswordAPI.Request requestContent = factory.getForgotPasswordAPIRequest();
        RequestWrapper<ForgotPasswordAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(ForgotPasswordAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testChangePasswordAPI() throws Exception {
        ChangePasswordAPI.Request requestContent = factory.getChangePasswordAPIRequest(2855,"pinbike2", "badly good should in do else came island is headphones when ends was sidekick on write write came leader this ");
        RequestWrapper<ChangePasswordAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(ChangePasswordAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testUpdateUserAvatarAPI() throws Exception {
        UpdateUserAvatarAPI.Request requestContent = factory.getUpdateUserAvatarAPIRequest(2855);
        RequestWrapper<UpdateUserAvatarAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(UpdateUserAvatarAPI.URL, request.toString());
        logger.info(response);
    }

    @Test
    public void testUpdateUserPhoneNumberAPI() throws Exception {
        UpdateUserPhoneNumberAPI.Request requestContent = factory.getUpdateUserPhoneNumberAPIRequest(2855, "1080","01208031857");
        RequestWrapper<UpdateUserPhoneNumberAPI.Request> request = new RequestWrapper<>();
        request.accessKey = testSet.driverId;
        request.requestContent = requestContent;
        logger.info(request.toString());
        String response = requester.request(UpdateUserPhoneNumberAPI.URL, request.toString());
        logger.info(response);
    }

}