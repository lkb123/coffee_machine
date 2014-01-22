package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Dispenser;
import controller.Mixer;
import exceptions.NoCreamerException;
import exceptions.NoCupException;
import exceptions.NoSugarException;
import exceptions.NoWaterExcpetion;

public class MixerTest {
	
	private static Dispenser d;
	private static Mixer m;
	
	@BeforeClass
	public static void init() {
		d = new Dispenser();
		m = new Mixer(d);
	}
	
	@Test
	public void dispenseCoffeeWithSugarWithCreamer() {
		addIngredients();
		try {
			m.dispense("coffee", true, true);
			assertEquals(0, d.getCreamerCount());
			assertEquals(0, d.getCupCount());
			assertEquals(0, d.getWaterCount());
			assertEquals(0, d.getSugarCount());
		} catch (Exception e) {
			fail("Catched an excpetion wherein no exception is expected");
		}
	}
	
	@Test
	public void dispenseCoffeeWithSugarNoCreamer() {
		addIngredients();
		try {
			m.dispense("coffee", true, false);
			assertEquals(1, d.getCreamerCount());
			assertEquals(0, d.getCupCount());
			assertEquals(0, d.getWaterCount());
			assertEquals(0, d.getSugarCount());
		} catch (Exception e) {
			fail("Catched an excpetion wherein no exception is expected");
		}
	}
	
	@Test
	public void dispenseCoffeeNoSugarWithCreamer() {
		addIngredients();
		try {
			m.dispense("coffee", false, true);
			assertEquals(0, d.getCreamerCount());
			assertEquals(0, d.getCupCount());
			assertEquals(0, d.getWaterCount());
			assertEquals(1, d.getSugarCount());
		} catch (Exception e) {
			fail("Catched an excpetion wherein no exception is expected");
		}
	}
	
	@Test
	public void dispenseCoffeeNoSugarNoCreamer() {
		addIngredients();
		try {
			m.dispense("coffee", false, false);
			assertEquals(1, d.getCreamerCount());
			assertEquals(0, d.getCupCount());
			assertEquals(0, d.getWaterCount());
			assertEquals(1, d.getSugarCount());
		} catch (Exception e) {
			fail("Catched an excpetion wherein no exception is expected");
		}
	}
	
	@Test
	public void dispenseCoffeeOutOfSugar() throws NoCupException, NoWaterExcpetion, NoCreamerException {
		addAllNoSugar();
		try {
			m.dispense("coffee", true, true);
			fail("Dispenser was out of sugar and an exception was not thrown");
		} catch (NoSugarException e) {
			assertEquals("No more sugar", e.getMessage());
		}
	}
	
	@Test
	public void dispenseCoffeeOutOfWater() throws NoCupException, NoSugarException, NoCreamerException {
		addAllNoWater();
		try {
			m.dispense("coffee", true, true);
			fail("Dispenser was out of water and an exception was not thrown");
		} catch (NoWaterExcpetion e) {
			assertEquals("No more water", e.getMessage());
		}
	}
	
	@Test
	public void dispenseCoffeeOutOfCreamer() throws NoCupException, NoSugarException, NoWaterExcpetion {
		addAllNoCreamer();
		try {
			m.dispense("coffee", true, true);
			fail("Dispenser was out of creamer and an exception was not thrown");
		} catch (NoCreamerException e) {
			
		}
	}
	
	@Test
	public void dispenseCoffeeOutOfCups() throws NoSugarException, NoWaterExcpetion, NoCreamerException {
		addAllNoCups();
		try {
			m.dispense("coffee", true, true);
			fail("Dispenser was out of cups and an exception was not thrown");
		} catch (NoCupException e) {
			
		}
	}
	
	@After
	public void resetIngredients() {
		d.reset();
	}
	
	private void addIngredients() {
		d.addCreamer(1);
		d.addCups(1);
		d.addSugar(1);
		d.addWater(1);
	}
	
	private void addAllNoSugar() {
		d.addCreamer(1);
		d.addCups(1);
		d.addWater(1);
	}
	
	private void addAllNoCreamer() {
		d.addCups(1);
		d.addSugar(1);
		d.addWater(1);
	}
	
	private void addAllNoCups() {
		d.addCreamer(1);
		d.addSugar(1);
		d.addWater(1);
	}
	
	private void addAllNoWater() {
		d.addCreamer(1);
		d.addCups(1);
		d.addSugar(1);
	}
}
