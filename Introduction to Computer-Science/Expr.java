//Christian
//Expr Klasse um eine Methode Connect erweitert

public class Expr {
	
    private enum Type {ATOM, LIST};
    
    private Type type;
    private Expr[] elements = null;
    private String value = "";
    
    
    private Expr(Type type, String value) {
    	
    	this.type = type;
        this.value = value;
        
        if (type == Type.LIST)
          this.elements = new Expr[0];
    }

    public static Expr createAtom(String s) {
        Expr e = new Expr(Type.ATOM, s);
        return e;
    }
    
    public static Expr createList() {
        Expr e = new Expr(Type.LIST, "");
        return e;
    }
    
    public void addListElement(Expr e) {
        if (type != Type.LIST) {
            System.err.println("elements can only be added to lists!");
            return;
        }
        
        Expr[] newElements = new Expr[elements.length+1];
        for (int i = 0; i < elements.length; ++i) {
            newElements[i] = elements[i];
        }
        newElements[newElements.length-1] = e;
        
        elements = newElements;
    }
    
    @Override
    public String toString() {
        if (type == Type.ATOM) {
            return value;
        } else {
            String result = "(";
            for (int i = 0; i < elements.length; ++i) {
                if (i != 0) result += " ";
                result += elements[i].toString();
            }
            return result + ")";
        }
    }

    private Expr cmdList() {
        Expr result = Expr.createList();
        for (int i = 1; i < elements.length; ++i)
            result.addListElement(elements[i]);
        return result;
    }

    private Expr cmdConcat() {
        Expr result = elements[1];
        for (int i = 0; i < elements[2].elements.length; ++i)
            result.addListElement(elements[2].elements[i]);
        return result;
    }
    
    private Expr cmdReplace() {
        Expr result = Expr.createList();
        for (int i = 0; i < elements[3].elements.length; ++i)
            if (elements[1].toString().equals(elements[3].elements[i].toString()))
                result.addListElement(elements[2]);
            else
                result.addListElement(elements[3].elements[i]);
        return result;
    }

    // (nth  2   (list a b c))  <--- type: LIST
    // ([0] [1]  [2]         )
    // elements[2]: beim Aufruf von nth ist es bereits evaluiert 
    // --> (nth 2 (a b c)) --> evaluiert zu c
    
    private Expr cmdNth() {
        // Testausgabe: 
        // System.out.println(elements[2].elements[Integer.parseInt(elements[1].toString())]);
        return elements[2].elements[Integer.parseInt(elements[1].toString())];
    }
    
    private Expr cmdReverse() {
        Expr tmp = Expr.createList();
        for (int i = elements[1].elements.length-1; i >= 0; --i)
            tmp.addListElement(elements[1].elements[i]);
        return tmp;
    }
    
    private Expr flatten() {
        if (type == Type.ATOM) {
            Expr result = Expr.createList();
            result.addListElement(this);
            return result;
        }
        
        Expr result = Expr.createList();
        
        for (int i = 0; i < elements.length; ++i) {
            for (Expr exp : elements[i].flatten().elements)
                result.addListElement(exp);
        }
        
        return result;
    }
    
    private Expr cmdFlatten() {
        if (type == Type.ATOM) {
            Expr result = Expr.createList();
            result.addListElement(this);
            return result;
        }
        
        return elements[1].flatten();
    }
    
    
    //verbindet 2 Atome zu einem
    private Expr cmdConnect() {
    	
    	//es muss eine Liste sein
    	if(type != Type.LIST) {
    		System.err.println("Error: only expressions in lists can be connected!");
    		return null;
    	}
    	
    	//Die beiden Elemente müssen Atome sein
    	if(elements[1].type == Type.LIST || elements[2].type == Type.LIST) {
    		System.err.println("Error: only Atoms can be connected!");
    		return null;
    	}
    	
    	//Mehr oder weniger als 2 Atome in der Liste
    	if(elements.length != 3) {
    		System.err.println("Error: only two Atoms allowed!");
    		return null;
    	}
    	
    	//Ergebnisstring und Hilfsarrays mit den Buchstaben der Atome
    	String result = "";
    	char[] val1 = elements[1].value.toCharArray();
    	char[] val2 = elements[2].value.toCharArray();
    	
    	//String mit den Buchstaben aus Array 1
    	for(int i = 0; i < val1.length; i++) {
    		result += "" + val1[i];
    	}
    	
    	//String mit den Buchstaben aus Array 2
    	for(int i = 0; i < val2.length; i++) {
    		result += "" + val2[i];
    	}
    	
    	//neue Expression - ein Atom mit value des verbundenen Strings
    	Expr erg = createAtom(result);
    	return erg;
    	
    }
    
