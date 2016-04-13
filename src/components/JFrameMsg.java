package components;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Frame d'affichage de messages.
 */
 
public class JFrameMsg extends JComponent implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Frame courante d'affichage du message. */
	private JFrame frame;
/**
 * Constructeur de la frame d'affichage du message.
 * @param title Titre de la fenetre d'affichage
 * @param msg Chaine de caracteres a afficher dans la fenetre.
 */
 
@SuppressWarnings("deprecation")
public JFrameMsg(String title, String msg) {
	super();
	// Traitement du Look & Feel : L&F Java.
	try {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (Exception error) {
	}

	// Creation d'une nouvelle frame.
	frame = new JFrame(title);

	// Gestion de la fermeture de cette frame.
	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			frame.dispose(); // On ferme la fenetre
		}
	});
	setLayout(new BorderLayout());
	JPanel statPanel = new JPanel();
	statPanel.setLayout(new BorderLayout());
	/* Affichage du message sous forme de label: */
	JPanel pan1 = new JPanel();
	JLabel label = new JLabel(msg);
	pan1.add(label);
	/* Bouton 'OK' de fermeture de la fenetre: */
	JPanel pan2 = new JPanel();
	JButton buttonOK = new JButton("OK");
	buttonOK.addActionListener(this);
	pan2.add(buttonOK);
	statPanel.add(pan1,"North");
	statPanel.add(pan2,"South");
	add(statPanel);
	frame.getContentPane().add("Center", this);
	frame.pack();
	frame.show();
}
/**
 * Action en cas de clic sue le bouton OK.
 * @param e Evenement appelle (non utilise car unique -> appui sur le bouton 'OK')
 * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
 */
 
public void actionPerformed(ActionEvent e) {
	/* La seule action possible est le clique su le bouton 'OK' */
	/* qui ferme la frame: */
	frame.dispose();
}
/**
 * Permet a la methode appelante de fermer elle-meme la frame (sans intervention
 * de l'utilisateur).
 */
 
public void dispose() {
	frame.dispose();
}
}