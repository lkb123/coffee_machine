package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Cashbox;
import controller.FrontPanel;
import controller.Mixer;
import exceptions.FlavorNotAvailableException;
import exceptions.NoSelectedFlavorException;
import exceptions.NotEnoughMoneyException;

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
	public void flavorNotAvailable() {
		try {
			fp.select("bavarian coffee");
			fail("The flavor selected is not available and must fail");
		} catch(FlavorNotAvailableException e) {
			assertEquals("The flavor bavarian coffee is not available", e.getMessage());
		}
	}
	
	@Test
	public void serveWithNotEnoughMoneyTest() throws FlavorNotAvailableException, NoSelectedFlavorException {
		try {
			fp.select("hot choco");
			c.insert(5);
			fp.serve();
			fail("Not enough money inserted and must fail");
		} catch (NotEnoughMoneyException e) {
			assertEquals("Please enter 10 pesos more", e.getMessage());
		}
	}
	
	/*
	@Test
	public void serveWithoutSelectingFlavorTest() throws NotEnoughMoneyException {
		try {
			fp.serve();
			fail("Not yet selected any flavor and must fail");
		} catch(NoSelectedFlavorException e) {
			
		}
	}
	*/
	
	@Test
	public void serteWithEnoughMoney() throws FlavorNotAvailableException, NotEnoughMoneyException {
		fp.select("coffee");
		c.insert(40);
		fp.serve();
		assertEquals(5, c.getCredit());
	}
	
	@After
	public void cleanCashbox() {
		c.change();
		assertEquals(0, c.getCredit());
	}
}
