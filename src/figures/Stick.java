package figures;
import components.Matrix;
import components.Piece;



public class Stick extends Piece {
	public Stick(int posX, int posY) {
		super(posX, posY);
		this.mat = new Matrix(4);
		int couleur = (int) (Math.random() * 4.0);
		this.mat.setVal(2, 0, couleur);
		this.mat.setVal(2, 1, couleur);
		this.mat.setVal(2, 2, couleur);
		this.mat.setVal(2, 3, couleur);
	}

	@Override
	public void rotation(boolean sens) {
		this.mat.transpose();
	}
}
