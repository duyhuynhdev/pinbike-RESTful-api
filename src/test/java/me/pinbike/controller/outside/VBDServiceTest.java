package me.pinbike.controller.outside;

import me.pinbike.controller.TestRequester;
import me.pinbike.geocoder.search.vietbando.SearchAllRequester;
import me.pinbike.geocoder.search.vietbando.common.Requester;
import me.pinbike.sharedjava.model.constanst.AC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hpduy17 on 1/6/16.
 */
public class VBDServiceTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSearchAll() throws Exception {
        SearchAllRequester.Input input = new SearchAllRequester.Input();
        input.Keyword = "140 Phùng Văn Cung";
        input.Page = 1;
        input.PageSize = 10;
        input.Lx = 11.4396;
        input.Ly = 105.4138;
        input.Rx = 9.8579;
        input.Ry = 107.9681;
        input.IsOrder = true;
        Requester.Input request = new Requester.Input();
        request.apiName = "SearchAll";
        request.data = input.toString();
        TestRequester requester = new TestRequester();
        String response = requester.request(AC.MAIN_SERVER + "VBDBridgeAPI", request.toString());
        System.out.println(response);
    }
}