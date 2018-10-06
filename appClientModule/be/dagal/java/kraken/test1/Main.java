package be.dagal.java.kraken.test1;
import javax.swing.JFrame;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.Reader;

import org.json.JSONObject;

import be.dagal.java.kraken.data.AssetInfo;
import be.dagal.java.kraken.data.AssetPair;
import be.dagal.java.kraken.data.Client;
import be.dagal.java.kraken.data.TickerInformation;
import be.dagal.java.kraken.ui.KrakenCurrenciesFrame;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {
	// Varibles Data
	private Client client;
	private Map<String,AssetInfo> assetsInfo;
	private Map<String,AssetPair> assetPairs;
	private Map<String,AssetInfo> assetBases;
	
	// Variables View
	private KrakenCurrenciesFrame kcf;
	
	// Variables Control
	
	public static void main(String[] args) throws Exception {
		System.out.println("Démarrage du programme principal!");
		
		// Création de l'instance principale
		Main mainProgram = new Main();

		System.out.println("Fin du programme principal!");
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();

		preInit();
		viewInit();
		controlInit();
		dataInit();
	}
	
	private void preInit() {
		assetsInfo = new TreeMap<String,AssetInfo>();
		assetPairs = new TreeMap<String,AssetPair>();
		assetBases = new TreeMap<String,AssetInfo>();
	}
	
	private void viewInit() {
		// TODO Affichage en fenêtre
		kcf = new KrakenCurrenciesFrame();
		kcf.setVisible(true);
	}
	
	private void controlInit() {
		// TODO signifier à la fenêtre principale des changements dans les données.
	}
	
	private void dataInit() {
		client = new Client();
		getAssetsInfo();
		// TODO Il faut envoyer les données à la partie graphique.
		
		// Tentative de récupération des paires d'échange ainsi que des unités de référence
		try {
			assetPairs = client.getTradableAssetPairs();
			Set<String> assetPairKeys = assetPairs.keySet();
			Iterator<String> assetPairIt = assetPairKeys.iterator();
			Set<TickerInformation> tickerInformations = new HashSet<TickerInformation>();
			while(assetPairIt.hasNext()) {
				AssetPair assetPair = assetPairs.get(assetPairIt.next());
				System.out.println(assetPair.getPairName());
				tickerInformations.add(new TickerInformation(client,assetPair));
				if (! assetBases.containsKey(assetPair.getQuoteAssetInfo().getName())) {
					assetBases.put(assetPair.getQuoteAssetInfo().getName(), assetPair.getQuoteAssetInfo());
					// TODO Il faut que ce soit la partie contrôle qui gère cette partie là et non une commande directe.
					System.out.println("Envoyer à kcf une nouvelle liste de base: " + assetBases.keySet());
					kcf.setBasesName(assetBases);
					System.out.println("Nouvelle base: " + assetPair.getQuoteAssetInfo().getName());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Il faut récupérer les données instantanées des différentes paires.
	}
	
	private Map<String,AssetInfo> getAssetsInfo(){
		if (assetsInfo == null) {
		// Tentative de récupération des informations des unités monétaires.
			try {
				assetsInfo = client.getAssetInfo();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return assetsInfo;
	}
}