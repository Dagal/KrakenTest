package be.dagal.java.kraken.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import be.dagal.java.kraken.data.AssetInfo;

public class BaseCurrencyRenderer extends JPanel implements ListCellRenderer<AssetInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6068121794345595814L;

	private JLabel currencyName = new JLabel();
	private JLabel currencyAvailable = new JLabel("0");

	public BaseCurrencyRenderer() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.insets = new Insets(2,2,2,2);

		currencyName.setOpaque(true);
		currencyName.setHorizontalAlignment(JLabel.LEFT);

		add(currencyName,constraints);
		add(currencyAvailable,constraints);
		setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends AssetInfo> list, AssetInfo value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// Nom de l'unité en cours
		currencyName.setText(value.getAltname());;
		
		// Quantité de l'unité disponible
		// TODO Il faut ajouter un champ montant disponible dans la classe AssetInfo
		currencyAvailable.setText("0");
		
		if (isSelected) {
			currencyName.setBackground(Color.BLUE);
			currencyName.setForeground(Color.YELLOW);
		}
		else {
			currencyName.setForeground(Color.BLACK);
			currencyName.setBackground(Color.LIGHT_GRAY);
		}
		// TODO Auto-generated method stub
		return this;
	}

}
