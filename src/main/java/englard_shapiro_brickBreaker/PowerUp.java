package englard_shapiro_brickBreaker;

import javax.swing.JComponent;

public abstract class PowerUp extends JComponent {

	private int x;
	private int y;

	public PowerUp(int x) {
		this.x = 300;
		this.y = 30;
	}

	public void powerUp() {
	}

	public void floatPowerDownScreen(int x, int y) {
		// where ever this is called will loop, and send diff x/ values to
		// 'animate'
		this.x = x;
		this.y = y;
	}
}
