package paketea;

import java.util.ArrayList;

public class WebOrri {
	
	private String url;
	private String indizea;
	private ArrayList<WebOrri> estekak = new ArrayList<WebOrri>();
	private ArrayList<GakoHitz> gakoak = new ArrayList<GakoHitz>();
	
	public WebOrri(String url, String indizea) {
		this.url = url;
		this.indizea = indizea;
		estekak = new ArrayList<WebOrri>();
		gakoak = new ArrayList<GakoHitz>();
	}
	
	public void estekaBerriaTxertatu(WebOrri weborria) {
		this.estekak.add(weborria);
	}
}