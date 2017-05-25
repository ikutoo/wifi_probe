package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sky on 2017/3/13.
 */
public class DateUtils {
    public static Long stringToLong(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String longToString(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return format.format(date);
    }
}
