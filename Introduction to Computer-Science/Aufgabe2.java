//Aufgabe 2 Tetranacci von Melanie und Christian

import java.util.Scanner;

public class Aufgabe2 {

	public static void main(String[] args) {
		//Zahl einlesen
		Scanner eingabe = new Scanner(System.in);
		int n = eingabe.nextInt();
		eingabe.close();
		
		//Wahl der Iterativen Methode ohne Array, da sie die beste Laufzeit hat
		System.out.println(tetraIt(n));
	}
	
	//rekursive Lösung (A)
	static long tetraRec(int n) {
		
		//Basisfall 1 
		if(n < 3) {
			return 0;
		}
		//Basisfall 2
		else if(n == 3) {
			return 1;
		}
		//rekursiver Aufruf zur Berechnung einer Zahl größer 3
		else {
			return tetraRec(n-1) + tetraRec(n-2) + tetraRec(n-3) + tetraRec(n-4);
		}
	}
	
	
	// rekursive Methode mit Array (B)
	static long tetraRecDP(int n) {
		
		//Array der Länge n+1, da die 0 bei der Folge mitzählt und so das Array sonst um 1 zu kurz wäre
		long[]a = new long[n+1];
		
		//Ergebnis ausrechnen und zurückgeben
		long erg = tetraRecDP(n, a);
		return erg;
	}
	
	
	//Hilfsmethode 1
	static long tetraRecDP(int n, long[]a) {
		
		// Basisfall 1
		if(n < 3) {
			return 0;
		}
		//Basifall 2
		else if(n == 3) {
			return 1;
		}
		
		// füllt das Array bis zu den Basiszahlen auf und erstellt einen Hilfsindex
		else {
			a[0] = 0;
			a[1] = 0;
			a[2] = 0;
			a[3] = 1;
			int i = 4;
			return tetraRecDP(a, i);
		}
	}
	
	//Hilfsmethode 2 - eindeutig, da die Parameter in unterschiedlicher Reihenfolge und unterschiedlicher Art sind
	static long tetraRecDP(long[]a, int index) {
		
		//läuft bis der letze index berechnet wurde
		if(index < a.length) {
			
			//berechnet den Wert des aktuellen index
			a[index] = a[index-1] + a[index-2] + a[index-3] + a[index-4];
			
			//rekursiver Aufruf => aufsteigende Berechnung der indizes 
			return tetraRecDP(a, index+1);
		}
		
		//rückgabe des letzten Wertes aus dem Array (also das Ergebnis)
		else {
			return a[index-1];
		}
	}
	
	//Iterative Lösung (C)
	static long tetraItDP(int n) {
		
		//Array der Länge n+1, da in der Folge die 0 mitgezählt wird
		long[] a = new long[n+1];
		
		//Fall 1 - führt zu Ausgabe 0
		if(n < 3) {
			return 0;
		}
		
		//Fall 2 - führt zu Ausgabe 1
		else if(n == 3) {
			return 1;
		}
		
		//Fall 3
		else if(n > 3) {
			
			//Die Basiswerte von a werden initialisiert
			a[0] = 0;
			a[1] = 0;
			a[2] = 0;
			a[3] = 1;
			
			//Schleife, die die gesuchte Stelle nach der Formel berechnet
			for(int i = 4; i < a.length; i++) {
				a[i] = a[i-1] + a[i-2] + a[i-3] + a[i-4];
			}
			
			//Rückgabe der berechneten Stelle
			
		}
		return a[n];
	}
	
	// Iterative Lösung ohne Array (D)
	static long tetraIt(int n) {
		
		//4 Hilfsvariablen für die Zwischenergebnisse
		long tmp1 = 0;
		long tmp2 = 0;
		long tmp3 = 0;
		long tmp4 = 1;
		
		//Variable für die Lösung
		long erg = 0;
		
		
		//Fall 1 - Ausgabe 0
		if (n < 3) {
			return 0;
		}
		//Fall 2 - Ausgabe 1
		else if(n == 3) {
			return 1;
		}
		
		//Fall 3
		else {
			//Schleife, die bis n läuft
			for(int i = 4; i < n+1; i++) {
				//berechnet für jeden Durchlauf das Ergebnis mit Hilfe der Zwischenergebnisse
				erg = tmp4 + tmp3 + tmp2 + tmp1;
				
				//aktualisiert die Zwischenergebnisse für den nächsten Durchlauf
				tmp1 = tmp2;
				tmp2 = tmp3;
				tmp3 = tmp4;
				tmp4 = erg;
			}
		}
		
		//gibt die gesuchte Stelle zurück
		return erg;
	}
	
	static void test(int n) {
		long a = System.nanoTime();
		//tetraRec(n);
		//tetraRecDP(n);
		//tetraItDP(n);
		//tetraIt(n);
		System.out.println(System.nanoTime()-a);
		
		/*	(E)
		 *	Bei n = 35 braucht die rekursive Methode ohne Array schon ziemlich lange, da der Rechenaufwand 
		 *	extrem ist und viele Zwischenergebnisse mehrfach berechnet werden müssen
		 *
		 *	Diskussion:							Speicher					Laufzeit
		 *	
		 *	Rekursiv					sehr ineffektive Nutzung 		sehr lange Laufzeit, da Berechnungen 
		 *																mehrfach durchgeführt werden müssen
		 *	
		 *	Rekursiv mit Array			bessere Speichernutzung 		deutlich verbessert, da bereits berechnete
		 *																Werte wiederverwertet werden
		 *
		 *	Iterativ mit Array			gleich wie bei Rekursiv			noch bessere Laufzeit, da Methoden 
		 *	 							mit Array						nicht mehrfach aufgerufen werden müssen
		 *								
		 *	Iterativ ohne Array			effektivste Speicherplatz-		Laufzeit in etwa dieselbe wie bei der
		 *								nutzung, nur das wird			Iterativen Methode ohne Array
		 *								verwendet, was im Moment
		 *								auch wirklich zur 
		 *								Brechnung gebraucht wird
		 *
		 *
		 *
		 *	(F)
		 *	n = 71 führt zu dem Ergebnis: -4843349825507395548
		 *	
		 *	Die berechneten Zahlen werden zu groß, also
		 *	kommt es hier zu einem Overflow, da die berechnete Zahl über die größt mögliche Zahl,
		 *	die mit einer Variabel vom Typ long dargestellt werden kann hinausgeht
		 *	Java benutzt das Zweierkomplement, deshalb wird Overflow 
		 *	
		 *	nach der Obergrenze
		 *	(Long.MAX_VALUE 9223372036854775807)
		 *	
		 *	auf die Untergrenze 
		 *	(Long.MIN_VALUE -9223372036854775808)
		 *	
		 *	addiert und führt daher zu einem negativen Ergebnis
		 */
		
	}
}
