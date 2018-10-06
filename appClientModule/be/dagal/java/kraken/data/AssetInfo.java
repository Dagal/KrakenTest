package be.dagal.java.kraken.data;

import javax.swing.event.EventListenerList;

import be.dagal.java.kraken.control.AssetInfoListener;

public class AssetInfo {
	private String name;
	private String altname;
	private String aclass;
	private int decimals;
	private int displayDecimals;
	
	private final EventListenerList listeners = new EventListenerList();
	
	public AssetInfo() {
		
	}
	public AssetInfo(String name, String altname, String aclass, int decimals, int displayDecimals) {
		this.name=name;
		this.altname=altname;
		this.aclass=aclass;
		this.decimals=decimals;
		this.displayDecimals=displayDecimals;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAltname() {
		return altname;
	}
	
	public String getAclass() {
		return aclass;
	}
	
	public int getDecimals() {
		return decimals;
	}
	
	public int getDisplayDecimals() {
		return displayDecimals;
	}
	
	public void set(String name, String altname, String aclass, int decimals, int displayDecimals) {
		this.name = name;
		this.altname = altname;
		this.aclass = aclass;
		this.decimals = decimals;
		this.displayDecimals = displayDecimals;
		
		fireValuesChanged();
	}
	
	public void addAssetInfoListener(AssetInfoListener listener) {
		listeners.add(AssetInfoListener.class, listener);
	}
	public void removeAssetInfoListener(AssetInfoListener listener) {
		listeners.remove(AssetInfoListener.class, listener);
	}
	public AssetInfoListener[] getAssetInfoListener() {
		return listeners.getListeners(AssetInfoListener.class);
	}
	
	protected void fireValuesChanged() {
		for (AssetInfoListener listener : getAssetInfoListener()) {
			listener.valueChanged();
		}
	}
}
