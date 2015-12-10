package me.pinbike.dao;

import com.pinride.pinbike.thrift.TRating;
import com.pinride.pinbike.thriftclient.TRatingClientImpl;
import me.pinbike.util.LogUtil;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class RatingDao extends DaoTemplate<TRating> {

    public RatingDao() {
        client = TRatingClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }


}
