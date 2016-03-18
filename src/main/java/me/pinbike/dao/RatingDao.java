package me.pinbike.dao;

import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.thrift.TRating;
import com.pinride.pinbike.thriftclient.TRatingClientImpl;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;

import java.util.List;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class RatingDao extends DaoTemplate<TRating> {

    public RatingDao() {
        client = TRatingClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }

    public List<TRating> getRatingsByUser(long userId) {
        try {
            logger.info(String.format("{userId: %d}", userId));
            AdapterResponseValue.ResponseListValue<TRating> response = client.getRatingsByUser(userId);
            validateResponse(response.getErrorCode(), getGenericName() + ".getRatingsByUser()", String.format("{userId: %d}", userId));
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public TRating getTripRating(long tripId, long userId) {
        try {
            logger.info(String.format("{tripId: %d, userId:%d}", tripId,userId));
            AdapterResponseValue.ResponseValue<TRating> response = client.getRatingByUserAndTrip(userId, tripId);
            validateResponse(response.getErrorCode(), getGenericName() + ".getTripRating()", String.format("{tripId: %d, userId:%d}", tripId, userId));
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }
}
