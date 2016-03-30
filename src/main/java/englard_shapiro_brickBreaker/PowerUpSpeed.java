package englard_shapiro_brickBreaker;

public class PowerUpSpeed extends PowerUp {

	private static final long serialVersionUID = 1L;

	@Override
	public void powerUp(BrickBreakerGame game) {
		super.powerUp(game);
		game.setSpeed(7);
	}

}
// PowerUp[] = {PowerUpSpeed, PowerUpPaddle ...}
// int powerIndex = Math.random();
// PowerUp p = new powers[powerIndex]
// p.powerUp();
// each powerUp class will override the powerUp method from the abstract parent
// class
// do this every 15 seconds