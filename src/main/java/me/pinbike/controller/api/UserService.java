package me.pinbike.controller.api;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.controller.adapter.UserAdapter;
import me.pinbike.controller.adapter.adapter_interface.IUserAdapter;
import me.pinbike.sharedjava.model.ChangeAvailableStatusAPI;
import me.pinbike.sharedjava.model.GetDriverAroundAPI;
import me.pinbike.sharedjava.model.GetUserProfileAPI;
import me.pinbike.sharedjava.model.UpdateMyLocationAPI;
import me.pinbike.sharedjava.model.base.RequestWrapper;
import me.pinbike.util.LogUtil;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.ResponseWrapper;
import org.apache.log4j.Logger;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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


}


