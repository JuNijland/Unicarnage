package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.Animation;
import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Bear extends Enemy{
	
	private BufferedImage[] sprites;
	private BufferedImage[] angrySprites;
	private boolean mad;
	
	public Bear(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		moveSpeed = 0.05;
		maxSpeed = 0.6;
		fallSpeed = 0.2;
		maxFallSpeed = 2.0
				;
		jumpStart = -4.0;
		stopJumpSpeed = 0.3;
		
		width = 60;
		height = 30;
		cwidth = 55;
		cheight = 26;
		
		health = maxHealth = 8;
		damage = 1;
		
		// load sprites
		spritesheet = res.getSpritesheet("bear");		
		sprites = new BufferedImage[4];
		angrySprites = new BufferedImage[4];
		
		for(int j =0; j < sprites.length; j++){
			sprites[j] = spritesheet.getSubimage(j * width, 0, width, height);
			angrySprites[j] = spritesheet.getSubimage(j * width, height, width, height);
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
		if (jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}
		if(falling){
			dy += fallSpeed;
		}else{
			jumping = false;
		}
		
	}
	
	public void update(){
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp,ytemp);
		
		animation.update();
		
		//check flinching
		if(flinching){
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 400) {
				flinching = false;
			}
		}
		
		if(player.getX()-x < 150 && player.getX()-x > -150 && health < maxHealth){
			//mad bear
			
			maxSpeed = 1.5;
			if(!mad) animation.setFrames(angrySprites);
			
			mad = true;
			
			animation.setDelay(80);
			if(player.getX() < x){
				left = true;
				right = false;
				facingRight = false;
			}else{
				left = false;
				right = true;
				facingRight = true;
			}
			
			if(dx == 0){
				setJumping(true);
			}else{
				setJumping(false);
			}
		}else{
			//not mad bear
			maxSpeed = 0.3;
			//animation.setFrames(sprites);
			animation.setDelay(200);
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
		}
	}
	
	public void draw(Graphics2D g){
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0)
				return;
		}
		
		super.draw(g);
	}

}
