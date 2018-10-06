package be.dagal.java.kraken.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import be.dagal.java.kraken.data.AssetInfo;
import be.dagal.java.kraken.data.AssetPair;

public class KrakenCurrenciesFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Set<AssetInfo> assetsInfo;
	Set<AssetPair> assetPairs;

	private BaseCurrencyComboBox baseCurrencyComboBox;
	private CurrencySummaryList currencySummaryList;
	private CurrencyDetail currencyDetail;
	
	//private JCombobox baseCurrencyComboBox;

	private Map<String, AssetInfo> assetsQuote;
	
	public KrakenCurrenciesFrame() {
		super();
		setTitle("Kraken Currencies");
		setSize(1024,768);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		assetsQuote = new TreeMap<String,AssetInfo>();
		assetsQuote.put("",new AssetInfo());

		AssetInfo currencyAAAA = new AssetInfo("aaaa","aaa","currency",1,2);
		AssetInfo currencyBBBB = new AssetInfo("bbbb","bbb","currency",3,4);
		DefaultListModel<CurrencySummary> assetInfoListModel = new DefaultListModel<CurrencySummary>();
		currencySummaryList = new CurrencySummaryList(assetInfoListModel);
		currencySummaryList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					final List<CurrencySummary> selectedValuesList = currencySummaryList.getSelectedValuesList();
					currencyDetail.setAssetInfo(selectedValuesList.get(0).getAssetInfo());
					currencyDetail.revalidate();
				}
			}
		});

		assetInfoListModel.addElement(new CurrencySummary(currencyAAAA));
		assetInfoListModel.addElement(new CurrencySummary(currencyBBBB));
		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel,BoxLayout.PAGE_AXIS));
		
		AssetInfo[] baseList = assetsQuote.values().toArray(new AssetInfo[0]);
		
		ComboBoxModel<AssetInfo> aModel = new DefaultComboBoxModel<AssetInfo>(baseList);
		baseCurrencyComboBox = new BaseCurrencyComboBox(aModel);
		westPanel.add(baseCurrencyComboBox);
		westPanel.add(currencySummaryList);
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(0,9999));
		westPanel.add(spacer);

		add(westPanel,BorderLayout.WEST);
		currencyDetail = new CurrencyDetail(currencyAAAA);
		add(currencyDetail,BorderLayout.CENTER);
	}
	
	public void init() {
		
	}
	
	public void setAssetsInfo(Set<AssetInfo> ai) {
		assetsInfo = ai;
	}
	public void setAssetPairs(Set<AssetPair> ap) {
		assetPairs = ap;
	}
	public void setBasesName(Map<String, AssetInfo> assetBases) {
		System.out.println("Réception d'une nouvelle liste : " + assetBases);
		this.assetsQuote = assetBases;
		AssetInfo[] baseList =  assetBases.values().toArray(new AssetInfo[0]);
		
		ComboBoxModel<AssetInfo> aModel = new DefaultComboBoxModel<AssetInfo>(baseList);
		System.out.println("Le modèle de la comboBox a changé: " + aModel);
		baseCurrencyComboBox.setModel(aModel);
	}
}
