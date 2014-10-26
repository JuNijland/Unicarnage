package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import Entity.Player;
import Entity.Enemies.AngryPlant;
import Entity.Enemies.Bird;
import Entity.Enemies.Enemy;
import Entity.Enemies.Snail;
import HUDs.HUD;
import Items.Horn;
import Items.RandomItem;
import Object.Chest;
import Object.Door;
import TileMap.Background;
import TileMap.TileMap;


public class Level1State extends LevelState{	

	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	
	public void init() {
		super.init();
		
		NEXTSTATE = 0;
		//Music
		music = res.getMusic("track1");
		music.play();
		
		// TileMap loading
		tileMap = new TileMap(32);
		tileMap.loadTiles(res.getSpritesheet("grasstileset"));
		tileMap.loadMap("/Maps/level1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.08);
		
		//Player
		player = gsm.player;
		player.setPosition(100, 100);
		player.updateLevel(tileMap, res);
		player.setUsing(false);
		
		//Background image
		bg = new Background(res.getSpritesheet("grassbg"), 0.1);
		
		hud = new HUD(player, res, lvltext);
		Horn firstAttack = new Horn(tileMap, res, player);
		hud.addAttack(firstAttack);
		
		randomItem = new RandomItem(tileMap, res, player);
		
		initEnemies();
		initObjects();
		
	}
	
	private void initEnemies() {
		enemies = new ArrayList<Enemy>();

		Snail s;
		Point[] points = new Point[] { new Point(200, 100),
				new Point(860, 200), new Point(1525, 200), new Point(70, 250),
				new Point(760, 350) };

		for (int i = 0; i < points.length; i++) {
			s = new Snail(tileMap, res, player);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
		AngryPlant ap = new AngryPlant(tileMap, res, player, projectiles);
		ap.setPosition(689, 400);
		ap.setSide(3);
		enemies.add(ap);
		ap = new AngryPlant(tileMap, res, player, projectiles);
		ap.setPosition(975, 360);
		ap.setSide(2);
		enemies.add(ap);

		Bird b = new Bird(tileMap, res, player);
		b.setPosition(600, 300);
		b.setPatrol(600, 800);
		enemies.add(b);
		b = new Bird(tileMap, res, player);
		b.setPosition(1500, 170);
		b.setPatrol(1500, 1700);
		enemies.add(b);
	}

	private void initObjects() {
		Chest chest = new Chest(tileMap, res);
		chest.setPosition(317, 180);
		objects.add(chest);
		chest = new Chest(tileMap, res);
		chest.setPosition(1508, 242);
		chest.setTrapped();
		objects.add(chest);

		Door door = new Door(tileMap, res);
		door.setPosition(1786, 420);
		objects.add(door);
	}
	
	public void update(){
		super.update();
	}
	
	public void draw(Graphics2D g){
		super.draw(g);
	}
	
}
