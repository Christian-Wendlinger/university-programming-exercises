//Klasse, die einen Baum mit allen möglichen aufbaut, diese bewertet und optimale Züge findet
//Onur, Christian

import java.util.Random;

public class GameTree {

	//Attribute
	public Board zustand;
	public GameTree[] folge;
	public int evaluation;

	//Konstruktor
	public GameTree() {
		zustand = new Board();
	}


	//GameTree mit allen Möglichen Zügen aufbauen
	public void buildGameTree(Board board, int counter) {

		//Abbruchbedingung - Kein Zug mehr möglich oder es steht ein Gewinner fest

		//Unentschieden
		if(board.check() == 0) {
			evaluation = 0;
			return;
		}

		//Unentschieden
		if(board.check() == 1) {
			evaluation = 1;
			return;
		}

		//Unentschieden
		if(board.check() == -1) {
			evaluation = -1;
			return;
		}

		//aktuellen Zustand initialisieren
		zustand = board;

		//Folgezustände ermitteln
		Board[] tmp = zustand.possibleMoves(counter%2);
		folge = new GameTree[tmp.length];


		//KindKnoten mit Folgezuständen initialisieren
		for(int i = 0; i < tmp.length; i++) {
			folge[i] = new GameTree();
			folge[i].zustand = tmp[i];
			folge[i].buildGameTree(folge[i].zustand, counter+1);
		}

		//evaluieren aus Sicht der K.I.
		for(int i = 0; i < folge.length; i++) {

			if(i+1 < folge.length) {

				//Spieler am Zug = schlechtester Zug -> höchster Evaluationswert
				if(counter%2 == 0) {
					evaluation = (int) Math.max(folge[i].evaluation, folge[i+1].evaluation);
				}
				//K.I an der Reihe = bester Zug -> niedrigster Evaluationswert
				else {
					evaluation = (int) Math.min(folge[i].evaluation, folge[i+1].evaluation);
				}

			}
		}
	}


	//besten zug auswählen
	public Board bestMove(Board board, int x) {

		GameTree tmp = new GameTree();
		Random random = new Random(System.currentTimeMillis());

		//Zug von Weiß gefunden
		for(int i = 0; i < folge.length; i++) {
			if(folge[i].zustand.equals(board)) {
				tmp = folge[i];
			}
		}


		//Zähvariablen für versch. Optionen
		int posWin = 0;
		int posRem = 0;
		int posLos = 0;


		//mögliche Züge zählen
		for(int i = 0; i < tmp.folge.length; i++) {

			//Gewinnende Züge
			if(tmp.folge[i].evaluation == -1*x) {
				posWin++;
			}

			//Unentschieden
			if(tmp.folge[i].evaluation == 0) {
				posRem++;
			}

			//Verlierende Züge
			if(tmp.folge[i].evaluation == 1*x) {
				posLos++;
			}

		}


		//falls ein gewinnender Zug existiert
		if(posWin != 0) {
			GameTree[] moves = new GameTree[posWin];
			int n = 0;

			//mögliche zustände speichern
			for(int i = 0; i < tmp.folge.length; i++) {
				if(tmp.folge[i].evaluation == -1*x) {
					moves[n] = tmp.folge[i];
					n++;
				}
			}

			//zufälligen Zug aus den gewinnenden Zügen auswählen
			tmp = moves[random.nextInt(posWin)];
		}


		//falls kein gewinnender Zug existiert, aber ein Unentschieden
		else if(posRem != 0) {
			GameTree[] moves = new GameTree[posRem];
			int n = 0;

			//mögliche zustände speichern
			for(int i = 0; i < tmp.folge.length; i++) {
				if(tmp.folge[i].evaluation == 0) {
					moves[n] = tmp.folge[i];
					n++;
				}
			}

			tmp = moves[random.nextInt(posRem)];
		}


		//falls nur verlierende Züge existieren
		else {
			GameTree[] moves = new GameTree[posLos];
			int n = 0;

			//mögliche zustände speichern
			for(int i = 0; i < tmp.folge.length; i++) {
				if(tmp.folge[i].evaluation == 1*x) {
					moves[n] = tmp.folge[i];
					n++;
				}
			}

			tmp = moves[random.nextInt(posLos)];
		}


		//Tree und Board anpassen
		this.zustand = tmp.zustand;
		this.folge = tmp.folge;
		return zustand;
	}


	//GameTree kopieren
	public void copy(GameTree t) {

		//kein gültiger Zustand
		if(t.zustand == null) {
			return;
		}

		//Attribute übernehmen
		this.zustand = t.zustand;
		this.folge = t.folge;


		//Keine weiteren Züge möglich => Spiel vorbei
		if(t.folge == null) {
			return;
		}

		//rekursiver Aufruf
		for(int i = 0; i < folge.length; i++) {
			folge[i].copy(t.folge[i]);
		}
	}
}
