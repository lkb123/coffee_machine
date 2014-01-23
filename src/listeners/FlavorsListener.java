package listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.FrontPanel;

public class FlavorsListener implements ActionListener {

	private JFrame parent;
	private FrontPanel fp;
	private JTextArea ta;
	private JTextField flavor, price;
	private JButton ok, cancel;
	private JDialog d;
	
	public FlavorsListener(JFrame main, FrontPanel fp, JTextArea ta) {
		parent = main;
		this.fp = fp;
		this.ta = ta;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("add flavor")) {
			handleAddFlavor();
		}
		else if(arg0.getActionCommand().equals("remove flavor")) {
			handleRemoveFlavor();
		}
	}

	private void handleRemoveFlavor() {
		
	}

	private void handleAddFlavor() {
		d = new JDialog(parent, "Add Flavor");
		d.setSize(new Dimension(290, 140));
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel form, button;
		JLabel f, p;
		form = new JPanel();
		form.setSize(new Dimension(290, 80));
		button = new JPanel();
		button.setSize(new Dimension(290, 15));
		
		f = new JLabel("Flavor: \n");
		flavor = new JTextField(20);
		//float xAlign = flavor.getAlignmentX();
		f.setLabelFor(flavor);
		form.add(f);
		form.add(flavor);
		
		p = new JLabel("Price: \n");
		price = new JTextField(20);
		p.setLabelFor(price);
		//price.setAlignmentX(xAlign);
		form.add(p);
		form.add(price);
		
		ok = new JButton("Ok");
		ok.addActionListener(new ButtonListener());
		button.add(ok);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ButtonListener());
		button.add(cancel);
		
		d.getContentPane().setLayout(new BoxLayout(d.getContentPane(), BoxLayout.PAGE_AXIS));
		d.getContentPane().add(form);
		d.getContentPane().add(button);
		d.setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == ok) {
				String newFlavor = flavor.getText();
				int newPrice = Integer.parseInt(price.getText());
				fp.addMenu(newFlavor, newPrice);
				ta.append(newFlavor + " added with and costs " + newPrice + "\n");
				d.dispose();
			}
			else if(arg0.getSource() == cancel) {
				d.dispose();
			}
			
		}
	}

}
