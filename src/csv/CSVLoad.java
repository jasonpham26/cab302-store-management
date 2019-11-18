package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class CSVLoad {
	protected FileReader fr;
	protected BufferedReader br;
	
	public CSVLoad(String filePath) throws FileNotFoundException {
		fr = new FileReader(filePath);
		br = new BufferedReader(fr);
	}
	
	protected boolean tryParseDouble(String element) {
	     try {
	         Double.parseDouble(element);
	         return true;
	      } catch (NumberFormatException e) {
	         return false;
	      }
	}
	
	protected boolean tryParseInt(String element) {
	     try {
	         Integer.parseInt(element);
	         return true;
	      } catch (NumberFormatException e) {
	         return false;
	      }
	}
	
	protected abstract boolean isValidContent(String line);
}
