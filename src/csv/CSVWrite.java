package csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import delivery.Manifest;
import delivery.Truck;
import stock.Item;

public class CSVWrite {

	private FileWriter fw;
	
	public CSVWrite(String filePath) throws IOException {
		fw = new FileWriter(filePath);
	}
	
	public void exportManifestFile(Manifest manifest) throws IOException {
		for(Truck truck : manifest.getTrucks()) {
			if(truck.isOrdinaryTruck()) {
				fw.write(">Ordinary\n");
			}else {
				fw.write(">Refrigerated\n");
			}

			for(Entry<Item, Integer> entry : truck.getCargo().entrySet()) {
				Item item = entry.getKey();
				int quantity = entry.getValue();
				fw.write(item.getName() + "," + String.valueOf(quantity) + "\n");
			}
		}
		fw.close();
	}
}
