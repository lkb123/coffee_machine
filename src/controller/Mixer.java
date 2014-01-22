package controller;

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
}
