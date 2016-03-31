package me.pinbike.util;

import me.pinbike.util.common.Path;

import java.io.IOException;
import java.util.List;

/**
 * Created by hpduy17 on 10/13/15.
 */
public class PinBikeConstant {
    public static final String md5key = "GoBigOrGoHome";
    public static final String accesskey = "pinbikeonair";
    public static final String APPLICATION_JSON_UTF8 = "application/json;";

    public static class BackEndConfig {
//        public static final String Host = "pinride.ddns.net";
        public static final String Host = "103.20.148.111";
        public static final int Port = 9000;
//        public static final String Host_Payment = "pinride.ddns.net";
        public static final String Host_Payment = "103.20.148.111";
        public static final int Port_Payment = 9002;
    }

    public static class TripConst {
        public static int NUMBER_OF_DRIVERS = 5;
    }

    public static class CustomerServiceMail {
        public static final String[] feedback_mails = {"service@pinride.me", "tungstuff@pinride.me", "duyhuynh@pinride.me"};
    }

    public static class DeveloperMail {
        public static final String[] backend_mails = {"anngoc2017@gmail.com"};
        public static final String[] api_mails = {"errorbot@pinride.me"};
    }


    public static class MailLayout {
        public static String error_mail_subject = "[ERROR] - Some exceptions R";
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
        public static String title_forget_password = "Your PinBike account was requested reset password";
        public static String title_change_password = "Your PinBike account was changed password";
        public static String title_feedback = " have sent a feedback to you";

