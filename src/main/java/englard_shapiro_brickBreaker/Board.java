package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	private Paddle paddle;
	private Ball ball;
	private ArrayList<Piece> bricks;
	public static final int BOARD_HEIGHT = 600;
	public static final int BOARD_WIDTH = 600;
	private int livesLeft;
	private BrickBreakerGame frame;
	private int score;

	private int powerX;
	private int powerY;

	public Board(BrickBreakerGame frame) {
		this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
		this.setBackground(Color.black);
		paddle = new Paddle();
		ball = new Ball(BOARD_WIDTH / 2, (paddle.getY() - Paddle.PADDLE_HEIGHT) - 10, paddle);
		bricks = new ArrayList<Piece>();

		/*
		 * int x =0; int y = 50; for(int i = 0; i <= 12; i++){ bricks.add(new
		 * Piece(x,y, Color.RED)); x += 50; } x = 0; y += 30; for(int i = 0; i
		 * <= 12; i++){ bricks.add(new Piece(x,y, Color.ORANGE)); x += 50; } x =
		 * 0; y += 30; for(int i = 0; i <= 12; i++){ bricks.add(new Piece(x,y,
		 * Color.YELLOW)); x += 50; } x = 0; y += 30; for(int i = 0; i <= 12;
		 * i++){ bricks.add(new Piece(x,y, Color.GREEN)); x += 50; } x = 0; y +=
		 * 30; for(int i = 0; i <= 12; i++){ bricks.add(new Piece(x,y,
		 * Color.BLUE)); x += 50; }
		 */

		int x = 0;
		int y = 0;
		for (int i = 0; i <= 12; i++) {
			bricks.add(new Piece(x, y, Color.RED));
			x += 50;
			y += 30;
		}

		x = 50;
		y = 0;
		for (int i = 0; i <= 11; i++) {
			bricks.add(new Piece(x, y, Color.ORANGE));
			x += 50;
			y += 30;
		}

		x = 100;
		y = 0;
		for (int i = 0; i <= 10; i++) {
			bricks.add(new Piece(x, y, Color.YELLOW));
			x += 50;
			y += 30;
		}

		x = 150;
		y = 0;
		for (int i = 0; i <= 9; i++) {
			bricks.add(new Piece(x, y, Color.GREEN));
			x += 50;
			y += 30;
		}

		x = 200;
		y = 0;
		for (int i = 0; i <= 8; i++) {
			bricks.add(new Piece(x, y, Color.BLUE));
			x += 50;
			y += 30;
		}

		x = 250;
		y = 0;
		for (int i = 0; i <= 7; i++) {
			bricks.add(new Piece(x, y, Color.RED));
			x += 50;
			y += 30;
		}

		x = 300;
		y = 0;
		for (int i = 0; i <= 6; i++) {
			bricks.add(new Piece(x, y, Color.ORANGE));
			x += 50;
			y += 30;
		}

		x = 350;
		y = 0;
		for (int i = 0; i <= 5; i++) {
			bricks.add(new Piece(x, y, Color.YELLOW));
			x += 50;
			y += 30;
		}

		x = 400;
		y = 0;
		for (int i = 0; i <= 4; i++) {
			bricks.add(new Piece(x, y, Color.GREEN));
			x += 50;
			y += 30;
		}
		x = 450;
		y = 0;
		for (int i = 0; i <= 3; i++) {
			bricks.add(new Piece(x, y, Color.BLUE));
			x += 50;
			y += 30;
		}
		bricks.add(new Piece(500, 0, Color.RED));
		bricks.add(new Piece(550, 30, Color.RED));
		bricks.add(new Piece(550, 0, Color.ORANGE));

		livesLeft = 3;
		score = 0;
		this.frame = frame;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(paddle.getX(), paddle.getY(), paddle.getPaddleLength(), Paddle.PADDLE_HEIGHT);
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
		g.setColor(Color.pink);
		g.fillOval(powerX, powerY, 20, 20);
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
			// pause time thread.sleep not working
			if (livesLeft == 0) {
				int playAgain = JOptionPane.showConfirmDialog(null, "Game over! Would you like to play again?",
						"Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
								getClass().getResource("/gameOver.jpg")));
				if (playAgain == 0) {
					frame.restart();
				} else {
					frame.dispose();
					System.exit(0);
				}
			} else {
				// send in new ball , remove this ball
				livesLeft--;
				frame.setLivesText(livesLeft);
				ball = new Ball(paddle.getX(), (paddle.getY() - Paddle.PADDLE_HEIGHT) - 10, paddle);
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
				frame.setScoreText();
				bricks.remove(hitBrick);
			}
		}
	}

	public void checkWinner() {
		if (bricks.size() == 0) {
			int playAgain = JOptionPane.showConfirmDialog(null, "You win! Would you like to play again?",
					"Congratulations!!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
							getClass().getResource("/winner.jpg")));
			if (playAgain == 0) {
				frame.restart();
			} else {
				frame.dispose();
				System.exit(0);
			}
		}
	}

	public int getScore() {
		return score;
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
}
