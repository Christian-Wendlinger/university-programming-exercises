
// Programm Caeser von Melanie und Christian

public class CodeV2 {

	public static void main(String[] args) {
		String code = "Y[KYYOMQKOZKTROYZK\n\nFSGXY\nFJ[VRU\nFYTOIQKXY\nFNGT[ZG\nFROUT\nFQKOTFHU[TZ_FTOKSGTJFSGMFHU[TZ_\nFQOZQGZ\nFYSGXZOKY";
		
		//Schleife, die die alle möglichen Verschiebungen durchgeht
		for(int i = 0; i < 31; i++) {
			
			//Schleife die alle Buchstaben einzeln durchgeht
			for(int j = 0; j < code.length(); j++){
				
				// Buchstaben ermitteln
				char tmp = code.charAt(j);
				
				// unterscheidet zwischen Buchstabe und neuer Zeile
				if(tmp == '\n') {
					System.out.print(tmp);
				}
				
				//verschiebt die Buchstaben und beginnt wieder bei A, falls das Ende erreicht wurde
				else {
					int hel = tmp+(i+1);
				
					if(hel > 95) {
						hel = 64 + hel%95;
					}
					System.out.print((char)hel);
				}
			}
			// schafft einfach nur Platz zwischen den Lösungen
			System.out.println("\n\n\n");
		}
	}

}
