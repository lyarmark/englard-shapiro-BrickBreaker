package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUpSlow extends PowerUp {

	@Override
	public void powerUp(BrickBreakerGame game) {
		game.slowDown();
	}

	@Override
	public void draw(Graphics g, int powerX, int powerY) {
		g.setColor(Color.CYAN);
		g.fillOval(powerX, powerY, PowerUp.DIAMETER, PowerUp.DIAMETER);
	}
}