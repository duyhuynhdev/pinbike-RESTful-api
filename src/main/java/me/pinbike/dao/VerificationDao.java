package me.pinbike.dao;

import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.thrift.TVerification;
import com.pinride.pinbike.thriftclient.TVerificationClientImpl;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class VerificationDao extends DaoTemplate<TVerification> {

    public VerificationDao() {
        client = TVerificationClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }



    public TVerification getVerificationByUser(long userId) {
        try {
            logger.info(String.format("{userId:%d}", userId));
            AdapterResponseValue.ResponseValue<TVerification> response = client.getVerificationByUser(userId);
            validateResponse(response.getErrorCode(), getGenericName() + ".getVerificationByUser()", String.format("{userId:%d }", userId));
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }
}