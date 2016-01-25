package me.pinbike.controller.api;

/**
 * Created by hpduy17 on 10/12/15.
 */

import eu.bitwalker.useragentutils.UserAgent;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;
import me.pinbike.util.MultiPartUtil;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.ResponseWrapper;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("/")
@ValidateRequest
public class UploadService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/UploadAvatarAPI")
    @Consumes("multipart/form-data")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public ResponseWrapper UploadAvatarAPI(MultipartFormDataInput input, @Context HttpServletRequest req) throws IOException {
        UserAgent userAgent = null;
        try {
            userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
        } catch (Exception ignored) {
        }
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("uploadedFile");
        String fileNames = new MultiPartUtil().uploadFile(inputParts, me.pinbike.util.common.Path.getInstance().getUserProfileImgPath());
        String userInfo = userAgent == null ? "" :  userAgent.getBrowser().getName() +" | "+userAgent.getOperatingSystem();
        logger.info("Some user use " + userInfo +" to upload "+fileNames);
        return new ResponseWrapper(AC.MessageCode.SUCCESSFULLY, me.pinbike.util.common.Path.getInstance().getUrlFromPath(fileNames));
    }


}


