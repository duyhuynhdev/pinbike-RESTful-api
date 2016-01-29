package me.pinbike.controller.api;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.controller.adapter.PassengerTripAdapter;
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
        IPassengerTripAdapter adapter = new PassengerTripAdapter();

        CreateTripAPI.Response responseContent;
        CreateTripAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.createTrip(requestContent);
        ResponseWrapper<CreateTripAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }


    @POST
    @Path("/CancelTripAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<CancelTripAPI.Response> CancelTripAPI(@Valid RequestWrapper<CancelTripAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapter();

        CancelTripAPI.Response responseContent;
        CancelTripAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.cancelTrip(requestContent);
        ResponseWrapper<CancelTripAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }


    @POST
    @Path("/RequestDriverAPI") //TODO: LONG POLLING
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<RequestDriverAPI.Response> RequestDriverAPI(@Valid RequestWrapper<RequestDriverAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapter();

        RequestDriverAPI.Response responseContent;
        RequestDriverAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.requestDriver(requestContent);
        ResponseWrapper<RequestDriverAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

    @POST
    @Path("/GetDriverUpdatedAPI")//TODO: LONG POLLING
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetDriverUpdatedAPI.Response> GetDriverUpdatedAPI(@Valid RequestWrapper<GetDriverUpdatedAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapter();

        GetDriverUpdatedAPI.Response responseContent;
        GetDriverUpdatedAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getDriverUpdated(requestContent);
        ResponseWrapper<GetDriverUpdatedAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }
//    @POST
//    @Path("/GetDriverUpdatedAPI")//TODO: LONG POLLING
//    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
//    public void GetDriverUpdatedAPI(@Valid RequestWrapper<GetDriverUpdatedAPI.Request> request, final @Suspend(30000) AsynchronousResponse rep) throws IOException {
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    IPassengerTripAdapter adapter = new PassengerTripAdapter();
//                    GetDriverUpdatedAPI.Response responseContent;
//                    GetDriverUpdatedAPI.Request requestContent = request.requestContent;
//                    logger.info(request.getClass().getSimpleName() + ":" + request.toString());
//                    responseContent = adapter.getDriverUpdated(requestContent);
//                    ResponseWrapper<GetDriverUpdatedAPI.Response> response = new ResponseWrapper<>(responseContent);
//                    logger.info(response.getClass().getSimpleName() + ":" + response.toString());
//                    Response temp = Response.ok(response.toString()).type(PinBikeConstant.APPLICATION_JSON_UTF8).build();
//                    rep.setResponse(temp);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        };
//        t.start();
//    }
    @POST
    @Path("/GetTripHistoryAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetTripHistoryAPI.Response> GetTripHistoryAPI(@Valid RequestWrapper<GetTripHistoryAPI.Request> request) throws IOException {
        IPassengerTripAdapter adapter = new PassengerTripAdapter();

        GetTripHistoryAPI.Response responseContent;
        GetTripHistoryAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getTripHistory(requestContent);
        ResponseWrapper<GetTripHistoryAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }

}


