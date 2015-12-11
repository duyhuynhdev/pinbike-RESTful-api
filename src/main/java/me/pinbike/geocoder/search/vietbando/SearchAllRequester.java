package me.pinbike.geocoder.search.vietbando;

import com.google.gson.Gson;
import me.pinbike.geocoder.search.vietbando.common.Requester;
import me.pinbike.geocoder.search.vietbando.common.ResponseObject;
import me.pinbike.util.LogUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by hpduy17 on 12/10/15.
 */
public class SearchAllRequester {
    private final String api_name = "SearchAll";
    private Logger logger = LogUtil.getLogger(this.getClass());

    public Output call(Input input) throws IOException {
        logger.info(input.toString());
        System.out.println(input.toString());
        Output output = new Requester().request(api_name, input.toString(), Output.class);
        logger.info(output.toString());
        System.out.println(output.toString());
        return output;
    }

    public static class Input {
        public String Keyword;
        public int Page;
        public int PageSize;
        public double Lx;
        public double Ly;
        public double Rx;
        public double Ry;
        public boolean IsOrder;

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }

    }

    public static class Output extends ResponseObject {
        public List<Place> List;

        public static class Place {
            public String Building;
            public String District;
            public String Fax;
            public String Floor;
            public double Latitude;
            public double Longitude;
            public String Name;
            public String Number;
            public String Phone;
            public String Province;
            public String Room;
            public String Street;
            public String VietbandoId;
            public String Ward;
        }

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }

    }
}
