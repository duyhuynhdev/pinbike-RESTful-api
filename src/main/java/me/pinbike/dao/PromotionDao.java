package me.pinbike.dao;

import com.pinride.pinbike.config.Const;
import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.thrift.TPromoCode;
import com.pinride.pinbike.thriftclient.TPromoCodeClientImpl;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;

import java.util.Date;
import java.util.List;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class PromotionDao extends DaoTemplate<TPromoCode> {

    public PromotionDao() {
        client = TPromoCodeClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }

    public TPromoCode usePromoCode(long promoCodeId, long userId) {
        try {
            logger.info(String.format("{promoCodeId:%s, userId:%d}", promoCodeId, userId));
            TPromoCode promoCode = get(promoCodeId);
            checkPromoCodeWithLock(promoCode, userId);
            int errorCode = client.addUserUsingPromoCode(promoCode.promoCodeId, userId);
            validateResponse(errorCode, getGenericName() + ".usePromoCode()", String.format("{promoCodeId:%s, userId:%d}", promoCodeId, userId));
            return promoCode;
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            if (ex.getMessageCode() == AC.MessageCode.NOT_EXIST) {
                ex.setMessageCode(AC.MessageCode.PROMOCODE_IS_INVALID);
            }
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public TPromoCode lockPromoCode(String code, long userId) {
        try {
            logger.info(String.format("{promoCode:%s, userId:%d}", code, userId));
            TPromoCode promoCode = getPromoByCode(code);
            checkPromoCodeWithLock(promoCode, userId);
            promoCode.isLock = true;
            int errorCode = client.update(promoCode);
            validateResponse(errorCode, getGenericName() + ".lockPromoCode()", String.format("{promoCode:%s, userId:%d}", code, userId));
            return promoCode;
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }

    }

    public TPromoCode unlockPromoCode(long promoCodeId, long userId) {
        try {
            logger.info(String.format("{promoCodeId:%d, userId:%d}", promoCodeId, userId));
            TPromoCode promoCode = get(promoCodeId);
            checkPromoCode(promoCode, userId);
            promoCode.isLock = false;
            int errorCode = client.update(promoCode);
            validateResponse(errorCode, getGenericName() + ".unlockPromoCode()", String.format("{promoCodeId:%d, userId:%d}", promoCodeId, userId));
            return promoCode;
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }

    }

    public void checkPromoCode(TPromoCode promoCode, long userId) {
        if (promoCode.promoCodeType == Const.PinBike.PromoCodeType.PROMOCODE_TYPE_GROUP
                && promoCode.sendGroupUserId != null
                && !promoCode.sendGroupUserId.contains(userId))
            throw new PinBikeException(AC.MessageCode.PROMOCODE_IS_INVALID);
        if (promoCode.promoCodeType == Const.PinBike.PromoCodeType.PROMOCODE_TYPE_LIMIT
                && promoCode.userIds != null
                && promoCode.userIds.size() >= promoCode.limit)
            throw new PinBikeException(AC.MessageCode.PROMOCODE_REACH_LIMIT);
        if (promoCode.userIds != null && promoCode.userIds.contains(userId))
            throw new PinBikeException(AC.MessageCode.PROMOCODE_HAS_BEEN_USED);
        long today = new Date().getTime();
        if (promoCode.startDate > today || promoCode.endDate < today)
            throw new PinBikeException(AC.MessageCode.PROMOCODE_IS_INVALID);
        if (promoCode.isUsed)
            throw new PinBikeException(AC.MessageCode.PROMOCODE_HAS_BEEN_USED);
    }

    public void checkPromoCodeWithLock(TPromoCode promoCode, long userId) {
        checkPromoCode(promoCode, userId);
        if (promoCode.promoCodeType == Const.PinBike.PromoCodeType.PROMOCODE_TYPE_SINGEL
                && promoCode.isLock)
            throw new PinBikeException(AC.MessageCode.PROMOCODE_HAS_BEEN_USED);
    }

    public TPromoCode getPromoByCode(String code) {
        try {
            logger.info(String.format("{promoCode:%s}", code));
            AdapterResponseValue.ResponseValue<TPromoCode> response = client.getPromoByCode(code);
            validateResponse(response.getErrorCode(), getGenericName() + ".getPromoByCode()", String.format("{promoCode:%s }", code));
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            if (ex.getMessageCode() == AC.MessageCode.NOT_EXIST) {
                ex.setMessageCode(AC.MessageCode.PROMOCODE_IS_INVALID);
            }
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<TPromoCode> getCurrentActivePromoCode() {
        try {
            logger.info("{}");
            AdapterResponseValue.ResponseListValue<TPromoCode> response = client.getCurrentActivePromoCode();
            validateResponse(response.getErrorCode(), getGenericName() + ".getCurrentActivePromoCode()", "{}");
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

}