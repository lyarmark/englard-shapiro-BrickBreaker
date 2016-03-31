package englard_shapiro_brickBreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.inject.Inject;
import com.google.inject.Singleton;

//@Singleton
public class BrickBreakerGame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel scorePanel;
	// private JLabel lives, score;
	private Board board;
	private boolean isPaused;
	private ScheduledExecutorService musicExecutor;
	private MusicThread music;
	private Runnable play;
	private boolean left = false;
	private boolean right = false;
	private int speed = 2;
	private JLabel pauseLabel;
	// private JLabel levelLabel;
	// private JButton help;
	private HelpDialog helpDialog;

	private PowerUp power;
	private PowerUp[] powers;
	private boolean startPower;
	private int x;
	private int y;

	@Inject
	public BrickBreakerGame(Board board) {
		setSize(600, 600);
		setTitle("Brick Breaker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // center the frame on computer screen
		setResizable(false);
		this.board = board;
		createComponents();
		setProperties();
		addComponents();
		addPowerUps();
		RunGame();
		isPaused = false;
		setVisible(true);
	}

	private void RunGame() {
		Runnable playSound = new Runnable() {

			public void run() {
				music = new MusicThread();
				music.start();
			}
		};
		this.musicExecutor.scheduleAtFixedRate(playSound, 0, 22, TimeUnit.SECONDS);

		play = new Runnable() {

			public void run() {
				while (true) {
					try {
						if (isPaused) {
							Thread.sleep(100);
						} else {
							movePaddle();
							board.moveBall();
							board.repaint();
							board.checkWinner();
							checkPower(board.getPaddle());
							Thread.sleep(speed);
						}
					} catch (InterruptedException e) {
						System.out.println("Interrupted thread exception");
					}
				}
			}

		};
		new Thread(play).start();

	}

	private void movePaddle() {
		if (left) {
			if (!isPaused) {
				board.movePaddleLeft();
			}
		} else if (right) {
			if (!isPaused) {
				board.movePaddleRight();
			}
		}
	}

	private void addComponents() {
		add(scorePanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
	}

	private void setProperties() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setFocusable(true);
		container.addKeyListener(this);

		/*
		 * scorePanel.setBackground(Color.BLACK); scorePanel.setLayout(new
		 * GridLayout(0, 3)); lives.setBackground(Color.BLACK);
		 * lives.setForeground(Color.WHITE); lives.setFont(new
		 * Font(lives.getFont().getName(), Font.PLAIN, 24)); setLivesText(3);
		 * score.setBackground(Color.BLACK); score.setForeground(Color.WHITE);
		 * score.setFont(new Font(score.getFont().getName(), Font.PLAIN, 24));
		 * levelLabel.setForeground(Color.WHITE);
		 * levelLabel.setBackground(Color.BLACK); levelLabel.setFont(new
		 * Font(levelLabel.getFont().getName(), Font.PLAIN, 24)); // help = new
		 * JButton("HELP"); // help.setBackground(Color.BLACK); //
		 * help.setForeground(Color.WHITE); // help.setFont(new
		 * Font(score.getFont().getName(), Font.PLAIN, 24));
		 * scorePanel.add(lives, BorderLayout.WEST); scorePanel.add(score,
		 * BorderLayout.CENTER); scorePanel.add(levelLabel, BorderLayout.EAST);
		 */
		pauseLabel.setForeground(Color.WHITE);
		pauseLabel.setOpaque(false);
		pauseLabel.setFont(new Font("Arial", Font.BOLD, 60));
		container.add(pauseLabel).setBounds(200, 230, 250, 100);
		pauseLabel.setVisible(false);

		helpDialog = new HelpDialog();
		helpDialog.setLocationRelativeTo(this);
		isPaused = true;

		/*
		 * help.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { displayHelp(); } });
		 */
	}

	private void createComponents() {
		// board = new Board(this);
		scorePanel = new JPanel(new BorderLayout());
		isPaused = false;
		// score = new JLabel("        Score: 0 ");
		// lives = new JLabel();
		// levelLabel = new JLabel("               Level: 1");
		pauseLabel = new JLabel("PAUSE");
		this.musicExecutor = Executors.newScheduledThreadPool(1);
		music = new MusicThread();
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		left = (c == KeyEvent.VK_LEFT);
		right = (c == KeyEvent.VK_RIGHT);
		if (c == KeyEvent.VK_P) {
			if (isPaused) {
				pauseLabel.setVisible(false);
			} else {
				pauseLabel.setVisible(true);
			}
			isPaused = !isPaused;

		} else if (c == KeyEvent.VK_H) {
			// NEEDS HELP STILL :)
			displayHelp();
		}
	}

	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		if (left && (c == KeyEvent.VK_LEFT)) {
			left = false;
		}
		if (right && (c == KeyEvent.VK_RIGHT)) {
			right = false;
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void restart() {
		// WORK ON RESTART
		board = new Board();
		add(board, BorderLayout.CENTER);
		// setLivesText(3);
		// setScoreText();
		// setLevelText();
	}

	/*
	 * public void setLivesText(int numLives) { StringBuilder builder = new
	 * StringBuilder(); builder.append(" Lives: "); //builder.append(numLives +
	 * " "); for (int i = 1; i <= numLives; i++) { builder.append("\u25CF "); }
	 * lives.setText(builder.toString()); }
	 * 
	 * public void setScoreText() { score.setText("        Score: " +
	 * board.getScore() + " "); }
	 * 
	 * public void setLevelText(){ levelLabel.setText("               Level: " +
	 * board.getLevel()); }
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void displayHelp() {
		try {
			// HelpDialog helpDialog = new HelpDialog();
			// helpDialog.setLocationRelativeTo(this);
			helpDialog.setVisible(true);
			helpDialog.requestFocus();
			isPaused = true;
			Thread.sleep(5000);
			helpDialog.setVisible(false);
			// set focus for paddle
			board.requestFocus();
			isPaused = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addPowerUps() {
		powers = new PowerUp[1];
		powers[0] = new PowerUpSpeed();
	}

	public void setPower() {
		Random random = new Random();
		// from 0-param, including 0, excluding param
		int powerIndex = random.nextInt(1);
		this.power = powers[powerIndex];
		power.powerUp(this);
	}

	public PowerUp getPower() {
		return power;
	}

	private void checkPower(Paddle paddle) {
		if (startPower) {
			if (power.checkHitPaddle(x, y, 600, paddle.getY(), paddle.getY())) {
				power.powerUp(this);
				startPower = false;
			}
			if (y >= board.BOARD_HEIGHT) {
				startPower = false;
			}
			board.setPowerY(y++);
		}

		// send a power down every X points
		if ((board.getScore() % 30) == 0 && startPower == false) {
			startPower = true;
			setPower();
			x = 300;
			y = 20;
			board.setPowerX(x);
			board.setPowerY(y);
		}
	}
}
