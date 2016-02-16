package me.pinbike.util.sms;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by hpduy17 on 1/12/16.
 */
public class SMSManager {
    public void sendActivationCode(String phone, String code) {
        String sms_Content = "PinBike activation code : " + code;
        ESMS esms = new ESMS(new String[]{phone}, sms_Content, ESMS.sendSMS, ESMS.type_fixed_phone_19001534_express, code);
        try {
            esms.send();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
