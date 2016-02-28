package englard_shapiro_brickBreaker.englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel {

	private Paddle paddle;
	private Ball ball;

	public Board() {
		this.setSize(600, 600);
		paddle = new Paddle(this.getWidth(), this.getHeight());
		ball = new Ball(this.getWidth()/2, (paddle.getY() -paddle.getHeight()),this.getWidth(), this.getHeight());
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);

		g.fillRect(paddle.getX(), paddle.getY(), paddle.getLength(),
				paddle.getHeight());
		g.setColor(Color.BLACK);
		g.fillOval(ball.getX(), ball.getY() , ball.getDiameter(), ball.getDiameter());

	}

	public void movePaddleLeft() {
		paddle.moveLeft();
		repaint();
	}

	public void movePaddleRight() {
		paddle.moveRight();
		repaint();
	}
	
	public void moveBall(){
		ball.move(paddle.getX(),paddle.getY());
	}

}
