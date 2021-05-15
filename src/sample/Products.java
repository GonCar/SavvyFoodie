package sample;

import java.util.Date;

public class Products {
    String product_name;
    String category;
    String description;
    boolean is_veggie;
    boolean is_gluten_free;
    int product_id;
    int product_weight;
    int price;
    Date expiry_date;

    public Products(int product_id,String product_name, String category, boolean is_veggie, boolean is_gluten_free, int product_weight, int price) {

        this.product_id = product_id;
        this.product_name = product_name;
        this.category = category;
        this.is_veggie = is_veggie;
        this.is_gluten_free = is_gluten_free;
        this.product_weight = product_weight;
        this.price = price;
        //this.expiry_date = expiry_date;
    }

    //setters
    public String getProduct_name() { return product_name; }
    public void setProduct_name(String product_name) { this.product_name = product_name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isIs_veggie() { return is_veggie; }
    public void setIs_veggie(boolean is_veggie) { this.is_veggie = is_veggie; }
    public boolean isIs_gluten_free() { return is_gluten_free; }
    public void setIs_gluten_free(boolean is_gluten_free) { this.is_gluten_free = is_gluten_free; }
    // getters
    public int getProduct_id() { return product_id; }
    public void setProduct_id(int product_id) { this.product_id = product_id; }
    public int getProduct_weight() { return product_weight; }
    public void setProduct_weight(int product_weight) { this.product_weight = product_weight; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public Date getExpiry_date() { return expiry_date; }
    public void setExpiry_date(Date expiry_date) { this.expiry_date = expiry_date; }
}
