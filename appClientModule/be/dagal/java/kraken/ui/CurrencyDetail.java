package be.dagal.java.kraken.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.dagal.java.kraken.data.AssetInfo;

public class CurrencyDetail extends JPanel{
	private AssetInfo assetInfo;
	private JLabel currencyName;
	private JLabel altName;
	private JLabel className;
	private JLabel decimals;
	private JLabel displayDecimals;
	
	public CurrencyDetail(AssetInfo ai) {
		super();
		currencyName = new JLabel();
		add(currencyName);
		altName = new JLabel();
		add(altName);
		className = new JLabel();
		add(className);
		decimals = new JLabel();
		add(decimals);
		displayDecimals = new JLabel();
		add(displayDecimals);
		setAssetInfo(ai);
	}
	
	public void setAssetInfo(AssetInfo ai) {
		assetInfo=ai;
		
		currencyName.setText("Currency name: " + assetInfo.getName());
		altName.setText("Alternate name: " + assetInfo.getAltname());
		className.setText("Class: " + assetInfo.getAclass());
		decimals.setText("Decimals: " + assetInfo.getDecimals());
		displayDecimals.setText("Display Decimals: " + assetInfo.getDisplayDecimals());
	}
}
