package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import TileMap.TileMap;
import Utility.ImageLoader;
import Utility.ResourceLoader;

public class Text extends MapObject{
	
	private String s;

	public Text(TileMap tm, ResourceLoader res, String s) {
		super(tm, res);
		this.s = s;
		
		
	}
	
	public void draw(Graphics2D g){
		setMapPosition();
		Font titleFont = new Font("Century Gothic", Font.PLAIN, 28);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString(s, (int) x, (int) y);
	}

}
