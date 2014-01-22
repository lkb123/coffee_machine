package listeners;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.FrontPanel;
import controller.Mixer;
import exceptions.FlavorNotAvailableException;
import exceptions.NoCreamerException;
import exceptions.NoCupException;
import exceptions.NoSelectedFlavorException;
import exceptions.NoSugarException;
import exceptions.NoWaterExcpetion;
import exceptions.NotEnoughMoneyException;

public class SelectFlavorListener implements ActionListener {
	private JFrame parent;
	private FrontPanel fp;
	private JTextArea ta;
	private JDialog d;
	private String flavor;
	private boolean sugar, creamer;
	private JCheckBox addSugar, addCreamer;
	
	public SelectFlavorListener(JFrame main, FrontPanel fp, Mixer m, JTextArea ta) {
		parent = main;
		this.fp = fp;
		this.ta = ta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		d = new JDialog(parent, "Select flavor");
		d.setSize(new Dimension(300, 150));
		d.setResizable(false);
		d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		d.setLocation(dim.width/2-d.getSize().width/2, dim.height/2-d.getSize().height/2);
		
		JPanel flavorPane = new JPanel();
		flavorPane.setSize(new Dimension(290, 40));
		JPanel additionalPane = new JPanel();
		additionalPane.setSize(new Dimension(290, 40));
		JPanel buttonPane = new JPanel();
		buttonPane.setSize(new Dimension(290, 40));
		
		buildComboBox(flavorPane);
		buildCheckBox(additionalPane);
		buildButtons(buttonPane);
		
		d.getContentPane().setLayout(new BoxLayout(d.getContentPane(), BoxLayout.PAGE_AXIS));
		d.getContentPane().add(flavorPane);
		d.getContentPane().add(additionalPane);
		d.getContentPane().add(buttonPane);
		d.setVisible(true);
	}

	private String[] getOptionFlavors() {
		String[] items = new String[fp.getMenu().size() + 1];
		int i = 0;
		for(Entry<String, Integer> entry : fp.getMenu().entrySet()) {
			String item = entry.getKey();
			items[i++] = item;
		}
		items[i] = "";
		return items;
	}
	
	private void buildButtons(JPanel buttonPane) {
		JButton ok, cancel;
		ok = new JButton("Ok");
		ok.setActionCommand("ok");
		ok.addActionListener(new ButtonListener());
		cancel = new JButton("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(new ButtonListener());
		buttonPane.add(ok);
		buttonPane.add(cancel);
	}
	
	private void buildComboBox(JPanel flavorPane) {
		JComboBox<String> comboBox = new JComboBox<String>(getOptionFlavors());
		comboBox.setSelectedIndex(fp.getMenu().size());
		comboBox.addActionListener(new ComboBoxListener());
		flavorPane.add(comboBox);
	}
	
	
	private void buildCheckBox(JPanel additionalPane) {
		addSugar = new JCheckBox("Add sugar");
		//addSugar.setActionCommand("add sugar");
		addSugar.setSelected(false);
		addSugar.addItemListener(new CheckBoxListener());
		addCreamer = new JCheckBox("Add creamer");
		//addCreamer.setActionCommand("add creamer");;
		addCreamer.setSelected(false);
		addCreamer.addItemListener(new CheckBoxListener());
		
		additionalPane.add(addSugar);
		additionalPane.add(addCreamer);
	}

	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("ok")) {
				try {
					fp.select(flavor);
					if(creamer)
						fp.checkAddCreamer();
					if(sugar)
						fp.checkAddSugar();
					fp.serve();
					ta.append("Done serving. Thank you!\n");
					d.dispose();
				} catch (NotEnoughMoneyException | NoSelectedFlavorException
						| NoCupException | NoSugarException | NoWaterExcpetion
						| NoCreamerException | FlavorNotAvailableException e1) {
					JOptionPane.showMessageDialog(d, e1.getMessage());
				}
			}
			if(e.getActionCommand().equals("cancel")) {
				d.dispose();
			}	
		}
	}
	
	private class ComboBoxListener implements ActionListener {
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			flavor = (String) cb.getSelectedItem();
		}
	}
	
	private class CheckBoxListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == addSugar)
				sugar = e.getStateChange() == ItemEvent.SELECTED ? true : false;
			if(e.getSource() == addCreamer)
				creamer = e.getStateChange() == ItemEvent.SELECTED ? true : false;
		}
	}

}
