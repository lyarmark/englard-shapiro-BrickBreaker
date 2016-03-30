package englard_shapiro_brickBreaker;

import javax.swing.JComponent;

public abstract class PowerUp extends JComponent {

	private static final long serialVersionUID = 1L;
	private int xPosition;
	private int yPosition;
	private int diameter;

	public void powerUp(BrickBreakerGame game) {
	}

	public boolean checkHitPaddle(int x, int y, int paddleLength) {
		boolean hit = checkTopPaddle(x, y, paddleLength);
		if (!hit) {
			hit = checkSidePaddle(x, y, paddleLength);
		}
		return hit;
	}

	private boolean checkTopPaddle(int x, int y, int paddleLength) {
		if (yPosition + diameter == (y)) {
			if (xPosition < (x + paddleLength) && xPosition > x) {
				return true;
			}
		}
		return false;
	}

	private boolean checkSidePaddle(int x, int y, int paddleLength) {

		if (((leftSide(x) || rightSide(x, paddleLength)) && yPosition + diameter >= y && yPosition + diameter <= (y + Paddle.PADDLE_HEIGHT))) {
			return true;
		}
		return false;
	}

	private boolean rightSide(int x, int paddleLength) {
		return xPosition == (x + paddleLength);
	}

	private boolean leftSide(int x) {
		return (((xPosition + diameter) >= x) && (xPosition < x));
	}

}
