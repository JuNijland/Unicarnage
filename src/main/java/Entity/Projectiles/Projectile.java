package Entity.Projectiles;

import java.awt.image.BufferedImage;

import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;
import Entity.MapObject;

public class Projectile extends MapObject{
	
	protected boolean hit;
	protected boolean remove;
	protected BufferedImage[] sprites;
	protected BufferedImage[] hitSprites;
	
	protected boolean hostile;
	
	//defines the kind of projectile
	protected String key;

	public Projectile(TileMap tm, ResourceLoader res) {
		super(tm, res);
	}
	
	public String getKey(){
		return key;
	}
	
	public void setHit(){
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		dx = 0;
	}
	
	public boolean isHostile(){
		return hostile;
	}
	
	public boolean shouldRemove(){
		return remove;
	}
	
	public void update(){}

}
