package views;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import components.Matrix;

public class SaveFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 203650247124306412L;
	private Matrix mat;
	private static JFrame frame;
	private static JTextField nomFichier;
	private static JButton buttonSave;
	private static JButton buttonAnnuler;

	public SaveFrame(String path, Matrix mat) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception v0) {
		}
		this.mat = mat;
		frame = new JFrame("Sauver une pi\u00e8ce");
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				SaveFrame.access$frame().dispose();
			}
		});
		this.setLayout(new BorderLayout());
		JPanel statPanel = new JPanel();
		statPanel.setLayout(new BorderLayout());
		JPanel pan1 = new JPanel();
		pan1.add(new JLabel("Nom du fichier: "));
		nomFichier = new JTextField(path, 20);
		pan1.add(nomFichier);
		statPanel.add(pan1, "Center");
		JPanel pan2 = new JPanel();
		statPanel.add(pan2, "East");
		this.add(statPanel, "East");
		JPanel pan3 = new JPanel();
		buttonSave = new JButton("Sauver");
		buttonSave.addActionListener(this);
		pan3.add(buttonSave);
		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(this);
		pan3.add(buttonAnnuler);
		this.add(pan3, "South");
		frame.getContentPane().add("Center", (Component) (this));
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Annuler")) {
			frame.dispose();
		} else if (e.getActionCommand().equals("Sauver")) {
			this.sauverMat(nomFichier.getText(), this.mat);
			if (this.mat != null) {
				frame.dispose();
			}
		}
	}

	public void sauverMat(String nomFic, Matrix mat) {
		FileOutputStream fic;
		ObjectOutputStream out;
		try {
			fic = new FileOutputStream(nomFic);
		} catch (IOException v0) {
			System.out
					.println("Impossible d'ouvrir le fichier en \u00e9criture "
							+ nomFic + " !");
			return;
		}
		try {
			out = new ObjectOutputStream(fic);
		} catch (IOException v1) {
			System.out.println("Impossible d'ouvrir le flux !");
			return;
		}
		try {
			out.writeObject(mat);
		} catch (IOException v2) {
			System.out.println("Impossible d'ecrire dans le flux !");
		}
		try {
			out.close();
		} catch (IOException v3) {
			System.out.println("Impossible de fermer le flux !");
			return;
		}
	}

	static JFrame access$frame() {
		return frame;
	}

}
