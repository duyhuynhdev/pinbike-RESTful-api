package me.pinbike.controller.adapter;

import com.pinride.pinbike.thrift.TBike;
import com.pinride.pinbike.thrift.TOrganization;
import com.pinride.pinbike.thrift.TUser;
import me.pinbike.controller.adapter.adapter_interface.Converter;
import me.pinbike.controller.adapter.adapter_interface.IBikeAdapter;
import me.pinbike.dao.BikeDao;
import me.pinbike.dao.OrganizationDao;
import me.pinbike.dao.UserDao;
import me.pinbike.sharedjava.model.AddBikeAPI;
import me.pinbike.sharedjava.model.UpdateMyCurrentBikeAPI;
import me.pinbike.sharedjava.model.base.UserDetail;

import java.util.List;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class BikeAdapter implements IBikeAdapter {

    @Override
    public AddBikeAPI.Response addBike(AddBikeAPI.Request request) {
        UserDao userDao = new UserDao();
        BikeDao bikeDao = new BikeDao();
        OrganizationDao organizationDao = new OrganizationDao();
        //get user detail to check user exist
        TUser user = userDao.get(request.userId);
        //insert bike
        TBike bike = new TBike();
        bike.model = request.model;
        bike.description = request.description;
        bike.userId = user.userId;
        bike.licensePlate = request.licensePlate;
        bikeDao.insert(bike);
        //get user detail after update bike
        user = userDao.get(request.userId);
        List<TBike> bikes = bikeDao.getList(user.bikeIds);
        List<TOrganization> organizations = organizationDao.getList(user.organizationIds);
        UserDetail userDetail = new Converter().convertUser(user, bikes, organizations);
        return new AddBikeAPI.Response(userDetail);
    }

    @Override
    public UpdateMyCurrentBikeAPI.Response updateMyCurrentBike(UpdateMyCurrentBikeAPI.Request request) {
        UserDao userDao = new UserDao();
        TUser user = userDao.get(request.userId);
        user.currentBikeId = request.bikeId;
        userDao.update(user);
        return null;
    }
}
