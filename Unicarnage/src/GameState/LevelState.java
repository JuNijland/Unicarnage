package GameState;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Audio.Music;
import Audio.Sound;
import Entity.Explosion;
import Entity.Player;
import Entity.Enemies.Enemy;
import Entity.Projectiles.Projectile;
import HUDs.HUD;
import Items.Coin;
import Items.Item;
import Items.RandomItem;
import Main.GamePanel;
import Object.StaticObject;
import TileMap.Background;
import TileMap.TileMap;
import Utility.LevelText;
import Utility.ResourceLoader;

public abstract class LevelState extends GameState{
	
	protected ResourceLoader res;
	protected TileMap tileMap;
	protected Background bg;
	protected Player player;
	protected HUD hud;	
	protected RandomItem randomItem;
	protected LevelText lvltext;
	protected Music music;
	protected Sound deadSound;
	protected Sound explosionSound;
	
	protected ArrayList<Enemy> enemies;
	protected ArrayList<Explosion> explosions;
	protected ArrayList<StaticObject> objects;
	protected ArrayList<Item> items; 
	protected ArrayList<Projectile> projectiles;
	
	protected boolean firstdead = true;
	protected long deadtimer;
	
	protected static int NEXTSTATE = 0; 
	

	public void init() {
		res = gsm.res;
		deadSound = res.getSound("dead");
		explosionSound = res.getSound("explosion");
		//Level Text
		lvltext = new LevelText(res);
		
		//ArrayLists of entities and/or animations
		items = new ArrayList<Item>();
		projectiles = new ArrayList<Projectile>();
		objects = new ArrayList<StaticObject>();
		explosions = new ArrayList<Explosion>();
		
	}
	
	private void addToInv(int i){
		Item item = items.get(i);
		hud.inv.addItem(item);
		items.remove(i);
	}

	public void update() {
		
		//update the player
		player.update();
		
		//check if the player is dead
		if(player.isDead()){
			
			//Gravestone gr = new Gravestone(tileMap, res);
			//gr.setPosition(player.getX(), player.getY() + 200);
			if(firstdead){
				music.stop();
				deadSound.play();
				deadtimer = System.currentTimeMillis();
				firstdead = false;
				explosions.add(new Explosion(player.getX(), player.getY()));
				player.setVisible(false); 
			}else{
				if(deadtimer < System.currentTimeMillis() - 7000)
					stopLevel(0);
				if(deadtimer < System.currentTimeMillis() - 2000){
					for(int i = 0; i < enemies.size(); i++){
						enemies.get(i).hit(1000000);
					}
				}
				
			}
			
			
		}
		
		//set tilemap position
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getX(),
				GamePanel.HEIGHT / 2 - player.getY());
		
		//set background position
		bg.setPosition(tileMap.getX(), tileMap.getY());
		
		//check attacks and collisions
		player.checkAttack(enemies);
		player.checkStaticObjects(objects);
		player.checkItems(items);
		player.checkProjectiles(projectiles);
		
		//update enemies
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()){
				Coin c = new Coin(tileMap, res, player);
				c.setPosition(e.getX(), e.getY());
				items.add(c);
				Item item = e.getLoot();
				if(e.getLoot() != null){
					item.setPosition(e.getX(), e.getY());
					items.add(item);
				}
				enemies.remove(i);
				i--;		
				explosions.add(new Explosion(e.getX(), e.getY()));
				explosionSound.play();
				
			}
		}
		
		//update items
		for(int i = 0; i < items.size(); i++){
			Item item = items.get(i);
			item.update();
			if(item.getInInv()){
				addToInv(i);
				i--;
			}else if(item.shouldRemove()){
				items.remove(i);
				i--;
			}
		}
		
		//update static objects
		for(int i = 0; i < objects.size(); i++){
			StaticObject object = objects.get(i);
			object.update();
			if(object.isActivated()){
				if(object.getKey() == "chest"){
					if(!object.getTrapped()){
						Item item = randomItem.getChestItem();
						item.setPosition(object.getX(), object.getY());
						items.add(item);
					}
				}else if(object.getKey() == "door"){
					stopLevel(NEXTSTATE);
				}
			}
		}
		
		//update explosions
		for(int i = 0; i < explosions.size(); i++){
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()){
				explosions.remove(i);
				i--;
			}
		}
		
		//update projectiles
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).update();
			if(projectiles.get(i).shouldRemove()){
				projectiles.remove(i);
				i--;
			}
		}
		
		//update the level text
		lvltext.update();
	}
	
	public void stopLevel(int state){
		//stops the level and saves some stats for the next level
		music.stop();
		gsm.saveStats(hud, player);
		gsm.setState(state);
	}

	public void draw(Graphics2D g) {
		//draw Background
		bg.draw(g);
		//draw tilemap
		tileMap.draw(g);
		
		//draw static objects
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).draw(g);
		}
		
		//draw items
		for(int i = 0; i < items.size(); i++){
			items.get(i).draw(g);
		}
		
		//draw Enemies
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
		
		
		//draw Explosions
		for(int i = 0; i < explosions.size(); i++){
			explosions.get(i).setMapPosition((int)tileMap.getX(), (int)tileMap.getY());
			explosions.get(i).draw(g);
		}
		
		//draw Projectiles
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).draw(g);
		}
		
		//draw Player
		player.draw(g);

		//draw hud
		hud.draw(g);
		g.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		if(player.isDead()){
			g.drawString("Game over...", 105, 100);
			g.drawString("But luckily for you,", 75, 120);
			g.drawString("you can try again!", 78, 140);

		}	
		//draw level text
		lvltext.draw(g);

	}

	public void keyPressed(int k) {
		//Keyboard control
		if(k == KeyEvent.VK_LEFT)
			player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if(k == KeyEvent.VK_UP)
			player.setJumping(true);
		if(k == KeyEvent.VK_DOWN)
			player.setDown(true);
		if(k == KeyEvent.VK_CONTROL)
			player.attack(hud.getAttack());
		if(k == KeyEvent.VK_SHIFT)
			hud.cycleUp();
		if(k == KeyEvent.VK_C)
			player.setUsing(true);
		if(k == KeyEvent.VK_Z)
			player.setPicking(true);
		if(k == KeyEvent.VK_1)
			hud.inv.setSlot(0);
		if(k == KeyEvent.VK_2)
			hud.inv.setSlot(1);
		if(k == KeyEvent.VK_3)
			hud.inv.setSlot(2);
		if(k == KeyEvent.VK_4)
			hud.inv.setSlot(3);
		if(k == KeyEvent.VK_5)
			hud.inv.setSlot(4);
		if(k == KeyEvent.VK_6)
			hud.inv.setSlot(5);
		if(k == KeyEvent.VK_7)
			hud.inv.setSlot(6);
		if(k == KeyEvent.VK_8)
			hud.inv.setSlot(7);
		if(k == KeyEvent.VK_9)
			hud.inv.setSlot(8);
		if(k == KeyEvent.VK_X)
			hud.inv.useItem();
		if(k == KeyEvent.VK_V)
			hud.inv.throwItem(items, player);
		
		
	}

	public void keyReleased(int k) {
		//Keyboard control
		if(k == KeyEvent.VK_LEFT)
			player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT)
			player.setRight(false);
		if(k == KeyEvent.VK_UP)
			player.setUp(false);
		if(k == KeyEvent.VK_DOWN)
			player.setDown(false);
		if(k == KeyEvent.VK_SPACE)
			player.setJumping(false);
		if(k == KeyEvent.VK_C)
			player.setUsing(false);
		if(k == KeyEvent.VK_Z)
			player.setPicking(false);
	}

}
