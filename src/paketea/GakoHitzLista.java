package paketea;

import java.util.ArrayList;

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
}