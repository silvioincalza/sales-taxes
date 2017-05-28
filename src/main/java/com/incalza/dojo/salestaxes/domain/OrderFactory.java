package com.incalza.dojo.salestaxes.domain;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by sincalza on 27/05/2017.
 */
public class OrderFactory {

    private TaxCalculator taxCalculator;

    public OrderFactory(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public Order placeOrder(List<Product> products) {
        return new Order(products.stream().map(product -> new ProductWithSalesTaxes(product, taxCalculator)).collect(toList()));
    }
}
