package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import delivery.OrdinaryTruck;
import delivery.RefrigeratedTruck;
import delivery.Truck;
import exception.DeliveryException;
import exception.StockException;
import stock.Item;
import stock.Stock;

/**
 * The unit test is created to test the implementation of class Truck
 * @author Nguyen Duc Nguyen
 *
 */
public class TruckTest {
	Truck ordinaryTruck;
	RefrigeratedTruck refrigeratedTruck;
	Item fish, beef, rice, cheese, yogurt;
	/**
	 * Test 0: setup and testing constructor.
	 */
	@Before @Test
	public void SetUp() {
		fish = new Item("fish", 13, 16, 375, 475, 2);
		beef = new Item("beef", 12, 17, 425, 550, 3);
		rice = new Item("rice", 2, 3, 225, 300);
		cheese = new Item("cheese", 4, 7, 375, 450, 3);
		yogurt = new Item("yogurt", 10, 12, 200, 325, 3);
		
		ordinaryTruck = new OrdinaryTruck();
		refrigeratedTruck = new RefrigeratedTruck();
	}
	
	/**
	 * Test 1: add dry food to ordinary truck properly (not too many)
	 * @throws DeliveryException
	 * @throws StockException
	 * [This test obliges you to implement addItem and getCargo methods]
	 */
	@Test
	public void addRightItemOrdinary() throws DeliveryException, StockException {
		ordinaryTruck.addItem(rice, 100);
		ordinaryTruck.addItem(rice, 200);
		Stock cargo = ordinaryTruck.getCargo();
		assertEquals("Failed to add item", 300, cargo.getTotalQuantity());
		
	}
	
	/**
	 * Test 2: add food to fresh truck properly (not too many)
	 * @throws DeliveryException
	 * @throws StockException
	 * [This test obliges you to implement addItem and getCargo methods]
	 */
	@Test
	public void addRightItemRefrigerated() throws DeliveryException, StockException {
		refrigeratedTruck.addItem(rice, 100);
		refrigeratedTruck.addItem(fish, 200);
		Stock cargo = refrigeratedTruck.getCargo();
		assertEquals("Failed to add item", 300, cargo.getTotalQuantity());
		
	}
	
	/**
	 * Test 3: add fresh food to ordinary truck.
	 * The test will raise exception because add item in wrong truck
	 * @throws DeliveryException
	 * @throws StockException
	 * [This test obliges you to implement addItem and getCargo methods]
	 */
	@Test(expected = DeliveryException.class)
	public void addWrongItemRefrigerated() throws DeliveryException, StockException {
		ordinaryTruck.addItem(fish, 200);
		
	}
	
	/**
	 * Test 4: add maximum amount of items to ordinary, which is 1000
	 * @throws StockException
	 * @throws DeliveryException 
	 * [This test obliges you to implement addItem and getCargo methods]
	 */
	@Test
	public void addMaxItemOrdinary() throws StockException, DeliveryException {
		ordinaryTruck.addItem(rice, 500);
		ordinaryTruck.addItem(rice, 500);
		Stock cargo = ordinaryTruck.getCargo();
		assertEquals("Failed to add item", 1000, cargo.getTotalQuantity());
	}
	
	/**
	 * Test 5: add more than maximum amount of items to ordinary, which is 1000.
	 * The test will raised exception because adding more than allowed capacity.
	 * @throws StockException
	 * @throws DeliveryException 
	 * [This test obliges you to implement addItem and getCargo methods]
	 */
	@Test(expected = DeliveryException.class)
	public void addManyItemOrdinary() throws StockException, DeliveryException {
		ordinaryTruck.addItem(rice, 500);
		ordinaryTruck.addItem(rice, 501);
	}
	
	/**
	 * Test 6: add maximum amount of items to refrigerated truck, which is 800.
	 * @throws StockException
	 * @throws DeliveryException 
	 * [This test obliges you to implement addItem and getCargo methods]
	 */
	@Test
	public void addMaxItemRefrigerated() throws StockException, DeliveryException {
		refrigeratedTruck.addItem(rice, 500);
		refrigeratedTruck.addItem(beef, 300);
		Stock cargo = refrigeratedTruck.getCargo();
		assertEquals("Failed to add item", 800, cargo.getTotalQuantity());
	}
	
