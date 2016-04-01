package englard_shapiro_brickBreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BrickBreakerGame extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private Board board;
	private boolean isPaused;
	private ScheduledExecutorService musicExecutor;
	private MusicThread music;
	private Runnable play;
	private boolean left = false;
	private boolean right = false;
	private int speed = 7;
	private JLabel pauseLabel;

	private PowerUp power;
	private PowerUp[] powers;
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
		add(board, BorderLayout.CENTER);
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
				while (!board.gameOver()) {
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
				dispose();
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

	private void setProperties() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setFocusable(true);
		container.addKeyListener(this);
		pauseLabel.setForeground(Color.WHITE);
		pauseLabel.setOpaque(false);
		pauseLabel.setFont(new Font("Arial", Font.BOLD, 60));
		container.add(pauseLabel).setBounds(200, 230, 250, 100);
		pauseLabel.setVisible(false);
	}

	private void createComponents() {
		isPaused = false;
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

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
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
	}

	public PowerUp getPower() {
		return power;
	}

	private void checkPower(Paddle paddle) {
		if (board.isStartPower()) {
			if (power.checkHitPaddle(board.getPowerX(), board.getPowerY(), paddle.getPaddleLength(), paddle.getY(),
					paddle.getX())) {
				power.powerUp(this);
				power = null;
				board.setStartPower(false);
			}
			if (y >= Board.BOARD_HEIGHT) {
				power = null;
				board.setStartPower(false);
			}
			board.setPowerY(y++);
		}

		// send a power down every X points
		if ((board.getScore() % 30) == 0 && !board.isStartPower() && power == null) {
			board.setStartPower(true);
			setPower();
			x = 300;
			y = 20;
			board.setPowerX(x);
			board.setPowerY(y);
			board.setPowerUp(power);
		} else if ((board.getScore() % 40) == 0 && power != null && !board.isStartPower()) {
			power.undoPowerUp(this);
		}
	}
}
