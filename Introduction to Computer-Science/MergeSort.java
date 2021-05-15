//MergeSort mit FileTapes - sehr seltsam
//Onur, Christian

public class MergeSort {

	static int size = 0;

	public static void main(String[] args) {

		//4 Tapes
		FileTape a1 = setup("a1", 0);
		FileTape a2 = setup("a2", 1);
		FileTape b1 = setup("b1", 0);
		FileTape b2 = setup("b2", 0);


		//Beispielfolge
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("32");
		a1.write("24");
		a1.write("4");
		a1.write("18");
		a1.write("8");
		a1.write("2");
		a1.write("5");
		a1.write("3");
		a1.write("7");
		a1.write("9");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.write("1");
		a1.write("6");
		a1.write("15");
		a1.write("11");
		a1.write("29");
		a1.write("17");
		a1.rewindAndRead();


		//Anzahl der Elemente zählen
		while(a1.hasNext()) {
			size++;
			a1.next();
		}
		a1.rewindAndRead();



		//Sortierte Folge
		FileTape erg = mergeAll(a1, a2, b1, b2);

		//Ausgabe der sortierten folge
		while(erg.hasNext()) {
			System.out.print(erg.next() + " ");
		}
	}


	//Anfangstapes erstellen
	private static FileTape setup(String name, int a) {

		//Unterscheidung zwischen lesen und schreiben
		if(a == 0) {
			FileTape tmp = new FileTape(name);
			tmp.rewindAndWrite();
			return tmp;
		}else {
			FileTape tmp = new FileTape(name);
			tmp.rewindAndRead();
			return tmp;
		}
	}


	//Split methode
	private static void split(FileTape a1, FileTape b1, FileTape b2, int width) {

		//Hilfsvariablen
		int c1 = 0;
		int c2 = 0;


		//Gleichmäßig aufteilen
		while(a1.hasNext()) {

			//Gleichmäßig auf die Tapes verteilen
			if(c2%2 == 0) {

				//schreiben
				b1.write(a1.next());
				c1++;

				//maximale Anzahl erreicht
				if(c1%width == 0) {
					c2++;
				}
			}

			//Analog
			else {
				b2.write(a1.next());
				c1++;

				if(c1%width == 0) {
					c2++;
				}
			}
		}
	}



	//Zusammenfügen
	private static void merge(FileTape a1, FileTape a2, FileTape b, int width) {

		//Zählvariablen
		int c1 = 0;
		int c2 = 0;

		//Hilfsvariablen für das Ergebnis erstellen
		FileTape c = setup("c", 0);
		Cons2List liste = new Cons2List();


		while(true) {

			//Hilfsstrings
			String s1 = "";
			String s2 = "";


			//Strings auslesen wenn möglich
			if(c1 < width) {
				if(a1.hasNext()) {
					s1 = a1.next();
					c1++;
				}
			}

			if(c2 < width) {
				if(a2.hasNext()) {
					s2 = a2.next();
					c2++;
				}
			}


			//Zwischenspeicher beschreiben
			if(s1 != "") {
				c.write(s1);
			}
			if(s2 != "") {
				c.write(s2);
			}


			//Am Ende angekommen
			if(s1 == "" && s2 == "") {
				c.rewindAndRead();


				//Zahlen in Liste Zwischenspeichern
				while(c.hasNext()) {
					liste.append(Integer.parseInt(c.next()));
				}


				//Liste sortieren und das Ergebnis in die Datei schreiben
				c.rewindAndWrite();
				Cons2List sort = QuickSort.sort(liste);
				merge(c, sort.head);


				//sortierte Reihenfolge übernehmen
				c.rewindAndRead();
				while(c.hasNext()) {
					b.write(c.next());
				}

				//Durchlauf beendet
				return;
			}

			/*
			//Falls es nicht in beiden Dateien noch Elemente gibt
			if(s1 == "" && s2 != "") {
				b.write(s2);
				continue;
			}else if(s1 != "" && s2 == "") {
				b.write(s1);
				continue;
			}else if (s1 == "" && s2 == "") {
				return;
			}


			//in der entsprechenden Reihenfolge schreiben
			if(s1.compareTo(s2) > 0) {
				b.write(s2);
				b.write(s1);
			}else {
				b.write(s1);
				b.write(s2);
			}
			*/
		}
	}

	//Hilfsmethode zum sortieren der aktuellen Teilfolge
	private static void merge(FileTape c, Cons2 cons) {

		//Liste durchlaufen
		if(cons == null) {
			return;
		}


		//Abschreiben
		c.write(Integer.toString((int) cons.obj));
		merge(c, cons.next);
	}


	//MergeSort Algorithmus Bottom Up
	private static FileTape mergeAll(FileTape a1, FileTape a2, FileTape b1, FileTape b2) {

		//split - merge wechsel
		int counter = 0;

		//Hilfsvariable für die Länge der Sublisten
		int tmp = 0;

		while(counter <= size) {

			int width = (int) Math.pow(2, tmp);

			//split-Phase
			if(counter%2 == 0) {

				//Tapes in den richtigen Modus setzen
				a1.rewindAndRead();
				a2.rewindAndRead();
				b1.rewindAndWrite();
				b2.rewindAndWrite();


				//Alle Elemente in Reihenfolge spliten
				split(a1, b1, b2, width);
				split(a2, b1, b2, width);

				//Schritt beendet
				counter++;
			}
			//Merge-Phase
			else {

				//Tapes vorbereiten
				a1.rewindAndWrite();
				a2.rewindAndWrite();
				b1.rewindAndRead();
				b2.rewindAndRead();

				//wechsel zwischen a1 und a2
				int c = 0;

				//Alle Elemente mergen
				while(b1.hasNext() || b2.hasNext()) {

					//auf a1 mergen
					if(c%2 == 0) {
						merge(b1, b2, a1, width);
						c++;
					}
					//auf a2 mergen
					else {
						merge(b1, b2, a2, width);
						c++;
					}
				}

				//Schritt beendet
				counter++;
				tmp++;
			}
		}


		//Ergebnis schreiben und zurückgeben
		FileTape erg = new FileTape("erg");

		a1.rewindAndRead();
		erg.rewindAndWrite();

		while(a1.hasNext()) {
			erg.write(a1.next());
		}

		erg.rewindAndRead();
		return erg;
	}
}
