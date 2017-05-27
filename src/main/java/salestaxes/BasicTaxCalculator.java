package salestaxes;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.*;
import static java.util.Arrays.asList;
import static salestaxes.PriceUtils.percentOf;
import static salestaxes.PriceUtils.roundOffToFiveCents;
import static salestaxes.ProductType.*;

/**
 * Created by sincalza on 26/05/2017.
 */
public class BasicTaxCalculator implements TaxCalculator {

    private static final List<ProductType> PRODUCT_TYPES_EXCEPTION = asList(food, medicinal, book);

    @Override
    public BigDecimal apply(Product product) {
        if (isAProductInException(product)) return ZERO;
        return roundOffToFiveCents(
                percentOf(product.getPrice(), TEN)
        ).setScale(2, ROUND_HALF_UP);
    }


    private boolean isAProductInException(Product product) {
        return PRODUCT_TYPES_EXCEPTION.contains(product.getType());
    }
}
