package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class BasicShoes extends Item{

	public BasicShoes(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 15;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		sprite = res.getSpritesheet("items").getSubimage(20, 0, width, height);
		
		key = "Shoes";
		useString = "+Speed";
	}
	
	public void use(){
		player.addMaxSpeed(0.2);
		player.addSpeed(0.05);
	}
	
	public void update(){
		super.update();
	}
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}

}
