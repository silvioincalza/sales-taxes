package com.incalza.dojo.salestaxes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by sincalza on 26/05/2017.
 */
public class Product {



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

    public BigDecimal getPriceOfPercentOf(BigDecimal percentOf) {
        return price.multiply(percentOf).divide(ONE_HUNDRED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return imported == product.imported &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price, type, imported);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("description", description)
                .append("price", price)
                .append("type", type)
                .append("imported", imported)
                .toString();
    }
}
