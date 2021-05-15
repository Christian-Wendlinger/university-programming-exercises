//Binärer Suchbaum für Wörter
//Onur, Christian

public class ListSearchTree {
  public Node root; // der Wurzelknoten

  //Hilfsvariable
  private static int c;

  public ListSearchTree() {
    root = null;
    /* leerer Baum */ }

  //Knoten in Suchbeim einfügen
  public void insert(Cons2 match) {

	  //erstes Wort
	  if(root == null) {
		  root = new Node(match);
	  }
	  //ansonsten Hilfsmethode
	  else {
		  insert(root, match, new Node(match));
	  }
  }

  //einfügen eines Knoten
  private void insert(Node node, Cons2 match, Node tmp) {

	  //gleiches Wort
	  if(node.wort.equals(tmp.wort)) {
		  node.addMatch(match);
	  }

	  //neues Wort hat kleineren Anfangsbuchstaben (ASCII)-> links
	  else if(node.wort.charAt(0) > tmp.wort.charAt(0)) {
		  //freier Platz = einfügen
		  if(node.left == null) {
			  node.left = tmp;
		  }
		  //weiter links sonst
		  else {
			  insert(node.left, match, tmp);
		  }
	  }
	  //größerer Anfangfsbuchstabe oder gleich -> rechts
	  else {
		  //freier Platz
		  if(node.right == null) {
			  node.right = tmp;
		  }
		  //weiter rechts sonst
		  else {
			  insert(node.right, match, tmp);
		  }
	  }
  }

  //alle matches finden
  public Cons2List findAll(String s) {
	  return findAll(root, s);
  }

  private Cons2List findAll(Node node, String s) {

	  //Wort nicht gefunden
	  if(node == null) {
		  return null;
	  }

	  //Wort gefunden
	  if(node.wort.equals(s)) {
		  return node.liste;
	  }
	  //Anfangsbuchstabe kleiner
	  else if(node.wort.charAt(0) > s.charAt(0)) {
		   return findAll(node.left, s);
	  }
	  //Anfangsbuchstabe größer oder gleich
	  else {
		  return findAll(node.right, s);
	  }
  }


  	//Ausgabe
	public void printMatchContext(String word, int n) {

		//Liste aller Vorkommnisse des gesuchten Wortes
		Cons2List tmp = findAll(word);

		//Wort kommt nicht vor
		if(tmp == null) {
			System.out.println("");
			return;
		}

		printMatchContext(tmp.head, n);
	}

	private void printMatchContext(Cons2 cons, int n) {

		//leer oder durch
		if(cons == null) {
			return;
		}

		//Ausgabe für ein Match
		printMatchContext(cons, n, 2*n+1);

		//rekursiver Aufruf
		printMatchContext(cons.next, n);
	}

	//Ausgabe erzeugen
	private void printMatchContext(Cons2 cons, int counter, int maxCounter) {
		Cons2 tmp = getStart((Cons2) cons.obj, counter);
		String s = getString(tmp, c, maxCounter-1, "");

    //Ausgabe für DOMJudge
		if(cons.next == null) {
			System.out.print(s + "\n");
		}else {
			System.out.print(s + " ");
		}
	}


	//Anfangsposition ermitteln
	private Cons2 getStart(Cons2 cons, int counter) {

		//kein Vorgänger mehr oder 0 erreicht
		if(cons.prev == null || counter == 0) {
			c = counter;
			return cons;
		}

		//rekursiver Aufruf
		counter--;
		return getStart(cons.prev, counter);
	}

	private String getString(Cons2 cons, int count, int maxCounter, String erg) {

		//genug Schritte gemacht
		if(count == maxCounter) {
			erg += cons.obj;
			return erg;
		}

		//Ende erreicht
		if(cons.next == null) {
			erg += cons.obj;
			return erg;
		}

		//Wort anfügen, counter erhöhen und rekursiv Aufrufen
		erg += cons.obj + " ";
		count++;
		return getString(cons.next, count, maxCounter, erg);
	}
}
