package Items;

import java.awt.Graphics2D;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class RabbitsFoot extends Item{

	public RabbitsFoot(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 15;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		sprite = res.getSpritesheet("items").getSubimage(60, 20, width, height);
		
		key = "Rabbits foot";
		useString = "+Luck";
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
