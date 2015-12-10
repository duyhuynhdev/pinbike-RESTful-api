package me.pinbike.dao;

import com.pinride.pinbike.thrift.TBroadcast;
import com.pinride.pinbike.thriftclient.TBroadcastClientImpl;
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

}
