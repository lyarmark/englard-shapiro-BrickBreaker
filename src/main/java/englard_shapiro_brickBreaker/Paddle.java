package englard_shapiro_brickBreaker;


public class Paddle  {

	private int xPos;
	private int yPos;
	public static final int PADDLE_LENGTH = 80;
	public static final int PADDLE_HEIGHT = 15;

	public Paddle() {

		xPos = (Board.BOARD_WIDTH - PADDLE_LENGTH) / 2;
		yPos = (Board.BOARD_HEIGHT - PADDLE_HEIGHT) - 75;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public void moveLeft() {
		if (xPos - 15 >= 0) {
			xPos -= 15;
		}
	}

	public void moveRight() {

		if (xPos + 15 <= (Board.BOARD_WIDTH - PADDLE_LENGTH)) {
			xPos += 15;
		}
	}

}
