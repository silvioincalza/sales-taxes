package com.incalza.dojo.salestaxes.domain;

import com.incalza.dojo.salestaxes.domain.Product.Type;

import java.math.BigDecimal;
import java.util.List;

import static com.incalza.dojo.salestaxes.domain.Product.Type.*;
import static com.incalza.dojo.salestaxes.utils.MathOperationUtils.roundOffToFiveCents;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;

/**
 * Created by sincalza on 26/05/2017.
 */
public class BasicTaxCalculator implements TaxCalculator {

    private static final List<Type> PRODUCT_TYPES_EXCEPTION = asList(food, medicinal, book);

    @Override
    public BigDecimal apply(Product product) {
        if (isAProductInException(product)) return ZERO;
        return roundOffToFiveCents(product.getPriceOfPercentOf(TEN));
    }

    private boolean isAProductInException(Product product) {
        return PRODUCT_TYPES_EXCEPTION.contains(product.getType());
    }
}
