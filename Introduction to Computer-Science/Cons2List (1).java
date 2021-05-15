//Cons2Liste mit angepasster append-Methode und public Referenzen;
//containsHost - Methode für die DNSHashMap Klasse

public class Cons2List {
  public Cons2 head, foot; // Kopf und Fuss der
                            // Liste

  public Cons2List() {
    head = foot = null;
    /* neue leere Liste */ }

  public boolean contains(Object obj) {
    return contains(head, obj);
  }

  protected boolean contains(Cons2 cons,
      Object obj) {
    // Keine Aenderung noetig
    if (cons == null)
      return false;
    else if (cons.obj.equals(obj))
      return true;
    else
      return contains(cons.next, obj);
  }

//containsMethode für DNSHashMap
public boolean containsHost(String host) {
  return containsHost(host, head);
}

private boolean containsHost(String host, Cons2 cons) {

  //host nicht gefunden
  if(cons == null) {
    return false;
  }

  //host gefunden
  String[] tmp = (String[]) cons.obj;
  if(tmp[0].equals(host)) {
    return true;
  }
  //weitersuchen
  else {
    return containsHost(host, cons.next);
  }
}


  public void print() {
    System.out.print("Liste " + this);
  }

  public void insert(Object obj) {
    Cons2 cons = new Cons2(obj); // neue
                                 // Cons-Zelle
    cons.next = head; // vorne anfuegen..
    // NEU: Rueckverweis
    if (head != null)
      head.prev = cons;
    head = cons; // .. und Kopf der Liste anpassen
    if (foot == null)
      foot = cons; // eventuell auch den Fuss
  }


  //angepasst, Cons2 Zelle wird zurückgegeben
  public Cons2 append(Object obj) {
    Cons2 cons = new Cons2(obj);

    if (foot == null)
      head = foot = cons;
    else {
      foot.next = cons;
      cons.prev = foot;
      foot = cons;
    }

    return cons;
  }

  public void remove(Object obj) {
    // NEU: vorherige Cons-Zelle nicht mehr
    // benoetigt
    remove(head, obj);
  }

  protected void remove(Cons2 cons, Object obj) {
    if (cons == null)
      return;
    if (cons.obj.equals(obj)) {
      // vorherige Cons-Zelle auf Nachfolgende
      // zeigen lassen,
      // somit faellt 'cons' aus der Liste
      if (cons.prev != null)
        cons.prev.next = cons.next;
      else // kein Vorgaenger = Kopf
        head = cons.next;
      // Rueckverweis
      if (cons.next != null)
        cons.next.prev = cons.prev;
      else // kein Nachfolger = foot
        foot = cons.prev;
    } else
      remove(cons.next, obj);
  }

  public boolean isEmpty() {
    return head == null;
  }

  public Object removeHead() {
    if (head == null)
      return null;
    Object res = head.obj;
    if (head == foot)
      head = foot = null;
    else {
      head = head.next;
      // Rueckverweis
      if (head != null)
        head.prev = null;
    }
    return res;
  }

  public int size() {
    Cons2 cons = head;
    int res = 0;
    while (cons != null) {
      res++;
      cons = cons.next;
    }
    return res;
  }

  public String toString() {
    Cons2 c = this.head;
    String result = "[";
    while (c != null) {
      result += c.obj;
      if (c.next != null)
        result += ", ";
      c = c.next;
    }
    return result + "]";
  }
}
