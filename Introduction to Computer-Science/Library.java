// Die Libraryklasse von Melanie und Christian

import java.util.Scanner;

public class Library {
	
	//Attribute
	private Author[] autoren;
	private Book[] buecher;
	
	//Konstruktor
	private Library(int a, int b) {
		this.autoren = new Author[a];
		this.buecher = new Book[b];
	}
	
	//Standartkonstruktor, der Arrays mit der Länge 1000 initialisiert
	private Library() {
		this.autoren = new Author[1000];
		this.buecher = new Book[1000];
	}
	
	//gibt den Author mit der entsprechenden ID zurück
	public Author getAuthor(int id) {
		
		//geht einmal das Array durch
		for(int i = 0;i < autoren.length; i++) {
			
			//falls die ID übereinstimmt wird der Author zurück gegeben
			if(autoren[i].getID() == id) {
				return autoren[i];
			}
		}
		//Falls die Überprüfung erfolglos war, wird null zurück gegeben
		return null;
	}
	
	
	public void printBibliography(int id) {
		
		//sucht den Author mit der entsprechenden ID und speicher ihn
		Author hlp = getAuthor(id);
		
		//gibt die Überschrift aus
		System.out.println("Bibliography of " + hlp.toString());
		
		//geht die Bücher durch
		for(int i = 0; i < buecher.length; i++) {
			
			//Falls der Author des Buches gleich unsrem gespeicherten Autor ist
			if(buecher[i].getBookAuthor() == hlp) {
				
				//wird der Buchtitel ausgegeben
				System.out.println(buecher[i].getBookTitle());
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		//neue Bücherei mit 2 Autoren und 3 Büchern
		Library sammlung = new Library(2, 3);
		
		//2 Beispielautoren
		Author chr = new Author("Christian", "Wendlinger");
		Author jnf = new Author("Jennifer", "Glock");
		
		//3 Beispielbücher
		Book chr1 = new Book(chr, "Programmieren für Anfänger");
		Book chr2 = new Book(chr, "Schnell Geld verdienen");
		Book jnf1 = new Book(jnf, "Die besten Backrezepte");
		
		
		//Autoren zuweisen
		sammlung.autoren[0] = chr;
		sammlung.autoren[1] = jnf;
		
		//Bücher zuweisen
		sammlung.buecher[0] = chr1;
		sammlung.buecher[1] = chr2;
		sammlung.buecher[2] = jnf1;
		
		
		//neuer Scanner
		Scanner eingabe = new Scanner(System.in);
		
		//endlosschleife, damit das Programm nicht einfach beendet wird
		while(true) {
			//ein Befehl wird eingelesen
			String befehl = eingabe.nextLine();
			
			//Der Befehl wird überprüft
			switch(befehl) {
			
			//Befehl "list"  - geht einmal alle Auoten durch und gibt die ID des Autors + den vollständigen Namen aus
			case("list"): for(int i = 0; i < sammlung.autoren.length; i++) {
								System.out.println(sammlung.autoren[i].getID() + " " + sammlung.autoren[i].toString());
							}
				break;
				
			//Befehl "bib"  -  verlangt nach einer Identifikationsnummer
			case("bib"): System.out.println("Geben sie eine Identifikationsnummer ein: ");
			
							//widerholt sich bis eine gültige ID eingegeben wurde
							while(true) {
								
								//die eingelesene Nummer wird gespeichert
								int nummer = eingabe.nextInt();
								boolean exist = false;
								
								//geht die Autoren durch und gibt dessen Bibliografie aus, falls er existiert
								for(int i = 0; i < sammlung.autoren.length; i++) {
									if(sammlung.autoren[i].getID() == nummer) {
										sammlung.printBibliography(nummer);
										exist = true;
									}
								}
								
								//wenn der Autor nicht exisitiert wird dieser Fehler ausgegeben
								if(!exist) {
									System.out.println("Ungültige ID");
									
								// Falls der Autor aber exisitert, wird die Schleife durchbrochen
								}else {
									break;
								}
							}
						break;
						
			// beendet das Programm und schließt den Scanner
			case("exit"): 	eingabe.close();
							return;
							
			//verhindert, dass einer Fehler ausgegeben wird, falls einfach nur enter gedrückt wird
			case(""): break;
			
			//gibt einen Fehler aus, falls der eingegebene Befehl nicht existiert
			default: System.out.println("Der eingegebene Befehl ist ungültig");
			}
		}
	}
}