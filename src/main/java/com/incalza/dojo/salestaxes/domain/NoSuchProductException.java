package com.incalza.dojo.salestaxes.domain;

import java.util.NoSuchElementException;

/**
 * Created by sincalza on 28/05/2017.
 */
public class NoSuchProductException extends NoSuchElementException {
    public NoSuchProductException(String message) {
        super(message);
    }
}
