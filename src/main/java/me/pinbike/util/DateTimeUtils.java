package me.pinbike.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hpduy17 on 6/17/15.
 */
public class DateTimeUtils {

    public static final long SECONDS = 1;
    public static final long MINUTES = 60;
    public static final long HOURS = 60 * 60;
    public static final long DAYS = 24 * 60 * 60;
    public static final long WEEKS = DAYS * 7;
    public static final long YEARS = DAYS * (365 * 4 + 1) / 4;
    public static final long MONTHS = YEARS / 12;

    public static final int SECONDS_PER_HOUR = 3600;
    public static final int SECONDS_PER_DAY = 86400;

    public static long now() {
        return new Date().getTime() / 1000;
    }

    public long getTimeFromMidNight(long epochTimeInSec) {
        return epochTimeInSec % SECONDS_PER_DAY;
    }

    public int getDateFrom1970(long epochTimeInSec) {
        return (int) epochTimeInSec / SECONDS_PER_DAY;
    }

    /**
     * @param dateOne epoch time
     * @param dateTwo epoch time
     * @return a time distance by second with sign (-) is dateOne before dateTwo (+) is dateOne after dateTwo
     * it will calculate time distance between 2 day but don't base on date
     * such as '1/2/1991 00:00:10' and '11/12/1891 00:00:50' is 40 second
     */
    public static long timeDistanceIgnoreDate(long dateOne, long dateTwo) {
        return Math.abs(dateOne % SECONDS_PER_DAY - dateTwo % SECONDS_PER_DAY);
    }

    /**
     * @param dateOne epoch time
     * @param dateTwo epoch time
     * @return a time distance by second with sign (-) is dateOne before dateTwo (+) is dateOne after dateTwo
     */
    public static long timeDistance(long dateOne, long dateTwo) {
        return Math.abs(dateOne - dateTwo);
    }

    public static long getTimeOnDay(long date) {
        return date % SECONDS_PER_DAY;
    }

    public static String getHCMFormatDate(long timeInMilisecond) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE, MMMMM dd, yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        return dateFormat.format(timeInMilisecond) + " at " + hourFormat.format(timeInMilisecond) + " (GTM +7)";
    }

    public static long parseddMMyyyyToSecond(String timeAsString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return dateFormat.parse(timeAsString).getTime() / 1000;
    }

    public static LinkedHashMap<Integer, List<Integer>> getYearAndMonthBetween2Day(long startDay, long endDay) {
        LinkedHashMap<Integer, List<Integer>> result = new LinkedHashMap<>();
        for (long i = startDay; i <= endDay; i = i + SECONDS_PER_DAY) {
            Date date = new Date(i * 1000);
            int year = com.pinride.pinbike.framework.util.DateTimeUtils.getYear(date);
            List<Integer> months = result.get(year);
            if (months == null) {
                months = new ArrayList<>();
                result.put(year, months);
            }
            if (!months.contains(date.getMonth()))
                months.add(com.pinride.pinbike.framework.util.DateTimeUtils.getMonth(date));
        }
        return result;
    }


}
