package csv;

import java.io.FileNotFoundException;
import java.io.IOException;

import stock.Item;
import exception.CSVFormatException;
import exception.DeliveryException;
import exception.StockException;
import delivery.*;
import main.Store;
/**
 * The class is a subclass of CSVLoad. It contains the logic how to read a manifest file to Manifest object.
 * 
 * @author Nguyen Duc Nguyen, n9902163
 *
 */
public class CSVLoadManifest extends CSVLoad{

	public CSVLoadManifest(String filePath) throws FileNotFoundException {
		super(filePath);
	}
	
	/**
	 * The method used to read data from a manifest file and then generating a Manifest object.
	 * 
	 * @return a Manifest object that already generated list of Trucks
	 * @throws IOException
	 * @throws DeliveryException
	 * @throws CSVFormatException
	 * @throws StockException
	 */
	public Manifest loadManifestFile() throws IOException, DeliveryException, CSVFormatException, StockException {
		Manifest manifest = new Manifest();
		Truck currentTruck = new OrdinaryTruck();
		
		for(String line = br.readLine(); line != null; line = br.readLine()) {
			if(!isValidContent(line)) {
				throw new CSVFormatException("The chosen file has incorrect content.");
			}
			
			if(line.toLowerCase().equals(">ordinary")) {
				if(!currentTruck.getCargo().isEmptyStock()) {
					manifest.getTrucks().add(currentTruck);
				}
				currentTruck = new OrdinaryTruck();
			}else if (line.toLowerCase().equals(">refrigerated")) {
				if(!currentTruck.getCargo().isEmptyStock()) {
					manifest.getTrucks().add(currentTruck);
				}
				currentTruck = new RefrigeratedTruck();
			}else {
				String[] elements = line.split(",");
				String itemNameManifest = elements[0];
				int quantity = Integer.parseInt(elements[1]);
				boolean hasItem = false;
				for(Item itemInventory: Store.getInstance().getInventory().keySet()) {
					if(itemNameManifest.equals(itemInventory.getName())) {
						currentTruck.addItem(itemInventory, quantity);
						hasItem = true;
						break;
					}
				}
				if(!hasItem) {
					throw new DeliveryException("The chosen manifest contains unknown item");
				}
			}
		}
		manifest.getTrucks().add(currentTruck);
		br.close();
		fr.close();
		return manifest;
	}

	@Override
	protected boolean isValidContent(String line) {
		if( line.toLowerCase().equals(">ordinary") || 
				line.toLowerCase().equals(">refrigerated")) {
				return true;
			} else {
				String[] elements = line.split(",");
				
				if(elements.length == 2 && tryParseInt(elements[1])) {
					return true;
				}
			}
			
			return false;
	}

}
