package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Animation.Animation;
import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Bird extends Enemy{
	
	private BufferedImage[] sprites;
	private int x1, x2;
	
	public Bird(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		moveSpeed = 1.0;
		maxSpeed = 1.0;
		fallSpeed = 0.0;
		maxFallSpeed = 10.0;
		
		health = maxHealth = 6;
		damage = 2;
		
		width = 32;
		height = 20;
		cwidth = 32;
		cheight = 18;
		
		spritesheet = res.getSpritesheet("bird");
		sprites = new BufferedImage[4];
		for(int i = 0; i < sprites.length; i++){
			sprites[i] = spritesheet.getSubimage(0 + i * width, 0, width, height);
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
			dx -= moveSpeed;
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		}else if(right){
			dx += moveSpeed;
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
		if(right && dx == 0 || right && x > x2){
			right = false;
			left = true;
			facingRight = false;
		}
		else if (left && dx == 0 || left && x < x1){
			right = true;
			left = false;
			facingRight = true;
		}
		
		animation.update();
	}
	
	public void setPatrol(int x1, int x2){
		this.x1 = x1;
		this.x2 = x2;
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
