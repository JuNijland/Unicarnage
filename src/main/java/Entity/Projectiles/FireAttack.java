package Entity.Projectiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.Animation;
import Audio.Sound;
import TileMap.TileMap;
import Utility.ResourceLoader;

public class FireAttack extends Projectile{
	
	private Sound fireSound;
	
	public FireAttack(TileMap tm, ResourceLoader res, boolean facingRight, double x, double y) {
		super(tm, res);
		this.facingRight = facingRight;
		
		width = 55;
		height = 32;
		cwidth = 50;
		cheight = 25;
		
		key = "fireattack";
		
		fireSound = res.getSound("fire");
		fireSound.play();
		
		spritesheet = res.getSpritesheet("fireattack");
		sprites = new BufferedImage[3];
		for(int i = 0; i < sprites.length; i++){
				sprites[i] = spritesheet.getSubimage(0, i * height, width, height);

		}
		if(facingRight)
			setPosition(x + 50, y - 2);
		else{
			setPosition(x - 50, y - 2);
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(150);
	}
	
	public void update(){		
		
		animation.update();
		if(animation.playedOnce())
			remove = true;
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}

}
