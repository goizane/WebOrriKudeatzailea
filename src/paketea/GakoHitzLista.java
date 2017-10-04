package paketea;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	public void kargatuHitzak(String helbidea, WebOrri weborria) {
		try {
			Scanner sarrera = new Scanner(new FileReader(helbidea));
			String hitza;
			while (sarrera.hasNext()) {
				hitza = sarrera.nextLine();
				String url = weborria.getUrl();
				if (url.indexOf(hitza) >= 0) {
					GakoHitz gakoHitza = new GakoHitz(hitza);
					weborria.gakoHitzBerriaTxertatu(gakoHitza);
				}
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
	}
}