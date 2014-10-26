package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Cookie extends Item{

	public Cookie(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 15;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		eatable = true;
		
		key = "Cookie";
		
		sprite = res.getSpritesheet("items").getSubimage(0, 0, width, height);
	}
	
	public void use(){
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
