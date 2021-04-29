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
        //test boolean
    void getProduct_id(){

        Products test = new Products(0, "", "", true, true, 0, 0, "");
        Assertions.assertEquals( 0, test.getProduct_id());
    }

   @Test
    void is_veggieTrue(){

        Products test = new Products(0, "", "", true, true, 0, 0, "");
        Assertions.assertEquals( true, test.isIs_veggie());
    }

    @Test
    void getExpiry_date(){

        Products test = new Products(0, "", "", true, true, 0, 0, "");
        Assertions.assertEquals( true, test.isIs_veggie());
    }



}
