package com.incalza.dojo.salestaxes.domain;

import java.util.Optional;

/**
 * Created by sincalza on 27/05/2017.
 */
public interface ProductReader {

    Optional<Product> read() throws NoSuchProductException;

    boolean hasNext();
}
