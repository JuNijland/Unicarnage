package Entity.Objects;

import java.awt.Graphics2D;

import Entity.MapObject;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Gravestone extends MapObject{

	public Gravestone(TileMap tm, ResourceLoader res) {
		super(tm, res);
		
		width = 30;
		height = 30;
		cwidth = 40;
		cheight = 25;
		
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		
		spritesheet = res.getSpritesheet("gravestone");

	}
	
	public void getNextPosition(){
		if(falling){
			dy += fallSpeed;
			if(dy > maxFallSpeed){
				dy = maxFallSpeed;
			}
		}
	}
	
	public void update(){
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
	}
	
	public void draw(Graphics2D g){
		g.drawImage(spritesheet, (int)(x + xmap - width / 2), (int)(y + ymap - height /2), null);
	}

}
