package englard_shapiro_brickBreaker.englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel {

	private Paddle paddle;
	private Ball ball;
	private Piece brick;

	public Board() {
		this.setSize(600, 600);
		paddle = new Paddle(this.getWidth(), this.getHeight());
		ball = new Ball(this.getWidth() / 2,
				(paddle.getY() - paddle.getHeight()), this.getWidth(),
				this.getHeight());
		brick = new Piece(100, 100, Color.RED);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);

		g.fillRect(paddle.getX(), paddle.getY(), paddle.getLength(),
				paddle.getHeight());
		g.setColor(Color.BLACK);
		g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(),
				ball.getDiameter());
		// create loop to set up all pieces - make 2d-array of pieces
		g.setColor(brick.getColor());
		g.fillRect(brick.getX(), brick.getY(), brick.getLength(),
				brick.getWidth());
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

		if (ball.getY() > (paddle.getY() + paddle.getHeight())) {
			// the ball died
			// decriment lives - pause time thread.sleep not working

			// send in new ball , remove this ball
			ball = new Ball(paddle.getX(), paddle.getY(), this.getWidth(),
					this.getHeight());
		} else {
			ball.move(paddle.getX(), paddle.getY(), brick);
		}
	}
}
