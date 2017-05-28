package com.incalza.dojo.salestaxes.domain;

import java.io.Reader;
import java.util.Optional;
import java.util.Scanner;

import static com.incalza.dojo.salestaxes.domain.RegExpProductParser.REG_EXP_PRODUCT_LINE;
import static java.util.Optional.ofNullable;

/**
 * Created by sincalza on 27/05/2017.
 */
public class TextualProductReader implements ProductReader {

    private final Scanner scanner;
    private final RegExpProductParser regExpProductParser = new RegExpProductParser();

    public TextualProductReader(Reader reader) {
        this.scanner = new Scanner(reader);
    }

    @Override
    public Optional<Product> read() {
        return regExpProductParser.parse(
                ofNullable(
                        scanner.findInLine(REG_EXP_PRODUCT_LINE)
                )
        );
    }
}
