package be.dagal.java.kraken.ui;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import be.dagal.java.kraken.data.AssetInfo;

public class BaseCurrencyComboBox extends JComboBox<AssetInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseCurrencyComboBox(ComboBoxModel<AssetInfo> aModel) {
		super(aModel);
		setRenderer(new BaseCurrencyRenderer());
		setEditor(new BaseCurrencyEditor());
		// TODO Auto-generated constructor stub
	}
}