        /**
         * forget password format
         * args:
         * 1: summary
         * 2: profile image
         * 3: name
         * 4: requested date
         * 5: new password
         * 6: Os
         * 7: Device
         * 8: IP address
         * 9: Location
         */
        public static String getForgetPasswordLayOut(String summary, String profilePicture, String name, String requestedDate, String newPassword, String os, String device, String ipAddress, String location, String email) throws IOException {
            return "<div dir=\"ltr\" id=\"yui_3_16_0_1_1453518493797_3125\">\n" +
                    "   <table cellspacing=\"0\" cellpadding=\"0\" id=\"yiv7738454714email_table\" style=\"border-collapse:collapse;width:98%;\" border=\"0\">\n" +
                    "      <tbody id=\"yui_3_16_0_1_1453518493797_3124\">\n" +
                    "         <tr id=\"yui_3_16_0_1_1453518493797_3123\">\n" +
                    "            <td id=\"yiv7738454714email_content\" style=\"font-size:12px;\">\n" +
                    "               <span style=\"width:620px;color:#FFFFFF;;font-size:1px;\">" + summary + "</span>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3186\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3185\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3184\">\n" +
                    "                        <td style=\"font-size:16px;background:#198BB6;color:#FFFFFF;font-weight:bold;vertical-align:baseline;letter-spacing:-0.03em;padding:5px 20px;\" id=\"yui_3_16_0_1_1453518493797_3183\"><a rel=\"nofollow\" style=\"text-decoration:none;\" target=\"_blank\" href=\"www.pinbike.me\" id=\"yui_3_16_0_1_1453518493797_3207\"><span style=\"background:#198BB6;color:#FFFFFF;font-weight:bold;vertical-align:middle;font-size:16px;letter-spacing:-0.03em;vertical-align:baseline;\" id=\"yui_3_16_0_1_1453518493797_3206\">PinBike</span></a></td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" width=\"620px\" style=\"border-collapse:collapse;width:620px;\" border=\"0\" id=\"yui_3_16_0_1_1453518493797_3139\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3138\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3137\">\n" +
                    "                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;background-color:#f2f2f2;border-left:none;border-right:none;border-top:none;border-bottom:none;\" id=\"yui_3_16_0_1_1453518493797_3136\">\n" +
                    "                           <table cellspacing=\"0\" cellpadding=\"0\" width=\"620px\" style=\"border-collapse:collapse;\" id=\"yui_3_16_0_1_1453518493797_3135\">\n" +
                    "                              <tbody id=\"yui_3_16_0_1_1453518493797_3134\">\n" +
                    "                                 <tr id=\"yui_3_16_0_1_1453518493797_3133\">\n" +
                    "                                    <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3132\">\n" +
                    "                                       <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse;width:100%;\" id=\"yui_3_16_0_1_1453518493797_3131\">\n" +
                    "                                          <tbody id=\"yui_3_16_0_1_1453518493797_3130\">\n" +
                    "                                             <tr id=\"yui_3_16_0_1_1453518493797_3129\">\n" +
                    "                                                <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:20px;background-color:#fff;border-left:none;border-right:none;border-top:none;border-bottom:none;\" id=\"yui_3_16_0_1_1453518493797_3128\">\n" +
                    "                                                   <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;\" id=\"yui_3_16_0_1_1453518493797_3147\">\n" +
                    "                                                      <tbody id=\"yui_3_16_0_1_1453518493797_3146\">\n" +
                    "                                                         <tr id=\"yui_3_16_0_1_1453518493797_3145\">\n" +
                    "                                                            <td valign=\"top\" style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-right:15px;text-align:left;\" id=\"yui_3_16_0_1_1453518493797_3208\"><img width=\"50\" height=\"50\" src=\"" + profilePicture + "\" style=\"border:0;\"></td>\n" +
                    "                                                            <td valign=\"top\" style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;width:100%;text-align:left;\" id=\"yui_3_16_0_1_1453518493797_3144\">\n" +
                    "                                                               <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;width:100%;\" id=\"yui_3_16_0_1_1453518493797_3143\">\n" +
                    "                                                                  <tbody id=\"yui_3_16_0_1_1453518493797_3142\">\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3192\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3191\">Hi " + name + ",</td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3210\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3209\">Your Pinbike password was requested to get back on " + requestedDate + ".</td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3141\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3140\">\n" +
                    "                                                                           <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin-top:5px;margin-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3215\">\n" +
                    "                                                                              <tbody id=\"yui_3_16_0_1_1453518493797_3214\">\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3295\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3329\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3328\">Operating system: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3294\">" + os + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3213\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3212\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3211\">Device: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3323\">" + device + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3269\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3268\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3267\">IP address: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\">" + ipAddress + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3218\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3217\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3216\">Estimated location: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\">" + location + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                              </tbody>\n" +
                    "                                                                           </table>\n" +
                    "                                                                        </td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3149\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3148\"><strong>If you did this,</strong>  please <a rel=\"nofollow\" target=\"_blank\" href=\"" + Path.getInstance().buildGetNewPasswordUrl(newPassword, email) + "\"  style=\"color:#3b5998;text-decoration:none;\">click this link to reset your password.</b></a> Your new password is <b>" + newPassword + ".</td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3151\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3150\"><strong>If you didn't do this,</strong> you can safely disregard this email. </td>" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3223\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3222\"></td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3182\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;\" id=\"yui_3_16_0_1_1453518493797_3181\">Thanks,<br>The PinBike Security Team</td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                  </tbody>\n" +
                    "                                                               </table>\n" +
                    "                                                            </td>\n" +
                    "                                                         </tr>\n" +
                    "                                                      </tbody>\n" +
                    "                                                   </table>\n" +
                    "                                                </td>\n" +
                    "                                             </tr>\n" +
                    "                                          </tbody>\n" +
                    "                                       </table>\n" +
                    "                                    </td>\n" +
                    "                                 </tr>\n" +
                    "                                 <tr>\n" +
                    "                                    <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;width:620px;\">\n" +
                    "                                       <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\" style=\"border-collapse:collapse;\">\n" +
                    "                                          <tbody>\n" +
                    "                                             <tr>\n" +
                    "                                                <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0;background-color:#fff;border-left:none;border-right:none;border-top:1px solid #ccc;border-bottom:none;\"></td>\n" +
                    "                                             </tr>\n" +
                    "                                          </tbody>\n" +
                    "                                       </table>\n" +
                    "                                    </td>\n" +
                    "                                 </tr>\n" +
                    "                              </tbody>\n" +
                    "                           </table>\n" +
                    "                        </td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3155\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3154\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3153\">\n" +
                    "                        <td style=\"font-size:11px;padding:30px 20px;background-color:#fff;border-left:none;border-right:none;border-top:none;border-bottom:none;color:#999999;border:none;\" id=\"yui_3_16_0_1_1453518493797_3152\">This message was sent from your request on PinBike app.<br>PinBike, Inc., Attention: Community Support, Ho Chi Minh City, Vietnam</td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <span style=\"width:620px;\"></span>\n" +
                    "            </td>\n" +
                    "         </tr>\n" +
                    "      </tbody>\n" +
                    "   </table>\n" +
                    "</div>";
        }

