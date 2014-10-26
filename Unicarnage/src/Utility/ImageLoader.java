package Utility;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public HashMap<String, BufferedImage> spritesheets;
	
	public ImageLoader(){
	// load sprites
			try{
				spritesheets = new HashMap<String, BufferedImage>();
				spritesheets.put("pukeball", ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/pukeball.gif")));
				spritesheets.put("snail", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/snail.gif")));
				spritesheets.put("explosion", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/explosion.gif")));
				spritesheets.put("player", ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/player.gif")));
				spritesheets.put("grasstileset", ImageIO.read(getClass().getResourceAsStream("/Tilesets/grasstileset2.gif")));
				spritesheets.put("level1bg", ImageIO.read(getClass().getResourceAsStream("/Backgrounds/bg_level1.gif")));
				spritesheets.put("chest", ImageIO.read(getClass().getResourceAsStream("/Sprites/Objects/chest.gif")));
				spritesheets.put("door", ImageIO.read(getClass().getResourceAsStream("/Sprites/Objects/door.gif")));
				spritesheets.put("angryplant", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/angryplant.gif")));
				spritesheets.put("plantprojectile", ImageIO.read(getClass().getResourceAsStream("/Sprites/Projectiles/plantprojectile.gif")));
				spritesheets.put("items", ImageIO.read(getClass().getResourceAsStream("/Sprites/Items/items.gif")));
				spritesheets.put("bird", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/bird.gif")));
				spritesheets.put("fireattack", ImageIO.read(getClass().getResourceAsStream("/Sprites/Projectiles/fireattack.gif")));
				spritesheets.put("leveltext", ImageIO.read(getClass().getResourceAsStream("/HUD/leveltext.gif")));
				spritesheets.put("bear", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/bear.gif")));
				spritesheets.put("testspriteset", ImageIO.read(getClass().getResourceAsStream("/Tilesets/test.gif")));

			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public BufferedImage getSpritesheet(String key){
		return spritesheets.get(key);
	}

}
