package test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.User;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

//Grouping Assertions
@DisplayName("Group multiple assertions")
class UserTest {
    private static final String user_name = "user1";
    private static final String entity = "private";
    private static final String password = "Password";
    private static final int phone_number = 1234567890;

    private User user;

    @BeforeEach
    void createUser(){
        user = new User();
        user.setUser_name(user_name);
        user.setEntity(entity);
        user.setPassword(password);
        user.setPhone_number(phone_number);

    }

    @Test
    @DisplayName("Can display the correct inputs")
    void ShouldHaveCorrectInput(){
        assertAll("userRegi",
                ()-assertEquals("user1", user.getUser_name()),
                ()-assertEquals("private",  user.getEntity(),
                ()-assertEquals("Password", user.getPassword()),
                ()-assertEquals("1234567890", user.getPhone_number()
            );

    }
    

}