package com.menakasoft.foodiex.util;

import java.util.Locale;
import java.util.regex.Pattern;

public class ParseHelper {
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern MOBILE = Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");

    public static boolean isValidEmail(String e) {
        return e != null && EMAIL.matcher(e.trim()).matches();
    }

    public static boolean isValidMobile(String m) {
        return m != null && MOBILE.matcher(m.trim()).matches();
    }

    public static boolean isValidPassword(String p) {
        return p != null && PASSWORD.matcher(p).matches();
    }

    public static String formatPrice(double price) {
        return String.format(Locale.ROOT, "Rs. %.2f", price);
    }

    public static void printDivider() {
        System.out.println("--------------------------------------------------");
    }

    public static void printHeader(String t) {
        System.out.println();
        printDivider();
        System.out.println("  " + t);
        printDivider();
    }
}
