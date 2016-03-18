package me.pinbike.controller.api;

/**
 * Created by hpduy17 on 10/12/15.
 */

import eu.bitwalker.useragentutils.UserAgent;
import me.pinbike.controller.adapter.UserAdapter;
import me.pinbike.controller.adapter.adapter_interface.IUserAdapter;
import me.pinbike.sharedjava.model.*;
import me.pinbike.sharedjava.model.base.RequestWrapper;
import me.pinbike.util.LogUtil;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.ResponseWrapper;
import me.pinbike.util.StringUtil;
import org.apache.log4j.Logger;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;

@Path("/")
@ValidateRequest
public class UserService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/GetUserProfileAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetUserProfileAPI.Response> GetUserProfileAPI(@Valid RequestWrapper<GetUserProfileAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        GetUserProfileAPI.Response responseContent;
        GetUserProfileAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getUserProfile(requestContent);
        ResponseWrapper<GetUserProfileAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/GetUserRatingsAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetUserRatingsAPI.Response> GetUserRatingsAPI(@Valid RequestWrapper<GetUserRatingsAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        GetUserRatingsAPI.Response responseContent;
        GetUserRatingsAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getUserRatings(requestContent);
        ResponseWrapper<GetUserRatingsAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }


    @POST
    @Path("/GetDriverAroundAPI") //TODO: LONG POLLING
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetDriverAroundAPI.Response> GetDriverAroundAPI(@Valid RequestWrapper<GetDriverAroundAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        GetDriverAroundAPI.Response responseContent;
        GetDriverAroundAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getDriverAround(requestContent);
        ResponseWrapper<GetDriverAroundAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }


    @POST
    @Path("/UpdateMyLocationAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<UpdateMyLocationAPI.Response> UpdateMyLocationAPI(@Valid RequestWrapper<UpdateMyLocationAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        UpdateMyLocationAPI.Response responseContent;
        UpdateMyLocationAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.updateMyLocation(requestContent);
        ResponseWrapper<UpdateMyLocationAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/ChangeAvailableStatusAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<ChangeAvailableStatusAPI.Response> ChangeAvailableStatusAPI(@Valid RequestWrapper<ChangeAvailableStatusAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        ChangeAvailableStatusAPI.Response responseContent;
        ChangeAvailableStatusAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.changeAvailableStatus(requestContent);
        ResponseWrapper<ChangeAvailableStatusAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/GetActivationCodeAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetActivationCodeAPI.Response> GetActivationCodeAPI(@Valid RequestWrapper<GetActivationCodeAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        GetActivationCodeAPI.Response responseContent;
        GetActivationCodeAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getActivationCode(requestContent);
        ResponseWrapper<GetActivationCodeAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/ActivatePhoneNumberAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<ActivatePhoneNumberAPI.Response> ActivatePhoneNumberAPI(@Valid RequestWrapper<ActivatePhoneNumberAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        ActivatePhoneNumberAPI.Response responseContent;
        ActivatePhoneNumberAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.activatePhoneNumber(requestContent);
        ResponseWrapper<ActivatePhoneNumberAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/RegisterAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<RegisterAPI.Response> RegisterAPI(@Valid RequestWrapper<RegisterAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        RegisterAPI.Response responseContent;
        RegisterAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.register(requestContent);
        ResponseWrapper<RegisterAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/LogoutAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<LogoutAPI.Response> LogoutAPI(@Valid RequestWrapper<LogoutAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        LogoutAPI.Response responseContent;
        LogoutAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.logout(requestContent);
        ResponseWrapper<LogoutAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @POST
    @Path("/LoginByEmailAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<LoginByEmailAPI.Response> LoginByEmailAPI(@Valid RequestWrapper<LoginByEmailAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        LoginByEmailAPI.Response responseContent;
        LoginByEmailAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.loginByEmail(requestContent);
        ResponseWrapper<LoginByEmailAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @POST
    @Path("/LoginBySocialAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<LoginBySocialAPI.Response> LoginBySocialAPI(@Valid RequestWrapper<LoginBySocialAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        LoginBySocialAPI.Response responseContent;
        LoginBySocialAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.loginBySocial(requestContent);
        ResponseWrapper<LoginBySocialAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }


    @POST
    @Path("/ForgotPasswordAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<ForgotPasswordAPI.Response> ForgotPasswordAPI(@Valid RequestWrapper<ForgotPasswordAPI.Request> request, @Context HttpServletRequest req) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        ForgotPasswordAPI.Response responseContent;
        ForgotPasswordAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.forgotPassword(requestContent, request.os, request.deviceModel, req.getRemoteAddr());
        ResponseWrapper<ForgotPasswordAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @POST
    @Path("/ChangePasswordAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<ChangePasswordAPI.Response> ChangePasswordAPI(@Valid RequestWrapper<ChangePasswordAPI.Request> request, @Context HttpServletRequest req) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        ChangePasswordAPI.Response responseContent;
        ChangePasswordAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.changePassword(requestContent, request.os, request.deviceModel, req.getRemoteAddr());
        ResponseWrapper<ChangePasswordAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @GET
    @Path("/ChangePasswordFromMailAPI")
    public String ChangePasswordFromMailAPI(@QueryParam("p") String p, @QueryParam("a") String a, @QueryParam("e") String e, @Context HttpServletRequest req) throws IOException {
        UserAgent userAgent = null;
        try {
            userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
        } catch (Exception ignored) {
        }
        if (new StringUtil().DecryptText(a).equals(PinBikeConstant.accesskey)) {
            ChangePasswordAPI.Request request = new ChangePasswordAPI.Request();
            request.newPassword = new StringUtil().DecryptText(p);
            request.email = e;
            request.deviceId = new StringUtil().DecryptText(a);
            RequestWrapper<ChangePasswordAPI.Request> requestWrapper = new RequestWrapper<>();
            requestWrapper.os = userAgent == null ? "Unknown" : userAgent.getOperatingSystem().getName();
            requestWrapper.deviceModel = userAgent == null ? "Unknown" : userAgent.getBrowser().getName();
            requestWrapper.requestContent = request;
            ResponseWrapper<ChangePasswordAPI.Response> response = ChangePasswordAPI(requestWrapper, req);
            if (response.isSuccess()) {
                return "Change password success";
            } else {
                return response.getMessage();
            }
        } else {
            return "Access key is invalid";
        }
    }


    @POST
    @Path("/UpdateUserAvatarAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<UpdateUserAvatarAPI.Response> UpdateUserAvatarAPI(@Valid RequestWrapper<UpdateUserAvatarAPI.Request> request, @Context HttpServletRequest req) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        UpdateUserAvatarAPI.Response responseContent;
        UpdateUserAvatarAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.updateUserAvatar(requestContent);
        ResponseWrapper<UpdateUserAvatarAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @POST
    @Path("/UpdateUserSocialAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<UpdateUserSocialAPI.Response> UpdateUserSocialAPI(@Valid RequestWrapper<UpdateUserSocialAPI.Request> request, @Context HttpServletRequest req) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        UpdateUserSocialAPI.Response responseContent;
        UpdateUserSocialAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.updateUserSocial(requestContent);
        ResponseWrapper<UpdateUserSocialAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }


    @POST
    @Path("/UpdateUserPhoneNumberAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<UpdateUserPhoneNumberAPI.Response> UpdateUserPhoneNumberAPI(@Valid RequestWrapper<UpdateUserPhoneNumberAPI.Request> request, @Context HttpServletRequest req) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        UpdateUserPhoneNumberAPI.Response responseContent;
        UpdateUserPhoneNumberAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.updateUserPhoneNumber(requestContent);
        ResponseWrapper<UpdateUserPhoneNumberAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @POST
    @Path("/GetLocationUpdatedAPI")//TODO: LONG POLLING
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetLocationUpdatedAPI.Response> GetLocationUpdatedAPI(@Valid RequestWrapper<GetLocationUpdatedAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        GetLocationUpdatedAPI.Response responseContent;
        GetLocationUpdatedAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getLocationUpdated(requestContent);
        ResponseWrapper<GetLocationUpdatedAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/UpdateUserEnglishCommunicateAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<UpdateUserEnglishCommunicateAPI.Response> UpdateUserEnglishCommunicateAPI(@Valid RequestWrapper<UpdateUserEnglishCommunicateAPI.Request> request) throws IOException {
        IUserAdapter adapter = new UserAdapter();

        UpdateUserEnglishCommunicateAPI.Response responseContent;
        UpdateUserEnglishCommunicateAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.updateUserEnglishCommunicate(requestContent);
        ResponseWrapper<UpdateUserEnglishCommunicateAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

}


