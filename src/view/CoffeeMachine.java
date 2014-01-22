package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Cashbox;
import controller.Dispenser;
import controller.FrontPanel;
import controller.Mixer;
import listeners.ChangeListener;
import listeners.InsertCoinListener;
import listeners.MaintenanceListener;
import listeners.SelectFlavorListener;



public class CoffeeMachine {
	private static Dispenser dispenser = new Dispenser();
	private static Mixer mixer = new Mixer(dispenser);
	private static Cashbox cashbox = new Cashbox();
	private static FrontPanel frontPanel = new FrontPanel(cashbox, mixer);
	private static JTextArea textArea;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGui();
			}
		});
	}
	
	public static void createGui() {
		JFrame main = new JFrame("Coffee Machine");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(new Dimension(400, 500));
		main.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		main.setLocation(dim.width/2-main.getSize().width/2, dim.height/2-main.getSize().height/2);
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(370, 400));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		upperPanel.setPreferredSize(new Dimension(372, 402));
		upperPanel.add(textArea);
		
		buildMenu(main);
		
		JButton select = new JButton("Select");
		select.addActionListener(new SelectFlavorListener(main, frontPanel, mixer, textArea));
		
		JButton change = new JButton("Change");
		change.addActionListener(new ChangeListener(cashbox, textArea));
		
		JButton insert = new JButton("Insert");
		insert.addActionListener(new InsertCoinListener(main, cashbox, textArea));
		
		lowerPanel.add(select);
		lowerPanel.add(change);
		lowerPanel.add(insert);
		
		main.getContentPane().add(upperPanel, BorderLayout.NORTH);
		main.getContentPane().add(lowerPanel);
		main.setVisible(true);
	}

	private static void buildMenu(JFrame main) {
		JMenuBar mbar = new JMenuBar();
		JMenu menu = new JMenu("Maintenance");
		JMenuItem cups, water, sugar, creamer;
		
		cups = new JMenuItem("Add cups");
		cups.setActionCommand("add cups");
		cups.addActionListener(new MaintenanceListener(main, dispenser, textArea));
		menu.add(cups);
		
		water = new JMenuItem("Add water");
		water.setActionCommand("add water");
		water.addActionListener(new MaintenanceListener(main, dispenser, textArea));
		menu.add(water);
		
		sugar = new JMenuItem("Add sugar");
		sugar.setActionCommand("add sugar");
		sugar.addActionListener(new MaintenanceListener(main, dispenser, textArea));
		menu.add(sugar);
		
		creamer = new JMenuItem("Add creamer");
		creamer.setActionCommand("add creamer");
		creamer.addActionListener(new MaintenanceListener(main, dispenser, textArea));
		menu.add(creamer);
		
		mbar.add(menu);
		main.setJMenuBar(mbar);
	}
}
