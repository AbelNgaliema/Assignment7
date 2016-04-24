package Factories;

import java.util.Map;

import Domain.Book;
import Domain.Buy;
import Domain.Customer;

/**
 * Created by AbelN on 15/04/2016.
 */
public class BuyFactory {

    public static Buy createBuy(long id, Map<String,String> value, Book book, Customer customer)
    {
        return new Buy.Builder().cashier(value.get("cashier")).mode(value.get("mode")).book(book).customer(customer).build();
    }
}
