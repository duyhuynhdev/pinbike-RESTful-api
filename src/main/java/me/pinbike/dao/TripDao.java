package me.pinbike.dao;

import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.thrift.TTrip;
import com.pinride.pinbike.thriftclient.TTripClientImpl;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;

import java.util.List;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class TripDao extends DaoTemplate<TTrip> {

    public TripDao() {
        client = TTripClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }

    public List<TTrip> getTripByDriver(long driverId) {
        try {
            logger.info(driverId);
            AdapterResponseValue.ResponseListValue<TTrip> response = client.getTripsByDriver(driverId);
            validateResponse(response.getErrorCode(), getGenericName() + ".getTripByDriver()", driverId+"");
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public List<TTrip> getTripByPassneger(long passengerId) {
        try {
            logger.info(passengerId);
            AdapterResponseValue.ResponseListValue<TTrip> response = client.getTripsByPassenger(passengerId);
            validateResponse(response.getErrorCode(), getGenericName() + ".getTripByPassneger()", passengerId+"");
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
