package csc4380.cle25;
import javax.sound.sampled.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AudioPlayer {
    private boolean stop = false;
    private Clip theClip;
    AudioPlayer(String fileName, boolean loop) {
        try {
            File theFile = new File(fileName);
            AudioInputStream theStream = 
                    AudioSystem.getAudioInputStream(theFile);
            AudioFormat theFormat = theStream.getFormat();
            DataLine.Info theInfo = new DataLine.Info(Clip.class, theFormat);
            theClip = (Clip) AudioSystem.getLine(theInfo);
            theClip.open(theStream);
            if (loop) {
                theClip.addLineListener(new LineListener() {

                    @Override
                    public void update(LineEvent le) {
                        if (le.getType() == LineEvent.Type.STOP && !stop) {
                            theClip.setFramePosition(0);
                            theClip.start();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
        theClip.stop();
    }
    
    public void playFromBeginning() {
        try {
            theClip.setFramePosition(0);
            theClip.start();
            stop = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        try {
            theClip.start();
            stop = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
