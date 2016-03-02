package me.pinbike.controller.adapter.promotion;

import com.pinride.pinbike.thrift.TPromoCode;
import me.pinbike.dao.PromotionDao;
import me.pinbike.util.DateTimeUtils;
import me.pinbike.util.SendMailUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hpduy17 on 2/24/16.
 */
public class PromotionAdapterTest {
    protected DataFactory factory = new DataFactory();

    @Test
    public void createFakeDB() throws UnsupportedEncodingException {
        long userId = 3765;
        PromotionDao dao = new PromotionDao();
        List<String> promoList = new ArrayList<>();
        int numPromocode = 100;
        for (int i = 0; i < numPromocode; i++) {
            TPromoCode code = new TPromoCode();
            code.eventName = factory.getBusinessName();
            code.code =  RandomStringUtils.randomAlphabetic(5).toUpperCase();
            code.value = factory.getNumberUpTo(10) * 10000;
            code.description = "Nhân dịp " + code.eventName + " nhập mã " + code.code + " để giảm ngay " + code.value + " cho chuyến đi của bạn";
            code.startDate = new Date().getTime() - i % 4 * DateTimeUtils.DAYS * 1000;
            code.endDate = new Date().getTime() + RandomUtils.nextInt(100) * DateTimeUtils.DAYS * 1000;
            code.promoCodeType = 1;
            if (code.promoCodeType == 2) {
                code.sendGroupUserId = new ArrayList<>();
                if (RandomUtils.nextInt() % 2 == 0)
                    code.sendGroupUserId.add(userId);

            }

            if (code.promoCodeType == 4)
                code.limit = factory.getNumberUpTo(20);
            dao.insert(code);
            promoList.add(code.code +"-"+ code.value);
        }
        String content = "";
        for (String s : promoList) {
            content += s + "<br>";
        }
        content = "<html>" + content + "</html>";
        new SendMailUtil("nguyenvuhuy0407=@gmail.com", "PromoCode List", content);
        while (true) {

        }
    }

    @Test
    public void testUsePromoCode() throws Exception {

    }
}