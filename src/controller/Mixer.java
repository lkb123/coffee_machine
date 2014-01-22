package controller;

import exceptions.NoCreamerException;
import exceptions.NoCupException;
import exceptions.NoSugarException;
import exceptions.NoWaterExcpetion;

public class Mixer {
	
	private Dispenser dispenser;
	
	public Mixer() {
		dispenser = null;
	}
	
	public Mixer(Dispenser dispenser) {
		this.dispenser = dispenser;
	}
	
	@Override
	public Mixer clone() {
		return this.clone();
	}

	public void dispense(String flavor, boolean sugar, boolean creamer) throws NoCupException, NoSugarException, NoWaterExcpetion, NoCreamerException {
		checkCreamer();
		checkCups();
		checkSugar();
		checkWater();
		
		if(creamer)
			dispenser.decrementCreamer();
		if(sugar)
			dispenser.decrementSugar();
		
		dispenser.decrementCups();
		dispenser.decrementWater();
	}
	
	private void checkCreamer() throws NoCreamerException {
		if(dispenser.getCreamerCount() == 0)
			throw new NoCreamerException("No more creamer");
	}
	
	private void checkCups() throws NoCupException {
		if(dispenser.getCupCount() == 0)
			throw new NoCupException("No more cups");
	}
	
	private void checkSugar() throws NoSugarException {
		if(dispenser.getSugarCount() == 0)
			throw new NoSugarException("No more sugar");
	}
	
	private void checkWater() throws NoWaterExcpetion {
		if(dispenser.getWaterCount() == 0)
			throw new NoWaterExcpetion("No more water");
	}
	
}
