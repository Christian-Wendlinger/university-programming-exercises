//Aufgabe 1 c und d reversed von Melanie und Christian

import java.util.Scanner;

public class Aufgabe1cd {
	public static void main(String[] args) {
	
		//Eingaben einlesen und Werte in einem Array speichern
		Scanner eingabe = new Scanner(System.in);
		int anzahl = eingabe.nextInt();
		int[] zahlen = new int[anzahl];
		
		for(int i = 0; i < anzahl; i++) {
			zahlen[i] = eingabe.nextInt();
		}
		eingabe.close();
		
		//neues Array, das die umgedrehten Zahlen aus der Methode zugewiesen bekommt
		int[] umgedreht = reversed(zahlen);
		
		//Zeilenweise Ausgabe des umgedrehten Arrays
		for(int i = 0; i < umgedreht.length; i++) {
			System.out.println(umgedreht[i]);
		}
		
	}
	//reverse Methode
	static int[] reversed(int[] a) {
		
		//Hilfsarray
		int[] b = new int[a.length];
		
		//das Array bekommt das Ergebnis der Hilfsmethode
		int[] reverse = reversed(a, b, 0);
		
		//Rückgabe des Ergebnisses
		return reverse;
	}
	
	//Hilfsmethode  - füllt b rekursiv, rückwärts mit den Werten von a auf und gibt b zurück
	static int[] reversed(int[] a, int[]b, int index) {
		
		//läuft solange, bis der letze index erreicht wurde
		if(index < a.length) {
			
			//setzt die Werte von Hinten in b ein, erhöht den index durch rekursiven Aufruf
			b[index] = a[(a.length-1)-index];
			return reversed(a, b, index+1);
		}
		
		//gibt das umgedrehte Array zurück
		else {
			return b;
		}
	}
	
}
