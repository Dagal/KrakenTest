package be.dagal.java.kraken.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import be.dagal.java.kraken.data.AssetInfo;

public class CurrencySummary extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected AssetInfo assetInfo;
	protected String altName;
	
	protected boolean selected;

	public CurrencySummary() {}
	public CurrencySummary(AssetInfo ai){
		selected = false;
		
		assetInfo = ai;
		altName = ai.getAltname();
		add(new JLabel(altName));
	}
	
	public AssetInfo getAssetInfo() {
		return assetInfo;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public boolean setSelected(boolean sel) {
		selected=sel;
		return selected;
	}
}
