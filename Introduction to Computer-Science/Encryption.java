//Verschlüsselung bzw Entschlüsselung eines Textes mithilfe eines Schlüssels
//Onur, Christian

import java.util.Scanner;

public class Encryption {
	
	//Alphabet A (65) - _ (95)
	
	
	public static String encrypt(String text, String key) {
		
		
		//verschlüsselter Text
		String erg = "";
		
		
		//den Klartext durchgehen
		for(int i = 0; i < text.length(); i++) {
			
			//Zeichen verschlüsseln
			int tmp = text.charAt(i) + key.charAt(i%key.length())%65;
			
			//wenn das Zeichen über das letze Zeichen hinausgeht, dann neu berechnen
			if(tmp > 95) {
				tmp = 64 + tmp%95; 
			}
			
			//verschlüsseltes Zeichen an den Text hängen
			erg += (char) tmp;
		}
		
		//verschlüsselten Text zurückgeben
		return erg;
	}
	
	
	public static String decrypt(String chiffe, String key) {
		
		//entschlüsselter Text
		String erg = "";
		
		for(int i = 0; i < chiffe.length(); i++) {
			
			//Zeichen entschlüsseln
			int tmp = chiffe.charAt(i) + (31 - key.charAt(i%key.length())%65);
			
			//rest wie bevor
			if(tmp > 95) {
				tmp = 64 + tmp%95;
			}
			
			erg += (char)tmp;
		}
		
		return erg;
	}
	
	
	public static void main(String[] args) {
		
		//neuer Scanner
		Scanner eingabe = new Scanner(System.in);
		
		//erforderlichen Daten einlesen und Scanner schließen
		String text = eingabe.nextLine();
		String key = eingabe.nextLine();
		String befehl = eingabe.nextLine();
		eingabe.close();
		
		
		//Ausgabe
		if(befehl.equals("encrypt")){
			System.out.println(encrypt(text, key));
		}
		
		if(befehl.equals("decrypt")) {
			System.out.println(decrypt(text, key));
		}
	}
}
