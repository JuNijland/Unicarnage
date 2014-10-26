package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Coin extends Item{

	public Coin(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 15;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		eatable = false;
		
		key = "Coin";
		
		sprite = res.getSpritesheet("items").getSubimage(20, 40, width, height);
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
