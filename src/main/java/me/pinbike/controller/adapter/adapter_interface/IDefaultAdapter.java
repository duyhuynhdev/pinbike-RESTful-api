package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.GetDefaultSettingAPI;
import me.pinbike.sharedjava.model.GetTermAndPrivacyAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IDefaultAdapter {

    GetDefaultSettingAPI.Response getDefaultSetting(GetDefaultSettingAPI.Request request);

    GetTermAndPrivacyAPI.Response getTermAndPrivacyAPI(GetTermAndPrivacyAPI.Request request);

}
