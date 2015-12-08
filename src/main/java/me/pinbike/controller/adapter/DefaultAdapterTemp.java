package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IDefaultAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.GetDefaultSettingAPI;
import me.pinbike.util.sample_data.SampleData;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class DefaultAdapterTemp extends ModelDataFactory implements IDefaultAdapter {
    @Override
    public GetDefaultSettingAPI.Response getDefaultSetting(GetDefaultSettingAPI.Request request) {
        GetDefaultSettingAPI.Response response = new GetDefaultSettingAPI.Response();
        response.aroundDistance = 0.5;
        response.coverImage = "http://www.sleekcover.com/covers/christmas-flakes-facebook-cover.jpg";
        response.fanPage = "https://www.facebook.com/PinBikeMe";
        response.qaLink = "https://www.facebook.com/PinBikeMe";
        response.requestTimeout = 15;
        response.website = "http://www.pinbike.me";
        try {
            response.priceModel = jsonToObject(SampleData.priceModelJSON,GetDefaultSettingAPI.Response.PriceModel.class);
        }catch (Exception ignored){}
        return null;
    }
}
