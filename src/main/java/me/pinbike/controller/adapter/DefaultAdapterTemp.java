package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IDefaultAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.GetDefaultSettingAPI;
import me.pinbike.sharedjava.model.GetTermAndPrivacyAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class DefaultAdapterTemp extends ModelDataFactory implements IDefaultAdapter {
    @Override
    public GetDefaultSettingAPI.Response getDefaultSetting(GetDefaultSettingAPI.Request request) {
        GetDefaultSettingAPI.Response response = new GetDefaultSettingAPI.Response();
        response.aroundDistance = 0.5;
        response.coverImage = "http://imgt.taimienphi.vn/cf/Images/nmt/2013/12/bo-hinh-nen-nam-moi-2014-doc-dao-an-tuong-3.jpg";
        response.fanPage = "https://www.facebook.com/PinBikeMe";
        response.faqPassengerLink = "https://www.facebook.com/PinBikeMe";
        response.faqDriverLink = "https://www.facebook.com/PinBikeMe";
        response.requestTimeout = 15;
        response.website = "http://www.pinbike.me";
        return response;
    }

    @Override
    public GetTermAndPrivacyAPI.Response getTermAndPrivacyAPI(GetTermAndPrivacyAPI.Request request) {
        return null;
    }
}
