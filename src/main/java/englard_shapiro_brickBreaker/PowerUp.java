package englard_shapiro_brickBreaker;

import javax.swing.JComponent;

public abstract class PowerUp extends JComponent {

	private static final long serialVersionUID = 1L;
	private int diameter;

	public void powerUp(BrickBreakerGame game) {
	}

	public boolean checkHitPaddle(int x, int y, int paddleLength, int yPosition, int xPosition) {
		boolean hit = checkTopPaddle(x, y, paddleLength, yPosition, xPosition);
		if (!hit) {
			hit = checkSidePaddle(x, y, paddleLength, yPosition, xPosition);
		}
		return hit;
	}

	private boolean checkTopPaddle(int x, int y, int paddleLength, int yPosition, int xPosition) {
		if (yPosition + diameter == (y)) {
			if (xPosition < (x + paddleLength) && xPosition > x) {
				return true;
			}
		}
		return false;
	}

	private boolean checkSidePaddle(int x, int y, int paddleLength, int yPosition, int xPosition) {

		if (((leftSide(x, xPosition) || rightSide(x, paddleLength, xPosition)) && yPosition + diameter >= y && yPosition + diameter <= (y + Paddle.PADDLE_HEIGHT))) {
			return true;
		}
		return false;
	}

	private boolean rightSide(int x, int paddleLength, int xPosition) {
		return xPosition == (x + paddleLength);
	}

	private boolean leftSide(int x, int xPosition) {
		return (((xPosition + diameter) >= x) && (xPosition < x));
	}

}
