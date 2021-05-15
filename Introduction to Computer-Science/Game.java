//Schach ohne Würfel
//Onur, Christian

import java.util.Random;
import java.util.Scanner;

public class Game {

	private static int counter = 0;

	public static void main(String[] args) throws InterruptedException{

		//Setup
		Board spiel = new Board();
		GameTree t = new GameTree();
		spiel.setup();
		t.buildGameTree(spiel, 0);

		//Start des Programms
		Scanner s = new Scanner(System.in);
		System.out.println("Willkommen zu Schach ohne Würfel!\nBewege deine Bauern Schritt für Schritt vorwärts oder schlage diagonal deine Gegner vom Feld\nFalls du nicht selber spielen möchtest, lasse einfach die K.I. gegen sich selbst spielen");
		System.out.println("\nFolgende Befehle stehen zur Verfügung:\n-spielen\n-simulation\n-exit\n");

		boolean exit = false;

		//Hauptprogramm
		while(!exit) {

			//Befehl einlesen
			System.out.println("Was möchtest du tun?\n");
			String eingabe = s.nextLine();

			switch(eingabe) {

			//Mensch gegen KI
			case("spielen"): counter = 0;
							 play(spiel, t);
							 break;

			//KI gegen KI Simulationen
			case("simulation"): System.out.println("Wie viele Spiele sollen von der K.I simuliert werden (>0)\n");
								int n = 0;

								//Anzahl größer 0 einlesen
								do {
									try {
										n = Integer.parseInt(s.nextLine());
									}catch(Exception e) {
										System.out.println("ungültige Anzahl");
									}
								}while(n < 1);

								//Simulationen durchführen
								for(int i = 0; i < n; i++) {
									counter = 0;
									playAI(spiel, t);
									Thread.sleep(5000);
								}
								break;

			//Programm beenden
			case("exit"): exit = true;
						  s.close();
						  break;
			default:
			}
		}
	}

	//das Spiel an sich
	private static void play(Board spiel, GameTree t) throws InterruptedException{

		//setup
		spiel.setup();
		spiel.draw();

		//GameTree kopieren, damit der ursprüngliche nicht verändert wird und beliebig viele Runden gespielt werden können
		GameTree b = new GameTree();
		b.copy(t);


		//Gameloop
		while(true) {

			//Spiel zuende
			if(spiel.check() == 0) {
				System.out.println("Remis");
				return;
			}

			if(spiel.check() == 1) {
				System.out.println("Weiß gewinnt");
				return;
			}
			if(spiel.check() == -1) {
				System.out.println("Schwarz gewinnt");
				return;
			}


			//weiß ist am zug
			if(counter%2 == 0) {
				int[] click1 = eidi.Display.getClickPosition();
				int[] click2 = eidi.Display.getClickPosition();

				Board tmp = spiel.makeMove(click1, click2);

				//mögliche Züge
				Board[] hlp = spiel.possibleMoves(0);

				//passender Zug gewählt
				for(int i = 0; i < hlp.length; i++) {
					if(tmp.equals(hlp[i])) {
						spiel = tmp;
						spiel.draw();
						counter++;
						Thread.sleep(1000);
					}
				}
			}
			//K.I ist am Zug
			else {
				spiel = b.bestMove(spiel, 1);
				spiel.draw();
				counter++;
			}
		}

	}


	//K.I vs K.I spielen lassen
	private static void playAI(Board spiel, GameTree t) throws InterruptedException{

		//setup
		spiel.setup();
		spiel.draw();

		Random random = new Random(System.currentTimeMillis());

		//GameTree kopieren, damit der ursprüngliche nicht verändert wird und beliebig viele Runden gespielt werden können
		GameTree b = new GameTree();
		GameTree w = new GameTree();
		b.copy(t);
		w.copy(t);


		//Gameloop
		while(true) {

			//Spiel zuende
			if(spiel.check() == 0) {
				System.out.println("Remis");
				return;
			}

			if(spiel.check() == 1) {
				System.out.println("White wins");
				return;
			}
			if(spiel.check() == -1) {
				System.out.println("Black wins");
				return;
			}


			//weiß ist am zug
			if(counter%2 == 0) {

				//erster Zug
				if(counter == 0) {
					Thread.sleep(1000);
					Board[] tmp = spiel.possibleMoves(0);
					spiel = tmp[random.nextInt(4)];

					//GameTree von weiß anpassen
					for(int i = 0; i < 4; i++) {
						if(w.folge[i].zustand.equals(spiel)) {
							w.zustand = w.folge[i].zustand;
							w.folge = w.folge[i].folge;
						}
					}

					spiel.draw();
					counter++;
				}
				//alle anderen Züge
				else {
					Thread.sleep(1000);
					spiel = w.bestMove(spiel, -1);
					spiel.draw();
					counter++;
				}
			}

			//K.I ist am Zug
			else {
				Thread.sleep(1000);
				spiel = b.bestMove(spiel, 1);
				spiel.draw();
				counter++;
			}
		}

	}
}
