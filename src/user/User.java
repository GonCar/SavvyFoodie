package user;

public abstract class User {
    private int user_ID;
    private String user_name;
    protected enum entity{store_manager,
                        customer}

    public User(int user_ID, String user_name) {
        this.user_ID = user_ID;
        this.user_name = user_name;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public String getUser_name() {
        return user_name;
    }
}
