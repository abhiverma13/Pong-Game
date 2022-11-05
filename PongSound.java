import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class PongSound {
			
	public void play1() {
		AudioInputStream stream;
		URL url = PongSound.class.getResource("PongPaddleSound.wav");
		try {
			stream = AudioSystem.getAudioInputStream(url);
			AudioFormat format = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip;
			try {
				clip = (Clip)AudioSystem.getLine(info);
				clip.open(stream);
				clip.setFramePosition(0);
				clip.start();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void play2() {
		AudioInputStream stream;
		URL url = PongSound.class.getResource("MarioDeathSound.wav");
		try {
			stream = AudioSystem.getAudioInputStream(url);
			AudioFormat format = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip;
			try {
				clip = (Clip)AudioSystem.getLine(info);
				clip.open(stream);
				clip.setFramePosition(0);
				clip.start();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

