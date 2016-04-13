import java.awt.*;
import javax.swing.*;
public class FrameHelp extends JFrame {
	HtmlPane html;
	public FrameHelp() {
		super("Aide");
		
		
		html = new HtmlPane();
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception v0) {
		}
		new JPanel();
		this.getContentPane().add((Component) (html));
		this.show();
	}
}
