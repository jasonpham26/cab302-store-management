package delivery;

import exception.DeliveryException;

/**
 * A class for an ordinary truck type, extends an abstract Truck class
 * @author Le Vinh Phuong Pham
 *
 */
public class OrdinaryTruck extends Truck{
	/**
	 * Constructor for an ordinary truck type, which can contains 1000 items
	 */
	public OrdinaryTruck() {
		super();
		maxCapacity = 1000;
	}

	@Override
	public double getTruckTemp() throws DeliveryException{
		throw new DeliveryException("Attempt to get temperature of ordirary truck");
	}
	
	@Override
	public double truckCost() {
		return 750 + 0.25 * cargo.getTotalQuantity();
	}

	@Override
	public boolean isOrdinaryTruck() {
		return true;
	}
}
