package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public abstract class PowerUp {

	public void powerUp() {
	}

	public void floatPowerDownScreen(Graphics g, int x, int y) {
		// where ever this is called will loop, and send diff x/ values to
		// 'animate'
		g.setColor(Color.pink);
		g.fillOval(x, y, 20, 20);
	}
}
