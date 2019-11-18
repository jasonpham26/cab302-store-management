package delivery;

import exception.DeliveryException;
import exception.StockException;
import stock.Item;
import stock.Stock;
/**
 * An abstract class for Truck
 * @author Le Vinh Phuong Pham
 *
 */
public abstract class Truck {
	protected int maxCapacity;
	protected Stock cargo;
	
	/**
	 * Initializes the truck with an empty cargo
	 */
	public Truck() {
		cargo = new Stock();
	}
	
	/**
	 * Get cargo on this truck
	 * @return cargo
	 */
	public Stock getCargo() {
		return cargo;
	}
	
	/**
	 * Check the remain capacity of this truck
	 * @return remaining capacity
	 */
	public int remainingCapacity() {
		return maxCapacity - cargo.getTotalQuantity();
	}
	
	/**
	 * Add an item to the truck's cargo with a specific quantity of that item
	 * @param item
	 * @param quantity
	 * @throws DeliveryException
	 * @throws StockException
	 */
	public void addItem(Item item, int quantity) throws DeliveryException, StockException {
		if(quantity > remainingCapacity()) {
			throw new DeliveryException("Attempt to add too many items to the truck");
		}else if (item.isFreshFood() && this.isOrdinaryTruck()){
			throw new DeliveryException("Attempt to add fresh food to an ordinary truck");
		}else {
			cargo.add(item, quantity);
		}
	}
	
	/**
	 * Get the Refrigerated truck temperature.
	 * @return Truck temperature
	 * @throws StockException
	 * @throws DeliveryException when attempt to get Ordinary truck temperature
	 */
	public abstract double getTruckTemp() throws StockException, DeliveryException;
	
	/**
	 * Return the hiring cost for the truck 
	 * @return hiring cost
	 */
	public abstract double truckCost() throws StockException;
	
	/**
	 * Check if the current Truck object is Ordinary truck or not (an ordinary truck does not
	 * have store temperature)
	 * @return true if Truck type is Ordinary
	 */
	public abstract boolean isOrdinaryTruck();
}
