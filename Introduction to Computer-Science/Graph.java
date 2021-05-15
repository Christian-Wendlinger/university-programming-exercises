//erstellen, merge und topologische Sortierung eines Graphen
//Onur, Christian


public class Graph {
	
	//Attribut
	private ObjectConsList ereignisse; 
	
	
	/*
	 * 	Falls mehr als 4 Ereignisse von einer Peron zu protokoll gegeben wird dann müssen neue Kontruktoren nach demselben
	 * 	Prinzip geschrieben werden, die restlichen Methoden funktionieren aber gleich
	 */
	
	//erstellt aus den 4 eingegebenen Ereignissen 4 Knoten und fügt diese in eine Liste ein, welche dann dem Attribut des Graphen zugewiesen wird
	public Graph(String e1, String e2, String e3, String e4) {
		GraphNode n1 = new GraphNode(e1);
		GraphNode n2 = new GraphNode(e2);
		n1.nachbarn.append(n2);
		GraphNode n3 = new GraphNode(e3);
		n2.nachbarn.append(n3);
		GraphNode n4 = new GraphNode(e4);
		n3.nachbarn.append(n4);
		
		ObjectConsList nodes = new ObjectConsList();
		nodes.append(n1);
		nodes.append(n2);
		nodes.append(n3);
		nodes.append(n4);
		
		this.ereignisse = nodes;
	}
	
	public void merge(Graph g2) {
		this.merge(this.ereignisse.head, g2.ereignisse.head, g2);
	}
	
	private void merge(ObjectCons c1, ObjectCons c2, Graph g2) {
		
		//Zweiter Graph einmal durch
		if(c2 == null) {
			this.merge(c1.next,g2.ereignisse.head, g2);
			return;
		}
		
		//Erste Liste komplett durchlaufen
		if(c1 == null) {
			return;
		}
		
		
		//Hilfsknoten
		GraphNode n1 = (GraphNode)c1.obj;
		GraphNode n2 = (GraphNode)c2.obj;	
		
		//Übereinstimmung gefunden, nächster Knoten aus g1 mit allen Knoten aus g2 vergleichen
		if(n1.name.equals(n2.name)) {
			
			//es existiert ein Nachfolgeknoten
			if(c2.next != null) {
				
				GraphNode tmp = (GraphNode) c2.next.obj;
				
				//noch nicht verbunden
				if(!(n1.nachbarn.contains(tmp))) {
					
					//Nachbar anfügen
					n1.nachbarn.append(tmp);
				}
				
			}		
			//weiter mit dem nächsten Knoten des Graphen
			this.merge(c1.next, g2.ereignisse.head, g2);
			return;
		}
		
		//weiter mit Nachbarn des aktuellen Knotens
		if(n1.nachbarn.head != null) {
			this.merge(n1.nachbarn.head, g2.ereignisse.head, g2);
		}
		
		//keine Übereinstimmung gefunden, aktuellen Knoten mit nächstem Knoten aus g2 vergleichen
		this.merge(c1, c2.next, g2);
		
	}
	
	public void printTopoSort() {
		//Alle Knoten auflisten
		ObjectConsList knoten = this.makeList();
		
		//Vorgängerzahl pro Knoten ermitteln
		count(knoten, knoten.head);
		
		
		//Reihenfolge der Knoten in einem String speichern
		String erg = "";
		
		while(true) {
			
			if(checkDone(knoten)) {
				System.out.println(erg);
				return;
			}
			
			//kein Knoten ohne Vorgänger gefunden
			if(!(checkZero(knoten))) {
				System.out.println("Kann nicht topologisch sortiert werden.");
				return;
			}
			
			erg = makeStep(knoten, erg);
		}
	}
	
	private ObjectConsList makeList() {
		
		//neue Liste für allen Knoten
		ObjectConsList knoten = new ObjectConsList();
		
		// nötig, da es Knoten die gibt, die gleich heißen aber nicht alle Nachbarn enthalten
		// die ersten verbundenen Knoten enthalten aber alle Nachbarn
		ObjectConsList check = new ObjectConsList();
		
		makeList(knoten, check, this.ereignisse.head);
		return knoten;
	}
	
	//Alle Knoten in eine Liste bringen
	private void makeList(ObjectConsList cons, ObjectConsList check, ObjectCons c1) {
		
		//Ende der Liste
		if(c1 == null) {
			return;
		}
		
		GraphNode n1 = (GraphNode) c1.obj;
		
		//neuer Knoten gefunden
		if(!(cons.contains(n1)) && !(check.contains(n1.name))) {
			cons.append(n1);
			check.append(n1.name);
		}
		
		//falls er keine Nachbarn hat beenden, ansonsten rekursiv für Nachbarn aufrufen
		if(n1.nachbarn.head != null) {
			makeList(cons, check, n1.nachbarn.head);
			return;
		}
		
		//weiter mit dem nächsten Knoten
		makeList(cons, check, c1.next);
	}
	
