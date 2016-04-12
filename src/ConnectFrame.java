import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import javax.swing.*;

public class ConnectFrame extends JPanel implements ActionListener, Runnable {
	private Thread thread;
	private Socket socket;
	private Game jeu;
	private static JFrame frame;
	private static JRadioButton buttonServeur;
	private static JRadioButton buttonClient;
	private static JTextField addClient;
	private static JLabel labelClient;
	private static JButton buttonConnecter;

	public ConnectFrame(Game jeu, String defaultPath) {
		this.jeu = jeu;
		this.thread = null;
		this.socket = null;
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception v0) {
		}
		frame = new JFrame("Se connecter \u00e0 un autre joueur");
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				ConnectFrame.access$frame().dispose();
			}
		});
		this.setLayout(new BorderLayout());
		JPanel pan1 = new JPanel();
		pan1.setLayout(new BorderLayout());
		JPanel subPan1 = new JPanel();
		buttonServeur = new JRadioButton(
				" Attendre un appel de la part du joueur n\u00b02                               ");
		buttonServeur.setActionCommand("Serveur");
		buttonServeur.addActionListener(this);
		buttonServeur.setSelected(true);
		subPan1.add(buttonServeur);
		pan1.add(subPan1, "North");
		JPanel subPan2 = new JPanel();
		buttonClient = new JRadioButton(" Appeler le joueur n\u00b02");
		buttonClient.setActionCommand("Client");
		buttonClient.addActionListener(this);
		buttonClient.setSelected(false);
		labelClient = new JLabel("\u00e0 l'adresse:");
		labelClient.setEnabled(false);
		addClient = new JTextField(defaultPath, 12);
		addClient.setEnabled(false);
		subPan2.add(buttonClient);
		subPan2.add(labelClient);
		subPan2.add(addClient);
		pan1.add(subPan2, "Center");
		this.add(pan1, "West");
		JPanel pan3 = new JPanel();
		buttonConnecter = new JButton("Ecouter...");
		buttonConnecter.setActionCommand("Try");
		buttonConnecter.addActionListener(this);
		pan3.add(buttonConnecter);
		JButton buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(this);
		pan3.add(buttonAnnuler);
		this.add(pan3, "South");
		frame.getContentPane().add("Center", (Component) (this));
		frame.pack();
		frame.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Annuler")) {
			frame.dispose();
			if (this.thread != null) {
				this.thread.stop();
				this.thread = null;
			}
		} else if (action.equals("Serveur")) {
			buttonClient.setSelected(false);
			buttonServeur.setSelected(true);
			labelClient.setEnabled(false);
			labelClient.repaint();
			addClient.setEnabled(false);
			buttonConnecter.setText("Ecouter...");
		} else if (action.equals("Client")) {
			buttonClient.setSelected(true);
			buttonServeur.setSelected(false);
			labelClient.setEnabled(true);
			labelClient.repaint();
			addClient.setEnabled(true);
			buttonConnecter.setText("Appeler...");
		} else if (action.equals("Try")) {
			if (buttonConnecter.getText().equals("Ecouter...")) {
				this.thread = new Thread(this);
				this.thread.start();
			} else {
				this.setSocket(Client.getSocket(addClient.getText()));
			}
		}
	}

	@Override
	public void run() {
		new ServeurFrame(this);
		do {
			try {
				Thread.sleep(1000);
				continue;
			} catch (InterruptedException v0) {
				continue;
			}
		} while (true);
	}

	public void setSocket(Socket socket) {
		if (socket == null) {
			new JFrameMsg("Demande de connexion",
					"Impossible de se connecter au joueur n\u00b02");
			if (this.thread != null) {
				this.thread.stop();
				this.thread = null;
			}
		} else {
			this.socket = socket;
			this.jeu.setSocket(socket, false);
			frame.dispose();
			if (this.thread != null) {
				this.thread.stop();
				this.thread = null;
			}
			if (socket != null) {
				new JFrameMsg("Demande de connexion",
						"La connexion a \u00e9t\u00e9 r\u00e9alis\u00e9e... Bonne partie !");
			}
		}
	}

	static JFrame access$frame() {
		return frame;
	}

}
