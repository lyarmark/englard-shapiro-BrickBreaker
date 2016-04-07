package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUpMini extends PowerUp {

	@Override
	public void powerUp(BrickBreakerGame game) {
		game.setPaddleMini();
	}

	@Override
	public void draw(Graphics g, int powerX, int powerY) {
		g.setColor(Color.PINK);
		g.fillRect(powerX, powerY, PowerUp.DIAMETER, PowerUp.DIAMETER);
	}
}