	//Knoten durchgehen
	private void count(ObjectConsList knoten, ObjectCons cons) {
		
		//Ende erreicht
		if(cons == null) {
			return;
		}
		
		//Hilfsknoten
		GraphNode node = (GraphNode) cons.obj;
		
		//Nachbarn des Knoten erhalten +1 Vorgänger
		add(knoten, node.nachbarn.head);
		
		//nächster Knoten
		count(knoten, cons.next);
	}
	
	//Nachbarn durchgehen
	private void add(ObjectConsList knoten, ObjectCons c1) {
		
		//Ende erreicht
		if(c1 == null) {
			return;
		}
		
		//Eingänge erhöhen
		GraphNode hlp = (GraphNode) c1.obj;
		String tmp = hlp.name;
		add(knoten.head, tmp);
		
		//rekursiver Aufruf
		add(knoten, c1.next);
	}
	
	//entsprechenden Nachbarn in der HauptListe finden und dessen Vorgängerzahl dann erhöhn
	private void add(ObjectCons c1, String name) {
		
		//Ende erreicht - sollte niemals passieren
		if(c1 == null) {
			return;
		}
		
		//Knoten gefunden
		GraphNode hlp = (GraphNode) c1.obj;
		if(hlp.name.equals(name)) {
			hlp.eingang++;
			return;
		}
		
		//Knoten nicht gefunden
		add(c1.next, name);
		
		
	}
	
	//Überprüft, ob ein Knoten ohne Vorgänger existiert
	private boolean checkZero(ObjectConsList knoten) {
		return checkZero(knoten.head);
	}
	
	private boolean checkZero(ObjectCons c1) {
		
		//keine 0 gefunden
		if(c1 == null) {
			return false;
		}
		
		//0 gefunden
		GraphNode tmp = (GraphNode) c1.obj;
		
		if(tmp.eingang == 0) {
			return true;
		}
		
		return checkZero(c1.next);
	}
	
	
	//prüft ob alle Knoten ausgegeben wurden
	private boolean checkDone(ObjectConsList knoten) {
		return checkDone(knoten.head);
	}
	
	private boolean checkDone(ObjectCons c1) {
		
		//alle Knoten durch
		if(c1 == null) {
			return true;
		}
		
		
		GraphNode tmp = (GraphNode) c1.obj;
		
		//Noch nicht alle Knoten durch
		if(tmp.eingang != -1) {
			return false;
		}
		
		//rekursiver Aufruf
		return checkDone(c1.next);
	}
	
	
	private String makeStep(ObjectConsList knoten, String erg) {
		return makeStep(knoten, knoten.head, erg);
	}
	
	private String makeStep(ObjectConsList knoten, ObjectCons cons, String erg) {
		
		//sollte nicht passieren
		if(cons == null) {
			return erg;
		}
		
		GraphNode tmp = (GraphNode) cons.obj;
		
		//Knoten ohne Vorgänger gefunden
		if(tmp.eingang == 0) {
			
			//eingänge auf -1 setzen
			tmp.eingang = -1;
			
			//Eingänge der Nachbarn anpassen
			adjustNeighbours(knoten, tmp.nachbarn.head);
			
			//erg anpassen
			erg += tmp.name + " ";
			return erg;
		}
		
		//aktueller Knoten hat nicht 0 eingänge
		return makeStep(knoten, cons.next, erg);
	}
	
	private void adjustNeighbours(ObjectConsList knoten, ObjectCons cons) {
		
		//Liste durch
		if(cons == null) {
			return;
		}
		
		//von aktuellem Nachbarn den eingang abziehen
		GraphNode tmp = (GraphNode) cons.obj;
		String node = tmp.name;
		adjustNeighbours(knoten.head, node);
		
		//rekursiver Aufruf
		adjustNeighbours(knoten, cons.next);
	}
	
	private void adjustNeighbours(ObjectCons cons, String node) {
		
		//einmal durch
		if(cons == null) {
			return;
		}
		
		//Knoten in der Liste gefunden
		GraphNode tmp = (GraphNode) cons.obj;
		if(tmp.name.equals(node)) {
			tmp.eingang--;
			return;
		}
		
		//rekursiver Aufruf
		adjustNeighbours(cons.next, node);
	}
	
	
	public static void main(String[] args) {
		
		//4 Graphen mit individuellen Zeugenaussagen erstellen
		Graph g1 = new Graph("E","D","C","I");
		Graph g2 = new Graph("E","C","A","J");
		Graph g3 = new Graph("A","H","B","F");
		Graph g4 = new Graph("B","D","G","F");
		
		//Alle Graphen mit dem ersten mergen, so dass jetzt g1 alle Zeugenaussagen enthält
		g1.merge(g2);
		g1.merge(g3);
		g1.merge(g4);
		
		
		g2.printTopoSort();
	}
	
}
