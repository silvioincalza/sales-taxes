package salestaxes;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

/**
 * Created by sincalza on 26/05/2017.
 */
public interface TaxCalculator {


    BigDecimal apply(Product product);


}
