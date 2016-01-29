package me.pinbike.controller.adapter.adapter_interface;

import com.pinride.pinbike.thrift.*;
import me.pinbike.dao.RatingDao;
import me.pinbike.sharedjava.model.base.*;
import me.pinbike.sharedjava.model.constanst.AC;
import me.pinbike.util.common.Path;

import java.io.IOException;
import java.util.List;

/**
 * Created by hpduy17 on 12/9/15.
 */
public class Converter {
    public UserDetail convertUser(TUser user, List<TBike> bikes, List<TOrganization> organizations, boolean isDriver) {
        if (user == null)
            return null;
        UserDetail userDetail = new UserDetail();
        userDetail.avatar = user.avatar;
        userDetail.birthday = user.birthday;
        userDetail.currentBikeId = user.currentBikeId;
        userDetail.email = user.email;
        userDetail.isAvailable = user.availableDriver;
        userDetail.joinedDate = user.dateCreated;
        userDetail.givenName = user.name;
        userDetail.familyName = user.lastName;
        userDetail.middleName = user.middleName;
        userDetail.phone = user.phone;
        userDetail.sex = user.sex;
        userDetail.status = user.status;
        userDetail.userId = user.userId;
        userDetail.currentLocation = new LatLng();
        if (user.currentLocation != null) {
            userDetail.currentLocation.lat = user.currentLocation.lat;
            userDetail.currentLocation.lng = user.currentLocation.lng;
        }
        userDetail.intro = user.intro;
        if (bikes != null)
            for (TBike bike : bikes) {
                userDetail.bikes.add(convertBike(bike));
            }
        if (organizations != null)
            for (TOrganization organization : organizations) {
                userDetail.organizations.add(convertOrganization(organization));
            }
        Rating rating = new Rating();
        try {
//            rating.totalScore = new UserDao().getTotalScoreOfRating(user.getUserId());
        } catch (Exception ignored) {
        }
        rating.ratingCount = (int) user.numberOfRating;
        try {
            if (isDriver)
                userDetail.numberOfTravelledTrip = user.getTripDriverIdsSize();
            else
                userDetail.numberOfTravelledTrip = user.getTripPassengerIdsSize();
        } catch (Exception ignored) {
        }
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


    public TripReviewSortDetail convertTripReviewSortDetail(TTrip trip, TUser partner) throws IOException {
        if (trip == null && partner == null)
            return null;
        TripReviewSortDetail tripReviewSortDetail = new TripReviewSortDetail();
        tripReviewSortDetail.distance = trip.distance;
        tripReviewSortDetail.endLocation = new Location();
        tripReviewSortDetail.startLocation = new Location();

        tripReviewSortDetail.endLocation.address = trip.getEndLocation(); //TODO will fix in future
        if (trip.getEndLatLng() != null) {
            tripReviewSortDetail.endLocation.lat = trip.getEndLatLng().lat;
            tripReviewSortDetail.endLocation.lng = trip.getEndLatLng().lng;
        }

        tripReviewSortDetail.startLocation.address = trip.getStartLocation(); //TODO will fix in future
        if (trip.getStartLatLng() != null) {
            tripReviewSortDetail.startLocation.lat = trip.getStartLatLng().lat;
            tripReviewSortDetail.startLocation.lng = trip.getStartLatLng().lng;
        }
        tripReviewSortDetail.partnerId = partner.userId;
        tripReviewSortDetail.partnerAvatar = Path.getInstance().getUrlFromPath(partner.avatar);
        tripReviewSortDetail.timeInSecond = trip.dateCreated;
        TRating tRating = new RatingDao().getTripRating(trip.tripId, partner.userId);
        if (tRating != null)
            tripReviewSortDetail.ratingScore = tRating.score;
        else
            tripReviewSortDetail.ratingScore = 0;
        if (trip.status == AC.UpdatedStatus.ENDED) {
            tripReviewSortDetail.isDestroyedTrip = false;
            tripReviewSortDetail.price = trip.price;
        } else {
            tripReviewSortDetail.isDestroyedTrip = true;
            tripReviewSortDetail.price = 0;
        }
        return tripReviewSortDetail;
    }

}