        public static String getChangePasswordLayOut(String summary, String profilePicture, String name, String requestedDate, String newPassword, String os, String device, String ipAddress, String location) throws IOException {
            return "<div dir=\"ltr\" id=\"yui_3_16_0_1_1453518493797_3125\">\n" +
                    "   <table cellspacing=\"0\" cellpadding=\"0\" id=\"yiv7738454714email_table\" style=\"border-collapse:collapse;width:98%;\" border=\"0\">\n" +
                    "      <tbody id=\"yui_3_16_0_1_1453518493797_3124\">\n" +
                    "         <tr id=\"yui_3_16_0_1_1453518493797_3123\">\n" +
                    "            <td id=\"yiv7738454714email_content\" style=\"font-size:12px;\">\n" +
                    "               <span style=\"width:620px;color:#FFFFFF;;font-size:1px;\">" + summary + "</span>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3186\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3185\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3184\">\n" +
                    "                        <td style=\"font-size:16px;background:#198BB6;color:#FFFFFF;font-weight:bold;vertical-align:baseline;letter-spacing:-0.03em;padding:5px 20px;\" id=\"yui_3_16_0_1_1453518493797_3183\"><a rel=\"nofollow\" style=\"text-decoration:none;\" target=\"_blank\" href=\"www.pinbike.me\" id=\"yui_3_16_0_1_1453518493797_3207\"><span style=\"background:#198BB6;color:#FFFFFF;font-weight:bold;vertical-align:middle;font-size:16px;letter-spacing:-0.03em;vertical-align:baseline;\" id=\"yui_3_16_0_1_1453518493797_3206\">PinBike</span></a></td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" width=\"620px\" style=\"border-collapse:collapse;width:620px;\" border=\"0\" id=\"yui_3_16_0_1_1453518493797_3139\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3138\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3137\">\n" +
                    "                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;background-color:#f2f2f2;border-left:none;border-right:none;border-top:none;border-bottom:none;\" id=\"yui_3_16_0_1_1453518493797_3136\">\n" +
                    "                           <table cellspacing=\"0\" cellpadding=\"0\" width=\"620px\" style=\"border-collapse:collapse;\" id=\"yui_3_16_0_1_1453518493797_3135\">\n" +
                    "                              <tbody id=\"yui_3_16_0_1_1453518493797_3134\">\n" +
                    "                                 <tr id=\"yui_3_16_0_1_1453518493797_3133\">\n" +
                    "                                    <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3132\">\n" +
                    "                                       <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse;width:100%;\" id=\"yui_3_16_0_1_1453518493797_3131\">\n" +
                    "                                          <tbody id=\"yui_3_16_0_1_1453518493797_3130\">\n" +
                    "                                             <tr id=\"yui_3_16_0_1_1453518493797_3129\">\n" +
                    "                                                <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:20px;background-color:#fff;border-left:none;border-right:none;border-top:none;border-bottom:none;\" id=\"yui_3_16_0_1_1453518493797_3128\">\n" +
                    "                                                   <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;\" id=\"yui_3_16_0_1_1453518493797_3147\">\n" +
                    "                                                      <tbody id=\"yui_3_16_0_1_1453518493797_3146\">\n" +
                    "                                                         <tr id=\"yui_3_16_0_1_1453518493797_3145\">\n" +
                    "                                                            <td valign=\"top\" style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-right:15px;text-align:left;\" id=\"yui_3_16_0_1_1453518493797_3208\"><img width=\"50\" height=\"50\" src=\"" + profilePicture + "\" style=\"border:0;\"></td>\n" +
                    "                                                            <td valign=\"top\" style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;width:100%;text-align:left;\" id=\"yui_3_16_0_1_1453518493797_3144\">\n" +
                    "                                                               <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;width:100%;\" id=\"yui_3_16_0_1_1453518493797_3143\">\n" +
                    "                                                                  <tbody id=\"yui_3_16_0_1_1453518493797_3142\">\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3192\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3191\">Hi " + name + ",</td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3210\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3209\">Your PinBike password was changed on " + requestedDate + ". Your new password is <b>" + newPassword + "</b></a>.</td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3141\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3140\">\n" +
                    "                                                                           <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin-top:5px;margin-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3215\">\n" +
                    "                                                                              <tbody id=\"yui_3_16_0_1_1453518493797_3214\">\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3295\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3329\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3328\">Operating system: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3294\">" + os + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3213\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3212\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3211\">Device: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3323\">" + device + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3269\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3268\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3267\">IP address: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\">" + ipAddress + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3218\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3217\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3216\">Estimated location: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\">" + location + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                              </tbody>\n" +
                    "                                                                           </table>\n" +
                    "                                                                        </td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3149\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3148\"><strong>If you did this,</strong> you can safely disregard this email.</td>" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3151\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3150\"><strong>If you didn't do this,</strong> please <a rel=\"nofollow\" target=\"_blank\" href=\"mailto:service@pinride.me\"  style=\"color:#3b5998;text-decoration:none;\">contact us </a> and <b>change your password</b> at PinBike app.</td>" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3223\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3222\"></td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3182\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;\" id=\"yui_3_16_0_1_1453518493797_3181\">Thanks,<br>The PinBike Security Team</td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                  </tbody>\n" +
                    "                                                               </table>\n" +
                    "                                                            </td>\n" +
                    "                                                         </tr>\n" +
                    "                                                      </tbody>\n" +
                    "                                                   </table>\n" +
                    "                                                </td>\n" +
                    "                                             </tr>\n" +
                    "                                          </tbody>\n" +
                    "                                       </table>\n" +
                    "                                    </td>\n" +
                    "                                 </tr>\n" +
                    "                                 <tr>\n" +
                    "                                    <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;width:620px;\">\n" +
                    "                                       <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\" style=\"border-collapse:collapse;\">\n" +
                    "                                          <tbody>\n" +
                    "                                             <tr>\n" +
                    "                                                <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0;background-color:#fff;border-left:none;border-right:none;border-top:1px solid #ccc;border-bottom:none;\"></td>\n" +
                    "                                             </tr>\n" +
                    "                                          </tbody>\n" +
                    "                                       </table>\n" +
                    "                                    </td>\n" +
                    "                                 </tr>\n" +
                    "                              </tbody>\n" +
                    "                           </table>\n" +
                    "                        </td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3155\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3154\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3153\">\n" +
                    "                        <td style=\"font-size:11px;padding:30px 20px;background-color:#fff;border-left:none;border-right:none;border-top:none;border-bottom:none;color:#999999;border:none;\" id=\"yui_3_16_0_1_1453518493797_3152\">This message was sent from your request on PinBike app.<br>PinBike, Inc., Attention: Community Support, Ho Chi Minh City, Vietnam</td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <span style=\"width:620px;\"></span>\n" +
                    "            </td>\n" +
                    "         </tr>\n" +
                    "      </tbody>\n" +
                    "   </table>\n" +
                    "</div>";
        }

