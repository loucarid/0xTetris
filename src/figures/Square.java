package figures;

import components.Matrix;
import components.Piece;

public class Square extends Piece {
	public Square(int posX, int posY) {
		super(posX, posY);
		this.mat = new Matrix(2);
		int couleur = (int) (Math.random() * 4.0);
		this.mat.setVal(0, 0, couleur);
		this.mat.setVal(1, 0, couleur);
		this.mat.setVal(0, 1, couleur);
		this.mat.setVal(1, 1, couleur);
	}
}
