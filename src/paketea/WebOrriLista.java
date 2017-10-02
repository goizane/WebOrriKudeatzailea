package paketea;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WebOrriLista {
	
	private ArrayList<WebOrri> lista;
	private static WebOrriLista nireWebOrriLista = new WebOrriLista();
	
	private WebOrriLista() {
		this.lista = new ArrayList<WebOrri>();
	}
	
	public static WebOrriLista getWebOrriLista() {
		if (WebOrriLista.nireWebOrriLista==null) {
			WebOrriLista.nireWebOrriLista = new WebOrriLista();
		}
		return WebOrriLista.nireWebOrriLista;
	}
	
	public void webOrriBerriaTxertatu(WebOrri weborria) {
		this.lista.add(weborria);
	}
	
	public void kargatuURL(String nomF) {
		try {
			Scanner sarrera = new Scanner(new FileReader("src/fitxategiak/smallindex"));
			String lerroa;
			String url;
			String indizea;
			WebOrri weborria;
			while (sarrera.hasNext()) {
				lerroa = sarrera.nextLine();
				url = lerroa.split("\\s+")[0];
				indizea = lerroa.split("\\s+")[1];
				weborria = new WebOrri(url, indizea);
				this.webOrriBerriaTxertatu(weborria);
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
	}
	
	public void kargatuEstekak(String nomF) {
		try {
			Scanner sarrera = new Scanner(new FileReader(nomF));
			String lerroa;
			while (sarrera.hasNext()) {
				lerroa = sarrera.nextLine();
				// TODO
				// Hemen web-orri bakoitzak dauzkan estekak fitxategitik kargatu behar dira.
				// Web-orri bakoitzaren URL-a pasatu beharko zaio metodo honi deitzerakoan, lista pertsonalizatua lortzeko.
			}
			sarrera.close();
		}
		catch(IOException e) {e.printStackTrace();}
	}
}