package Entity.Projectiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.Animation;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class PlantProjectile extends Projectile{

	public PlantProjectile(TileMap tm, ResourceLoader res, double dx, double dy) {
		super(tm, res);
		
		width = 10;
		height = 10;
		cwidth = 8;
		cheight = 8;
		
		hostile = true;
		
		key = "plantball";
		
		setVector(dx, dy);
		if(dx > 0){
			facingRight = true;
		}else{
			facingRight = false;
		}
		
		spritesheet = res.getSpritesheet("plantprojectile");
		sprites = new BufferedImage[4];
		for(int i = 0; i < sprites.length; i++){
			sprites[i] = spritesheet.getSubimage(0 + i * width, 0, width, height);
		}
		hitSprites = new BufferedImage[1];
		hitSprites[0] = spritesheet.getSubimage(0, 0, width, height);
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(100);
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
