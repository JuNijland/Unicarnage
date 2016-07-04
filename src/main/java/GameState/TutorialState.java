package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Player;
import Entity.Enemies.Enemy;
import Entity.Enemies.Penguin;
import Entity.Enemies.Snail;
import HUDs.HUD;
import Items.Horn;
import Items.RandomItem;
import Object.Chest;
import Object.Door;
import TileMap.Background;
import TileMap.TileMap;


public class TutorialState extends LevelState{	

	public TutorialState(GameStateManager gsm){
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
		tileMap.loadMap("/Maps/tutorial.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.08);
		
		//Player
		player = new Player(tileMap, res);
		player.setPosition(100, 400);
		
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
			new Point(650, 200)
		};
					
		for(int i = 0; i < points.length; i++){
			s = new Snail(tileMap, res, player);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}	
		
		/*Bear b = new Bear(tileMap, res, player);
		b.setPosition(700, 200);
		b.setLoot(new Pepper(tileMap, res, player));
		enemies.add(b);*/
		
		/*Snowman sn = new Snowman(tileMap, res, player, projectiles);
		sn.setPosition(700, 200);
		enemies.add(sn)*/;
		
		/*Polarbear b = new Polarbear(tileMap, res, player);
		b.setPosition(700, 200);
		b.setLoot(new BasicShoes(tileMap, res, player));
		enemies.add(b);*/
		
		Penguin pe = new Penguin(tileMap, res, player);
		pe.setPosition(700, 200);
		enemies.add(pe);
	}
	
	private void initObjects(){
		Chest chest = new Chest(tileMap, res);
		chest.setPosition(50, 210);
		objects.add(chest);
		
		Door door = new Door(tileMap, res);
		door.setPosition(850, 386);
		objects.add(door);
	}
	
	public void update(){
		super.update();
	}
	
	public void draw(Graphics2D g){
		super.draw(g);
	}
	
}