        public static String getFeedbackLayout(String feedbackContent, String profilePicture, String name, String email, String phone, List<String> feedbackImages, String feedback_date) throws IOException {
            String html = "<div dir=\"ltr\" id=\"yui_3_16_0_1_1453518493797_3125\">\n" +
                    "   <table cellspacing=\"0\" cellpadding=\"0\" id=\"yiv7738454714email_table\" style=\"border-collapse:collapse;width:98%;\" border=\"0\">\n" +
                    "      <tbody id=\"yui_3_16_0_1_1453518493797_3124\">\n" +
                    "         <tr id=\"yui_3_16_0_1_1453518493797_3123\">\n" +
                    "            <td id=\"yiv7738454714email_content\" style=\"font-size:12px;\">\n" +
                    "               <span style=\"width:620px;color:#FFFFFF;;font-size:1px;\">" + feedbackContent + "</span>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3186\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3185\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3184\">\n" +
                    "                        <td style=\"font-size:16px;background:#198BB6;color:#FFFFFF;font-weight:bold;vertical-align:baseline;letter-spacing:-0.03em;padding:5px 20px;\" id=\"yui_3_16_0_1_1453518493797_3183\"><a rel=\"nofollow\" style=\"text-decoration:none;\" target=\"_blank\" href=\"www.pinbike.me\" id=\"yui_3_16_0_1_1453518493797_3207\"><span style=\"background:#198BB6;color:#FFFFFF;font-weight:bold;vertical-align:middle;font-size:16px;letter-spacing:-0.03em;vertical-align:baseline;\" id=\"yui_3_16_0_1_1453518493797_3206\">PinBike</span></a></td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" width=\"620px\" style=\"border-collapse:collapse;width:620px;\" border=\"0\" id=\"yui_3_16_0_1_1453518493797_3139\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3138\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3137\">\n" +
                    "                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;background-color:#f2f2f2;border-left:none;border-right:none;border-top:none;border-bottom:none;\" id=\"yui_3_16_0_1_1453518493797_3136\">\n" +
                    "                           <table cellspacing=\"0\" cellpadding=\"0\" width=\"620px\" style=\"border-collapse:collapse;\" id=\"yui_3_16_0_1_1453518493797_3135\">\n" +
                    "                              <tbody id=\"yui_3_16_0_1_1453518493797_3134\">\n" +
                    "                                 <tr id=\"yui_3_16_0_1_1453518493797_3133\">\n" +
                    "                                    <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3132\">\n" +
                    "                                       <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse;width:100%;\" id=\"yui_3_16_0_1_1453518493797_3131\">\n" +
                    "                                          <tbody id=\"yui_3_16_0_1_1453518493797_3130\">\n" +
                    "                                             <tr id=\"yui_3_16_0_1_1453518493797_3129\">\n" +
                    "                                                <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:20px;background-color:#fff;border-left:none;border-right:none;border-top:none;border-bottom:none;\" id=\"yui_3_16_0_1_1453518493797_3128\">\n" +
                    "                                                   <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;\" id=\"yui_3_16_0_1_1453518493797_3147\">\n" +
                    "                                                      <tbody id=\"yui_3_16_0_1_1453518493797_3146\">\n" +
                    "                                                         <tr id=\"yui_3_16_0_1_1453518493797_3145\">\n" +
                    "                                                            <td valign=\"top\" style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-right:15px;text-align:left;\" id=\"yui_3_16_0_1_1453518493797_3208\"><img width=\"50\" height=\"50\" src=\"" + profilePicture + "\" style=\"border:0;\"></td>\n" +
                    "                                                            <td valign=\"top\" style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;width:100%;text-align:left;\" id=\"yui_3_16_0_1_1453518493797_3144\">\n" +
                    "                                                               <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;width:100%;\" id=\"yui_3_16_0_1_1453518493797_3143\">\n" +
                    "                                                                  <tbody id=\"yui_3_16_0_1_1453518493797_3142\">\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3192\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3191\">To service team,</td>\n" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3210\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3209\">" + name + " have sent the feedback since " + feedback_date + ". <br/>  <br/> \"" + feedbackContent + "\"" +
                    "                                                                     </tr>\n" +
                    "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3141\">\n" +
                    "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3140\">\n" +
                    "                                                                           <table cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin-top:5px;margin-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3215\">\n" +
                    "                                                                              <tbody id=\"yui_3_16_0_1_1453518493797_3214\">\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3295\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3329\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3328\">User name: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3294\">" + name + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3213\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3212\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3211\">Email: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3323\">" + email + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                                 <tr style=\"\" id=\"yui_3_16_0_1_1453518493797_3269\">\n" +
                    "                                                                                    <td style=\"padding-left:10px;\" id=\"yui_3_16_0_1_1453518493797_3268\"><span style=\"color:#808080;\" id=\"yui_3_16_0_1_1453518493797_3267\">Phone: </span></td>\n" +
                    "                                                                                    <td style=\"padding-left:10px;\">" + phone + "</td>\n" +
                    "                                                                                 </tr>\n" +
                    "                                                                              </tbody>\n" +
                    "                                                                           </table>\n" +
                    "                                                                        </td>\n" +
                    "                                                                     </tr>\n";
            if (feedbackImages != null && !feedbackImages.isEmpty()) {
                for (String url : feedbackImages) {
                    html += "                                                                     <tr id=\"yui_3_16_0_1_1453518493797_3149\">\n" +
                            "                                                                        <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding-top:5px;padding-bottom:5px;\" id=\"yui_3_16_0_1_1453518493797_3148\"><img src=\"" + Path.getInstance().getUrlFromPath(url) + "\" style=\"border:0;\"></td>" +
                            "                                                                     </tr>\n";
                }
            }
            html += "                                                                  </tbody>\n" +
                    "                                                               </table>\n" +
                    "                                                            </td>\n" +
                    "                                                         </tr>\n" +
                    "                                                      </tbody>\n" +
                    "                                                   </table>\n" +
                    "                                                </td>\n" +
                    "                                             </tr>\n" +
                    "                                          </tbody>\n" +
                    "                                       </table>\n" +
                    "                                    </td>\n" +
                    "                                 </tr>\n" +
                    "                                 <tr>\n" +
                    "                                    <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0px;width:620px;\">\n" +
                    "                                       <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\" style=\"border-collapse:collapse;\">\n" +
                    "                                          <tbody>\n" +
                    "                                             <tr>\n" +
                    "                                                <td style=\"font-size:11px;font-family:LucidaGrande, tahoma, verdana, arial, sans-serif;padding:0;background-color:#fff;border-left:none;border-right:none;border-top:1px solid #ccc;border-bottom:none;\"></td>\n" +
                    "                                             </tr>\n" +
                    "                                          </tbody>\n" +
                    "                                       </table>\n" +
                    "                                    </td>\n" +
                    "                                 </tr>\n" +
                    "                              </tbody>\n" +
                    "                           </table>\n" +
                    "                        </td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"border-collapse:collapse;width:620px;\" id=\"yui_3_16_0_1_1453518493797_3155\">\n" +
                    "                  <tbody id=\"yui_3_16_0_1_1453518493797_3154\">\n" +
                    "                     <tr id=\"yui_3_16_0_1_1453518493797_3153\">\n" +
                    "                        <td style=\"font-size:11px;padding:30px 20px;background-color:#fff;border-left:none;border-right:none;border-top:none;border-bottom:none;color:#999999;border:none;\" id=\"yui_3_16_0_1_1453518493797_3152\">This message was sent from customer feedback on PinBike app.<br>PinBike, Inc., Attention: Community Support, Ho Chi Minh City, Vietnam</td>\n" +
                    "                     </tr>\n" +
                    "                  </tbody>\n" +
                    "               </table>\n" +
                    "               <span style=\"width:620px;\"></span>\n" +
                    "            </td>\n" +
                    "         </tr>\n" +
                    "      </tbody>\n" +
                    "   </table>\n" +
                    "</div>";
            return html;
        }
    }


}
