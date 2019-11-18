package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import csv.*;
import delivery.*;
import exception.CSVFormatException;
import exception.DeliveryException;
import exception.StockException;
import stock.*;

public class Logic {
	private Store store;
	
	public Logic() {
		store = Store.getInstance();
	}
	public void loadItems(String filePath) throws IOException, CSVFormatException, StockException {
		CSVLoadItems csv = new CSVLoadItems(filePath);
		ArrayList<Item> items = csv.loadItemsFile();
		for(Item item : items) {
			store.getInventory().add(item, 0);
		}
	}

	public void loadSaleLog(String filePath) throws IOException, CSVFormatException, StockException, DeliveryException {
		CSVLoadSaleLog csv = new CSVLoadSaleLog(filePath);
		Stock saleLog = csv.loadSaleLogFile();
		for(Entry<Item, Integer> entry : saleLog.entrySet()) {
			Item item = entry.getKey();
			int quantity = entry.getValue();
			store.getInventory().remove(item, quantity);
		}
		store.addCapital(saleLog.getTotalPrice());
	}

	public void loadManifest(String filePath) throws StockException, IOException, DeliveryException, CSVFormatException {
		CSVLoadManifest csv = new CSVLoadManifest(filePath);
		Manifest manifest = csv.loadManifestFile();
		for(Truck truck : manifest.getTrucks()) {
			for(Entry<Item, Integer> entry : truck.getCargo().entrySet()) {
				Item item = entry.getKey();
				int quantity = entry.getValue();
				store.getInventory().add(item, quantity);
			}
		}
		store.minusCapital(manifest.getManifestCost());
	}

	public void exportManifest(String filePath) throws StockException, DeliveryException, IOException {
		CSVWrite csv = new CSVWrite(filePath);
		Stock stockOrder = generateStockOrder(store.getInventory());
		Manifest manifest = new Manifest();
		manifest.generateTrucks(stockOrder);
		csv.exportManifestFile(manifest);
		
	}
	
	private Stock generateStockOrder(Stock inventory) throws StockException {
		Stock stockOrder = new Stock();
		for(Entry<Item, Integer> entry : inventory.entrySet()) {
			Item item = entry.getKey();
			int quantity = entry.getValue();
			if(quantity <= item.getReorderPoint()) {
				stockOrder.add(item, item.getReorderAmount());
			}
		}
		return stockOrder;
	}

}
