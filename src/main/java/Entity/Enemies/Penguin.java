package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.Animation;
import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Penguin extends Enemy{
	
	private BufferedImage[] sprites;
	
	public Penguin(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		moveSpeed = 0.3;
		maxSpeed = 1.4;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		
		width = 32;
		height = 32;
		cwidth = 25;
		cheight = 20;
		
		health = maxHealth = 6;
		damage = 2;
		
		// load sprites
		spritesheet = res.getSpritesheet("pinguin");		
		sprites = new BufferedImage[3];
		for(int i =0; i < sprites.length; i++){
			sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(200);
		
		right = true;
		facingRight = true;
	}
	
	private void getNextPosition(){
		
		//movement
		if(left){
			dx -= moveSpeed;
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		}else if(right){
			dx += moveSpeed;
			if(dx > maxSpeed){
				dx = maxSpeed;
			}
		}
		if(falling){
			dy += fallSpeed;
		}
		
	}
	
	public void update(){
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp,ytemp);
		
		//check flinching
		if(flinching){
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching = false;
			}
		}
		
		//hit wall? turn
		if(right && dx == 0){
			right = false;
			left = true;
			facingRight = false;
		}
		else if (left && dx == 0){
			right = true;
			left = false;
			facingRight = true;
		}
		
		animation.update();
	}
	
	public void draw(Graphics2D g){
		//if(notOnScreen()) return;
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0)
				return;
		}
		
		setMapPosition();
		super.draw(g);
	}

}
