package com.incalza.dojo.salestaxes.domain;

import org.junit.Test;

import static com.incalza.dojo.salestaxes.domain.Product.Type.not_available;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sincalza on 27/05/2017.
 */
public class ProductTest {

    @Test
    public void getPriceOfPercentOf() throws Exception {
        Product product = new Product(TEN, "none", not_available);
        assertThat(product.getPriceOfPercentOf(TEN))
                .isEqualByComparingTo(ONE);
    }


}