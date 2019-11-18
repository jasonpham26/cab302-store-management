package csv;

import java.io.FileNotFoundException;
import java.io.IOException;

import exception.CSVFormatException;
import exception.DeliveryException;
import exception.StockException;
import main.Store;
import stock.Item;
import stock.Stock;

public class CSVLoadSaleLog extends CSVLoad{

	public CSVLoadSaleLog(String filePath) throws FileNotFoundException {
		super(filePath);
	}
	public Stock loadSaleLogFile() throws IOException, CSVFormatException, StockException, DeliveryException {
		Stock saleLog = new Stock();
		
		for(String line = br.readLine(); line != null; line = br.readLine()) {
			if(!isValidContent(line)) {
				throw new CSVFormatException("The chosen file has incorrect content.");
			}
			
			String[] elements = line.split(",");
			String itemNameSaleLog = elements[0];
			int quantity = Integer.parseInt(elements[1]);
			boolean hasItem = false;
			for(Item itemInventory: Store.getInstance().getInventory().keySet()) {
				if(itemNameSaleLog.equals(itemInventory.getName())) {
					saleLog.add(itemInventory, quantity);
					hasItem = true;
					break;
				}
			}
			if(!hasItem) {
				throw new DeliveryException("The chosen sale log contains unknown item");
			}
		}
		br.close();
		fr.close();
		return saleLog;
	}
	@Override
	protected boolean isValidContent(String line) {
		String[] elements = line.split(",");
		
		if(elements.length == 2 && tryParseInt(elements[1])) {
			return true;
		}
		
		return false;
	}

}
