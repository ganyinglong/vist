package com.gyl.visit.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    public static final String YYYY_MM_DD = "yyyy-MM -dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final long MILLS_PER_DAY = 86400000L;

    public static String format(Date date, String parttern, TimeZone timeZone) {
        SimpleDateFormat formater = new SimpleDateFormat(parttern);
        formater.setTimeZone(timeZone);
        return formater.format(date);
    }

    public static String format(Date date, String parttern) {
        SimpleDateFormat formater = new SimpleDateFormat(parttern);
        return formater.format(date);
    }

    public static long toUnixTimeStamp(long timeStamp) {
        return timeStamp / 1000;
    }

    public static Date afterDays(Date date, int days) {
        return afterDays(date.getTime(), days);
    }

    public static Date prase(String date, String parttern) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat(parttern);
        return formater.parse(date);
    }

    public static Date prase(String date) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return formater.parse(date);
    }

    /**
     * 几天之后的时间
     *
     * @param timstrap
     * @param days
     * @return
     */
    public static Date afterDays(long timstrap, int days) {
        return new Date(MILLS_PER_DAY * days + timstrap);
    }

    public static Date beforeDays(long timestrap, int days) {
        return new Date(timestrap - (MILLS_PER_DAY * days));
    }

    public static boolean isBefore(Date date1, Date date2) {
        if (null == date1) {
            return false;
        }
        return date1.before(date2);
    }

    public static boolean isAfter(Date date1, Date date2) {
        if (null == date1) {
            return false;
        }
        return date1.after(date2);
    }

    public static String timeDesc(Date date) {
        long millsBeforeNow = System.currentTimeMillis() - date.getTime();
        StringBuilder decs = new StringBuilder();
        if (millsBeforeNow < 60000L) {
            long s = millsBeforeNow / 1000L;
            decs.append(s).append("秒前");
        } else if (millsBeforeNow < 3600000L) {
            long m = millsBeforeNow / 60000L;
            decs.append(m).append("分钟前");
        } else if (millsBeforeNow < 86400000L) {
            long h = millsBeforeNow / 3600000L;
            decs.append(h).append("小时前");
        } else {
            long d = millsBeforeNow / 86400000L;
            decs.append(d).append("天前");
        }
        return decs.toString();
    }

    public static String timeDescWithSub(Date date) {
        long millsBeforeNow = System.currentTimeMillis() - date.getTime();
        StringBuilder decs = new StringBuilder();
        if (millsBeforeNow < 60000L) {
            long s = millsBeforeNow / 1000L;
            decs.append(s).append("秒前");
        } else if (millsBeforeNow < 3600000L) {
            long m = millsBeforeNow / 60000L;
            decs.append(m).append("分钟前");
        } else if (millsBeforeNow < 86400000L) {
            long h = millsBeforeNow / 3600000L;
            decs.append(h).append("小时前");
        } else {
            String format = format(date, "MM-dd HH:mm");
            decs.append(format);
        }
        return decs.toString();
    }
}
