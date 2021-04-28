package sample;

public class User {
    String user_name;
    String entity;
    String city;
    String country;
    String email;
    String password;
    int user_id;
    int phone_number;
    double account_amount = 0;
    double rating = 0;


    public User(String user_name, String entity, String password, int phone_number){
        this.user_name = user_name;
        this.entity = entity;
        this.password = password;
        this.phone_number = phone_number;
    }

    // getters
    public String getUser_name() { return user_name; }
    public String getEntity() { return entity; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getUser_id() { return user_id; }
    public int getPhone_number() { return phone_number; }
    public double getAccount_amount() { return account_amount; }
    public double getRating() { return rating; }

    // setters
    public void setUser_name(String user_name) { this.user_name = user_name; }
    public void setEntity(String entity) { this.entity = entity; }
    public void setCity(String city) { this.city = city; }
    public void setCountry(String country) { this.country = country; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setUser_id(int user_id) { this.user_id = user_id; }
    public void setPhone_number(int phone_number) { this.phone_number = phone_number; }
    public void setAccount_amount(double account_amount) { this.account_amount = account_amount; }
    public void setRating(double rating) { this.rating = rating; }
}
