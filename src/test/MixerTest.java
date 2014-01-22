package test;

import org.junit.BeforeClass;

import controller.Dispenser;
import controller.Mixer;

public class MixerTest {
	
	private static Dispenser d;
	private static Mixer m;
	
	@BeforeClass
	public static void init() {
		d = new Dispenser();
		m = new Mixer(d);
	}
}
