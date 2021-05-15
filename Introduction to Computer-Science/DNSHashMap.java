//Hashing mit Chaining als Kollisionsbehandlung - zum speichern von Hosts und IP-Adresse
//Onur, Christian

import java.util.Scanner;

public class DNSHashMap {

	//Größe und Array zum speichern der Adressen
	private int size;
	private Cons2List[] values;

	//Konstruktor
	public DNSHashMap(int size) {
		this.size = size;
		values = new Cons2List[this.size];
	}


	//put Methodee
	public boolean put(String hostName, String ip) {

		//Hashfunktion
		int index = hostName.hashCode()%size;
		if(index < 0) {
			index *= -1;
		}

		//neue Liste, falls es der erste Eintrag an dieser Position ist
		String[] tmp = {hostName, ip};
		if(values[index] == null) {
			values[index] = new Cons2List();
		}

		//host bereits vorhanden
		if(values[index].containsHost(hostName)) {
			System.out.println(hostName + " already exists");
			return false;
		}

		//chaining, speichert hostname + entsprechende ip
		values[index].append(tmp);
		return true;
	}


	//ip zurückgeben
	public String get(String hostName) {

		//Hashfunktion
		int index = hostName.hashCode()%size;
		if(index < 0) {
			index *= -1;
		}

		//garkein Eintrag an dieser Stelle
		if(values[index] == null) {
			return hostName + " not found";
		}

		//Hilfsmethode
		return get(hostName, values[index].head);
	}

	private String get(String h, Cons2 cons) {

		//Host existiert nicht
		if(cons == null) {
			return h + " not found";
		}

		//host gefunden
		String hlp[] = (String[]) cons.obj;
		if(hlp[0].equals(h)) {
			return hlp[1];
		}

		//rekursiver Aufruf
		return get(h, cons.next);
	}






	//alles ab hier gehört nicht zur Aufgabe

	//eingabe parsen
	private String[] parse(String s) {
		s += " ";

		String[] tmp = {"",""};
		int n = 0;

		for(int i = 0; i < s.length(); i++) {

			if(n > 1) {
				return null;
			}

			if(s.charAt(i) != ' ') {
				tmp[n] += s.charAt(i);
			}else {
				n++;
			}
		}

		if(tmp[0].equals("") || tmp[1].equals("")) {
			return null;
		}

		return tmp;
	}

	//kleine main, um eine simple Datenbank zu erstellen
	public static void main(String[] args) {

		//Größe einlesen
		Scanner s = new Scanner(System.in);
		System.out.println("enter size of DNSHashMap > 0");

		int size = 0;

		//Integer größer 0 erzwingen
		do {
			try {
				size = Integer.parseInt(s.nextLine());

				if(size < 1){
						System.out.println("number > 0 required");
				}

			}catch(Exception e) {
				System.out.println("integer required");
			}
		}while(size < 1);


		DNSHashMap h = new DNSHashMap(size);

		//Adressen einlesen
		System.out.println("now enter " + size + " hostnames and ip adresses seperated by single spacebar");

		for(int i = 0; i < size; i++) {
			String[] tmp = h.parse(s.nextLine());

			//ungültige eingabe
			if(tmp == null) {
				System.out.println("invalid input");
				i--;
				continue;
			}

			//konnte nicht eingefügt werden
			if(!h.put(tmp[0], tmp[1])) {
				i--;
			}

		}


		//IP Adressen aus der HashMap abfragen
		System.out.println("now you can get ip adresses with entering a hostname or close by entering exit");
		while(true) {

			String befehl = s.nextLine();

			switch(befehl) {
			case(""): break;
			case("exit"): s.close();
						  return;
			default: System.out.println(h.get(befehl));
			}
		}
	}
}
