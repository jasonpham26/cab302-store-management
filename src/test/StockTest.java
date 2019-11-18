package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import exception.StockException;
import stock.Item;
import stock.Stock;

/**
 * This unit test is created to test the implementation of class Stock()
 * which is a collection of Items
 * @author Le Vinh Phuong Pham
 *
 */
public class StockTest {


	/**
	 * Random an amount of quantity ranging from min to max
	 * @param min
	 * @param max
	 * @return
	 */
	private static int randQuantity(int min, int max) {
		Random generator = new Random();
		int i = generator.nextInt(max) + min;
		return i;
	}
	// Declaring mock objects
	Item dryFood;
	Item freshFood;
	Stock stock;

	/**
	 * Test 0. Setup and test mock objects before running any test
	 * [This test obliges you to implement a Stock class]
	 * @return
	 */
	@Before @Test
	public void setup() {
		dryFood = new Item("pasta", 2, 3, 225, 300);
		freshFood = new Item("pork", 8, 14, 175, 250, -3);
		stock = new Stock(); //
	}

	/**
	 * Test 1. Add an item and its quantity to a stock
	 * [This test obliges you to implement add() and getQuantity() methods to add a specific item
	 * to the collection and get the quantity of that item]
	 * @throws StockException 
	 */
	@Test
	public void test_addItem() throws StockException {
		int expected = randQuantity(10, 100);
		stock.add(dryFood, expected);
		assertEquals("Failed to add item", expected, stock.getQuantity(dryFood));
	}
	/**
	 * These tests below are designed to test the correctness of add() methods
	 */

	/**
	 * Test 1a. Call add() function to add more quantity to an Item which is already within the Stock
	 * @throws StockException 
	 */
	@Test
	public void test_addMoreQuantity() throws StockException {
		stock.add(dryFood, 10);
		stock.add(dryFood, 20);
		int expected = 30;
		assertEquals("Failed to add more quantity to an existing item", expected, stock.getQuantity(dryFood));

	}
	/**
	 * Test 1b. Add a negative value to the Item quantity
	 */
	@Test(expected = StockException.class)
	public void test_addNegativeQuantity() throws StockException {
		stock.add(dryFood, 50);
		stock.add(dryFood, -20);
	}

	/**
	 * Test 3a. Get the total manufacturing cost of Items within the Stock
	 * [This test obliges you to implement the getTotalCost() which will will loop through
	 * the Stock collection to get each item cost than calculate the total cost]
	 * @throws StockException
	 */
	@Test
	public void test_getTotalCost() throws StockException {
		double dryFoodCost = dryFood.getCost();
		double freshFoodCost = freshFood.getCost();
		double expectedCost = dryFoodCost*5 + freshFoodCost*7;
		stock.add(dryFood, 5);
		stock.add(freshFood, 7);
		assertEquals("Failed to get total cost of the Stock", expectedCost, stock.getTotalCost(), 0.001);

	}
	/**
	 * Test 3b. Get total cost of a collection of items with zero quantity
	 */
	@Test
	public void test_getTotalCost2() throws StockException {
		stock.add(dryFood, 0);
		stock.add(freshFood, 0);
		double expected = 0;
		assertEquals("Failed to get total cost of the Stock", expected , stock.getTotalCost(), 0.001);
	}

	/**
	 * Test 4a. Get the total sell price of Items within the Stock
	 * [This test obliges you to implement the getTotalPrice() which will will loop through
	 * the Stock collection to get each item sell price than calculate the total sell price]
	 * @throws StockException
	 */
	@Test
	public void test_getTotalPrice() throws StockException {
		stock.add(dryFood, 10);
		stock.add(freshFood, 20);
		double expected = dryFood.getPrice()*10 + freshFood.getPrice()*20;
		assertEquals("Failed to get sell price of the Stock", expected , stock.getTotalPrice(), 0.001);
	}
	/**
	 * Test 4b. Get total sell price from zero quantity
	 */
	@Test
	public void test_getTotalPrice2() throws StockException{
		stock.add(dryFood, 0);
		stock.add(freshFood, 0);
		double expected = 0;
		assertEquals("Failed to get sell price of the Stock", expected , stock.getTotalPrice(), 0.001);

	}

	/**
	 * Test 5. Check if the Stock contains any perishable item which needed to store in
	 * low temperature
	 * [This test obliges you to implement the hasFreshFood() method to check if there is any fresh food Item
	 * within the Stock]
	 * @throws StockException
	 */
	@Test
	public void test_hasFreshFood() throws StockException {

		// Add 2 types of item to the Stock collection
		stock.add(dryFood, randQuantity(10, 100));
		stock.add(freshFood, randQuantity(10, 100));
		boolean expected = true; // Expected method to return true
		assertEquals("Failed! Stock does contain the fresh item", expected, stock.hasFreshFood());
	}

