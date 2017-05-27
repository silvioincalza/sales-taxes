package com.incalza.dojo.salestaxes.utils;

public class Asserts {
    public static <T> void hasItems(String message, T... items) {
        if (items == null || items.length == 0)
            throw new IllegalArgumentException(message);
    }
}