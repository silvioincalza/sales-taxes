package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static com.incalza.dojo.salestaxes.utils.Asserts.hasItems;

/**
 * Created by sincalza on 26/05/2017.
 */
public class Basket {

    private List<Product> products;

    private TaxCalculator taxCalculator;

    public Basket(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public Basket put(Product... products) {
        hasItems("Product cannot be empty, put one of a product instance at least.", products);
        this.products = asList(products);
        return this;
    }

    public BigDecimal total() {
        return products.stream()
                .map(product -> new ProductWithSalesTaxes(product, taxCalculator).getPricePlusSalesTaxes())
                .reduce(ZERO, BigDecimal::add);
    }

    public BigDecimal salesTaxes() {
        return products.stream()
                .map(v -> new ProductWithSalesTaxes(v, taxCalculator).getSalesTaxes())
                .reduce(ZERO, BigDecimal::add);
    }
}
