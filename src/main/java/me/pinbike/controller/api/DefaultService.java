package me.pinbike.controller.api;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.controller.adapter.DefaultAdapterTemp;
import me.pinbike.controller.adapter.adapter_interface.IDefaultAdapter;
import me.pinbike.sharedjava.model.GetDefaultSettingAPI;
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
public class DefaultService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/GetDefaultSettingAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper<GetDefaultSettingAPI.Response> GetDefaultSettingAPI(@Valid RequestWrapper<GetDefaultSettingAPI.Request> request) throws IOException {

        IDefaultAdapter adapter = new DefaultAdapterTemp();

        GetDefaultSettingAPI.Response responseContent;
        GetDefaultSettingAPI.Request requestContent = request.requestContent;

        logger.info(request.getClass().getSimpleName() + ":" + request.toString());
        responseContent = adapter.getDefaultSetting(requestContent);
        logger.info(responseContent.getClass().getSimpleName() + ":" + responseContent.toString());

        return new ResponseWrapper<>(responseContent);
    }


}


