package me.pinbike.controller.adapter.adapter_interface.promotion_inferace;

import me.pinbike.sharedjava.model.UsePromoCodeAPI;

/**
 * Created by hpduy17 on 2/23/16.
 */
public interface IPromotionAdapter {

    UsePromoCodeAPI.Response usePromoCode(UsePromoCodeAPI.Request request);
}
