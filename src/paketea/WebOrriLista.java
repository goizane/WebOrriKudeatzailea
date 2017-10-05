package paketea;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

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
	public void webOrriBerriaTxertatu(WebOrri weborria) {
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
	public void webOrriListaGorde() {
		//TODO
		// Kargatu ditugun webOrrien lista eta esteka lista beste
		// fitxategi berri batzuetan gorde
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
		// edozein posizioko indize batekin, SWAP eginez.
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