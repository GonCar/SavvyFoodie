package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getUser_name() {
        User c = new User("My", "", "", 0);
        assertEquals("My", c.getUser_name());
    }
}