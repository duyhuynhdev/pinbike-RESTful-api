package me.pinbike.controller.api;

import me.pinbike.controller.adapter.VerificationAdapter;
import me.pinbike.controller.adapter.adapter_interface.IVerificationAdapter;
import me.pinbike.sharedjava.model.*;
import me.pinbike.sharedjava.model.base.RequestWrapper;
import me.pinbike.util.LogUtil;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.ResponseWrapper;
import org.apache.log4j.Logger;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.Valid;
import javax.ws.rs.*;
import java.io.IOException;

/**
 * Created by hpduy17 on 2/17/16.
 */
@Path("/")
@ValidateRequest
public class VerificationService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/AddVerificationAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<AddVerificationAPI.Response> AddVerificationAPI(@Valid RequestWrapper<AddVerificationAPI.Request> request) throws IOException {

        IVerificationAdapter adapter = new VerificationAdapter();

        AddVerificationAPI.Response responseContent;
        AddVerificationAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.addVerification(requestContent);
        ResponseWrapper<AddVerificationAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @POST
    @Path("/RequestVerifyAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<RequestVerifyAPI.Response> RequestVerifyAPI(@Valid RequestWrapper<RequestVerifyAPI.Request> request) throws IOException {

        IVerificationAdapter adapter = new VerificationAdapter();

        RequestVerifyAPI.Response responseContent;
        RequestVerifyAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.requestVerifyAPI(requestContent);
        ResponseWrapper<RequestVerifyAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }


    @POST
    @Path("/ConfirmVerifiedUserAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<ConfirmVerifiedUserAPI.Response> ConfirmVerifiedUserAPI(@Valid RequestWrapper<ConfirmVerifiedUserAPI.Request> request) throws IOException {

        IVerificationAdapter adapter = new VerificationAdapter();

        ConfirmVerifiedUserAPI.Response responseContent;
        ConfirmVerifiedUserAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.confirmVerifiedUserAPI(requestContent);
        ResponseWrapper<ConfirmVerifiedUserAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @GET
    @Path("/ConfirmVerifiedUserViaEmail")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<ConfirmVerifiedUserAPI.Response> ConfirmVerifiedUserViaEmail(@QueryParam("userId") long userId) throws IOException {

        ConfirmVerifiedUserAPI.Request requestContent = new ConfirmVerifiedUserAPI.Request();
        requestContent.userId = userId;
        IVerificationAdapter adapter = new VerificationAdapter();

        ConfirmVerifiedUserAPI.Response responseContent;

        logger.info(requestContent.getClass().getSimpleName() + ":" + requestContent.toString());
        responseContent = adapter.confirmVerifiedUserAPI(requestContent);
        ResponseWrapper<ConfirmVerifiedUserAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @POST
    @Path("/GetVerifiedContactOfflineAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetVerifiedContactOfflineAPI.Response> GetVerifiedContactOfflineAPI(@Valid RequestWrapper<GetVerifiedContactOfflineAPI.Request> request) throws IOException {

        IVerificationAdapter adapter = new VerificationAdapterg();

        GetVerifiedContactOfflineAPI.Response responseContent;
        GetVerifiedContactOfflineAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getVerifiedContactOfflineAPI(requestContent);
        ResponseWrapper<GetVerifiedContactOfflineAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }

    @POST
    @Path("/GetUserVerifiedStatusAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetUserVerifiedStatusAPI.Response> GetUserVerifiedStatusAPI(@Valid RequestWrapper<GetUserVerifiedStatusAPI.Request> request) throws IOException {

        IVerificationAdapter adapter = new VerificationAdapter();

        GetUserVerifiedStatusAPI.Response responseContent;
        GetUserVerifiedStatusAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getUserVerifiedStatusAPI(requestContent);
        ResponseWrapper<GetUserVerifiedStatusAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }
}
