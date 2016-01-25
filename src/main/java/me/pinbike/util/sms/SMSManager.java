package me.pinbike.util.sms;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by hpduy17 on 1/12/16.
 */
public class SMSManager {
    public void sendActivationCode(String phone, String code) {
        String sms_Content = "Welcome to PinBike! Your activation code is " + code;
        ESMS esms = new ESMS(new String[]{phone}, sms_Content, ESMS.sendSMS, ESMS.type_random_phone_express, code);
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
