package englard_shapiro_brickBreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrickBrackerGame extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel scorePanel;
	private Board board;
	private boolean isPaused;
	private ScheduledExecutorService musicExecutor;
	private MusicThread music;
	private Runnable play;
	private boolean left = false;
	private boolean right = false;

	public BrickBrackerGame() {
		setSize(600, 600);
		setTitle("Brick Breaker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		createComponents();
		setProperties();
		addComponents();
		RunGame();

	}

	private void RunGame() {
		Runnable playSound = new Runnable() {

			public void run() {
				music = new MusicThread();
				// music.start();
			}
		};
		this.musicExecutor.scheduleAtFixedRate(playSound, 0, 22,
				TimeUnit.SECONDS);
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
							Thread.sleep(5);
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

	}

	private void createComponents() {
		board = new Board(this);
		scorePanel = new JPanel();
		scorePanel.setBackground(Color.BLACK);
		JLabel lives = new JLabel("Lives: ");
		lives.setBackground(Color.BLACK);
		lives.setForeground(Color.WHITE);
		lives.setText("Lives: \u25CF \u25CF \u25CF");
		scorePanel.add(lives);
		JLabel score = new JLabel("    Score: 0");
		score.setBackground(Color.BLACK);
		score.setForeground(Color.WHITE);
		scorePanel.add(score);
		isPaused = false;
		this.musicExecutor = Executors.newScheduledThreadPool(1);
		music = new MusicThread();

	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		left = (c == KeyEvent.VK_LEFT);
		right = (c == KeyEvent.VK_RIGHT);

		if (c == KeyEvent.VK_P) {
			isPaused = true;
		} else if (c == KeyEvent.VK_R) {
			isPaused = false;
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
		// TODO Auto-generated method stub

	}

	public void restart() {
		createComponents();
		setProperties();
		addComponents();
	}

}
