package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Collections.unmodifiableList;

/**
 * Created by sincalza on 27/05/2017.
 */
public class Order extends Model {

    private final List<ProductWithSalesTaxes> products;

    public Order(List<ProductWithSalesTaxes> products) {
        this.products = unmodifiableList(products);
    }

    public BigDecimal total() {
        return products.stream()
                .map(product -> product.getPricePlusSalesTaxes())
                .reduce(ZERO, BigDecimal::add);
    }

    public BigDecimal salesTaxes() {
        return products.stream()
                .map(product -> product.getSalesTaxes())
                .reduce(ZERO, BigDecimal::add);
    }

    public List<ProductWithSalesTaxes> getProducts() {
        return products;
    }
}
