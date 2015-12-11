package me.pinbike.controller.outside;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.geocoder.search.vietbando.common.Requester;
import me.pinbike.util.LogUtil;
import me.pinbike.util.PinBikeConstant;
import org.apache.log4j.Logger;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

@Path("/")
@ValidateRequest
public class VBDService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/VietBanDoAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public String SearchAll(@Valid Requester.Input request) throws IOException {
        Requester requester = new Requester();
        String response;
        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        response = requester.request(request.apiName, request.data);
        logger.info(response.getClass().getSimpleName() + ":" + response);
        return response;
    }


}


