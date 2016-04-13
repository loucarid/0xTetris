package components;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.net.*;
import javax.swing.*;

public class About extends JDialog implements ActionListener {
	private Image[] image;
	private MediaTracker MT;
	private JButton ivjJButton1;
	private JPanel ivjJDialogContentPane;
	private JLabel ivjJLabel1;
	private JLabel ivjJLabel2;
	private JLabel ivjJLabel21;
	private JLabel ivjJLabel3;
	private JLabel ivjJLabel4;
	private JLabel ivjJLabel5;
	private JLabel ivjJLabel6;
	private JLabel ivjJLabel7;
	private JLabel ivjJLabel8;
	private JLabel ivjJLabel9;
	static Class class$java$awt$Window;

	public About() {
		this.ivjJButton1 = null;
		this.ivjJDialogContentPane = null;
		this.ivjJLabel1 = null;
		this.ivjJLabel2 = null;
		this.ivjJLabel21 = null;
		this.ivjJLabel3 = null;
		this.ivjJLabel4 = null;
		this.ivjJLabel5 = null;
		this.ivjJLabel6 = null;
		this.ivjJLabel7 = null;
		this.ivjJLabel8 = null;
		this.ivjJLabel9 = null;
		this.image = new Image[2];
		this.MT = new MediaTracker((Component) (this));
		int i = 0;
		while (i < 2) {
			try {
				this.image[0] = Toolkit.getDefaultToolkit().getImage(
						new URL("http://flylib.com/books/4/126/1/html/2/images/fig126_01.jpg"));
				this.image[1] = Toolkit.getDefaultToolkit().getImage(
						new URL("http://flylib.com/books/4/126/1/html/2/images/fig126_01.jpg"));
			} catch (MalformedURLException e) {
				System.err.println("Probleme de saisie d'images.");
				e.printStackTrace();
			}
			++i;
		}
		this.MT.addImage(this.image[0], 0);
		this.MT.addImage(this.image[1], 1);
		try {
			this.MT.waitForAll();
		} catch (InterruptedException v0) {
			System.err
					.println("Toutes les images n'ont pas \u00e9t\u00e9 charg\u00e9es. ");
		}
		this.initialize();
		this.setModal(true);
		try {
			Class[] arrclass;
			Class class_;
			Class aCloserClass;
			aCloserClass = Class.forName("com.ibm.uvm.abt.edit.WindowCloser");
			arrclass = new Class[1];
			class_ = class$java$awt$Window;
			if (class_ == null) {
				try {
					class_ = About.class$java$awt$Window = Class
							.forName("java.awt.Window");
				} catch (ClassNotFoundException v3) {
					throw new NoClassDefFoundError(v3.getMessage());
				}
			}
			arrclass[0] = class_;
			Class[] parmTypes = arrclass;
			Object[] parms = new Object[] { this };
			Constructor aCtor = aCloserClass.getConstructor(parmTypes);
			aCtor.newInstance(parms);
		} catch (Throwable v4) {
		}
		this.setVisible(true);
	}

	public About(Frame owner) {
		super(owner);
		this.ivjJButton1 = null;
		this.ivjJDialogContentPane = null;
		this.ivjJLabel1 = null;
		this.ivjJLabel2 = null;
		this.ivjJLabel21 = null;
		this.ivjJLabel3 = null;
		this.ivjJLabel4 = null;
		this.ivjJLabel5 = null;
		this.ivjJLabel6 = null;
		this.ivjJLabel7 = null;
		this.ivjJLabel8 = null;
		this.ivjJLabel9 = null;
	}

	public About(Frame owner, String title) {
		super(owner, title);
		this.ivjJButton1 = null;
		this.ivjJDialogContentPane = null;
		this.ivjJLabel1 = null;
		this.ivjJLabel2 = null;
		this.ivjJLabel21 = null;
		this.ivjJLabel3 = null;
		this.ivjJLabel4 = null;
		this.ivjJLabel5 = null;
		this.ivjJLabel6 = null;
		this.ivjJLabel7 = null;
		this.ivjJLabel8 = null;
		this.ivjJLabel9 = null;
	}

	public About(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		this.ivjJButton1 = null;
		this.ivjJDialogContentPane = null;
		this.ivjJLabel1 = null;
		this.ivjJLabel2 = null;
		this.ivjJLabel21 = null;
		this.ivjJLabel3 = null;
		this.ivjJLabel4 = null;
		this.ivjJLabel5 = null;
		this.ivjJLabel6 = null;
		this.ivjJLabel7 = null;
		this.ivjJLabel8 = null;
		this.ivjJLabel9 = null;
	}

