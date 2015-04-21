package Entity.Projectiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.Animation;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Snowball extends Projectile{

	public Snowball(TileMap tm, ResourceLoader res, double dx, double dy) {
		super(tm, res);
		
		width = 10;
		height = 10;
		cwidth = 8;
		cheight = 8;
		
		hostile = true;
		
		key = "snowball";
		
		setVector(dx, dy);
		
		if(dx > 0){
			facingRight = true;
		}else{
			facingRight = false;
		}
		
		spritesheet = res.getSpritesheet("snowball");
		sprites = new BufferedImage[1];
		sprites[0] = spritesheet.getSubimage(0, 0, width, height);
		
		hitSprites = new BufferedImage[1];
		hitSprites[0] = sprites[0];
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(-1);
	}
	
	public void update(){		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dx == 0 && !hit || dy == 0 && !hit) setHit();
		
		animation.update();
		if(hit && animation.playedOnce())
			remove = true;
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		
		super.draw(g);
	}
	
	

}
