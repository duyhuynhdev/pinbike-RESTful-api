package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.GetDefaultSettingAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IDefaultAdapter {

    GetDefaultSettingAPI.Response getDefaultSetting(GetDefaultSettingAPI.Request request);

}
