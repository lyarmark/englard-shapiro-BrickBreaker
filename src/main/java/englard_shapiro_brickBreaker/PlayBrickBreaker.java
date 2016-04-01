package englard_shapiro_brickBreaker;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class PlayBrickBreaker {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new GameModule());
		BrickBreakerGame game = injector.getInstance(BrickBreakerGame.class);
	}
}