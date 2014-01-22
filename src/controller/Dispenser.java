package controller;

public class Dispenser {
	
	private int sugarCount;
	private int cupCount;
	
	public Dispenser() {
		sugarCount = cupCount = 0;
	}
	
	public Dispenser(int sugarCount, int cupCount) {
		this.sugarCount = sugarCount;
		this.cupCount = cupCount;
	}
	
	public void addSugar(int add) {
		sugarCount += add;
	}
	
	public void addCups(int add) {
		cupCount += add;
	}
	
	public int getCupCount() {
		return cupCount;
	}
	
	public int getSugarCount() {
		return sugarCount;
	}
}
