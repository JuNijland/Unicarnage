package Object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.Animation;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Door extends StaticObject{
	
	private final static int STATIC = 0;
	private final static int ACTIVATED = 1;
	
	
	public Door(TileMap tm, ResourceLoader res){
		super(tm, res); 
		
		key = "door";
		
		currentState = STATIC;
		sprites = new BufferedImage[1];
		
		width = 30;
		height = 60;
		cwidth = 20;
		cheight = 40;
		
			
		spritesheet = res.getSpritesheet("door");
		sprites[0] = spritesheet.getSubimage(0, 0, 30, 60);
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(-1);

		
	}
	
	public void update(){
		
		if (currentState == ACTIVATED) {
			if (animation.playedOnce()){
				currentState = STATIC;
				activated = false;
	
			}
		}
		
		
		if(activated){
			if(currentState != ACTIVATED){
				
				currentState = ACTIVATED;
				hasActivated = true;
				
			}
		}
		animation.update();
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}
}
