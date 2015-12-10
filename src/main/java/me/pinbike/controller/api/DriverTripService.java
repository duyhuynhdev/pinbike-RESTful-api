package me.pinbike.controller.api;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.controller.adapter.DriverTripAdapterTemp;
import me.pinbike.controller.adapter.adapter_interface.IDriverTripAdapter;
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
public class DriverTripService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/GetRequestFromPassengerAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetRequestFromPassengerAPI.Response> GetRequestFromPassengerAPI(@Valid RequestWrapper<GetRequestFromPassengerAPI.Request> request) throws IOException {

        IDriverTripAdapter adapter = new DriverTripAdapterTemp();

        GetRequestFromPassengerAPI.Response responseContent;
        GetRequestFromPassengerAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getRequestFromPassenger(requestContent);
        ResponseWrapper<GetRequestFromPassengerAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/ArrivedPickUpLocationAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<ArrivedPickUpLocationAPI.Response> ArrivedPickUpLocationAPI(@Valid RequestWrapper<ArrivedPickUpLocationAPI.Request> request) throws IOException {
        IDriverTripAdapter adapter = new DriverTripAdapterTemp();

        ArrivedPickUpLocationAPI.Response responseContent;
        ArrivedPickUpLocationAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.arrivedPickUpLocation(requestContent);
        ResponseWrapper<ArrivedPickUpLocationAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/AcceptPassengerRequestAPI") //TODO LONG POLLING
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<AcceptPassengerRequestAPI.Response> AcceptPassengerRequestAPI(@Valid RequestWrapper<AcceptPassengerRequestAPI.Request> request) throws IOException {

        IDriverTripAdapter adapter = new DriverTripAdapterTemp();

        AcceptPassengerRequestAPI.Response responseContent;
        AcceptPassengerRequestAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.acceptPassengerRequest(requestContent);
        ResponseWrapper<AcceptPassengerRequestAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/DestroyTripAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<DestroyTripAPI.Response> DestroyTripAPI(@Valid RequestWrapper<DestroyTripAPI.Request> request) throws IOException {

        IDriverTripAdapter adapter = new DriverTripAdapterTemp();

        DestroyTripAPI.Response responseContent;
        DestroyTripAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.destroyTrip(requestContent);
        ResponseWrapper<DestroyTripAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/GetPassengerUpdatedAPI") //TODO: LONG POLLING
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetPassengerUpdatedAPI.Response> GetPassengerUpdatedAPI(@Valid RequestWrapper<GetPassengerUpdatedAPI.Request> request) throws IOException {
        IDriverTripAdapter adapter = new DriverTripAdapterTemp();

        GetPassengerUpdatedAPI.Response responseContent;
        GetPassengerUpdatedAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getPassengerUpdated(requestContent);
        ResponseWrapper<GetPassengerUpdatedAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/StartTripAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<StartTripAPI.Response> StartTripAPI(@Valid RequestWrapper<StartTripAPI.Request> request) throws IOException {
        IDriverTripAdapter adapter = new DriverTripAdapterTemp();

        StartTripAPI.Response responseContent;
        StartTripAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.startTrip(requestContent);
        ResponseWrapper<StartTripAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/EndTripAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<EndTripAPI.Response> EndTripAPI(@Valid RequestWrapper<EndTripAPI.Request> request) throws IOException {
        IDriverTripAdapter adapter = new DriverTripAdapterTemp();

        EndTripAPI.Response responseContent;
        EndTripAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.endTrip(requestContent);
        ResponseWrapper<EndTripAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/RatingTripAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<RatingTripAPI.Response> RatingTripAPI(@Valid RequestWrapper<RatingTripAPI.Request> request) throws IOException {
        IDriverTripAdapter adapter = new DriverTripAdapterTemp();

        RatingTripAPI.Response responseContent;
        RatingTripAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.ratingTrip(requestContent);
        ResponseWrapper<RatingTripAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }
}


