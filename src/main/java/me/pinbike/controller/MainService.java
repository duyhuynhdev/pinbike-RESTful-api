package me.pinbike.controller;

/**
 * Created by hpduy17 on 10/12/15.
 */

import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/")
@ValidateRequest
public class MainService {
    @GET
    @Path("/")
    public Response main() throws IOException {
        return Response.status(200).entity("PINBIKE EXPRESS").build();
    }

    @POST
    @Path("/Test")
    public Response testAPI(String unicodeTest) throws IOException {
        String result = unicodeTest + " đã qua server rồi nha";
        System.out.println(result);
        return Response.status(200).entity(result).build();
    }


}


