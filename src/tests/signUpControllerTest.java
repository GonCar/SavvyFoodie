package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.signUpController;

import java.util.ArrayList;
import java.util.Arrays;


class signUpControllerTest {

    signUpController SUC = new signUpController();

    /** Not Null Test */
    @Test
    @DisplayName("Not Null Test")
    void checkUserExists(){
        System.out.println("Not Null Test for Sign-Up passed!");
        String nullString = null;
        String notNullSting = "Geng";
        Assertions.assertNotNull(notNullSting);

    }

    /**Iterable Equal Test */
    @Test
    @DisplayName("Iterable Equal Test")
    public void assertIterableEqual(){
        System.out.println("Interabel Eqaul Test for Sign Up passed!");
        Iterable<String> listOne = new ArrayList<>(( Arrays.asList("Geng", "Meron", "Gonzalo")));
        Iterable<String> listTwo = new ArrayList<>(( Arrays.asList("Geng", "Meron", "Gonzalo")));

        Assertions.assertIterableEquals(listOne, listTwo);

    }

}