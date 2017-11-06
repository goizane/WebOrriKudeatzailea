package praktika2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements ListADT<T> {

	// ATRIBUTUAK
	protected Node<T> first; // lehenengoaren erreferentzia
	protected String deskr;  // deskribapena
	protected int count;

	// ERAIKITZAILEA
	public DoubleLinkedList() {
		first = null;
		deskr = "";
		count = 0;
	}
	
	// METODOAK
	public void setDeskr(String ize) {
	// Postbaldintza: listaren deskribapena aldatuko da "ize" deskribapenarekin
	  deskr = ize;
	  
	// KOSTUA = O(1)
	}

	public String getDeskr() {
	// Postbaldintza: listaren deskribapena itzuliko du
	  return deskr;
	  
	// KOSTUA = O(1)
	}

	public T removeFirst() {
	// Aurrebaldintza: zerrenda ez da hutsa.
	// Posbaldintza: Lehen elementua ezabatu eta bere datua itzuliko du.
		Node<T> lehenengoa = first;
		// Zerrendak elementu bakarra badu:
		if (first.next==first) {
			first = null;
			this.count--;
			return lehenengoa.data;
		}
		else {
			// Zerrendak elementu bat baino gehiago badu:
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
	// Aurrebaldintza: zerrenda ez da hutsa.
	// Posbaldintza: Azken elementua ezabatu eta bere datua itzuliko du.
		Node<T> lehenengoa = first;
		// Zerrendak elementu bakarra badu:
		if (first.next==first) {
			first = null;
			this.count--;
			return lehenengoa.data;
		}
		else {
			// Zerrendak elementu bat baino gehiago badu:
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
	// Postbaldintza: Balio hori listan baldin badago, bere lehen agerpena ezabatuko du. 
	//				  Kendutako objektuaren erreferentzia bueltatuko du (null ez baldin badago).
		T emaitza = null;
		// Zerrendak elementu bakarra badu:
		if (first.next==first) {
			// Elementu hori ezabatu behar duguna bada:
			if (this.contains(elem)==true) {
				Node<T> lehenengoa = first;
				first = null;
				this.count--;
				emaitza =  lehenengoa.data;
			}
		}
		// Zerrendak elementu bat baino gehiago baditu:
		else {
			// Zerrendan ezabatu nahi dugun elementua badago:
			if (this.contains(elem)==true) {
				Node<T> unekoa = first;
				while (unekoa.data.equals(elem)==false) {
					unekoa = unekoa.next;
				}
				if (unekoa == first){
					first = unekoa.next;
				}else{
					Node<T> aurrekoa = unekoa.prev;
					Node<T> hurrengoa = unekoa.next;
					aurrekoa.next = hurrengoa;
					hurrengoa.prev = aurrekoa;	
				}
				emaitza = unekoa.data;
				this.count--;
			}
		}
		return emaitza;
		// N = Zerrendako elementu kopurua --> KOSTUA = O(N)
	}

	public T first() {
	// Postbaldintza: listako lehen elementua bueltatzen du
	      if (isEmpty())
	          return null;
	      else return first.data;
	      
	      // KOSTUA = O(1)
	}

	public T last() {
	// Postbaldintza: listako azken elementua ematen du
	      if (isEmpty())
	          return null;
	      else return first.prev.data;
	      
	      //KOSTUA = O(1)
	}

	public boolean contains(T elem) {
	// Postbaldintza: True bueltatuko du aurkituz gero, eta false bestela
		boolean aurkituta = false;
	
		// Zerrenda hutsa bada:
		if (isEmpty()) {
			return false;
		}
		// Zerrenda hutsa ez bada:
		else {
			T unekoDatua;
			Iterator<T> it = iterator();
			while ((it.hasNext()) && (aurkituta==false)) {
				unekoDatua = it.next();
				if (unekoDatua.equals(elem)) {
					aurkituta = true;
				}
			}
			return aurkituta;
		}
		// N = Zerrendako elementu kopurua --> KOSTUA = O(N)
	}

	public T find(T elem) {
	// Posbaldintza: Elementua bueltatuko du aurkituz gero, eta null bestela
		boolean aurkituta = false;
		// Zerrenda hutsa bada:
		if (isEmpty()) {
			return null;
		}
		// Zerrendak elementu bakarra badu:
		else if (first.next==first) {
			if (first.data.equals(elem)) {
				return first.data;
			}
			else {
				return null;
			}
		}
		// Zerrendak elementu bat baino gehiago baditu:
		else {
			T unekoDatua = null;
			Iterator<T> it = iterator();
			while ((it.hasNext()) && (aurkituta==false)) {
				unekoDatua = it.next();
				if (unekoDatua.equals(elem)) {
					aurkituta = true;
				}
			}
			if (aurkituta==false) {
				unekoDatua = null;
			}
			return unekoDatua;
		}
		// N = Zerrendako elementu kopurua --> KOSTUA = O(N)
	}

	public boolean isEmpty() {
	// Postbaldintza: true bueltatuko du lista hutsa bada, bestela false
		return (first == null);
		
		//KOSTUA = O(1)
	}
	
	public int size() {
	// Postbaldintza: listaren tamaina itzultzen du
		return count;
		//KOSTUA = O(1)
	}
	
	
	/** Return an iterator to the stack that iterates through the items . */ 
	public Iterator<T> iterator() { return new ListIterator(); }
	
	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<T> {
		Node<T> unekoa = first;
		Boolean amaituta = false;
		
		public boolean hasNext() {
			if (amaituta || unekoa==null) {
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
			if (unekoa==first){
				amaituta=true;
			}
			return elem;
		}
		
		public void remove() {
			System.out.println("Remove metodoa ez da inplementatu!");
		}
	} // private class
	
	
	public void adabegiakInprimatu() {
	// Postbaldintza: listaren adabegi bakoitzaren datua pantailaratuko du
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		String result = new String();
		result = "";
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T elem = it.next();
			result = result + "[" + elem.toString() + "] ";
		}
		return "SimpleLinkedList: [ " + result + "]";
	}
}