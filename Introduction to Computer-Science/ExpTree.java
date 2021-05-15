//ExpTree - Methoden: evalExp(), toRPNString(), toInfixString(), createExpTreeFromRPN(), main: 4 Ausgaben
//Onur, Christian

import java.util.Scanner;

public class ExpTree {
	
	public ExpNode root; 
	
	//Zählvariable für Knoten
	private static int nodes;
	
	//Zählvariable für die Höhe
	private static int height;
	

	public ExpTree() {
		root = null;
	}

	public boolean contains(Object o) {
		return contains(root, o);
	}

	protected boolean contains(ExpNode node, Object o) {
		if (node == null)
			return false;
		if (node.o.equals(o))
			return true;
		return contains(node.left, o) || contains(node.right, o);
	}
	
	public void preorder() {
	    preorder(root);
	    System.out.println();
	  }

	public void preorder(ExpNode node) {
		if (node == null)
			return;
		do_something(node.o);
		preorder(node.left);
		preorder(node.right);
	}

	public void inorder() {
	    inorder(root);
	    System.out.println();
	  }
	
	public void inorder(ExpNode node) {
		if (node == null)
			return;
		inorder(node.left);
		do_something(node.o);
		inorder(node.right);
	}
	
	public void postorder() {
	    postorder(root);
	    System.out.println();
	  }

	public void postorder(ExpNode node) {
		if (node == null)
			return;
		
		postorder(node.left);
		postorder(node.right);
		do_something(node.o);
	}

	public void do_something(Object o) {
		System.out.print(o.toString() + " ");
	}

	
	//Anzahl der Knoten
	public int size() {
		nodes = 0;
		return size(root);
	}
	
