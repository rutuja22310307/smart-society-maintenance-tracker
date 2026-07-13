package com.society.maintenance.util;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}