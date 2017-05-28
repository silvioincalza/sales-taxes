package com.incalza.dojo.salestaxes.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static com.incalza.dojo.salestaxes.domain.Product.Type.book;
import static com.incalza.dojo.salestaxes.domain.Product.Type.food;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sincalza on 28/05/2017.
 */
public class RegExpProductParserTest {

    private ProductParser<String> productParser = new RegExpProductParser();

    @Test
    public void nothingToParse() throws Exception {
        assertThat(productParser.parse(empty())).isEmpty();
        assertThat(productParser.parse(of("agsdfgsfgwqectqwxqeqw"))).isEmpty();
        assertThat(productParser.parse(of(""))).isEmpty();
    }

    @Test
    public void parseBookProduct() throws Exception {
        assertThat(productParser.parse(of("1 book at 10.10")).get())
                .isEqualTo(new Product(new BigDecimal("10.10"), "book", book));
    }


    @Test
    public void parseFoodProduct() throws Exception {
        assertThat(productParser.parse(of("1 box of chocolates at 10.10")).get())
                .isEqualTo(new Product(new BigDecimal("10.10"), "box of chocolates", food));
    }

    @Test
    public void parseImportedProduct() throws Exception {
        assertThat(productParser.parse(of("1 box of imported chocolates at 10.10")).get())
                .isEqualTo(new Product(new BigDecimal("10.10"), "box of imported chocolates", food, true));
    }
}