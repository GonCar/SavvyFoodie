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
        Products test = new Products("apple", "", 0, 0, 120);
        Assertions.assertEquals( "apple", test.getProduct_name());
    }

    @Test
    void getProduct_name(){
        System.out.println("Product Category test passed");
        Products test = new Products("", "Fruit", 0, 0, 120);
        Assertions.assertEquals("Fruit", test.getCategory());
    }

    @Test
    void getProduct_weight(){
        System.out.println("Product Weight test passed");
        Products test = new Products("apple", "", 99,0, 120);
        Assertions.assertEquals(99, test.getProduct_weight());
    }

}


