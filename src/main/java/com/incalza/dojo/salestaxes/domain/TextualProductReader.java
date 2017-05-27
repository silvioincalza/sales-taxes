package com.incalza.dojo.salestaxes.domain;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.CharBuffer;

/**
 * Created by sincalza on 27/05/2017.
 */
public class TextualProductReader implements ProductReader<String> {

    static final int BUFFERING_CAPACITY = 1024;
    private final InputStreamReader inputStreamReader;

    public TextualProductReader(InputStreamReader inputStreamReader) {
        this.inputStreamReader = inputStreamReader;
    }

    @Override
    public String read() {
        try {
            final StringWriter stringWriter = new StringWriter();
            final CharBuffer charBuffer = CharBuffer.allocate(BUFFERING_CAPACITY);
            int read;
            while ((read = inputStreamReader.read(charBuffer)) >= 0) {
                stringWriter.write(charBuffer.array(), 0, read);
                System.out.println(read);
            }
            System.out.println(stringWriter.getBuffer().toString());
            return stringWriter.getBuffer().toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("product not readied");
    }
}
