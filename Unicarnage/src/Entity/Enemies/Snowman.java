package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Animation.Animation;
import Entity.Player;
import Entity.Projectiles.PlantProjectile;
import Entity.Projectiles.Projectile;
import Entity.Projectiles.Snowball;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Snowman extends Enemy{
	
	private BufferedImage[] attackingsprites;
	private BufferedImage[] sprites;
	private long lastAttack;
	private long attackInterval;
	
	private boolean attacking;
	private int side;
	private boolean mad;
	
	private ArrayList<Projectile> snowballs;
	
	public Snowman(TileMap tm, ResourceLoader res, Player p, ArrayList<Projectile> projectiles){
		super(tm, res, p);
		snowballs = projectiles;
		
		attackInterval = 1000;
		lastAttack = System.nanoTime();
		
		moveSpeed = 0.2;
		maxSpeed = 2.0;
		fallSpeed = 0.2;
		maxFallSpeed = 2.0;
		jumpStart = -3.5;
		stopJumpSpeed = 0.3;
		
		width = 32;
		height = 64;
		cwidth = 28;
		cheight = 54;
		
		health = maxHealth = 99999;
		damage = 0;
		
		facingRight = true;
		
		sprites = new BufferedImage[1];
		attackingsprites = new BufferedImage[3];
		sprites[0] = res.getSpritesheet("snowman").getSubimage(0, 0, width, height);	
		attackingsprites[0] = res.getSpritesheet("snowman").getSubimage(width, 0, width, height);
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(-1);
		
		key = "snowman";
		
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
	
	@Override 
	public void hit(int damage){}
	
	public void setFiringSpeed(long interval){
		attackInterval = interval;
	}
	
	public void update(){
		//basics
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp,ytemp);
		
		animation.update();
		
		if(flinching){
			long elapsed2 = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed2 > 400) {
				flinching = false;
			}
		}
		 
		if(player.getX()-x < 150 && player.getX()-x > -150 && !(player.getX()-x < 50 && player.getX()-x > -50)){
			//mad snowman
			long elapsed = (System.nanoTime() - lastAttack) / 1000000;
			if(elapsed > attackInterval){
				lastAttack = System.nanoTime();
				attacking = true;
			}
			if(attacking == true){
				
				//projectile stuff
				Snowball ball;
				if(facingRight){
					ball = new Snowball(tileMap, res, 3.5, 0.0000001);
				}else{
					ball = new Snowball(tileMap, res, -3.5, 0.0000001);
				}
				
				ball.setPosition(this.x, this.y);
				snowballs.add(ball);
				
				animation.setFrames(sprites);
				animation.setDelay(-1);
				attacking = false;
				
			}
			
			maxSpeed = 2.0;
			if(!mad) 
				animation.setFrames(attackingsprites);
			mad = true;			
			if(player.getX() < x){
				left = true;
				right = false;
				facingRight = false;
			}else{
				left = false;
				right = true;
				facingRight = true;
			}
			
			
			setJumping(true);
			
			
		}else{
			//not mad snowman
			animation.setFrames(sprites);
			setJumping(false);
			maxSpeed = 0.0000001;
			mad = false;
			//animation.setFrames(sprites);
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
			setJumping(false);
		}
	}
	
	public void draw(Graphics2D g){
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		if (flinching) {
			long elapsed2 = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed2 / 100 % 2 == 0)
				return;
		}
		
		super.draw(g);
	}

}
