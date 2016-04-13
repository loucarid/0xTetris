package components;

import java.awt.*;
import javax.swing.*;

public class PieceEditCanvas extends JComponent {
	private Matrix mat;

	public PieceEditCanvas(int size) {
		this.mat = new Matrix(size);
		this.setBackground(Color.yellow);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(this.mat.getSize() * 24 + 1,
				this.mat.getSize() * 24 + 1);
	}

	@Override
	public Dimension getPreferredSize() {
		return this.getMinimumSize();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		int x = 0;
		while (x <= 24 * this.mat.getSize()) {
			g.drawLine(x, 0, x, 24 * this.mat.getSize());
			x += 24;
		}
		int y = 0;
		while (y <= 24 * this.mat.getSize()) {
			g.drawLine(0, y, 24 * this.mat.getSize(), y);
			y += 24;
		}
		x = 0;
		while (x < this.mat.getSize()) {
			int y2 = 0;
			while (y2 < this.mat.getSize()) {
				if (this.mat.getVal(x, y2) != -1) {
					new BlocGraphique(
							Couleur.getCouleur(this.mat.getVal(x, y2)), x, y2)
							.draw(g);
				}
				++y2;
			}
			++x;
		}
	}

	public void setMat(Matrix mat) {
		if (mat.getSize() != this.mat.getSize()) {
			this.mat = mat;
		} else {
			int x = 0;
			while (x < mat.getSize()) {
				int y = 0;
				while (y < mat.getSize()) {
					this.mat.setVal(x, y, mat.getVal(x, y));
					++y;
				}
				++x;
			}
		}
	}

	@Override
	public void update(Graphics g) {
		this.paint(g);
	}
}
