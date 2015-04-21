package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entity.Player;
import TileMap.TileMap;
import Utility.ResourceLoader;

public class Anvil extends Item{

	public Anvil(TileMap tm, ResourceLoader res, Player p) {
		super(tm, res, p);
		
		width = 20;
		height = 20;
		cwidth = 20;
		cheight = 15;
		fallSpeed = 0.3;
		maxFallSpeed = 1.0;
		
		eatable = true;

		sprite = res.getSpritesheet("items").getSubimage(40, 0, width, height);
		
		key = "Anvil";
		useString = "Fatass";
		
	}
	
	public void use(){
		player.addFallSpeed(0.03);
		player.addMaxFallSpeed(1.2);
	}
	
	public void update(){
		super.update();
	}
	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
	}
	
	

}
