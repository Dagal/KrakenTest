package be.dagal.java.kraken.data;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.util.Pair;

public class TickerInformation {
	private String pairName;
	private double askPrice;
	private double askWholeLotVolume;
	private double askLotVolume;
	private double bidPrice;
	private double bidWholeLotVolume;
	private double bidLotVolume;
	private double lastTradePrice;
	private double lastTradeLotVolume;
	private double volumeToday;
	private double volumeLast24H;
	private double volumeWeightAverageToday;
	private double volumeWeightAverageLast24H;
	private double numberTradeToday;
	private double numberTradeLast24H;
	private double lowToday;
	private double lowLast24H;
	private double highToday;
	private double highLast24H;
	private double todayOpeningPrice;
	
	public TickerInformation() {
		
	}
	public TickerInformation(Client client, AssetPair assetPair) {
		JSONObject tickerInformationObject;
		pairName = assetPair.getPairName();
		if (pairName.endsWith(".d")) {
			pairName = pairName.substring(0, pairName.length()-2);
		}
		Pair<String,String> pair = new Pair<String,String>("pair",pairName);
		Set<Pair<String,String>> setPair = new HashSet<Pair<String,String>>();
		setPair.add(pair);
		try {
			JSONObject responseObject = client.publicRequest("Ticker",setPair);
			tickerInformationObject = responseObject.getJSONObject(pairName);
			System.out.println(tickerInformationObject.toString());
			JSONArray askArray = tickerInformationObject.getJSONArray("a");
			askPrice = askArray.getDouble(0);
			askWholeLotVolume = askArray.getDouble(1);
			askLotVolume = askArray.getDouble(2);
			JSONArray bidArray = tickerInformationObject.getJSONArray("b");
			bidPrice = bidArray.getDouble(0);
			bidWholeLotVolume = bidArray.getDouble(1);
			bidLotVolume = bidArray.getDouble(2);
			JSONArray lastTradeArray = tickerInformationObject.getJSONArray("c");
			lastTradePrice = lastTradeArray.getDouble(0);
			lastTradeLotVolume = lastTradeArray.getDouble(1);
			JSONArray volumeArray = tickerInformationObject.getJSONArray("v");
			volumeToday = volumeArray.getDouble(0);
			volumeLast24H = volumeArray.getDouble(1);
			JSONArray volumeWeightAverageArray = tickerInformationObject.getJSONArray("p");
			volumeWeightAverageToday = volumeWeightAverageArray.getDouble(0);
			volumeWeightAverageLast24H = volumeWeightAverageArray.getDouble(1);
			JSONArray numberTradeArray = tickerInformationObject.getJSONArray("t");
			numberTradeToday = numberTradeArray.getDouble(0);
			numberTradeLast24H = numberTradeArray.getDouble(1);
			JSONArray lowArray = tickerInformationObject.getJSONArray("l");
			lowToday = lowArray.getDouble(0);
			lowLast24H = lowArray.getDouble(1);
			JSONArray highArray = tickerInformationObject.getJSONArray("h");
			highToday = highArray.getDouble(0);
			highLast24H = highArray.getDouble(1);
			todayOpeningPrice = tickerInformationObject.getDouble("o");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public TickerInformation(String pairName, double askPrice, double askWholeLotVolume, double askLotVolume,
			double bidPrice, double bidWholeLotVolume, double bidLotVolume, double lastTradePrice,
			double lastTradeLotVolume, double volumeToday, double volumeLast24H, double volumeWeightAverageToday,
			double volumeWeightAverageLast24H, double numberTradeToday, double numberTradeLast24H, double lowToday,
			double lowLast24H, double highToday, double highLast24H, double todayOpeningPrice) {
		super();
		this.pairName = pairName;
		this.askPrice = askPrice;
		this.askWholeLotVolume = askWholeLotVolume;
		this.askLotVolume = askLotVolume;
		this.bidPrice = bidPrice;
		this.bidWholeLotVolume = bidWholeLotVolume;
		this.bidLotVolume = bidLotVolume;
		this.lastTradePrice = lastTradePrice;
		this.lastTradeLotVolume = lastTradeLotVolume;
		this.volumeToday = volumeToday;
		this.volumeLast24H = volumeLast24H;
		this.volumeWeightAverageToday = volumeWeightAverageToday;
		this.volumeWeightAverageLast24H = volumeWeightAverageLast24H;
		this.numberTradeToday = numberTradeToday;
		this.numberTradeLast24H = numberTradeLast24H;
		this.lowToday = lowToday;
		this.lowLast24H = lowLast24H;
		this.highToday = highToday;
		this.highLast24H = highLast24H;
		this.todayOpeningPrice = todayOpeningPrice;
	}
	
	public String getPairName() {
		return pairName;
	}
	public double getAskPrice() {
		return askPrice;
	}
	public double getAskWholeLotVolume() {
		return askWholeLotVolume;
	}
	public double getAskLotVolume() {
		return askLotVolume;
	}
	public double getBidPrice() {
		return bidPrice;
	}
	public double getBidWholeLotVolume() {
		return bidWholeLotVolume;
	}
	public double getBidLotVolume() {
		return bidLotVolume;
	}
	public double getLastTradePrice() {
		return lastTradePrice;
	}
	public double getLastTradeLotVolume() {
		return lastTradeLotVolume;
	}
	public double getVolumeToday() {
		return volumeToday;
	}
	public double getVolumeLast24H() {
		return volumeLast24H;
	}
	public double getVolumeWeightAverageToday() {
		return volumeWeightAverageToday;
	}
	public double getVolumeWeightAverageLast24H() {
		return volumeWeightAverageLast24H;
	}
	public double getNumberTradeToday() {
		return numberTradeToday;
	}
	public double getNumberTradeLast24H() {
		return numberTradeLast24H;
	}
	public double getLowToday() {
		return lowToday;
	}
	public double getLowLast24H() {
		return lowLast24H;
	}
	public double getHighToday() {
		return highToday;
	}
	public double getHighLast24H() {
		return highLast24H;
	}
	public double getTodayOpeningPrice() {
		return todayOpeningPrice;
	}
	public void setPairName(String pairName) {
		this.pairName = pairName;
	}
	public void setAskPrice(double askPrice) {
		this.askPrice = askPrice;
	}
	public void setAskWholeLotVolume(double askWholeLotVolume) {
		this.askWholeLotVolume = askWholeLotVolume;
	}
	public void setAskLotVolume(double askLotVolume) {
		this.askLotVolume = askLotVolume;
	}
	public void setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public void setBidWholeLotVolume(double bidWholeLotVolume) {
		this.bidWholeLotVolume = bidWholeLotVolume;
	}
	public void setBidLotVolume(double bidLotVolume) {
		this.bidLotVolume = bidLotVolume;
	}
	public void setLastTradePrice(double lastTradePrice) {
		this.lastTradePrice = lastTradePrice;
	}
	public void setLastTradeLotVolume(double lastTradeLotVolume) {
		this.lastTradeLotVolume = lastTradeLotVolume;
	}
	public void setVolumeToday(double volumeToday) {
		this.volumeToday = volumeToday;
	}
	public void setVolumeLast24H(double volumeLast24H) {
		this.volumeLast24H = volumeLast24H;
	}
	public void setVolumeWeightAverageToday(double volumeWeightAverageToday) {
		this.volumeWeightAverageToday = volumeWeightAverageToday;
	}
	public void setVolumeWeightAverageLast24H(double volumeWeightAverageLast24H) {
		this.volumeWeightAverageLast24H = volumeWeightAverageLast24H;
	}
	public void setNumberTradeToday(double numberTradeToday) {
		this.numberTradeToday = numberTradeToday;
	}
	public void setNumberTradeLast24H(double numberTradeLast24H) {
		this.numberTradeLast24H = numberTradeLast24H;
	}
	public void setLowToday(double lowToday) {
		this.lowToday = lowToday;
	}
	public void setLowLast24H(double lowLast24H) {
		this.lowLast24H = lowLast24H;
	}
	public void setHighToday(double highToday) {
		this.highToday = highToday;
	}
	public void setHighLast24H(double highLast24H) {
		this.highLast24H = highLast24H;
	}
	public void setTodayOpeningPrice(double todayOpeningPrice) {
		this.todayOpeningPrice = todayOpeningPrice;
	}
	
	
}
