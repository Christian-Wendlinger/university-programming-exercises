//Die Autorenklasse von Melanie und Christian

public class Author {
	
	//Zählt die Objekte
	private static int erzeugt = 1;
	
	//Attribute
	private int id;
	private String vorname;
	private String nachname;
	
	//Konstruktor - id wird automatisch zugewiesen und die Zählvariable erhöht
	public Author(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.id = erzeugt;
		erzeugt++;
	}
	
	//Rückabe des Vornamen
	public String getFirstName() {
		return this.vorname;
	}
	
	//Rückgabe des Nachnamen
	public String getLastName() {
		return this.nachname;
	}
	
	//gibt die ID zurück
	public int getID() {
		return this.id;
	}
	
	//gibt den vollen Namen zurück
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}
	
	// d) ruft die Methode automatisch bei der Ausgabe auf, sobald sie implementiert wurde
}
