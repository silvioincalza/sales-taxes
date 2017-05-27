package com.incalza.dojo.salestaxes.domain;

import com.incalza.dojo.salestaxes.utils.MathOperationUtils;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;

/**
 * Created by sincalza on 26/05/2017.
 */
public class ImportTaxCalculator implements TaxCalculator {


    @Override
    public BigDecimal apply(Product product) {
        if (!product.isImported()) return ZERO;
        return MathOperationUtils.roundOffToFiveCents(
                MathOperationUtils.percentOf(product.getPrice(), MathOperationUtils.FIVE)
        ).setScale(2, ROUND_HALF_UP);
    }
}
