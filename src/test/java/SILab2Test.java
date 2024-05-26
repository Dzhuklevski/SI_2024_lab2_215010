import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void everyBranchTest() {
        RuntimeException e;
        ArrayList<Item> list = new ArrayList<>();

        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(e.getMessage().contains("allItems list can't be null!"));

        list.add(new Item("Item 1", null, 10, 0));
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list, 0));
        assertTrue(e.getMessage().contains("No barcode!"));

        list.clear();
        list.add(new Item("Item 1", "0123", 50, 0));
        list.add(new Item("Item 2", "0451", 150, 0));
        list.add(new Item("Item 3", "ABCD", 500, 0.5f));
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(list, 0));
        assertTrue(e.getMessage().contains("Invalid character in item barcode!"));

        list.clear();
        list.add(new Item("Item 1", "0123", 120, 0));
        list.add(new Item("Item 2", "9142", 100, 0));
        list.add(new Item("Item 3", "7909", 300, 0));

        assertTrue(SILab2.checkCart(list, 1000));

        assertFalse(SILab2.checkCart(list, 200));
    }

    @Test
    public void multipleConditionTest() {
        ArrayList<Item> list = new ArrayList<>();


        list.add(new Item("Item", "0451", 500, 0.1f));
        assertTrue(SILab2.checkCart(list, 1000));

        list.clear();
        list.add(new Item("Item", "1234", 350, 0.25f));
        assertTrue(SILab2.checkCart(list, 1000));

        list.clear();
        list.add(new Item("Item", "1234", 900, 0));
        assertTrue(SILab2.checkCart(list, 1000));

        list.clear();
        list.add(new Item("Item", "9152", 100, 0.5f));
        assertTrue(SILab2.checkCart(list, 1000));
    }
}