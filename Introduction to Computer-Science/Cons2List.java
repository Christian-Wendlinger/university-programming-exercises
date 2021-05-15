//Christian
//doppelt verkettete Liste

public class Cons2List {
	
	//Liste mit head und foot
	private Cons2 head,foot;
		
		
	//Konstruktor leere Liste
	public Cons2List() {
		head = foot = null;
	}
		
		
		
	//contains Methode
	public boolean contains(Object o) {
		return contains(head, o);
	}
		
	//hilfsmethode
	private boolean contains(Cons2 cons, Object o) {
		
		//Liste leer
		if(head == null) {
			return false;
		}
			
		//falls das Objekt der ConsZelle genau gleich dem Objekt o ist wird true zurück gegeben
		if(cons.o.equals(o)) {
			return true;
		}
		else {
				
			//falls es das letze Element ist, kann keins mehr Folgen, also ist das Element nicht drin
			if(cons == foot) {
				return false;
			}
				
			//rekursiver Aufruf
			return contains(cons.next, o);
				
		}
	}
		
		
		
		
	//remove Methode (entfernt alle Zellen, die auf das Objekt o referenzieren)
	public void remove(Object o) {
		remove(head, o);
	}
		
	//Hilfsmethode
	private void remove(Cons2 cons, Object o) {
		
		//liste Leer
		if(head == null) {
			return;
		}
		
		//falls die Liste einmal durchlaufen wurde und das Element nicht gefunden wurde
		if(cons == null) {
			return;
		}
			
		//falls das Object gefunden wurde
		if(cons.o.equals(o)) {
			
			
			//falls die Liste nur 1 Element enthält
			if(head == foot) {
				head = foot = null;
				return;
			}
				
			//falls es nicht der Anfang der Liste ist und auch nicht das Ende
			if(cons != head && cons != foot) {
				//Referenz neu setzen
				cons.next.prev = cons.prev;
			}
			
			//falls es der Anfang ist
			if(cons == head) {
				//head um 1 nach rechts und Referenz zum Anfang der Liste löschen
				head = head.next;
				head.prev = null;
				cons.next = null;
				remove(o);
				return;
			}
			
			
			//falls es nicht das Ende der Liste ist und auch nicht der Anfang
			if(cons != foot && cons != head) {
					
				//Referenz neu setzen
				cons.prev.next = cons.next;
			}
			
			//falls es das Ende der Liste ist
			if(cons == foot) {
				//foot um 1 nach vorne verschieben und die Referenz zur letzten Zelle löschen
				foot = foot.prev;
				foot.next = null;
				cons.prev = null;
				return;
			}
				
			//die Referenzen von Cons löschen, damit es mit der Liste nichts mehr zu tun hat
			//die cons.next vor dem Löschen zwischenspeichern, um die Rekursion nicht von vorne
			//beginnen lassen zu müssen
			Cons2 tmp = cons.next;
			cons.next = cons.prev = null;
			remove(tmp, o);
		}
		
		//rekursiver Aufruf, falls nicht vorhanden
		remove(cons.next, o);

	}
	
	
	
	//append Methode (hinten anfügen)
	public void append(Object o) {
		Cons2 cons = new Cons2(o);
		
		//Falls die Liste leer ist
		if(head == null) {
			head = foot = cons;
			return;
		}
		
		//Zellen richtig verketten und den foot ans neue Ende setzen
		cons.prev = foot;
		foot.next = cons;
		foot = cons;
	}
	
	
	
	//insert Methode(vorne anfügen)
	public void insert(Object o) {
		Cons2 cons = new Cons2(o);
		
		//falls die Liste leer ist
		if(head == null) {
			head = foot = cons;
			return;
		}
		
		//Zellen richtig verketten und den head an den neuen Anfang setzen
		cons.next = head;
		head.prev = cons;
		head = cons;
	}
	
	
	//isEmpty Methode
	public boolean isEmpty() {
		
		if(head == null) {
			return true;
		}
		
		return false;
	}
	
	//removeHead gibt das Objekt in der Zelle zurück, auf die Head zeigt und entfernt die Zelle aus der Liste
	
