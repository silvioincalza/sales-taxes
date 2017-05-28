package com.incalza.dojo.salestaxes.domain;

import java.io.Reader;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.lineSeparator;
import static java.util.Optional.ofNullable;

/**
 * Created by sincalza on 27/05/2017.
 */
public class TextualProductReader implements ProductReader {

    private final Scanner scanner;
    private final ProductParser<String> productParser;

    public TextualProductReader(Reader reader) {
        this(reader, new RegExpTextualProductParser());
    }

    public TextualProductReader(Reader reader, ProductParser<String> productParser) {
        this.scanner = new Scanner(reader).useDelimiter(lineSeparator());
        this.productParser = productParser;
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }

    @Override
    public Optional<Product> read() {
        try {
            return productParser.parse(ofNullable(scanner.next()));
        } catch (NoSuchElementException e) {
            throw new NoSuchProductException(e.getMessage());
        }
    }
}
