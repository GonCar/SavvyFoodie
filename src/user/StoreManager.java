package user;

public class StoreManager extends User {

    public StoreManager(int user_ID, String user_name) {
        super(user_ID, user_name);

        entity category = entity.store_manager;
    }
}
