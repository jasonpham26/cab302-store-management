package stock;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import exception.StockException;
import stock.Item;
/**
 * Stock class, collection of items.
 * @author Nguyen Duc Nguyen
 *
 */
public class Stock extends AbstractMap<Item, Integer>{
	private Map<Item, Integer> stock;

	/**
	 * Constructor for Stock list 
	 */
	public Stock() {
		stock = new HashMap<Item, Integer>();
	}
	
	/**
	 * Add an item to a Stock collection
	 * @param item
	 * @param quantity must be greater or equal than 0
	 * @throws StockException invalid input 
	 */
	public void add(Item item, Integer quantity) throws StockException{
		if(quantity < 0) {
			throw new StockException("Attempt to add negative quantity.");
		}else if (stock.containsKey(item)) {
			stock.put(item, quantity + stock.get(item) );
		}else {
			stock.put(item, quantity);
		}
	}
	
	/**
	 * Remove a number of quantity from a specific item in the Stock list
	 * @param item
	 * @param quantity
	 * @throws StockException
	 */
	public void remove(Item item, Integer quantity) throws StockException{
		if(quantity < 0) {
			throw new StockException("Attempt to remove negative quantity of the item.");
		}else if (!stock.containsKey(item)) {
			throw new StockException("Attempt to remove unknown item.");
		}else if(stock.get(item) < quantity){
			throw new StockException("Attempt to remove too many quantity of the item");
		}else {
			stock.put(item, stock.get(item) - quantity);
		}
	}
	
	/**
	 * Get quantity of a specific item
	 * @param item
	 * @return item quantity
	 * @throws StockException when attemp to get quantity of unknown item
	 */
	public int getQuantity(Item item) throws StockException{
		if(!stock.containsKey(item)) {
			throw new StockException("Attempt to get quantity of unknown item.");
		}
		return stock.get(item);
	}
	
	/**
	 * Calculate total manufacturing cost by looping through the stock to get
	 * each item cost and add them up together
	 * @return total cost of all items in the Stock list
	 * @throws StockException
	 */
	public double getTotalCost() {
		int totalCost = 0;
		for(Entry<Item, Integer> entry: stock.entrySet()) {
			double itemCost = entry.getKey().getCost();
			int itemQuantity = entry.getValue();
			totalCost += itemCost * itemQuantity;
		}
		return totalCost;
	}
	
	/**
	 * Calculate sell price quantity by looping through the stock to get
	 * each item price and add them up together
	 * @return total sell price
	 * @throws StockException
	 */
	public double getTotalPrice() {
		int totalPrice= 0;
		for(Entry<Item, Integer> entry: stock.entrySet()) {
			double itemPrice = entry.getKey().getPrice();
			int itemQuantity = entry.getValue();
			totalPrice += itemPrice * itemQuantity;
		}
		return totalPrice;
	}
	
	/**
	 * Loop through the entire Stock list to check whether if the Stock does 
	 * contain perishable food that needs to be store in specific temperature
	 * @return
	 * @throws StockException
	 */
	public boolean hasFreshFood() {
		for(Entry<Item, Integer> entry : stock.entrySet()) {
			Item item = entry.getKey();
			int quantity = entry.getValue();
			if(item.isFreshFood() && quantity > 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the food that has lowest store temperature
	 * @return Item with lowest store temperature
	 * @throws StockException
	 */
	public Item getLowestTempItem() throws StockException {
		if(isEmptyStock()) {
			throw new StockException("Stock is empty");
		} else if(!hasFreshFood()) {
			return getAnyDryItem();
		}
		
		double lowestTemp = 100.0;
		Item result = null;
		for(Entry<Item, Integer> entry : stock.entrySet()) {
			Item item = entry.getKey();
			int quantity = entry.getValue();
			if (item.isFreshFood() && item.getTemp() < lowestTemp && quantity > 0) {
				result = item;
				lowestTemp = item.getTemp();
			}
		}
		return result;
	}
	
	
	private Item getAnyDryItem() throws StockException {
		for(Entry<Item, Integer> entry : stock.entrySet()) {
			Item item = entry.getKey();
			int quantity = entry.getValue();
			if (!item.isFreshFood() && quantity > 0) {
				return item;
			}
		}
		throw new StockException("No dry item in stock");
	}
	
	
	public int getTotalQuantity() {
		if(stock.isEmpty()) {
			return 0;
		}
		int result = 0;
		for(Integer quantity : stock.values()) {
			result += quantity;
		}
		return result;
	}
	
	public boolean isEmptyStock() {
		return getTotalQuantity() == 0;
	}
	
	@Override
	public Set<Entry<Item, Integer>> entrySet() {
		return stock.entrySet();
	}
}
