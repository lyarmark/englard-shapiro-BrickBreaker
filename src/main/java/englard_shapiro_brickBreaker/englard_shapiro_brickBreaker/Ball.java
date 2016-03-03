package englard_shapiro_brickBreaker.englard_shapiro_brickBreaker;

import javax.swing.JComponent;

public class Ball extends JComponent{

	private Board board;
	private int frameWidth;
	private int frameHeight;
	private int diameter;
	private int xPos;
	private int yPos;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	private boolean hit;

	public Ball(Board board, int x, int y, int frameWidth, int frameHeight) {
		this.board = board;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		diameter = 10;
		xPos = x;
		yPos = y;
		moveUp = true;
		moveRight = true;
		moveDown = false;
		moveLeft = false;
		hit = false;
	}

	public int getDiameter() {
		return diameter;
	}

	public void move(int paddleX, int paddleY, Piece brick) {
		setMoveDirection(paddleX, paddleY, brick);

		if (moveLeft) {
			xPos -= 1;

		} else if (moveRight) {
			xPos += 1;

		}
		if (moveUp) {
			yPos -= 1;
		} else if (moveDown) {
			yPos += 1;
		}

	}

	private void setMoveDirection(int x, int y, Piece brick) {
		// check if the ball should move up or down and then right or left
		/*
		 * put all checking in a thread
		 */
		checkHitWall();
		/*int nextX = xPos, nextY = yPos;
		if (moveLeft) {
			nextX -= 1;
		} else if (moveRight) {
			nextX += 1;
		}
		if (moveUp) {
			nextY -= 1;
		} else if (moveDown) {
			nextY += 1;
		}
		Component component = board.whichComponent(nextX, nextY);
		//Component component = new Paddle(0, 0);
		if (component instanceof Paddle) {*/
			checkHitPaddle(x, y);
		/*} else if (component instanceof Piece) {
			// send in list of brick and have a loop to check each brick 
			checkBrickCollision(brick);
		}*/

	}

	private void checkHitWall() {
		checkTopWall();
		checkLeftWall();
		checkRightWall();
	}

	private void checkBrickCollision(Piece brick) {
		checkBrickRight(brick);
		checkBrickLeft(brick);
		checkBrickTop(brick);
		checkBrickBottom(brick);

	}

	private void checkBrickRight(Piece brick) {
		if ((yPos == (brick.getY() + brick.getLength())
				&& (xPos <= (brick.getX() + brick.getWidth())) && (xPos >= brick
				.getX()))) {
			switchRightandLeft();
		}
	}

	private void checkBrickLeft(Piece brick) {
		if ((yPos == brick.getY())
				&& (xPos <= (brick.getX() + brick.getWidth()))
				&& (xPos >= brick.getX())) {
			switchRightandLeft();
		}
	}

	private void switchRightandLeft() {
		if (moveRight) {
			moveRight = false;
			moveLeft = true;
		} else {
			moveRight = true;
			moveLeft = false;
		}

	}

	private void checkBrickBottom(Piece brick) {
		if ((xPos == brick.getX() + brick.getWidth())
				&& (yPos <= brick.getY() + brick.getLength())
				&& (yPos >= brick.getY())) {
			switchUpandDown();
		}

	}

	private void checkBrickTop(Piece brick) {
		if ((xPos == brick.getX())
				&& (yPos <= brick.getY() + brick.getLength())
				&& (yPos >= brick.getY())) {
			switchUpandDown();
		}

	}

	private void switchUpandDown() {
		if (moveUp) {
			moveUp = false;
			moveDown = true;
		} else {
			moveUp = true;
			moveDown = false;
		}

	}

	private void checkHitPaddle(int x, int y) {

		if (yPos == (y - 15)) { // on the line of the paddle 15 is height of
								// paddle
			if (xPos <= (x + 80) && xPos >= x) { // 80 is length of paddle
				// if it hit the paddle ball move up
				moveUp = true;
				moveDown = false;
			}
		}

	}

	private void checkTopWall() {
		if (yPos - 11 <= 0) {// ten in the diameter of the ball

			moveDown = true;
			moveUp = false;

		}
	}

	private void checkLeftWall() {
		if (xPos - 11 <= 0) { // ten in the diameter of the ball
			moveRight = true;
			moveLeft = false;
		}
	}

	private void checkRightWall() {
		if (xPos + 11 >= frameWidth) { // ten in the diameter of the ball
			moveLeft = true;
			moveRight = false;
		}
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
}
