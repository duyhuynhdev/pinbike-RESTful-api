package me.pinbike.controller;

/**
 * Created by hpduy17 on 10/12/15.
 */

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/")
public class MainService {
    @POST
    @Path("/")
    @Consumes(MediaType.TEXT_HTML)
    public Response main() throws IOException {
        return Response.status(200).entity("PINBIKE EXPRESS").build();
    }



}


