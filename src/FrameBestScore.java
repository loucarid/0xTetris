import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


public class FrameBestScore implements ActionListener {
	/** Frame d'affichage. */
	private static JFrame frame;
	/** Liste des noms des colonnes du tableau. */ 
	@SuppressWarnings("unused")
	private static String[] names;
	/** Tableau contenant tous les meilleurs scores. */
	private Object[][] data;
	/** Le nombre de scores enregistres. (entre 0 et 5). */
	private int nbHightScore;
	/** Le fichier d'acces du fichier des meilleurs */
	private final static String nomFichier = "best.txt";
	/** La colonne d'insertion d'un nouveau score. */
	private int colNewScore;
	/** La ligne d'insertion d'un nouveau score. */
	private int ligneNewScore;
	/** Le score a sauvegarder. */
	private int score;
	/** Le niveau atteint. */
	private int niveau;
	/** Le nombre de lignes. */
	private int ligne;
	/** La ligne d'insertion. */
	private int ligneInsertion;
/**
 * Surcharge du constructeur.
 * Utilise pour inserer un score dans la table.
 * param score Le score que l'on va inserer.
 * param niveau Le niveau atteint par le joueur
 * param ligne Le nombre de lignes remplies.
 */
public FrameBestScore() {
	/* Declaration de la fenetre. */
	frame = new JFrame("Meilleurs scores");
	frame.setSize(582, 267);
	
	/* Gestion de la croix de fermeture de la frame. */
	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			frame.dispose();
		}
	});

	/* Aucun score enregistre pour le moment. */
	nbHightScore = 0;
	
	/* Initialisation des donnees a partir d'un fichier. Remplissage du tableau. */
	final String[] names = {"Rang", "Nom", "Score", "Ligne", "Niveau"};
	data = new Object[5][5];
	initData();

	/* On teste si le score merite d'etre enregistre dans la table.*/
	ligneInsertion = testScore(score);
	
	/* Creation d'un modele pour la base. */
	TableModel dataModel = new AbstractTableModel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/* Surcharge obligatoire des methodes de la TableModel. */
		public int getColumnCount() { // Renvoie le nombre de colonnes.
			return names.length;
		}
		public int getRowCount() { // Renvoie le nombre de lignes.
			return data.length;
		}
		public Object getValueAt(int row, int col) { // Renvoie une valeur de la table. 
			return data[row][col];
		}

		/* Surcharge d'autres methodes. */
		public String getColumnName(int column) { // Renvoie le nom d'une colonne.
			return names[column];
		}
		public Class<? extends Object> getColumnClass(int c) { // Renvoie la class d'un objet d'une colonne.
			return getValueAt(0, c).getClass();
		}
		public boolean isCellEditable(int row, int col) { // Indique si on peut ecrire dans la table.
				return false; // Ici on ne peut pas...
		}
		public void setValueAt(Object aValue, int row, int column) {  // Insertion d'une valeur.
			data[row][column] = aValue;
			saveFichier(); // Sauvegarde du nouveau tableau dans un fichier.
		}
	};

	/* Creation de la table. */
	JTable tableView = new JTable(dataModel);
	tableView.editCellAt(1, 3);
	
	/* Ecrit le score en rouge. */
	TableColumn numbersColumn = tableView.getColumn("Score");
	DefaultTableCellRenderer numberColumnRenderer = new DefaultTableCellRenderer() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void setValue(Object value) {
			@SuppressWarnings("unused")
			int cellValue = (value instanceof Number) ? ((Number) value).intValue() : 0;
			setForeground(Color.red);
			setText((value == null) ? "" : value.toString());
		}
	};
	numberColumnRenderer.setHorizontalAlignment(JLabel.CENTER);
	numbersColumn.setCellRenderer(numberColumnRenderer);
	numbersColumn.sizeWidthToFit();

	/* Insertion du tout dans un panel. */
	JScrollPane scrollpane = new JScrollPane(tableView);
	scrollpane.setBorder(new BevelBorder(BevelBorder.LOWERED));
	scrollpane.setBounds(61, 65, 430, 105);

	/* Insertion du tout dans un panel. */
	JPanel panel = new JPanel();
	panel.setLayout(null);
	panel.setBackground(new java.awt.Color(189, 211, 211));
	panel.add(scrollpane);

	/* Creation d'un label. */
	JLabel label = new JLabel("MEILLEURS SCORES : ");
	label.setBounds(122, 15, 333, 27);
	label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setForeground(Color.red);
	label.setFont(new Font("dialog", 1, 18));
	panel.add(label);

	/* Creation d'un bouton. */
	JButton boutonOk = new JButton("Ok !!");
	boutonOk.setBounds(242, 190, 98, 25);
	panel.add(boutonOk);
	boutonOk.addActionListener(this);

	/* On place le tout dans la fenetre. */
	frame.getContentPane().add(panel);
	frame.setVisible(true);
}
/**
 * Surcharge du constructeur.<P>Utilise pour inserer un score dans la table.
 * @param score Le score que l'on va inserer.
 * @param niveau Le niveau atteint par le joueur
 * @param ligne Le nombre de lignes remplies.
 */
