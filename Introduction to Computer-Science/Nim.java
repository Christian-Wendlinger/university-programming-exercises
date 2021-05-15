
// Programm Dr.Nim von Melanie und Christian

import java.util.Scanner;
import java.util.Random;

public class Nim {
	public static void main(String[] args) {
		
		Scanner spieler = new Scanner(System.in);
		Random zufall = new Random(System.currentTimeMillis());
		
		// Hilfsvariable, die das Spiel beendet
		boolean aktiv = true;
		
		// Anfangszahl der Streichhoelzer 10 - 25
		int streichhoelzer = zufall.nextInt(16) + 10;
		
		//Hilfsvariable, um zu bestimmen wer an der Reihe ist
		byte counter = 0;
		
		//Hilfsvariable, um die Anzahl der genommenen Streichhoelzer zu speichern
		int tmp = 0;
		
		// Spielschleife
		while(aktiv) {
			if(streichhoelzer > 0) {
				if(streichhoelzer == 1){
					System.out.println("Es befindet sich " + streichhoelzer + " Streichholz auf dem Tisch\n");
				}
				else{
					System.out.println("Es befinden sich " + streichhoelzer + " Streichhoelzer auf dem Tisch\n");
				}
				// sorgt dafür, dass abwechselnd Spieler und Computer an der Reihe ist
				if(counter%2 == 0) {
					System.out.println("Wie viele Streichhoelzer moechtest du nehmen?");
					
					// sorgt dafür, dass der Spieler eine Zahl zwischen 1 und 3 eingibt
					do {
						tmp = spieler.nextInt();
					}
					while(tmp < 1 || tmp > 3);
					
					if(tmp == 1) {
						System.out.println("Du nimmst " + tmp + " Streichholz vom Tisch");
					}
					else {
						System.out.println("Du nimmst " + tmp + " Streichhoelzer vom Tisch");
					}
					streichhoelzer -= tmp;
					counter++;
				}
				// Zug des Computers
				else {
					//lässt den Computer die richtige Anzahl Streichhoelzer nehmen, falls er das Spiel
					//gewinenn kann
					if(streichhoelzer == 3) tmp = 3;	
					else if(streichhoelzer == 2) tmp = 2;
					else if(streichhoelzer == 1) tmp = 1;
					
					// lässt Computer eine Zufällige Zahl Streichhoelzer nehmen
					else tmp = zufall.nextInt(3) + 1;
					
					//Ausgabe wie viele der Computer nimmt
					
					if(tmp == 1) {
						System.out.println("Der Computer nimmt " + tmp + " Streichholz vom Tisch");
					}
					else {
						System.out.println("Der Computer nimmt " + tmp + " Streichhoelzer vom Tisch");
					}
					streichhoelzer -= tmp;
					counter++;
				}
			}
			// beendet das Spiel
			else {
				aktiv = false;
				spieler.close();
			}
		}
		
		// Ausgabe des Ergebnisses
		if(counter%2 == 0) {
			System.out.println("\nLeider hat der Computer gewonnen.");
		}
		else {
			System.out.println("\nHerzlichen Glueckwunsch! Du hast gewonnen.");
		}
	}
}
