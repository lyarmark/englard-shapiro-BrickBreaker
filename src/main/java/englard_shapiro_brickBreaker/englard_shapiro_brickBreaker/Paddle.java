package englard_shapiro_brickBreaker.englard_shapiro_brickBreaker;

import javax.swing.JComponent;

public class Paddle extends JComponent {

	private int xPos;
	private int yPos;
	private int length;
	private int height;
	private int frameWidth;

	public Paddle(int width, int height) {
		frameWidth = height;
		length = 80;
		this.height = 15;
		xPos = (width - length) / 2;
		yPos = (height - this.height) - 75;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	public void moveLeft() {
		if (xPos - 10 >= 0) {
			xPos -= 10;
		}
	}

	public void moveRight() {

		if (xPos + 10 <= (frameWidth - length)) {
			xPos += 10;
		}
	}

}
