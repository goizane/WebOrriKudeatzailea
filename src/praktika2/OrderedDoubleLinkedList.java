package praktika2;

public class OrderedDoubleLinkedList<T> extends DoubleLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){
	// Elementua gehitu dagokion posizioan zerrenda ordenatu batean
	// Aurrebaldintza: Zerrenda txikitik handira dago ordenatuta.
		Node<T> berria = new Node<T>(elem);
		if (isEmpty()) {
			first = berria;
		}
		else {
			// Aquí insertar el nuevo nodo en su lugar ordenado!
			//TODO
		}
		this.count++;
		// KOSTUA
	}
}