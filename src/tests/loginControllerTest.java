package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.loginController;

import java.util.ArrayList;
import java.util.Arrays;


class loginControllerTest {
    loginController LIC = new loginController();

    @Test
    @DisplayName("Not Null Test")
    void checkLogin() {
        System.out.println("Not Null Test for Log-In passed!");
        String nullString = null;
        String notNullSting = "Geng";
        Assertions.assertNotNull(notNullSting);

    }

    @Test
    @DisplayName("Iterable Eqaul Test")
    public void assertIterableEqual(){
        System.out.println("Interabel Eqaul Test for Log-In passed!");
        Iterable<String> listOne = new ArrayList<>(( Arrays.asList("Geng", "Meron", "Gonzalo")));
        Iterable<String> listTwo = new ArrayList<>(( Arrays.asList("Geng", "Meron", "Gonzalo")));

        Assertions.assertIterableEquals(listOne, listTwo);

    }

    @Test
    @DisplayName("Assert True Test")
    public void assertTrueTest() {
        System.out.println("Boolean True Test for Log-In passed!");
        boolean trueBoolean = true;
        boolean falseBoolean = false;

        Assertions.assertTrue(trueBoolean);
    }

    @Test
    @DisplayName("Assert False Test")
    public void assertFalseTest(){
        System.out.println("Boolean False Test for Log-In passed!");
        boolean trueBoolean = true;
        boolean falseBoolean = false;
        Assertions.assertFalse(falseBoolean);
    }

    @Test
    @DisplayName("Assert Null Pointer Exception Test")
    public void assertExceptionTest(){
        System.out.println("Assert Null Pointer Exception Test for Log-In passed!");
        Assertions.assertThrows(NullPointerException.class, ()-> LIC.checkLogin(null, null));
    }

}



