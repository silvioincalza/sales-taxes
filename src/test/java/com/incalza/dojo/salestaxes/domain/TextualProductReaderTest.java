package com.incalza.dojo.salestaxes.domain;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import static java.nio.charset.Charset.forName;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by sincalza on 27/05/2017.
 */
public class TextualProductReaderTest {

    public static final String DESCRIPTION_EXPECTED = "test";
    public static final String UTF_8 = "UTF-8";

    @Test
    public void read() throws Exception {
        final String expectedProductLine = "1 book at 12.49";
        final String input = expectedProductLine + "\nfngjlsdf;sn;gfknsjkdnf;aks\ngl;aksfl;gajld;" +
                "jqcw[oirugn[oqmrpoghuqcefqw[gwrjmg,qe[cj,rei[ogcjeh ]og2t3umt40gr2cgipw;lj'mp3qew[oipxqrlmgj2]" +
                "premogdj,ilqrxct1mhqcwpiual.,klnxem1pfilna;HMCEFNQULH  Andl;c  jfmq[herxoinl;cke   f;/";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(forName(UTF_8)));
        ProductReader productReader = new TextualProductReader(new InputStreamReader(inputStream, UTF_8));
        assertThat(productReader.read().get())
                .isNotNull()
                .isEqualTo(new Product(new BigDecimal("12.49"), "book", Product.Type.not_available));
        assertThat(productReader.read()).isEmpty();
    }


}