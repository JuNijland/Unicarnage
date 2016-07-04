package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Player;
import Entity.Enemies.Enemy;
import Entity.Enemies.Penguin;
import Entity.Enemies.Polarbear;
import Entity.Enemies.Snail;
import Entity.Enemies.Snowman;
import HUDs.HUD;
import Items.BasicShoes;
import Items.Horn;
import Items.Milk;
import Items.RandomItem;
import Object.Chest;
import Object.Door;
import TileMap.Background;
import TileMap.TileMap;


public class TestLevelState extends LevelState{	

	public TestLevelState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	
	public void init() {
		super.init();
		
		NEXTSTATE = 2;
		//Music
		music = res.getMusic("track1");
		music.play();
		
		//TileMap loading
		tileMap = new TileMap(32);
		tileMap.loadTiles(res.getSpritesheet("grasstileset"));
		tileMap.loadMap("/Maps/level1-2.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.08);
		
		//Player
		player = new Player(tileMap, res);
		player.setPosition(100, 800);
		player.addMaxHealth(50);
		player.addHealth(50);
		
		//Background image
		bg = new Background(res.getSpritesheet("grassbg"), 0.1);
		
		hud = new HUD(player, res, lvltext);
		Horn firstAttack = new Horn(tileMap, res, player);
		hud.addAttack(firstAttack);
		
		randomItem = new RandomItem(tileMap, res, player);
		
		initEnemies();
		initObjects();
		
	}
	
	private void initEnemies(){
		enemies = new ArrayList<Enemy>();
		
		Snail s;
		Point[] points = new Point[]{
			new Point(200, 800),
			new Point(550, 800),
			new Point(1100, 700),
			new Point(1500, 800),
			new Point(1800, 800)
		};
					
		for(int i = 0; i < points.length; i++){
			s = new Snail(tileMap, res, player);
			s.setPosition(points[i].x, points[i].y);
			s.setLoot(new Milk(tileMap, res, player));
			enemies.add(s);
		}	

	}
	
	private void initObjects(){
		Chest chest = new Chest(tileMap, res);
		chest.setPosition(350, 210 + 18*32);
		objects.add(chest);	
		chest = new Chest(tileMap, res);
		chest.setPosition(350 + 14*32, 210 + 11*32);
		objects.add(chest);
		
		Door door = new Door(tileMap, res);
		door.setPosition(2850, 67 + 22*32);
		objects.add(door);
	}
	
	public void update(){
		super.update();
	}
	
	public void draw(Graphics2D g){
		super.draw(g);
	}
	
}
