package HUDs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entity.Player;
import Items.Item;
import Utility.ImageLoader;
import Utility.LevelText;
import Utility.ResourceLoader;

public class HUD {
	
	private Player player;
	private BufferedImage image;
	private Font font;
	private ArrayList<Item> attacks;
	private int currentAttack;
	
	public Inventory inv;
	
	public HUD(Player p, ResourceLoader res, LevelText lvltext){
		player = p;
		attacks = new ArrayList<Item>();
		inv = new Inventory(p, res, attacks, lvltext);
		try{
			image = res.getSpritesheet("hud");
			
			font = new Font("Arial", Font.PLAIN, 8);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public double healthPercent(){
		return ((double)player.getHealth() / (double)player.getMaxHealth());
	}
	
	public double manaPercent(){
		return ((double)player.getMana() / (double)player.getMaxMana());
	}
	
	public void cycleUp(){
		currentAttack++;
		if(currentAttack > attacks.size() - 1){
			currentAttack = 0;
		}
	}
	
	public void updateLevel(Player p, ResourceLoader res, LevelText lvltext){
		player = p;
		inv.updateLevel(p, res, attacks, lvltext);
	}
	
	public Item getAttack(){
		return attacks.get(currentAttack);
	}
	
	public void addAttack(Item i){
		if(i.isAttack()){
			i.setInHUD(true);
			attacks.add(i);
		}
	}

	
	public void draw(Graphics2D g){
		g.drawImage(image, 10, 0, null);
		g.setFont(font);
		g.setColor(new Color(211, 40, 40));
		g.fillRect((int)(100 +(1.0 - healthPercent()) * 50.0), 1, (int)(healthPercent() * 50.0), 8);
		g.setColor(new Color(60, 77, 201));
		g.fillRect(170, 1, (int)(manaPercent() * 50.0), 8);
		g.setColor(Color.WHITE);
		g.drawString(player.getHealth() + " / " + player.getMaxHealth(), 118, 8);
		g.drawString(player.getMana()/100 + " / " + player.getMaxMana()/100, 185, 8);
		if(attacks.size() > 0){
			attacks.get(currentAttack).draw(g);
		}
		inv.draw(g);
	}
	

}
