//Conszellen für generische Objekte
//Onur, Christian

public class Cons<E> {

	//generisches Objekt
	private E o;

	//Nachfolger
	public Cons<E> next;


	//Kontruktor
	public Cons(E o){
		this.o = o;
		next = null;
	}

	//Zugriff auf das Objekt
	public E get() {
		return o;
	}
}
