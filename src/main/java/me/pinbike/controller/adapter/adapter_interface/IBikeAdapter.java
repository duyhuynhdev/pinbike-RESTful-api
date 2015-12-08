package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.AddBikeAPI;
import me.pinbike.sharedjava.model.UpdateMyCurrentBikeAPI;

/**
 * Created by hpduy17 on 12/4/15.
 */
public interface IBikeAdapter {

    AddBikeAPI.Response addBike(AddBikeAPI.Request request);

    UpdateMyCurrentBikeAPI.Response updateMyCurrentBike(UpdateMyCurrentBikeAPI.Request request);

}
