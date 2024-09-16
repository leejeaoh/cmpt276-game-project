package Documents.main;

import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

/**
 * The Sound class handles audio playback in the game.
 * It loads and plays different audio files for various game events.
 */
public class Sound {
	//used to open the audio file
	Clip clip;

	//to store the path of the sound file
	URL soundURL[] = new URL[20];

	/**
     * Constructs a Sound object and initializes the URLs for the sound files.
     */
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/map1.wav");
		soundURL[1] = getClass().getResource("/sound/collectkey.wav");
		soundURL[2] = getClass().getResource("/sound/doorOpen.wav");
	}

	/**
     * Loads the audio file specified by the index i.
     * @param i The index of the sound file to load.
     */
	public void fileSet(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {

		}
	}

	/**
     * Starts playing the loaded audio file.
     */
	public void play() {
		clip.start();
	}

	/**
     * Loops the loaded audio file continuously.
     */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
     * Stops playing the audio file.
     */
	public void stop() {
		clip.stop();
	}

	/**
     * Completely close the audio file.
     */
	public void close() {
		clip.close();
	}

}