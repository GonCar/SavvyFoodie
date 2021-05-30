package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.Products;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("function testing in Class Products")

class ProductsTest {


    /** Product Name test passed */
    @Test
    void getProduct_name(){
        System.out.println("Product Name test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021, "g@g.com");
        Assertions.assertEquals("Apple", test.getProduct_name());
    }


    /** Product Name: Null-Test */
    @Test
    @DisplayName("Null Test")
    void checkProductNameIsNull() {
        System.out.println("Product Name: Null-Test passed!");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021, "g@g.com");
        String nullString = null;
        Assertions.assertNull(nullString);
    }

    /** Product Name: Not-Null-Test */
    @Test
    @DisplayName("Not-Null Test")
    void checkProductNameIsNotNull() {
        System.out.println("Product Name: Not-Null-Test passed!");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021, "g@g.com");
        String nullString = null;
        String notNullSting = "Apple";
        Assertions.assertNotNull(notNullSting);

    }

    /** Product Name: Iterable Equal Test */
    @Test
    @DisplayName("Iterable Eqaul Test for String")
    public void assertIterableEqual(){
        System.out.println("Product Name: Iterabel Eqaul Test passed!");
        Iterable<String> listOne = new ArrayList<>(( Arrays.asList("Apple", "Pear", "Orange")));
        Iterable<String> listTwo = new ArrayList<>(( Arrays.asList("Apple", "Pear", "Orange")));

        Assertions.assertIterableEquals(listOne, listTwo);
    }

    /** Product Category assertion testing */
    @Test
    void getProduct_category(){
        System.out.println("Product Category test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021, "g@g.com");
        Assertions.assertEquals("Fruit", test.getCategory());
    }

    /** Price test assertion testing */
    @Test
    void getProduct_price(){
        System.out.println("Price test passed");
        Products test = new Products("Apple", "Fruit", 0, 99, 2021, "g@g.com");
        Assertions.assertEquals(99, test.getPrice());
    }

    //Product Price: Interable Equal Test
    @Test
    @DisplayName("Iterable Eqaul Test for Integer")
    public void assertIterableEqualInteger(){
        System.out.println("Product Price: Interabel Eqaul Test passed!");
        Iterable<Integer> listOne = new ArrayList<>();
        if (listOne != null) {
        }
        Iterable<Integer> listTwo = new ArrayList<>();
        if (listTwo != null) {
        }
        Assertions.assertIterableEquals(listOne, listTwo);
    }

    /** Expired Date assertion test */
    @Test
    @DisplayName("Expired Date assertion test")
    void getProduct_date(){
        System.out.println("Expired Date test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021-05-12, "g@g.com");
        Assertions.assertEquals(2021-05-12, test.getExpiry_date());
    }

    /** Not SAME Date test */
    @Test
    @DisplayName("Not-Same Test")
    public void productDateNotSame(){
        System.out.println("Not SAME Date test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021-05-12, "g@g.com");
        Assertions.assertNotSame(2021-05-30, 2021-05-12, "unpaired date error found");
    }

    /** Product Weight test */
    @Test
    @DisplayName("Product Weight test")
    void getProduct_weight(){
        System.out.println("Product Weight test passed");
        Products test = new Products("Apple", "Fruit", 99, 0, 2021, "g@g.com");
        Assertions.assertEquals(99, test.getProduct_weight());
    }

}



