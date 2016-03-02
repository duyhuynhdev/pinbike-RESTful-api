package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IVerificationAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.*;
import me.pinbike.sharedjava.model.base.GroupContact;
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
    public GetVerifiedContactOfflineAPI.Response getVerifiedContactOfflineAPI(GetVerifiedContactOfflineAPI.Request request) {
        List<GroupContact> groupContacts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            GroupContact gc = new GroupContact();
            gc.name = factory.getCity();
            gc.contacts = new ArrayList<>();
            for (int j = 0; j <= 3; j++) {
                OfflineContact contact = new OfflineContact();
                contact.name = factory.getBusinessName();
                contact.phone = "01692715716";
                gc.contacts.add(contact);
            }
            groupContacts.add(gc);
        }
        GetVerifiedContactOfflineAPI.Response response = new GetVerifiedContactOfflineAPI.Response();
        response.groupContacts = groupContacts;
        return response;
    }

    @Override
    public GetUserVerifiedStatusAPI.Response getUserVerifiedStatusAPI(GetUserVerifiedStatusAPI.Request request) {
        return null;
    }
}
