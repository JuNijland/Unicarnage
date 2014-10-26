package Entity.Enemies;

import Audio.Sound;
import Entity.MapObject;
import Entity.Player;
import Items.Item;
import TileMap.TileMap;
import Utility.ResourceLoader;

public class Enemy extends MapObject{
	
	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;
	
	protected String key;
	
	protected Sound hitsound;
	
	protected boolean flinching;
	protected long flinchTimer;
	
	protected Player player;
	
	protected Item loot;
	
	public Enemy(TileMap tm, ResourceLoader res, Player p){
		super(tm, res);
		player = p;
		hitsound = res.getSound("hit");
	}
	
	public boolean isDead() { return dead; }
	
	public int getDamage() { return damage; }
	
	public void hit(int damage) {
		if(!flinching)
			hitsound.play();
		if(dead || flinching) 
			return;
		health -= damage;
		if(health < 0) 
			health = 0;
		if(health == 0) 
			dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	public String getKey(){
		return key;
	}
	
	public void update(){}
	
	public Item getLoot(){
		return loot;
	}
	
	public void setLoot(Item item){
		loot = item;
	}

}
