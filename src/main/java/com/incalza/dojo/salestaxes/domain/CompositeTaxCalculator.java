package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;
import java.util.List;

import static com.incalza.dojo.salestaxes.utils.Asserts.hasItems;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

/**
 * Created by sincalza on 27/05/2017.
 */
public class CompositeTaxCalculator implements TaxCalculator {
    private final List<TaxCalculator> taxCalculators;

    public CompositeTaxCalculator(TaxCalculator... taxCalculators) {
        hasItems("Tax Calculator cannot be empty, put one of a Tax Calculator instance at least.", taxCalculators);
        this.taxCalculators = asList(taxCalculators);
    }

    @Override
    public BigDecimal apply(Product product) {
        return taxCalculators.stream()
                .map(taxCalculator -> taxCalculator.apply(product))
                .reduce(ZERO, BigDecimal::add);
    }
}
