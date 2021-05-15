// Aufgabe 1 von Melanie und Christian


public class Methoden {
	public static void main(String[] args) {
		
		//Test Teilaufgabe A
		String test = "Das ist mein eigener String";
		System.out.println(removeVowels(test));
		
		//Test Teilaufgabe B
		int[] a = {4, 3, 5, 7, 9, 11, 11, 13, 15, 17};
		System.out.println(isSorted(a));
		
		//Test Teilaufgabe C
		int[] zahlen = {1, 5, 7, 2, 1, 9};
		int[] zahlen1 = reverse1(zahlen);
		for(int i : zahlen1) {
			System.out.print( i +" ");
		}
		
		//Ausgabe des Originalarrays
		System.out.println();
		for(int i : zahlen) {
			System.out.print( i +" ");
		}
			
		//Test Teilaufgabe D
		reverse2(zahlen);
		System.out.println();
		for(int i : zahlen) {
			System.out.print(i + " ");
			
		}
		
		//Test Teilafugabe E
		String myText = "Wie viele E's wohl in diesem kurzen Text stecken?";
		System.out.println("\n" + countChar('e', myText));
		
		//Test Teilaufgabe F
		String buchstabenAnzahl = "Dieser Text hat nicht so viele Buchstaben";
		printHistogram(buchstabenAnzahl);
	}
	
	//Teilaufgabe A
	static String removeVowels(String text) {
		
		String ergebnis = "";
		
		//Schleife über den kompletten String
		for(int i = 0; i < text.length(); i++) {
			
			//Testet den Buchstaben und falls er kein Vokal ist wird er an den Ergebnisstring übergeben
			switch (text.charAt(i)) {
			case ('A'): break;
			case ('a'): break;
			case ('E'): break;
			case ('e'): break;
			case ('I'): break;
			case ('i'): break;
			case ('O'): break;
			case ('o'): break;
			case ('U'): break;
			case ('u'): break;
			default: ergebnis += ""+text.charAt(i);
			}
		}
		
		//gibt den String ohne Vokale zurück
		return ergebnis;
	}
	
	//Teilaufgabe B
	static boolean isSorted(int[] numbers) {
		int counter = 0;
		
		//Schleife über das Array
		for(int i = 0; i < numbers.length; i++) {
			
			//vergleicht die Zahl mit der davor (i aber größer 0, da die stelle -1 nicht existiert)
			if((i > 0) && (numbers[i-1] > numbers[i])) {
				counter++;
			}
		}
		
		//Rückgabe
		if(counter == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	//Teilaufgabe C
	static int[] reverse1(int[] a) {
		
		//neues Array, da das ursprüngliche nicht verändert werden soll
		int[] reversedA = new int[a.length];
		
		//Setzt die Positionen von a rückwärts in reversedA ein
		for (int i = 0; i < a.length; i++) {
			reversedA[i] = a[(a.length-1)-i];
		}
		
		//Rückgabe
		return reversedA;
	}
	
	//Teilaufgabe D - der Unterschied ist, dass das ursprüngliche Array verändert werden muss, da void kein anderes Array zurückgeben kann
	static void reverse2(int[] a) {
		
		//Hilfsarray
		int[] hlp = new int[a.length];
		
		//Kopie des Arrays erstellen
		for(int j  = 0; j < a.length; j++) {
			hlp[j] = a[j];
		}
		
		//kehrt die Reihenfolge der Zahlen um
		for(int i = 0; i < a.length; i++) {
			a[i] = a[(a.length-1)-i];
		}
	}
	
	//Teilaufgabe E
	static int countChar(char c, String s) {
		int counter = 0;
		
		// char c ein mal in groß und klein um vergleichen zu können
		char hlp = Character.toUpperCase(c);
		char hlp2 = Character.toLowerCase(c);
		
		//Schleife über den String
		for(int i = 0; i < s.length(); i++) {
			
			//erhöht counter, falls der Charakter vorkommt, egal ob groß oder klein
			if(s.charAt(i) == hlp) {
				counter++;
			}
			else if(s.charAt(i) == hlp2) {
				counter++;
			}
		}
		
		//gibt die gezählte Anzahl des Characters zurück
		return counter;
	}
	
	//Teilaufgabe F
	static void printHistogram(String s) {
		int[] klein = new int[26];
		int[] gross = new int[26];
		
		float counter = 0f;
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) > 96 && s.charAt(i) < 123) {
				klein[s.charAt(i)%97]++;
				counter++;
			}
			else if(s.charAt(i) > 64 && s.charAt(i) < 91) {
				gross[s.charAt(i)%65]++;
				counter++;
			}
		}
		
		for(int i = 0; i < 26; i++) {
			System.out.println((char)(i+65) + "  " + (klein[i] + gross[i]) + " mal: Häufigkeit " + (((klein[i] + gross[i])/counter)*100) + "%");
		}
	}
}
