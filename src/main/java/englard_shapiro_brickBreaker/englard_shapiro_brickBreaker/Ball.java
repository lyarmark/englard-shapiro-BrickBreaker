package englard_shapiro_brickBreaker.englard_shapiro_brickBreaker;

public class Ball {

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

	public Ball(int x, int y, int width, int height) {
		frameWidth = width;
		frameHeight = height;
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

	public void move(int paddleX, int paddleY) {
		hit = hitsPaddle(paddleX, paddleY);
		setMoveDirection();

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

	private void setMoveDirection() {
		// check if the ball should move up or down and then right or left
		checkMoveUp();
		checkMoveDown();
		checkMoveRight();
		checkMoveLeft();

	}

	private void checkMoveUp() {
		// set up the hit paddle method
		if (hit) {
			moveUp = true;
		}
	}

	private boolean hitsPaddle(int x, int y) {

		if (yPos == (y - 15)) { // on the line of the paddle 15 is height of
								// paddle
			if (xPos <= (x + 80)) { // 80 is length of paddle
				return true;
			}
		}
		return false;
	}

	private void checkMoveDown() {
		if (yPos - 11 <= 0) {// ten in the diameter of the ball

			moveDown = true;
			moveUp = false;

		}
	}

	private void checkMoveRight() {
		if (xPos - 11 <= 0) { // ten in the diameter of the ball
			moveRight = true;
			moveLeft = false;
		}
	}

	private void checkMoveLeft() {
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
