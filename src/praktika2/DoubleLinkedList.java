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
		if (first.next==null) {
			first = null;
			this.count--;
			return lehenengoa.data;
		}
		else {
			Node<T> bigarrena = first.next;
			Node<T> azkena = first.prev;
			bigarrena.prev = azkena;
			azkena.next = bigarrena;
			first = bigarrena;
			this.count--;
			return lehenengoa.data;
		}
		// KOSTUA = O(1)
	}

	public T removeLast() {
	// listako azken elementua kendu da
	// Aurrebaldintza: zerrenda ez da hutsa
		if (first.next==null) {
			Node<T> lehenengoa = first;
			first = null;
			this.count--;
			return lehenengoa.data;
		}
		else {
			Node<T> azkena = first.prev;
			Node<T> azkenaurrekoa = azkena.prev;
			first.prev = azkenaurrekoa;
			azkenaurrekoa.next = first;
			this.count--;
			return azkena.data;
		}
		// KOSTUA = O(1)
    }


	public T remove(T elem) {
	// Aurrebaldintza: zerrenda ez da hutsa
	// Balio hori listan baldin badago, bere lehen agerpena ezabatuko dut. Kendutako objektuaren erreferentzia 
    // bueltatuko du (null ez baldin badago)
		if (first.next==null) {
			if (this.contains(elem)==true) {
				Node<T> lehenengoa = first;
				first = null;
				this.count--;
				return lehenengoa.data;
			}
			else {
				return null;
			}
		}
		else {
			boolean aurkituta = false;
			Node<T> unekoa = first;
			Iterator<T> it = iterator();
			while ((it.hasNext()) && (aurkituta==false)) {
				unekoa.data = it.next();
				if (unekoa.data.equals(elem)) {
					aurkituta = true;
				}
			}
			if (aurkituta==false) {
				return null;
			}
			else {
				Node<T> aurrekoa = unekoa.prev;
				Node<T> hurrengoa = unekoa.next;
				aurrekoa.next = hurrengoa;
				hurrengoa.prev = aurrekoa;
				return unekoa.data;
			}
		}
		// N = Zerrendako elementu kopurua --> KOSTUA = O(N)
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
		boolean aurkituta = false;
		if (isEmpty()) {
			aurkituta = false;
		}
		T unekoa;
		Iterator<T> it = iterator();
		while ((it.hasNext()) && (aurkituta==false)) {
			unekoa = it.next();
			if (unekoa.equals(elem)) {
				aurkituta = true;
			}
		}
		return aurkituta;
		// N = Zerrendako elementu kopurua --> KOSTUA = O(N)
	}

	public T find(T elem) {
	// Elementua bueltatuko du aurkituz gero, eta null bestela
		boolean aurkituta = false;
		if (isEmpty()) {
			return null;
		}
		T unekoa = null;
		Iterator<T> it = iterator();
		while ((it.hasNext()) && (aurkituta==false)) {
			unekoa = it.next();
			if (unekoa.equals(elem)) {
				aurkituta = true;
			}
		}
		if (aurkituta==false) {
			unekoa = null;
		}
		return unekoa;
		// N = Zerrendako elementu kopurua --> KOSTUA = O(N)
	}

	public boolean isEmpty() 
	{ return first == null;};
	
	public int size() 
	{ return count;};
	
	
	/** Return an iterator to the stack that iterates through the items . */ 
	public Iterator<T> iterator() { return new ListIterator(); }
	
	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<T> {
		Node<T> unekoa = first;
		Node<T> hurrengoa = first.next;
		
		public boolean hasNext() {
			if (hurrengoa==first) {
				return false;
			}
			else {
				return true;
			}
		}
		
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T elem = unekoa.data;
			unekoa = unekoa.next;
			return elem;
		}
		
		public void remove() {
			System.out.println("Remove metodoa ez da inplementatu!");
		}
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