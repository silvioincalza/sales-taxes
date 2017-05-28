package com.incalza.dojo.salestaxes.domain;

import java.util.ArrayList;
import java.util.List;

import static com.incalza.dojo.salestaxes.utils.Asserts.hasItems;
import static java.util.Arrays.asList;

/**
 * Created by sincalza on 26/05/2017.
 */
public class Basket {

    private List<Product> products = new ArrayList<>();

    private final OrderFactory orderFactory;

    public Basket(OrderFactory orderFactory) {
        this.orderFactory = orderFactory;
    }

    public Basket put(Product... products) {
        hasItems("Product cannot be empty, put one of a product instance at least.", products);
        this.products.addAll(asList(products));
        return this;
    }

    public Order checkOut() {
        return orderFactory.placeOrder(products);
    }
}
