package me.pinbike.util;

import org.junit.Test;

/**
 * Created by hpduy17 on 3/21/16.
 */
public class SendMailUtilTest {

    @Test
    public void testSend() throws Exception {
        String email = "abc";
        String content = "abc";
        new SendMailUtil("giaonguoinhanh@gmail.com", "Contact from GNN", "<html>content:" + content + "</br> email:" + email + "</html>");
    }
}