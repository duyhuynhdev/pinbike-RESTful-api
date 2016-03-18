package me.pinbike.util.sms;

import me.pinbike.util.SendMailUtil;
import me.pinbike.util.common.HttpRequester;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hpduy17 on 1/12/16.
 */
public class VietGuysSMS {
    private static final String address_content= "https://sms.vietguys.biz/api/?u=PinRide&pwd=n3v28&from=1261&phone=%s&sms=%s&bid=PinBike";
    private static final String method = "GET";
    private static final String account = "PinRide";
    private static final String passcode = "n3v28";
    private static final String serviceId = "1261";

    public void send(String phone, String sms) throws IOException, ParserConfigurationException, TransformerException {
        phone = formalizePhoneNumber(phone);
        sms = sms.replaceAll(" ","%20");
        HttpRequester requester = new HttpRequester();
        String output = requester.call(String.format(address_content,phone,sms), "", method, new HashMap<>());
        boolean flag = true;
        String warning = "";
        switch (output.trim()){
            case "-1":
                warning = "Chưa nhập đầy đủ các tham số yêu cầu";
                break;
            case "-2":
                warning = "Không thể kết nối đến máy chủ VIETGUYS, máy chủ đang bận";
                break;
            case "-3":
                warning = "Thông tin xác nhận tài khoản chưa chính xác";
                break;
            case "-4":
                warning = "Tài khoản bị khóa";
                break;
            case "-5":
                warning = "Thông tin xác nhận tài khoản chưa chính xác";
                break;
            case "-6":
                warning = "Chức năng API chưa kích hoạt";
                break;
            case "-7":
                warning = "IP bị giới hạn truy cập, không được phép gửi từ IP này";
                break;
            case "-8":
                warning = "Giá trị Gửi-từ-đâu chưa được phép sử dụng, vui lòng liên hệ với VIETGUYS để khai báo trước khi sử dụng";
                break;
            case "-9":
                warning = "Tài khoản hết credits gửi tin";
                break;
            case "-10":
                warning = "Số điện thoại người nhận chưa chính xác";
                break;
            case "-11":
                warning = "Số điện thoại nằm trong danh sách Blacklist, là danh sách không muốn nhận tin nhắn";
                break;
            case "-12":
                warning = "Tài khoản không đủ credits để thực hiện gửi tin nhắn";
                break;

            default: flag = false;
        }
        if(flag) {
            new SendMailUtil("tungstuff@pinride.me","Message from viet guy sms after sent activation code", warning ).send();
            new SendMailUtil("duyhuynh@pinride.me","Message from viet guy sms after sent activation code", warning ).send();
        }
        System.out.println(output);
    }

    private String formalizePhoneNumber(String phone){
        if(phone.startsWith("0"))
            return "84"+phone.substring(1);
        return phone;
    }


}
