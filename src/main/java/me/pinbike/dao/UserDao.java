package me.pinbike.dao;

import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.thrift.TUser;
import com.pinride.pinbike.thriftclient.TUserClientImpl;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.base.LatLng;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;

import java.util.List;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class UserDao extends DaoTemplate<TUser> {

    public UserDao() {
        client = TUserClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }

    public List<TUser> getDriverAround(LatLng ll) {
        try {
            logger.info(ll.toString());
            AdapterResponseValue.ResponseListValue<TUser> response = client.getDriverAround(ll.lat, ll.lng);
            validateResponse(response.getErrorCode(), getGenericName() + ".getDriverAround()", ll.toString());
            return response.getList();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public void updateAvailableDriver(long id, boolean isAvailable) {
        try {
            logger.info(String.format("{id:%d, isAvailable:%s", id, isAvailable));
            int errorCode = client.updateAvailableDriver(id, isAvailable);
            validateResponse(errorCode, getGenericName() + ".updateAvailableDriver()", String.format("{id:%d, isAvailable:%s", id, isAvailable));
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public void updateLocation(long id, LatLng ll) {
        try {
            logger.info(String.format("{id:%d, location:%s", id, ll.toString()));
            int errorCode = client.updateLocation(id, ll.lat, ll.lng);
            validateResponse(errorCode, getGenericName() + ".updateLocation()", String.format("{id:%d, location:%s", id, ll.toString()));
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }


}
