package me.pinbike.dao;

import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thriftclient.TBikeClientImpl;
import me.pinbike.util.LogUtil;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class BikeDao extends DaoTemplate<TBike> {

    public BikeDao() {
        client = TBikeClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }


}