	/**
	 * Test 6a. Get the item which has the lowest temperature within the stock
	 * [This test obliges you to implement getLowestTemp() to loop through the Stock collection and find out the
	 * lowest temperature]
	 * @throws StockException
	 */
	@Test
	public void test_getLowestTempItem() throws StockException {
		// Initializes another fresh food item for testing
		Item freshFood2 = new Item("chicken" ,8,14,175,250,-2);
		stock.add(freshFood2, randQuantity(10, 100));
		stock.add(freshFood, randQuantity(10, 100));
		assertEquals("Failed to get lowest temp item", freshFood, stock.getLowestTempItem());
	}

	/**
	 * Test 6b. Get item with lowest temperature. In this case stock does contain a dry food item
	 */
	@Test
	public void test_getLowestTempItem2() throws StockException {
		Item freshFood2 = new Item("chicken",8,14,175,250,-2);
		stock.add(dryFood, 10);
		stock.add(freshFood, 10);
		stock.add(freshFood2, 10);
		assertEquals("Failed to get lowest temp item", freshFood, stock.getLowestTempItem());
	}

	/**
	 * Test 6c. Get item with lowest temp, but if the lowest temperature item has 0 quantity
	 * then get the second lowest temp
	 */
	@Test
	public void test_getLowestTempItem3() throws StockException {
		Item freshFood2 = new Item("chicken",8,14,175,250,-2);
		stock.add(freshFood, 0);
		stock.add(freshFood2, 10);
		assertEquals("Failed to get lowest temp item", freshFood2, stock.getLowestTempItem());
	}
	/**
	 * Test 7a. Get total quantity
	 */
	@Test
	public void test_totalQuantity() throws StockException {
		int quantity1 = 500;
		int quantity2 = 400;
		int expected = quantity1 + quantity2;
		stock.add(dryFood, quantity1);
		stock.add(freshFood, quantity2);
		assertEquals("Failed to get total quantity", expected, stock.getTotalQuantity());
	}

	/**
	 * Test 7b. Get total quantity but in this case, the Stock does not contains 0 item quantity
	 */
	@Test
	public void test_totalQuantity2() throws StockException {
		stock.add(dryFood, 0);
		stock.add(freshFood, 0);
		assertEquals("Failed to get total quantity", 0, stock.getTotalQuantity());
	}

	/**
	 * Test 7c. Get total quantity but in this case, the Stock is empty
	 */
	@Test
	public void test_totalQuantity3() throws StockException {
		assertEquals("Failed to get total quantity", 0, stock.getTotalQuantity());
	}
	/**
	 * Test 8. Remove an Item from the Stock
	 *[This test obliges you to implement remove() method to delete an item from the Stock collection]
	 */
	@Test
	public void test_removeItemQuantity() throws StockException {
		stock.add(freshFood, 0);
		stock.add(freshFood, 30); // Add 30 units to quantity
		stock.remove(freshFood, 25); // Delete 25 units from item quantity
		assertEquals("Failed to decrease item quantity", 5, stock.getQuantity(freshFood));
	}
	// These small test is designed to check the remove() method correctness

	/**
	 * Test 8a. Check if remove() too many quantity
	 */
	@Test(expected = StockException.class)
	public void test_removeTooManyItem() throws StockException{
		stock.add(freshFood, 0);
		stock.add(freshFood, 30); // Add 30 units to quantity
		stock.remove(freshFood, 35); // Delete 35 units from item quantity
	}

	/**
	 * Test 8b. Check if remove() throws exception against negative value
	 */
	@Test(expected = StockException.class)
	public void test_removeNegativeQuantity() throws StockException {
		stock.add(freshFood, 0);
		stock.add(freshFood, 30);
		stock.remove(freshFood, 35);
	}
	/**
	 * Test 8c. Remove an unknown item from the Stock
	 */
	@Test(expected = StockException.class)
	public void test_removeUnknownItem() throws StockException {
		stock.add(freshFood, 0);
		stock.add(freshFood, 30);
		stock.remove(dryFood, 35);
	}


	/**
	 * Test 9. Get quantity of an unknown item when call getQuantity() method
	 */
	@Test(expected = StockException.class)
	public void test_getQuantityUnknownItem() throws StockException {
		stock.add(freshFood, 30);
		stock.getQuantity(dryFood);
	}

	/**
	 * Test 10a. Check if Stock is empty
	 * [This test obliges you to implement isEmptyStock() to check if Stock does not contain any Item]
	 */
	@Test
	public void test_isEmpty(){
		assertTrue(stock.isEmptyStock());
	}

	/**
	 * Test 10b. Check if Stock is empty
	 * @throws StockException 
	 */
	@Test
	public void test_isEmpty2() throws StockException {
		stock.add(dryFood, 100); // Stock is not empty
		boolean expected = false;
		assertEquals("Failed ! Stock does contain item", expected, stock.isEmptyStock());
	}
}
