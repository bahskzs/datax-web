package com.wugui.datax.admin.util;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateFormatUtils {

    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String DATE_FORMAT2 = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String DATETIME_FORMAT2 = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String TIMESTAMP = "Timestamp";

    public static final List<String> formatList() {
        List<String> formatList = new ArrayList<>();
        formatList.add(DATE_FORMAT);
        formatList.add(TIME_FORMAT);
        formatList.add(DATETIME_FORMAT);
        formatList.add(TIMESTAMP);
        return formatList;
    }



    public static String getCurrentDate() {
        //参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT2, Locale.CHINESE);
        return dateFormat.format(new Date());
    }

    public static java.sql.Date getCurrentDate(boolean isDB) {
        java.sql.Date date = java.sql.Date.valueOf(DateFormatUtils.getCurrentDate());
        return date;
    }

//    public static void main(String[] args) {
//        System.out.println(DateFormatUtils.getCurrentDate(true).toString());
//    }
}
