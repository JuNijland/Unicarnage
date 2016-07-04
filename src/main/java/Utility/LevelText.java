package Utility;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelText {
	
	private ResourceLoader res;
	private ArrayList<String> textBuffer;
	private boolean drawing;
	private int count;
	private BufferedImage img;
	
	private Font font;
	
	public LevelText(ResourceLoader res){
		this.res = res;
		img = res.getSpritesheet("leveltext");
		
		textBuffer = new ArrayList<String>();
		font = new Font("Arial", Font.BOLD, 12);
	}
	
	public void displayText(String txt){
		textBuffer.add(txt);
	}
	
	public void update(){
		if(!textBuffer.isEmpty()){
			drawing = true;
			count++;
			if(count > 200){
				textBuffer.remove(0);
				count = 0;
				drawing = false;
			}
		}else{
			drawing = false;
		}
		
	}
	
	public void draw(Graphics2D g){
		int x = 320;
		int y = 30;
		g.setFont(font);
		if(drawing){
			if(count < 25){
				x = 320 - count * 4;
			}else if(count >= 25 && count <= 175){
				x = 220;
			}else if(count > 175){
				x = 220 + (count - 175) * 4;
			}
			g.drawImage(img, x, y - 15, null);
			if(count >= 25 && count <=175){
				g.drawString(textBuffer.get(0), x + 12, y);
			}
			
		}
	}

}
