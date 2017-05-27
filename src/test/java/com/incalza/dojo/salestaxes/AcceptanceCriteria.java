package com.incalza.dojo.salestaxes;

import org.junit.Test;
import salestaxes.*;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static salestaxes.ProductType.*;

/**
 * Created by sincalza on 26/05/2017.
 */
public class AcceptanceCriteria {

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

        Product product1 = new Product("book", new BigDecimal("12.49"), book);
        Product product2 = new Product("music CD", new BigDecimal("14.99"), music);
        Product product3 = new Product("chocolate bar", new BigDecimal("0.85"), food);
        Basket basket = new Basket(new BasicTaxCalculator());
        basket.put(product1, product2, product3);

        assertThat(basket.salesTaxes())
                .withFailMessage("salesTaxes not matched")
                .isEqualTo(new BigDecimal("1.50"));

        assertThat(basket.total())
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

        Product product1 = new Product("box of chocolates", new BigDecimal("10.00"), food, true);
        Product product2 = new Product("bottle of perfume", new BigDecimal("47.50"), perfume, true);

        final ChainOfTaxCalculator salesTaxes = new ChainOfTaxCalculator(new BasicTaxCalculator(), new ImportTaxCalculator());
        Basket basket = new Basket(salesTaxes).put(product1, product2);

//        assertThat(product1.getPriceWithSalesTaxes(salesTaxes))
//                .isEqualTo(new BigDecimal("10.50"));
//
//        assertThat(product2.getPriceWithSalesTaxes(salesTaxes))
//                .isEqualTo(new BigDecimal("54.65"));

        assertThat(basket.salesTaxes())
                .withFailMessage("salesTaxes not matched")
                .isEqualTo(new BigDecimal("7.65"));

        assertThat(basket.total())
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

        Product product1 = new Product("imported bottle of perfume", new BigDecimal("27.99"), perfume, true);
        Product product2 = new Product("bottle of perfume", new BigDecimal("18.99"), perfume, false);
        Product product3 = new Product("packet of headache pills", new BigDecimal("9.75"), medicinal, false);
        Product product4 = new Product("box of imported chocolates", new BigDecimal("11.25"), food, true);

        final ChainOfTaxCalculator salesTaxes = new ChainOfTaxCalculator(new BasicTaxCalculator(), new ImportTaxCalculator());
        Basket basket = new Basket(salesTaxes);

        basket.put(product1, product2, product3, product4);

//        assertThat(product1.getPriceWithSalesTaxes(salesTaxes))
//                .isEqualTo(new BigDecimal("32.19"));

//        assertThat(product2.getPriceWithSalesTaxes(salesTaxes))
//                .isEqualTo(new BigDecimal("20.89"));

//        assertThat(product3.getPriceWithSalesTaxes(salesTaxes))
//                .isEqualTo(new BigDecimal("9.75"));

//        assertThat(product4.getPriceWithSalesTaxes(salesTaxes))
//                .isEqualTo(new BigDecimal("11.85"));


        assertThat(basket.salesTaxes())
                .withFailMessage("salesTaxes not matched")
                .isEqualTo(new BigDecimal("6.70"));

        assertThat(basket.total())
                .withFailMessage("total not matched")
                .isEqualTo(new BigDecimal("74.68"));
    }


}
