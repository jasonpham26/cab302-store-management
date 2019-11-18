package stock;

import exception.StockException;
/**
 * An Item, possessing at least the following properties: 
 * name , manufacturing cost, sell price (in dollars), reorder point,
 * reorder amount.
 * @author Le Vinh Phuong Pham
 *
 */
public class Item {
	private String name;
	private double cost;
	private double price;
	private int reorderPoint;
	private int reorderAmount;
	private double safeTemp;
	private boolean isFresh;
	

	/**
	 * Constructor for Dry Food Item
	 * @param name
	 * @param cost
	 * @param price
	 * @param reorderPoint
	 * @param reorderAmount
	 */
	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount) {
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		isFresh = false;
	}
	
	/**
	 * Constructor for fresh food
	 * @param name
	 * @param cost
	 * @param price
	 * @param reorderPoint
	 * @param reorderAmount
	 * @param safeTemp store temperature of item
	 */
	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount, double safeTemp) {
		this(name, cost, price, reorderPoint, reorderAmount);
		this.safeTemp = safeTemp;
		isFresh = true;
	}
	
	/**
	 * Get the name of current item
	 * @return name of item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the manufacturing cost of item
	 * @return manufacturing cost
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Get the sell price of item
	 * @return item sell price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Items whose quantities are less than or equal to their reorder point must 
	 * be reordered
	 * @return reorder point of item
	 */
	public int getReorderPoint() {
		return reorderPoint;
	}
	
	/**
	 * When an item needed to be reorder, N units of that item needed to reorder
	 * where N is reorder amount
	 * @return reorder amount
	 */
	public int getReorderAmount() {
		return reorderAmount;
	}
	
	/**
	 * Get the store temperature of current item, if item is a dry food type then 
	 * throw exception
	 * @return Store temperature of item
	 * @throws StockException
	 */
	public double getTemp() throws StockException {
		if(!isFresh) {
			throw new StockException("Attempt to get safe temperature of dry food");
		}
		return safeTemp;
	}
	
	/**
	 * Indicates whether if current Item is perishable or not
	 * @return True if Item is perishable 
	 */
	public boolean isFreshFood() {
		return isFresh;
	}
}
