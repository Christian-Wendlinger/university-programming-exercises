//Klasse zum Testen der generischen Consliste - sollte 15 mal true ausgeben
//Onur, Christian

public class ConsListTest {

	public static void main(String[] args) {

		//alle Methoden sollten true liefern

		//size
		if(testS1()) {
			System.out.println("test: size 0 successful");
		}else {
			System.out.println("test: size 0 failed");
		}

		if(testS2()) {
			System.out.println("test: size odd successful");
		}else {
			System.out.println("test: size odd failed");
		}

		if(testS3()) {
			System.out.println("test: size even successful");
		}else {
			System.out.println("test: size even failed");
		}


		//add
		if(testI1()) {
			System.out.println("test: add Index 0 successful");
		}else {
			System.out.println("test: add Index 0 failed");
		}

		if(testI2()) {
			System.out.println("test: add end of list successful");
		}else {
			System.out.println("test: add end of list failed");
		}

		if(testI3()) {
			System.out.println("test: add middle of list (Index 1) successful");
		}else {
			System.out.println("test: add middle of list (Index 1) failed");
		}

		if(testG1()) {
			System.out.println("test: get first element successful");
		}else {
			System.out.println("test: get first element failed");
		}

		if(testG2()) {
			System.out.println("test: get last element successful");
		}else {
			System.out.println("test: get last element failed");
		}

		if(testG3()) {
			System.out.println("test: get in between element successful");
		}else {
			System.out.println("test: get in between element failed");
		}


		//remove
		if(testR1()) {
			System.out.println("test: remove first element successful");
		}else {
			System.out.println("test: remove first element failed");
		}

		if(testR2()) {
			System.out.println("test: remove last element successful");
		}else {
			System.out.println("test: remove last element failed");
		}

		if(testR3()) {
			System.out.println("test: remove in between element successful");
		}else {
			System.out.println("test: remove in between element failed");
		}


		//Fehlerbehaftete Tests
		if(testF1()) {
			System.out.println("test: catch invalid index (add) successful");
		}else {
			System.out.println("test: catch invalid index (add) failed");
		}

		if(testF2()) {
			System.out.println("test: catch invalid index (remove) successful");
		}else {
			System.out.println("test: catch invalid index (remove) failed");
		}

		if(testF3()) {
			System.out.println("test: catch invalid index (get) successful");
		}else {
			System.out.println("test: catch invalid index (get) failed");
		}
	}

	//Testmethode 1 - size bei leerer Liste
	private static boolean testS1() {

		ConsList<Integer> liste = new ConsList<Integer>();

		return liste.size() == 0;
	}

	//Testmethode 2 - size bei ungerader Anzahl von Elementen
	private static boolean testS2() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(1);

		return liste.size() == 1;
	}

	//Testmethode 3 - size bei gerader Anzahl von Elementen
	private static boolean testS3() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(1);
		liste.add(2);

		return liste.size() == 2;
	}

	//Testmethode 4 - einfügen an den Anfang der Liste
	private static boolean testI1() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(0, 1);

		return liste.get(0) == 1;
	}

	//Testmethode 5 - einfügen ans Ende der Liste
	private static boolean testI2() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(3);
		liste.add(1, 5);

		return liste.get(1) == 5;
	}

	//Testmethode 6 - einfügen mitten in die Liste
	private static boolean testI3() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(3);
		liste.add(5);
		liste.add(1, 4);

		return liste.get(1) == 4 && liste.get(2) == 5;
	}

	//Testmethode 7 - Objekt am Anfang der Liste auslesen
	private static boolean testG1() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(3);
		liste.add(5);
		liste.add(1, 4);

		return liste.get(0) == 3;
	}

	//Testmethode 8 - Objekt am Ende der Liste auslesen
	private static boolean testG2() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(3);
		liste.add(5);
		liste.add(1, 4);

		return liste.get(2) == 5;
	}

	//Testmethode 9 - Obbjekt in der Mitte der Liste auslesen
	private static boolean testG3() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(3);
		liste.add(5);
		liste.add(1, 4);

		return liste.get(1) == 4;
	}

	//Testmethode 10 - entfernen erstes Objekt der Liste
	private static boolean testR1() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(3);
		liste.add(5);
		liste.add(1, 4);

		return liste.remove(0) == 3;
	}

	//Testmethode 11 - entfernen letzes Objekt der Liste
	private static boolean testR2() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(3);
		liste.add(5);
		liste.add(1, 4);

		return liste.remove(2) == 5;
	}

	//Testmethode 12 - entfernen mittleres Objekt der Liste
	private static boolean testR3() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(3);
		liste.add(5);
		liste.add(1, 4);

		return liste.remove(1) == 4;
	}

	//Fehlermethode 1 - negativen Index hinzufügen
	private static boolean testF1() {

		ConsList<Integer> liste = new ConsList<Integer>();
		try {
			liste.add(-1, 2);
		}
		catch(IndexOutOfBoundsException e) {
			return true;
		}

		return false;
	}

	//Fehlermethode 2 - nicht vorhandenen Index löschen
	private static boolean testF2() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(1);
		try {
			liste.remove(1);
		}
		catch(IndexOutOfBoundsException e) {
			return true;
		}

		return false;
	}

	//Fehlermethode 3 - nicht vorhandenen Index zurückgeben
	private static boolean testF3() {

		ConsList<Integer> liste = new ConsList<Integer>();
		liste.add(1);
		try {
			liste.get(1);
		}
		catch(IndexOutOfBoundsException e) {
			return true;
		}

		return false;
	}
}
