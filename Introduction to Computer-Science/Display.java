//Ausgabe einer Zahl durch Displayclicks von Melanie und Christian

public class Display {
	public static void main(String[] args) {
		
		//Display 16 auf 1
		boolean[][] anzeige = new boolean[16][1];
		
		//endlosschleife
		while(true){
			
			//aktualisiert das Display bei jedem durchlauf
			eidi.Display.set(anzeige);
			
			//wartet auf einen Klick im Display
			int[] pos = eidi.Display.getClickPosition();
			
			//invertiert die geklickte Position
			anzeige[pos[0]][pos[1]] = !anzeige[pos[0]][pos[1]];
			
			// berechnet die Dezimalzahl ganz normal nach Umrechnungssystem
			int ergebnis = 0;
			for(int i = 0; i < 16; i++){
				if(anzeige[15-i][0] == true){
					ergebnis += Math.pow(2,i);
				}
			}
			
			//gibt nach jedem Durchlauf(Klick) die aktuelle angezeigte Zahl aus
			System.out.println(ergebnis);
		}
	}
}