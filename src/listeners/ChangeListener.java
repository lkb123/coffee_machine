package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import controller.Cashbox;

public class ChangeListener implements ActionListener {
	Cashbox cb;
	JTextArea ta;
	
	public ChangeListener(Cashbox cb, JTextArea ta) {
		this.cb = cb;
		this.ta = ta;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ta.append(cb.change() + " pesos returned\n");
	}

}
