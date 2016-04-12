
import java.awt.*;
import java.awt.event.*;
import java.net.*;

import javax.swing.*;

public class ServeurFrame extends JPanel implements ActionListener {
	private static JFrame frame;
	private ConnectFrame connectFrame;
	private Thread thread;

	public ServeurFrame(ConnectFrame connectFrame) {
		this.connectFrame = connectFrame;
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception v0) {
		}
		frame = new JFrame("Attendre un appel");
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				ServeurFrame.access$frame().dispose();
			}
		});
		this.setLayout(new BorderLayout());
		JPanel pan1 = new JPanel();
		pan1.add(new JLabel("Attente d'une connexion en cours..."));
		this.add(pan1, "Center");
		JPanel pan2 = new JPanel();
		JButton buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(this);
		pan2.add(buttonAnnuler);
		this.add(pan2, "South");
		frame.getContentPane().add("Center", (Component) (this));
		frame.pack();
		frame.show();
		new Serveur(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Annuler") && this.thread != null) {
			frame.dispose();
			this.thread.stop();
			this.connectFrame.setSocket(null);
		}
	}

	public void setSocket(Socket socket) {
		this.connectFrame.setSocket(socket);
		frame.dispose();
		this.thread.stop();
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	static JFrame access$frame() {
		return frame;
	}

}
