//Iteratorklasse für generische Conslisten aus Objekten, die verglichen werden können
//Onur, Christian

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ConsListIterator<E extends Comparable<E>> implements Iterator<E> {

	//Attribute
	ConsList<E> liste;
	Cons<E> pointer;

	//Konstruktor
	public ConsListIterator(ConsList<E> liste) {
		this.liste = liste;
		pointer = liste.getHead();
	}

	//es existiert ein Element
	public boolean hasNext() {
		return pointer != null;
	}


	//Element zurückgeben
	public E next() {

		//Element zurückgeben und pointer verschieben
		if(hasNext()) {
			E tmp =  pointer.get();
			pointer = pointer.next;
			return tmp;
		}

		//Exception werfen
		throw new NoSuchElementException();
	}
}
