package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * Created by sincalza on 26/05/2017.
 */
public class ImportationTaxCalculator extends RoundOffToFiveCentsTaxCalculator {

    private static final BigDecimal FIVE = BigDecimal.valueOf(5.0);

    @Override
    public BigDecimal doApply(Product product) {
        if (!product.isImported()) return ZERO;
        return product.getPriceOfPercentOf(FIVE);
    }
}
