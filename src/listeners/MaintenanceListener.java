package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import controller.Dispenser;

public class MaintenanceListener implements ActionListener {
	JFrame parent;
	Dispenser d;
	JTextArea ta;
	
	public MaintenanceListener(JFrame main, Dispenser d, JTextArea ta) {
		parent = main;
		this.d = d;
		this.ta = ta;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			if(arg0.getActionCommand().equals("add cups")) {
				int add = promptInput("cups");
				d.addCups(add);
				ta.append(add + " cups added\n");
			}
			else if(arg0.getActionCommand().equals("add water")) {
				int add = promptInput("water");
				d.addWater(add);
				ta.append(add + " waters added\n");
			}
			else if(arg0.getActionCommand().equals("add sugar")) {
				int add = promptInput("sugar");
				d.addSugar(add);
				ta.append(add + " sugars added\n");
			}
			else if(arg0.getActionCommand().equals("add creamer")) {
				int add = promptInput("creamer");
				d.addCreamer(add);
				ta.append(add + " creamers added\n");
			}
		} catch (Exception e) {
			//triggered when cancel button is pressed.
			//just ignore
		}
	}
	
	private int promptInput(String ingredient) {
		String message = "How much " + ingredient + " to add";
		String input = JOptionPane.showInputDialog(parent, message);
		if(input.isEmpty())
			try {
				throw new Exception("Empty fields");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				promptInput(ingredient);
			}
		
		int add = Integer.parseInt(input);
		return add;
	}
	

}
