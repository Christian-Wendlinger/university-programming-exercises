
// Programm Hamming von Melanie und Christian

import java.util.Scanner;

public class Hamming {
	public static void main(String[] args) {
		Scanner eingabe = new Scanner(System.in);
		
		// Wörtereingabe
		String wort1 = eingabe.nextLine();
		String wort2 = eingabe.nextLine();
		eingabe.close();
		
		// Alle Buchstaben groß, um vergleichen zu können
		wort1 = wort1.toUpperCase();
		wort2 = wort2.toUpperCase();
		
		// Zählervariable
		byte counter = 0;
		
		//Wörterlänge überprüfen
		if(wort1.length() == wort2.length()) {
			
			// Buchstaben ermitteln und vergleichen
			for(int i = 0; i < wort1.length(); i++) {
				
				char w1 = wort1.charAt(i);
				char w2 = wort2.charAt(i);
				
				if(w1 != w2) {
					counter++;
				}
			}
		// Anzahl der unterschiedlichen Buchstaben ausgeben
		System.out.println(counter);
		}
		else {
			System.out.println("Fehler");
		}
	}
}
