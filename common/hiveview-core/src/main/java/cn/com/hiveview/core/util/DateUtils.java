package cn.com.hiveview.core.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DateUtils{

    private static ThreadLocal<DateFormat> fullDateFormatter = new ThreadLocal<DateFormat>();
    private static ThreadLocal<DateFormat> shortDateFormatter = new ThreadLocal<DateFormat>();
    private static ThreadLocal<DateFormat> dateFormatter = new ThreadLocal<DateFormat>();
    private static ThreadLocal<DateFormat> yearFormatter = new ThreadLocal<DateFormat>();
    private static ThreadLocal<DateFormat> uploadFormatter = new ThreadLocal<DateFormat>();
    private static AtomicInteger ATOMIC = new AtomicInteger(1000);
    /**
     * @param date
     * @return String
     * @throws
     * @Title: dateToDay
     * @Description: 转化到天
     */
    public static String dateToDay(final Date date) {
        DateFormat df = dateFormatter.get();
        if (df == null) {
            df = new SimpleDateFormat("yyyyMMdd");
            dateFormatter.set(df);
        }
        return df.format(date);
    }
    public static String getFilePath() {
        DateFormat df = uploadFormatter.get();
        if (df == null) {
            df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss/");
            uploadFormatter.set(df);
        }
        return df.format(new Date());
    }
}
