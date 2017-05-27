package salestaxes;

import java.math.BigDecimal;

/**
 * Created by sincalza on 26/05/2017.
 */
public class Product {
    private final String description;
    private final BigDecimal price;
    private final ProductType type;
    private boolean imported;

    public Product(String description, BigDecimal price, ProductType type, boolean imported) {
        this.description = description;
        this.price = price;
        this.type = type;
        this.imported = imported;
    }

    public Product(String description, BigDecimal price, ProductType type) {
        this(description, price, type, false);
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }

    public boolean isImported() {
        return imported;
    }
}
