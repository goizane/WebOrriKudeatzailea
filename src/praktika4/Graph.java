package praktika4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;
import praktika1.WebOrri;
import praktika1.WebOrriLista;

public class Graph {
	
	HashMap<String, Integer> th;  // hash taula, string-aren bidez bilatzeko
	String[] keys;                // kodearen bidez bilatzeko
	ArrayList<Integer>[] adjList; // loturak
	
	public void grafoaSortu() {
		// POST: Web guztien zerrendatik grafoa sortzen du.
		//       Adabegiak web orriak dira.
		
		WebOrriLista lista = WebOrriLista.getWebOrriLista();
		lista.kargatuURL("src/fitxategiak/smallindex.txt");
		lista.kargatuEstekak("src/fitxategiak/smallpld-arc.txt");
		Iterator<WebOrri> it = lista.getIterator();
		
		// 1. pausoa: “th” bete
		WebOrri egungoa;
		th = new HashMap<String, Integer>();
		while (it.hasNext()) {
			egungoa = it.next();
			th.put(egungoa.getUrl(), egungoa.getIndizea());
		}
		//KOSTUA: O(N)
		
		// 2. pausoa: “keys” bete
		keys = new String[th.size()];
		for (String k: th.keySet()) keys[th.get(k)] = k;
		//KOSTUA: O(N)
		
		// 3. pausoa: “adjList” bete
		int i = 0;
		int indize;
		ArrayList<Integer> lista2 = null;
		it =  lista.getIterator();
		adjList = new ArrayList [th.size()];
		while (it.hasNext()) {
			egungoa = it.next();
			lista2 = new ArrayList<Integer>();
			Iterator<WebOrri> it2 = egungoa.getEstekak().iterator();
			while (it2.hasNext()) {
				WebOrri e = it2.next();
				indize = e.getIndizea();
				lista2.add(indize);
			}
			//adjList = new ArrayList<Integer>[th.size()];
			adjList[i]= new ArrayList<Integer>();
			adjList[i] = lista2;
			i++;
		}
		//KOSTUA: O(NxM)
	}
	
	public void print() {
	   for (int i = 0; i < adjList.length; i++){
		System.out.print("Element: " + i + " " + keys[i] + " --> ");
		for (int k: adjList[i])  System.out.print(keys[k] + " ### ");
		
		System.out.println();
	   }
	}
	
	public boolean erlazionatuta(String a1, String a2) {
		//Postbaldintza: True bueltatuko du a1 weborritik a2 weborrira bidea baldin badago, bestela false.
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		int pos1 = th.get(a1);
		//System.out.println("1.esteka: "+ pos1);
		int pos2 = th.get(a2);
		//System.out.println("2.esteka: "+ pos2);
		boolean aurkitua = false;
		boolean[] aztertuak = new boolean[th.size()];
		aztertuGabeak.add(pos1);
		while ((!aztertuGabeak.isEmpty()) && (!aurkitua)) {
			int egungoa = aztertuGabeak.remove();
			if (egungoa==pos2) {
				aurkitua = true;
			}
			else {
				for (Integer i: adjList[egungoa]) {
					if (!aztertuak[i]) {
						aztertuGabeak.add(i);
						aztertuak[i] = true;
					}
				}
			}
		}
		System.out.println(aurkitua);
		return aurkitua;
	}
	
	public HashMap<String, Double> pageRank() {
		// POSTBALDINTZA: Emaitza web-orri zerrendaren web-orri
		//                bakoitzaren pageRank algoritmoaren balioa da
		HashMap<String,Double> emaitza = new HashMap<String,Double>();
		HashMap<String,Double> aurreko = new HashMap<String,Double>();
		Double nodoKop = (double) th.size();
		Double hasierakoBalioa = (1/nodoKop);
		for (String k : th.keySet()) {
			emaitza.put(k, hasierakoBalioa);
		}
		Double kenketenBatuketa = 100.0;
		while (kenketenBatuketa >= 0.0001) {
			Double dampingFactor = 0.85;
			Double hasierakoZatiketa = (1.0-dampingFactor)/nodoKop;
			for (String k : th.keySet()) {
				Double batuPageRank = 0.0;
				for (Integer balio : th.values()) {
					for (Integer a : adjList[balio]) {
						Double lotura = new Double(th.get(a).SIZE);
						Double pageRank = emaitza.get(a);
						batuPageRank = batuPageRank + (pageRank/lotura);
					}
					batuPageRank = dampingFactor * batuPageRank;
				}
				hasierakoZatiketa = hasierakoZatiketa + batuPageRank;
				aurreko.put(k, emaitza.get(k));
				emaitza.put(k, hasierakoZatiketa);
			}
			Double kenketa = 0.0;
			kenketenBatuketa = 0.0;
			for (String k: th.keySet()) {
				kenketa = emaitza.get(k) - aurreko.get(k);
				kenketenBatuketa = kenketenBatuketa + Math.abs(kenketa);
			}
		}
		return emaitza;
	}
	
	public void pantailaratuPageRank(HashMap<String, Double> pageRank) {
		for (String k: pageRank.keySet()){
			System.out.println("Key: " + k + "  ||  Balioa: " + pageRank.get(k));
		}
	}
	
	public ArrayList<String> bilatzailea(String gakoHitz, HashMap<String, Double> pageRank) {
		// POSTBALDINTZA: Emaitza emandako gako-hitza duten web-orrien zerrenda da, bere
		//                pageRank-aren arabera handienetik txikienera ordenatuta (hau da,
		//                lehenengo posizioetan pageRank handiena duten web-orriak agertuko dira)
		ArrayList<String> emaitza;
		
		return emaitza;
	}
}