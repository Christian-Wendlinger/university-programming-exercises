//Box Klasse
//Onur, Christian

public class Box {
	
	//Attribute
	private double height;
	private double width;
	private double depth;
	private double weight;
	
	
	
	//get Methoden
	public double getHeight() {
		return this.height;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public double getDepth() {
		return this.depth;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	
	//Konstruktor
	public Box(double height, double width, double depth, double weight) {
		
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.weight = weight;
	}
	
	
	//Rotationsmethoden - 6 Mögliche Rotationen, wobei eine die ursprüngliche darstellt
	
	//Gewicht bleibt immer unverändert
	
	
	//keine Rotation
	public Box rotate1() {
		
		//neue Box, unverändert
		Box tmp = new Box(this.height, this.width, this.depth, this.weight);
		
		//gedrehte Box zurückgeben
		return tmp;
	}
	
	//Drehung 90° nach links
	public Box rotate2() {
		
		//Tiefe und Breite tauschen
		Box tmp = new Box(this.height, this.depth, this.width, this.weight);
		
		return tmp;
	}
	
	//Drehung 90° nach links + aufstellen
	public Box rotate3() {
		
		//alle 3 Attribute tauschen
		Box tmp = new Box(this.depth, this.height, this.width, this.weight);
		
		return tmp;
	}
	
	//Drehung 90° nach vorne
	public Box rotate4() {
		
		//Höhe und Tiefe tauschen
		Box tmp = new Box(this.depth, this.width, this.height, this.weight);
		
		return tmp;
	}

	//Drehung 90° nach vorne + Drehung 90° nach links
	public Box rotate5() {
		
		//Alle 3 Attribute tauschen
		Box tmp = new Box(this.width, this.depth, this.height, this.weight);
		
		return tmp;
	}
	
	//nach links aufstellen
	public Box rotate6() {
		Box tmp = new Box(this.width, this.height, this.depth, this.weight);
		
		return tmp;
	}
}
