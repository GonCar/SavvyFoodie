package sample;


public class Products {
    String product_name;
    String category;
    String description;
    int product_id;
    int product_weight;
    int price;
    long expiry_date;
    String owner_contact;

    public Products(String product_name, String category, int product_weight, int price , long expiry_date, String owner_contact) {

        this.product_name = product_name;
        this.category = category;
        this.product_weight = product_weight;
        this.price = price;
        this.expiry_date = expiry_date;
        this.owner_contact = owner_contact;
    }


    //setters
    public String getProduct_name() { return product_name; }
    public void setProduct_name(String product_name) { this.product_name = product_name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public void setOwner_contact(String owner_contact) { this.owner_contact = owner_contact; }

    // getters
    public int getProduct_id() { return product_id; }
    public void setProduct_id(int product_id) { this.product_id = product_id; }
    public int getProduct_weight() { return product_weight; }
    public void setProduct_weight(int product_weight) { this.product_weight = product_weight; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public long getExpiry_date() { return expiry_date; }
    public void setExpiry_date(long expiry_date) { this.expiry_date = expiry_date; }
    public String getOwner_contact() { return owner_contact; }
}
