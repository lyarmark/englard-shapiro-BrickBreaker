package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.inject.Singleton;

@Singleton
public class UpperPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lives;
	private JLabel score;
	private JLabel levels;

	public UpperPanel() {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(0, 3));

		score = new JLabel();
		lives = new JLabel();
		levels = new JLabel();

		lives.setForeground(Color.WHITE);
		lives.setFont(new Font(lives.getFont().getName(), Font.PLAIN, 24));
		setLivesText(3);
		score.setForeground(Color.WHITE);
		score.setFont(new Font(score.getFont().getName(), Font.PLAIN, 24));
		setScoreText(0);
		levels.setForeground(Color.WHITE);
		levels.setFont(new Font(levels.getFont().getName(), Font.PLAIN, 24));
		setLevelText(1);

		add(lives);
		add(score);
		add(levels);
	}

	public void setLivesText(int numLives) {
		StringBuilder builder = new StringBuilder();
		builder.append(" Lives: ");
		for (int i = 0; i < numLives; i++) {
			builder.append("\u25CF ");
		}
		lives.setText(builder.toString());
	}

	public void setScoreText(int scoreNum) {
		score.setText("      Score: " + scoreNum + " ");
	}

	public void setLevelText(int level) {
		levels.setText("               Level: " + level);
	}
}
