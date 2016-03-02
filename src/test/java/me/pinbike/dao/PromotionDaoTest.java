package me.pinbike.dao;

import com.pinride.pinbike.thrift.TPromoCode;
import org.junit.Test;

import java.util.List;

/**
 * Created by hpduy17 on 2/24/16.
 */
public class PromotionDaoTest {

    @Test
        public void testGetCurrentActivePromoCode() throws Exception {
            List<TPromoCode> promocode = new PromotionDao().getCurrentActivePromoCode();
            for(TPromoCode p :promocode)
                System.out.println(p.toString());
        }
}