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
        List<Item> items = Arrays.asList(new Item(null, 3, 270, 0.0));
        SILab2.checkCart(items, "0123456789123456");
    }

    @Test(expected = RuntimeException.class)
    public void test_itemWithEmptyName_throwsException() {
        List<Item> items = Arrays.asList(new Item("", 1, 111, 0.2));
        SILab2.checkCart(items, "1234567890123456");
    }

    @Test(expected = RuntimeException.class)
    public void test_invalidCardNumber_short() {
        List<Item> items = Arrays.asList(new Item("Sladoled", 3, 100, -0.5));
        SILab2.checkCart(items, "123456789");
    }

    @Test(expected = RuntimeException.class)
    public void test_invalidCardNumber_nonDigit() {
        List<Item> items = Arrays.asList(new Item("Kompiri", 10, 75, 0.1));
        SILab2.checkCart(items, "12345678901234AB");
    }

    @Test
    public void test_itemWithDiscount() {
        List<Item> items = Arrays.asList(new Item("Spageti", 3, 200, 0.1));
        double expected = -30 + 3 * 200 * 0.9;
        double actual = SILab2.checkCart(items, "0123456789101112");
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void test_itemWithHighPriceNoDiscount() {
        List<Item> items = Arrays.asList(new Item("Parfem", 1, 650, 0.0));
        double expected = -30 + 650;
        double actual = SILab2.checkCart(items, "6543210987654321");
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void test_multipleItems_mixed() {
        List<Item> items = Arrays.asList(
                new Item("Kromid", 12, 5, 0.0),         // -30 + 12 * 5 = 30
                new Item("Ajvar", 1, 500, 0.0),   // -30 + 500 = 470
                new Item("Lebcinja",8 , 5, 0.2)           //  -30 + 8 * 5 * 0.8 = 2
        );
        double expected = -90 + 60 + 500 + 32;
        double actual = SILab2.checkCart(items, "1615141312111098");
        assertEquals(expected, actual, 0.01);
    }
}
