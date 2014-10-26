package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Animation.Animation;
import Entity.Player;
import Entity.Projectiles.PlantProjectile;
import Entity.Projectiles.Projectile;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class AngryPlant extends Enemy{
	
	private BufferedImage[] attackingsprites;
	private BufferedImage[] sprites;
	private long lastAttack;
	private long attackInterval;
	
	private boolean attacking;
	private int side;
	
	private ArrayList<Projectile> plantballs;
	
	public AngryPlant(TileMap tm, ResourceLoader res, Player p, ArrayList<Projectile> projectiles){
		super(tm, res, p);
		plantballs = projectiles;
		side = 0;
		
		attackInterval = 5000;
		lastAttack = System.nanoTime();
		
		moveSpeed = 0.0;
		maxSpeed = 0.0;
		fallSpeed = 0.0;
		maxFallSpeed = 0.0;
		
		width = 20;
		height = 30;
		cwidth = 16;
		cheight = 25;
		
		health = maxHealth = 999999;
		damage = 1;
		
		facingRight = true;
		
		sprites = new BufferedImage[1];
		attackingsprites = new BufferedImage[3];
		sprites[0] = res.getSpritesheet("angryplant").getSubimage(0, 0, width, height);
		for(int i = 0; i < attackingsprites.length; i++){
			attackingsprites[i] = res.getSpritesheet("angryplant").getSubimage(0 + (i * width), 0, width, height);
		}
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(-1);
		
	}
	
	private void getNextPosition(){	
		//falling
		if(falling){
			dy += fallSpeed;
		}
		
	}
	
	@Override 
	public void hit(int damage){}
	
	public void setFiringSpeed(long interval){
		attackInterval = interval;
	}
	
	public void setSide(int i){
		side = i;
		if(i == 1 || i == 3){
			cwidth = 25;
			cheight = 16;
			width = 32;
			height = 32;
			sprites[0] = res.getSpritesheet("angryplant").getSubimage(60, 0, width, height);
			for(int j = 0; j < attackingsprites.length; j++){
				attackingsprites[j] = res.getSpritesheet("angryplant").getSubimage(60 + (j * width), 0, width, height);
			}
		}else{
			cwidth = 16;
			cheight = 25;
		}
	}
	
	
	public void update(){
		//basics
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp,ytemp);
		
		long elapsed = (System.nanoTime() - lastAttack) / 1000000;
		if(elapsed > attackInterval){
			lastAttack = System.nanoTime();
			attacking = true;
			animation.setFrames(attackingsprites);
			animation.setDelay(200);
		}
		if(attacking == true){
			if(animation.playedOnce()){
				//projectile stuff
				PlantProjectile p1;
				PlantProjectile p2;
				PlantProjectile p3;
				if(side == 0){
					p1 = new PlantProjectile(tileMap, res, 0.000001, -1.0);
					p2 = new PlantProjectile(tileMap, res, 0.7, -0.7);
					p3 = new PlantProjectile(tileMap, res, -0.7, -0.7);
				}else if(side == 1){
					p1 = new PlantProjectile(tileMap, res, -1.0, 0.000001);
					p2 = new PlantProjectile(tileMap, res, -0.7, -0.7);
					p3 = new PlantProjectile(tileMap, res, -0.7, 0.7);
				}else if(side == 2){
					p1 = new PlantProjectile(tileMap, res, 0.000001, 1.0);
					p2 = new PlantProjectile(tileMap, res, 0.7, 0.7);
					p3 = new PlantProjectile(tileMap, res, -0.7, 0.7);
				}else{
					p1 = new PlantProjectile(tileMap, res, 1.0, 0.000001);
					p2 = new PlantProjectile(tileMap, res, 0.7, -0.7);
					p3 = new PlantProjectile(tileMap, res, 0.7, 0.7);
				}
				
				p1.setPosition(this.x, this.y);
				plantballs.add(p1);
				
				p2.setPosition(this.x, this.y);
				plantballs.add(p2);
				
				p3.setPosition(this.x, this.y);
				plantballs.add(p3);
				
				animation.setFrames(sprites);
				animation.setDelay(-1);
				attacking = false;
			}
		}
		
		animation.update();
	}
	
	public void draw(Graphics2D g){
		//if(notOnScreen()) return;
		
		setMapPosition();
		if(side == 0){
			g.drawImage(animation.getImage(),(int)(x + xmap - width / 2), (int)(y + ymap - height /2), null);
		}else if(side == 1){
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width),  (int)(y + ymap - height / 2), -width, height, null);
		}else if(side == 2){
			g.drawImage(animation.getImage(),(int)(x + xmap - width / 2), (int)(y + ymap - height /2 + height), width, -height, null);	
		}else if(side == 3){
			g.drawImage(animation.getImage(),(int)(x + xmap - width / 2), (int)(y + ymap - height /2), null);
			
		}
	}

}
