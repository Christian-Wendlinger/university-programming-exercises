//Node-Klasse für die Tutoriumsaufgabe
//Onur, Christian

public class GraphNode {
	
	//Attribute
	public String name;
	public ObjectConsList nachbarn;
	public int tutorium;
	
	//Konstruktor
	public GraphNode(String name) {
		this.name = name;
		this.nachbarn = new ObjectConsList();
		this.tutorium = -1;
	}
	
	//equals @Override, vergleicht Namen und die Liste der Nachbarn
	public boolean equals(GraphNode node) {
		return this.name.equals(node.name) && this.nachbarn.equals(node.nachbarn); 
	}
}
