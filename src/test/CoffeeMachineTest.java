package test;

import static org.junit.Assert.*;

import java.security.KeyException;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.Cashbox;
import controller.FrontPanel;
import controller.Mixer;
import exceptions.FlavorNotAvailableException;

public class CoffeeMachineTest {
	
	private static Cashbox c;
	private static Mixer m;
	private static FrontPanel fp;
	
	@BeforeClass
	public static void init() throws FlavorNotAvailableException {
		c = new Cashbox();
		m = new Mixer();
		fp = new FrontPanel(c, m);
		fp.addMenu("hot choco", new Integer(15));
		fp.select("hot choco");
	}
	@Test
	public void cashboxTest() {
		c.insert(5);
		c.insert(10);
		assertEquals(15, c.getCredit());
		c.consume(5);
		assertEquals(10, c.getCredit());
		assertEquals(10, c.change());
		assertEquals(0, c.getCredit());	//cash box is zero after getting the change
	}
	
	@Test
	public void getMenuTest() {
		assertEquals(15, fp.getPrice("hot choco"));
		assertEquals(35, fp.getPrice("coffee"));
	}
	
	@Test
	public void flavorNotAvailable() throws FlavorNotAvailableException {
		try {
			fp.select("bavarian coffe");
			fail("The flavor selected is not available and must fail");
		} catch(FlavorNotAvailableException e) {
			assertEquals("The flavor selected is not available", e.getMessage());
		}
	}
	
	@Test
	public void notEnoughMoneyTest() {
		
	}
	
	@Test
	public void enoughMoneyTest() {
		
	}
}
