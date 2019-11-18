package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import exception.StockException;

import org.junit.Assert;

import main.Store;
/**
 * This class contains tests for Store class correctness
 * @author Le Vinh Phuong Pham
 *
 */
public class StoreTest {
	
	
	Store store;
	double initialCapital = 100000;

	/**
	 * Test 0. Initialize new Store object before any test and also
	 * test the correctness of Store constructor.
	 * [This test obliges you to implement getInstance() to generate a singleton object]
	 */
	@Before @Test
	public void test_storeGetInstance() {
		// Singleton test
		store = store.getInstance(); 
	}
	
	/**
	 * Test 1. Get store name. According to the specifications, our store name
	 * will always be "Super Mart"
	 */
	@Test
	public void test_getStoreName() {
		String expected = "Super Mart";
		assertEquals("Failed to get Store name", expected, store.getStoreName());
	}
	
	/**
	 * Test 2. Get capital. When first opened, a starting capital of $100,000.00
	 * must be displayed.
	 */
	@Test
	public void test_getCapital() {
		assertEquals("Failed to get store capital", initialCapital, store.getCapital(), 0.001);
	}
	
	@Test
	public void test_addCapital() {
		store.addCapital(500);
		assertEquals("Failed to add capital", initialCapital + 500, store.getCapital(), 0.001);
	}
	
	@Test
	public void test_minusCapital() {
		store.minusCapital(500);
		assertEquals("Failed to add capital", initialCapital - 500, store.getCapital(), 0.001);
	}
}
