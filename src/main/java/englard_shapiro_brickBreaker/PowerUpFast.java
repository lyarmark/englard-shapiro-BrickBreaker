package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUpFast extends PowerUp {

	@Override
	public void powerUp(BrickBreakerGame game) {
		//game.setSpeed(2);
		game.speedUp();
	}

	@Override
	public void draw(Graphics g, int powerX, int powerY) {
		g.setColor(Color.MAGENTA);
		g.fillOval(powerX, powerY, super.DIAMETER, super.DIAMETER);
	}
}