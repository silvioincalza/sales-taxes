package com.incalza.dojo.salestaxes;

public class Assert {
    public static <T> void hasItems(String message, T... items) {
        if (items == null || items.length == 0)
            throw new IllegalArgumentException(message);
    }
}