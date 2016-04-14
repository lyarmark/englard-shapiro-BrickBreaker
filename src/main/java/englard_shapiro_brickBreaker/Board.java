package englard_shapiro_brickBreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private Paddle paddle;
	private Ball ball;
	private ArrayList<Piece> bricks;
	public static final int BOARD_HEIGHT = 600;
	public static final int BOARD_WIDTH = 600;
	private int livesLeft;
	private int score;
	private int level;
	private UpperPanel panel;
	private int powerX;
	private int powerY;
	private PowerUp powerUp;
	private boolean gameOver;
	private boolean startPower;
	private boolean newBall;

	@Inject
	public Board(Paddle paddle, UpperPanel panel, Ball ball) {
		setSize(BOARD_WIDTH, BOARD_HEIGHT);
		setBackground(Color.black);
		setLayout(new BorderLayout());
		this.panel = panel;
		this.paddle = paddle;
		this.ball = ball;
		bricks = new ArrayList<Piece>();
		setLevelOne();
		livesLeft = 3;
		score = 0;
		gameOver = false;
		newBall = true;
		add(panel, BorderLayout.NORTH);
		setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillOval(ball.getX(), ball.getY(), Ball.BALL_DIAMETER, Ball.BALL_DIAMETER);
		// create loop to set up all pieces - make array of pieces
		for (int i = 0; i < bricks.size(); i++) {
			Piece brick = bricks.get(i);
			g.setColor(brick.getColor());
			g.fillRect(brick.getX(), brick.getY(), Piece.BRICK_LENGTH, Piece.BRICK_WIDTH);
			g.setColor(Color.BLACK);
			g.drawRect(brick.getX(), brick.getY(), Piece.BRICK_LENGTH, Piece.BRICK_WIDTH);
		}
		if (startPower == true) {
			powerUp.draw(g, powerX, powerY);
		}
		g.setColor(Color.BLUE);
		g.fillRect(paddle.getX(), paddle.getY(), paddle.getPaddleLength(), Paddle.PADDLE_HEIGHT);
	}

	public void movePaddleLeft() {
		paddle.moveLeft();
		repaint();
	}

	public void movePaddleRight() {
		paddle.moveRight();
		repaint();
	}

	public void moveBall() throws InterruptedException {

		if (ball.getY() > BOARD_HEIGHT) {
			// the ball died
			if (livesLeft == 0) {
				int playAgain = JOptionPane.showConfirmDialog(null, "Game over! Would you like to play again?",
						"Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								getClass().getResource("/gameOver.jpg")));
				if (playAgain == 0) {
					restart();
				} else {
					gameOver = true;
				}
			} else {
				// send in new ball , remove this ball
				newBall = true;
				paddle.setDefault();
				livesLeft--;
				panel.setLivesText(livesLeft);
				ball = new Ball(paddle);
			}

		} else {
			Piece hitBrick = ball.move(paddle.getX(), paddle.getY(), bricks);
			if (hitBrick != null) {
				Color brickColor = hitBrick.getColor();
				if (brickColor == Color.BLUE) {
					score += 100;
				} else if (brickColor == Color.GREEN) {
					score += 200;
				} else if (brickColor == Color.YELLOW) {
					score += 300;
				} else if (brickColor == Color.ORANGE) {
					score += 400;
				} else {
					score += 500;
				}
				panel.setScoreText(score);
				bricks.remove(hitBrick);
			}
		}
	}

	public void checkWinner() {
		if (bricks.size() == 0) {
			if (level < 3) {
				newBall = true;
				setDefault();
				nextLevel();
				panel.setLevelText(level);
			} else {
				int playAgain = JOptionPane.showConfirmDialog(null, "You win! Would you like to play again?",
						"Congratulations!!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								getClass().getResource("/winner.jpg")));
				if (playAgain == 0) {
					restart();
				} else {
					gameOver = true;
				}
			}
		}
	}

	private void nextLevel() {
		if (level == 1) {
			setLevelTwo();
		} else if (level == 2) {
			setLevelThree();
		}
	}

	private void setLevelOne() {
		level = 1;
		int x = 0;
		int y = 60;
		for (int i = 0; i < 12; i++) {
			bricks.add(new Piece(x, y, Color.RED));
			x += 50;
		}
		x = 0;
		y += 30;
		for (int i = 0; i < 12; i++) {
			bricks.add(new Piece(x, y, Color.ORANGE));
			x += 50;
		}
		x = 0;
		y += 30;
		for (int i = 0; i < 12; i++) {
			bricks.add(new Piece(x, y, Color.YELLOW));
			x += 50;
		}
		x = 0;
		y += 30;
		for (int i = 0; i < 12; i++) {
			bricks.add(new Piece(x, y, Color.GREEN));
			x += 50;
		}
		x = 0;
		y += 30;
		for (int i = 0; i < 12; i++) {
			bricks.add(new Piece(x, y, Color.BLUE));
			x += 50;
		}
		repaint();
	}

	private void setLevelTwo() {
		level = 2;
		int x = 0;
		int y = 30;
		for (int i = 0; i < 12; i++) {
			bricks.add(new Piece(x, y, Color.RED));
			x += 50;
			y += 30;
		}

		x = 50;
		y = 30;
		for (int i = 0; i < 11; i++) {
			bricks.add(new Piece(x, y, Color.ORANGE));
			x += 50;
			y += 30;
		}

		x = 100;
		y = 30;
		for (int i = 0; i < 10; i++) {
			bricks.add(new Piece(x, y, Color.YELLOW));
			x += 50;
			y += 30;
		}

		x = 150;
		y = 30;
		for (int i = 0; i < 9; i++) {
			bricks.add(new Piece(x, y, Color.GREEN));
			x += 50;
			y += 30;
		}

		x = 200;
		y = 30;
		for (int i = 0; i < 8; i++) {
			bricks.add(new Piece(x, y, Color.BLUE));
			x += 50;
			y += 30;
		}

		repaint();
	}

	private void setLevelThree() {
		level = 3;
		bricks.add(new Piece(275, 30, Color.BLUE));
		int x = 150;
		int y = 60;
		for (int i = 0; i <= 5; i++) {
			bricks.add(new Piece(x, y, Color.RED));
			x += 50;
		}
		x = 125;
		y += 30;
		for (int i = 0; i <= 6; i++) {
			bricks.add(new Piece(x, y, Color.ORANGE));
			x += 50;
		}
		x = 100;
		y += 30;
		for (int i = 0; i <= 7; i++) {
			bricks.add(new Piece(x, y, Color.YELLOW));
			x += 50;
		}
		x = 75;
		y += 30;
		for (int i = 0; i <= 8; i++) {
			bricks.add(new Piece(x, y, Color.GREEN));
			x += 50;
		}
		x = 50;
		y += 30;
		for (int i = 0; i <= 9; i++) {
			bricks.add(new Piece(x, y, Color.BLUE));
			x += 50;
		}
		x = 275;
		y += 30;
		for (int i = 0; i <= 7; i++) {
			bricks.add(new Piece(x, y, Color.RED));
			y += 30;
		}
		bricks.add(new Piece(225, y - 30, Color.RED));
		bricks.add(new Piece(225, y, Color.RED));
		bricks.add(new Piece(175, y - 30, Color.RED));
		bricks.add(new Piece(175, y - 60, Color.RED));
		repaint();
	}

	public void setPaddleMini() {
		paddle.setPaddleMini();
	}

	public void setPaddleLong() {
		paddle.setPaddleLong();
	}

	public int getPowerX() {
		return powerX;
	}

	public int getPowerY() {
		return powerY;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public void addLivesLeft() {
		livesLeft++;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public void setPowerX(int powerX) {
		this.powerX = powerX;
	}

	public void setPowerY(int powerY) {
		this.powerY = powerY;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
	}

	public void restart() {
		bricks.clear();
		setLevelOne();
		score = 0;
		newBall = true;
		setDefault();
		livesLeft = 3;
		panel.setLevelText(1);
		panel.setLivesText(livesLeft);
		panel.setScoreText(score);
	}

	public boolean gameOver() {
		return gameOver;
	}

	public boolean isStartPower() {
		return startPower;
	}

	public void setStartPower(boolean startPower) {
		this.startPower = startPower;
	}

	public boolean newBall() {
		return newBall;
	}

	public void setNewBall() {
		newBall = false;
	}

	public void setDefault() {
		setStartPower(false);
		setPowerUp(null);
		paddle.setDefault();
		ball.setDefault();
		repaint();
	}
}