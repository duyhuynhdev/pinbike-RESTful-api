package me.pinbike.controller.api;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.controller.adapter.BikeAdapterTemp;
import me.pinbike.controller.adapter.adapter_interface.IBikeAdapter;
import me.pinbike.sharedjava.model.AddBikeAPI;
import me.pinbike.sharedjava.model.UpdateMyCurrentBikeAPI;
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
public class BikeService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/AddBikeAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<AddBikeAPI.Response> AddBikeAPI(@Valid RequestWrapper<AddBikeAPI.Request> request) throws IOException {

        IBikeAdapter adapter = new BikeAdapterTemp();

        AddBikeAPI.Response responseContent;
        AddBikeAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.addBike(requestContent);
        logger.info(responseContent.getClass().getSimpleName() + ":" + responseContent.toString());

        return new ResponseWrapper<>(responseContent);
    }

    @POST
    @Path("/UpdateMyCurrentBikeAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<UpdateMyCurrentBikeAPI.Response> UpdateMyCurrentBikeAPI(@Valid RequestWrapper<UpdateMyCurrentBikeAPI.Request> request) throws IOException {
        IBikeAdapter adapter = new BikeAdapterTemp();

        UpdateMyCurrentBikeAPI.Response responseContent;
        UpdateMyCurrentBikeAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.updateMyCurrentBike(requestContent);
        logger.info(responseContent.getClass().getSimpleName() + ":" + responseContent.toString());
        return new ResponseWrapper<>();
    }


}


