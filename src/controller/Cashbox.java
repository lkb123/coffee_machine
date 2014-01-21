package controller;

public class Cashbox {
	
	private int credit;
	
	/**
	 * 
	 * @param amount the amount of coins you insert to the cash box
	 */
	public void insert(int amount) {
		credit += amount;
	}
	
	/**
	 * gives the change and asserts the credit to 0
	 * @return the change remaining in the cash box
	 */
	public int change() {
		int change = credit;
		credit = 0;
		return change;
	}

	/**
	 * 
	 * @return the credit in the cash box
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * 
	 * @param i the amount to be consumed
	 */
	public void consume(int amount) {
		credit -= amount;
	}
	
	@Override
	public Cashbox clone() {
		return this.clone();
	}
	
}
