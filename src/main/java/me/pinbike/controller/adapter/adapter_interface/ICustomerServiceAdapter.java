package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.FeedbackAPI;

import java.io.IOException;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface ICustomerServiceAdapter {

    FeedbackAPI.Response feedback(FeedbackAPI.Request request) throws IOException;

}
