package Product;

import java.sql.Date;

public class Product {

    private int product_id;
    private double product_price;
    private String[] categories;
    private Date expiry_date;

    public Product(int product_id, double product_price, String[] categories, Date expiry_date) {
        this.product_id = product_id;
        this.product_price = product_price;
        this.categories = categories;
        this.expiry_date = expiry_date;
    }

    public double getProduct_price() {
        return product_price;
    }

    public String[] getCategories() {
        return categories;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }
}
