package Items;

import java.awt.Graphics2D;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Pepper extends Item{

	public Pepper(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 15;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		key = "Pepper";
		useString = "New Attack!";
		
		attack = true;
		eatable = true;
		
		sprite = res.getSpritesheet("items").getSubimage(20, 20, width, height);
	}
	
	public void update(){
		super.update();
	}
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}

}
