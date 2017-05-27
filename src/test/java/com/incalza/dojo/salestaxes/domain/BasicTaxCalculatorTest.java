package com.incalza.dojo.salestaxes;

import com.incalza.dojo.salestaxes.domain.BasicTaxCalculator;
import com.incalza.dojo.salestaxes.domain.Product;
import com.incalza.dojo.salestaxes.domain.TaxCalculator;
import org.junit.Test;

import java.math.BigDecimal;

import static com.incalza.dojo.salestaxes.domain.Product.Type.*;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sincalza on 27/05/2017.
 */
public class BasicTaxCalculatorTest {

    private TaxCalculator taxCalculator = new BasicTaxCalculator();

    @Test
    public void whenProductIsFoodThenNotApplySaleTax() throws Exception {
        assertThat(taxCalculator.apply(new Product(TEN, "", food)))
                .isEqualByComparingTo(ZERO);
    }

    @Test
    public void whenProductIsMedicinalThenNotApplySaleTax() throws Exception {
        assertThat(taxCalculator.apply(new Product(TEN, "", medicinal)))
                .isEqualByComparingTo(ZERO);
    }

    @Test
    public void whenProductIsBookThenNotApplySaleTax() throws Exception {
        assertThat(taxCalculator.apply(new Product(TEN, "", book)))
                .isEqualByComparingTo(ZERO);
    }


    @Test
    public void whenProductIsAGoodsTheApplySaleTax() throws Exception {
        assertThat(taxCalculator.apply(new Product(TEN, "", perfume)))
                .isEqualByComparingTo(new BigDecimal(1.00));
    }

}