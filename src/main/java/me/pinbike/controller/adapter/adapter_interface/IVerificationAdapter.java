package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IVerificationAdapter {
    public AddVerificationAPI.Response addVerification(AddVerificationAPI.Request request);

    public RequestVerifyAPI.Response requestVerifyAPI(RequestVerifyAPI.Request request) throws IOException;

    public ConfirmVerifiedUserAPI.Response confirmVerifiedUserAPI(ConfirmVerifiedUserAPI.Request request);

    public GetVerifiedContactOfflineAPI.Response getVerifiedContactOfflineAPI(GetVerifiedContactOfflineAPI.Request request);

    public GetUserVerifiedStatusAPI.Response getUserVerifiedStatusAPI(GetUserVerifiedStatusAPI.Request request);

}