    //Dokumentation des Verhaltens der Methode
    /*
     * Funktion: es wird eine Liste mit 3 Elementen übergeben
     * 
     * Paramater: das erste Element ist das Zeichen '&'
     * 			  darauf folgen 2 Atome
     * 
     * Rückgabe: Die Methode verknüpft nun beiden Atome zu einem einzigen ohne Leerzeichen
     * 			 Zurückgegeben wird also ein neues Atom
     * 
     * Fehlerfälle: Es wird keine Liste übergeben (eigentlich aber schon durch die evalmethode selsbt ausgeschlossen
     * 				Asugegeben wird eine Fehlermeldung und null returnt
     * 
     * 				Bei einem der beiden Atome handelt es sich um eine Liste:
     * 				Es wird auch eine Fehlermeldung ausgegeben und null returnt
     * 
     * 				Es werden mehr als 3 oder weniger als 2 Atome übergeben
     * 				Es wird auch eine Fehlermeldung ausgegeben und null returnt 
     * 
     */
    
    public Expr eval() {
        if (type == Type.ATOM)
            return this;

        for (int i = 0; i < elements.length; ++i) {
            elements[i] = elements[i].eval();
        }
    
        switch (elements[0].toString()) {
        case "#":
            return cmdList();
        case "$":
            return cmdNth();
        case "~":
            return cmdReverse();
        case "+":
            return cmdConcat();
        case "%":
            return cmdReplace();
        case "*":
            return cmdFlatten();
        
        //eigener Befehl
        case "&":
        	return cmdConnect();
        default:
            System.err.println("Could not evaluate list: " + toString());
            return null;
        }
    }
    
    public Expr evalVerbose() {
      if (type == Type.ATOM)
          return this;

      for (int i = 0; i < elements.length; ++i) {
          // System.out.println("e" + i + ": " + elements[i]);
          elements[i] = elements[i].evalVerbose();
          // System.out.println("e" + i + ": " + elements[i]);
      }
      
      System.out.print(this + " --> ");
  
      Expr e = null;
      switch (elements[0].toString()) {
      case "#":
          e = cmdList();
          break;
      case "$":
          e = cmdNth();
          break;
      case "~":
          e = cmdReverse();
          break;
      case "+":
          e = cmdConcat();
          break;
      case "%":
          e = cmdReplace();
          break;
      case "*":
          e = cmdFlatten();
          break;
      default:
          System.err.println("Could not evaluate list: " + toString());
          return null;
      }
      System.out.println(e);
      return e;
  }
    
  public static void main(String[] args) {

	  
	//Liste
    Expr l1 = createList();
    
    Expr l2 = createList();
    
    Expr l3 = createList();

    // (& abcde cd)
    l1.addListElement(createAtom("&"));
    l1.addListElement(createAtom("abcde"));
    l1.addListElement(createAtom("cd"));
    
    //(& hello world)
    l2.addListElement(createAtom("&"));
    l2.addListElement(createAtom("hello"));
    l2.addListElement(createAtom("world"));
    
    
    //(& wie geht es) - Fehler
    l3.addListElement(createAtom("&"));
    l3.addListElement(createAtom("wie"));
    l3.addListElement(createAtom("geht"));
    l3.addListElement(createAtom("es"));
    
    // Liste 1 ausgeben
    System.out.println("l1 = " + l1);
    
    // Liste 2 ausgeben
    System.out.println("l2 = " + l2);
    
    // Das Ergebnis der Evaluation der Liste l1 ausgeben
    System.out.println("Result l1: " + l1.eval());
    
    // Das Ergebnis der Evaluation der Liste l2 ausgeben
    System.out.println("Result l2: " + l2.eval());
    
    //Liste 3 ausgeben
    System.out.println("l3 = " + l3);
    
    // Das Ergebnis der Evaluation der Liste l3 ausgeben
    System.out.println("Result l3: " + l3.eval());
    
    
  }
}
