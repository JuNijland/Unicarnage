package Items;

import java.awt.Graphics2D;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Horn extends Item{
	
	public Horn(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 16;
		cheight = 16;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		key = "Horn";
		useString = "New Attack!";
		
		attack = true;
		
		sprite = res.getSpritesheet("items").getSubimage(0, 20, width, height);
	}
	
	public void use(){
		
	}
	
	public void update(){
		super.update();
	}
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}

}
