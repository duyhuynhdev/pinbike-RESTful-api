package me.pinbike.dao;

import com.pinride.pinbike.thrift.TBroadcast;
import com.pinride.pinbike.thrift.TUser;
import com.pinride.pinbike.thriftclient.TBroadcastClientImpl;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class BroadcastDao extends DaoTemplate<TBroadcast> {

    public BroadcastDao() {
        client = TBroadcastClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }

    public void updateCurrentBroadcast(TUser user, String deviceId, String regId, int os) {
        try {
            logger.info(String.format("user:%s \n deviceId:%s , regId:%s , os:%d", user.toString(), deviceId, regId, os));
            // get current broadcast
            TBroadcast broadcast = null;
            boolean isExits = false;
            try {
                broadcast = get(user.getCurrentBroadcastId());
                isExits = true;
                if (!broadcast.deviceId.equals(deviceId)) {
                    throw new PinBikeException(AC.MessageCode.ACCOUNT_HAS_BEEN_USING, "Your account has been used on other device! Please logout before login!");
                }
            } catch (PinBikeException ex) {
                if (ex.getMessageCode() != AC.MessageCode.NOT_EXIST)
                    throw ex;
            }
            if (broadcast == null)
                broadcast = new TBroadcast();
            broadcast.deviceId = deviceId;
            if (os == AC.BroadcastOS.ANDROID)
                broadcast.regId = regId;
            broadcast.os = os;
            broadcast.userId = user.userId;
            broadcast.active = false;
            if (isExits)
                update(broadcast);
            else {
                insert(broadcast);
            }
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public void removeCurrentBroadcast(TUser user, String deviceId) {
        try {
            logger.info(String.format("{user:%s \n deviceId:%s}", user.toString(), deviceId));
            // get current broadcast
            TBroadcast broadcast = get(user.getCurrentBroadcastId());
            if (!broadcast.deviceId.equals(deviceId)) {
                throw new PinBikeException(AC.MessageCode.ACCOUNT_HAS_BEEN_USING, "Your account has been used on other device!");
            }
            delete(broadcast.broadcastId);
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }
}
