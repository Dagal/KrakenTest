/**
 * 
 */
package be.dagal.java.kraken.data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.util.Pair;

/**
 * @author dejar
 *
 */
public class Client
{
	private String baseurl;
	private String version;
	
	private String apikey;
	private String secretkey;
	
	private int nonce;
	private int otp;
	
	private int callCounter;
	private int tiers;
	private int maxCallCounter;
	private int ledgerTradeHistoryCalls;
	private int placeCancelOrderCalls;
	private int otherApiCalls;
	
	private int timeBeforeDecreaseCallCounter;
	private int decreaseCallCounter;
	private int placeCancelOrderRate;
	
	private Map<String,AssetInfo> assetsInfo = null;
	private Map<String,AssetPair> assetPairs = null;
	
	public Client()
	{
		baseurl="https://api.kraken.com/";
		version="0";
		apikey="";
		secretkey="";
		Date date = new Date();
		int diff = (int) date.getTime();
		nonce=diff;
		otp=-1;
		callCounter=0;
		tiers=2;
		ledgerTradeHistoryCalls=2;
		placeCancelOrderCalls=0;
		otherApiCalls=1;
		switch(tiers) {
		case 2:
			maxCallCounter=15;
			timeBeforeDecreaseCallCounter=3000;
			decreaseCallCounter=1;
			break;
		default:
			break;
		}
		placeCancelOrderRate=1000;
	}
	
	public JSONObject publicRequest(String function) throws Exception
	{
		Set<Pair<String,String>> params = new HashSet<Pair<String,String>>();
		return publicRequest(function,params);
	}
	public JSONObject publicRequest(String function,Set<Pair<String,String>> params) throws Exception
	{
		URL url = new URL(baseurl + version + "/public/" + function);
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		
		Iterator<Pair<String,String>> it = params.iterator();
		
		String urlParameters = "";
		
		while (it.hasNext()) {
			Pair<String,String> pair = it.next();
			urlParameters += pair.getKey() + "=" + pair.getValue() + "&";
		}

		// Send post request
		
		conn.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		int responseCode = conn.getResponseCode();
		
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		StringBuilder sb = new StringBuilder();
		for (int c;(c=in.read())>=0;)
			sb.append((char)c);
		String response = sb.toString();
		
		JSONObject myResponse = new JSONObject(response.toString());
		
		if (myResponse.has("result")) {
			return myResponse.getJSONObject("result");			
		}
		else {
			System.out.println("Il y a une erreur de requête: " + function);
			return new JSONObject();
		}
	}
	
	public int getServerTime() throws Exception
	{
		JSONObject myResponse = publicRequest("Time");			

		int time64 = myResponse.getInt("unixtime");
		
		return time64;
	}
	
	public Map<String,AssetInfo> getAssetInfo() throws Exception
	{
		// Get asset info
		JSONObject myResult = publicRequest("Assets");
		System.out.println(myResult.toString());
		Set<java.lang.String> mySet = myResult.keySet();
		
		assetsInfo = new TreeMap<String,AssetInfo>();
		
		for (String assetName : mySet) {
			JSONObject myAsset = myResult.getJSONObject(assetName);
			assetsInfo.put(assetName, new AssetInfo(assetName,myAsset.getString("altname"),myAsset.getString("aclass"),myAsset.getInt("decimals"),myAsset.getInt("display_decimals")));
		}
		
		return assetsInfo;
	}
	
	public Map<String,AssetPair> getTradableAssetPairs() throws Exception
	{
		// Get tradable asset pairs
		if (assetsInfo == null) {
			getAssetInfo();
		}
		
		JSONObject myResult = publicRequest("AssetPairs");
		Set<String> mySet = myResult.keySet();
		
		assetPairs = new TreeMap<String,AssetPair>();
		
		for (String pairName : mySet) {
			JSONObject myPair = myResult.getJSONObject(pairName);
			JSONArray leverageBuyArray = myPair.getJSONArray("leverage_buy");
			Set<Integer> leverageBuy = new HashSet<Integer>();
			for (int i=0;i<leverageBuyArray.length();++i) {
				leverageBuy.add(leverageBuyArray.getInt(i));
			}

			JSONArray leverageSellArray = myPair.getJSONArray("leverage_sell");
			Set<Integer> leverageSell = new HashSet<Integer>();
			for (int i=0;i<leverageSellArray.length();++i) {
				leverageSell.add(leverageSellArray.getInt(i));
			}
			
			JSONArray feesArray = myPair.getJSONArray("fees");
			Set<Pair<Integer,Double>> fees = new HashSet<Pair<Integer,Double>>();
			for (int i=0;i<feesArray.length();++i) {
				JSONArray fee = feesArray.getJSONArray(i);
				fees.add(new Pair<>(fee.getInt(0),fee.getDouble(1)));
			}
			
			Set<Pair<Integer,Double>> feesMaker = new HashSet<Pair<Integer,Double>>();
			if (myPair.has("fees_maker")) {
				JSONArray feesMakerArray = myPair.getJSONArray("fees_maker");
				for (int i=0;i<feesMakerArray.length();++i) {
					JSONArray fee = feesMakerArray.getJSONArray(i);
					fees.add(new Pair<>(fee.getInt(0),fee.getDouble(1)));
				}
			}

			assetPairs.put(pairName,
					new AssetPair(
					pairName,
					myPair.getString("altname"),
					assetsInfo.get(myPair.getString("base")),
					assetsInfo.get(myPair.getString("quote")),
					myPair.getString("lot"),
					myPair.getInt("pair_decimals"),
					myPair.getInt("lot_decimals"),
					myPair.getInt("lot_multiplier"),
					leverageBuy,
					leverageSell,
					fees,
					feesMaker,
					myPair.getString("fee_volume_currency"),
					myPair.getInt("margin_call"),
					myPair.getInt("margin_stop")));
		}
		
		return assetPairs;
	}
	
	void getOHLCData()
	{
		
	}
	
	void getOrderBook()
	{
		
	}
	
	void getRecentTrade()
	{
		
	}
	
	void getRecentSpreadData()
	{
		
	}
	
	void privateRequest()
	{
		
	}
	
	void getAccountBalance()
	{
		
	}
	
	void getTradeBalance()
	{
		
	}
	
	void getOpenOrders()
	{
		
	}
	
	void getClosedOrders()
	{
		
	}
	
	void QueryOrdersInfo()
	{
		
	}
	
	void getTradesHistory()
	{
		
	}
	
	void queryTradesInfo()
	{
		
	}
	
	void getOpenPositions()
	{
		
	}
	
	void getLedgersInfo()
	{
		
	}
	
	void queryLedgers()
	{
		
	}
	
	void getTradeVolume()
	{
		
	}
	
	void addStandardOrder()
	{
		
	}
	
	void cancelOpenOrder()
	{
		
	}
	
	void getDepositMethods()
	{
		
	}
	
	void getDepositAddresses()
	{
		
	}
	
	void getStatusOfRecentDeposits()
	{
		
	}
	
	void getWithdrawalInformation()
	{
		
	}
	
	void withdrawFunds()
	{
		
	}
	
	void getStatusOfRecentWithdrawals()
	{
		
	}
	
	void requestWithdrawalCancelation()
	{
		
	}
}
