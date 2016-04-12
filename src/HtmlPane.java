import java.awt.*;
import java.io.*;
import java.net.*;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

public class HtmlPane extends JScrollPane implements HyperlinkListener {
	JEditorPane html;

	public HtmlPane() {
		try {
			File f = new File("z:/www/Internet/buffa/tetris/index.html");
			String s = f.getAbsolutePath();
			s = "file:" + s;
			new URL(s);
			this.html = new JEditorPane(s);
			this.html.setEditable(false);
			this.html.addHyperlinkListener(this);
			this.html.setBackground(new Color(189, 211, 211));
			JViewport vp = this.getViewport();
			vp.add(this.html);
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			this.linkActivated(e.getURL());
		}
	}

	protected void linkActivated(URL u) {
		Cursor c = this.html.getCursor();
		Cursor waitCursor = Cursor.getPredefinedCursor(3);
		this.html.setCursor(waitCursor);
		SwingUtilities.invokeLater(new PageLoader(this, u, c));
	}

	class PageLoader implements Runnable {
		HtmlPane pane;
		URL url;
		Cursor cursor;
		public PageLoader(HtmlPane htmlPane, URL u, Cursor c) {
			this.pane = htmlPane;
			this.url = u;
			this.cursor = c;
		}

		/* synthetic ;


		/* syntheticPageLoader(HtmlPane this$0, URL u, Cursor c) {

		}
		*/
		@Override
		public void run() {
			if (this.url == null) {
				this.pane.html.setCursor(this.cursor);
				Container parent = this.pane.html.getParent();
				parent.repaint();
			} else {
				Document doc = this.pane.html.getDocument();
				try {
					try {
						this.pane.html.setPage(this.url);
					} catch (IOException v0) {
						this.pane.html.setDocument(doc);
						this.pane.getToolkit().beep();
					}
				} catch (Throwable var3_3) {
					Object var2_4 = null;
					this.url = null;
					SwingUtilities.invokeLater(this);
					throw var3_3;
				}
				Object var2_5 = null;
				this.url = null;
				SwingUtilities.invokeLater(this);
			}
		}
	}


}
