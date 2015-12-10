package me.pinbike.dao;

import com.pinride.pinbike.thrift.TTrip;
import com.pinride.pinbike.thriftclient.TTripClientImpl;
import me.pinbike.util.LogUtil;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class TripDao extends DaoTemplate<TTrip> {

    public TripDao() {
        client = TTripClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }


}
