package me.pinbike.controller.api;

import me.pinbike.controller.adapter.adapter_interface.promotion_inferace.IPromotionAdapter;
import me.pinbike.controller.adapter.promotion.PromotionAdapter;
import me.pinbike.sharedjava.model.UsePromoCodeAPI;
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
public class PromotionService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/UsePromoCodeAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<UsePromoCodeAPI.Response> UsePromoCodeAPI (@Valid RequestWrapper<UsePromoCodeAPI.Request> request) throws IOException {

        IPromotionAdapter adapter = new PromotionAdapter();

        UsePromoCodeAPI.Response responseContent;
        UsePromoCodeAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.usePromoCode(requestContent);
        ResponseWrapper<UsePromoCodeAPI.Response> response = new ResponseWrapper<>(responseContent);
        logger.info(response.getClass().getSimpleName() + ":" + response.toString());
        return response;
    }
}
