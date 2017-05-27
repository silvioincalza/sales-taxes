package com.incalza.dojo.salestaxes.domain;

import java.io.Serializable;

/**
 * Created by sincalza on 27/05/2017.
 */
public interface ProductReader<T extends Serializable> {

    T read();

}
