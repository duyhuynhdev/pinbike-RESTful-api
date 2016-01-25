package me.pinbike.geocoder.search.vietbando.ip;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import me.pinbike.geocoder.search.vietbando.common.IpLocation;
import me.pinbike.util.common.Path;

import java.io.File;
import java.io.IOException;

/**
 * Created by hpduy17 on 1/23/16.
 */
public class GeoFromIpAddress {
    public String getAddressFromIp(String ipAddress) {
        try {
            IpLocation location = getLocation(ipAddress);
            return location.getCity() + ", " + location.getCountryName();
        } catch (Exception ex) {
        }
        return "Unknown Location";
    }

    public IpLocation getLocation(String ip) throws IOException {
        IpLocation location = new IpLocation();
        File file = new File(Path.getInstance().getDataPath() + "/GeoLiteCity.dat");
        LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
        Location locationServices = lookup.getLocation(ip);
        location.setCountryCode(locationServices.countryCode);
        location.setCountryName(locationServices.countryName);
        location.setRegion(locationServices.region);
        location.setRegionName(locationServices.region);
        location.setCity(locationServices.city);
        location.setPostalCode(locationServices.postalCode);
        location.setLatitude(String.valueOf(locationServices.latitude));
        location.setLongitude(String.valueOf(locationServices.longitude));

        return location;
    }


}
