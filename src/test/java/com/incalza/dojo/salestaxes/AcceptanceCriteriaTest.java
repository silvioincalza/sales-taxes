package com.incalza.dojo.salestaxes;

import com.incalza.dojo.salestaxes.domain.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sincalza on 26/05/2017.
 */
public class AcceptanceCriteriaTest {

    private final OrderFactory orderFactory = new OrderFactory(
            new ChainOfTaxCalculator(
                    new BasicTaxCalculator(),
                    new ImportationTaxCalculator()
            )
    );

//        Input 1:
//        1 book at 12.49
//        1 music CD at 14.99
//        1 chocolate bar at 0.85
//
//        Output 1:
//        1 book : 12.49
//        1 music CD: 16.49
//        1 chocolate bar: 0.85
//        Sales Taxes: 1.50
//        Total: 29.83

    @Test
    public void firstTestCase() throws Exception {

//        Product product1 = new Product(new BigDecimal("12.49"), "book", book);
//        Product product2 = new Product(new BigDecimal("14.99"), "music CD", music);
//        Product product3 = new Product(new BigDecimal("0.85"), "chocolate bar", food);


        Basket basket = new Basket(orderFactory);

//        basket.put(product1, product2, product3);

        ProductReader reader = new TextualProductReader(getInput("/input1.txt"));

        while (reader.hasNext()) {
            reader.read().ifPresent(basket::put);
        }

        assertThat(basket.checkOut().salesTaxes())
                .withFailMessage("salesTaxes not matched")
                .isEqualTo(new BigDecimal("1.50"));

        assertThat(basket.checkOut().total())
                .withFailMessage("total not matched")
                .isEqualTo(new BigDecimal("29.83"));

    }

//        Input 2:
//        1 imported box of chocolates at 10.00
//        1 imported bottle of perfume at 47.50


//        Output 2:
//        1 imported box of chocolates: 10.50
//        1 imported bottle of perfume: 54.65
//        Sales Taxes: 7.65
//        Total: 65.15
    @Test
    public void secondTestCase() throws Exception {

//        Product product1 = new Product(new BigDecimal("10.00"), "box of chocolates", food, true);
//        Product product2 = new Product(new BigDecimal("47.50"), "bottle of perfume", perfume, true);

        Basket basket = new Basket(orderFactory);//.put(product1, product2);

        ProductReader reader = new TextualProductReader(getInput("/input2.txt"));

        while (reader.hasNext()) {
            reader.read().ifPresent(basket::put);
        }

        assertThat(basket.checkOut().salesTaxes())
                .isEqualTo(new BigDecimal("7.65"));

        assertThat(basket.checkOut().total())
                .isEqualTo(new BigDecimal("65.15"));

    }

//        Input 3:
//        1 imported bottle of perfume at 27.99
//        1 bottle of perfume at 18.99
//        1 packet of headache pills at 9.75
//        1 box of imported chocolates at 11.25

//        Output 3:
//        1 imported bottle of perfume: 32.19
//        1 bottle of perfume: 20.89
//        1 packet of headache pills: 9.75
//        1 imported box of chocolates: 11.85
//        Sales Taxes: 6.70
//        Total: 74.68
    @Test
    public void thirdTestCase() throws Exception {

//        Product product1 = new Product("imported bottle of perfume", perfume, true, new BigDecimal("27.99"));
//        Product product2 = new Product("bottle of perfume", perfume, false, new BigDecimal("18.99"));
//        Product product3 = new Product("packet of headache pills", medicinal, false, new BigDecimal("9.75"));
//        Product product4 = new Product("box of imported chocolates", food, true, new BigDecimal("11.25"));

        Basket basket = new Basket(orderFactory); //.put(product1, product2, product3, product4);

        ProductReader reader = new TextualProductReader(getInput("/input3.txt"));

        while (reader.hasNext()) {
            reader.read().ifPresent(basket::put);
        }

        assertThat(basket.checkOut().salesTaxes())
                .withFailMessage("salesTaxes not matched")
                .isEqualTo(new BigDecimal("6.70"));

        assertThat(basket.checkOut().total())
                .withFailMessage("total not matched")
                .isEqualTo(new BigDecimal("74.68"));
    }


    private FileReader getInput(String name) throws FileNotFoundException {
        return new FileReader(AcceptanceCriteriaTest.class.getResource(name).getFile());
    }

}
