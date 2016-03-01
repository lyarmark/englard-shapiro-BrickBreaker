package englard_shapiro_brickBreaker.englard_shapiro_brickBreaker;

import java.awt.Color;

public class Piece {

	private int xPos;
	private int yPos;
	private Color color;
	private int length;
	private int width;

	public Color getColor() {
		return color;
	}

	/*
	 * the x is left most spot of rectnagle
	 */
	public Piece(int x, int y, Color color) {
		xPos = x;
		yPos = y;
		this.color = color;
		length = 50;
		width = 30;
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

	public int getWidth() {
		return width;
	}

}
