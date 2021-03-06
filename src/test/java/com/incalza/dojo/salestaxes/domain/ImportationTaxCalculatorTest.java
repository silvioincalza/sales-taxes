package com.incalza.dojo.salestaxes.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static com.incalza.dojo.salestaxes.domain.Product.Type.book;
import static com.incalza.dojo.salestaxes.domain.Product.Type.perfume;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sincalza on 27/05/2017.
 */
public class ImportationTaxCalculatorTest {

    private TaxCalculator taxCalculator = new ImportationTaxCalculator();

    @Test
    public void whenProductIsBookThenNotApplySaleTax() throws Exception {
        assertThat(taxCalculator.apply(new Product(TEN, "", book, false)))
                .isEqualByComparingTo(ZERO);
    }


    @Test
    public void whenProductIsAGoodsTheApplySaleTax() throws Exception {
        assertThat(taxCalculator.apply(new Product(TEN, "", perfume, true)))
                .isEqualByComparingTo(new BigDecimal(0.50));
    }


}