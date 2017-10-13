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
		//aurre:
		//post: web-orriaren url itzuliko du
		return this.url;
	}

	public int getIndizea() {
		//aurre:
		//post: web-orriaren indizea itzuliko du
		return this.indizea;
	}
	
	public ArrayList<WebOrri> getEstekak() {
		//aurre:
		//post: web-orriaren esteka zerrenda itzuliko du
		return this.estekak;
	}

	public void estekaGehitu(WebOrri pEsteka) {
		//aurre: esteka bat (web-orria) parametro bezala
		//post: web-orriaren esteken zerrendan, esteka berria gehituko da, jada aurretik sartuta ez badago.
		Iterator<WebOrri> it = this.getIteratorEstekak();
		boolean topatua = false;
		WebOrri egungoa = null;
		while (it.hasNext()){
			egungoa = it.next();
			if (egungoa.getUrl().equals(pEsteka.getUrl())){
				topatua = true;
			}
		}
		if (!topatua){
			this.estekak.add(pEsteka);
		}	
	}
	
	public void estekaBerriaTxertatu(WebOrri weborria) {
		this.estekak.add(weborria);
	}
	
	public void gakoHitzBerriaTxertatu(GakoHitz gakoHitza) {
		//aurre: gako hitz berria parametro bezala
		//post: gako hitz berria web-orriaren gako hitzen zerrendan gordeko da, jada aurretik sartuta ez badago.
		Iterator<GakoHitz> it = this.getIteratorGakoHitzak();
		GakoHitz egungoa = null;
		boolean topatua = false;
		while(it.hasNext()){
			egungoa = it.next();
			if (egungoa.getHitza().equals(gakoHitza.getHitza())){
				topatua = true;
			}
		}
		if (!topatua){
			this.gakoak.add(gakoHitza);
		}
	}
	
	public int compareTo(WebOrri weborria) {
		//aurre:
		//post: web-orrien url-a konparatzen ditu alfabetikoki, itzulitako zenbakia >=0 bada 
		//		esan nahi du alfabetikoki lehenago dagoela, eta <0 bada alfabetikoki atzerago dago.
		return this.url.compareTo(weborria.getUrl());
	}
	
	private Iterator<WebOrri> getIteratorEstekak() {
		//aurre:
		//post: Esteken zerrendaren iteradorea itzuli
		return estekak.iterator();
	}
	
	private Iterator<GakoHitz> getIteratorGakoHitzak() {
		//aurre:
		//post: Gako hitzen zerrendaren iteradorea itzuli
		return gakoak.iterator();
	}
	
	//**********METODO HAUEK PROBAK EGITEKO DIRA**********	
	public void inprimatuEstekak() {	
		Iterator<WebOrri> it = this.getIteratorEstekak();
		WebOrri egungoa = null;
		while (it.hasNext()) {
			egungoa = it.next();
			System.out.println(egungoa.getUrl());
		}
	}

	public void inprimatuGakoHitzak(){
		Iterator<GakoHitz> it = this.getIteratorGakoHitzak();
		GakoHitz egungoa = null;
		while (it.hasNext()){
			egungoa = it.next();
			System.out.println(egungoa.getHitza());
		}
	}
}