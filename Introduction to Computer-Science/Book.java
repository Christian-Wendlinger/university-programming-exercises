//Die Buchklasse von Melanie und Christian

public class Book {
	
	//Zählvariable
	private static int erzeugt = 1;
	
	//Attribute
	private int id;
	private Author autor;
	private String titel;
	
	//b) wenn man den Autor nachträglich bearbeitet, dann wird der Autor automatisch angepasst
	public Book(Author autor, String titel) {
		this.autor = autor;
		this.titel = titel;
		this.id = erzeugt;
		erzeugt++;
	}
	
	//gibt das Autorobjekt zurück
	public Author getBookAuthor() {
		return this.autor;
	}
	
	//gibt den Titel zuürck
	public String getBookTitle() {
		return this.titel;
	}
	
	//gibt die ID zurück
	public int getID() {
		return this.id;
	}
	
	//gibt den vollständigen Namen des Autors und den Titel zurück
	public String toString() {
		return this.autor.toString() + " - " + this.getBookTitle();
	}
	
}
