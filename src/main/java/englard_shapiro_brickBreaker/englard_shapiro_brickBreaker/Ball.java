package englard_shapiro_brickBreaker.englard_shapiro_brickBreaker;


public class Ball {

	private int xPos;
	private int yPos;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	public static int BALL_DIAMETER = 10;


	public Ball(int x, int y) {
		xPos = x;
		yPos = y;
		moveUp = true;
		moveRight = true;
		moveDown = false;
		moveLeft = false;
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
		
		checkHitWall();
		checkHitPaddle(x, y);
		checkBrickCollision(brick);
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
		if ((xPos == (brick.getX()+ brick.getLength()))
				&& (yPos <= (brick.getY() + brick.getWidth()))
				&& (yPos >= brick.getY())) {
			switchRightandLeft();
		}
	}

	private void checkBrickLeft(Piece brick) {
		if ((xPos == brick.getX())
				&& (yPos <= (brick.getY() + brick.getWidth()))
				&& (yPos >= brick.getY())) {
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
		if ((yPos == brick.getY() + brick.getWidth())
				&& (xPos <= brick.getX() + brick.getLength())
				&& (xPos >= brick.getX())) {
			switchUpandDown();
		}

	}

	private void checkBrickTop(Piece brick) {
		if ((yPos == brick.getY())
				&& (xPos <= brick.getX() + brick.getLength())
				&& (xPos >= brick.getX())) {
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
		checkTopPaddle(x, y);
		checkRightSidePaddle(x, y);
		checkLeftSidePaddle(x, y);

	}

	private void checkLeftSidePaddle(int x, int y) {
		if (xPos == x && yPos >= y && yPos <=(y + Paddle.PADDLE_HEIGHT)) {
			switchUpandDown();
			switchRightandLeft();
		}
	}

	private void checkRightSidePaddle(int x, int y) {
		if (xPos == (x + Paddle.PADDLE_LENGTH) && yPos >= y
				&& yPos <= (y + Paddle.PADDLE_HEIGHT)) {

			switchUpandDown();
			switchRightandLeft();
		}
	}

	private void checkTopPaddle(int x, int y) {
		if (yPos == (y - Paddle.PADDLE_HEIGHT)) {
			if (xPos <= (x + Paddle.PADDLE_LENGTH) && xPos >= x) {
				switchUpandDown();
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
		if (xPos + 11 >= Board.BOARD_WIDTH) { // ten in the diameter of the ball
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