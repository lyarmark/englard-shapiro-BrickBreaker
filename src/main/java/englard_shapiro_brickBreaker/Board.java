package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Paddle paddle;
	private Ball ball;
	private ArrayList<Piece> bricks;
	public static final int BOARD_HEIGHT = 600;
	public static final int BOARD_WIDTH = 600;
	private int livesLeft;
	private BrickBrackerGame frame;
	private int score;

	public Board(BrickBrackerGame frame) {
		this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
		this.setBackground(Color.black);
		paddle = new Paddle();
		ball = new Ball(BOARD_WIDTH / 2,
				(paddle.getY() - Paddle.PADDLE_HEIGHT) - 10, paddle);
		bricks = new ArrayList<Piece>();
				
		/*int x =0;
		int y = 50;
		for(int i = 0; i <= 12; i++){
			bricks.add(new Piece(x,y, Color.RED));
			x += 50;
		}
		x = 0;
		y += 30;
		for(int i = 0; i <= 12; i++){
			bricks.add(new Piece(x,y, Color.ORANGE));
			x += 50;
		}
		x = 0;
		y += 30;
		for(int i = 0; i <= 12; i++){
			bricks.add(new Piece(x,y, Color.YELLOW));
			x += 50;
		}
		x = 0;
		y += 30;
		for(int i = 0; i <= 12; i++){
			bricks.add(new Piece(x,y, Color.GREEN));
			x += 50;
		}
		x = 0;
		y += 30;
		for(int i = 0; i <= 12; i++){
			bricks.add(new Piece(x,y, Color.BLUE));
			x += 50;
		}*/
		
		/*bricks.add(new Piece(0, 50, Color.RED));
		bricks.add(new Piece(50, 50, Color.RED));
		bricks.add(new Piece(100, 50, Color.RED));
		bricks.add(new Piece(150, 50, Color.RED));
		bricks.add(new Piece(200, 50, Color.RED));
		bricks.add(new Piece(250, 50, Color.RED));
		bricks.add(new Piece(300, 50, Color.RED));
		bricks.add(new Piece(350, 50, Color.RED));
		bricks.add(new Piece(400, 50, Color.RED));
		bricks.add(new Piece(450, 50, Color.RED));
		bricks.add(new Piece(500, 50, Color.RED));
		bricks.add(new Piece(550, 50, Color.RED));
		bricks.add(new Piece(0, 80, Color.ORANGE));
		bricks.add(new Piece(50, 80, Color.ORANGE));
		bricks.add(new Piece(100, 80, Color.ORANGE));
		bricks.add(new Piece(150, 80, Color.ORANGE));
		bricks.add(new Piece(200, 80, Color.ORANGE));
		bricks.add(new Piece(250, 80, Color.ORANGE));
		bricks.add(new Piece(300, 80, Color.ORANGE));
		bricks.add(new Piece(350, 80, Color.ORANGE));
		bricks.add(new Piece(400, 80, Color.ORANGE));
		bricks.add(new Piece(450, 80, Color.ORANGE));
		bricks.add(new Piece(500, 80, Color.ORANGE));
		bricks.add(new Piece(550, 80, Color.ORANGE));
		bricks.add(new Piece(0, 110, Color.YELLOW));
		bricks.add(new Piece(50, 110, Color.YELLOW));
		bricks.add(new Piece(100, 110, Color.YELLOW));
		bricks.add(new Piece(150, 110, Color.YELLOW));
		bricks.add(new Piece(200, 110, Color.YELLOW));
		bricks.add(new Piece(250, 110, Color.YELLOW));
		bricks.add(new Piece(300, 110, Color.YELLOW));
		bricks.add(new Piece(350, 110, Color.YELLOW));
		bricks.add(new Piece(400, 110, Color.YELLOW));
		bricks.add(new Piece(450, 110, Color.YELLOW));
		bricks.add(new Piece(500, 110, Color.YELLOW));
		bricks.add(new Piece(550, 110, Color.YELLOW));
		bricks.add(new Piece(0, 140, Color.GREEN));
		bricks.add(new Piece(50, 140, Color.GREEN));
		bricks.add(new Piece(100, 140, Color.GREEN));
		bricks.add(new Piece(150, 140, Color.GREEN));
		bricks.add(new Piece(200, 140, Color.GREEN));
		bricks.add(new Piece(250, 140, Color.GREEN));
		bricks.add(new Piece(300, 140, Color.GREEN));
		bricks.add(new Piece(350, 140, Color.GREEN));
		bricks.add(new Piece(400, 140, Color.GREEN));
		bricks.add(new Piece(450, 140, Color.GREEN));
		bricks.add(new Piece(500, 140, Color.GREEN));
		bricks.add(new Piece(550, 140, Color.GREEN));
		bricks.add(new Piece(0, 170, Color.BLUE));
		bricks.add(new Piece(50, 170, Color.BLUE));
		bricks.add(new Piece(100, 170, Color.BLUE));
		bricks.add(new Piece(150, 170, Color.BLUE));
		bricks.add(new Piece(200, 170, Color.BLUE));
		bricks.add(new Piece(250, 170, Color.BLUE));
		bricks.add(new Piece(300, 170, Color.BLUE));
		bricks.add(new Piece(350, 170, Color.BLUE));
		bricks.add(new Piece(400, 170, Color.BLUE));
		bricks.add(new Piece(450, 170, Color.BLUE));
		bricks.add(new Piece(500, 170, Color.BLUE));
		bricks.add(new Piece(550, 170, Color.BLUE));*/
		
		int x = 0;
		int y = 0;
		for(int i = 0; i <= 12; i++){
			bricks.add(new Piece(x, y, Color.RED));
			x += 50;
			y += 30;
		}
		
		x = 50;
		y = 0;
		for(int i = 0; i <= 11; i++){
			bricks.add(new Piece(x, y, Color.ORANGE));
			x += 50;
			y += 30;
		}
		
		x = 100;
		y = 0;
		for(int i = 0; i <= 10; i++){
			bricks.add(new Piece(x, y, Color.YELLOW));
			x += 50;
			y += 30;
		}
		
		x = 150;
		y = 0;
		for(int i = 0; i <= 9; i++){
			bricks.add(new Piece(x, y, Color.GREEN));
			x += 50;
			y += 30;
		}
		
		x = 200;
		y = 0;
		for(int i = 0; i <= 8; i++){
			bricks.add(new Piece(x, y, Color.BLUE));
			x += 50;
			y += 30;
		}
		
		x = 250;
		y = 0;
		for(int i = 0; i <= 7; i++){
			bricks.add(new Piece(x, y, Color.RED));
			x += 50;
			y += 30;
		}
		
		x = 300;
		y = 0;
		for(int i = 0; i <= 6; i++){
			bricks.add(new Piece(x, y, Color.ORANGE));
			x += 50;
			y += 30;
		}
		
		x = 350;
		y = 0;
		for(int i = 0; i <= 5; i++){
			bricks.add(new Piece(x, y, Color.YELLOW));
			x += 50;
			y += 30;
		}
		
		x = 400;
		y = 0;
		for(int i = 0; i <= 4; i++){
			bricks.add(new Piece(x, y, Color.GREEN));
			x += 50;
			y += 30;
		}
		x = 450;
		y = 0;
		for(int i = 0; i <= 3; i++){
			bricks.add(new Piece(x, y, Color.BLUE));
			x += 50;
			y += 30;
		}
		bricks.add(new Piece(500, 0, Color.RED));
		bricks.add(new Piece(550, 30, Color.RED));
		bricks.add(new Piece(550, 0, Color.ORANGE));
		/*bricks.add(new Piece(0, 0, Color.RED));
		bricks.add(new Piece(50, 30, Color.RED));
		bricks.add(new Piece(100, 60, Color.RED));
		bricks.add(new Piece(150, 90, Color.RED));
		bricks.add(new Piece(200, 120, Color.RED));
		bricks.add(new Piece(250, 150, Color.RED));
		bricks.add(new Piece(300, 180, Color.RED));
		bricks.add(new Piece(350, 210, Color.RED));
		bricks.add(new Piece(400, 240, Color.RED));
		bricks.add(new Piece(450, 270, Color.RED));
		bricks.add(new Piece(500, 300, Color.RED));
		bricks.add(new Piece(550, 330, Color.RED));
		
		bricks.add(new Piece(50, 0, Color.ORANGE));
		bricks.add(new Piece(100, 30, Color.ORANGE));
		bricks.add(new Piece(150, 60, Color.ORANGE));
		bricks.add(new Piece(200, 90, Color.ORANGE));
		bricks.add(new Piece(250, 120, Color.ORANGE));
		bricks.add(new Piece(300, 150, Color.ORANGE));
		bricks.add(new Piece(350, 180, Color.ORANGE));
		bricks.add(new Piece(400, 210, Color.ORANGE));
		bricks.add(new Piece(450, 240, Color.ORANGE));
		bricks.add(new Piece(500, 270, Color.ORANGE));
		bricks.add(new Piece(550, 300, Color.ORANGE));
		bricks.add(new Piece(600, 330, Color.ORANGE));*/
		
		livesLeft = 3;
		score = 0;
		this.frame = frame;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(paddle.getX(), paddle.getY(), paddle.getPaddleLength(),
				Paddle.PADDLE_HEIGHT);
		g.setColor(Color.white);
		g.fillOval(ball.getX(), ball.getY(), Ball.BALL_DIAMETER,
				Ball.BALL_DIAMETER);
		// create loop to set up all pieces - make array of pieces
		for (int i = 0; i < bricks.size(); i++) {
			Piece brick = bricks.get(i);
			g.setColor(brick.getColor());
			g.fillRect(brick.getX(), brick.getY(), Piece.BRICK_LENGTH,
					Piece.BRICK_WIDTH);
			g.setColor(Color.BLACK);
			g.drawRect(brick.getX(), brick.getY(), Piece.BRICK_LENGTH,
					Piece.BRICK_WIDTH);
		}

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
				int playAgain = JOptionPane.showConfirmDialog(null,
						"Game over! Would you like to play again?",
						"Game Over", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(
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
				ball = new Ball(paddle.getX(),
						(paddle.getY() - Paddle.PADDLE_HEIGHT) - 10, paddle);
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
			int playAgain = JOptionPane.showConfirmDialog(null,
					"You win! Would you like to play again?",
					"Congratulations!!", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass()
							.getResource("/winner.jpg")));
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

	public void addLivesLeft(){
		livesLeft++;
	}
	
	public void addScore(int score){
		this.score += score;
	}
}
