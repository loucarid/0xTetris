import java.awt.*;
import javax.swing.*;
public class FrameHelp extends JFrame {
	public FrameHelp() {
		super("Aide");
		HtmlPane html;
		
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
