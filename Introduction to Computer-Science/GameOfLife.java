//the game of life von Melanie und Christian


import java.util.Random;
import java.util.Scanner;

public class GameOfLife {
	public static void main(String[] args) throws InterruptedException {
		
		//Spielfeldgröße einlesen
		Scanner eingabe = new Scanner(System.in);
		System.out.println("Geben sie die Spielfeldgroesse ein (Minimum 8): ");
		int x = 0;
		do{
			x = eingabe.nextInt();
		}while (x < 8);
		eingabe.close();
		
		//Spielfeld erstellen und alle Zellen auf schwarz setzen
		boolean[][] spielfeld = new boolean[x][x];
		eidi.Display.set(spielfeld);
		
		//erstellt die Ausgangssituation mit 50% lebendigen Zellen und visualierst die Situation
		generateCells(spielfeld, x);
		eidi.Display.set(spielfeld);
		
		
		// Jetzt beginnt das eigentliche Spiel
		int counter = 0;
		// Wiederholt die Spielschleife solange bis alle Zellen schwarz sind
		do {
			counter = 0;
			
			//Methode zum überprüfen der Nachbarn weiter unten
			checkNeighbours(spielfeld, x);
			
			
			// Ausgabe nach jedem Durchlauf:
			
			// Zählt die Anzahl der lebenden Zellen nach jedem Durchlauf
			for(int i = 0; i < x; i++) {
				for(int j = 0; j < x; j++) {
					if(spielfeld[j][i] == true) {
						counter++;
					}
				}
			}
			
			//updatet den Status nach jedem Durchlauf, nach einer kurzen Pause
			Thread.sleep(500);
			eidi.Display.set(spielfeld);
			
		}while(counter != 0);
		
	}
	
	
	
	
	// Diese Methode generiert das Spielfeld
	static void generateCells(boolean[][] cells, int x) {
		
		//Counter für die Anzahl der lebenden Zellen
		int counter = 0;
		
		// Kopie des originalen Arrays, alle Zellen werden schwarz initialisiert
		boolean[][] cellsCopy = new boolean[x][x];
		
		
		//Zufallsgenerator
		Random zufallsGenerator = new Random(System.currentTimeMillis());
		
		
		// geht die Zellen einzeln durch
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				
				//temporäre Speichervariable - liefert den Wert 0 oder 1
				int tmp = zufallsGenerator.nextInt(2);
				
				//initialisiert lebendige Zellen weiß und erhöht den counter
				if(tmp == 1) {
					cellsCopy[j][i] = true;
					counter++;
				}	
				// sobald 50% der Zellen weiß sind, wird das ursprüngliche Array überschrieben und die Methode verlassen
				if(counter == (x*x)/2) {
					for(int k = 0; k < x; k++) {
						for(int l = 0; l < x; l++) {
							cells[l][k] = cellsCopy[l][k];
						}
					}
					return;
				}	
			}
		}	
		// falls die Methode einmal durchgelaufen ist und am Ende weniger als 50% der Zellen weiß sind wird die Methode wiederholt
		generateCells(cells, x);
		
	}
	
	
	// Diese Methode überprüft die Nachbarn
	
	static void checkNeighbours(boolean[][] tafel, int x) {
		//Hilfsarray, um nicht das eigentliche Spielfeld zu überschreiben
		boolean[][] spielfeldUpdate = new boolean[x][x];
		
		//überprüft den Status jeder einzelnen Zelle
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				int nachbarn = 0;
				
				//Schleife über die die umliegenden Zellen
				for(int k = -1; k < 2; k++){
					for(int l = -1; l < 2; l++){
						
						// Hilfsvariablen
						int zeile = ((j+l)+x)%x;
						int spalte = ((i+k)+x)%x;
						
						//erhöht den Wert der Nachbarn, wenn der Nachbar lebendig ist, überspringt aber die Zelle selbst in der Schleife
						if(tafel[zeile][spalte] == true && (!(zeile == j && spalte == i))){
							nachbarn++;
						}
						
					}
				}
				
				//updatet den Status der Zelle nach den Regeln
				if(tafel[j][i] == false && nachbarn == 3){
					spielfeldUpdate[j][i] = true;
				}
				else if(tafel[j][i] == true && (nachbarn < 2 || nachbarn > 3)){
					spielfeldUpdate[j][i] = false;
				}
				else{
					spielfeldUpdate[j][i] = tafel[j][i];
				}
			}
		}
		
		//überträgt die geupdateten Werte auf das Spielfeld
		for(int m = 0; m < x; m++){
			for(int n = 0; n < x; n++){
				tafel[n][m] = spielfeldUpdate[n][m];
			}
		}
	}
}


