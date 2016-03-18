package me.pinbike.dao;

import com.google.gson.Gson;
import com.pinride.pinbike.config.adapter.AdapterResponseValue;
import com.pinride.pinbike.thrift.TConst;
import com.pinride.pinbike.thriftclient.TConstClientImpl;
import me.pinbike.provider.exception.PinBikeException;
import me.pinbike.sharedjava.model.GetDefaultSettingAPI;
import me.pinbike.sharedjava.model.base.GroupContact;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.LogUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class ConstDao {
    TConstClientImpl client;
    protected Logger logger;

    public ConstDao() {
        client = TConstClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }

    public Const getConst() {
        try {
            logger.info("{}");
            AdapterResponseValue.ResponseValue<TConst> response = client.get();
            new DaoTemplate().validateResponse(response.getErrorCode(), "ConstDao" + ".getConst()", "{}");
            String jsonString = response.getValue().json;
            Gson gson = new Gson();
            return gson.fromJson(jsonString, Const.class);
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }
    public void update(TConst tConst) {
        try {
            logger.info("{}");
            int errorCode = client.update(tConst);
            new DaoTemplate().validateResponse(errorCode, "ConstDao" + ".update()", "{}");
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public TConst insert(TConst tConst) {
        try {
            logger.info("{}");
            AdapterResponseValue.ResponseValue<TConst> response = client.insert(tConst);
            new DaoTemplate().validateResponse(response.getErrorCode(), "ConstDao" + ".insert()", "{}");
            return response.getValue();
        } catch (PinBikeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new PinBikeException(AC.MessageCode.SYSTEM_EXCEPTION, ex.getMessage());
        }
    }

    public static class Const extends GetDefaultSettingAPI.Response {
        public double beginningCredit;
        public String termsLink;
        public String privacyLink;
        public List<GroupContact> groupContacts = new ArrayList<>();
        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }


}
