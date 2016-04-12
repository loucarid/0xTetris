import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuBar extends JMenuBar implements ActionListener {
	private static int handicap;
	private static int niveau;
	private Tetris tetris;
	private JMenu menuJeu;
	private JMenu menuOptions;
	private JMenu menuOptionsNiveau;
	private JMenu menuOptionsHandicap;
	private JMenu menuPersonnaliser;
	private JMenu menuAide;
	private JMenuItem itemNouvelle;
	private JMenuItem itemPause;
	private JMenuItem itemDemarrer;
	private JMenuItem itemBestScore;
	private JMenuItem itemEditer;
	private JMenuItem itemAPropos;
	private JMenuItem itemAideEnLigne;
	private JMenuItem itemQuitter;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	private JSeparator separator4;
	private JSeparator separator5;
	private JSeparator separator6;
	private JSeparator separator7;
	private JCheckBoxMenuItem checkBoxSon;
	private JCheckBoxMenuItem checkBoxVoirPiece;
	private JRadioButtonMenuItem radioButton1Player;
	private JRadioButtonMenuItem radioButton2Player;
	private JRadioButtonMenuItem radioButtonNiveau1;
	private JRadioButtonMenuItem radioButtonNiveau2;
	private JRadioButtonMenuItem radioButtonNiveau3;
	private JRadioButtonMenuItem radioButtonNiveau4;
	private JRadioButtonMenuItem radioButtonNiveau5;
	private JRadioButtonMenuItem radioButtonNiveau6;
	private JRadioButtonMenuItem radioButtonNiveau7;
	private JRadioButtonMenuItem radioButtonNiveau8;
	private JRadioButtonMenuItem radioButtonNiveau9;
	private JRadioButtonMenuItem radioButtonNiveau10;
	private JRadioButtonMenuItem radioButtonHandicap0;
	private JRadioButtonMenuItem radioButtonHandicap1;
	private JRadioButtonMenuItem radioButtonHandicap2;
	private JRadioButtonMenuItem radioButtonHandicap3;
	private JRadioButtonMenuItem radioButtonHandicap4;
	private JRadioButtonMenuItem radioButtonHandicap5;
	private JRadioButtonMenuItem radioButtonHandicap6;
	private JRadioButtonMenuItem radioButtonHandicap7;

	public MenuBar(Tetris tetris) {
		handicap = 0;
		niveau = 1;
		this.tetris = tetris;
		this.menuJeu();
		this.menuOptions();
		this.menuPersonnaliser();
		this.menuAide();
		this.add(this.menuJeu);
		this.add(this.menuOptions);
		this.add(this.menuPersonnaliser);
		this.add(this.menuAide);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String commande = e.getActionCommand();
		MonCanvas canvas = this.tetris.getCanvas();
		Game jeu = canvas.getJeu();
		if (commande.equals("Nouvelle")) {
			this.setEnabledMenuOptions(false);
			jeu.nouveauJeu();
			jeu.setHandicap(handicap);
			jeu.setNiveau(niveau);
			canvas.startThread();
			canvas.repaint();
		} else if (commande.equals("Pause")) {
			canvas.stopThread();
			this.itemPause.setEnabled(false);
			this.itemDemarrer.setEnabled(true);
		} else if (commande.equals("Redemarrer")) {
			canvas.startThread();
			this.itemPause.setEnabled(true);
			this.itemDemarrer.setEnabled(false);
		} else if (commande.equals("Quitter")) {
			System.exit(1);
		} else if (commande.equals("Aper\u00e7u de la pi\u00e8ce")) {
			if (this.checkBoxVoirPiece.getState()) {
				this.tetris.getCps().setVisible(true);
				this.tetris.getLabelApercu().setVisible(true);
			} else {
				this.tetris.getLabelApercu().setVisible(false);
				this.tetris.getCps().setVisible(false);
			}
		} else if (commande.equals("Niveau 1")) {
			this.resetNiveau();
			niveau = 1;
			jeu.setNiveau(1);
		} else if (commande.equals("Niveau 2")) {
			this.resetNiveau();
			niveau = 2;
			jeu.setNiveau(2);
		} else if (commande.equals("Niveau 3")) {
			this.resetNiveau();
			niveau = 3;
			jeu.setNiveau(3);
		} else if (commande.equals("Niveau 4")) {
			this.resetNiveau();
			niveau = 4;
			jeu.setNiveau(4);
		} else if (commande.equals("Niveau 5")) {
			this.resetNiveau();
			niveau = 5;
			jeu.setNiveau(5);
		} else if (commande.equals("Niveau 6")) {
			this.resetNiveau();
			niveau = 6;
			jeu.setNiveau(6);
		} else if (commande.equals("Niveau 7")) {
			this.resetNiveau();
			niveau = 7;
			jeu.setNiveau(7);
		} else if (commande.equals("Niveau 8")) {
			this.resetNiveau();
			niveau = 8;
			jeu.setNiveau(8);
		} else if (commande.equals("Niveau 9")) {
			this.resetNiveau();
			niveau = 9;
			jeu.setNiveau(9);
		} else if (commande.equals("Niveau 10")) {
			this.resetNiveau();
			niveau = 10;
			jeu.setNiveau(10);
		} else if (commande.equals("Handicap 0")) {
			this.resetHandicap();
			handicap = 0;
		} else if (commande.equals("Handicap 1")) {
			this.resetHandicap();
			handicap = 1;
		} else if (commande.equals("Handicap 2")) {
			this.resetHandicap();
			handicap = 2;
		} else if (commande.equals("Handicap 3")) {
			this.resetHandicap();
			handicap = 3;
		} else if (commande.equals("Handicap 4")) {
			this.resetHandicap();
			handicap = 4;
		} else if (commande.equals("Handicap 5")) {
			this.resetHandicap();
			handicap = 5;
		} else if (commande.equals("Handicap 6")) {
			this.resetHandicap();
			handicap = 6;
		} else if (commande.equals("Handicap 7")) {
			this.resetHandicap();
			handicap = 7;
		} else if (commande.equals("1 Joueur")) {
			this.radioButton1Player.setSelected(true);
			this.radioButton2Player.setSelected(false);
			jeu.setSocket(null, true);
		} else if (commande.equals("2 Joueurs")) {
			this.radioButton1Player.setSelected(false);
			this.radioButton2Player.setSelected(true);
			new ConnectFrame(jeu, "");
		} else if (commande.equals("Meilleurs Scores")) {
			new FrameBestScore();
		} else if (commande.equals("Editer Nouvelle Pi\u00e8ce")) {
			new PieceEdit(5, jeu);
		} else if (commande.equals("A Propos...")) {
			new About();
		} else if (commande.equals("Aide en Ligne")) {
			new FrameHelp();
		}
	}

	public void menuAide() {
		this.menuAide = new JMenu("Aide");
		this.menuAide.setMnemonic('a');
		this.menuAide.setHorizontalAlignment(0);
		this.menuAide.setHorizontalTextPosition(0);
		this.itemAPropos = new JMenuItem("A Propos...");
		this.separator1 = new JSeparator();
		this.itemAideEnLigne = new JMenuItem("Aide en Ligne");
		this.menuAide.add(this.itemAPropos);
		this.menuAide.add(this.separator1);
		this.menuAide.add(this.itemAideEnLigne);
		this.itemAPropos.addActionListener(this);
		this.itemAideEnLigne.addActionListener(this);
	}

	public void menuJeu() {
		this.menuJeu = new JMenu("Jeu");
		this.menuJeu.setMnemonic('j');
		this.menuJeu.setHorizontalAlignment(0);
		this.menuJeu.setHorizontalTextPosition(0);
		this.itemNouvelle = new JMenuItem("Nouvelle");
		this.itemNouvelle.setMnemonic('n');
		this.separator2 = new JSeparator();
		this.itemPause = new JMenuItem("Pause");
		this.itemPause.setMnemonic('p');
		this.itemDemarrer = new JMenuItem("Redemarrer");
		this.itemDemarrer.setMnemonic('r');
		this.itemDemarrer.setEnabled(true);
		this.itemDemarrer.setEnabled(false);
		this.separator3 = new JSeparator();
		this.itemQuitter = new JMenuItem("Quitter");
		this.itemQuitter.setMnemonic('q');
		this.menuJeu.add(this.itemNouvelle);
		this.menuJeu.add(this.separator2);
		this.menuJeu.add(this.itemPause);
		this.menuJeu.add(this.itemDemarrer);
		this.menuJeu.add(this.separator3);
		this.menuJeu.add(this.itemQuitter);
		this.itemNouvelle.addActionListener(this);
		this.itemPause.addActionListener(this);
		this.itemDemarrer.addActionListener(this);
		this.itemQuitter.addActionListener(this);
	}

	public void menuOptions() {
		this.menuOptions = new JMenu("Options");
		this.menuOptions.setMnemonic('O');
		this.menuOptions.setHorizontalAlignment(0);
		this.menuOptions.setHorizontalTextPosition(0);
		this.checkBoxSon = new JCheckBoxMenuItem("Son");
		this.checkBoxVoirPiece = new JCheckBoxMenuItem(
				"Aper\u00e7u de la pi\u00e8ce");
		this.checkBoxVoirPiece.setSelected(true);
		this.separator4 = new JSeparator();
		this.menuOptionsNiveau();
		this.menuOptionsHandicap();
		this.separator6 = new JSeparator();
		this.radioButton1Player = new JRadioButtonMenuItem("1 Joueur");
		this.radioButton1Player.addActionListener(this);
		this.radioButton1Player.setSelected(true);
		this.radioButton2Player = new JRadioButtonMenuItem("2 Joueurs");
		this.radioButton2Player.addActionListener(this);
		this.separator7 = new JSeparator();
		this.itemBestScore = new JMenuItem("Meilleurs Scores");
		this.menuOptions.add(this.checkBoxSon);
		this.menuOptions.add(this.checkBoxVoirPiece);
		this.menuOptions.add(this.separator4);
		this.menuOptions.add(this.menuOptionsNiveau);
		this.menuOptions.add(this.menuOptionsHandicap);
		this.menuOptions.add(this.separator6);
		this.menuOptions.add(this.radioButton1Player);
		this.menuOptions.add(this.radioButton2Player);
		this.menuOptions.add(this.separator7);
		this.menuOptions.add(this.itemBestScore);
		this.checkBoxVoirPiece.addActionListener(this);
		this.itemBestScore.addActionListener(this);
	}

	public void menuOptionsHandicap() {
		this.menuOptionsHandicap = new JMenu("Handicap");
		this.radioButtonHandicap0 = new JRadioButtonMenuItem("Handicap 0");
		this.radioButtonHandicap1 = new JRadioButtonMenuItem("Handicap 1");
		this.radioButtonHandicap2 = new JRadioButtonMenuItem("Handicap 2");
		this.radioButtonHandicap3 = new JRadioButtonMenuItem("Handicap 3");
		this.radioButtonHandicap4 = new JRadioButtonMenuItem("Handicap 4");
		this.radioButtonHandicap5 = new JRadioButtonMenuItem("Handicap 5");
		this.radioButtonHandicap6 = new JRadioButtonMenuItem("Handicap 6");
		this.radioButtonHandicap7 = new JRadioButtonMenuItem("Handicap 7");
		this.menuOptionsHandicap.add(this.radioButtonHandicap0);
		this.menuOptionsHandicap.add(this.radioButtonHandicap1);
		this.menuOptionsHandicap.add(this.radioButtonHandicap2);
		this.menuOptionsHandicap.add(this.radioButtonHandicap3);
		this.menuOptionsHandicap.add(this.radioButtonHandicap4);
		this.menuOptionsHandicap.add(this.radioButtonHandicap5);
		this.menuOptionsHandicap.add(this.radioButtonHandicap6);
		this.menuOptionsHandicap.add(this.radioButtonHandicap7);
		this.radioButtonHandicap0.addActionListener(this);
		this.radioButtonHandicap1.addActionListener(this);
		this.radioButtonHandicap2.addActionListener(this);
		this.radioButtonHandicap3.addActionListener(this);
		this.radioButtonHandicap4.addActionListener(this);
		this.radioButtonHandicap5.addActionListener(this);
		this.radioButtonHandicap6.addActionListener(this);
		this.radioButtonHandicap7.addActionListener(this);
		this.radioButtonHandicap0.setSelected(true);
		this.menuOptionsHandicap.setEnabled(true);
	}

	public void menuOptionsNiveau() {
		this.menuOptionsNiveau = new JMenu("Niveau");
		this.radioButtonNiveau1 = new JRadioButtonMenuItem("Niveau 1");
		this.radioButtonNiveau2 = new JRadioButtonMenuItem("Niveau 2");
		this.radioButtonNiveau3 = new JRadioButtonMenuItem("Niveau 3");
		this.radioButtonNiveau4 = new JRadioButtonMenuItem("Niveau 4");
		this.radioButtonNiveau5 = new JRadioButtonMenuItem("Niveau 5");
		this.radioButtonNiveau6 = new JRadioButtonMenuItem("Niveau 6");
		this.radioButtonNiveau7 = new JRadioButtonMenuItem("Niveau 7");
		this.radioButtonNiveau8 = new JRadioButtonMenuItem("Niveau 8");
		this.radioButtonNiveau9 = new JRadioButtonMenuItem("Niveau 9");
		this.radioButtonNiveau10 = new JRadioButtonMenuItem("Niveau 10");
		this.menuOptionsNiveau.add(this.radioButtonNiveau1);
		this.menuOptionsNiveau.add(this.radioButtonNiveau2);
		this.menuOptionsNiveau.add(this.radioButtonNiveau3);
		this.menuOptionsNiveau.add(this.radioButtonNiveau4);
		this.menuOptionsNiveau.add(this.radioButtonNiveau5);
		this.menuOptionsNiveau.add(this.radioButtonNiveau6);
		this.menuOptionsNiveau.add(this.radioButtonNiveau7);
		this.menuOptionsNiveau.add(this.radioButtonNiveau8);
		this.menuOptionsNiveau.add(this.radioButtonNiveau9);
		this.menuOptionsNiveau.add(this.radioButtonNiveau10);
		this.radioButtonNiveau1.addActionListener(this);
		this.radioButtonNiveau2.addActionListener(this);
		this.radioButtonNiveau3.addActionListener(this);
		this.radioButtonNiveau4.addActionListener(this);
		this.radioButtonNiveau5.addActionListener(this);
		this.radioButtonNiveau6.addActionListener(this);
		this.radioButtonNiveau7.addActionListener(this);
		this.radioButtonNiveau8.addActionListener(this);
		this.radioButtonNiveau9.addActionListener(this);
		this.radioButtonNiveau10.addActionListener(this);
		this.radioButtonNiveau1.setSelected(true);
		this.menuOptionsNiveau.setEnabled(true);
	}

	public void menuPersonnaliser() {
		this.menuPersonnaliser = new JMenu("Personnaliser");
		this.menuPersonnaliser.setMnemonic('p');
		this.menuPersonnaliser.setHorizontalAlignment(0);
		this.menuPersonnaliser.setHorizontalTextPosition(0);
		this.itemEditer = new JMenuItem("Editer Nouvelle Pi\u00e8ce");
		this.menuPersonnaliser.add(this.itemEditer);
		this.itemEditer.addActionListener(this);
	}

	public void resetHandicap() {
		switch (handicap) {
		case 0: {
			this.radioButtonHandicap0.setSelected(false);
			break;
		}
		case 1: {
			this.radioButtonHandicap1.setSelected(false);
			break;
		}
		case 2: {
			this.radioButtonHandicap2.setSelected(false);
			break;
		}
		case 3: {
			this.radioButtonHandicap3.setSelected(false);
			break;
		}
		case 4: {
			this.radioButtonHandicap4.setSelected(false);
			break;
		}
		case 5: {
			this.radioButtonHandicap5.setSelected(false);
			break;
		}
		case 6: {
			this.radioButtonHandicap6.setSelected(false);
			break;
		}
		case 7: {
			this.radioButtonHandicap7.setSelected(false);
		}
		}
	}

	public void resetNiveau() {
		switch (niveau) {
		case 1: {
			this.radioButtonNiveau1.setSelected(false);
			break;
		}
		case 2: {
			this.radioButtonNiveau2.setSelected(false);
			break;
		}
		case 3: {
			this.radioButtonNiveau3.setSelected(false);
			break;
		}
		case 4: {
			this.radioButtonNiveau4.setSelected(false);
			break;
		}
		case 5: {
			this.radioButtonNiveau5.setSelected(false);
			break;
		}
		case 6: {
			this.radioButtonNiveau6.setSelected(false);
			break;
		}
		case 7: {
			this.radioButtonNiveau7.setSelected(false);
			break;
		}
		case 8: {
			this.radioButtonNiveau8.setSelected(false);
			break;
		}
		case 9: {
			this.radioButtonNiveau9.setSelected(false);
			break;
		}
		case 10: {
			this.radioButtonNiveau10.setSelected(false);
		}
		}
	}

	public void setEnabledMenuOptions(boolean bool) {
		if (bool) {
			this.menuOptionsNiveau.setEnabled(true);
			this.menuOptionsHandicap.setEnabled(true);
		} else {
			this.menuOptionsNiveau.setEnabled(false);
			this.menuOptionsHandicap.setEnabled(false);
		}
	}
}
