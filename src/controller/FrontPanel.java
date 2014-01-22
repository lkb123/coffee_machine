package controller;

import java.security.KeyException;
import java.util.HashMap;

import exceptions.FlavorNotAvailableException;
import exceptions.NoSelectedFlavorException;
import exceptions.NotEnoughMoneyException;

public class FrontPanel {
	
	private HashMap<String, Integer> menu = new HashMap<String, Integer> ();
	private String selected;
	private int price;
	private Cashbox cashbox;
	private Mixer mixer;
	
	/**
	 * Constructs the FrontPanel object
	 */
	public FrontPanel() {
		menu.put("coffee", new Integer(35));
		selected = "";
		price = 0;
	}
	
	/**
	 * Constructs the FrontPanel object
	 * @param cashbox the cash box of the machine
	 * @param mixer the mixer of the machine
	 */
	public FrontPanel(Cashbox cashbox, Mixer mixer) {
		this();
		this.cashbox = cashbox;
		this.mixer = mixer;
	}
	
	/**
	 * 
	 * @return a copy of the menus offered by the machine
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Integer> getMenu() {
		return (HashMap<String, Integer>) menu.clone();
	}

	/**
	 * lets you add another menu
	 * @param key the name of the flavor of the new menu
	 * @param value the corresponding price for the flavor
	 */
	public void addMenu(String key, Integer value) {
		menu.put(key, value);
	}
	
	/**
	 * removes a menu
	 * @param key the flavor of the menu to be removed
	 * @throws FlavorNotAvailableException 
	 * @throws KeyException thrown if the key is not found
	 */
	public void removeMenu(String key) throws FlavorNotAvailableException {
		if(menu.containsKey(key)) {
			menu.remove(key);
		}
		else
			throw new FlavorNotAvailableException("The flavor " + key + " is not available");
	}

	/**
	 * selects a flavor from the menu
	 * @param key the flavor that is selected
	 * @throws FlavorNotAvailableException 
	 * @throws KeyException thrown if the key is not found
	 */
	public void select(String key) throws FlavorNotAvailableException {
		if(menu.containsKey(key)) {
			selected = key;
			price = menu.get(key);
		} else 
			throw new FlavorNotAvailableException("The flavor " + key + " is not available");
		
	}
	
	public void serve() throws NotEnoughMoneyException {
		if(hasEnoughMoney()) {
			
			cashbox.consume(price);
		}
		else {
			int balance = getPrice(selected) - cashbox.getCredit();
			throw new NotEnoughMoneyException("Please enter " + balance + " pesos more");
		}
	
	}
	
	/**
	 * 
	 * @return indicates if there is enough money in the cash box
	 */
	private boolean hasEnoughMoney() {
		return price <= cashbox.getCredit();
	}
	
	/**
	 * 
	 * @return the selected flavor
	 */
	public String getSelected() {
		return selected;
	}
	
	/**
	 * 
	 * @return the corresponding price of the selected flavor
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @param flavor the flavor you want to know the price of 
	 * @return	the price of the flavor specified
	 */
	public int getPrice(String flavor) {
		return menu.get(flavor);
	}
	
}
