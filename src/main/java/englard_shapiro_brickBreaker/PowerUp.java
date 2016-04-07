package englard_shapiro_brickBreaker;

import java.awt.Graphics;

public abstract class PowerUp {

	final static int DIAMETER = 20;

	public abstract void powerUp(BrickBreakerGame game);

	public abstract void draw(Graphics g, int powerX, int powerY);

	public boolean checkHitPaddle(int x, int y, int paddleLength,
			int yPosition, int xPosition) {
		boolean hit = checkTopPaddle(x, y, paddleLength, yPosition, xPosition);
		if (!hit) {
			hit = checkSidePaddle(x, y, paddleLength, yPosition, xPosition);
		}
		return hit;
	}

	private boolean checkTopPaddle(int x, int y, int paddleLength,
			int yPosition, int xPosition) {
		if (y + DIAMETER == (yPosition - 1)) {
			if (x <= (xPosition + paddleLength) && x >= xPosition) {
				return true;
			}
		}
		return false;
	}

	private boolean checkSidePaddle(int x, int y, int paddleLength,
			int yPosition, int xPosition) {

		if (((leftSide(x, xPosition) || rightSide(x, paddleLength, xPosition))
				&& yPosition + DIAMETER >= y && yPosition + DIAMETER <= (y + Paddle.PADDLE_HEIGHT))) {
			return true;
		}
		return false;
	}

	private boolean rightSide(int x, int paddleLength, int xPosition) {
		return xPosition == (x + paddleLength);
	}

	private boolean leftSide(int x, int xPosition) {
		return (((xPosition + DIAMETER) >= x) && (xPosition < x));
	}
}
