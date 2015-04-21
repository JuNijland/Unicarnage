package Items;

import java.util.Random;

import Entity.Player;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class RandomItem {
	
	private TileMap tileMap;
	private Player player;
	private ResourceLoader res;
	private Random rand;
	
	private Item[] chestItems;
	
	public RandomItem(TileMap tm, ResourceLoader res, Player p){
		tileMap = tm;
		player = p;
		this.res = res;
		rand = new Random();
		
		chestItems = new Item[9];
		
	}
	
	public Item getChestItem(){
		chestItems[0] = new Anvil(tileMap, res, player);
		chestItems[1] = new BadMeat(tileMap, res, player);
		chestItems[2] = new BasicShoes(tileMap, res, player);
		chestItems[3] = new Cookie(tileMap, res, player);
		chestItems[4] = new Feather(tileMap, res, player);
		chestItems[5] = new Pepper(tileMap, res, player);
		chestItems[6] = new Milk(tileMap, res, player);
		chestItems[7] = new RabbitsFoot(tileMap, res, player);
		chestItems[8] = new Table(tileMap, res, player);
		return chestItems[rand.nextInt(9)];
	}

}
