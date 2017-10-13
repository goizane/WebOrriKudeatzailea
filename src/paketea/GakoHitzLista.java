package paketea;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GakoHitzLista {
	
	// ATRIBUTUAK
	private ArrayList<GakoHitz> lista;
	private static GakoHitzLista nireGakoHitzLista = new GakoHitzLista();
	
	// ERAIKITZAILEA
	private GakoHitzLista() {
		this.lista = new ArrayList<GakoHitz>();
	}
	
	// SINGLETON
	public static GakoHitzLista getGakoHitzLista() {
		if (GakoHitzLista.nireGakoHitzLista==null) {
			GakoHitzLista.nireGakoHitzLista = new GakoHitzLista();
		}
		return GakoHitzLista.nireGakoHitzLista;
	}
	
	// METODOAK
	private Iterator<GakoHitz> getIterator() {
		//aurre:
		//post: gako hitzen zerrendaren iteradorea itzuli
		return this.lista.iterator();
	}
	
	public void kargatuHitzak(String nomF) {
		//aurre: gako hitzak dituen fitxategi baten izena 
		//post: gako hitzak GakoHitzLista-n gordeko ditu 
		try {
			Scanner sarrera = new Scanner(new FileReader(nomF));
			String hitza;
			while (sarrera.hasNext()) {
				hitza = sarrera.nextLine();
				GakoHitz gakoHitza = new GakoHitz(hitza);
				this.lista.add(gakoHitza);	
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
		// N = GakoHitz kopurua fitxategian --> KOSTUA = O(N)
	}
	
	public void webOrrienGakoHitzak(WebOrri weborri) {
		//aurre: web-orri bat
		//post: gako hitzak bilatuko ditu web-orriaren url-an, eta web-orriaren gakoen listan gehituko ditu 
		String url = weborri.getUrl();
		Iterator<GakoHitz> it = this.getIterator();
		while (it.hasNext()) {
			GakoHitz egungoa = it.next();
			String hitza = egungoa.getHitza();
			if (url.indexOf(hitza) >= 0){
				weborri.gakoHitzBerriaTxertatu(egungoa);
			}
		}
		// N = GakoHitz kopurua zerrendan
		// M = WebOrri zehatz honek duen GakoHitz kopurua bere listan
		// KOSTUA = O(NxM)
	}
}