package com.incalza.dojo.salestaxes;

import java.math.BigDecimal;

/**
 * Created by sincalza on 26/05/2017.
 */
public interface TaxCalculator {


    BigDecimal apply(Product product);


}
