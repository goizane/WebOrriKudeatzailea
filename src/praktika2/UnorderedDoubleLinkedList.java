package praktika2;

import java.util.Iterator;

public class UnorderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {
	// Elementua gehitzen du hasieran
		Node<T> berria = new Node<T>(elem);
		if (isEmpty()) {
			first = berria;
		}
		else {
			Node<T> lehenengoa = first;
			Node<T> azkena = first.prev;
			berria.next = lehenengoa;
			berria.prev = azkena;
			azkena.next = berria;
			lehenengoa.prev = berria;
			first = berria;
		}
		this.count++;
		// KOSTUA = O(1)
	}

	public void addToRear(T elem) {
	// Elementua gehitzen du bukaeran
		Node<T> berria = new Node<T>(elem);
		if (isEmpty()) {
			first = berria;
		}
		else {
			Node<T> lehenengoa = first;
			Node<T> azkena = first.prev;
			berria.next = lehenengoa;
			berria.prev = azkena;
			azkena.next = berria;
			lehenengoa.prev = berria;
		}
		this.count++;
		// KOSTUA = O(1)
	}
	
	public void addAfter(T elem, T target) {
	// Elementua gehitzen du Target elementuaren ondoren.
	// Aurrebaldintza: Target elementua beti egon behar da zerrendan.
	// Aurrebaldintza: Ezin da zerrenda hutsa izan.
		Node<T> berria = new Node<T>(elem);
		if (first.next==null) {  // Nodo bakarra badago, hori da Target elementua.
			first.next = berria;
			first.prev = berria;
			berria.next = first;
			berria.prev = first;
		}
		else {
			Node<T> unekoa = first;
			boolean topatuta = false;
			Iterator<T> it = iterator();
			while (topatuta==false) {
				// Ez da inoiz zerrendatik aterako, Target elementua beti zerrendan egon
				// behar delako. Beraz, momenturen baten aurkituko du.
				unekoa.data = it.next();
				if (unekoa.data.equals(target)) {
					topatuta = true;
				}
			}
			Node<T> hurrengoa = unekoa.next;
			berria.next = hurrengoa;
			berria.prev = unekoa;
			unekoa.next = berria;
			hurrengoa.prev = berria;
		}		
		this.count++;
		// N = Zerrendako elementu kopurua --> KOSTUA = O(N)
	}
}