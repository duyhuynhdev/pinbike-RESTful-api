package me.pinbike.controller.adapter.adapter_interface;

import com.pinride.pinbike.thrift.*;
import me.pinbike.sharedjava.model.base.*;

import java.util.List;

/**
 * Created by hpduy17 on 12/9/15.
 */
public class Converter {
    public UserDetail convertUser(TUser user, List<TBike> bikes, List<TOrganization> organizations) {
        if (user == null)
            return null;
        UserDetail userDetail = new UserDetail();
        userDetail.avatar = user.avatar;
        userDetail.birthday = user.brithday;
        userDetail.currentBikeId = user.currentBikeId;
        userDetail.email = user.email;
        userDetail.isAvailable = user.availableDriver;
        userDetail.joinedDate = user.dateCreated;
        userDetail.name = user.name;
        userDetail.phone = user.phone;
        userDetail.sex = user.sex;
        userDetail.status = user.status;
        userDetail.userId = user.userId;
        userDetail.currentLocation = new LatLng();
        userDetail.currentLocation.lat = user.currentLocation.lat;
        userDetail.currentLocation.lng = user.currentLocation.lng;
        userDetail.intro = user.intro;
        for (TBike bike : bikes) {
            userDetail.bikes.add(convertBike(bike));
        }
        for (TOrganization organization : organizations) {
            userDetail.organizations.add(convertOrganization(organization));
        }
        Rating rating = new Rating();
        rating.totalScore = user.totalScore;
        rating.ratingCount = (int) user.numberOfRating;
        userDetail.rating = rating;
        return userDetail;
    }

    public Bike convertBike(TBike bike) {
        if (bike == null)
            return null;
        Bike bikeDetail = new Bike();
        bikeDetail.bikeId = bike.bikeId;
        bikeDetail.description = bike.description;
        bikeDetail.licensePlate = bike.licensePlate;
        bikeDetail.model = bike.model;
        return bikeDetail;
    }

    public Organization convertOrganization(TOrganization organization) {
        if (organization == null)
            return null;
        Organization organizationDetail = new Organization();
        organizationDetail.organizationId = organization.organizationId;
        organizationDetail.organizationJoinedDate = organization.dateCreated;
        organizationDetail.organizationName = organization.name;
        return organizationDetail;
    }

    public UpdatedLocation convertUpdatedLocation(TLatLng ll) {
        if (ll == null)
            return null;
        UpdatedLocation updatedLocation = new UpdatedLocation();
        updatedLocation.location.lat = ll.lat;
        updatedLocation.location.lng = ll.lng;
        updatedLocation.updatedTime = ll.time;
        return updatedLocation;
    }

    public TripDetail convertTripDetail(TTrip trip) {
        if (trip == null)
            return null;
        TripDetail tripDetail = new TripDetail();
        tripDetail.distance = trip.distance;
        tripDetail.endLocation = new Location();
        tripDetail.startLocation = new Location();

        tripDetail.endLocation.address = trip.getEndLocation(); //TODO will fix in future
        if (trip.getEndLatLng() != null) {
            tripDetail.endLocation.lat = trip.getEndLatLng().lat;
            tripDetail.endLocation.lng = trip.getEndLatLng().lng;
        }

        tripDetail.startLocation.address = trip.getStartLocation(); //TODO will fix in future
        if (trip.getStartLatLng() != null) {
            tripDetail.startLocation.lat = trip.getStartLatLng().lat;
            tripDetail.startLocation.lng = trip.getStartLatLng().lng;
        }
        tripDetail.passengerId = trip.passengerId;
        tripDetail.price = trip.price;
        return tripDetail;
    }


}
