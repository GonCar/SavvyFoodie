package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.User;

//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

//Grouping Assertions by Geng
@DisplayName("Group multiple assertions")
class UserTest {
    /*private User users;
    //private static final String user_name = "user1";
    //private static final String entity = "private";
    //private static final String password = "Password";
    //private static final int phone_number = 1234567890;

    //private User user;

    @BeforeEach
    void createUser(){
        //User users = new User("user1", "", "", 0);
        //user.setUser_name("user1");
        //user.setEntity(entity);
        //user.setPassword(password);
        //user.setPhone_number(phone_number);

    }

    @Test
    @DisplayName("Can display the correct inputs")
    void ShouldHaveCorrectInput(){
        assertAll("name",
                ()-> assertEquals("user1", users.getUser_name(), "input error")
                //()-> assertEquals("private", user.getEntity()),
                //()-> assertEquals("Password", user.getPassword()),
                //()-> assertEquals("1234567890", user.getPhone_number())
                );

    }

     */


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