	public About(Frame owner, boolean modal) {
		super(owner, modal);
		this.ivjJButton1 = null;
		this.ivjJDialogContentPane = null;
		this.ivjJLabel1 = null;
		this.ivjJLabel2 = null;
		this.ivjJLabel21 = null;
		this.ivjJLabel3 = null;
		this.ivjJLabel4 = null;
		this.ivjJLabel5 = null;
		this.ivjJLabel6 = null;
		this.ivjJLabel7 = null;
		this.ivjJLabel8 = null;
		this.ivjJLabel9 = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.getJButton1()) {
			this.connEtoC1(e);
		}
	}

	private void connEtoC1(ActionEvent arg1) {
		try {
			this.jButton1_ActionPerformed(arg1);
		} catch (Throwable ivjExc) {
			this.handleException(ivjExc);
		}
	}

	private JButton getJButton1() {
		if (this.ivjJButton1 == null) {
			try {
				this.ivjJButton1 = new JButton();
				this.ivjJButton1.setName("JButton1");
				this.ivjJButton1.setText("Ok !!");
				this.ivjJButton1.setBounds(203, 267, 122, 32);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJButton1;
	}

	private JPanel getJDialogContentPane() {
		if (this.ivjJDialogContentPane == null) {
			try {
				this.ivjJDialogContentPane = new JPanel();
				this.ivjJDialogContentPane.setName("JDialogContentPane");
				this.ivjJDialogContentPane.setLayout(null);
				this.ivjJDialogContentPane.setBackground(new Color(189, 211,
						211));
				this.getJDialogContentPane().add(
						this.getJButton1(),
						this.getJButton1().getName());
				this.getJDialogContentPane().add(this.getJLabel1(),
						this.getJLabel1().getName());
				this.getJDialogContentPane().add(this.getJLabel2(),
						this.getJLabel2().getName());
				this.getJDialogContentPane().add(
						this.getJLabel21(),
						this.getJLabel21().getName());
				this.getJDialogContentPane().add(this.getJLabel3(),
						this.getJLabel3().getName());
				this.getJDialogContentPane().add(this.getJLabel4(),
						this.getJLabel4().getName());
				this.getJDialogContentPane().add(this.getJLabel5(),
						this.getJLabel5().getName());
				this.getJDialogContentPane().add(this.getJLabel6(),
						this.getJLabel6().getName());
				this.getJDialogContentPane().add(this.getJLabel7(),
						this.getJLabel7().getName());
				this.getJDialogContentPane().add(this.getJLabel8(),
						this.getJLabel8().getName());
				this.getJDialogContentPane().add(this.getJLabel9(),
						this.getJLabel9().getName());
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJDialogContentPane;
	}

	private JLabel getJLabel1() {
		if (this.ivjJLabel1 == null) {
			try {
				this.ivjJLabel1 = new JLabel();
				this.ivjJLabel1.setName("JLabel1");
				this.ivjJLabel1
						.setText("TETRIS 1.0 - PROJET JAVA - DESS ISI - ESSI");
				this.ivjJLabel1.setForeground(Color.red);
				this.ivjJLabel1.setHorizontalTextPosition(0);
				this.ivjJLabel1.setFont(new Font("dialog", 1, 14));
				this.ivjJLabel1.setBounds(75, 18, 393, 15);
				this.ivjJLabel1.setHorizontalAlignment(0);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel1;
	}

	private JLabel getJLabel2() {
		if (this.ivjJLabel2 == null) {
			try {
				this.ivjJLabel2 = new JLabel();
				if (this.MT.statusID(0, false) == 8) {
					this.ivjJLabel2
							.setIcon(new ImageIcon(this.image[0]));
				}
				this.ivjJLabel2.setBounds(339, 129, 95, 105);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel2;
	}

	private JLabel getJLabel21() {
		if (this.ivjJLabel21 == null) {
			try {
				this.ivjJLabel21 = new JLabel();
				if (this.MT.statusID(1, false) == 8) {
					this.ivjJLabel21
							.setIcon(new ImageIcon(this.image[1]));
				}
				this.ivjJLabel21.setBounds(100, 129, 90, 105);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel21;
	}

	private JLabel getJLabel3() {
		if (this.ivjJLabel3 == null) {
			try {
				this.ivjJLabel3 = new JLabel();
				this.ivjJLabel3.setText("Fran\u00e7ois Alamargot");
				this.ivjJLabel3.setBounds(93, 234, 117, 15);
				this.ivjJLabel3.setForeground(Color.black);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel3;
	}

	private JLabel getJLabel4() {
		if (this.ivjJLabel4 == null) {
			try {
				this.ivjJLabel4 = new JLabel();
				this.ivjJLabel4.setText("Arnaud Buiguez");
				this.ivjJLabel4.setBounds(330, 235, 111, 15);
				this.ivjJLabel4.setForeground(Color.black);
				this.ivjJLabel4.setHorizontalAlignment(0);
				this.ivjJLabel4.setHorizontalTextPosition(0);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel4;
	}

	private JLabel getJLabel5() {
		if (this.ivjJLabel5 == null) {
			try {
				this.ivjJLabel5 = new JLabel();
				this.ivjJLabel5.setName("JLabel5");
				this.ivjJLabel5.setText("Conception");
				this.ivjJLabel5.setBounds(201, 151, 138, 17);
				this.ivjJLabel5.setForeground(Color.black);
				this.ivjJLabel5.setHorizontalAlignment(0);
				this.ivjJLabel5.setHorizontalTextPosition(0);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel5;
	}

	private JLabel getJLabel6() {
		if (this.ivjJLabel6 == null) {
			try {
				this.ivjJLabel6 = new JLabel();
				this.ivjJLabel6.setName("JLabel6");
				this.ivjJLabel6.setText("&");
				this.ivjJLabel6.setBounds(228, 173, 81, 15);
				this.ivjJLabel6.setForeground(Color.black);
				this.ivjJLabel6.setHorizontalAlignment(0);
				this.ivjJLabel6.setHorizontalTextPosition(0);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel6;
	}

	private JLabel getJLabel7() {
		if (this.ivjJLabel7 == null) {
			try {
				this.ivjJLabel7 = new JLabel();
				this.ivjJLabel7.setName("JLabel7");
				this.ivjJLabel7.setText("Programmation");
				this.ivjJLabel7.setBounds(214, 194, 117, 15);
				this.ivjJLabel7.setForeground(Color.black);
				this.ivjJLabel7.setHorizontalAlignment(0);
				this.ivjJLabel7.setHorizontalTextPosition(0);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel7;
	}

	private JLabel getJLabel8() {
		if (this.ivjJLabel8 == null) {
			try {
				this.ivjJLabel8 = new JLabel();
				this.ivjJLabel8.setName("JLabel8");
				this.ivjJLabel8.setText("Si Tetris, t'es pas content....");
				this.ivjJLabel8.setForeground(Color.blue);
				this.ivjJLabel8.setHorizontalTextPosition(0);
				this.ivjJLabel8.setFont(new Font("dialog", 2, 12));
				this.ivjJLabel8.setBounds(92, 57, 363, 15);
				this.ivjJLabel8.setHorizontalAlignment(0);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel8;
	}

	private JLabel getJLabel9() {
		if (this.ivjJLabel9 == null) {
			try {
				this.ivjJLabel9 = new JLabel();
				this.ivjJLabel9.setName("JLabel9");
				this.ivjJLabel9.setText("Si Java bien, c'est Java Bean...");
				this.ivjJLabel9.setForeground(Color.blue);
				this.ivjJLabel9.setHorizontalTextPosition(0);
				this.ivjJLabel9.setFont(new Font("dialog", 2, 12));
				this.ivjJLabel9.setBounds(112, 81, 314, 15);
				this.ivjJLabel9.setHorizontalAlignment(0);
			} catch (Throwable ivjExc) {
				this.handleException(ivjExc);
			}
		}
		return this.ivjJLabel9;
	}

	private void handleException(Throwable exception) {
	}

	private void initConnections() {
		this.getJButton1().addActionListener(this);
	}

	private void initialize() {
		this.setName("Apropos");
		this.setDefaultCloseOperation(2);
		this.setSize(528, 342);
		this.setContentPane(this.getJDialogContentPane());
		this.initConnections();
	}

	public void jButton1_ActionPerformed(ActionEvent actionEvent) {
		this.dispose();
	}


}
