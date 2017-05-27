package salestaxes;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;
import static salestaxes.PriceUtils.*;

/**
 * Created by sincalza on 26/05/2017.
 */
public class ImportTaxCalculator implements TaxCalculator {


    @Override
    public BigDecimal apply(Product product) {
        if (!product.isImported()) return ZERO;
        return roundOffToFiveCents(
                percentOf(product.getPrice(), FIVE)
        ).setScale(2, ROUND_HALF_UP);
    }
}
