import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private String soundName;
	Clip clip;
	
	public Sound(String soundName) {
		this.soundName = soundName;
		try {
            URL url = this.getClass().getClassLoader().getResource("sounds/" + soundName + ".wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
	}
	
	
	
	public void playSound() {
        clip.start();
	}
	
	public void stopSound() {
		clip.stop();
	}
}