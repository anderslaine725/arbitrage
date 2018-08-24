package com.arbi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date parseDate(String str, String datePattern) {
        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date;
    }

    public static String formatDate(Date date, String datePattern) {
        if (date == null)
            return "";

        return new SimpleDateFormat(datePattern).format(date);
    }

}
