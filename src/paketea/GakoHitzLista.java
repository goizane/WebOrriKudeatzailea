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
	public void kargatuHitzak(String helbidea) {
		try {
			Scanner sarrera = new Scanner(new FileReader(helbidea));
			String lerroa;
			while (sarrera.hasNext()) {
				lerroa = sarrera.nextLine();
				// TODO
				// Hemen web-orri bakoitzak dauzkan gakoak fitxategitik kargatu behar dira.
				// Web-orri bakoitzaren URL-a pasatu beharko zaio metodo honi deitzerakoan, lista pertsonalizatua lortzeko.
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
	}
}