package com.incalza.dojo.salestaxes.domain;

import java.io.Reader;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;
import static java.util.regex.Pattern.compile;

/**
 * Created by sincalza on 27/05/2017.
 */
public class TextualProductReader implements ProductReader {

    private final Scanner scanner;
    private static final Pattern PRODUCT_LINE = compile("((\\d)+[\\s\\t]([a-zA-Z])+[\\s\\t]at[\\s\\t](\\d{1,11}(.\\d{1,2})?))");
    private final RegExpProductParser regExpProductParser = new RegExpProductParser();

    public TextualProductReader(Reader reader) {
        this.scanner = new Scanner(reader);
    }

    @Override
    public Optional<Product> read() {
        return regExpProductParser.parse(
                ofNullable(
                        scanner.findInLine(PRODUCT_LINE)
                )
        );
    }
}
