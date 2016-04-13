
/*
 * Decompiled with CFR 0_114.
 */
public class ElleInverse extends Piece {
	public ElleInverse(int posX, int posY) {
		super(posX, posY);
		this.mat = new Matrix(3);
		int couleur = (int) (Math.random() * 4.0);
		this.mat.setVal(1, 0, couleur);
		this.mat.setVal(1, 1, couleur);
		this.mat.setVal(1, 2, couleur);
		this.mat.setVal(0, 2, couleur);
	}
}
