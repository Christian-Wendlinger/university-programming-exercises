//Klasse, die das Spielbrett speichert und visualisiert
//Onur, Christian

import eidi.Display.*;

public class Board {

	//Spielfeld
	public Color[][] board;
	public String[][] images;

	//leeres Board initialisieren
	public Board() {
		board = new Color[4][4];
		images = new String[4][4];
	}

	//Anfangszustand initialisieren
	public void setup() {

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				//schwarze Bauern setzen
				if(j == 0) {
					images[i][j] = "black.png";
				}
				//weiße Bauern setzen
				if(j == 3) {
					images[i][j] = "white.png";
				}

				//Muster erstellen
				drawTiles();
			}
		}
	}

	//alle möglichen Spielzüge für weiß und schwarz werden in einem Array als boards gespeichert
	public Board[] possibleMoves(int index) {

		//zählt die möglichen Züge
		int moves = 0;
		Board[] zug = new Board[moves];

		//weiß an der Reihe
		if(index == 0) {

			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {

					//weißer Bauer gefunden
					if(images[i][j] != null && images[i][j].equals("white.png")) {

						//Zug nach vorne, wenn das Brett nicht zuende ist und kein Bauer im Weg steht
						if((j-1) >= 0 && images[i][j-1] == null) {
							zug = addpossibleMove(zug);
							zug[moves] = action(images, i, j, i, j-1);
							moves++;
						}

						//Schlagen nach links, wenn Brett nicht zuende ist und dort ein Bauer steht
						if((i-1) >= 0 && (j-1) >= 0 && images[i-1][j-1] != null && images[i-1][j-1].equals("black.png")) {
							zug = addpossibleMove(zug);
							zug[moves] = action(images, i, j, i-1, j-1);
							moves++;
						}

						//Schlagen nach rechts, wenn Brett nicht zuende ist und dort ein Bauer steht
						if((i+1) < 4 && (j-1) >= 0 && images[i+1][j-1] != null && images[i+1][j-1].equals("black.png")) {
							zug = addpossibleMove(zug);
							zug[moves] = action(images, i, j, i+1, j-1);
							moves++;
						}
					}
				}
			}
		}
		//schwarz an der Reihe
		else {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {

					//weißer Bauer gefunden
					if(images[i][j] != null && images[i][j].equals("black.png")) {

						//Zug nach vorne, wenn das Brett nicht zuende ist und kein Bauer im Weg steht
						if((j+1) < 4 && images[i][j+1] == null) {
							zug = addpossibleMove(zug);
							zug[moves] = action(images, i, j, i, j+1);
							moves++;
						}

						//Schlagen nach links, wenn Brett nicht zuende ist und dort ein Bauer steht
						if((i-1) >= 0 && (j+1) < 4 && images[i-1][j+1] != null && images[i-1][j+1].equals("white.png")) {
							zug = addpossibleMove(zug);
							zug[moves] = action(images, i, j, i-1, j+1);
							moves++;
						}

						//Schlagen nach rechts, wenn Brett nicht zuende ist und dort ein Bauer steht
						if((i+1) < 4 && (j+1) < 4 && images[i+1][j+1] != null && images[i+1][j+1].equals("white.png")) {
							zug = addpossibleMove(zug);
							zug[moves] = action(images, i, j, i+1, j+1);
							moves++;
						}
					}
				}
			}
		}

		return zug;
	}

	//Positionen tauschen
	private Board action(String[][] images, int posx1, int posy1, int posx2, int posy2) {

		Board tmp = new Board();

		//alle Bauern, die nicht geändert werden kopieren
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				if(images[i][j] != null && !(i == posx1 && j == posy1 || i == posx2 && j == posy2)) {
					tmp.images[i][j] = images[i][j];
				}
			}
		}


		//die Positionen überschreiben
		tmp.images[posx2][posy2] = images[posx1][posy1];
		tmp.drawTiles();

		return tmp;
	}

	//Hintergund zeichnen
	private void drawTiles() {

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				//Farben setzen
				if((i+j)%2 == 0) {
					board[i][j] = Color.DARK_GREY;
				}
				else{
					board[i][j] = Color.LIGHT_GREY;
				}
			}
		}
	}


	//Array um 1 verlängern
	private Board[] addpossibleMove(Board[] a) {

		Board[] b = new Board[a.length+1];

		for(int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}

		return b;
	}

	//visualisieren
	public void draw() {
		eidi.Display.set(board, images);
	}

	//prüfen, ob das Spiel vorbei ist
	public int check() {

		Board[] white = possibleMoves(0);
		Board[] black = possibleMoves(1);

		//prüfen, ob ein Bauer die andere Seite erreicht hat
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				//weißer Bauer am anderen Ende
				if(j == 0 && images[i][j] != null && images[i][j] == "white.png") {
					return 1;
				}
				//schwarzer Bauer am anderen Ende
				else if (j == 3 && images[i][j] != null && images[i][j] == "black.png"){
					return -1;
				}
			}
		}

		//Kein Bauer auf der anderen Seite, aber Spiel trotzdem zuende

		//Unentschieden
		if(white.length == 0 && black.length == 0) {
			return 0;
		}

		//Weiß keine Züge mehr, Schwarz aber schon
		if(white.length == 0 && black.length != 0) {
			return -1;
		}

		//Schwarz keine Züge mehr, Weiß aber schon
		if(white.length != 0 && black.length == 0) {
			return 1;
		}


		//Spiel noch nicht vorbei
		return 9;
	}

	//Zug durchführen
	public Board makeMove(int[] x, int[] y) {
		Board tmp = action(images, x[0], x[1], y[0], y[1]);
		return tmp;
	}

	//equals Methode
	public boolean equals(Board board) {

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				//nicht gleich
				if(images[i][j] == null && board.images[i][j] != null) {
					return false;
				}

				if(images[i][j] != null && board.images[i][j] == null) {
					return false;
				}

				if(images[i][j] == null && board.images[i][j] == null) {
					continue;
				}

				if(!images[i][j].equals(board.images[i][j])) {
					return false;
				}
			}
		}
		//gleich
		return true;
	}
}
