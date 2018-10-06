package be.dagal.java.kraken.data;

import java.util.Set;

import javafx.util.Pair;

public class AssetPair {
	private String pairName;
	private String altName;
	private AssetInfo baseAssetInfo;
	private AssetInfo quoteAssetInfo;
	private String lot;
	private int pairDecimals;
	private int lotDecimals;
	private int lotMultiplier;
	private Set<Integer> leverageBuy;
	private Set<Integer> leverageSell;
	private Set<Pair<Integer,Double>> fees;
	private Set<Pair<Integer,Double>> feesMaker;
	private String feeVolumeCurrency;
	private int marginCall;
	private int marginStop;
	public AssetPair(String pairName,
			String altName,
			AssetInfo baseAssetInfo,
			AssetInfo quoteAssetInfo,
			String lot,
			int pairDecimals,
			int lotDecimals,
			int lotMultiplier,
			Set<Integer> leverageBuy,
			Set<Integer> leverageSell,
			Set<Pair<Integer, Double>> fees,
			Set<Pair<Integer, Double>> feesMaker,
			String feeVolumeCurrency,
			int marginCall,
			int marginStop) {
		super();
		this.pairName = pairName;
		this.altName = altName;
		this.baseAssetInfo = baseAssetInfo;
		this.quoteAssetInfo = quoteAssetInfo;
		this.lot = lot;
		this.pairDecimals = pairDecimals;
		this.lotDecimals = lotDecimals;
		this.lotMultiplier = lotMultiplier;
		this.leverageBuy = leverageBuy;
		this.leverageSell = leverageSell;
		this.fees = fees;
		this.feesMaker = feesMaker;
		this.feeVolumeCurrency = feeVolumeCurrency;
		this.marginCall = marginCall;
		this.marginStop = marginStop;
	}
	@Override
	public String toString() {
		return "AssetPair [pairName=" + pairName + ", altName=" + altName + ", aclassBase=" + baseAssetInfo.getAclass() + ", baseId="
				+ baseAssetInfo.getAclass() + ", aclassQuote=" + quoteAssetInfo.getAclass() + ", quoteId=" + quoteAssetInfo.getName() + ", lot=" + lot + ", pairDecimals="
				+ pairDecimals + ", lotDecimals=" + lotDecimals + ", lotMultiplier=" + lotMultiplier + ", leverageBuy="
				+ leverageBuy + ", leverageSell=" + leverageSell + ", fees=" + fees + ", feesMaker=" + feesMaker
				+ ", feeVolumeCurrency=" + feeVolumeCurrency + ", marginCall=" + marginCall + ", marginStop="
				+ marginStop + "]";
	}
	public String getPairName() {
		return pairName;
	}
	public String getAltName() {
		return altName;
	}
	public AssetInfo getBaseAssetInfo() {
		return baseAssetInfo;
	}
	public AssetInfo getQuoteAssetInfo() {
		return quoteAssetInfo;
	}
	public String getLot() {
		return lot;
	}
	public int getPairDecimals() {
		return pairDecimals;
	}
	public int getLotDecimals() {
		return lotDecimals;
	}
	public int getLotMultiplier() {
		return lotMultiplier;
	}
	public String getFeeVolumeCurrency() {
		return feeVolumeCurrency;
	}
	public int getMarginCall() {
		return marginCall;
	}
	public int getMarginStop() {
		return marginStop;
	}
	public Set<Integer> getLeverageBuy() {
		return leverageBuy;
	}
	public Set<Integer> getLeverageSell() {
		return leverageSell;
	}
	public Set<Pair<Integer, Double>> getFees() {
		return fees;
	}
	public Set<Pair<Integer, Double>> getFeesMaker() {
		return feesMaker;
	}
}
