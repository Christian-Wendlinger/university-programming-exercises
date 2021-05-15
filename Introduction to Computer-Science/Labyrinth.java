// Labyrinthaufgabe von Melanie und Christian

import eidi.Display.*;
import java.util.*;
import java.io.*;

public class Labyrinth {
	
	//Mainmethode
	public static void main(String[] args){
		
		//erstellt das Labyrinth
		Color[][] anzeige = setup("src/Blatt07Eidi/labyrinth2.txt");
		eidi.Display.set(anzeige);
		
		//ermittelt die Startposition
		int[] anfangsKoordinaten = searchStartingPosition(anzeige);
		
		//findet das Ziel
		findPath(anzeige, anfangsKoordinaten[0], anfangsKoordinaten[1]);
	}
	
	//weist den gegebenen Zeichen eine Farbe zu
	static Color char2Color(char c){
		
		switch(c) {
		case '#': return Color.RED;
		case ' ': return Color.WHITE;
		case 'Z': return Color.GREEN;
		case 'S': return Color.BLUE;
		default: return null;
		}
	}
	
	//liest die Textdatei aus und erstellt die Anzeige
	static Color[][] setup(String dateiname){
		
		//Hilfsvariablen
		int[] size = new int[2];
		int index = -1;
		
		//Anzeige, die gleich angepasst wird
		Color[][] feld = new Color[1][1];
		
		//Scanner, der die Datei ausliest, falls sie vorhanden ist
		try(Scanner eingabe = new Scanner(new File(dateiname))) {
			
			//liest die ersten zwei Zeilen aus und erstellt die Größe des Feldes
			size[0] = eingabe.nextInt();
			size[1] = eingabe.nextInt();
			
			//Hilfsmethode
			feld = setup(size);
			
			//füllt die Anzeige entsprechend der Textdatei
			while(eingabe.hasNextLine()) {
				String line = eingabe.nextLine();
				
				//weitere Hilfsmethode
				setup(feld, index, line);
				index++;
			}
		}
		//Falls die Datei nicht gefunden werden konnte
		catch(FileNotFoundException e) {
			System.out.println("Die Datei " + dateiname + " konnte nicht gefunden werden!");
		}
		// gibt das Gesamtergebnis zurück
		return feld;
	}
	
	//Hilfsmethode, welche die Größe des Labyrinths anpasst
	static Color[][] setup(int[]a){
		Color[][] b = new Color[a[0]][a[1]];
		return b;
	}
	
	// Hilfsmethode, welche das Labyrinth Zeichenweise mit der entsprechenden Farbe füllt
	
	static void setup(Color[][] a, int index, String line) {
		
		//geht die Zeichen einzeln durch
		for(int i = 0; i < line.length(); i++) {
			
			//methode aus teilaufgabe a)
			a[i][index] = char2Color(line.charAt(i)); // Index als Hilfsvariable, um in der Zeile weiter nach unten gehen zu können
		}
	}
	
	//ermittelt die Anfangsposition
	static int[] searchStartingPosition(Color[][] a) {
		int[] koordinaten = new int[2];
		
		//geht einmal das gesamte Array durch
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				
				//falls das Feld blau ist (Anfangsfeld)
				if(a[i][j] == Color.BLUE) {
					
					//werden die Koordinaten in einem Array gespeichert
					koordinaten[0] = i;
					koordinaten[1] = j;
				}
			}
		}
		
		//gibt die Anfangskoordinaten zurück
		return koordinaten;
	}
	
	//kopiert das Labyrinth
	static Color[][] copyDisplay(Color[][] a){
		
		//neues Array mit den gleichen Maßen
		Color[][] b = new Color[a.length][a[0].length];
		
		//doppelte Schleife über das gesamte Array
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				//füllt b mit den entsprechenden Farben von dem Ausgangsarray
					b[i][j] = a[i][j];
			}
		}
		//gibt die Kopie zurück
		return b;
	}
	
	//sucht einen Weg zum Ziel des Labyrinths
	static Color[][] findPath(Color[][] a, int x, int y){
		
		//kopiert die aktuelle Situation
		Color[][] b = copyDisplay(a);
		
		//setzt das aktuelle Feld auf blau
		b[x][y] = Color.BLUE;
		
		
		//Schleife über 9 Felder mit dem aktellen im Zentrum
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
			
				//Hilfsvariablen um die relative Position zu erfassen
				int posx = x+i;
				int posy = y+j;
					
				//Bedingungen: die nachbarn müssen innerhalb des Arrays liegen, dürfen keine Eckfelder sein oder das Feld selbst und müssen Weiß oder Grün sein 
				if(posx > -1 && posx < b.length && posy > -1 && posy < b[0].length && (posx == x || posy == y) && (b[posx][posy] == Color.WHITE || b[posx][posy] == Color.GREEN) && !(posx == x && posy == y)){
						
					//wenn der Nachbar grün ist der Algorithmus beendet und das Display kann aktualisiert werden
						if(b[posx][posy] == Color.GREEN) {
							eidi.Display.set(b);
							return b;
					//Rekursion bis das grüne Feld gefunden wurde oder alle Felder blau sind, es also keinen Weg gibt 
						}else{
							findPath(b, posx, posy);
						}
				}
			}
		}
		
	//gibt null zurück, falls kein Weg gefunden wurde
	return null;
	}
}


