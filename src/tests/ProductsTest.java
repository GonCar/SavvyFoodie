package tests;
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
        System.out.println("Product Name test passed");
        Products test = new Products("apple", "", true, true, 0, 120);
        Assertions.assertEquals( "apple", test.getProduct_name());

    }

    @Test
    void getProduct_name(){
        System.out.println("Product Category test passed");
        Products test = new Products("", "Fruit", true, true, 0, 120);
        Assertions.assertEquals("Fruit", test.getCategory());
    }


   @Test
    void is_veggieTrue(){
       System.out.println("Boolean Veggie('true/false') test passed");
        Products test = new Products("apple", "", true, true, 0, 120);
        Assertions.assertEquals( true, test.isIs_veggie());
    }

    @Test
    void getBoolean_isGlutenFree(){
        System.out.println("Boolean Gluten Free('true/false') test passed");
        Products test = new Products("apple", "", true, false, 0, 120);
        Assertions.assertEquals( false, test.isIs_gluten_free());
    }

    @Test
    void getProduct_weight(){
        System.out.println("Product Weight test passed");
        Products test = new Products("apple", "", true, true, 99, 120);
        Assertions.assertEquals(99, test.getProduct_weight());
    }




}


