package englard_shapiro_brickBreaker.englard_shapiro_brickBreaker;

import java.util.ArrayList;

public class Ball {

	private int xPos;
	private int yPos;
	private boolean brickHit;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	public static final int BALL_DIAMETER = 10;

	public Ball(int x, int y) {
		xPos = x;
		yPos = y;
		moveUp = true;
		moveRight = true;
		moveDown = false;
		moveLeft = false;
		brickHit = false;
	}

	//returns brick that was hit or null if no brick hit
	public Piece move(int paddleX, int paddleY, ArrayList<Piece> bricks) {
		brickHit = false;
		Piece hitBrick = setMoveDirection(paddleX, paddleY, bricks);

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

		return hitBrick;
	}

	//returns brick that was hit or null if no brick hit
	private Piece setMoveDirection(int x, int y, ArrayList<Piece> bricks) {
		// check if the ball should move up or down and then right or left

		checkHitWall();
		checkHitPaddle(x, y);
		for (Piece brick : bricks) {
			checkBrickCollision(brick);
			if (brickHit) {
				return brick;
			}
		}
		//no brick hit
		return null;
	}

	private void checkHitWall() {
		checkTopWall();
		checkLeftWall();
		checkRightWall();
	}

	private void checkTopWall() {
		if (yPos - BALL_DIAMETER <= 0) {
			switchUpandDown();
		}
	}

	private void checkLeftWall() {
		if (xPos - BALL_DIAMETER <= 0) {
			switchRightandLeft();
		}
	}

	private void checkRightWall() {

		if (xPos + BALL_DIAMETER + 6 >= Board.BOARD_WIDTH) {
			switchRightandLeft();
		}
	}

	private void checkHitPaddle(int x, int y) {
		checkTopPaddle(x, y);
		checkRightSidePaddle(x, y);
		checkLeftSidePaddle(x, y);

	}

	private void checkLeftSidePaddle(int x, int y) {
		if (xPos == x && yPos >= y && yPos <= (y + Paddle.PADDLE_HEIGHT)) {
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
		if (yPos == (y - BALL_DIAMETER)) {
			if (xPos <= (x + Paddle.PADDLE_LENGTH) && xPos >= x) {
				switchUpandDown();
			}
		}
	}

	private void checkBrickCollision(Piece brick) {
		checkBrickRight(brick);
		checkBrickLeft(brick);
		checkBrickTop(brick);
		checkBrickBottom(brick);

	}

	private void checkBrickRight(Piece brick) {
		int brickY = brick.getY();
		if ((xPos == (brick.getX() + Piece.BRICK_LENGTH))
				&& (yPos <= (brickY + Piece.BRICK_WIDTH)) && (yPos >= brickY)) {
			switchRightandLeft();
			brickHit = true;
		}
	}

	private void checkBrickLeft(Piece brick) {
		int brickY = brick.getY();
		if ((xPos == brick.getX()) && (yPos <= (brickY + Piece.BRICK_WIDTH))
				&& (yPos >= brickY)) {
			switchRightandLeft();
			brickHit = true;
		}
	}

	private void checkBrickBottom(Piece brick) {
		int brickX = brick.getX();
		if ((yPos == brick.getY() + Piece.BRICK_WIDTH)
				&& (xPos <= brickX + Piece.BRICK_LENGTH) && (xPos >= brickX)) {
			switchUpandDown();
			brickHit = true;
		}

	}

	private void checkBrickTop(Piece brick) {
		int brickX = brick.getX();
		if ((yPos == brick.getY()) && (xPos <= brickX + Piece.BRICK_LENGTH)
				&& (xPos >= brickX)) {
			switchUpandDown();
			brickHit = true;
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

	private void switchUpandDown() {
		if (moveUp) {
			moveUp = false;
			moveDown = true;
		} else {
			moveUp = true;
			moveDown = false;
		}

	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}
}