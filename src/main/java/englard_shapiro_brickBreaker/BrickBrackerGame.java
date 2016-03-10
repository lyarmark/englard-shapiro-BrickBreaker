package englard_shapiro_brickBreaker;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BrickBrackerGame extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private boolean isPaused;
	private ScheduledExecutorService musicExecutor;
	private MusicThread music;
	private Runnable play;

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
				//music.start();
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
							board.moveBall();
							board.repaint();
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

	private void addComponents() {
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
		isPaused = false;
		this.musicExecutor = Executors.newScheduledThreadPool(1);
		music = new MusicThread();

	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_LEFT) {
			if (!isPaused) {
				board.movePaddleLeft();
			}
		} else if (c == KeyEvent.VK_RIGHT) {
			if (!isPaused) {
				board.movePaddleRight();
			}
		} else if (c == KeyEvent.VK_P) {
			isPaused = true;
		} else if (c == KeyEvent.VK_R) {
			isPaused = false;
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

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
