package me.pinbike.controller;

import me.pinbike.util.common.HttpRequester;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hpduy17 on 12/10/15.
 */
public class TestRequester extends HttpRequester {
    private static final String method = "POST";
    private static final String content_type = "application/json";
    private static HashMap<String, String> properties = new HashMap<>();

    static {
        properties.put("Content-Type", content_type);
    }

    public String request(String url, String data) throws IOException {
        String output = call(url, data, method, properties);
        return output;
    }

    public <T> T request(String url, String data, Class<T> type) throws IOException {
        String output = call(url, data, method, properties);
        JSONObject jsonObject = new JSONObject(output);
        ObjectMapper mapper = new ObjectMapper();
        T result = mapper.readValue(jsonObject.getJSONObject("result").toString(), type);
        return result;
    }


}
