package paketea;

import java.util.ArrayList;
import java.util.Iterator;

public class WebOrri {
	
	// ATRIBUTUAK
	private String url;
	private int indizea;
	private ArrayList<WebOrri> estekak = new ArrayList<WebOrri>();
	private ArrayList<GakoHitz> gakoak = new ArrayList<GakoHitz>();
	
	// ERAIKITZAILEA
	public WebOrri(String url, int indizea) {
		this.url = url;
		this.indizea = indizea;
		this.estekak = new ArrayList<WebOrri>();
		this.gakoak = new ArrayList<GakoHitz>();
	}
	
	// METODOAK
	public String getUrl() {
		return this.url;
	}

	public int getIndizea() {
		return this.indizea;
	}

	public void estekaGehitu(WebOrri pEsteka) {
		this.estekak.add(pEsteka);
	}
	
	public void estekaBerriaTxertatu(WebOrri weborria) {
		this.estekak.add(weborria);
	}
	
	
	//**********METODO HAUEK PROBAK EGITEKO DIRA**********
	
	public Iterator<WebOrri> getIteratorEstekak() {
		return estekak.iterator();
	}
	
	public void inprimatuEstekak() {	
		Iterator<WebOrri> it = this.getIteratorEstekak();
		WebOrri egungoa = null;
		while (it.hasNext()) {
			egungoa = it.next();
			System.out.println(egungoa);
		}
	}
}