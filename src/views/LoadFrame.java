package views;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import components.Matrix;
import components.PieceEdit;

public class LoadFrame extends JPanel implements ActionListener {
	private Matrix mat;
	private PieceEdit pieceEdit;
	private static JFrame frame;
	private static JTextField nomFichier;
	private static JButton buttonLoad;
	private static JButton buttonAnnuler;

	public LoadFrame(String path, PieceEdit pieceEdit) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception v0) {
		}
		this.pieceEdit = pieceEdit;
		frame = new JFrame("Charger une pi\u00e8ce");
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				LoadFrame.access$frame().dispose();
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
		buttonLoad = new JButton("Charger");
		buttonLoad.addActionListener(this);
		pan3.add(buttonLoad);
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
		} else if (e.getActionCommand().equals("Charger")
				&& this.chargerMat(nomFichier.getText())) {
			this.pieceEdit.setMatrix(this.mat);
			frame.dispose();
		}
	}

	public boolean chargerMat(String nomFic) {
		FileInputStream fic;
		ObjectInputStream in;
		try {
			fic = new FileInputStream(nomFic);
		} catch (IOException v0) {
			return false;
		}
		try {
			in = new ObjectInputStream(fic);
		} catch (IOException v1) {
			return false;
		}
		try {
			this.mat = (Matrix) in.readObject();
		} catch (Exception v2) {
			return false;
		}
		try {
			in.close();
		} catch (IOException v3) {
			return false;
		}
		return true;
	}

	static JFrame access$frame() {
		return frame;
	}

}
