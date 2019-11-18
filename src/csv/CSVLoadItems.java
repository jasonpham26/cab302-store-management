package csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import exception.CSVFormatException;
import stock.Item;

public class CSVLoadItems extends CSVLoad{

	public CSVLoadItems(String filePath) throws FileNotFoundException{
		super(filePath);
	}

	public ArrayList<Item> loadItemsFile() throws IOException, CSVFormatException{
		ArrayList<Item> items = new ArrayList<Item>();
		
		for(String line = br.readLine(); line != null; line = br.readLine()) {
			if(!isValidContent(line)) {
				throw new CSVFormatException("The chosen file has incorrect content.");
			}
			
			String[] elements = line.split(",");
			String name = elements[0];
			double cost = Double.parseDouble(elements[1]);
			double price = Double.parseDouble(elements[2]);
			int reorderPoint = Integer.parseInt(elements[3]);
			int reorderAmount = Integer.parseInt(elements[4]);
			
			if (elements.length == 5) {
				items.add(new Item(name, cost, price, reorderPoint, reorderAmount));
			} else if(elements.length == 6) {
				double safeTemp = Double.parseDouble(elements[5]);
				items.add(new Item(name, cost, price, reorderPoint, reorderAmount, safeTemp));
			}
		}
		br.close();
		fr.close();
		return items;
	}
	
	@Override
	protected boolean isValidContent(String line) {
		String[] elements = line.split(",");
		
		if( elements.length == 5 && tryParseDouble(elements[1]) && tryParseDouble(elements[2]) &&
			tryParseInt(elements[3]) && tryParseInt(elements[4])) {
			return true;
		}else if (elements.length == 6 && tryParseDouble(elements[1]) && tryParseDouble(elements[2]) &&
			tryParseInt(elements[3]) && tryParseInt(elements[4]) && tryParseDouble(elements[5])) {
			return true;
		}
		
		return false;
	}

}
