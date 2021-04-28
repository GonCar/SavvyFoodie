package test;


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
        User users = new User("user1", "", "", 0);
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

    }*/


    @Test
    void getUser_name() {
        User c = new User("My", "", "", 0);
        assertEquals("My", c.getUser_name());
    }


    @Test
    void getEntity() {
        User c = new User("", "private", "", 0);
        assertEquals("private", c.getEntity(),"Error Entity Inputs"); //how about "business" in Entity?
    }

    @Test
    void getPassword() {
        User c = new User("", "", "abc123", 0);
        assertEquals("abc123", c.getPassword());
    }

    @Test
    void getPhone_number() {
        User c = new User("", "", "", 0);
        assertEquals(0, c.getPhone_number());
    }

}