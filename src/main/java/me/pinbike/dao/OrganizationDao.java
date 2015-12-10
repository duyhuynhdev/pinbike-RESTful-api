package me.pinbike.dao;

import com.pinride.pinbike.thrift.TOrganization;
import com.pinride.pinbike.thriftclient.TOrganizationClientImpl;
import me.pinbike.util.LogUtil;

import static me.pinbike.util.PinBikeConstant.BackEndConfig;

/**
 * Created by hpduy17 on 12/8/15.
 */
public class OrganizationDao extends DaoTemplate<TOrganization> {

    public OrganizationDao() {
        client = TOrganizationClientImpl.getInstance(BackEndConfig.Host, BackEndConfig.Port);
        logger = LogUtil.getLogger(this.getClass());
    }


}
