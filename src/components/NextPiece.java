package components;

import java.awt.*;
import javax.swing.*;

public class NextPiece extends JComponent {
	Dimension size;
	Piece suivante;

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(162, 162);
	}

	@Override
	public Dimension getPreferredSize() {
		return this.getMinimumSize();
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, 162, 162);
		g.setColor(Color.black);
		g.drawLine(0, 0, 161, 0);
		g.drawLine(0, 0, 0, 161);
		g.setColor(Color.white);
		g.drawLine(161, 0, 161, 161);
		g.drawLine(0, 161, 161, 161);
		g.setColor(new Color(220, 220, 220));
		g.fillRect(1, 1, 160, 160);
		this.suivante.drawSuivante(g);
	}

	public void setPiece(Piece suivante) {
		this.suivante = suivante;
		this.repaint();
	}

	@Override
	public void update(Graphics g) {
		this.paint(g);
	}
}
