package me.pinbike.controller.adapter;

import me.pinbike.controller.adapter.adapter_interface.IBikeAdapter;
import me.pinbike.controller.adapter.adapter_interface.ModelDataFactory;
import me.pinbike.sharedjava.model.AddBikeAPI;
import me.pinbike.sharedjava.model.UpdateMyCurrentBikeAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class BikeAdapterTemp extends ModelDataFactory implements IBikeAdapter {
    @Override
    public AddBikeAPI.Response addBike(AddBikeAPI.Request request) {
        AddBikeAPI.Response response = new AddBikeAPI.Response(getUserDetail());
        return response;
    }

    @Override
    public UpdateMyCurrentBikeAPI.Response updateMyCurrentBike(UpdateMyCurrentBikeAPI.Request request) {
        return null;
    }
}
