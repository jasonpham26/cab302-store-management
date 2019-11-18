package test;
import stock.Item;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import exception.StockException;

import org.junit.Assert;

/**
 * Item class, holding properties of a particular item. 
 * They are: name, cost, price, reorder point, reorder amount, safe temperature.
 * 
 * @author Nguyen Duc Nguyen
 *
 */
public class ItemTest {
	Item dryItem, freshItem;
	/**
	 * Test 1: testing constructor, parsing item properties
	 */
	@Before
	public void SetUp() {
		dryItem = new Item("Rice", 2, 3, 225, 300);
		freshItem = new Item("Ice cream", 8, 14, 175, 250, -20);
	}
	/**
	 * Test 2: testing getName method, return name of the item
	 */
	@Test
	public void getName(){
		assertEquals("getName method is wrong", "Rice", dryItem.getName());
	}
	/**
	 * Test 3: testing getCost method, return cost of the item
	 */
	@Test
	public void getCost(){
		assertEquals("getCost method is wrong", 2, dryItem.getCost(), 0.001);
	}
	/**
	 * Test 4: testing getPrice method, return price of the item
	 */
	@Test
	public void getPrice(){
		assertEquals("getPrice method is wrong", 3, dryItem.getPrice(), 0.001);
	}
	/**
	 * Test 5: testing getReorderPoint method, return reorder point of the item
	 */
	@Test
	public void getReorderPoint(){
		assertEquals("getReorderPoint method is wrong", 225, dryItem.getReorderPoint());
	}
	/**
	 * Test 6: testing getReorderAmount method, return reorder amount of the item
	 */
	@Test
	public void getReorderAmount(){
		assertEquals("getReorderAmount method is wrong", 300, dryItem.getReorderAmount());
	}
	/**
	 * Test 7: testing getTemp method
	 * The test try to get temperature from fresh food which has safe temperature.
	 * @throws StockException
	 * The exception is raised when try to get temperature from dry food
	 * This test does not raise any.
	 */
	@Test
	public void getTempFromFreshFood() throws StockException{
		assertEquals("getTemp method is wrong", -20, freshItem.getTemp(), 0.001);
	}
	/**
	 * Test 8: testing getTemp method
	 * The test try to get temperature from dry food which does not have safe temperature
	 * @throws StockException
	 * This test will raise an exception.
	 */
	@Test(expected = StockException.class)
	public void getTempFromDryFood() throws StockException{
		dryItem.getTemp();
	}
	/**
	 * Test 9: testing isFreshFood method, return true if it is and vice versa
	 * The test tests on fresh food item. Expected true
	 */
	@Test
	public void testIsFreshFood1() {
		assertEquals("isFreshFood method is wrong", true, freshItem.isFreshFood());
	}
	/**
	 * Test 10: testing isFreshFood method, return true if it is and vice versa
	 * The test tests on dry food item. Expected false
	 */
	@Test
	public void testIsFreshFood2() {
		assertEquals("sFreshFood method is wrong", false, dryItem.isFreshFood());
	}
}