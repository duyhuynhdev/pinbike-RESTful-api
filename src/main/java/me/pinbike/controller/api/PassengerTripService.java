package me.pinbike.controller.api;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.controller.adapter.PassengerTripAdapterTemp;
import me.pinbike.controller.adapter.adapter_interface.IPassengerTripAdapter;
import me.pinbike.sharedjava.model.*;
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
public class PassengerTripService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/CreateTripAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<CreateTripAPI.Response> CreateTripAPI(@Valid RequestWrapper<CreateTripAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapterTemp();

        CreateTripAPI.Response responseContent;
        CreateTripAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.createTrip(requestContent);
        logger.info(responseContent.getClass().getSimpleName() + ":" + responseContent.toString());

        return new ResponseWrapper<>(responseContent);
    }


    @POST
    @Path("/CancelTripAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<CancelTripAPI.Response> CancelTripAPI(@Valid RequestWrapper<CancelTripAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapterTemp();

        CancelTripAPI.Response responseContent;
        CancelTripAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.cancelTrip(requestContent);
        logger.info(responseContent.getClass().getSimpleName() + ":" + responseContent.toString());

        return new ResponseWrapper<>();
    }


    @POST
    @Path("/RequestDriverAPI") //TODO: LONG POLLING
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<RequestDriverAPI.Response> RequestDriverAPI(@Valid RequestWrapper<RequestDriverAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapterTemp();

        RequestDriverAPI.Response responseContent;
        RequestDriverAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.requestDriver(requestContent);
        logger.info(responseContent.getClass().getSimpleName() + ":" + responseContent.toString());
        return new ResponseWrapper<>(responseContent);
    }

    @POST
    @Path("/ReceivedDriverAcceptAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<ReceivedDriverAcceptAPI.Response> ReceivedDriverAcceptAPI(@Valid RequestWrapper<ReceivedDriverAcceptAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapterTemp();

        ReceivedDriverAcceptAPI.Response responseContent;
        ReceivedDriverAcceptAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.receivedDriverAccept(requestContent);
        logger.info(responseContent.getClass().getSimpleName() + ":" + responseContent.toString());

        return new ResponseWrapper<>();
    }

    @POST
    @Path("/GetDriverUpdatedAPI")//TODO: LONG POLLING
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetDriverUpdatedAPI.Response> GetDriverUpdatedAPI(@Valid RequestWrapper<GetDriverUpdatedAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapterTemp();

        GetDriverUpdatedAPI.Response responseContent;
        GetDriverUpdatedAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getDriverUpdated(requestContent);
        logger.info(responseContent.getClass().getSimpleName() + ":" + responseContent.toString());

        return new ResponseWrapper<>(responseContent);
    }

}


