package Items;

import java.awt.Graphics2D;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Milk extends Item{

	public Milk(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 15;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		eatable = true;
		
		key = "Milk";
		useString = "+Max HP";
		
		sprite = res.getSpritesheet("items").getSubimage(40, 20, width, height);
	}
	
	public void use(){
		player.addMaxHealth(1);
		player.addHealth(1);
	}
	
	public void update(){
		super.update();
	}
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}

}
