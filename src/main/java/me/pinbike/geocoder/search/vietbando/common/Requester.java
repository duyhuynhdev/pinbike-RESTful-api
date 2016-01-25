package me.pinbike.geocoder.search.vietbando.common;

import com.google.gson.Gson;
import me.pinbike.util.common.HttpRequester;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hpduy17 on 12/10/15.
 */
public class Requester extends HttpRequester {
    private static final String address_prefix = "http://developers.vietbando.com/V2/service/PartnerPortalservice.svc/rest/";
    private static final String method = "POST";
    private static final String register_key = "98a82402-85f6-4b03-9da7-b5fe54ccf3da";
    private static final String content_type = "application/json";
    private static HashMap<String, String> properties = new HashMap<>();

    static {
        properties.put("RegisterKey", register_key);
        properties.put("Content-Type", content_type);
    }

    public String request(String apiName, String data) throws IOException {
        String output = call(address_prefix + apiName, data, method, properties);
        return output;
    }

    public <T> T request(String apiName, String data, Class<T> type) throws IOException {
        String output = call(address_prefix + apiName, data, method, properties);
        ObjectMapper mapper = new ObjectMapper();
        T result = mapper.readValue(output, type);
        return result;
    }

    public static class Input {
        public String apiName;
        public String data;

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }


}
