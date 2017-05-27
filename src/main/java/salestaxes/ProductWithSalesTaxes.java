package salestaxes;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Created by sincalza on 27/05/2017.
 */
public class ProductWithSalesTaxes extends Product {
    public ProductWithSalesTaxes(Product product) {
        super(product.getDescription(), product.getPrice(), product.getType(), product.isImported());
    }


    public BigDecimal getPricePlusSalesTaxes(TaxCalculator taxCalculator) {
        return getPrice().add(taxCalculator.apply(this));
    }

    public BigDecimal getSalesTaxes(TaxCalculator taxCalculator) {
        return taxCalculator.apply(this);
    }


}
