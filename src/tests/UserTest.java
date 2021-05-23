package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.User;


import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Group multiple assertions")
class UserTest {


    @Test
    @DisplayName("Group multiple testing")
    void User(){
        System.out.println("User Name, Entity, Password, Phone Number passed test!");
        User users = new User("user1","private", "Password", 0);
        assertAll("UserRegistration",
                ()-> assertEquals("user1", users.getUser_name()),
                ()-> assertEquals("private", users.getEntity()),
                ()-> assertEquals("Password", users.getPassword()),
                ()-> assertEquals(0, users.getPhone_number())

        );
    }


}