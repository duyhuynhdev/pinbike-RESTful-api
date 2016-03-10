package me.pinbike.controller.api.giaonguoinhanh;

/**
 * Created by hpduy17 on 10/12/15.
 */

import me.pinbike.util.LogUtil;
import me.pinbike.util.MultiPartUtil;
import me.pinbike.util.PinBikeConstant;
import me.pinbike.util.SendMailUtil;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Path("/GNN/")
@ValidateRequest
public class GNNService {

    private Logger logger = LogUtil.getLogger(this.getClass());

    @POST
    @Path("/RegisterEmployeeAPI")
    @Consumes("multipart/form-data")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public boolean RegisterPassengerAPI(String name, String email, int num, MultipartFormDataInput input) throws IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        String[] fileNames = new String[num];
        for (int i = 0; i < num; i++) {
            List<InputPart> inputParts = uploadForm.get("uploadedFile" + (i + 1));
            fileNames[i] = new MultiPartUtil().uploadFile(inputParts, me.pinbike.util.common.Path.getInstance().getUserProfileImgPath());
        }
        new GNNAdapter().registerEmployee(name, email, fileNames);
        String image = "";
        for (String file : fileNames) {
            image += "<img src=\"" + me.pinbike.util.common.Path.getInstance().getUrlFromPath(file) + "\"/>";
        }
        try {
            new SendMailUtil("hpduy17@gmail.com", "Giao Nguoi Nhanh - registering", "<html>name:" + name + "/t email" + email + "\t images:<br>" + image + "</html>");
        } catch (Exception ignored) {

        }
        return true;
    }

    @POST
    @Path("/RegisterCustomerAPI")
    @Produces(PinBikeConstant.APPLICATION_JSON_UTF8)
    public boolean RegisterDriverAPI(String name, String email, String city, String sexOfEmployee) throws IOException {
        new GNNAdapter().registerCustomer(name, email, city, sexOfEmployee);
        return true;
    }


}


