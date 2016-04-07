package englard_shapiro_brickBreaker;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class MusicThread extends Thread {
	private AudioClip clip;

	public MusicThread() {
	}

	public void run() {

		URL urlClick = getClass().getResource("/Nine.wav");
		clip = Applet.newAudioClip(urlClick);
		clip.play();
	}

	public void stopMusic() {
		clip.stop();
	}
}
