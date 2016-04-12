/*
 * Decompiled with CFR 0_114.
 */
import java.awt.Graphics;

public class Piece {
	protected Matrix mat = null;
	public int posX;
	public int posY;

	public Piece(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void down() {
		++this.posY;
	}

	public void draw(Graphics g) {
		int miyeuPiece = this.mat.getSize() / 2;
		int x = 0;
		while (x < this.getSize()) {
			int y = 0;
			while (y < this.getSize()) {
				if (this.mat.getVal(x, y) != -1) {
					new BlocGraphique(
							Couleur.getCouleur(this.mat.getVal(x, y)),
							this.posX + x - miyeuPiece, this.posY + y
									- miyeuPiece).draw(g);
				}
				++y;
			}
			++x;
		}
	}

	public void drawSuivante(Graphics g) {
		int miyeuPiece = this.mat.getSize() / 2;
		int x = 0;
		while (x < this.getSize()) {
			int y = 0;
			while (y < this.getSize()) {
				if (this.mat.getVal(x, y) != -1) {
					new BlocGraphique(
							Couleur.getCouleur(this.mat.getVal(x, y)), 3 + x
									- miyeuPiece, 3 + y - miyeuPiece).draw(g);
				}
				++y;
			}
			++x;
		}
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public int getSize() {
		return this.mat.getSize();
	}

	public int getVal(int x, int y) {
		return this.mat.getVal(x, y);
	}

	public void rotation(boolean sens) {
		this.mat.rotation(sens);
	}

	public void translation(boolean sens) {
		this.posX = sens ? ++this.posX : --this.posX;
	}

	public void up() {
		--this.posY;
	}
}
