package sample;


public class Products {
    String product_name;
    String category;
    int product_weight;
    int price;
    long expiry_date;
    String owner_contact;

    /** Constructor */
    public Products(String product_name, String category, int product_weight, int price , long expiry_date, String owner_contact) {
        this.product_name = product_name;
        this.category = category;
        this.product_weight = product_weight;
        this.price = price;
        this.expiry_date = expiry_date;
        this.owner_contact = owner_contact;
    }

    /** Setters */
    public String getProduct_name() { return product_name; }
    public String getCategory() { return category; }

    /** Getters */
    public int getProduct_weight() { return product_weight; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public long getExpiry_date() { return expiry_date; }
    public String getOwner_contact() { return owner_contact; }
}
