package me.pinbike.dao;

import com.pinride.pinbike.config.adapter.AdapterResponseValue.ResponseValue;
import com.pinride.pinbike.thrift.TUser;
import com.pinride.pinbike.thriftclient.TUserClientImpl;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.MessageCode;
import me.pinbike.util.LogUtil;
import org.apache.log4j.Logger;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class UserDao extends DaoTemplate {
    private TUserClientImpl client = TUserClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
    private Logger logger = LogUtil.getLogger(this.getClass());

    public void insert(TUser user) {
        try {
            logger.info(user.toString());
            ResponseValue<TUser> response = client.insert(user);
            validateResponse(response, "Exception raise when insert user");
            logger.info(response.toString());
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

}
