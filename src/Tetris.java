import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tetris extends JFrame implements KeyListener {
	private JPanel panel;
	private MenuBar barreMenu;
	public JLabel labelApercu;
	public JLabel labelScore;
	private JLabel labelLevel;
	private JLabel labelLigne;
	private MonCanvas canvas;
	private CanvasPieceSuivante cps;

	public Tetris() {
		this.setTitle("Tetris");
		this.setResizable(false);
		this.setSize(555, 550);
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception v0) {
		}
		/*
		this.addWindowListener(new WindowAdapter(this) {
			/* synthetic Tetris this$0;

			public void windowClosing(WindowEvent e) {
				this.this$0.dispose();
				System.exit(1);
			}
		});*/
		this.panel = new JPanel();
		this.panel.setBackground(new Color(189, 211, 211));
		this.panel.setLayout(null);
		this.barreMenu = new MenuBar(this);
		this.setJMenuBar(this.barreMenu);
		this.addKeyListener(this);
		this.cps = new CanvasPieceSuivante();
		this.cps.setBounds(350, 45, 162, 162);
		this.panel.add((Component) (this.cps));
		this.canvas = new MonCanvas(this);
		this.canvas.setBounds(17, 13, 290, 482);
		this.panel.add((Component) (this.canvas));
		this.labelApercu = new JLabel();
		this.labelApercu.setText("PIECE SUIVANTE");
		this.labelApercu.setForeground(Color.black);
		this.labelApercu.setFont(new Font("dialog", 1, 24));
		this.labelApercu.setBounds(320, 5, 270, 32);
		this.panel.add(this.labelApercu);
		this.labelLevel = new JLabel("NIVEAU : 1 ");
		this.labelLevel.setForeground(Color.red);
		this.labelLevel.setFont(new Font("dialog", 1, 24));
		this.labelLevel.setBounds(350, 240, 250, 36);
		this.panel.add(this.labelLevel);
		this.labelScore = new JLabel("SCORE  : 0 ");
		this.labelScore.setForeground(Color.blue);
		this.labelScore.setFont(new Font("dialog", 1, 24));
		this.labelScore.setBounds(350, 290, 250, 36);
		this.panel.add(this.labelScore);
		this.labelLigne = new JLabel("LIGNE   : 0 ");
		this.labelLigne.setForeground(Color.yellow);
		this.labelLigne.setFont(new Font("dialog", 1, 24));
		this.labelLigne.setBounds(350, 350, 250, 36);
		this.panel.add(this.labelLigne);
		this.getContentPane().add(this.panel);
		this.show();
	}

	public MonCanvas getCanvas() {
		return this.canvas;
	}

	public CanvasPieceSuivante getCps() {
		return this.cps;
	}

	public JLabel getLabelApercu() {
		return this.labelApercu;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.canvas.testKey(e.getKeyCode());
		this.canvas.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		new Tetris();
	}

	public void setEnabledMenuOptions() {
		this.barreMenu.setEnabledMenuOptions(true);
	}

	public void setLabelLevel(int level) {
		this.labelLevel.setText("NIVEAU : " + level);
	}

	public void setLabelLigne(int ligne) {
		if (ligne > 1) {
			this.labelLigne.setText("LIGNES : " + ligne);
		} else {
			this.labelLigne.setText("LIGNE   : " + ligne);
		}
	}

	public void setLabelScore(int score) {
		this.labelScore.setText("SCORE  : " + score);
	}

}
