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
    public static final String REG_EXP_NUM_OF_PRODUCTS = "(\\d{1,3})";
    public static final String REG_EXP_DESCRIPTION = "([a-zA-Z]+)";
    public static final String REG_EXP_PRICE = "(\\d{1,11}(.\\d{1,2})?)";
    public static final Pattern REG_EXP_PRODUCT_LINE = compile("(" + REG_EXP_NUM_OF_PRODUCTS +
            "[\\s\\t]" + REG_EXP_DESCRIPTION + "[\\s\\t]at[\\s\\t]" + REG_EXP_PRICE + ")");

    @Override
    public Optional<Product> parse(Optional<String> productLine) {
        return productLine.map(line -> {
            final Matcher matcher = REG_EXP_PRODUCT_LINE.matcher(line);
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
