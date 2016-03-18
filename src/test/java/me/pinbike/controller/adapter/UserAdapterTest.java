package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.LoginBySocialAPI;
import me.pinbike.sharedjava.model.RegisterAPI;
import me.pinbike.sharedjava.model.constanst.AC;
import org.junit.Test;

/**
 * Created by hpduy17 on 3/15/16.
 */
public class UserAdapterTest {

    @Test
    public void testLoginBySocial() throws Exception {
        LoginBySocialAPI.Request request = new LoginBySocialAPI.Request();
        request.socialId = "10152850663931856";
        request.socialType = 2;
        request.deviceId ="";
        request.os = AC.BroadcastOS.IOS;
        request.regId ="";
        new UserAdapter().loginBySocial(request);
    }

    @Test
    public void testRegister() throws Exception {
        RegisterAPI.Request request = new ModelDataFactory().getRegisterAPIRequest();
        new UserAdapter().register(request);

    }
}