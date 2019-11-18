package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import delivery.Manifest;
import delivery.OrdinaryTruck;
import delivery.RefrigeratedTruck;
import delivery.Truck;
import exception.DeliveryException;
import exception.StockException;
import stock.Item;
/**
 * 
 * @author Nguyen Duc Nguyen
 *
 */
public class ManifestTest {
	private Manifest manifest;
	private Truck ordinaryTruck, refrigeratedTruck;
	private ArrayList<Truck> trucks;
	private Item fish, beef, rice;
	
	@Before
	public void setUp() throws DeliveryException, StockException {
		manifest = new Manifest();
		
		ordinaryTruck = new OrdinaryTruck();
		refrigeratedTruck = new RefrigeratedTruck();
		
		ArrayList<Truck> trucks = new ArrayList<Truck>();

		fish = new Item("fish", 13, 16, 375, 475, 2);
		beef = new Item("beef", 12, 17, 425, 550, 3);
		rice = new Item("rice", 2, 3, 225, 300);
	}
	
	@Test
	public void testGetTrucksEmpty() {
		assertEquals("Failed", trucks, manifest.getTrucks());
	}
	
	@Test
	public void testAddTruck1() {
		trucks.add(ordinaryTruck);
		manifest.addTruck(ordinaryTruck);
		assertEquals("Failed", trucks, manifest.getTrucks());
	}
	
	@Test
	public void testAddTruck2() {
		trucks.add(ordinaryTruck);
		trucks.add(refrigeratedTruck);
		
		manifest.addTruck(ordinaryTruck);
		manifest.addTruck(refrigeratedTruck);
		assertEquals("Failed", trucks, manifest.getTrucks());
	}
	
	@Test
	public void testGetManifestCostEmpty() throws StockException {
		assertEquals("Failed", 0.0, manifest.getManifestCost());
	}
	
	@Test
	public void testGetManifestCostOrdinary() throws DeliveryException, StockException {
		ordinaryTruck.addItem(rice, 250);
		double expected = ordinaryTruck.truckCost() + ordinaryTruck.getCargo().getTotalCost();
		
		manifest.addTruck(ordinaryTruck);
		assertEquals("Failed", expected, manifest.getManifestCost());
	}
	
	@Test
	public void testGetManifestCostRefrigerated() throws DeliveryException, StockException {
		refrigeratedTruck.addItem(rice, 250);
		refrigeratedTruck.addItem(beef, 250);
		refrigeratedTruck.addItem(fish, 250);
		double expected = refrigeratedTruck.truckCost() + refrigeratedTruck.getCargo().getTotalCost();
		
		manifest.addTruck(refrigeratedTruck);
		assertEquals("Failed", expected, manifest.getManifestCost());
	}
	
	@Test
	public void testGetManifestCostBoth() throws DeliveryException, StockException {
		refrigeratedTruck.addItem(rice, 250);
		refrigeratedTruck.addItem(beef, 250);
		refrigeratedTruck.addItem(fish, 250);
		ordinaryTruck.addItem(rice, 250);
		double expected = refrigeratedTruck.truckCost() + refrigeratedTruck.getCargo().getTotalCost();
		expected += ordinaryTruck.truckCost() + ordinaryTruck.getCargo().getTotalCost();
		
		manifest.addTruck(refrigeratedTruck);
		manifest.addTruck(ordinaryTruck);
		assertEquals("Failed", expected, manifest.getManifestCost());
	}
}
