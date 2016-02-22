package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.AddVerificationAPI;
import me.pinbike.sharedjava.model.ConfirmVerifiedUserAPI;
import me.pinbike.sharedjava.model.GetVerifiedContactOfflineAPI;
import me.pinbike.sharedjava.model.RequestVerifyAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IVerificationAdapter {
    public AddVerificationAPI.Response addVerification(AddVerificationAPI.Request request);

    public RequestVerifyAPI.Response requestVerifyAPI(RequestVerifyAPI.Request request);

    public ConfirmVerifiedUserAPI.Response confirmVerifiedUserAPI(ConfirmVerifiedUserAPI.Request request);

    public GetVerifiedContactOfflineAPI.Response GetVerifiedContactOfflineAPI(GetVerifiedContactOfflineAPI.Request request);

}
