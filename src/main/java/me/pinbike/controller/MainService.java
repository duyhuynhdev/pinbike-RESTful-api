package me.pinbike.controller;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.sharedjava.model.AddBikeAPI;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/")
@ValidateRequest
public class MainService {
    @POST
    @Path("/")
    @Consumes(MediaType.TEXT_HTML)
    public Response aAPI() throws IOException {

        return Response.status(200).entity("PINBIKE EXPRESS").build();
    }

    @POST
    @Path("/Test")
    @Consumes("application/json")
    public Response testAPI(@Valid AddBikeAPI.Request request) throws IOException {

        return Response.status(200).entity("PINBIKE EXPRESS").build();
    }


}


