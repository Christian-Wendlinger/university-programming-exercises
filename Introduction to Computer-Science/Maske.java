//Verändern bestimmter Bits einer Zahl von Melanie und Christian

import java.util.Scanner;

public class Maske {
	public static void main(String[] args) {
		
		//einlesen aller erforderlichen Zahlen
		Scanner eingabe = new Scanner(System.in);
		int x = eingabe.nextInt();
		int n = eingabe.nextInt();
		
		
		/*erstelle eine Maske, die nur aus 0en besteht, außer an den Stellen
		 * an denen eine Eingabe gemacht wurde, dort hat die Maske den Wert 1
		 */
		int maske = 0;
		
		for(int i = 0; i < n; i++) {
			int tmp = eingabe.nextInt();
			if(tmp == 31) {
				maske += 0x80000000;
			}else {
				maske += (int)Math.pow(2, tmp);
			}
		}
			
		eingabe.close();
		
		/*Falls die Zahl an der entsprechenden Stelle 0 ist, wird sie zu 1, da 0 XOR 1 = 1
		 * falls sie 1 ist wird sie zu 0, da 1 XOR 1 = 0
		 * die Stellen werden also invertiert
		 * die restlichen Stellen der Maske sind 0
		 * => 0 XOR 0 = 0
		 * =  0 XOR 1 = 1
		 * die restlichen Stellen bleiben also unverändert
		 */		
		System.out.println(x ^ maske);
	}
}