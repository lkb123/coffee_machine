package controller;

public class Dispenser {
	
	private int sugarCount, cupCount, creamerCount, waterCount;
	
	public Dispenser() {
		sugarCount = cupCount = creamerCount = waterCount = 0;
	}
	
	public Dispenser(int sugarCount, int cupCount, int creamerCount, int waterCount) {
		this.sugarCount = sugarCount;
		this.cupCount = cupCount;
		this.creamerCount = creamerCount;
		this.waterCount = waterCount;
	}
	
	public void addSugar(int add) {
		sugarCount += add;
	}
	
	public void addCups(int add) {
		cupCount += add;
	}
	
	public void addCreamer(int add) {
		creamerCount += add;
	}
	
	public void addWater(int add) {
		waterCount += add;
	}
	
	public void decrementSugar() {
		if(sugarCount > 0)
			sugarCount -= 1;
	}
	
	public void decrementCups() {
		if(cupCount > 0)
			cupCount -= 1;
	}
	
	public void decrementCreamer() {
		if(creamerCount > 0)
			creamerCount -= 1;
	}
	
	public void decrementWater() {
		if(waterCount > 0)
			waterCount -= 1;
	}
	
	public int getCupCount() {
		return cupCount;
	}
	
	public int getSugarCount() {
		return sugarCount;
	}
	
	public int getCreamerCount() {
		return creamerCount;
	}
	
	public int getWaterCount() {
		return waterCount;
	}
	
	public void reset() {
		cupCount = creamerCount = waterCount = sugarCount = 0;
	}
}
