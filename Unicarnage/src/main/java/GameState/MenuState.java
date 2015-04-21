package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Audio.Sound;
import TileMap.Background;
import Utility.ResourceLoader;

public class MenuState extends GameState{
	
	private Background bg;
	private Sound selectSound;
	
	private int currentChoice = 0;
	private String[] options = {
			"Start",
			"Controls",
			"Quit"
	};
	private Color titleColor;
	private Font titleFont;
	private ResourceLoader res;
	
	private Font font;
	
	public MenuState(GameStateManager gsm){
		this.gsm = gsm;
		this.res = gsm.res;
		selectSound = res.getSound("menu");
		try{
			BufferedImage backgroundImg = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/menubg.gif"));
			bg = new Background(backgroundImg, 1);
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			
			font = new Font("Arial", Font.PLAIN, 12);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init(){
		
	}
	
	public void update(){
		bg.update();
	}
	
	public void draw(Graphics2D g){
		bg.draw(g);
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("UniCarnage", 85, 70);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}else{
				g.setColor(Color.LIGHT_GRAY);
			}
			g.drawString(options[i], 145, 140 + i * 15);
		}
	}
	
	private void select() {
		if(currentChoice == 0){
			gsm.setState(GameStateManager.TESTSTATE);
		}
		if(currentChoice == 1){
			gsm.setState(GameStateManager.CONTROLSSTATE);
		}
		if(currentChoice == 2){
			System.exit(0);
		}
	}
	
	public void keyPressed(int k){
		if(k == KeyEvent.VK_ENTER){
			select();
			selectSound.play();
		}
		if(k == KeyEvent.VK_UP){
			//up
			
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
			selectSound.play();
		}
		if(k == KeyEvent.VK_DOWN){
			//down
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
			selectSound.play();
		}
	}
	
	public void keyReleased(int k){
		
	}
	

}
