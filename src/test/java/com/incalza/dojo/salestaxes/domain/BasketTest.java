package com.incalza.dojo.salestaxes.domain;

import org.junit.Test;

import static com.incalza.dojo.salestaxes.domain.Product.Type.food;
import static com.incalza.dojo.salestaxes.domain.TaxCalculator.NOP;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sincalza on 27/05/2017.
 */
public class BasketTest {

    @Test
    public void whenDoCheckOutThenPlaceAOrder() {
        Basket basket = new Basket(new OrderFactory(NOP))
                .put(new Product(TEN, "a description", food));

        Order order = basket.checkOut();

        assertThat(order).isNotNull();
        assertThat(order.salesTaxes()).isEqualTo(ZERO);
        assertThat(order.total()).isEqualTo(TEN);
    }

}