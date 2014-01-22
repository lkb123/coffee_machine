package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Cashbox;
import controller.Dispenser;
import controller.FrontPanel;
import controller.Mixer;
import listeners.ChangeListener;
import listeners.InsertCoinListener;
import listeners.SelectFlavorListener;



public class CoffeeMachine {
	static Dispenser dispenser = new Dispenser();
	static Mixer mixer = new Mixer(dispenser);
	static Cashbox cashbox = new Cashbox();
	static FrontPanel frontPanel = new FrontPanel(cashbox, mixer);
	
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
		JTextArea textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(370, 400));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		upperPanel.setPreferredSize(new Dimension(372, 402));
		upperPanel.add(textArea);
		
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
}
