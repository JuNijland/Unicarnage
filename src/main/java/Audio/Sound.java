package Audio;


import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound implements Runnable {
    private AudioClip sound;
    
    public Sound(String s) {
    	try{
         URL url = Sound.class.getResource("/SFX/" + s);
         this.sound=Applet.newAudioClip(url);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public void play() {
    	try{
    		this.sound.play();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void stop() {
    	this.sound.stop();
    }
    @Override
    public void run() {
        this.play();
    }

} 
