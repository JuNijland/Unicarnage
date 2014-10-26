package Audio;

import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;

public class Music implements Runnable {
    private AudioClip sound;
    
    public Music(String s) {
    	try{
         URL url = Sound.class.getResource("/Music/" + s);
         this.sound=Applet.newAudioClip(url);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public void play() {
        this.sound.loop();
    }
    @Override
    public void run() {
        this.play();
    }
    
    public void stop(){
    	this.sound.stop();
    }
 
} 