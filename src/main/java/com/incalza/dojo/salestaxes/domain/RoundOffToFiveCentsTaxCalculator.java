package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;

import static com.incalza.dojo.salestaxes.utils.MathOperationUtils.roundOffToFiveCents;

/**
 * Created by sincalza on 28/05/2017.
 */
public abstract class RoundOffToFiveCentsTaxCalculator implements TaxCalculator {

    public abstract BigDecimal doApply(Product product);

    @Override
    public BigDecimal apply(Product product) {
        return roundOffToFiveCents(doApply(product));
    }
}
