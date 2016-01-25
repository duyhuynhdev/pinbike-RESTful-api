package me.pinbike.dao;

import com.pinride.pinbike.config.Const;
import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.thrift.TBroadcast;
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

    public TUser getUserByEmailPassword(String email, String password) {
        try {
            logger.info(String.format("{email:%s, password:%s", email, password));
            AdapterResponseValue.ResponseValue<TUser> response = client.getUserBySocial(email, Const.PinBike.SocialType.EMAIL);
            validateResponse(response.getErrorCode(), getGenericName() + ".getUserBySocial()", String.format("{email:%s, password:%s", email, password));
            if (response.getValue().password.equals(password))
                return response.getValue();
            throw new PinBikeException(AC.MessageCode.WRONG_PASSWORD, "Your password is invalid");
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public TUser getUserBySocial(String socialId, int socialType) {
        try {
            logger.info(String.format("{socialId:%s, socialType:%d", socialId, socialType));
            AdapterResponseValue.ResponseValue<TUser> response = client.getUserBySocial(socialId, socialType);
            validateResponse(response.getErrorCode(), getGenericName() + ".getUserBySocial()", String.format("{socialId:%s, socialType:%d", socialId, socialType));
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
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

    public boolean checkCurrentDeviceId(long userId, String deviceId) {
        try {
            logger.info(String.format("{userId:%d", userId));
            AdapterResponseValue.ResponseValue<TUser> response = client.get(userId);
            validateResponse(response.getErrorCode(), getGenericName() + ".checkCurrentDeviceId()", String.format("{userId:%d", userId));
            try {
                BroadcastDao dao = new BroadcastDao();
                TBroadcast broadcast = dao.get(response.getValue().currentBroadcastId);
                return broadcast.deviceId.equals(deviceId);
            } catch (PinBikeException ex) {
                if (ex.getMessageCode() != AC.MessageCode.NOT_EXIST)
                    throw ex;
                return true;
            }
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public void updateSocialForUser(long userId, String socialId, int socialType) {
        try {
            logger.info(String.format("{userId:%d , socialId:%s, socialType:%d", userId, socialId, socialType));
            int errorCode = client.updateSocialForUser(userId, socialId, socialType);
            validateResponse(errorCode, getGenericName() + ".updateSocialForUser()", String.format("{userId:%d , socialId:%s, socialType:%d", userId, socialId, socialType));
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }
}
