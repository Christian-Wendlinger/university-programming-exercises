//Binarbaum aus Moodle - hinzugefügte Methoden: Größe des Baumes, Tiefe, Breitendurchlauf, Spiegeln + diverse Hilfsmethoden und Hilfsvariablen + Testmain (4 Bäume, auskommentiert)
//Onur, Christian

public class BinTree {
	
	public Node root; 
	
	//Zählvariable für Knoten
	private static int nodes;
	
	//Zählvariable für die Höhe
	private static int height;
	

	public BinTree() {
		root = null;
	}

	public boolean contains(int z) {
		return contains(root, z);
	}

	protected boolean contains(Node node, int z) {
		if (node == null)
			return false;
		if (node.zahl == z)
			return true;
		return contains(node.left, z) || contains(node.right, z);
	}
	
	public void preorder() {
	    preorder(root);
	    System.out.println();
	  }

	public void preorder(Node node) {
		if (node == null)
			return;
		do_something(node.zahl);
		preorder(node.left);
		preorder(node.right);
	}

	public void inorder() {
	    inorder(root);
	    System.out.println();
	  }
	
	public void inorder(Node node) {
		if (node == null)
			return;
		inorder(node.left);
		do_something(node.zahl);
		inorder(node.right);
	}
	
	public void postorder() {
	    postorder(root);
	    System.out.println();
	  }

	public void postorder(Node node) {
		if (node == null)
			return;
		postorder(node.left);
		postorder(node.right);
		do_something(node.zahl);
	}

	public void do_something(Object o) {
		System.out.print(o + " ");
	}
	
	public static void main(String[] args) {
		/*
		//Testbaum 1
		BinTree t = new BinTree();
	    Node n1 = new Node(1);
	    Node n2 = new Node(2);
	    Node n3 = new Node(3);
	    Node n4 = new Node(4);
	    Node n5 = new Node(5);
	    Node n6 = new Node(6);
	    Node n7 = new Node(7);
	    t.root = n1;
	    n1.left = n2;
	    n1.right = n3;
	    n2.left = n4;
	    n2.right = n5;
	    n3.left = n6;
	    n3.right = n7;
	   // t.preorder();
	   // t.inorder();
	   // t.postorder();
	    
	    System.out.println(t.size());
	    System.out.println(t.depth());
	    t.printB();
	    System.out.println();
	    
	    t.mirror();
	    System.out.println(t.size());
	    System.out.println(t.depth());
	    t.printB();
	    System.out.println();
	    */
	    
		/*
	    //Testbaum 2
	    BinTree t2 = new BinTree();
	    Node n11 = new Node(1);
	    Node n21 = new Node(2);
	    Node n31 = new Node(3);
	    Node n41 = new Node(4);
	    Node n51 = new Node(5);
	    Node n61 = new Node(6);
	    Node n71 = new Node(7);
	    Node n81 = new Node(8);
	    Node n91 = new Node(9);
	    
	    t2.root = n11;
	    n11.left = n21;
	    n21.right = n81;
	    n11.right = n31;
	    n31.left = n41;
	    n41.left = n51;
	    n41.right = n61;
	    n81.right = n71;
	    n21.left = n91;
	    
	    System.out.println(t2.size());
	    System.out.println(t2.depth());
	    t2.printB();
	    System.out.println();
	    t2.mirror();
	    System.out.println(t2.size());
	    System.out.println(t2.depth());
	    t2.printB();
	    System.out.println();
	    */
	    
	    /*
	    //Testbaum 3
	    BinTree t3 = new BinTree();
	    Node n12 = new Node(1);
	    Node n22 = new Node(2);
	    Node n32 = new Node(3);
	    Node n42 = new Node(4);
	    Node n52 = new Node(5);
	    Node n62 = new Node(6);
	    Node n72 = new Node(7);
	    Node n82 = new Node(8);
	    Node n92 = new Node(9);
	    Node n102 = new Node(10);
	    Node n112 = new Node(11);
	    Node n122 = new Node(12);
	    Node n132 = new Node(13);
	    
	    t3.root = n12;
	    n12.left = n22;
	    n22.right = n82;
	    n12.right = n32;
	    n32.left = n42;
	    n42.left = n52;
	    n42.right = n62;
	    n82.right = n72;
	    n22.left = n92;
	    n92.left = n102;
	    n102.left = n112;
	    n102.right = n122;
	    n52.right = n132;
	    
	    System.out.println(t3.size());
	    System.out.println(t3.depth());
	    t3.printB();
	    System.out.println();
	    t3.mirror();
	    System.out.println(t3.size());
	    System.out.println(t3.depth());
	    t3.printB();
	    System.out.println();
	    */
		
		/*
		//Testbaum 4 (leerer Baum)
		BinTree t4 = new BinTree();
		
		System.out.println(t4.size());
		System.out.println(t4.depth());
		t4.printB();
		System.out.println();
		t4.mirror();
		System.out.println(t4.size());
		System.out.println(t4.depth());
		t4.printB();
		System.out.println();
		*/
	}
	
	
	//Anzahl der Knoten
	public int size() {
		nodes = 0;
		return size(root);
	}
	
