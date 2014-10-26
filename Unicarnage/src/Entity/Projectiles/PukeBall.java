package Entity.Projectiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.Animation;
import Entity.MapObject;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class PukeBall extends MapObject{
	
	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	
	public PukeBall(TileMap tm, ResourceLoader res, boolean right){	
		super(tm, res);
		
		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;
		
		facingRight = right;
		
		moveSpeed = 3.8;
		if(right){
			dx = moveSpeed;
		}else{ 
			dx = -moveSpeed;		
		}	
		
		//load sprites
		spritesheet = res.getSpritesheet("pukeball");
		sprites = new BufferedImage[4];
		for(int i = 0; i < sprites.length; i++){
			sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
		}
		
		hitSprites = new BufferedImage[3];
		for(int i = 0; i< 3; i++){
			hitSprites[i] = spritesheet.getSubimage(i * width, height, width, height);
		}
			
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(70);
	}
	
	public void setHit(){
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		dx = 0;
	}
	
	public boolean shouldRemove() { return remove; }
	
	public void update(){
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dx == 0 && !hit) setHit();
		
		animation.update();
		if(hit && animation.playedOnce())
			remove = true;
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		
		super.draw(g);
	}
}
