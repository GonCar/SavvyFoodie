package user;

import Product.Product;

import java.util.ArrayList;

public class Customer extends User{

    private ArrayList<Product> shopping_cart;

    public Customer(int user_ID, String user_name) {
        super(user_ID, user_name);

        shopping_cart = new ArrayList<>();
    }
    entity category = entity.customer;
}
