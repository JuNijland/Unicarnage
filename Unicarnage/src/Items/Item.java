package Items;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entity.Player;
import Main.GamePanel;
import TileMap.Tile;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public abstract class Item {
	
	//tilemap
	protected TileMap tileMap;
	protected int tileSize;
	//gravity
	protected double fallSpeed;
	protected double maxFallSpeed;
	
	protected BufferedImage sprite;
	
	//inventory
	protected boolean inInv;
	protected boolean inHUD;
	protected int invSlot;
	
	protected boolean remove;
	
	//player for item effects
	protected Player player;
	
	//mapposition
	protected double x, y;
	protected double xmap, ymap;
	
	//dimensions
	protected int width;
	protected int height;
	
	//collision box
	protected int cwidth;
	protected int cheight;
	
	//tilemapcollision stuff
	protected boolean topLeft, topRight, topUp, bottomLeft, bottomRight, bottomDown;
	protected int currRow;
	protected int currCol;
	protected double ydest;
	protected double ytemp;
	protected double dy;
	protected boolean falling;
	
	protected boolean eatable;
	protected boolean attack;
	
	//Image Loader
	protected ResourceLoader res;
	
	protected String key;
	protected String useString;
	
	public Item(TileMap tm, ResourceLoader res, Player p){
		player = p;
		tileMap = tm;
		tileSize = tm.getTileSize();
		this.res = res;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x - cwidth/2, (int)y - cheight / 2, cwidth, cheight);
	}
	
	public void calculateCorners(double x, double y) {
		int leftTile = (int)(x - cwidth / 2) / tileSize;
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;
		int topTile = (int)(y - cheight / 2) / tileSize;
		int bottomTile = (int)(y + cheight / 2 - 1) / tileSize;
		
		if(topTile < 0 || bottomTile >= tileMap.getNumRows() ||
                leftTile < 0 || rightTile >= tileMap.getNumCols()) {
                topLeft = topRight = bottomLeft = bottomRight = bottomDown = false;
                return;
        }
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int tu = tileMap.getType(topTile, rightTile - 1);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		int bd = tileMap.getType(bottomTile, leftTile + 1);
		
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		topUp = tu == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
		bottomDown = bd == Tile.BLOCKED;
	}
	
	public void checkTileMapCollision(){
		
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		
		ydest = y + dy;
		
		ytemp = y;
		
		calculateCorners(x, ydest);
		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				ytemp = currRow * tileSize + cheight / 2;
			}else{
				ytemp += dy;
			}
		}
		if(dy > 0) {
			if(bottomLeft || bottomRight || bottomDown) {
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * tileSize - cheight / 2 ;
			}else{
				ytemp += dy;
			}
		}
		if(!falling){
			calculateCorners(x, ydest + 1);
			if(!bottomLeft && !bottomRight){
				falling = true;
			}
		}
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public String getKey(){
		return key;
	}
	
	public String getUseString(){
		return useString;
	}
	
	public boolean notOnScreen(){
		return x + xmap + width < 0 ||
				x + xmap - width > GamePanel.WIDTH ||
				y + ymap + height < 0 ||
				y + ymap - height > GamePanel.HEIGHT;
	}
	
	public void setMapPosition(){
		xmap = tileMap.getX();
		ymap = tileMap.getY();
	}
	
	
	public void use(){
		
	}
	
	public void setInvSlot(int i){
		invSlot = i;
	}
	
	public void setInInv(boolean b){
		inInv = b;
	}
	
	public void setInHUD(boolean b){
		inHUD = b;
	}
	
	public boolean isAttack(){
		return attack;
	}
	
	public boolean getInInv(){
		return inInv;
	}
	
	private void getNextPosition(){
		
		if(falling){
			dy += fallSpeed;
		}
		
	}
	
	public boolean getEatable(){
		return eatable;
	}
	
	public boolean shouldRemove(){
		return remove;
	}
	
	public void update(){
		getNextPosition();
		checkTileMapCollision();
		setPosition(x,ytemp);
	}
	
	public void draw(Graphics2D g){
		if(inInv){
			g.drawImage(sprite, 61 + invSlot * 22, 219, null);
		}else if(inHUD){
			g.drawImage(sprite, 150, 0, null);
		}else{
			g.drawImage(sprite, (int)(x + xmap - width / 2), (int)(y + ymap - height /2), null);
		}
	}
	

}