	/**
	 * Test 7: add more than maximum amount of items to refrigerated truck, which is 800.
	 * The test will raised exception because adding more than allowed capacity.
	 * @throws StockException
	 * @throws DeliveryException 
	 * [This test obliges you to implement addItem and getCargo methods]
	 */
	@Test(expected = DeliveryException.class)
	public void addManyItemRefrigerated() throws StockException, DeliveryException {
		refrigeratedTruck.addItem(rice, 500);
		refrigeratedTruck.addItem(beef, 301);
	}
	
	/**
	 * 
	 * Test 9: testing whether ordinaryTruck is an ordinary truck. (true)
	 * [This test obliges you to implement isOrdinarayTruck method]
	 */
	@Test
	public void isOrdinaryTruck1() {
		assertEquals("Failed determining type of truck", true, ordinaryTruck.isOrdinaryTruck());
	}
	
	/**
	 * 
	 * Test 10: testing whether refrigeratedTruck is an ordinary truck (false)
	 * [This test obliges you to implement isOrdinarayTruck method]
	 */
	@Test
	public void isOrdinaryTruck2() {
		assertEquals("Failed determining type of truck", false, refrigeratedTruck.isOrdinaryTruck());
	}
	
	/**
	 * Test 11: get remaining capacity of refrigerated truck.
	 * The test add 400 unit of beef, so the truck still has 400 capacity.
	 * It is because maximum capacity of regrigerated truck is 800
	 * @throws DeliveryException
	 * @throws StockException
	 * [This test obliges you to implement remainingCapacity method]
	 */
	@Test
	public void remainingCapacity1() throws DeliveryException, StockException {
		refrigeratedTruck.addItem(beef, 400);
		assertEquals("Failed to get remaining capacity", 400, refrigeratedTruck.remainingCapacity());
	}
	
	/**
	 * Test 12: get remaining capacity of ordinary truck.
	 * The test add 400 unit of rice, so the truck still has 600 capacity.
	 * It is because maximum capacity of ordinary truck is 1000
	 * @throws DeliveryException
	 * @throws StockException
	 * [This test obliges you to implement remainingCapacity method]
	 */
	@Test
	public void remainingCapacity2() throws DeliveryException, StockException {
		ordinaryTruck.addItem(rice, 200);
		assertEquals("Failed to get remaining capacity", 800, ordinaryTruck.remainingCapacity());
	}
	
	/**
	 * Test 13: testing get truck temperature from the truck has no cargo
	 * The test will raise StockException because trying to get temp from empty stock
	 * @throws StockException
	 * @throws DeliveryException
	 * [This test obliges you to implement getTruckTemp method]
	 */
	@Test(expected = DeliveryException.class)
	public void getEmptyTruckTemp() throws StockException, DeliveryException {
		ordinaryTruck.getTruckTemp();
	}
	
	/**
	 * Test 14: testing get truck temperature from an ordinary truck
	 * The test will raise DeliveryException because 
	 * trying to get truck's temperature from an ordinary truck
	 * @throws StockException
	 * @throws DeliveryException
	 * [This test obliges you to implement getTruckTemp method]
	 */
	@Test(expected = DeliveryException.class)
	public void getOrdinaryTruckTemp() throws StockException, DeliveryException {
		ordinaryTruck.getTruckTemp();
	}
	
	/**
	 * Test 15: testing get truck temperature from a refrigerated truck
	 * @throws StockException
	 * @throws DeliveryException
	 * [This test obliges you to implement getTruckTemp method]
	 */
	@Test
	public void getRefrigeratedTruckTemp() throws DeliveryException, StockException {
		refrigeratedTruck.addItem(yogurt, 2);
		refrigeratedTruck.addItem(fish, 2);
		refrigeratedTruck.addItem(beef, 2);
		
		assertEquals("Failed to get truck temperature", 2, refrigeratedTruck.getTruckTemp(), 0.1);
	}
}
