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
        System.out.println("Product ID test passed");
        Products test = new Products("Test", "Test", 0, 0, 0);
        Assertions.assertEquals( 0, test.getProduct_id());

    }

    @Test
    void getProduct_name(){
        System.out.println("Product Name test passed");
        Products test = new Products("apple", "Test", 0, 0, 0);
        Assertions.assertEquals("apple", test.getProduct_name());
    }

    @Test
    void getProduct_weight(){
        System.out.println("Product Weight test passed");
        Products test = new Products("Test", "Test", 55, 0, 0);
        Assertions.assertEquals(55, test.getProduct_weight());
    }

}
