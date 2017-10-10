package paketea;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class WebOrriLista {
	
	// ATRIBUTUAK
	private ArrayList<WebOrri> lista;
	private static WebOrriLista nireWebOrriLista = new WebOrriLista();
	
	// ERAIKITZAILEA
	private WebOrriLista() {
		this.lista = new ArrayList<WebOrri>();
	}
	
	// SINGLETON
	public static WebOrriLista getWebOrriLista() {
		if (WebOrriLista.nireWebOrriLista==null) {
			WebOrriLista.nireWebOrriLista = new WebOrriLista();
		}
		return WebOrriLista.nireWebOrriLista;
	}
	
	// METODOAK
	public void webOrriBerriaTxertatu(String url) {
		int indizea = this.lista.size();
		WebOrri weborria = new WebOrri(url, indizea);
		this.lista.add(weborria);
	}
	
	public void webOrriaGehitu(WebOrri weborria) {
		this.lista.add(weborria);
	}
	
	public void kargatuURL(String nomF) {
		//aurre: web orrien url eta indizeak dituen fitxategia
		//post: url eta indizeak listan sartu 
		try {
			Scanner sarrera = new Scanner(new FileReader(nomF));
			String lerroa;
			String hutsunea = " ";
			while (sarrera.hasNext()) {
				lerroa = sarrera.nextLine();
				String[] hitzak = lerroa.split(hutsunea);	//array bat sortuko da url[0] eta indizearekin[1]
				String url = hitzak[0];
				int indizea = Integer.parseInt(hitzak[1]);
				lista.add(new WebOrri(url, indizea));	//lista beteko da web-orrien url eta indizeekin	

				/*
				lerroa = sarrera.nextLine();
				url = lerroa.split("\\s+")[0];
				indizea = lerroa.split("\\s+")[1];
				weborria = new WebOrri(url, indizea);
				this.webOrriBerriaTxertatu(weborria);
				 */
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
	}
	
	public void kargatuEstekak(String nomF) {
		//aurre: web-orri bakoitzaren estekak dituen fitxategia 
		//port: web-orri bakoitzean esteken zerrenda bat beteko da
		try {
			Scanner sarrera = new Scanner(new FileReader(nomF));
			String lerroa;
			String hutsunea = " ";
			while (sarrera.hasNext()) {
				lerroa = sarrera.nextLine();
				String[] indizeak = lerroa.split(hutsunea);	//array bat sortuko da web-orriaren indizearekin[0] eta estekaren indizearekin[1]
				int webOrriarenInd = Integer.parseInt(indizeak[0]);
				int estekarenInd = Integer.parseInt(indizeak[1]);

				WebOrri webOrri = this.webOrriaBilatuINDIZEA(webOrriarenInd);	//webOrria bilatzen du 
				/*
				webOrri.estekaGehitu(estekarenInd);
				*/
				WebOrri webOrriEsteka = this.webOrriaBilatuINDIZEA(estekarenInd);
				webOrri.estekaGehitu(webOrriEsteka);
				


			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
	}
	
	private Iterator<WebOrri> getIterator() {
		return this.lista.iterator();
	}

	public WebOrri webOrriaBilatuURL(String pUrl) {
		//aurre: web orria baten url-a parametro bezala
		//post: url hori duen web orria itzultzen du (baldin badago)
		boolean topatua=false;
		WebOrri egungoa=null;
		Iterator<WebOrri> it = this.getIterator();
		while (it.hasNext() && !topatua){
			egungoa=it.next();
			if (egungoa.getUrl().equals(pUrl)){
				topatua=true;
			}
		}
		return egungoa;
	}

	public WebOrri webOrriaBilatuINDIZEA(int pIndizea){
		//aurre: web orria baten indizea parametro bezala
		//post: indize hori duen web orria itzultzen du (baldin badago)
		boolean topatua=false;
		WebOrri egungoa=null;
		Iterator<WebOrri> it = this.getIterator();
		while (it.hasNext() && !topatua){
			egungoa=it.next();
			if (egungoa.getIndizea()==pIndizea){
				topatua=true;
			}
		}
		return egungoa;
	}
	
	public boolean webOrriaBilatu(WebOrri weborria) {
		//aurre: Web-orri oso bat parametro bezala
		//post: Web-orri hori listan baldin badago,
		//      "true" bueltatzen du. Bestela, "false".
		return this.lista.contains(weborria);
	}
	
	public void gakoHitzakTxertatu(String helbidea) {
		WebOrri egungoa = null;
		Iterator<WebOrri> it = this.getIterator();
		GakoHitzLista gakoLista = null;
		gakoLista = GakoHitzLista.getGakoHitzLista();
		while (it.hasNext()) {
			egungoa = it.next();
			gakoLista.kargatuHitzak(helbidea, egungoa);
		}
	}
	
	// Web-orrien zerrenda fitxategitan gorde
	public void webOrriListaGorde() throws IOException {
		FileWriter fw = new FileWriter("src/fitxategiak/berriaindex.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i=0; i<this.lista.size(); i++) {
			WebOrri weborria = this.lista.get(i);
			bw.write(weborria.getUrl() + " " + weborria.getIndizea());
			bw.newLine();
		}
		bw.close();
	}
	
	// Web-orrien zerrenda alfabetikoki ordenatu (QuickSort)
	public void webOrriListaOrdenatu(ArrayList<WebOrri> weborriLista) {
		webOrriListaOrdenatu(weborriLista, 0, weborriLista.size()-1);
	}
	
	private void webOrriListaOrdenatu(ArrayList<WebOrri> weborriLista, int hasiera, int bukaera) {
		if (bukaera - hasiera > 0) {
			int ind = zatiketa(weborriLista, hasiera, bukaera);
			webOrriListaOrdenatu(weborriLista, hasiera, ind - 1);
			webOrriListaOrdenatu(weborriLista, ind + 1, bukaera);
		}
	}
	
	private int zatiketa(ArrayList<WebOrri> weborriLista, int i, int f) {
		// Lista ordenaturik datorrenez, QuickSort motelago bihurtzen da.
		// Hori ekiditeko, hasierako indizea (i) trukatu dezakegu beste
		// edozein posizioko (random) indize batekin, SWAP eginez.
		int randomIndizea = ThreadLocalRandom.current().nextInt(i, f + 1);
		Collections.swap(weborriLista, i, randomIndizea);
		
		WebOrri weborria = weborriLista.get(i);
		int ezker = i;
		int eskuin = f;
		while (ezker < eskuin) {
			while (weborriLista.get(ezker).compareTo(weborria) <= 0 && ezker < eskuin)
				ezker++;
			while (weborriLista.get(eskuin).compareTo(weborria) > 0)
				eskuin--;

			if (ezker < eskuin)
				Collections.swap(weborriLista, ezker, eskuin);
		}
		weborriLista.set(i, weborriLista.get(eskuin));
		weborriLista.set(eskuin, weborria);
		return eskuin;
	}


	//**********METODO HAUEK PROBAK EGITEKO DIRA**********
	
	public void inprimatuEstekak(int pInd){
		WebOrri w = this.webOrriaBilatuINDIZEA(pInd);
		w.inprimatuEstekak();
	}
}