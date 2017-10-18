package praktika1;

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
		//aurre: Web-orri berriaren url
		//post: web-orri barri bat txertatuko da listan
		if (this.webOrriaBilatuURL(url)==null){
			int indizea = this.lista.size();
			WebOrri weborria = new WebOrri(url, indizea);
			this.lista.add(weborria);
		}else{
			System.out.println("--> " + url + " web-orria, listan txertatuta dago jada");
		}
		// N = WebOrri kopurua zerrendan --> KOSTUA = O(N)
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
				this.lista.add(new WebOrri(url, indizea));	//lista beteko da web-orrien url eta indizeekin	
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
		// N = WebOrri kopurua fitxategian --> KOSTUA = O(N)
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
				WebOrri webOrriEsteka = this.webOrriaBilatuINDIZEA(estekarenInd);
				webOrri.estekaGehitu(webOrriEsteka);
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
		// N = WebOrri kopurua zerrendan
		// M = Esteka kopurua fitxategian
		// KOSTUA = O(NxM)
	}

	private Iterator<WebOrri> getIterator() {
		//aurre:
		//post: web-orriak zeharkatzeko iteradorea bueltatzen du
		return this.lista.iterator();
	}

	public WebOrri webOrriaBilatuURL(String pUrl) {
		//aurre: web orria baten url-a parametro bezala
		//post: url hori duen web orria itzultzen du (baldin badago)
		boolean topatua = false;
		WebOrri egungoa = null;
		WebOrri emaitza = null;
		Iterator<WebOrri> it = this.getIterator();
		while (it.hasNext() && !topatua){
			egungoa=it.next();
			if (egungoa.getUrl().equals(pUrl)){
				topatua=true;
				emaitza = egungoa;
			}
		}
		return emaitza;
		// N = WebOrri kopurua zerrendan --> KOSTUA = O(N)
	}

	public WebOrri webOrriaBilatuINDIZEA(int pIndizea){
		//aurre: web orria baten indizea parametro bezala
		//post: indize hori duen web orria itzultzen du (baldin badago)
		boolean topatua = false;
		WebOrri egungoa = null;
		WebOrri emaitza = null;
		Iterator<WebOrri> it = this.getIterator();
		while (it.hasNext() && !topatua){
			egungoa=it.next();
			if (egungoa.getIndizea()==pIndizea){
				topatua=true;
				emaitza = egungoa;
			}
		}
		return emaitza;
		// N = WebOrri kopurua zerrendan --> KOSTUA = O(N)
	}

	public boolean webOrriaBilatu(WebOrri weborria) {
		//aurre: Web-orri oso bat parametro bezala
		//post: Web-orri hori listan baldin badago,
		//      "true" bueltatzen du. Bestela, "false".
		return this.lista.contains(weborria);
		// N = WebOrri kopurua zerrendan --> KOSTUA = O(N)
	}

	public void gakoHitzakTxertatu() {
		//aurre:
		//post: web-orri bakoitzaren gako hitzak lista batean gordeko dira
		WebOrri egungoa = null;
		Iterator<WebOrri> it = this.getIterator();
		GakoHitzLista gakoLista = GakoHitzLista.getGakoHitzLista();
		while (it.hasNext()) {
			egungoa = it.next();
			gakoLista.webOrrienGakoHitzak(egungoa);  // Metodo honen kostua O(MxP) da
		}
		// N = WebOrri kopurua zerrendan
		// M = GakoHitz kopurua zerrendan
		// P = WebOrri zehatz batek duen GakoHitz kopurua bere listan
		// KOSTUA = O(NxMxP)
	}

	// Web-orrien zerrenda fitxategi berri batean gorde
	public void webOrriListaGorde() throws IOException {
		//aurre:
		//post: fitxategi berri bat sortuko dugu web-orri guztien url eta indizeekin
		FileWriter fw = new FileWriter("src/fitxategiak/berriaindex.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i=0; i<this.lista.size(); i++) {
			WebOrri weborria = this.lista.get(i);
			bw.write(weborria.getUrl() + " " + weborria.getIndizea());
			bw.newLine();
		}
		bw.close();
		// N = WebOrri kopurua zerrendan --> KOSTUA = O(N)
	}
	
	
	// Web-orriek estekatzen dituzten web-orriak fitxategi berri batean gorde
	public void estekaListaGorde() throws IOException {
		//aurre:
		//post: fitxategi berri bat sortuko dugu web-orri guztien estekekin
		FileWriter fw = new FileWriter("src/fitxategiak/berriapld-arc.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i=0; i<this.lista.size(); i++) {
			WebOrri weborria = this.lista.get(i);
			for (int j=0; j<weborria.getEstekak().size(); j++) {
				WebOrri esteka = weborria.getEstekak().get(j);
				bw.write(weborria.getIndizea() + " " + esteka.getIndizea());
				bw.newLine();
			}
		}
		bw.close();
		// N = WebOrri kopurua zerrendan
		// M = WebOrri bakoitzak duen esteka kopurua bere listan
		// KOSTUA = O(NxM)
	}


	public void webOrriListaOrdenatu(WebOrriLista weborriZerrenda) {
		//aurre:
		//post: Web-orrien zerrenda alfabetikoki ordenatu (QuickSort)
		ArrayList<WebOrri> weborriLista = weborriZerrenda.lista;
		webOrriListaOrdenatu(weborriLista, 0, weborriLista.size()-1);
		// N = WebOrri kopurua zerrendan --> KOSTUA = O(N logN) <-- QuickSort
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
		// N = WebOrri kopurua zerrendan
		// M = WebOrri bakoitzak duen esteka kopurua bere listan
		// KOSTUA = O(NxM)
	}

	public void inprimatuWebOrriak(){
		System.out.println(lista.size());
		Iterator<WebOrri> it = this.getIterator();
		WebOrri egungoa = null;
		while (it.hasNext()){
			egungoa = it.next();
			System.out.println(egungoa.getUrl() + " " + egungoa.getIndizea());

		}
		// N = WebOrri kopurua zerrendan --> KOSTUA = O(N)
	}
}