package test;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.Products;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("function testing in Class Products")

class ProductsTest {

    @Test
    void getProduct_id(){
        System.out.println("Product ID test passed");
        Products test = new Products("Test", "Test", false, false, 0, 0);
        Assertions.assertEquals( 0, test.getProduct_id());

    }

    @Test
    void getProduct_name(){
        System.out.println("Product Name test passed");
        Products test = new Products("apple", "Test", false, false, 0, 0);
        Assertions.assertEquals("apple", test.getProduct_name());
    }


   @Test
    void is_veggieTrue(){
       System.out.println("Boolean Veggie('true/false') test passed");
        Products test = new Products("Test", "Test", true, false, 0, 0);
        Assertions.assertEquals( true, test.isIs_veggie());
    }

    @Test
    void getBoolean_isGlutenFree(){
        System.out.println("Boolean Gluten Free('true/false') test passed");
        Products test = new Products("Test", "Test", false, true, 0, 0);
        Assertions.assertEquals( true, test.isIs_gluten_free());
    }

    @Test
    void getProduct_weight(){
        System.out.println("Product Weight test passed");
        Products test = new Products("Test", "Test", false, false, 55, 0);
        Assertions.assertEquals(55, test.getProduct_weight());
    }




}
