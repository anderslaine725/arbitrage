package com.arbi.util;

import java.text.DecimalFormat;

public class NumberUtil {
    public static int strToInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {}
        return defaultValue;
    }

    public static long strToLong(String str, long defaultValue) {
        try {
            return Long.parseLong(str);
        } catch (Exception ex) {}
        return defaultValue;
    }

    public static Double strToDouble(String str, double defaultValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception ex) {}
        return defaultValue;
    }

    public static String format(int number, String decimalFormat) {
        return new DecimalFormat(decimalFormat).format(number);
    }

    public static String format(long number, String decimalFormat) {
        return new DecimalFormat(decimalFormat).format(number);
    }
    
    public static String format(double number, String decimalFormat) {
        return new DecimalFormat(decimalFormat).format(number);
    }
}
