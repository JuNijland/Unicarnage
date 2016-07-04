package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Animation.Animation;
import Main.GamePanel;
import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public abstract class StaticObject{
	
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	//defines the name of the object
	protected String key;
	
	//posistion
	protected double x;
	protected double y;
	
	//dimensions
	protected int width;
	protected int height;
	
	//collision box
	protected int cwidth;
	protected int cheight;
	
	//currently used
	protected boolean activated;
	protected boolean hasActivated;
	protected boolean trapped;
	protected boolean done;
		
	
	//animation
	protected Animation animation;
	protected int currentState;
	
	//sprite
	protected BufferedImage spritesheet;
	protected BufferedImage[] sprites;
	protected BufferedImage[] activationSprites;
	
	//ResourceLoader
	protected ResourceLoader res;
	
	public StaticObject(TileMap tm, ResourceLoader res){
		tileMap = tm;
		tileSize = tm.getTileSize();
		this.res = res;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x - cwidth/2, (int)y - cheight / 2, cwidth, cheight);
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public String getKey(){
		return key;
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
	
	public int getX(){
		return (int) x;
	}
	
	public int getY(){
		return (int) y;
	}
	
	public boolean setActive(){
		if(!hasActivated){
			activated = true;
			return true;
		}
		return false;
	}
	
	public boolean isActivated(){
		if(activated){
			if(!done){
				done = true;
				return activated;
			}
		}	
		return false;
	}
	
	public boolean getTrapped(){
		return trapped;
	}
	
	public void setTrapped(){
		trapped = true;
	}
	
	public void update(){}
	
	public void draw(Graphics2D g){
		g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height /2), null);
	}
	


}
