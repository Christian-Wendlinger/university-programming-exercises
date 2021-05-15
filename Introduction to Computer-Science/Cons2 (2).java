//unverändert
//Onur, Christian

public class Cons2 {
  public Object obj; // das Objekt in dieser Zelle
  public Cons2 next; // Verweis auf die naechste
                     // Zelle
  public Cons2 prev; // Verweis auf vorherige
                     // Zelle

  public Cons2(Object obj) {
    this.obj = obj;
    next = prev = null;
  }

  public String toString() {
    return obj.toString();
  }
}
