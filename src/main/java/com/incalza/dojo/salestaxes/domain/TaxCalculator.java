package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

/**
 * Created by sincalza on 26/05/2017.
 */
public interface TaxCalculator {

    BigDecimal apply(Product product);

    TaxCalculator NONE = product -> ZERO;
}
