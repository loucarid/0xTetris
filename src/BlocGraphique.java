/*
 * Decompiled with CFR 0_114.
 */
import java.awt.Color;
import java.awt.Graphics;

public class BlocGraphique {
	private int posX;
	private int posY;
	private Color couleur;

	public BlocGraphique(Color couleur, int posX, int posY) {
		this.couleur = couleur;
		this.posX = posX;
		this.posY = posY;
	}

	public void draw(Graphics g) {
		g.setColor(this.couleur);
		g.fill3DRect(this.posX * 24 + 1, this.posY * 24 + 1, 24, 24, true);
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
