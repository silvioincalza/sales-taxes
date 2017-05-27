package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;

import static com.incalza.dojo.salestaxes.utils.MathOperationUtils.roundOffToFiveCents;
import static java.math.BigDecimal.ZERO;

/**
 * Created by sincalza on 26/05/2017.
 */
public class ImportTaxCalculator implements TaxCalculator {

    private static final BigDecimal FIVE = BigDecimal.valueOf(5.0);

    @Override
    public BigDecimal apply(Product product) {
        if (!product.isImported()) return ZERO;
        return roundOffToFiveCents(product.getPriceOfPercentOf(FIVE));
    }
}
