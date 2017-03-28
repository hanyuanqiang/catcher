package com.hyq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 跟时间相关的工具类
 */
public class DateUtil {

    public static final String DATE_FORMAT_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_PATTERN_MS1 = "yyyy-MM-dd HH:mm:ss.S";
    public static final String DATE_FORMAT_PATTERN_MS3 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_PATTERN_DOJO = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public static final String DATE_FORMAT_PATTERN_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_PATTERN_HOUR = "yyyy-MM-dd HH";
    public static final String DATE_FORMAT_PATTERN_MIN = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_PATTERN_MONTH1 = "yyyy-MM";
    public static final String DATE_FORMAT_PATTERN_MONTH2 = "yyyy.MM";

    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }

    private static Date add(Date date, int calendarField, int amount) {
        if(date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static Date setYears(Date date, int amount) {
        return set(date, 1, amount);
    }

    public static Date setMonths(Date date, int amount) {
        return set(date, 2, amount);
    }

    public static Date setDays(Date date, int amount) {
        return set(date, 5, amount);
    }

    public static Date setHours(Date date, int amount) {
        return set(date, 11, amount);
    }

    public static Date setMinutes(Date date, int amount) {
        return set(date, 12, amount);
    }

    public static Date setSeconds(Date date, int amount) {
        return set(date, 13, amount);
    }

    public static Date setMilliseconds(Date date, int amount) {
        return set(date, 14, amount);
    }

    private static Date set(Date date, int calendarField, int amount) {
        if(date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setLenient(false);
            c.setTime(date);
            c.set(calendarField, amount);
            return c.getTime();
        }
    }

    public static Calendar toCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    public static Calendar toCalendar(Date date, TimeZone tz) {
        Calendar c = Calendar.getInstance(tz);
        c.setTime(date);
        return c;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static String transDate2Str(Date date) throws ParseException {
       return transDate2Str(date,DATE_FORMAT_PATTERN_DEFAULT);
    }

    public static String transDate2Str(Date date,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date transStr2Date(String str) throws ParseException {
        return transStr2Date(str, DATE_FORMAT_PATTERN_DEFAULT);
    }

    public static Date transStr2Date(String str,String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static Date transObj2Date(Object obj){
        return transObj2Date(obj, null);
    }

    public static Date transObj2Date(Object obj, Date defaultValue){
        Date value = defaultValue;
        try{
            if(CheckUtil.isNull(obj)){
                return defaultValue;
            }else if(obj instanceof Date){
                value = (Date)obj;
            }else if(obj instanceof Long){
                value = new Date(((Long)obj).longValue());
            }else if(obj instanceof Calendar){
                value = ((Calendar)obj).getTime();
            }else if(obj instanceof String){
                try {
                    transStr2Date((String)obj);
                } catch (Exception e1) {}
            }
        }catch (Exception e){}
        return value;
    }

    //获取开始时间和结束时间之间的天数(如果结束时间小于开始时间则返回负数)
    public static long getDiffDays (String dateBegin, String dateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat (DATE_FORMAT_PATTERN_DEFAULT);
        try {
            Date begin=sdf.parse(dateBegin);
            Date end = sdf.parse(dateEnd);
            return (end.getTime()-begin.getTime())/(3600*24*1000) + 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getDiffDays(Date dateBegin, Date dateEnd) throws ParseException {
        String begin = transDate2Str(dateBegin);
        String end = transDate2Str(dateEnd);
        return getDiffDays(begin,end);
    }

    /**
     * 获得星期几(周日为1，周六为7)
     * @param date
     * 		给定日期
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得星期几（中文）
     * @param date
     * @return
     */
    public static String getWeekDayCN(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

    /**
     * 获取中文格式日期",如：1984年10月9日
     * @return
     */
    public static String getDateCN(Date date) {
        Calendar calendar = toCalendar(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "年" + month + "月" + day + "日";
    }

    /**
     * 获取包含星期的中文格式日期",如：1984年10月9日 星期二
     * @return
     */
    public static String getDateAndWeekCN(Date date) {
        Calendar calendar = toCalendar(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "年" + month + "月" + day + "日 " + getWeekDayCN(calendar.getTime());
    }
}
