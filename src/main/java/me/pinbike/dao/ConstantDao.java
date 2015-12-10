package me.pinbike.dao;

import com.pinride.pinbike.thrift.TConst;
import com.pinride.pinbike.thriftclient.TConstClientImpl;
import me.pinbike.util.LogUtil;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class ConstantDao extends DaoTemplate<TConst> {

    public ConstantDao() {
        client = TConstClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }


}
