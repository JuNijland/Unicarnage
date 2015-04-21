package Utility;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Audio.Music;
import Audio.Sound;

public class ResourceLoader {
	
	public HashMap<String, BufferedImage> spritesheets;
	public HashMap<String, Sound> sounds;
	public HashMap<String, Music> music;
	
	public ResourceLoader(){
		
		/* spritesheets */
		try{
			spritesheets = new HashMap<String, BufferedImage>();
			spritesheets.put("pukeball", ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/pukeball.gif")));
			spritesheets.put("snail", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/snail.gif")));
			spritesheets.put("explosion", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/explosion.gif")));
			spritesheets.put("player", ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/player.gif")));
			spritesheets.put("grasstileset", ImageIO.read(getClass().getResourceAsStream("/Tilesets/grasstileset2.gif")));
			spritesheets.put("snowtileset", ImageIO.read(getClass().getResourceAsStream("/Tilesets/snowtileset.gif")));
			spritesheets.put("grassbg", ImageIO.read(getClass().getResourceAsStream("/Backgrounds/bg_level1.gif")));
			spritesheets.put("snowbg", ImageIO.read(getClass().getResourceAsStream("/Backgrounds/bgsnow.gif")));
			spritesheets.put("chest", ImageIO.read(getClass().getResourceAsStream("/Sprites/Objects/chest.gif")));
			spritesheets.put("door", ImageIO.read(getClass().getResourceAsStream("/Sprites/Objects/door.gif")));
			spritesheets.put("angryplant", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/angryplant.gif")));
			spritesheets.put("plantprojectile", ImageIO.read(getClass().getResourceAsStream("/Sprites/Projectiles/plantprojectile.gif")));
			spritesheets.put("items", ImageIO.read(getClass().getResourceAsStream("/Sprites/Items/items.gif")));
			spritesheets.put("bird", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/bird.gif")));
			spritesheets.put("fireattack", ImageIO.read(getClass().getResourceAsStream("/Sprites/Projectiles/fireattack.gif")));
			spritesheets.put("leveltext", ImageIO.read(getClass().getResourceAsStream("/HUD/leveltext.gif")));
			spritesheets.put("inventory", ImageIO.read(getClass().getResourceAsStream("/HUD/inventory.gif")));
			spritesheets.put("hud", ImageIO.read(getClass().getResourceAsStream("/HUD/hud.gif")));
			spritesheets.put("bear", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/bear.gif")));
			spritesheets.put("polarbear", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/polarbear.gif")));
			spritesheets.put("pinguin", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/penguin.gif")));
			spritesheets.put("snowman", ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/snowman.gif")));
			spritesheets.put("snowball", ImageIO.read(getClass().getResourceAsStream("/Sprites/Projectiles/snowball.gif")));
			spritesheets.put("testspriteset", ImageIO.read(getClass().getResourceAsStream("/Tilesets/test.gif")));
			spritesheets.put("dead", ImageIO.read(getClass().getResourceAsStream("/Foregrounds/dead.gif")));

		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			sounds = new HashMap<String, Sound>();
			sounds.put("eat", new Sound("eating.wav"));
			sounds.put("jump", new Sound("jump.wav"));
			sounds.put("attack", new Sound("attack.wav"));
			sounds.put("damage", new Sound("damage.wav"));
			sounds.put("pickup", new Sound("pickup.wav"));		
			sounds.put("dead", new Sound("dead.wav"));		
			sounds.put("hit", new Sound("hit.wav"));		
			sounds.put("explosion", new Sound("explosion.wav"));	
			sounds.put("fire", new Sound("fire.wav"));
			sounds.put("menu", new Sound("menu.wav"));
			sounds.put("equip", new Sound("equip.wav"));



		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			music = new HashMap<String, Music>();
			music.put("track1", new Music("track1.wav"));
			music.put("track2", new Music("snowmusic.wav"));

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSpritesheet(String key){
		return spritesheets.get(key);
	}
	
	public Sound getSound(String key){
		return sounds.get(key);
	}
	
	public Music getMusic(String key){
		return music.get(key);
	}
	
}
