package com.dimaoprog.sportsconnectivity;

import android.arch.persistence.room.TypeConverter;
import android.databinding.InverseMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    public static Date longToDate(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long dateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    public static String dateToString(Date date) {
        return date == null ? null : new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date);
    }

}