package praktika3;

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
	
	
	public void grafoaSortu(WebOrriLista listaWeb) {
		// POST: Web guztien zerrendatik grafoa sortzen du.
		//       Adabegiak web orriak dira.
		
		WebOrriLista lista = WebOrriLista.getWebOrriLista();
		Iterator<WebOrri> it = lista.getIterator();
		
		// 1. pausoa: “th” bete
		WebOrri egungoa;
		while (it.hasNext()) {
			egungoa = it.next();
			th.put(egungoa.getUrl(), egungoa.getIndizea());
		}
		
		// 2. pausoa: “keys” bete
		keys = new String[th.size()];
		for (String k: th.keySet()) keys[th.get(k)] = k;
		
		// 3. pausoa: “adjList” bete
		int i = 0;
		int indize;
		ArrayList<Integer> lista2 = null;
		while (it.hasNext()) {
			egungoa = it.next();
			lista2 = new ArrayList<Integer>();
			while (egungoa.getEstekak().iterator().hasNext()) {
				indize = egungoa.getEstekak().iterator().next().getIndizea();
				lista2.add(indize);
			}
			adjList[i] = lista2;
			i++;
		}
	}
	
	
	public void print() {
	   for (int i = 0; i < adjList.length; i++){
		System.out.print("Element: " + i + " " + keys[i] + " --> ");
		for (int k: adjList[i])  System.out.print(keys[k] + " ### ");
		
		System.out.println();
	   }
	}
	
	
	public boolean erlazionatuta(String a1, String a2) {
		Queue<Integer> aztertuGabeak = new LinkedList<Integer>();
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
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
		return aurkitua;
	}
}