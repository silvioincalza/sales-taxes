package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;

/**
 * Created by sincalza on 26/05/2017.
 */
public class Product extends Model {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100.0);

    public enum Type {
        music,
        book,
        food,
        perfume,
        medicinal,
        not_available
    }

    private final String description;
    private final BigDecimal price;
    private final Type type;
    private boolean imported;

    public Product(BigDecimal price, String description, Type type, boolean imported) {
        this.description = description;
        this.price = price;
        this.type = type;
        this.imported = imported;
    }

    public Product(BigDecimal price, String description, Type type) {
        this(price, description, type, false);
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public boolean isImported() {
        return imported;
    }

    public BigDecimal getPercentOfPrice(BigDecimal percentOf) {
        return price.multiply(percentOf).divide(ONE_HUNDRED);
    }

}