	//Hilfsmethode
	private int size(Node node) {
		
		//gibt die Knoten zurück, wenn ein Pfad durchlaufen wurde
		if(node == null) {
			return nodes;
		}
		
		//erhöht die Anzahl der Knoten und geht in die Teilbäume
		nodes++;
		size(node.left);
		size(node.right);
		
		//gibt das Endergebnis zurück
		return nodes;
	}
	
	//Tiefe 
	public int depth() {
		height = 0;
		return depth(root, 0);
	}
	
	//Hilfsmethode
	private int depth(Node node, int count) {
		
		//Zählvariable zurückgeben, sobald ein Ast durchlaufen wurde
		if(node == null) {
			return count;
		}
		
		//Die Zählvariable wird nur erhöht, wenn Nachfolger existieren
		if(node.left != null || node.right != null) {
			count++;
		}
		
		//wenn der Ast tiefer ist, als die anderen bisher wird der globale counter erhöht
		if(count > height) {
			height = count;
		}
		
		//Teilbäume betrachten
		depth(node.left, count);
		depth(node.right, count);
		
		
		//Das globale Ergebnis zurückgeben
		return height;
	}
	
	
	//Breitendurchlauf ausgeben
	public void printB() {
		
		// falls Baum leer, beenden
		if(root == null) {
			return;
		}
		
		//Liste erstellen und Wurzel einfügen
		ObjectConsList knoten = new ObjectConsList();
		knoten.append(root);
		
		
		//Liste auffüllen
		this.printB(knoten.head, knoten);
	}
	
	//Füllen:
	private void printB(ObjectCons cons, ObjectConsList knoten) {
		
		//wenn kein Element mehr folgt beenden
		if(cons == null) {
			return;
		}
		
		//Hilfsknoten (referenziert auf den Knoten in der Liste)
		Node node = (Node) cons.obj;
		
		//Ausgabe des Inhalts
		System.out.print(node.zahl + " ");
		
		
		//Falls vorhanden linken Nachfolger in die Liste anfügen
		if(node.left != null) {
			knoten.append(node.left);
		}
		//Falls vorhanden rechten Nachfolger in die Liste anfügen
		if(node.right != null) {
			knoten.append(node.right);
		}
		
		//rekursiver Aufruf
		this.printB(cons.next, knoten);
		
		/*
		 * 	Auf diese Weise wird eine Liste erstellt, die in der richtigen Reihenfolge wächst
		 * 	und zeitgleich ausgegeben wird
		 */
	}
	
	//Baum spiegeln
	public void mirror() {
		
		//falls der Baum leer ist, ist nichts zu tun
		if(root == null) {
			return;
		}
		
		mirror(root);
	}
	//Hilfsmethode
	private void mirror(Node node) {
		
		//keine Nachfolger
		if(node.left == null && node.right == null) {
			return;
		}
		
		
		//Zwei Nachfolger
		if(node.left != null && node.right != null) {
			Node tmp = node.left;
			node.left = node.right;
			node.right = tmp;
		}
		
		
		//nur linker Nachfolger
		if(node.left != null && node.right == null) {
			node.right = node.left;
			node.left = null;
		}
		
		
		//nur rechter Nachfolger
		if(node.left == null && node.right != null) {
			node.left = node.right;
			node.right = null;
		}
		
		//rekursiver Aufruf mit existierenden Nachfolgern
		if(node.left != null) {
			this.mirror(node.left);
		}
		if(node.right != null) {
			this.mirror(node.right);
		}
	}
}
