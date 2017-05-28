package com.incalza.dojo.salestaxes.domain;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.regex.Pattern.compile;

/**
 * Created by sincalza on 28/05/2017.
 */
public class RegExpProductParser implements ProductParser<String> {
    public static final String REG_EXP_NUM_OF_PRODUCTS = "(\\d{1,3})";
    public static final String REG_EXP_DESCRIPTION = "([a-zA-Z\\s_-]+)";
    public static final String REG_EXP_PRICE = "(\\d{1,11}(.\\d{1,2})?)";
    public static final Pattern REG_EXP_PRODUCT_LINE = compile("(" + REG_EXP_NUM_OF_PRODUCTS +
            "[\\s\\t]" + REG_EXP_DESCRIPTION + "[\\s\\t]at[\\s\\t]" + REG_EXP_PRICE + ")");

    @Override
    public Optional<Product> parse(Optional<String> productLine) {
        return productLine.flatMap(line -> {
            final Matcher matcher = REG_EXP_PRODUCT_LINE.matcher(line);
            if (!matcher.find()) return empty();
            final String description = matcher.group(3);
            final String price = matcher.group(4);
            return of(new Product(new BigDecimal(price), description, parseType(description), isImported(description)));
        });
    }

    private boolean isImported(String description) {
        return description.contains("imported");
    }

    private Product.Type parseType(String description) {
        if (description.contains("book")) return Product.Type.book;
        if (description.contains("chocolate")) return Product.Type.food;
        if (description.contains("pill")) return Product.Type.medicinal;
        if (description.contains("perfume")) return Product.Type.perfume;
        if (description.contains("music")) return Product.Type.music;

        return Product.Type.not_available;
    }

}