	public Object removeHead() {
		Cons2 cons = head;
		
		//Liste leer
		if(head == null ) {
			return null;
		}
		
		//Liste nur 1 Element
		if(head == foot) {
			head = foot = null;
			return cons.o;
		}
		
		head.next.prev = null;
		head = head.next;
		cons.next = null;
		
		return cons.o;
	}
	
	
	//size, gibt die Anzahl der Zellen zurück;
	public int size() {
		return size(head, 0);
	}
	
	//hilfsmethode
	private int size(Cons2 cons, int sum) {
		
		//Liste leer
		if(head == null) {
			return 0;
		}
		
		//1 Element
		if(head == foot) {
			return 1;
		}
		
		if(cons != foot) {
			sum++;
			return size(cons.next, sum);
		}
		
		//sum+1, da sonst die foot Zelle nicht gezählt wird
		return sum+1;
	}
	
	
	
	//toString
	public String toString() {
		String tmp = "[";
		return toString(head, tmp);
	}
	
	//hilfsmethode
	private String toString(Cons2 cons, String res) {
		
		//Liste leer
		if(head == null) {
			return res+"]";
		}
		
		//Ende der Liste
		if(cons == foot) {
			return res + cons.toString() + "]";
		}
		
		res += cons.toString() + ", ";
		
		return toString(cons.next, res);
	}
	
	//test main
	public static void main(String[] args) {
		
		/*
		Cons2List l = new Cons2List();
		
		l.append(1); 
		l.append(2); 
		l.append(3);
		l.remove(2);
		
		Cons2 c0 = new Cons2(0); 
		Cons2 c2 = new Cons2(2);
		
		c0.next = c2; 
		l.insert(c0); 
		l.append(c2); 
		
		System.out.println(l);
		*/
		
		/*
		//testListe für die Methoden
		Cons2List test = new Cons2List();
		
		test.append(1);
		test.append(1);
		test.append(2);
		test.append(3);
		test.append(3);
		test.append(1);
		test.append(1);
		test.append(2);
		test.append(1);
		test.append(1);
		test.append(2);
		test.append(3);
		test.append(3);
		test.insert(1);
		test.insert(4);
		test.insert(5);
		test.append(2);
		test.insert(19);

		System.out.println(test);
		System.out.println(test.size());
		System.out.println(test.isEmpty());
		
		System.out.println(test.contains(1));
		System.out.println(test.contains(2));
		System.out.println(test.contains(3));
		System.out.println(test.contains(4));
		
		test.remove(1);
		test.remove(2);
		System.out.println(test);
		System.out.println(test.size());
		
		System.out.println(test.contains(1));
		System.out.println(test.contains(2));
		System.out.println(test.contains(3));
		System.out.println(test.contains(4));
		System.out.println(test.contains("Hallo"));
		System.out.println(test.isEmpty());
		
		System.out.println(test.removeHead());
		System.out.println(test.removeHead());
		System.out.println(test.removeHead());
		System.out.println(test.removeHead());
		System.out.println(test.removeHead());
		System.out.println(test.removeHead());
		System.out.println(test.removeHead());
		System.out.println(test.removeHead());
		System.out.println(test.removeHead());
		
		test.remove(5);
		System.out.println(test);
		System.out.println(test.size());
		System.out.println(test.isEmpty());
		
		test.addAt(-3, 8);
		test.addAt(7, 8);
		test.addAt(2, 5);
		test.addAt(2, "jo");
		test.addAt(6, "vorletztes");
		test.addAt(8, "ende");
		test.addAt(0, 1);
		test.addAt(0, "Anfang");
		System.out.println(test);
		
		test.remove(8);
		test.remove("Anfang");
		test.remove("jo");
		test.remove("vorletztes");
		test.remove("fünf");
		test.remove(5);
		System.out.println(test);
		System.out.println(test.isEmpty());
		
		test.addAt(1, "stimmt");
		test.addAt(3, "nach ende");
		System.out.println(test);
		*/
		
		/*
		//Test für die Arraymethoden
		Object[] a = {1, 2, 3};
		
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		
		System.out.println();
		
		Object[] b = addAt(a, 2, 4);
		
		for(int i = 0; i < b.length; i++) {
			System.out.print(b[i]);
		}
		*/
	}
	
	
	//Teilaufgabe f
	
