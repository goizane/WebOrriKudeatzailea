package praktika2;

public class OrderedDoubleLinkedList<T extends Comparable<T>> extends DoubleLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){
	// Elementua gehitu dagokion posizioan zerrenda ordenatu batean.
	// Aurrebaldintza: Zerrenda txikitik handira dago ordenatuta.
	// Posbaldintza: Zerrendan nodoren bat baldin badado elementuaren berdina dena,
	//				 elementua honen ondoan txertatuko da, aurretik edo atzetik.
		Node<T> berria = new Node<T>(elem);
		// Zerrenda hutsa bada:
		if (isEmpty()) {
			first = berria;
		}
		// Zerrendak elementu bakarra badu:
		else if (first.next==null) {
			// Elementua lehenengo nodo-ko datua bainoa handiago edo berdina bada:
			if (elem.compareTo(first.data)>=0) {
				first.next = berria;
				first.prev = berria;
				berria.next = first;
				berria.prev = first;
			}
			// Elementua lehenengo nodo-ko datua baino txikiago bada:
			else {
				first.next = berria;
				first.prev = berria;
				berria.next = first;
				berria.prev = first;
				first = berria;
			}
		}
		// Zerrendak elementu bat baino gehiago baditu:
		else {
			// Ematen diguten elementua zerrendako handiena bada:
			if (elem.compareTo(first.prev.data)>=0) {
				Node<T> lehenengoa = first;
				Node<T> azkena = first.prev;
				berria.next = lehenengoa;
				berria.prev = azkena;
				azkena.next = berria;
				lehenengoa.prev = berria;
			}
			// Ematen diguten elementua zerrendako txikiena bada:
			else if (elem.compareTo(first.data)<=0) {
				Node<T> lehenengoa = first;
				Node<T> azkena = first.prev;
				berria.next = lehenengoa;
				berria.prev = azkena;
				azkena.next = berria;
				lehenengoa.prev = berria;
				first = berria;
			}
			// Elementua erdiko balioen artean badago:
			else {
				Node<T> unekoa = first;
				Node<T> hurrengoa = first.next;
				while (elem.compareTo(hurrengoa.data)>=0) {
					unekoa = hurrengoa;
					hurrengoa = hurrengoa.next;
				}
				berria.next = hurrengoa;
				berria.prev = unekoa;
				unekoa.next = berria;
				hurrengoa.prev = berria;
			}
		}
		this.count++;
		// N = Zerrendako elementu kopurua --> KOSTUA = O(N)
	}
}