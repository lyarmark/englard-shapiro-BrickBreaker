package englard_shapiro_brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUpSpeed extends PowerUp {

	@Override
	public void powerUp(BrickBreakerGame game) {
		game.setSpeed(2);
	}

	@Override
	public void draw(Graphics g, int powerX, int powerY) {
		g.setColor(Color.MAGENTA);
		g.fillOval(powerX, powerY, super.DIAMETER, super.DIAMETER);
	}

	@Override
	public void undoPowerUp(BrickBreakerGame game) {
		System.out.println("undo power");
		game.setSpeed(5);
	}
}
// PowerUp[] = {PowerUpSpeed, PowerUpPaddle ...}
// int powerIndex = Math.random();
// PowerUp p = new powers[powerIndex]
// p.powerUp();
// each powerUp class will override the powerUp method from the abstract parent
// class
// do this every 15 seconds

