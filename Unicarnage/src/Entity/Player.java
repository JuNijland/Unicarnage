package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Animation.Animation;
import Audio.Sound;
import Entity.Enemies.Enemy;
import Entity.Projectiles.FireAttack;
import Entity.Projectiles.Projectile;
import Entity.Projectiles.PukeBall;
import Items.Item;
import Object.StaticObject;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Player extends MapObject {

	// player stuff
	private int health;
	private int maxHealth;
	private int mana;
	private int maxMana;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;

	// attacks

	// horn attack
	private boolean hornattack;
	private int hornDamage;
	private int hornRange;
	
	//use button
	private boolean using;
	
	private boolean picking;

	// mana attack
	private boolean puking;
	private int pukeCost;
	private int manaDamage;
	private ArrayList<PukeBall> pukeBalls;
	
	//fire attack
	private boolean fireattack;
	private int fireCost;
	private int fireDamage;
	private int fireRange;
	private FireAttack fa;

	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 2, 4, 1, 1, 2, 1, 1 };

	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int FALLING = 2;
	private static final int JUMPING = 3;
	private static final int HORNATTACK = 4;
	private static final int PUKEATTACK = 5;
	private static final int FIREATTACK = 6;
	
	private boolean visible = true;
	
	//Sounds
	private Sound attackSound;
	private Sound jumpSound;
	private Sound damageSound;
	
	//level1state

	public Player(TileMap tm, ResourceLoader res) {
		super(tm, res);
		//sounds
		jumpSound =	res.getSound("jump");
		attackSound = res.getSound("attack");
		damageSound = res.getSound("damage");
		

		width = 60;
		height = 30;
		cwidth = 40;
		cheight = 25;

		moveSpeed = 0.5;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;

		facingRight = true;

		health = maxHealth = 5;

		hornDamage = 2;
		hornRange = 8;

		mana = maxMana = 1000;

		pukeCost = 400;
		manaDamage = 5;
		pukeBalls = new ArrayList<PukeBall>();
		
		fireCost = 400;
		fireDamage = 6;
		fireRange = 100;
		
		spritesheet = res.getSpritesheet("player");
		sprites = new ArrayList<BufferedImage[]>();

		for (int i = 0; i < 7; i++) {
			BufferedImage[] bi = new BufferedImage[numFrames[i]];
			for (int j = 0; j < numFrames[i]; j++) {
				if (i == 4) {
					bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
				} else {
					bi[j] = spritesheet.getSubimage(j * width, i * height,width, height);

				}

			}
			sprites.add(bi);
		}

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);

	}
	
	public void setVisible(boolean b){
		visible = b;
	}
	
	public void updateLevel(TileMap tm, ResourceLoader res){
		tileMap = tm;
		this.res = res;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getMana() {
		return mana;
	}

	public int getMaxMana() {
		return maxMana;
	}
	
	public void setUsing(boolean b) {
		using = b;
	}
	
	public void setPicking(boolean b){
		picking = b;
	}
	
	public void addHealth(int amount){
		health += amount;
		if (health > maxHealth)
			health = maxHealth;
	}
	
	public void addMaxHealth(int amount){
		maxHealth += amount;
	}
	
	public void addMaxSpeed(double d){
		maxSpeed += d;
	}
	
	public void addSpeed(double d){
		moveSpeed += d;
	}
	
	public void addMaxJump(double amount){
		jumpStart -= amount;
	}
	
	public void addStopJump(double amount){
		stopJumpSpeed += amount;
	}
	
	public void addFallSpeed(double amount){
		fallSpeed += amount;
	}
	
	public void addMaxFallSpeed(double amount){
		maxFallSpeed += amount;
	}
	
	public void attack(Item i){
		if(i.getKey() == "Bad Meat"){
			puking = true;
		}else if(i.getKey() == "Horn"){
			hornattack = true;
		}
		else if(i.getKey() == "Pepper"){
			fireattack = true;
		}
	}
	
	public void checkProjectiles(ArrayList<Projectile> projectiles){
		for(int i = 0; i < projectiles.size(); i++){
			Projectile p = projectiles.get(i);
			if(p.isHostile()){
				if(intersects(p)){
					if(p.getKey() == "plantball"){
						hit(1);
						p.setHit();
					}else if(p.getKey() == "snowball"){
						hit(1);
						p.setHit();
					}
				}
			}
		}
	}
	
	public void checkStaticObjects(ArrayList<StaticObject> objects){
		for(int i = 0; i < objects.size(); i++){
			StaticObject o = objects.get(i);
			if(using){
				if(intersects(o)){
					if(o.setActive())
						if(!o.getTrapped()){
						
						}else if(o.getTrapped()){
						hit(1);
					}
				}
			}
		}
	}
	
	public void checkItems(ArrayList<Item> items){
		for(int i = 0; i < items.size(); i++){
			Item item = items.get(i);
			if(picking){
				if(intersects(item)){
					item.setInInv(true);
				}
			}
		}
	}

	public void checkAttack(ArrayList<Enemy> enemies) {

		// loop trough enemies
		for (int i = 0; i < enemies.size(); i++) {

			Enemy e = enemies.get(i);

			// horn attack
			if (hornattack) {
				if (facingRight) {
					if (
							e.getX() > x 
							&& e.getX() - e.getCWidth()/2 - 32 < x + hornRange
							&& e.getY() > y - e.getCHeight() / 2
							&& e.getY() < y + e.getCHeight() / 2) {

						e.hit(hornDamage);
					}

				} else {
					if (
							e.getX() < x 
							&& e.getX() + e.getCWidth()/2 + 32 > x - hornRange
							&& e.getY() > y - e.getCHeight() / 2
							&& e.getY() < y + e.getCHeight() / 2) {
						e.hit(hornDamage);
					}

				}
			}
			//fire attack
			if (fireattack && fa != null) {
				if (facingRight) {
					if (e.getX() > x && e.getX() < x + fireRange
							&& e.getY() > y - height / 2
							&& e.getY() < y + height / 2) {

						e.hit(fireDamage);
					}

				} else {
					if (e.getX() < x && e.getX() > x - fireRange
							&& e.getY() > y - height / 2
							&& e.getY() < y + height / 2) {
						e.hit(fireDamage);
					}

				}
			}
			
			//puke attack
			for (int j = 0; j < pukeBalls.size(); j++) {
				if (pukeBalls.get(j).intersects(e)) {
					e.hit(manaDamage);
					pukeBalls.get(j).setHit();
				}
			}

			// check enemy collision
			if (intersects(e)) {
				if(e.getKey() != "snowman")
					hit(e.getDamage());
			}
		}

	}

	public void hit(int damage) {
		if (flinching)
			return;
		health -= damage;
		damageSound.play();
		if (health < 0)
			health = 0;
		if (health == 0)
			dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public Animation getAnimation() {
		return animation;
	}
	
	public boolean isDead(){
		if(y > tileMap.getHeight() + 100 || health <= 0){
			return true;
		}else{
			return false;
		}
	}

	private void getNextPosition() {
		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		// cannot move while pukeattack
		if ((currentAction == PUKEATTACK || currentAction == FIREATTACK)&& !(jumping || falling)) {
			dx = 0;
		}
		// jumping
		if (jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}

		// falling
		if (falling) {
			dy += fallSpeed;
			if (dy > 0)
				jumping = false;
			if (dy < 0 && !jumping)
				dy += stopJumpSpeed;

			if (dy > maxFallSpeed)
				dy = maxFallSpeed;
		}
	}

	public void update() {
		// update position
		if(!isDead()){
			getNextPosition();
			checkTileMapCollision();
			setPosition(xtemp, ytemp);
	
			if (currentAction == PUKEATTACK) {
				if (animation.playedOnce())
					puking = false;
			}
	
			if (currentAction == HORNATTACK) {
				if (animation.playedOnce())
					hornattack = false;
				if (!falling && !jumping) {
					if (facingRight) {
						checkTileMapCollision(0.6, 0);
					} else {
						checkTileMapCollision(-0.6, 0);
					}
				}
			}
			
			if(currentAction == FIREATTACK){
				if(animation.playedOnce())
					fireattack = false;
			}
	
			// mana attack
			mana += 1;
			if (mana > maxMana)
				mana = maxMana;
			if (puking && currentAction != PUKEATTACK) {
				if (mana > pukeCost) {
					mana -= pukeCost;
					PukeBall pb = new PukeBall(tileMap, res, facingRight);
					pb.setPosition(x + 5, y - 5);
					pukeBalls.add(pb);
				}
			}
	
			if (fireattack && currentAction != FIREATTACK) {
				if (mana > fireCost) {
					mana -= fireCost;
					fa = new FireAttack(tileMap, res, facingRight, x, y);
				}
			}
	
			// update manaattack
			for (int i = 0; i < pukeBalls.size(); i++) {
				pukeBalls.get(i).update();
				if (pukeBalls.get(i).shouldRemove()) {
					pukeBalls.remove(i);
					i--;
				}
			}
			
			if(fa != null){
				fa.update();
				if(fa.shouldRemove()){
					fa = null;
				}
			}
	
			// check done flinching
			if (flinching) {
				long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
				if (elapsed > 1000) {
					flinching = false;
				}
			}
	
			// set animation and sound
			if (hornattack) {
				if (currentAction != HORNATTACK) {
					currentAction = HORNATTACK;
					animation.setFrames(sprites.get(HORNATTACK));
					animation.setDelay(200);
					width = 120;
					attackSound.play();
				}
			} else if (puking) {
				if (currentAction != PUKEATTACK) {
					currentAction = PUKEATTACK;
					animation.setFrames(sprites.get(PUKEATTACK));
					animation.setDelay(200);
					width = 60;
				}
			} else if(fireattack){
				if(currentAction != FIREATTACK){
					currentAction = FIREATTACK;
					animation.setFrames(sprites.get(FIREATTACK));
					animation.setDelay(600);
					width = 60;
				}
			
			} else if (dy > 0) {
				if (currentAction != FALLING) {
					currentAction = FALLING;
					animation.setFrames(sprites.get(FALLING));
					animation.setDelay(100);
					width = 60;
				}
	
			} else if (dy < 0) {
				if (currentAction != JUMPING) {
					currentAction = JUMPING;
					animation.setFrames(sprites.get(JUMPING));
					animation.setDelay(-1);
					width = 60;
					jumpSound.play();
				}
	
			} else if (left || right) {
				if (currentAction != WALKING) {
					currentAction = WALKING;
					animation.setFrames(sprites.get(WALKING));
					animation.setDelay((int) (moveSpeed * 250));
					width = 60;
				}
			} else {
				if (currentAction != IDLE) {
					currentAction = IDLE;
					animation.setFrames(sprites.get(IDLE));
					animation.setDelay(800);
					width = 60;
				}
			}
			animation.update();
	
			// set Direction
			if (currentAction != HORNATTACK && currentAction != PUKEATTACK && currentAction != FIREATTACK) {
				if (right)
					facingRight = true;
				if (left)
					facingRight = false;
			}
		}
	}

	public void draw(Graphics2D g) {
		if(visible){
			setMapPosition();
	
			// draw manaballs
			for (int i = 0; i < pukeBalls.size(); i++) {
				pukeBalls.get(i).draw(g);
			}
			//draw fireattack
			if(fa != null){
				fa.draw(g);
			}
	
			// drawPlayer
			if (flinching) {
				long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
				if (elapsed / 100 % 2 == 0)
					return;
			}
	
			super.draw(g);
		}
	}

}
