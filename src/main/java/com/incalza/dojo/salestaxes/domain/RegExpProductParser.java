package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * Created by sincalza on 28/05/2017.
 */
public class RegExpProductParser implements ProductParser<String> {
    public static final Pattern REGEXP_PRODUCT_LINE = compile("((\\d)+[\\s\\t]([a-zA-Z]+)[\\s\\t]at[\\s\\t](\\d{1,11}(.\\d{1,2})?))");

    @Override
    public Optional<Product> parse(Optional<String> productLine) {
        return productLine.map(line -> {
            final Matcher matcher = REGEXP_PRODUCT_LINE.matcher(line);
            if (!matcher.find()) return null;
            final String description = matcher.group(3);
            final String price = matcher.group(4);
            return new Product(new BigDecimal(price), description, parseType(description));
        });
    }

    private Product.Type parseType(String description) {
        if (description.contains("book")) return Product.Type.book;
        return Product.Type.not_available;
    }

}
