package HUDs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Audio.Sound;
import Entity.Player;
import Items.Item;
import Utility.ImageLoader;
import Utility.LevelText;
import Utility.ResourceLoader;

public class Inventory {
	
	Player p;
	BufferedImage image;
	Font font;
	Item[] items;
	private int coins;
	private int selSlot;
	private LevelText lvltext;
	private ResourceLoader res;
	
	private Sound eatingSound;
	private Sound equipSound;
	private Sound pickupSound;
	
	private ArrayList<Item> attacks;
	
	public Inventory(Player p, ResourceLoader res, ArrayList<Item> attacks, LevelText lvltext){
		this.p = p;
		this.attacks = attacks;
		this.lvltext = lvltext;
		this.res = res;
		eatingSound = res.getSound("eat");
		equipSound = res.getSound("equip");
		pickupSound = res.getSound("pickup");
		
		coins = 0;
		selSlot = 0;
		//ITEM ARRAYLIST
		items = new Item[9];
		
		font = new Font("Arial", Font.PLAIN, 12);
		try{
			image = res.getSpritesheet("inventory");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean addItem(Item item){
		pickupSound.play();
		if(item.getKey() == "Coin"){
			coins++;
			return true;		
		}else{
			item.setInInv(true);
			lvltext.displayText(item.getKey());
			if(items[selSlot] == null){
				item.setInvSlot(selSlot);
				items[selSlot] = item;
				return true;
			}
			for(int i = 0; i < items.length; i++){
				if(items[i] == null){
					item.setInvSlot(i);
					items[i] = item;
					return true;
				}
			}
		}
		
		
		return false;
	
	}
	
	public void useItem(){
		if(items[selSlot] != null){
			if(items[selSlot].getUseString() != null) lvltext.displayText(items[selSlot].getUseString());
			if(items[selSlot].isAttack()){
				items[selSlot].setInInv(false);
				items[selSlot].setInHUD(true);
				attacks.add(items[selSlot]);
			}else{
				items[selSlot].use();
			}
			if(items[selSlot].getEatable()){
				eatingSound.play();
			}else{
				equipSound.play();
			}
			items[selSlot] = null;
		}	
	}
	
	public void throwItem(ArrayList<Item> itemslist, Player p){
		if(items[selSlot] != null){
			Item item = items[selSlot];
			item.setPosition(p.getX(), p.getY());
			item.setInInv(false);
			itemslist.add(item);
			items[selSlot] = null;
		}
	
	}
	
	public void updateLevel(Player p, ResourceLoader res, ArrayList<Item> attacks, LevelText lvltext){
		this.p = p;
		this.res = res;
		this.attacks = attacks;
		this.lvltext = lvltext;
	}
	
	public void setSlot(int i){
		selSlot = i;
	}
	
			
	public void draw(Graphics2D g){
		font = new Font("Arial", Font.PLAIN, 12);
		g.setFont(font);
		g.drawImage(image, 10, 215, null);
		g.setColor(Color.WHITE);
		for(int i =1; i < 10; i++){
			g.drawString(new String(""+i), 46 + (i * 22), 233);
			
		}
		for(int i = 0; i < items.length; i++){ 
			if(items[i] != null)
				items[i].draw(g); 
		}
		g.setColor(Color.BLACK);
		font = new Font("Arial", Font.BOLD, 12);
		g.setFont(font);
		g.drawRect(62 + selSlot * 22, 218, 19, 19);
		g.drawString(coins + "x ", 5, 16);
		g.drawImage(res.getSpritesheet("items").getSubimage(20, 40, 20, 20), 13, 0, null);
	}

}
