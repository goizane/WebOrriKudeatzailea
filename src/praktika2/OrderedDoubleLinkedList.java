package praktika2;

public class OrderedDoubleLinkedList<T extends Comparable<T>> extends DoubleLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){
	// Aurrebaldintza: Zerrenda txikitik handira dago ordenatuta.
	// Posbaldintza: Elementua gehitu dagokion posizioan zerrenda ordenatu batean.
	//				 Zerrendan "elem" elemetuaren berdina badago, nodo horren aurretik edo atzetik txertatuto da.
		Node<T> berria = new Node<T>(elem);
		// Zerrenda hutsa bada:
		if (isEmpty()) {
			first = berria;
			first.next = berria;
			first.prev = berria;
		}
		//Zerrenda elementu bakarra badu
		else if (first.next == first){
			if (elem.compareTo(first.data)>=0){
				first.next = berria;
				first.prev = berria;
				berria.next = first;
				berria.prev = first;
				
				
			}else{
				berria.next = first;
				berria.prev = first;
				first.prev = berria;
				first.next = berria;
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