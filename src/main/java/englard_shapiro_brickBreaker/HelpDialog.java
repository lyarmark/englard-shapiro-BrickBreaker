package englard_shapiro_brickBreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.google.inject.Singleton;

@Singleton
public class HelpDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private Font pristinaFont;

	public HelpDialog() {
		setResizable(false);
		setSize(350, 200);
		setTitle("INSTRUCTIONS");

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(new Color(176, 224, 230));

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			InputStream in = getClass().getResource("/PRISTINA.TTF").openStream();
			pristinaFont = Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(30f);
			ge.registerFont(pristinaFont);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel title = new JLabel("how to play:");
		title.setBorder(new EmptyBorder(0, 15, 0, 0));
		title.setFont(pristinaFont);
		title.setOpaque(false);
		title.setPreferredSize(new Dimension(this.getWidth(), 65));
		JTextArea text = new JTextArea("\u2022  Jump over balls of another color to empty the grid.\n"
				+ "\u2022  Score additional points per ball jumped over.\n"
				+ "\u2022  You may jump over one or more balls of the same color,\n"
				+ "     with a ball of another color.\n"
				+ "\u2022  Bonus: if you remove all balls but one, score is doubled!\n"
				+ "\u2022  Right click to clear selection.");
		text.setBorder(new EmptyBorder(0, 15, 0, 0));
		text.setEditable(false);
		text.setOpaque(false);
		container.add(title, BorderLayout.NORTH);
		container.add(text, BorderLayout.CENTER);
		setVisible(true);
	}
}
