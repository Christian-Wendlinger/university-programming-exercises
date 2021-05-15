//Bitshifting von Melanie und Christian

public class Aufgabe5 {
	public static void main(String[] args) {
		
		//Teilaufgabe a
		int i = 0xDA9FA7B1;
		
		/*shiftet die Zahl 16 bit nach links und dann wieder 16 bit nach rechts => die ersten 16 bit
		 *sind nun 1en und der Rest der Zahl unverändert
		 */
		
		 i = i << 16;
		 i = i >> 16;
		
		 // Maske mit 16Nullen und 16 1en
		 int maske = 0x0000FFFF;
		 
		 /*bitweises & => die ersten 16 bit werden auf 0 gesetzt, da 0 && etwas ist immer 0
		  * die letzen 16 bits bleiben unberührt, da 1 & 1 = 1 bzw. 1 & = 0 
		  * also keine Veränderung, wenn man die rechte Seite als ursprüngliche Bits annimt
		  */
		 int ergebnis = i & maske;
		 
		 System.out.println(ergebnis);
		 
		 
		 
		 //Teilaufgabe b
		 
		 i = 0xAC91B0;
		 
		 /* shiftet die Zahl um 16 nach rechts und dann wieder nach links
		  * die ersten 16 bit sind also unverändert und die letzen 16 bits
		  * werden mit 0en aufgefüllt
		  */
		 i = i >> 16;
		 i = i << 16;
		 
		 /*Die Maske bleibt die selber, nur der Operator ändert sich
		 *Die ersten 16 bits sind 0, dh der Wert änder sich durch den Operator nicht
		 *die letzten 16 bits find durch die maske hinterher 1, bei oder immer 1 rauskommt,
		 *wenn eine 1 dabei ist
		 */
		 ergebnis = i | maske;
				 
		 System.out.println(ergebnis);
	}
}
