package Audio;

import java.io.InputStream;
import java.net.URL;
import javax.swing.*;
import javax.sound.sampled.*;

public class Sound2 {

    public static void main(String[] args) throws Exception {
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        InputStream input = Sound2.class.getResourceAsStream("/SFX/fire.wav");
        AudioInputStream ais = AudioSystem.getAudioInputStream(input);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });
    }
}