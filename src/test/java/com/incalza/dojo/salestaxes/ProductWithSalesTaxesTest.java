package com.incalza.dojo.salestaxes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static com.incalza.dojo.salestaxes.Product.Type.not_available;

/**
 * Created by sincalza on 27/05/2017.
 */
public class ProductWithSalesTaxesTest {

    private TaxCalculator taxCalculator = mock(TaxCalculator.class);
    private Product product = new Product(ONE, "", not_available);
    private ProductWithSalesTaxes productWithSalesTaxes = new ProductWithSalesTaxes(product, taxCalculator);

    @Before
    public void setUp() throws Exception {
        when(taxCalculator.apply(any(ProductWithSalesTaxes.class))).thenReturn(ONE);
    }

    @Test
    public void getPricePlusSalesTaxes() throws Exception {
        assertThat(productWithSalesTaxes.getPricePlusSalesTaxes())
                .isEqualByComparingTo(new BigDecimal("2.00"));
    }

    @Test
    public void getSalesTaxes() throws Exception {
        assertThat(productWithSalesTaxes.getSalesTaxes())
                .isEqualByComparingTo(new BigDecimal("1.00"));
    }

}