package com.incalza.dojo.salestaxes.utils;

import java.math.BigDecimal;

import static java.lang.Math.ceil;
import static java.math.BigDecimal.valueOf;

public class MathOperationUtils {

    private static final BigDecimal FIVE_CENTS = valueOf(0.05);

    public static BigDecimal roundOffToFiveCents(BigDecimal value) {
        final double smallestValue = ceil(
                value
                        .divide(FIVE_CENTS)
                        .doubleValue()
        );
        return new BigDecimal(smallestValue).multiply(FIVE_CENTS);
    }
}