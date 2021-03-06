package englard_shapiro_brickBreaker;

import com.google.inject.Singleton;

@Singleton
public class Paddle {

	private static final int MOVE_AMOUNT = 2;
	private int xPos;
	private int yPos;
	private int paddleLength;
	public static final int PADDLE_HEIGHT = 15;

	public Paddle() {
		paddleLength = 80;
		setDefault();
	}

	public void setDefault() {
		xPos = (Board.BOARD_WIDTH - paddleLength) / 2;
		yPos = (Board.BOARD_HEIGHT - PADDLE_HEIGHT) - 40;
		paddleLength = 80;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public int getPaddleLength() {
		return paddleLength;
	}

	public void setPaddleMini() {
		paddleLength = 55;
	}

	public void setPaddleLong() {
		paddleLength = 160;
	}

	public void moveLeft() {
		if (xPos - MOVE_AMOUNT >= 0) {
			xPos -= MOVE_AMOUNT;
		}
	}

	public void moveRight() {
		if (xPos + MOVE_AMOUNT + 5 < (Board.BOARD_WIDTH - paddleLength)) {
			xPos += MOVE_AMOUNT;
		}
	}

}