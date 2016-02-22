package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.AddVerificationAPI;
import me.pinbike.sharedjava.model.ConfirmVerifiedUserAPI;
import me.pinbike.sharedjava.model.GetVerifiedContactOfflineAPI;
import me.pinbike.sharedjava.model.RequestVerifyAPI;
import me.pinbike.sharedjava.model.base.OfflineContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 2/22/16.
 */
public class VerificationAdapterTemp extends ModelDataFactory implements IVerificationAdapter {
    @Override
    public AddVerificationAPI.Response addVerification(AddVerificationAPI.Request request) {
        return null;
    }

    @Override
    public RequestVerifyAPI.Response requestVerifyAPI(RequestVerifyAPI.Request request) {
        return null;
    }

    @Override
    public ConfirmVerifiedUserAPI.Response confirmVerifiedUserAPI(ConfirmVerifiedUserAPI.Request request) {
        return null;
    }

    @Override
    public GetVerifiedContactOfflineAPI.Response GetVerifiedContactOfflineAPI(GetVerifiedContactOfflineAPI.Request request) {
        List<OfflineContact> contactList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            OfflineContact contact = new OfflineContact();
            contact.name = factory.getBusinessName();
            contact.phone = factory.getNumberUpTo(10) + "";
            contactList.add(contact);
        }
        GetVerifiedContactOfflineAPI.Response response = new GetVerifiedContactOfflineAPI.Response();
        response.contacts = contactList;
        return response;
    }
}
