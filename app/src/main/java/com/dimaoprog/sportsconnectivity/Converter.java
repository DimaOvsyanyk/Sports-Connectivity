package com.dimaoprog.sportsconnectivity;

import android.arch.persistence.room.TypeConverter;
import android.databinding.InverseMethod;

import java.util.Date;

public class Converter {

    @InverseMethod("stringToInt")
    public static String intToString(int value) {
        return value == 0 ? "" : String.valueOf(value);
    }

    public static int stringToInt(String value) {
        return value.isEmpty() ? 0 : Integer.valueOf(value);
    }

    @InverseMethod("stringToDouble")
    public static String doubleToString(double value) {
        return String.valueOf(value);
    }

    public static double stringToDouble(String value) {
        return Double.valueOf(value);
    }

    @TypeConverter
    public static Date toDate(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }

}