public FrameBestScore(int score, int niveau, int ligne) {
	this.score = score;
	this.niveau = niveau;
	this.ligne = ligne;

	/* Declaration de la fenetre. */
	frame = new JFrame("Meilleurs scores");
	frame.setSize(582, 267);
	
	/* Gestion de la croix de fermeture de la frame. */
	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			frame.dispose();
		}
	});

	/* Aucun score enregistre pour le moment. */
	nbHightScore = 0;
	
	/* Initialisation des donnees a partir d'un fichier. Remplissage du tableau. */
	final String[] names = {"Rang", "Nom", "Score", "Ligne", "Niveau"};
	data = new Object[5][5];
	initData();

	/* On teste si le score merite d'etre enregistre dans la table.*/
	ligneInsertion = testScore(score);
	
	/* Creation d'un modele pour la base. */
	TableModel dataModel = new AbstractTableModel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/* Surcharge obligatoire des methodes de la TableModel. */
		public int getColumnCount() { // Renvoie le nombre de colonnes.
			return names.length;
		}
		public int getRowCount() { // Renvoie le nombre de lignes.
			return data.length;
		}
		public Object getValueAt(int row, int col) { // Renvoie une valeur de la table. 
			return data[row][col];
		}

		/* Surcharge d'autres methodes. */
		public String getColumnName(int column) { // Renvoie le nom d'une colonne.
			return names[column];
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int c) { // Renvoie la class d'un objet d'une colonne.
			return getValueAt(0, c).getClass();
		}
		public boolean isCellEditable(int row, int col) { // Indique si on peut ecrire dans la table.
			// On est sur le nom de la nouvelle personne enregistree.
// Donc on peut ecrire
// Dans tous les autres cas, non.
			return (row == getLigneInsertion()) && (col == 1);
		}
		public void setValueAt(Object aValue, int row, int column) {  // Insertion d'une valeur.
			data[row][column] = aValue;
			saveFichier(); // Sauvegarde du nouveau tableau dans un fichier.
		}
	};

	/* Creation de la table. */
	JTable tableView = new JTable(dataModel);
	tableView.editCellAt(1, 3);
	
	/* Ecrit le score en rouge. */
	TableColumn numbersColumn = tableView.getColumn("Score");
	DefaultTableCellRenderer numberColumnRenderer = new DefaultTableCellRenderer() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void setValue(Object value) {
			@SuppressWarnings("unused")
			int cellValue = (value instanceof Number) ? ((Number) value).intValue() : 0;
			setForeground(Color.red);
			setText((value == null) ? "" : value.toString());
		}
	};
	numberColumnRenderer.setHorizontalAlignment(JLabel.CENTER);
	numbersColumn.setCellRenderer(numberColumnRenderer);
	numbersColumn.sizeWidthToFit();

	/* Insertion du tout dans un panel. */
	JScrollPane scrollpane = new JScrollPane(tableView);
	scrollpane.setBorder(new BevelBorder(BevelBorder.LOWERED));
	scrollpane.setBounds(61, 65, 430, 105);

	/* Insertion du tout dans un panel. */
	JPanel panel = new JPanel();
	panel.setLayout(null);
	panel.setBackground(new java.awt.Color(189, 211, 211));
	panel.add(scrollpane);

	/* Creation d'un label. */
	JLabel label = new JLabel("MEILLEURS SCORES : ");
	label.setBounds(122, 15, 333, 27);
	label.setHorizontalAlignment(SwingConstants.CENTER);
	label.setForeground(Color.red);
	label.setFont(new Font("dialog", 1, 18));
	panel.add(label);

	/* Creation d'un bouton. */
	JButton boutonOk = new JButton("Ok");
	boutonOk.setBounds(242, 190, 98, 25);
	panel.add(boutonOk);
	boutonOk.addActionListener(this);

	/* On place le tout dans la fenetre. */
	frame.getContentPane().add(panel);
	frame.setVisible(true);
}
/**
 * Ecouteur du bouton Ok.
 * @param e Evenement genere par le bouton Ok.
 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
 */
