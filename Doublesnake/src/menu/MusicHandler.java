/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Windows Seven
 */
class MusicHandler implements Runnable {

        private Thread th;
        private boolean newMusic;
        private String location;
        private boolean play;

        public MusicHandler() {
            th = new Thread(this);
            play = true;
            location = "home.wav";
            th.start();
            newMusic = false;
        }

        @Override
        public void run() {
            SourceDataLine soundLine = null;
            int BUFFER_SIZE = 64 * 1024;
            while (true) {
                try {

                    File soundFile = new File(location);
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                    AudioFormat audioFormat = audioInputStream.getFormat();
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                    soundLine = (SourceDataLine) AudioSystem.getLine(info);
                    soundLine.open(audioFormat);
                    soundLine.start();
                    int nBytesRead = 0;
                    byte[] sampledData = new byte[BUFFER_SIZE];

                    while (nBytesRead != -1 && play == true) {
                        nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
                        if (newMusic) {
                            newMusic = false;
                            th.sleep(1000);
                            break;
                        }
                        if (nBytesRead >= 0) {
                            soundLine.write(sampledData, 0, nBytesRead);

                        }
                    }
                } catch (Exception e) {
                    System.err.println("Could not start music!");
                    e.printStackTrace();
                }
            }

        }

        public void setLocation(String location) {
            this.location = location;
            newMusic = true;
        }
    }
    
  