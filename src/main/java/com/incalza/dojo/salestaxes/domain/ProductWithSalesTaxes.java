package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;

/**
 * Created by sincalza on 27/05/2017.
 */
public class ProductWithSalesTaxes extends Product {
    private TaxCalculator taxCalculator;

    public ProductWithSalesTaxes(Product product, TaxCalculator taxCalculator) {
        super(product.getPrice(), product.getDescription(), product.getType(), product.isImported());
        this.taxCalculator = taxCalculator;
    }

    public BigDecimal getPricePlusSalesTaxes() {
        return getPrice().add(taxCalculator.apply(this));
    }

    public BigDecimal getSalesTaxes() {
        return taxCalculator.apply(this);
    }
    
}
