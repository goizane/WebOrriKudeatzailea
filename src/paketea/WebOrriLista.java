package paketea;

import java.util.ArrayList;

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
}