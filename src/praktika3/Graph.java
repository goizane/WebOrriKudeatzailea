package praktika3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import praktika1.WebOrriLista;

public class Graph {
	
	HashMap<String, Integer> th;  // hash taula, string-aren bidez bilatzeko
	String[] keys;                // kodearen bidez bilatzeko
	ArrayList<Integer>[] adjList; // loturak
	
	
	public void grafoaSortu(WebOrriLista listaWeb) {
		// Post: web guztien zerrendatik grafoa sortzen du
		//       Adabegiak web orriak dira 
		
            // 1. pausoa: “th” bete
            // KODEA OSATU

            // 2. pausoa: “keys” bete
		keys = new String[th.size()];
		for (String k: th.keySet()) keys[th.get(k)] = k;

            // 3. pausoa: “adjList” bete
            // KODEA OSATU
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

                 // KODEA OSATU
		
		return aurkitua;
	}
}