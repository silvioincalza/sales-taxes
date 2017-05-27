package com.incalza.dojo.salestaxes.domain;

import org.junit.Test;

import static com.incalza.dojo.salestaxes.domain.Product.Type.not_available;
import static com.incalza.dojo.salestaxes.domain.TaxCalculator.NONE;
import static java.math.BigDecimal.ONE;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sincalza on 27/05/2017.
 */
public class OrderFactoryTest {
    private OrderFactory orderFactory = new OrderFactory(NONE);

    @Test
    public void placeOrder() throws Exception {
        final Product product = new Product(ONE, "", not_available);
        final Order order = orderFactory.placeOrder(asList(product));

        assertThat(order).isNotNull();
        assertThat(order.getProducts()).contains(new ProductWithSalesTaxes(product, NONE));

    }
}
