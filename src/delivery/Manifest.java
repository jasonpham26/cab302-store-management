package delivery;

import java.util.ArrayList;

import delivery.*;
import exception.DeliveryException;
import exception.StockException;
import stock.*;

public class Manifest {
	private ArrayList<Truck> trucks;
	private Truck currentTruck;
	
	public Manifest() {
		trucks = new ArrayList<Truck>();	
		currentTruck = null;
	}
	
	public ArrayList<Truck> getTrucks() {
		return trucks;
	}

	public void addTruck(Truck truck) {
		trucks.add(truck);
	}

	public double getManifestCost() throws StockException {
		double result = 0.0;
		for(Truck truck : trucks) {
			result += truck.getCargo().getTotalCost() + truck.truckCost();
		}
		return result;
	}

	public void generateTrucks(Stock stockOrder) throws StockException, DeliveryException {
		while(!stockOrder.isEmptyStock()) {
			Item item = stockOrder.getLowestTempItem();
			int quantity = stockOrder.getQuantity(item);
			if(currentTruck == null) {
				if(item.isFreshFood()) {
					currentTruck =  new RefrigeratedTruck();
				}else {
					currentTruck = new OrdinaryTruck();
				}
			}else {
				if(quantity <= currentTruck.remainingCapacity()) {
					stockOrder.remove(item, quantity);
					currentTruck.addItem(item, quantity);
					if(stockOrder.isEmptyStock()) {
						trucks.add(currentTruck);
					}
				}else {
					stockOrder.remove(item, currentTruck.remainingCapacity());
					currentTruck.addItem(item, currentTruck.remainingCapacity());
					trucks.add(currentTruck);
					currentTruck = null;
				}
			}	
		}
	}

}
