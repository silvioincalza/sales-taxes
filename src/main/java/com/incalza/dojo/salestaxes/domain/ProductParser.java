package com.incalza.dojo.salestaxes.domain;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by sincalza on 28/05/2017.
 */
public interface ProductParser<T extends Serializable> {
    Optional<Product> parse(Optional<T> input);
}
