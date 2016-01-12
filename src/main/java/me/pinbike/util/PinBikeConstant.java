package me.pinbike.util;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class PinBikeConstant {
    public static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

    public static class BackEndConfig {
        public static final String Host = "pinride.ddns.net";
        public static final int Port = 9000;
    }

    public static class TripConst {
        public static int NUMBER_OF_DRIVERS = 5;
    }

    public static class DeveloperMail {
        public static final String[] backend_mails = {"errorbot@pinride.me", "anngoc2017@gmail.com"};
    }

    public static class MailLayout {
        public static String error_mail_subject = "[ERROR] - Some exceptions raise";
        public static String error_mail = "<html><head>" + "<style type=\"text/css\">" +
                "   * {font:12px Arial, Helvetica, sans-serif;}" +
                "   table {border-collapse:collapse;}" +
                "   table.grid td {padding:0 7px;border:solid #ccc 1px;}" +
                "   .bold {font-weight:bold;padding-right:12px;}" +
                "</style></head>" +
                "<body>" +
                "<table>" +
                "<tr><td class=\"bold\">Error Code:</td><td>%d</td></tr>" +
                "<tr><td class=\"bold\">Exception:</td><td>%s</td></tr>" +
                "<tr><td class=\"bold\">Method Name:</td><td>%s</td></tr>" +
                "<tr><td class=\"bold\">Input Object:</td><td>%s</td></tr>" +
                "</table><br />" +
                "</body></html>";
    }
}
