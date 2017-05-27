package com.incalza.dojo.salestaxes;

import java.math.BigDecimal;

/**
 * Created by sincalza on 26/05/2017.
 */
public class Product {

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
}
