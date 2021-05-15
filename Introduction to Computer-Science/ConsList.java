//Consliste für generische, vergleichbare Datentypen + Iterator - mit folgenden Methoden: size, add, get (Atrribute + Inhalte), remove, isEmpty, sort,
//Onur, Christian

import java.util.AbstractList;

public class ConsList<E extends Comparable<E>> extends AbstractList<E> {

	//Anfang und Ende der Liste
	private Cons<E> head;
	private Cons<E> foot;

	//neue leere Liste
	public ConsList(){
		head = foot = null;
	}



	//get Methoden für Attribute
	public Cons<E> getHead(){
		return head;
	}

	public Cons<E> getFoot(){
		return foot;
	}



	//Länge der Liste
	public int size() {

		assert classInv(): "error - link broken";

		return size(head, 0);
	}

	private int size(Cons<E> cons, int counter) {

		//Ende erreicht
		if(cons == null) {

			assert classInv(): "error - link broken";
			assert cons == null: "end not reached";
			assert counter >= 0: "error: negative size";

			return counter;
		}

		//weiter mit Nachfolger => counter +1
		return size(cons.next, ++counter);
	}


	//Objekt an einer bestimmten Stelle
	public E get(int index) {

		assert classInv(): "error - link broken";

		return get(head, 0, index);
	}

	private E get(Cons<E> cons, int counter, int index) {

		//Ende der Liste erreicht => Index zu groß oder Index negativ
		if(cons == null || index < 0) {

			assert cons == null || index < 0: "no element with that index in list";
			assert classInv(): "error - link broken";

			throw new IndexOutOfBoundsException();
		}

		//Index erreicht
		if(counter == index) {

			assert classInv(): "error - link broken";
			assert counter == index: "wrong index";

			return cons.get();
		}

		//rekursiver Aufruf
		return get(cons.next, ++counter, index);
	}



	//Objekt an der Stelle index einfügen
	public void add(int index, E obj) {

		//ungültiger Index
		if(index < 0 || index > size()) {

			assert classInv(): "error - link broken";
			assert index < 0 || index > size(): "invalid index to add";
			throw new IndexOutOfBoundsException();
		}

		//erstes Element, das eingefügt wird
		if(isEmpty()) {
			Cons<E> tmp = new Cons<E>(obj);
			head = foot = tmp;

			assert classInv(): "error - link broken";
			assert head == foot && head == tmp: "not inserted correctly (very first element of list)";

			return;
		}

		//vorne einfügen
		if(index == 0) {
			Cons<E> tmp = new Cons<E>(obj);
			tmp.next = head;
			head = tmp;

			assert classInv(): "error - link broken";
			assert head == tmp: "not inserted correctly (element at the beginning of the list)";

			return;
		}

		//hinten einfügen
		if(index == size()) {
			Cons<E> tmp = new Cons<E>(obj);
			foot.next = tmp;
			foot = tmp;

			assert classInv(): "error - link broken";
			assert foot == tmp: "not inserted correctly (element at the end of the list)";

			return;
		}

		//zwischendrin einfügen
		add(head, 0, index, obj);
	}

	private void add(Cons<E> cons, int counter, int index, E obj){

		//index erreicht
		if(counter == index-1) {
			Cons<E> tmp = new Cons<E>(obj);
			tmp.next = cons.next;
			cons.next = tmp;

			assert classInv(): "error - link broken";
			assert cons.next == tmp: "not inserted correctly (element in the middle of the list)";

			return;
		}

		//rekursiv weiter
		add(cons.next, ++counter, index, obj);
	}


	//Objekt löschen
	public E remove(int index) {

		//ungültiger index
		if(index < 0 || index >= size()) {

			assert classInv(): "error - link broken";
			assert index < 0 || index >= size(): "index should be in list";
			throw new IndexOutOfBoundsException();
		}

		//erstes Objekt entfernen
		if(index == 0) {

			//Sonderfall einelementige Liste
			if(size() == 1) {
				E erg = head.get();
				head = foot = null;
				return erg;
			}

			Cons<E> tmp = head;
			head = tmp.next;
			tmp.next = null;

			assert classInv(): "error - link broken";
			assert tmp != head: "not removed correctly (element at position 0)";

			return tmp.get();
		}

		return remove(head, 0, index);
	}

	private E remove(Cons<E> cons, int counter, int index) {

		//Index erreicht
		if(counter == index-1) {
			Cons<E> tmp = cons.next;
			cons.next = tmp.next;
			tmp.next = null;

			//foot anpassen, falls das letze Element gelöscht wird
			if(tmp == foot) {
				foot = cons;
			}

			assert classInv(): "error - link broken";
			assert tmp != cons.next: "not removed correctly (element in list)";

			return tmp.get();
		}

		//rekursiver Aufruf
		return remove(cons.next, ++counter, index);
	}


	//prüft ob die Liste leer ist
	public boolean isEmpty() {

		assert classInv(): "error - link broken";

		return size() == 0;
	}


	//Liste in Ordnung - die Liste ist nicht leer bzw. besteht nicht nur aus einem Element, aber der head hat keinen Nachfolger
	private boolean classInv() {
		return classInv(head);
	}

	private boolean classInv(Cons<E> cons) {

		//Sonderfall leere Liste
		if(head == foot && foot == null) {
			return true;
		}

		//Alle Verbindungen in Ordnung
		if(cons.next == null) {
			return cons == foot;
		}
		//rekursiver Aufruf
		else {
			return classInv(cons.next);
		}
	}


	//Iterator
	public ConsListIterator<E> iterator(){
		return new ConsListIterator<E>(this);
	}


	//Quicksort
	public ConsList<E> sort() {
		return sort(this);
	}

	private ConsList<E> sort(ConsList<E> liste){

		//Abbruchbedingung
		if(liste.size() <= 1) {
			return liste;
		}

		//Pivot wählen
		E a = liste.head.get();

		//Sublisten erstellen
		ConsList<E> s1 = new ConsList<E>();
		ConsList<E> s2 = new ConsList<E>();

		//Spliten anhand der Pivotelements
		split(liste.head.next, a, s1, s2);


		//rekursiver Aufruf
		s1 = sort(s1);
		s2 = sort(s2);

		//mergen
		return merge(s1, a, s2);
	}


	//aufteilen
	private void split(Cons<E> pointer, E a, ConsList<E> s1, ConsList<E> s2) {

		//komplett gesplitet
		if(pointer == null) {
			return;
		}

		//in die entsprechende Liste einfügen
		if(pointer.get().compareTo(a) < 0) {
			s1.add(pointer.get());
		}
		else {
			s2.add(pointer.get());
		}

		//rekursiver Aufruf
		split(pointer.next, a, s1, s2);
	}

	//zusammenfügen
	private ConsList<E> merge(ConsList<E> s1, E a, ConsList<E> s2) {

		//pivotelement anfügen
		s1.add(a);
		s1.addAll(s2);

		return s1;
	}
}
