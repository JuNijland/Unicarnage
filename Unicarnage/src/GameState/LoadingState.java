package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class LoadingState extends GameState{

	private Font font;
	private boolean first = true;
	private int forwardState;
	
	public LoadingState(GameStateManager gsm, int gameState){
		this.gsm = gsm;
		this.forwardState = gameState;
			
		font = new Font("Arial", Font.PLAIN, 12);

	}
	
	public void init(){
		
	}
	
	public void update(){
	
	}
	
	public void draw(Graphics2D g){
		g.setBackground(Color.WHITE);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Loading...", 133, 100);
		
		if(first){
			gsm.initResources();
			gsm.setState(forwardState);
			first = false;
		}
		
	}
	

	public void keyPressed(int k){
		
	}
	
	public void keyReleased(int k){
		
	}
	

}
