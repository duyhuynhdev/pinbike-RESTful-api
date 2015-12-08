package me.pinbike.controller.adapter.adapter_interface;

import me.pinbike.sharedjava.model.base.*;
import me.pinbike.util.DateTimeUtils;
import me.pinbike.util.sample_data.SampleData;
import org.codehaus.jackson.map.ObjectMapper;
import org.fluttercode.datafactory.impl.DataFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by hpduy17 on 12/4/15.
 */
public class ModelDataFactory {
    protected DataFactory factory = new DataFactory();

    public Bike getBike() {
        Bike bike = new Bike();
        bike.bikeId = factory.getNumberUpTo(Integer.MAX_VALUE);
        bike.description = getDescription(20);
        bike.licensePlate = factory.getNumberText(2) + "-" + factory.getRandomChars(1).toUpperCase() + factory.getNumberText(1) + " " + factory.getNumberText(4);
        bike.model = factory.getItem(SampleData.bike_models) + " " + factory.getItem(SampleData.colors);
        return bike;
    }

    public LatLng getLatLng(double lat, double lng, double radius) {
        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000f;
        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(lat);

        LatLng latLng = new LatLng();
        latLng.lng = new_x + lng;
        latLng.lat = y + lat;

        return latLng;
    }

    public Location getLocation(double lat, double lng, double radius) {
        Location location = new Location();
        LatLng latLng = getLatLng(lat, lng, radius);
        location.lat = latLng.lat;
        location.lng = latLng.lng;
        location.address = factory.getAddress() + factory.getCity();
        return location;
    }

    public Organization getOrganization() {
        Organization organization = new Organization();
        organization.organizationId = factory.getNumberUpTo(Integer.MAX_VALUE);
        organization.organizationJoinedDate = factory.getDateBetween(new Date(1417761483000L), new Date()).getTime() / 1000;
        organization.organizationName = factory.getBusinessName();
        return organization;
    }

    public Rating getRating() {
        Rating rating = new Rating();
        rating.ratingCount = factory.getNumberUpTo(100);
        rating.totalScore = factory.getNumberBetween(0, 5);
        return rating;
    }

    public TripDetail getTripDetail() {
        TripDetail tripDetail = new TripDetail();
        tripDetail.distance = factory.getNumberBetween(1, 30);
        tripDetail.endLocation = getLocation(10.123, 106.456, 100);
        tripDetail.passengerId = factory.getNumberUpTo(Integer.MAX_VALUE);
        tripDetail.price = factory.getNumberBetween((int) (1000 * tripDetail.distance), (int) (2000 * tripDetail.distance));
        tripDetail.startLocation = getLocation(10.123, 106.456, 100);
        return tripDetail;
    }

    public UpdatedLocation getUpdatedLocation(double lat, double lng, double radius) {
        UpdatedLocation updatedLocation = new UpdatedLocation();
        updatedLocation.location = getLatLng(lat, lng, radius * 100);
        updatedLocation.updatedTime = DateTimeUtils.now();
        return updatedLocation;
    }

    public UpdatedLocation getUpdatedLocation() {
        return getUpdatedLocation(10.123, 106.456, 100);
    }

    public UpdatedStatus getUpdatedStatus() {
        UpdatedStatus updatedStatus = new UpdatedStatus();
        updatedStatus.driverId = factory.getNumberUpTo(Integer.MAX_VALUE);
        updatedStatus.tripId = factory.getNumberUpTo(Integer.MAX_VALUE);
        return updatedStatus;
    }

    public UserDetail getUserDetail() {
        UserDetail userDetail = new UserDetail();
        userDetail.avatar = factory.getItem(SampleData.avatars);
        userDetail.birthday = factory.getDateBetween(new Date(473412072000L), new Date(1135927272000L)).getTime() / 1000;
        Bike[] bikes = new Bike[]{getBike(), getBike(), getBike()};
        userDetail.bikes = Arrays.asList(bikes);
        userDetail.currentBikeId = factory.getItem(bikes).bikeId;
        userDetail.email = factory.getEmailAddress();
        userDetail.isAvailable = true;
        userDetail.joinedDate = factory.getDateBetween(new Date(1417761483000L), new Date()).getTime() / 1000;
        userDetail.name = factory.getName();
        userDetail.organizations = Arrays.asList(getOrganization(), getOrganization(), getOrganization());
        userDetail.phone = factory.getItem(SampleData.phone_prefixes) + factory.getNumberText(7);
        userDetail.rating = getRating();
        userDetail.sex = factory.getItem(SampleData.sexes);
        userDetail.status = factory.getItem(SampleData.statuses);
        userDetail.userId = factory.getNumberUpTo(Integer.MAX_VALUE);
        return userDetail;
    }

    public UserReason getUserReason() {
        UserReason userReason = new UserReason();
        userReason.reasonType = factory.getItem(SampleData.reason_types);
        userReason.description = getDescription(20);
        return userReason;
    }

    /**
     * Custom Function by Duy Huynh
     *
     * @param length
     * @return
     */
    public String getDescription(int length) {
        String description = "";
        for (int i = 0; i < length; i++) {
            description += factory.getRandomWord() + " ";
        }
        return description;
    }

    public <T> T jsonToObject(String src, Class<T> type) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T result = mapper.readValue(src, type);
        return result;
    }
}
