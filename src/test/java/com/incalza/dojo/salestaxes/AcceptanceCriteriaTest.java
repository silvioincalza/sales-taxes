package com.incalza.dojo.salestaxes;

import com.incalza.dojo.salestaxes.domain.*;
import org.junit.Test;

import java.math.BigDecimal;

import static com.incalza.dojo.salestaxes.domain.Product.Type.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sincalza on 26/05/2017.
 */
public class AcceptanceCriteriaTest {

    @Test
    public void firstTestCase() throws Exception {
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

        Product product1 = new Product(new BigDecimal("12.49"), "book", book);
        Product product2 = new Product(new BigDecimal("14.99"), "music CD", music);
        Product product3 = new Product(new BigDecimal("0.85"), "chocolate bar", food);
        Basket basket = new Basket(new OrderFactory(new BasicTaxCalculator()));
        basket.put(product1, product2, product3);

        assertThat(basket.checkOut().salesTaxes())
                .withFailMessage("salesTaxes not matched")
                .isEqualTo(new BigDecimal("1.50"));

        assertThat(basket.checkOut().total())
                .withFailMessage("total not matched")
                .isEqualTo(new BigDecimal("29.83"));

    }

    @Test
    public void secondTestCase() throws Exception {
//        Input 2:
//        1 imported box of chocolates at 10.00
//        1 imported bottle of perfume at 47.50


//        Output 2:
//        1 imported box of chocolates: 10.50
//        1 imported bottle of perfume: 54.65
//        Sales Taxes: 7.65
//        Total: 65.15

        Product product1 = new Product(new BigDecimal("10.00"), "box of chocolates", food, true);
        Product product2 = new Product(new BigDecimal("47.50"), "bottle of perfume", perfume, true);

        final ChainOfTaxCalculator salesTaxes = new ChainOfTaxCalculator(new BasicTaxCalculator(), new ImportTaxCalculator());
        Basket basket = new Basket(new OrderFactory(salesTaxes)).put(product1, product2);

        assertThat(basket.checkOut().salesTaxes())
                .withFailMessage("salesTaxes not matched")
                .isEqualTo(new BigDecimal("7.65"));

        assertThat(basket.checkOut().total())
                .withFailMessage("total not matched")
                .isEqualTo(new BigDecimal("65.15"));

    }

    @Test
    public void thirdTestCase() throws Exception {
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

        Product product1 = new Product(new BigDecimal("27.99"), "imported bottle of perfume", perfume, true);
        Product product2 = new Product(new BigDecimal("18.99"), "bottle of perfume", perfume, false);
        Product product3 = new Product(new BigDecimal("9.75"), "packet of headache pills", medicinal, false);
        Product product4 = new Product(new BigDecimal("11.25"), "box of imported chocolates", food, true);

        final ChainOfTaxCalculator salesTaxes = new ChainOfTaxCalculator(new BasicTaxCalculator(), new ImportTaxCalculator());
        Basket basket = new Basket(new OrderFactory(salesTaxes));

        basket.put(product1, product2, product3, product4);

        assertThat(basket.checkOut().salesTaxes())
                .withFailMessage("salesTaxes not matched")
                .isEqualTo(new BigDecimal("6.70"));

        assertThat(basket.checkOut().total())
                .withFailMessage("total not matched")
                .isEqualTo(new BigDecimal("74.68"));
    }


}
