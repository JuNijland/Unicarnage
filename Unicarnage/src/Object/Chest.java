package Object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Animation.Animation;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Chest extends StaticObject{
	
	private final static int STATIC = 0;
	private final static int ACTIVATED = 1;
	
	
	
	private BufferedImage[] foolSprites;
	private BufferedImage[] hasUsedSprites;
	
	public Chest(TileMap tm, ResourceLoader res){ 
		super(tm, res);
		trapped = false; 
		
		key = "chest";
		
		currentState = STATIC;
		sprites = new BufferedImage[1];
		activationSprites = new BufferedImage[3];
		foolSprites = new BufferedImage[5];
		hasUsedSprites = new BufferedImage[1];
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
			
		spritesheet = res.getSpritesheet("chest");
		sprites[0] = spritesheet.getSubimage(0, 0, 30, 30);
		hasUsedSprites[0] = spritesheet.getSubimage(60, 0, width, height);
		for(int i = 0; i < 3; i++){
			activationSprites[i] = spritesheet.getSubimage(i*30, 0, 30, 30);
		}
		for(int i = 0; i < 5; i++){
			foolSprites[i] = spritesheet.getSubimage(i*30, 0, 30, 30);
		}
	
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(-1);
		
	}
	
	public void update(){
		
		if (currentState == ACTIVATED) {
			if (animation.playedOnce()){
				currentState = STATIC;
				activated = false;
				if(trapped){
					animation.setFrames(foolSprites);
					animation.setFrame(4);
				}else{
					animation.setFrames(activationSprites);
					animation.setFrame(2);
				}
				
				animation.setDelay(-1);
			}
		}
		
		
		if(activated){
			if(currentState != ACTIVATED){
				if(!trapped){
					currentState = ACTIVATED;
					hasActivated = true;
					animation.setFrames(activationSprites);
					animation.setDelay(200);
				}else{
					currentState = ACTIVATED;
					animation.setFrames(foolSprites);
					animation.setDelay(100);
				}
			}
		}
		animation.update();
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}
}
