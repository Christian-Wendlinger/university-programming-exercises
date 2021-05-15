//Das LightsOut Spiel von Melanie und Christian

import java.util.Random;

public class LightsOut {
	public static void main(String[] args) {
		
		//Anzeige erstellen
		boolean[][] spielfeld = new boolean[5][5];
		
		//Initialisiert das Spielfeld am Anfang/ Methode weiter unten
		setup(spielfeld);
		eidi.Display.set(spielfeld);
		
		//Spielschleife, bis alle Lichter aus sind
		int counter = 0;
		do {
			counter = 0;
			
			//erfasst das geclickte Feld
			int[] click = eidi.Display.getClickPosition();
			
			//schaltet das Feld und die Nachbarn um, aktualisiert das Spielfeld
			switchLight(spielfeld, click[0], click[1]);
			eidi.Display.set(spielfeld);
			
			//zählt die weißen Felder
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					if(spielfeld[i][j]) {
						counter++;
					}
				}
			}
		}while(counter > 0);
		
		//eine kleine Nachricht für den Gewinner
		System.out.println("Gute Arbeit! :)");
	}
	
	//Methode zum umschalten der Felder
	static void switchLight(boolean[][] pixels, int x, int y) {
		
		//invertiert das geklickte Feld
		if(pixels[x][y]) {
			pixels[x][y] = false;
		}else {
			pixels[x][y] = true;
		}
		
		//läuft relativ zu x und y 3x3 Felder durch
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				
				//Hilfsvariablen um relative Positionen zu ermitteln
				int posx = x+i;
				int posy = y+j;
				
				//Nur Felder, die innerhalb des Spielfelds liegen werden überhaupt beeinflusst
				if(posx > -1 && posx < 5 && posy > -1 && posy < 5) {
					
					//Schaltet ein weißes Feld schwarz, wenn das Feld nicht das geklickte Feld ist
					//und kein Feld in der Ecke ist
					if(pixels[posx][posy] && (posx == x || posy == y) && !(posx == x && posy == y)) {
						pixels[posx][posy] = false;
					}
					//Schaltet ein schwarzes Feld weiß, wenn das Feld nicht das geklickte Feld ist
					//und kein Feld in der Ecke ist
					else if(!pixels[posx][posy] && (posx == x || posy == y) && !(posx == x && posy == y)){
						pixels[posx][posy] = true;
					}
				}
			}
		}
	}
	
	//führt die switchLight Methode 10 mal mit zufälligen Koordinaten durch, um das Spielfeld zu initialisieren
	static void setup(boolean[][] display) {
		Random generate = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < 10; i++) {
			int tmpx = generate.nextInt(5);
			int tmpy = generate.nextInt(5);
			
			switchLight(display, tmpx, tmpy);
		}
	}
	
}
