package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IDefaultAdapter;
import me.pinbike.dao.ConstDao;
import me.pinbike.sharedjava.model.GetDefaultSettingAPI;
import me.pinbike.sharedjava.model.GetTermAndPrivacyAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class DefaultAdapter implements IDefaultAdapter {
    @Override
    public GetDefaultSettingAPI.Response getDefaultSetting(GetDefaultSettingAPI.Request request) {
        return new ConstDao().getConst();
    }

    @Override
    public GetTermAndPrivacyAPI.Response getTermAndPrivacyAPI(GetTermAndPrivacyAPI.Request request) {
        ConstDao.Const c = new ConstDao().getConst();
        GetTermAndPrivacyAPI.Response response = new GetTermAndPrivacyAPI.Response();
        response.privacyLink = c.privacyLink;
        response.termsLink = c.termsLink;
        return response;
    }

}
