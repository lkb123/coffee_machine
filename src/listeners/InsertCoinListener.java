package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import controller.Cashbox;

public class InsertCoinListener implements ActionListener {
	JFrame parent;
	Cashbox cb;
	JTextArea ta;
	
	public InsertCoinListener(JFrame main, Cashbox cb, JTextArea ta) {
		parent = main;
		this.cb = cb;
		this.ta = ta;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			String input = JOptionPane.showInputDialog(parent, "Enter amount to be inserted");
			int amount = Integer.parseInt(input);
			cb.insert(amount);
			ta.append(amount + " pesos inserted\n");
		} catch (Exception e) {
			//triggered when cancel button is pressed.
			//just ignore
		}
	}

}
