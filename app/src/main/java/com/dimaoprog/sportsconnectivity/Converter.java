package com.dimaoprog.sportsconnectivity;
import android.databinding.InverseMethod;

public class Converter {

        @InverseMethod("stringToInt")
        public static String intToString(int value) {
            return String.valueOf(value);
        }

        public static int stringToInt(String value) {
            return Integer.valueOf(value);
        }

        @InverseMethod("stringToDouble")
        public static String doubleToString(double value) {
            return String.valueOf(value);
        }

        public static double stringToDouble(String value) {
            return Double.valueOf(value);
        }


}