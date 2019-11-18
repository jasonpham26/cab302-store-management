package delivery;

import exception.StockException;

/**
 * A class for a refrigerated truck type, extends an abstract Truck class
 * @author Le Vinh Phuong Pham
 *
 */
public class RefrigeratedTruck extends Truck{
	/**
	 * Constructor for a refrigerated truck type, which can contains 800 items
	 */
	public RefrigeratedTruck() {
		super();
		maxCapacity = 800;
	}
	
	@Override
	public double getTruckTemp() throws StockException {
		return cargo.getLowestTempItem().getTemp();
	}

	@Override
	public double truckCost() throws StockException {
		return 900 + 200*Math.pow(0.7, getTruckTemp()/5);
	}

	@Override
	public boolean isOrdinaryTruck() {
		return false;
	}
}
