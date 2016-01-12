package me.pinbike.geocoder.search.vietbando;

import org.junit.Test;

/**
 * Created by hpduy17 on 12/11/15.
 */
public class SearchAllRequesterTest {

    @Test
    public void testCall() throws Exception {
        SearchAllRequester.Input input = new SearchAllRequester.Input();
        input.Keyword = "140 Phùng Văn Cung";
        input.Page = 1;
        input.PageSize = 10;
        input.Lx = 11.4396;
        input.Ly = 105.4138;
        input.Rx = 9.8579;
        input.Ry = 107.9681;
        input.IsOrder = true;
        new SearchAllRequester().call(input);
    }

    @Test
    public void testCall1() throws Exception {

    }
}