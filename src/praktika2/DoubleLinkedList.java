package praktika2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {

	// Atributuak
	protected Node<T> first; // lehenengoaren erreferentzia
	protected String deskr;  // deskribapena
	protected int count;

	public DoubleLinkedList() {
		first = null;
		deskr = "";
		count = 0;
	}
	
	public void setDeskr(String ize) {
	  deskr = ize;
	}

	public String getDeskr() {
	  return deskr;
	}

	public T removeFirst() {
	// listako lehen elementua kendu da
	// Aurrebaldintza: zerrenda ez da hutsa
		Node<T> lehenengoa = first;
		Node<T> bigarrena = first.next;
		Node<T> azkena = first.prev;
		bigarrena.prev = azkena;
		azkena.next = bigarrena;
		first = bigarrena;
		this.count--;
		return lehenengoa.data;
		// KOSTUA = O(1)
	}

	public T removeLast() {
	// listako azken elementua kendu da
	// Aurrebaldintza: zerrenda ez da hutsa
		Node<T> azkena = first.prev;
		Node<T> azkenaurrekoa = azkena.prev;
		first.prev = azkenaurrekoa;
		azkenaurrekoa.next = first;
		this.count--;
		return azkena.data;
		// KOSTUA = O(1)
    }


	public T remove(T elem) {
	// Aurrebaldintza: zerrenda ez da hutsa
	// Balio hori listan baldin badago, bere lehen agerpena ezabatuko dut. Kendutako objektuaren erreferentzia 
    // bueltatuko du (null ez baldin badago)
		// KODEA OSATU ETA KOSTUA KALKULATU
		
	}

	public T first() {
	// listako lehen elementua ematen du
	      if (isEmpty())
	          return null;
	      else return first.data;
	}

	public T last() {
	// listako azken elementua ematen du
	      if (isEmpty())
	          return null;
	      else return first.prev.data;
	}

	public boolean contains(T elem) {
	// Egiazkoa bueltatuko du aurkituz gero, eta false bestela
		if (isEmpty()) {
			return false;
		}

		      		// KODEA OSATU ETA KOSTUA KALKULATU
	}

	public T find(T elem) {
	// Elementua bueltatuko du aurkituz gero, eta null bestela

		// KODEA OSATU ETA KOSTUA KALKULATU
	}

	public boolean isEmpty() 
	{ return first == null;};
	
	public int size() 
	{ return count;};
	
	
	/** Return an iterator to the stack that iterates through the items . */ 
	public Iterator<T> iterator() { return new ListIterator(); }
	
	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<T> {
		// KODEA OSATU
		
	} // private class
	
	
	public void adabegiakInprimatu() {
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		String result = new String();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T elem = it.next();
			result = result + "[" + elem.toString() + "] \n";
		}
		return "SimpleLinkedList " + result + "]";
	}
}