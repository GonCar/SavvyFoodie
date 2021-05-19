
package tests;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.Products;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("function testing in Class Products")

class ProductsTest {
    //Products test = new Products("Apple", "Fruit", 0, 0, 2021);


    @Test
    void getProduct_name(){
        System.out.println("Product Name test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021, "g@g.com");
        Assertions.assertEquals("Apple", test.getProduct_name());
    }



    @Test
    @DisplayName("Null Test")
    void checkProductNameIsNull() {
        System.out.println("Product Name: Null-Test passed!");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021, "g@g.com");
        String nullString = null;
        Assertions.assertNull(nullString);


    }

    @Test
    @DisplayName("Not-Null Test")
    void checkProductNameIsNotNull() {
        System.out.println("Product Name: Not-Null-Test passed!");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021, "g@g.com");
        String nullString = null;
        String notNullSting = "Apple";
        Assertions.assertNotNull(notNullSting);

    }

    @Test
    @DisplayName("Iterable Eqaul Test for String")
    public void assertIterableEqual(){
        System.out.println("Product Name: Interabel Eqaul Test passed!");
        Iterable<String> listOne = new ArrayList<>(( Arrays.asList("Apple", "Pear", "Orange")));
        Iterable<String> listTwo = new ArrayList<>(( Arrays.asList("Apple", "Pear", "Orange")));

        Assertions.assertIterableEquals(listOne, listTwo);

    }


    @Test
    void getProduct_category(){
        System.out.println("Product Category test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021, "g@g.com");
        Assertions.assertEquals("Fruit", test.getCategory());
    }

    @Test
    void getProduct_price(){
        System.out.println("Price test passed");
        Products test = new Products("Apple", "Fruit", 0, 99, 2021, "g@g.com");
        Assertions.assertEquals(99, test.getPrice());
    }

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


    @Test
    void getProduct_date(){
        System.out.println("Expired Date test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021-05-12, "g@g.com");
        Assertions.assertEquals(2021-05-12, test.getExpiry_date());
    }

    @Test
    @DisplayName("Not-Same Test")
    public void productDateNotSame(){
        System.out.println("Not SAME Date test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021-05-12, "g@g.com");
        Assertions.assertNotSame(2021-05-30, 2021-05-12, "unpaired date error found");
    }






/*
   @Test
    void is_veggieTrue(){
       System.out.println("Boolean Veggie('true/false') test passed");
        Products test = new Products("apple", "", true, true, 2021);
        Assertions.assertEquals( true, test.isIs_veggie());
    }

 */
/*
    @Test
    void getBoolean_isGlutenFree(){
        System.out.println("Boolean Gluten Free('true/false') test passed");
        Products test = new Products("apple", "", true, false, 0, 120);
        Assertions.assertEquals( false, test.isIs_gluten_free());
    }

 */

    @Test
    void getProduct_weight(){
        System.out.println("Product Weight test passed");
        Products test = new Products("Apple", "Fruit", 99, 0, 2021, "g@g.com");
        Assertions.assertEquals(99, test.getProduct_weight());
    }

    /*@Test
    @DisplayName("Assert Fail Test Case")
    public void assertFailTest(){
        Assertions.fail("this test cases will fail every time");
    }

     */



}



