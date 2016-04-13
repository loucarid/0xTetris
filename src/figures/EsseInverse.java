package figures;
import components.Matrix;
import components.Piece;

/**
 * Piece de type: frome en 'S' inversee.
 */
public class EsseInverse extends Piece {
	public EsseInverse(int posX, int posY) {
		super(posX, posY);
		this.mat = new Matrix(3);
		int couleur = (int) (Math.random() * 4.0);
		this.mat.setVal(1, 1, couleur);
		this.mat.setVal(0, 1, couleur);
		this.mat.setVal(1, 2, couleur);
		this.mat.setVal(2, 2, couleur);
	}
}
