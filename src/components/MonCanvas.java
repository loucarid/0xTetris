package components;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.*;
import javax.swing.*;

public class MonCanvas extends JComponent implements Runnable {
	private Image[] image;
	private MediaTracker MT;
	private Game game;
	private int timer;
	private Thread thread;
	private Piece suivante;
	private Tetris tetris;
	private int niveau;

	public MonCanvas(Tetris tetris) {
		this.game = null;
		this.tetris = tetris;
		this.image = new Image[10];
		this.MT = new MediaTracker((Component) (this));
		try {
			this.image[0] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau1.jpg"));
			this.image[1] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau2.jpg"));
			this.image[2] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau3.jpg"));
			this.image[3] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau4.jpg"));
			this.image[4] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau5.jpg"));
			this.image[5] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau6.jpg"));
			this.image[6] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau7.jpg"));
			this.image[7] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau8.jpg"));
			this.image[8] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau9.jpg"));
			this.image[9] = Toolkit
					.getDefaultToolkit()
					.getImage(
							new URL(
									"http://www.essi.fr/~buiguez/buffa/tetris/fond/niveau10.jpg"));
		} catch (MalformedURLException v0) {
		}
		this.MT.addImage(this.image[0], 0);
		this.MT.addImage(this.image[1], 1);
		this.MT.addImage(this.image[2], 2);
		this.MT.addImage(this.image[3], 3);
		this.MT.addImage(this.image[4], 4);
		this.MT.addImage(this.image[5], 5);
		this.MT.addImage(this.image[6], 6);
		this.MT.addImage(this.image[7], 7);
		this.MT.addImage(this.image[8], 8);
		this.MT.addImage(this.image[9], 9);
		try {
			this.MT.waitForAll();
		} catch (InterruptedException v1) {
		}
		this.niveau = 1;
		this.timer = 1000;
		this.game = new Game(12, 20, tetris.getCps());
		this.start();
	}

	public Game getJeu() {
		return this.game;
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(290, 482);
	}

	@Override
	public Dimension getPreferredSize() {
		return this.getMinimumSize();
	}

	@Override
	public void paint(Graphics g) {
		if (this.MT.statusID(this.niveau - 1, false) == 8) {
			g.drawImage(this.image[this.niveau - 1], 1, 1,
					(ImageObserver) (this));
		}
		g.setColor(Color.black);
		g.drawRect(0, 0, 289, 481);
		this.game.draw(g);
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(this.timer);
			} catch (InterruptedException v0) {
			}
			if (!this.game.play()) {
				this.game.testScore();
				this.tetris.setEnabledMenuOptions();
				this.thread.suspend();
			}
			this.tetris.setLabelScore(this.game.getScore());
			this.tetris.setLabelLigne(this.game.getLigne());
			int nouveauNiveau = this.game.getNiveau();
			this.tetris.setLabelLevel(nouveauNiveau);
			if (nouveauNiveau != this.niveau) {
				this.timer = (int) (1000.0 * Math.pow(0.6666666865348816,
						nouveauNiveau - 1));
				this.niveau = nouveauNiveau;
			}
			this.repaint();
		} while (true);
	}

	public void setJeu(Game jeu) {
		this.game = jeu;
	}

	public void start() {
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void startThread() {
		this.thread.resume();
	}

	public void stopThread() {
		this.thread.interrupt();
	}

	public void testKey(int code) {
		this.game.testKey(code);
		this.repaint();
	}

	@Override
	public void update(Graphics g) {
		this.paint(g);
	}
}
