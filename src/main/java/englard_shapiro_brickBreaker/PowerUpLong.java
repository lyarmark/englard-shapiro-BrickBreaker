package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUpLong extends PowerUp{

	@Override
	public void powerUp(BrickBreakerGame game) {
		game.setPaddleLong();
	}

	@Override
	public void draw(Graphics g, int powerX, int powerY) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(powerX, powerY, super.DIAMETER, super.DIAMETER);
	}
}