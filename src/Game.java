/*
 * Decompiled with CFR 0_114.
 */
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class Game {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private int width;
	private int height;
	private int[][] mat;
	private Piece enCours;
	private Piece suivante;
	private int score;
	private int ligne;
	private int ligneNiveau;
	private int niveau;
	private CanvasPieceSuivante cps;
	private Vector userMatrix;

	public Game(int width, int height, CanvasPieceSuivante cps) {
		this.width = width;
		this.height = height;
		this.cps = cps;
		this.userMatrix = new Vector();
		this.mat = new int[width][height];
		int i = 0;
		while (i < width) {
			int j = 0;
			while (j < height) {
				this.mat[i][j] = -1;
				++j;
			}
			++i;
		}
		this.enCours = null;
		this.suivante = null;
		this.nextPiece();
		this.nextPiece();
		this.score = 0;
		this.ligne = 0;
		this.ligneNiveau = 0;
		this.niveau = 1;
	}

	public void addPiece2Jeu(Piece piece) {
		int pieceX = piece.getPosX() - piece.getSize() / 2;
		int pieceY = piece.getPosY() - piece.getSize() / 2;
		int x = 0;
		while (x < piece.getSize()) {
			int y = 0;
			while (y < piece.getSize()) {
				if (pieceX + x < this.width && pieceY + y < this.height
						&& piece.getVal(x, y) != -1) {
					this.mat[pieceX + x][pieceY + y] = piece.getVal(x, y);
				}
				++y;
			}
			++x;
		}
	}

	public void addUserPiece(Matrix mat) {
		this.userMatrix.addElement(mat);
	}

	public void draw(Graphics g) {
		this.enCours.draw(g);
		int x = 0;
		while (x < this.width) {
			int y = 0;
			while (y < this.height) {
				if (this.mat[x][y] != -1) {
					new BlocGraphique(Couleur.getCouleur(this.mat[x][y]), x, y)
							.draw(g);
				}
				++y;
			}
			++x;
		}
	}

	public void dropLine(int numLine) {
		int y = numLine;
		while (y > 0) {
			int x = 0;
			while (x < this.width) {
				this.mat[x][y] = this.mat[x][y - 1];
				++x;
			}
			--y;
		}
	}

	public int getLigne() {
		return this.ligne;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public int getScore() {
		return this.score;
	}

	public Piece getSuivante() {
		return this.suivante;
	}

	public boolean isPositionPossible(Piece piece) {
		int pieceX = piece.getPosX() - piece.getSize() / 2;
		int pieceY = piece.getPosY() - piece.getSize() / 2;
		int x = 0;
		while (x < piece.getSize()) {
			int y = 0;
			while (y < piece.getSize()) {
				if (piece.getVal(x, y) != -1
						&& (pieceX + x < 0 || pieceX + x >= this.width
								|| pieceY + y < 0 || pieceY + y >= this.height || this.mat[pieceX
								+ x][pieceY + y] != -1)) {
					return false;
				}
				++y;
			}
			++x;
		}
		return true;
	}

	public void nextPiece() {
		this.enCours = this.suivante;
		int numTypePiece = (int) (Math.random() * (7 + this.userMatrix
				.size()));
		switch (numTypePiece) {
		case 0: {
			this.suivante = new Square((int) (Math.random()
					* (this.width - 4) + 2.0), 2);
			break;
		}
		case 1: {
			this.suivante = new Elle((int) (Math.random()
					* (this.width - 4) + 2.0), 2);
			break;
		}
		case 2: {
			this.suivante = new Esse((int) (Math.random()
					* (this.width - 4) + 2.0), 2);
			break;
		}
		case 3: {
			this.suivante = new Stick((int) (Math.random()
					* (this.width - 4) + 2.0), 2);
			break;
		}
		case 4: {
			this.suivante = new Te((int) (Math.random()
					* (this.width - 4) + 2.0), 2);
			break;
		}
		case 5: {
			this.suivante = new EsseInverse((int) (Math.random()
					* (this.width - 4) + 2.0), 2);
			break;
		}
		case 6: {
			this.suivante = new ElleInverse((int) (Math.random()
					* (this.width - 4) + 2.0), 2);
			break;
		}
		default: {
			Matrix mat = (Matrix) this.userMatrix.elementAt(numTypePiece - 7);
			this.suivante = new UserPiece(
					(int) (Math.random()
							* (this.width - mat.getSize()) + (mat
							.getSize() + 1) / 2), (mat.getSize() + 1) / 2, mat);
		}
		}
		this.cps.setPiece(this.suivante);
	}

	public void nouveauJeu() {
		int j = 0;
		while (j < this.height) {
			int i = 0;
			while (i < this.width) {
				this.mat[i][j] = -1;
				++i;
			}
			++j;
		}
		this.enCours = null;
		this.suivante = null;
		this.nextPiece();
		this.nextPiece();
		this.score = 0;
		this.ligne = 0;
	}

	public boolean play() {
		if (this.socket != null) {
			try {
				if (this.in.available() > 0) {
					int handicap = this.in.read();
					switch (handicap) {
					case 0: {
						this.setSocket(null, false);
						new JFrameMsg("Ev\u00e9nement reseau",
								" Le joueur n\u00b02 a abandonn\u00e9 la partie !");
						break;
					}
					case 1: {
						this.setSocket(null, false);
						new JFrameMsg("WAHOOO!",
								"Bravo !! Vous avez vaincu votre adversaire !");
						break;
					}
					default: {
						this.setHandicap(handicap);
						break;
					}
					}
				}
			} catch (IOException v0) {
			}
		}
		this.enCours.down();
		if (!this.isPositionPossible(this.enCours)) {
			this.enCours.up();
			this.addPiece2Jeu(this.enCours);
			int nbLine = this.verifieLine();
			if (nbLine == 0) {
				this.score += 10;
			} else {
				if (this.socket != null && nbLine > 1) {
					try {
						this.out.write(nbLine);
					} catch (IOException v1) {
					}
				}
				this.ligneNiveau += nbLine;
				this.score += 100 * (int) Math.pow(2.0, nbLine - 1);
				this.ligne += nbLine;
				if (this.ligneNiveau >= 10) {
					++this.niveau;
					this.ligneNiveau -= 10;
				}
			}
			this.nextPiece();
			if (!this.isPositionPossible(this.enCours)) {
				if (this.socket != null) {
					try {
						this.out.write(1);
					} catch (IOException v2) {
					}
					this.setSocket(null, false);
				}
				return false;
			}
		}
		return true;
	}

	public void setHandicap(int nbRangees) {
		int i;
		int j = 0;
		while (j <= this.height - 1 - nbRangees) {
			i = 0;
			while (i < this.width) {
				this.mat[i][j] = this.mat[i][j + nbRangees];
				++i;
			}
			++j;
		}
		j = this.height - nbRangees;
		while (j < this.height) {
			i = 0;
			while (i < this.width) {
				int couleur = (int) (Math.random() * Couleur.getSize()
						* 2.0 - 1.0);
				this.mat[i][j] = couleur < Couleur.getSize() ? couleur : -1;
				++i;
			}
			++j;
		}
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public void setSocket(Socket socket, boolean prevenirLAutre) {
		if (socket != null) {
			try {
				this.in = new DataInputStream(socket.getInputStream());
			} catch (IOException v0) {
				try {
					socket.close();
				} catch (IOException v1) {
				}
				socket = null;
			}
			try {
				this.out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException v2) {
				try {
					socket.close();
				} catch (IOException v3) {
				}
				socket = null;
			}
		} else if (this.socket != null) {
			try {
				if (prevenirLAutre) {
					this.out.write(0);
				}
				this.in.close();
				this.out.close();
				this.socket.close();
			} catch (IOException v4) {
			}
		}
		this.socket = socket;
	}

	public void testKey(int code) {
		switch (code) {
		case 37: {
			this.enCours.translation(false);
			if (this.isPositionPossible(this.enCours))
				break;
			this.enCours.translation(true);
			break;
		}
		case 39: {
			this.enCours.translation(true);
			if (this.isPositionPossible(this.enCours))
				break;
			this.enCours.translation(false);
			break;
		}
		case 40: {
			this.enCours.down();
			if (this.isPositionPossible(this.enCours))
				break;
			this.enCours.up();
			break;
		}
		case 38: {
			this.enCours.rotation(true);
			if (this.isPositionPossible(this.enCours))
				break;
			this.enCours.rotation(false);
		}
		}
	}

	public void testScore() {
		new FrameBestScore(this.score, this.niveau, this.ligne);
	}

	public int verifieLine() {
		int retour = 0;
		int y = 0;
		while (y < this.height) {
			int x = 0;
			while (x < this.width) {
				if (this.mat[x][y] == -1)
					break;
				++x;
			}
			if (x == this.width) {
				++retour;
				this.dropLine(y);
				--y;
			}
			++y;
		}
		return retour;
	}
}
