package fi.tuni.prog3.junitorder;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    
    public OrderTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddItems1() {
        System.out.println("addItems1");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        boolean expResult = true;
        boolean result = order.addItems(item, 3);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testAddItems2() {
        System.out.println("addItems2");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        boolean expResult = false;
        boolean result = order.addItems(item, -1);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testAddItems3() {
        System.out.println("addItems3");
        Order order = new Order();
        Order.Item item1 = new Order.Item("Computer", 5.50);
        order.addItems(item1, 1);
        Order.Item item2 = new Order.Item("Computer", 5.00);
        boolean expResult = false;
        boolean result = order.addItems(item2, 1);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testAddItems4() {
        System.out.println("addItems4");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        order.addItem(item, 3);
        boolean expResult = true;
        boolean result = order.addItems("Computer", 3);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testAddItems5() {
        System.out.println("addItems5");
        Order order = new Order();
        boolean expResult = false;
        boolean result = order.addItems("Computer", 2);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testAddItems6() {
        System.out.println("addItems6");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        order.addItem(item, 3);
        boolean expResult = false;
        boolean result = order.addItems("Computer", -1);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testGetEntries() {
        System.out.println("getEntries");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        order.addItem(item, 3);
        Order.Entry entry = new Order.Entry(item, 3);
        List<Order.Entry> expResult = new ArrayList<>();
        expResult.add(entry);
        List<Order.Entry> result = order.getEntries();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testGetEntryCount() {
        System.out.println("getEntryCount");
        Order order = new Order();
        Order.Item item1 = new Order.Item("Computer", 5.50);
        order.addItem(item1, 3);
        Order.Item item2 = new Order.Item("Dildo", 9.99);
        order.addItem(item2, 2);
        int expResult = 2;
        int result = order.getEntryCount();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testGetItemCount() {
        System.out.println("getItemCount");
        Order order = new Order();
        Order.Item item1 = new Order.Item("Computer", 5.50);
        order.addItem(item1, 3);
        Order.Item item2 = new Order.Item("Dildo", 9.99);
        order.addItem(item2, 2);
        int expResult = 5;
        int result = order.getItemCount();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testGetTotalPrice() {
        System.out.println("getTotalPrice");
        Order order = new Order();
        Order.Item item1 = new Order.Item("Computer", 5.50);
        order.addItem(item1, 3);
        Order.Item item2 = new Order.Item("Dildo", 9.99);
        order.addItem(item2, 2);
        double expResult = 36.48;
        double result = order.getTotalPrice();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testIsEmpty1() {
        System.out.println("isEmpty1");
        Order order = new Order();
        boolean expResult = true;
        boolean result = order.isEmpty();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testIsEmpty2() {
        System.out.println("isEmpty2");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        order.addItem(item, 3);
        boolean expResult = false;
        boolean result = order.isEmpty();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testRemoveItems1() {
        System.out.println("removeItems1");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        order.addItem(item, 3);
        boolean expResult = true;
        boolean result = order.removeItems("Computer", 2);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testRemoveItems2() {
        System.out.println("removeItems2");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        order.addItem(item, 3);
        boolean expResult = false;
        boolean result = order.removeItems("Computer", -1);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testRemoveItems3() {
        System.out.println("removeItems3");
        Order order = new Order();
        Order.Item item = new Order.Item("Computer", 5.50);
        order.addItem(item, 3);
        boolean expResult = false;
        boolean result = order.removeItems("Computer", 4);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testItemEquals() {
        System.out.println("itemEquals");
        Order.Item item1 = new Order.Item("Computer", 5.50);
        Order.Item item2 = new Order.Item("Computer", 6.00);
        boolean expResult = true;
        boolean result = item1.equals(item2);
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testItemGetName() {
        System.out.println("itemGetName");
        Order.Item item = new Order.Item("Computer", 5.50);
        String expResult = "Computer";
        String result = item.getName();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testItemGetPrice() {
        System.out.println("itemGetPrice");
        Order.Item item = new Order.Item("Computer", 5.50);
        double expResult = 5.50;
        double result = item.getPrice();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testItemToString() {
        System.out.println("itemToString");
        Order.Item item = new Order.Item("Computer", 5.50);
        String expResult = "Item(Computer, 5.50)";
        String result = item.toString();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testEntryGetCount() {
        System.out.println("entryGetCount");
        Order.Item item = new Order.Item("Computer", 5.50);
        Order.Entry entry = new Order.Entry(item, 3);
        int expResult = 3;
        int result = entry.getCount();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testEntryGetItem() {
        System.out.println("entryGetItem");
        Order.Item item = new Order.Item("Computer", 5.50);
        Order.Entry entry = new Order.Entry(item, 3);
        Order.Item expResult = new Order.Item("Computer", 5.50);
        Order.Item result = entry.getItem();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testEntryGetItemName() {
        System.out.println("entryGetItemName");
        Order.Item item = new Order.Item("Computer", 5.50);
        Order.Entry entry = new Order.Entry(item, 3);
        String expResult = "Computer";
        String result = entry.getItemName();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testEntryGetUnitPrice() {
        System.out.println("entryGetUnitPrice");
        Order.Item item = new Order.Item("Computer", 5.50);
        Order.Entry entry = new Order.Entry(item, 3);
        double expResult = 5.50;
        double result = entry.getUnitPrice();
        assertEquals(expResult, result, "This was wrong");
    }
    
    @Test
    public void testEntryToString() {
        System.out.println("entryGetToString");
        Order.Item item = new Order.Item("Computer", 5.50);
        Order.Entry entry = new Order.Entry(item, 3);
        String expResult = "3 units of Item(Computer, 5.50)";
        String result = entry.toString();
        assertEquals(expResult, result, "This was wrong");
    }
}
