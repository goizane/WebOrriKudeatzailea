package praktika4;

import java.util.ArrayList;
import java.util.HashMap;

public class ProbaGraph {

	public static void main(String[] args) {
		Graph g = new Graph();
		g.grafoaSortu();
		System.out.println("Grafoa pantailaratu:");
		g.print();
		System.out.println("");
		System.out.println("");
		System.out.println("Erlazionatuta daude 0-apr-creditcards.com eta 0-100editions.net web-orriak?");
		g.erlazionatuta("0-apr-creditcards.com", "0-100editions.net");
		System.out.println("");
		System.out.println("");
		System.out.println("Page Rank-ak pantailaratu:");
		HashMap<String, Double> pageRank = g.pageRank();
		g.pantailaratuPageRank(pageRank);
		System.out.println("");
		System.out.println("");
		System.out.println("GakoHitza duten WebOrri zerrenda PageRank-aren arabera ordenaturik pantailaratu:");
		ArrayList<String> webOrriak = g.bilatzailea("refix", pageRank);
		g.pantailaratuBilatzailea(webOrriak);
		System.out.println("");
		System.out.println("");
		System.out.println("GakoHitz BIAK dituzten WebOrri zerrenda PageRank-aren arabera ordenaturik pantailaratu:");
		ArrayList<String> webOrriakBi = g.bilatzailea("refix", "objection", pageRank);
		g.pantailaratuBilatzailea(webOrriakBi);
		System.out.println("");
		System.out.println("");
	}
}