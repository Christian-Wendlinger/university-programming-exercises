//Node mit allgemeinen Objekten, statt nur Integern
//Onur, Christian

public class ExpNode {
	 
	  public Object o;
	  public ExpNode left;
	  public ExpNode right;

	  public ExpNode(Object o) {
	    this.o = o;
	    left = right = null;
	  }
	}
