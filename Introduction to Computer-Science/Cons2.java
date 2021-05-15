//Christian
//Conszellen für die doppelt verkettete Liste

public class Cons2 {
	
	//Attribute, Objekt und 2 Referenzen
	public Object o;
	public Cons2 prev,next;
		
	//Konstruktor;
	public Cons2(Object o) {
		this.o = o;
		this.prev = this.next = null;
	}
	
	//toString
	public String toString() {
		return this.o.toString();
	}
}
