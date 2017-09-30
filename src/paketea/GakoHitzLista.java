package paketea;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GakoHitzLista {
	
	private ArrayList<GakoHitz> lista;
	private static GakoHitzLista nireGakoHitzLista = new GakoHitzLista();
	
	private GakoHitzLista() {
		this.lista = new ArrayList<GakoHitz>();
	}
	
	public static GakoHitzLista getGakoHitzLista() {
		if (GakoHitzLista.nireGakoHitzLista==null) {
			GakoHitzLista.nireGakoHitzLista = new GakoHitzLista();
		}
		return GakoHitzLista.nireGakoHitzLista;
	}
	
	public void kargatuHitzak(String nomF) {
		try {
			Scanner sarrera = new Scanner(new FileReader(nomF));
			String lerroa;
			while (sarrera.hasNext()) {
				lerroa = sarrera.nextLine();
				// TODO
				// Hemen kudeatu
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
	}
}