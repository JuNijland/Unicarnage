package Items;

import java.awt.Graphics2D;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Table extends Item{

	public Table(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 15;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		sprite = res.getSpritesheet("items").getSubimage(0, 40, width, height);
		
		key = "Table";
	}
	
	public void use(){
		player.setPosition(player.getX(), player.getY() - 20);
	}
	
	public void update(){
		super.update();
	}
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}

}
