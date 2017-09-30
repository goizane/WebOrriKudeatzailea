package paketea;

import java.util.ArrayList;

public class WebOrri {
	
	private String url;
	private ArrayList<WebOrri> estekak = new ArrayList<WebOrri>();
	private ArrayList<GakoHitz> gakoak = new ArrayList<GakoHitz>();
	
	public WebOrri(String url) {
		this.url = url;
	}
}