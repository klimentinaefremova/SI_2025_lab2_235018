import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class SILab2Test {

    @Test(expected = RuntimeException.class)
    public void test_nullItemList_throwsException() {
        SILab2.checkCart(null, "0123456789012345");
    }

    @Test(expected = RuntimeException.class)
    public void test_itemWithNullName_throwsException() {
        List<Item> items = Arrays.asList(new Item("", 1, 111, 0.0));
        SILab2.checkCart(items, "1234567890123456");
    }

    @Test(expected = RuntimeException.class)
    public void test_itemWithEmptyName_throwsException() {
        List<Item> items = Arrays.asList(new Item(null, 3, 70, 0.1));
        SILab2.checkCart(items, "1234567890123456");
    }

    @Test(expected = RuntimeException.class)
    public void test_invalidCardNumber_short() {
        List<Item> items = Arrays.asList(new Item("Domati", 4, 45, 0.0));
        SILab2.checkCart(items, "1232344398");
    }

    @Test(expected = RuntimeException.class)
    public void test_invalidCardNumber_nonDigit() {
        List<Item> items = Arrays.asList(new Item("Zejtin", 10, 90, -0.3));
        SILab2.checkCart(items, "12345678901234AB");
    }

    @Test
    public void test_itemWithDiscount() {
        List<Item> items = Arrays.asList(new Item("Sladoled", 2, 200, 0.1)); 
        double expected = -30 + 2 * 200 * 0.9; 
        double actual = SILab2.checkCart(items, "1234567890123456");
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void test_itemWithHighPriceNoDiscount() {
        List<Item> items = Arrays.asList(new Item("Ajvar", 1, 400, 0.0));
        double expected = -30 + 400; 
        double actual = SILab2.checkCart(items, "0123456789123456");
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void test_multipleItems_mixed() {
        List<Item> items = Arrays.asList(
                new Item("Kromid", 12, 50, 0),         // -30 + 12 * 50 = 570
                new Item("Sok", 3, 350, 0.2),   //  -30 + 350*0.8*3 = 810
                new Item("Leb", 20, 20, 0)           // -30 + 20 * 20 = 370
        );
        double expected = -90 + 600 + 840 + 400;
        double actual = SILab2.checkCart(items, "01234567890123456");
        assertEquals(expected, actual, 0.01);
    }
}
