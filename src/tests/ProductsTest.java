package tests;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.Products;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.formatDate;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("function testing in Class Products")

class ProductsTest {
/*
    @Test
    void getProduct_id(){
        System.out.println("Product Name test passed");
        Products test = new Products("apple", "", 0, 0, 120);
        Assertions.assertEquals( "apple", test.getProduct_name());
    }

 */

    @Test
    void getProduct_name(){
        System.out.println("Product Name test passed");
        Products test = new Products("Apple", "Fruit", 0, 0, 2021);
        Assertions.assertEquals("Apple", test.getProduct_name());
    }



    @Test
    void getProduct_category(){
        System.out.println("Product Category test passed");
        Products test = new Products("", "Fruit", 0, 0, 2021);
        Assertions.assertEquals("Fruit", test.getCategory());
    }

    @Test
    void getProduct_price(){
        System.out.println("Price test passed");
        Products test = new Products("", "Fruit", 0, 99, 2021);
        Assertions.assertEquals(99, test.getPrice());
    }

    @Test
    void getProduct_date(){
        System.out.println("Expired Date test passed");
        Products test = new Products("", "Fruit", 0, 0, 2021-05-12);
        Assertions.assertEquals(2021-05-12, test.getExpiry_date());
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
        Products test = new Products("apple", "", 99,0, 120);
        Assertions.assertEquals(99, test.getProduct_weight());
    }

}


