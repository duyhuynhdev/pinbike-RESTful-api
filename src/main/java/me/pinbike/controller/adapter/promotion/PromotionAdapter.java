package me.pinbike.controller.adapter.promotion;

import com.pinride.pinbike.thrift.TPromoCode;
import com.pinride.pinbike.thrift.TTrip;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.promotion_inferace.IPromotionAdapter;
import me.pinbike.dao.PromotionDao;
import me.pinbike.dao.TripDao;
import me.pinbike.dao.UserDao;
import me.pinbike.sharedjava.model.UsePromoCodeAPI;

/**
 * Created by hpduy17 on 2/23/16.
 */
public class PromotionAdapter implements IPromotionAdapter {
    @Override
    public UsePromoCodeAPI.Response usePromoCode(UsePromoCodeAPI.Request request) {
        UserDao userDao = new UserDao();
        TripDao tripDao = new TripDao();
        PromotionDao promotionDao = new PromotionDao();
        TUser user = userDao.get(request.userId);
        TTrip trip = tripDao.get(request.tripId);
        //unlock old promo code
        try{
            promotionDao.unlockPromoCode(trip.promoCodeId,request.userId);
        }catch (Exception ignored){}
        // lock new promoCode
        TPromoCode promoCode = promotionDao.lockPromoCode(request.promoCode, user.userId);
        //put it to trip
        trip.promoCodeId = promoCode.promoCodeId;
        trip.promoCodeValue = promoCode.value;
        tripDao.update(trip);
        UsePromoCodeAPI.Response response = new UsePromoCodeAPI.Response();
        response.promoCodeValue = promoCode.value;
        return response;
    }
}
