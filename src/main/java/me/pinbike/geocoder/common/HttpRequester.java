package me.pinbike.geocoder.common;

import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by hpduy17 on 12/10/15.
 */
public class HttpRequester {

    public String call(String address, String data, final String method, @Nullable HashMap<String, String> properties) throws IOException {
        URL url = new URL(address);
        HttpURLConnection myConn = (HttpURLConnection) url.openConnection();
        myConn.setDoOutput(true); // do output or post
        myConn.setRequestMethod(method);
        if (properties != null) {
            for (String key : properties.keySet()) {
                myConn.setRequestProperty(key, properties.get(key));
            }
        }
        PrintWriter po = new PrintWriter(new OutputStreamWriter(myConn.getOutputStream(), "UTF-8"));
        po.println(data);
        po.close();
        //read data
        StringBuffer strBuffer = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(myConn.getInputStream(), "UTF-8"));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            strBuffer.append(inputLine);
        }
        in.close();
        return strBuffer.toString();
    }
}
