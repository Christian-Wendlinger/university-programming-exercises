//Hammingfolge ohne aufwendige Berechnungen
//Onur, Christian

public class Hamming {

	public static void main(String[] args) {
		hamming(100000000);
	}


	//Ausgabe der Hammingfolge bis n
	public static void hamming(int n) {

		//Liste zum speichern der Folge erstellen
		Cons2List liste = new Cons2List();


		//1 in die Liste einfügen oder eine leere Liste ausgeben
		if(n > 0) {
			liste.append(1);
		}else {
			System.out.println(liste);
			return;
		}

		//Folge bis n duplikatsfrei erstellen
		hamming((int) liste.head.obj, n, liste);

		//Folge sortieren
		Cons2List erg = QuickSort.sort(liste);

		//Folge ausgeben
		System.out.println(erg);
	}

	//erstellt die Duplikatsfreie folge
	private static void hamming(int glied, int n, Cons2List liste) {

		//die 3 möglichen Folgeglieder
		int tmp1 = glied*2;
		int tmp2 = glied*3;
		int tmp3 = glied*5;


		//Falls die Zahlen kleiner sind als die Grenze und noch nicht enthalten sind werden sie eingefügt und danach wird rekursiv mit diesen weitergerechnet
		if(tmp1 <= n && !liste.contains(tmp1)) {
			liste.append(tmp1);
			hamming(tmp1, n, liste);
		}

		if(tmp2 <= n && !liste.contains(tmp2)) {
			liste.append(tmp2);
			hamming(tmp2, n, liste);
		}

		if(tmp3 <= n && !liste.contains(tmp3)) {
			liste.append(tmp3);
			hamming(tmp3, n, liste);
		}
	}
}