	//(i)
	public static Object[] remove(Object[] a, Object o) {
		
		Object[] b = new Object[a.length];
		
		//a Kopieren
		for(int i = 0; i < b.length; i++) {
			b[i] = a[i];
		}
		
		//objekt suchen und b an der entsprechenden Stelle auf null zeigen lassen
		for(int i = 0; i < b.length; i++) {
			if(b[i].equals(o)) {
				b[i] = null;
				return b;
			}
		}
		
		//falls das Objekt nicht gefunden wurde wird b zurück gegeben (also einfach eine Kopie des originalen Arrays)
		return b;
	}
	
	//(ii)
	/* 
	 * bei einfach verketteten Listen muss die Referenzder  Vorgängerzelle gespeichert werden,
	 * damit die richtige verkettung nicht verloren geht
	 * 
	 * bei doppelt verketteten Listen, müssen mehr Referenzen richtig verändert werden, damit die Liste nicht kaputt geht, dafür braucht man
	 * allerdings den Vorgänger nicht speichern, da durch die Verkettung in beide Richtungen auf den Vorgänger zugegriffen werden kann
	 * 
	 * bei Arrays (zumindest bei der Version, wie ich sie implementiert habe) bleibt das Array gleich groß und daher wird 
	 * Speicher unnötig verbraucht und das Array besitzt unnütze Lücken, die auf null referenzieren
	 * 
	 */
	
	//(iii)
	
	public void addAt(int index, Object o) {
		
		//Am Anfan einfügen, falls der index negativ oder 0 ist
		if(index <= 0) {
			this.insert(o);
			return;
		}
		
		//am Ende einfügen, falls der index größer ist als die aktuelle Listengröße
		if(index >= this.size()) {
			this.append(o);
			return;
		}
		
		//überladene Hilfsmethode
		addAt(index, o, 0, head);
	}
	
	//hilfsmethode
	private void addAt(int index, Object o, int count, Cons2 cons) {
		
		//falls am Index angekommen, Referenzen anpassen und Methode beenden
		if(count == index) {
			Cons2 tmp = new Cons2(o);
			tmp.next = cons;
			tmp.prev = cons.prev;
			cons.prev = tmp;
			tmp.prev.next = tmp;
			return;
		}
		
		//Zählvariale erhöhen und rekursiv aufrufen
		count++;
		addAt(index, o, count, cons.next);
	}
	
	
	
	
	//(iv)
	public static Object[] addAt(Object[] a, int index, Object o) {
		
		//neues Array, das 1 länger ist
		Object[] b = new Object[a.length+1];
		
		//Fall am Anfang einfügen
		if(index <= 0) {
			
			//index 0 = objekt
			b[0] = o;
			
			//Rest füllen
			for(int i = 1; i < b.length; i++) {
				b[i] = a[i-1];
			}
			
			return b;
		}
		
		//Fall am Ende einfügen
		if(index >= a.length) {
			
			//array kopieren
			for(int i = 0; i < a.length; i++) {
				b[i] = a[i];
			}
			
			//letztes Element ist das neue Objekt
			b[a.length] = o;
			
			return b;
		}
		
		//Fall irgendwo im Array einfügen
		
		//bis zum Index füllen
		for(int i = 0; i < index; i++) {
			b[i] = a[i];
		}
		
		//am Index das Objekt enfügen
		b[index] = o;
		
		//Rest füllen
		for(int i = index+1; i < b.length; i++) {
			b[i] = a[i-1];
		}
		
		return b;
	}
	
	//(v)
	/*
	 * Bei einer Liste müssen nur so viele Berechnungsschritte durchgeführt werden, bis das Element eingefügt wurde
	 * Die Berechnung für ganz vorne oder ganz hinten ist minimal, da dafür der head und foot aushelfen
	 * Im schlimmsten Fall ist der Index kurz vor Ende, dann wäre der Aufwand am größten, da immer beim head begonnen wird
	 * 
	 * Bei einem Array jedoch, muss trotzdem jedes mal das komplette Array durchgegangen werden, da alle Elemente in ein Array kopiert
	 * werden müssen, das eine Stelle mehr Platz hat als das ursprüngliche Array, deswegen  ist selbst die Berechnung für das einfügen 
	 * direkt am Anfang oder direkt am Ende ziemlich aufwendig
	 * 
	 * Bei Arrays müssen die Elemente auch Zwischengespeichert werden, was deutlich mehr Speicherplatz benötigt als eine Liste
	 * 
	 * die Implementierung bei einfach verketten und doppelt verketten Listen ist im Prinzip genau gleich
	 * 
	 */
}
