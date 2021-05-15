//Aufgaben 1 a und b isMember von Melanie und Christian

import java.util.Scanner;

public class Aufgabe1ab {
	
	public static void main(String[] args) {
		
		//Eingaben einlesen
		Scanner eingabe = new Scanner(System.in);
		
		String zeile = eingabe.nextLine();
		char character = eingabe.nextLine().charAt(0);
		
		eingabe.close();
		
		//Ergebnis ausgeben
		System.out.println(isMember(character, zeile));
		
	}
	

	// A + B
	static boolean isMember(char c, String s) {
		
		//gibt das Ergebnis der Hilsmethode zurück
		boolean member = isMember(c, s, 0);
		return member;
		
	}
	
	//Hilsmethode
	static boolean isMember(char c, String s, int index) {
		
		//führt das Programm rekursiv aus
		if(index < s.length()){
			
			//gibt true zurück, falls das Zeichen vorhanden ist
			if(s.charAt(index) == c) {
				return true;
			}
			//überpüft den nächsten Buchstaben
			else {
				return isMember(c, s, index+1);
			}
		}
		//gibt false zurück, falls der Buchstabe bis zum letzen Index nicht vorkommt
		else {
			return false;
		}
		
	}
	
	//B
	
}
