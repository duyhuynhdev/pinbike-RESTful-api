package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IDefaultAdapter;
import me.pinbike.sharedjava.model.GetDefaultSettingAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class DefaultAdapter implements IDefaultAdapter{
    @Override
    public GetDefaultSettingAPI.Response getDefaultSetting(GetDefaultSettingAPI.Request request) {
        //TODO waiting Constant from back end @ann
        return null;
    }

}