	//Hilfsmethode
	private int size(ExpNode node) {
		
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
	private int depth(ExpNode node, int count) {
		
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
	
	
	//Baum spiegeln
	public void mirror() {
		
		//falls der Baum leer ist, ist nichts zu tun
		if(root == null) {
			return;
		}
		
		mirror(root);
	}
	//Hilfsmethode
	private void mirror(ExpNode node) {
		
		//keine Nachfolger
		if(node.left == null && node.right == null) {
			return;
		}
		
		
		//Zwei Nachfolger
		if(node.left != null && node.right != null) {
			ExpNode tmp = node.left;
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
	
	/*
	public static void main(String[] args) {
		ExpTree exp = new ExpTree(); 
		
		// Wurzelknoten mit Operator * erzeugen 
		exp.root = new ExpNode("*"); 
		
		// Kindknoten der Wurzel erzeugen und an Wurzel anh¨angen 
		ExpNode nPlus = new ExpNode("+"); 
		ExpNode nMinus = new ExpNode("-"); 
		exp.root.left = nPlus; 
		exp.root.right = nMinus;
		
		// Additionsteilbaum erzeugen 
		nPlus.left = new ExpNode(5); 
		nPlus.right = new ExpNode(3);
		
		// Subtraktionsteilbaum erzeugen 
		nMinus.left = new ExpNode(1);
		nMinus.right = new ExpNode(2);
		
		exp.postorder(); 
		exp.inorder();
		
		System.out.println(exp.evalExp());
		System.out.println(exp.toRPNString());
		System.out.println(exp.toInfixString());
	}
	
	/*
	 * 	Ausgabe der main: 
	 * 	5 3 + 1 2 - *   (postorder)
	 * 	5 + 3 * 1 - 2 	(in order)
	 * 
	 * 	Post-Order wird zuerst der Linke Teil ausgewertet, dann der rechte teil und dann die Mitte
	 * 	In- Order wird zuerst der Linke Teil ausgewertet, dann die Mitte und dann der rechte
	 * 	Bei beiden Varianten eben rekursiv
	 * 
	 *  Die Post-Order-Traversierung entspricht der polnischen Notation, wie auf Blatt 9 kennen gelernt
	 *  Die In-Order-Traversierung kommt der uns bekannten Schreibweise aus den Mathevorlesungen am nächsten. Es fehlen lediglich Klammern.
	 */
	
	
	public static void main(String[] args) {
		
		ExpTree baum = new ExpTree();
		baum.createExpTreeFromRPN();
		baum.inorder();
		baum.preorder();
		System.out.println(baum.evalExp());
		System.out.println(baum.toInfixString());
	}
	
	
	
	
	//Baum berechnen
	public int evalExp() {
		return evalExp(root, 0);
	}
	
	//Hilfsmethode
	private int evalExp(ExpNode node, int erg) {
		
		//durchlauf beendet
		if(node == null) {
			return erg;
		}
		
		//Rechenzeichen im Knoten
		if(node.o instanceof String) {
			
			//Rechenzeichen als String speichern und switchen
			String op = (String) node.o;
			
			//je nach Rechenzeichen unterschiedliche Operation
			switch(op) {
			case("+"):	return add(node, erg);
			case("-"):	return sub(node, erg);
			case("*"):	return mul(node, erg);
			case("/"):	return div(node, erg);
			default: return 0;
			}
		}

		return (Integer) node.o;
	
	}
	
	//Addition
	private int add(ExpNode node, int erg) {
		
		Integer i1 = null;
		Integer i2 = null;
			
		//Zahl links
		if(node.left.o instanceof Integer) {
			i1 = (Integer) node.left.o;
		}
		//Rechenzeichen links
		else {
			i1 = evalExp(node.left, erg);
		}
			
		
		//Zahl rechts
		if(node.right.o instanceof Integer) {
			i2 = (Integer) node.right.o;
		}
		//Rechenzeichen rechts
		else {
			i2 = evalExp(node.right, erg);
		}
		
		
		//Ergebnis berechnen und zurückgeben
		erg = i1 + i2;
		return erg;
	}
	
	//Subtraktion
	private int sub(ExpNode node, int erg) {
			
		Integer i1 = null;
		Integer i2 = null;
				
		//Zahl links
		if(node.left.o instanceof Integer) {
			i1 = (Integer) node.left.o;
		}
		//Rechenzeichen links
		else {
			i1 = evalExp(node.left, erg);
		}

		
		//Zahl rechts
		if(node.right.o instanceof Integer) {
			i2 = (Integer) node.right.o;
		}
		//Rechenzeichen rechts
		else {
			i2 = evalExp(node.right, erg);
		}
		
		
		//Ergebnis berechnen und zurückgeben
		erg = i1 - i2;
		return erg;
	}
	

	//Multiplikation
	private int mul(ExpNode node, int erg) {
		
		Integer i1 = null;
		Integer i2 = null;
			
		//Zahl links
		if(node.left.o instanceof Integer) {
			i1 = (Integer) node.left.o;
		}
		//Rechenzeichen links
		else {
			i1 = evalExp(node.left, erg);
		}
			
		
		//Zahl rechts
		if(node.right.o instanceof Integer) {
			i2 = (Integer) node.right.o;
		}
		//Rechenzeichen rechts
		else {
			i2 = evalExp(node.right, erg);
		}
		
		
		//Ergebnis berechnen und zurückgeben
		erg = i1 * i2;
		return erg;
	}
	
	//Division
	private int div(ExpNode node, int erg) {
			
		Integer i1 = null;
		Integer i2 = null;
				
		//Zahl links
		if(node.left.o instanceof Integer) {
			i1 = (Integer) node.left.o;
		}
		//Rechenzeichen links
		else {
			i1 = evalExp(node.left, erg);
		}
			
				
		//Zahl rechts
		if(node.right.o instanceof Integer) {
			i2 = (Integer) node.right.o;
		}
		//Rechenzeichen rechts
		else {
			i2 = evalExp(node.right, erg);
		}
			
		
		//Ergebnis berechnen und zurückgeben
		erg = i1 / i2;
		return erg;
	}
	
	
	//umgekehrte Polnische Notation
	public String toRPNString() {
		return toRPNString(root, "");
	}
	
	//Hilfsmethode 1 - wie Post - Order nur mit zusätzlichem String
	private String toRPNString(ExpNode node, String res) {
		
		if(node == null) {
			return res;
		}
		
		res = toRPNString(node.left, res);
		res = toRPNString(node.right, res);
		res = toRPNString(node.o, res);
		
		return res;
	}
	
	//Hilfsmethode 2
	private String toRPNString(Object o, String res) {
		//Inhalt an den String hängen und Leerzeichen
		return res + o.toString() + " ";
	}
	
	
	
	//geklammerte Ausgabe
	
	/*
	 *	abgewandelte Methode der evalExp() - Methode
	 *	statt Integern werden String zurückgegeben
	 *	und statt Berechnungen werden ensprechende Strings mit Klammern, Rechenzeichen und Leezeichen erstellt 
	 *	und statt Integern für die Ausdrücke, werden nun aussschließlich Strings genutzt
	 */
	public String toInfixString() {
		return toInfixString(root, "");
	}
	
	//Hilfsmethode
	private String toInfixString(ExpNode node, String res) {
		
		//durchlauf beendet
		if(node == null) {
			return res;
		}
		
		//Rechenzeichen im Knoten
		if(node.o instanceof String) {
			
			//Rechenzeichen als String speichern und switchen
			String op = (String) node.o;
			
			//je nach Rechenzeichen unterschiedliche Operation
			switch(op) {
			case("+"):	return add(node, res);
			case("-"):	return sub(node, res);
			case("*"):	return mul(node, res);
			case("/"):	return div(node, res);
			default: return "";
			}
		}
		
		return "" + node.o;
	}
	
	//Addition
	private String add(ExpNode node, String res) {
		
		String i1 = null;
		String i2 = null;
			
		//Zahl links
		if(node.left.o instanceof Integer) {
			i1 = "" + node.left.o;
		}
		//Rechenzeichen links
		else {
			i1 = toInfixString(node.left, res);
		}
			
		
		//Zahl rechts
		if(node.right.o instanceof Integer) {
			i2 = "" + node.right.o;
		}
		//Rechenzeichen rechts
		else {
			i2 = toInfixString(node.right, res);
		}
		
		
		//Ergebnis auswerten und zurückgeben
		res = "(" + i1 + " + " + i2 + ")";
		return res;
	}
	
	//Subtraktion
	private String sub(ExpNode node, String res) {
			
		String i1 = null;
		String i2 = null;
				
		//Zahl links
		if(node.left.o instanceof Integer) {
			i1 = "" + node.left.o;
		}
		//Rechenzeichen links
		else {
			i1 = toInfixString(node.left, res);
		}

		
		//Zahl rechts
		if(node.right.o instanceof Integer) {
			i2 = "" + node.right.o;
		}
		//Rechenzeichen rechts
		else {
			i2 = toInfixString(node.right, res);
		}
		
		
		//Ergebnis auswerten und zurückgeben
		res = "(" + i1 + " - " + i2 + ")";
		return res;
	}
	

	//Multiplikation
	private String mul(ExpNode node, String res) {
		
		String i1 = null;
		String i2 = null;
			
		//Zahl links
		if(node.left.o instanceof Integer) {
			i1 = "" + node.left.o;
		}
		//Rechenzeichen links
		else {
			i1 = toInfixString(node.left, res);
		}
			
		
		//Zahl rechts
		if(node.right.o instanceof Integer) {
			i2 = "" + node.right.o;
		}
		//Rechenzeichen rechts
		else {
			i2 = toInfixString(node.right, res);
		}
		
		
		//Ergebnis auswerten und zurückgeben
		res = "(" + i1 + " * " + i2 + ")";
		return res;
	}
	
	//Division
	private String div(ExpNode node, String res) {
			
		String i1 = null;
		String i2 = null;
		
		//Zahl links
		if(node.left.o instanceof Integer) {
			i1 = "" + node.left.o;
		}
		//Rechenzeichen links
		else {
			i1 = toInfixString(node.left, res);
		}
			
				
		//Zahl rechts
		if(node.right.o instanceof Integer) {
			i2 = "" + node.right.o;
		}
		//Rechenzeichen rechts
		else {
			i2 = toInfixString(node.right, res);
		}
			
		
		//Ergebnis auswerten und zurückgeben
		res = "(" + i1 + " / " + i2 + ")";
		return res;
	}
	
	
	//Baum erstellen
	public void createExpTreeFromRPN() {
		
		//scanner + neue Liste
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		ObjectConsList ausdruck = new ObjectConsList();
		
		//Schleife, die je nach eingabe die Liste füllt und mit exit beendet wird 
		while(!exit) {
			
			String eingabe = scanner.nextLine();
			
			//Aufbau wie ein Stack
			switch(eingabe) {
			
			//die letzen Zwei Zahlen vom Stack nehmen (welche bereits Nodes sind)
			case("+"): ExpNode n1 = (ExpNode) ausdruck.removeHead();
					   ExpNode n2 = (ExpNode) ausdruck.removeHead();
					   
					   //neue Node mit dem Operator
					   ExpNode n3 = new ExpNode("+");
					   
					   //die Zahlen links und rechts an den Operator hängen
					   n3.left = n2;
					   n3.right = n1;
					   
					   //neuen Teilbaum auf den Stack legen
					   ausdruck.insert(n3);
			break;
			
			//andere Operatoren Analog
			case("-"): ExpNode n11 = (ExpNode) ausdruck.removeHead();
					   ExpNode n21 = (ExpNode) ausdruck.removeHead();
			           ExpNode n31 = new ExpNode("-");
			           n31.left = n21;
			           n31.right = n11;
			           ausdruck.insert(n31);
			break;
			case("*"): ExpNode n12 = (ExpNode) ausdruck.removeHead();
					   ExpNode n22 = (ExpNode) ausdruck.removeHead();
					   ExpNode n32 = new ExpNode("*");
					   n32.left = n22;
					   n32.right = n12;
					   ausdruck.insert(n32);
			break;
			case("/"): ExpNode n13 = (ExpNode) ausdruck.removeHead();
			   		   ExpNode n23 = (ExpNode) ausdruck.removeHead();
			   		   ExpNode n33 = new ExpNode("/");
			   		   n33.left = n23;
			   		   n33.right = n13;
			   		   ausdruck.insert(n33);
			break;
			
			//Eingabe beenden
			case("exit"): exit = true;
						  scanner.close();
			break;
			
			//Node mit Zahl erstellen und auf den Stack legen
			default: ausdruck.insert(new ExpNode(Integer.parseInt(eingabe)));
			}
			
			//Wurzel des Baumes auf das verbleibende Element der Liste
			root = (ExpNode) ausdruck.head.obj;
		}
		
	}
}
