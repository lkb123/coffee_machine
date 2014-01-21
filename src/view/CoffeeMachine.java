package view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class CoffeeMachine {
	
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
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JTextArea textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(370, 400));
		textArea.setEditable(false);
		upperPanel.setPreferredSize(new Dimension(372, 402));
		upperPanel.add(textArea);
		
		JButton select = new JButton("Select");
		JButton change = new JButton("Change");
		JButton insert = new JButton("Insert");
		lowerPanel.add(select);
		lowerPanel.add(change);
		lowerPanel.add(insert);
		
		main.getContentPane().add(upperPanel, BorderLayout.NORTH);
		main.getContentPane().add(lowerPanel);
		main.setVisible(true);
	}
}
