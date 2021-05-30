package sample;

public class User {
    String user_name;
    String entity;
    String password;
    int phone_number;

    /** Constructor */
    public User(String user_name, String entity, String password, int phone_number){
        this.user_name = user_name;
        this.entity = entity;
        this.password = password;
        this.phone_number = phone_number;
    }

    /** Getters */
    public String getUser_name() { return user_name; }
    public String getEntity() { return entity; }
    public String getPassword() { return password; }
    public int getPhone_number() { return phone_number; }

}
