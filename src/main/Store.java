package main;

import stock.Stock;

public class Store {
	private String storeName;
	private double capital;
	private Stock inventory;
	
	private static Store single_instance = null;
	
	private Store() {
		storeName = "Super Mart";
		capital = 100000;
		inventory = new Stock();
	}
	
	public static Store getInstance() {
		if (single_instance == null) {
			single_instance = new Store();
		}
		return single_instance;
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public double getCapital() {
		return capital;
	}
	
	public Stock getInventory() {
		return inventory;
	}
	
	public void addCapital(double value) {
		capital += value;
	}
	
	public void minusCapital(double value) {
		capital -= value;
	}
}