public void actionPerformed(ActionEvent e) {
	frame.dispose(); // On ferme la fenetre.
}
/**
 * Renvoie la colonne ou doit se faire l'insertion.
 * @return La colonne d'insertion. */
public int getColNewScore() {
	return colNewScore;
}
/**
 *  Renvoie la ligne ou doit se faire l'insertion.
 * @return La ligne d'insertion. */
public int getLigneInsertion() {
	return ligneInsertion;
}
/**
 * Renvoie la ligne du nouveau score.
 * @return La ligne du nouveau score. */
public int getLigneNewScore() {
	return ligneNewScore;
}
/**
 * Initialisation du tableau de la table a partir d'un fichier.
 */
public void initData() {
	FileReader fic = null;  // Le fichier de lecture.
	String sentence;         // La phrase lue dans le fichier.
	BufferedReader br = null;
	@SuppressWarnings("unused")
	String f = null;
	
	try { // Lecture du fichier.
		fic = new FileReader(nomFichier);
	} catch (IOException e) {
		try {
			FileWriter fw = new FileWriter(nomFichier);
			PrintWriter pw = new PrintWriter(fw);
			pw.print("");
			pw.close();
			fic = new FileReader(nomFichier);
		} catch (IOException error) {
			System.err.println("Erreur d'ouverture de fichier. ");
		}
	}


	
	try {
		/* On bufferise le flux de lecture. */
		br = new BufferedReader(fic);
		// On essaie de lire une premiere ligne. */
		sentence = br.readLine();
	} catch (IOException e) {
		sentence = null;
	}
	
	while (sentence != null) { // La phrase n'est pas nulle : on peut la traiter...
		StringTokenizer st = new StringTokenizer(sentence, " "); // On cherche les espaces.
		for (int i = 0; i < 5; i++) {
			data[nbHightScore][i] = st.nextToken(); // Le premier jeton.
		}
		nbHightScore++; // Il y a une ligne de plus...
		try { // On essaie de lire une autre ligne.
			sentence = br.readLine();
		} catch (IOException e) {
			sentence = null;
		}
	}
	
	try { // On ferme le fichier.
		br.close();
	} catch (IOException e) {
		System.out.println("Impossible de fermer le fichier ouvert en lecture !");
	}
}
/**
 * Sauvegarde des donnees dans un fichier.
 */
public void saveFichier() {
	FileWriter fw = null;
	boolean finFichier = false;
	PrintWriter pw = null;
	try { // On ouvre le fichier en ecriture.
		fw = new FileWriter(nomFichier);
		/* On formate le flux d'ecriture. */
		pw = new PrintWriter(fw);
	} catch (IOException e) {
		System.out.println("Ecriture impossible");
	}


	/* On remplit le fichier. */
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			if ( data[i][j] != null) {
				pw.print(data[i][j] + " ");
			} else {
				finFichier = true;
			}
		}
		if (finFichier == false) {
			pw.println("1 System 0 0 0");
		}
	}

	/* On ferme le fichier. */
	try {
		fw.close();
	} catch (IOException e) {
		System.out.println("Probleme de fermeture");
	}
}
/**
 * Teste si un score merite d'etre sauvegarder ie est dans les 5 premiers.
 * @param score Le score qu'il faut inserer.
 * @return -1 si le score n'est pas enregistre. */
public int testScore(int score) {
	for (int i = 0; i < 4; i++) { // Pour chaque ligne,
		String scoreTeste = (String) data[i][2];
	   /* On compare le score enregistre avec le score teste. */
		if ( (scoreTeste == null) || (new Integer(scoreTeste).intValue() < score)) { // Un best Score !!
			/* On decale le tableau. */
			for (int j = 4; j > i; j--) {
				for (int k = 0; k < 5; k++) {
					if ((k == 0) && (data[j-1][0] != null)) {
						int rang = new Integer((String) data[j-1][0]).intValue() + 1;
						data[j][k] = new String("" + rang);
					} else {
						data[j][k] = data[j-1][k];
					}
				}
			}
			int rang = i + 1;
			/* On initialise la ligne nouvellement creee. */
			data[i][0] = new String("" + rang);
			data[i][1] = new String("Inconnu");
			data[i][2] = new String("" + score);
			data[i][3] = new String("" + ligne);
			data[i][4] = new String("" + niveau);
			return i; // La ligne d'insertion.
		}
	}
	return -1;     // Pas d'insertion.
}
}