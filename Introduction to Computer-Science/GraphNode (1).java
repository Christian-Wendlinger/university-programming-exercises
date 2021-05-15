//GraphNode Klasse aus Blatt 10 mit zusätzlichem Zähler für die Eingänge
//Onur, Christian

public class GraphNode {
	
	//Attribute
	public String name;
	public ObjectConsList nachbarn;
	
	//zähler für eingehende Kanten
	public int eingang;
	
	
	//Konstruktor
	public GraphNode(String name) {
		this.name = name;
		this.nachbarn = new ObjectConsList();
	}
	
	//equals @Override, vergleicht Namen und die Liste der Nachbarn
	public boolean equals(GraphNode node) {
		return this.name.equals(node.name) && this.nachbarn.equals(node.nachbarn); 
	}

	/*
	public static void main(String[] args) {
		
		GraphNode n1 = new GraphNode("a");
		GraphNode n2 = new GraphNode("b");
		GraphNode n3 = new GraphNode("c");
		
		n1.nachbarn.append(n2);
		n1.nachbarn.append(n3);
		
		n2.nachbarn.append(n3);
		
		GraphNode n4 = new GraphNode("b");
		n4.nachbarn.append(n3);
		
		System.out.println(n2.equals(n4));
	}
	*/
}
