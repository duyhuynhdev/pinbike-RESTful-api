package me.pinbike.controller.api;

import me.pinbike.controller.adapter.CustomerServiceAdapter;
import me.pinbike.controller.adapter.adapter_interface.ICustomerServiceAdapter;
import me.pinbike.sharedjava.model.FeedbackAPI;
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

/**
 * Created by hpduy17 on 2/17/16.
 */
@Path("/")
@ValidateRequest
public class CustomerService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/FeedbackAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    /**
     * email
     * phone
     * content
     * error images
     */
    public ResponseWrapper<FeedbackAPI.Response> FeedbackAPI(@Valid RequestWrapper<FeedbackAPI.Request> request) throws IOException {

        ICustomerServiceAdapter adapter = new CustomerServiceAdapter();

        FeedbackAPI.Response responseContent;
        FeedbackAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.feedback(requestContent);
        ResponseWrapper<FeedbackAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());

        return response;
    }
}
