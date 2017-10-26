package praktika2;

public class UnorderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {
	// Elementua gehitzen du hasieran
		Node<T> berria = new Node<T>(elem);
		// Zerrenda hutsa bada:
		if (isEmpty()) {
			first = berria;
		}
		// Zerrenda hutsa ez bada:
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
		// Zerrenda hutsa bada:
		if (isEmpty()) {
			first = berria;
		}
		// Zerrenda hutsa ez bada:
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
		// Nodo bakarra badago, hori da Target elementua:
		if (first.next==null) {
			first.next = berria;
			first.prev = berria;
			berria.next = first;
			berria.prev = first;
		}
		// Zerrendak nodo bat baino gehiago baditu:
		else {
			Node<T> unekoa = first;
			boolean topatuta = false;
			while (topatuta==false) {
				// Ez da inoiz zerrendatik aterako, Target elementua beti zerrendan egon
				// behar delako. Beraz, momenturen baten aurkituko du.
				if (unekoa.data.equals(target)) {
					topatuta = true;
				}
				if (topatuta==false) {
					unekoa = unekoa.next;
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