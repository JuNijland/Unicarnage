package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class ControlsState extends GameState{
	
	public ControlsState(GameStateManager gsm){
		this.gsm = gsm;
	}

	public void init() {
		
	}

	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 600, 480);
		g.setColor(Color.BLACK);
		g.drawString("Arrow Keys to move", 20, 20);
		g.drawString("\"z\" to pick up item", 20, 40);
		g.drawString("\"e\" to activate a object (chest)", 20, 60);
		g.drawString("\"r\" to use or eat an item", 20, 80);
		g.drawString("\"q\" to throw an item ont the ground", 20, 100);
		g.drawString("Control to attack", 20, 120);
		g.drawString("Shift to change attack", 20, 140);
		g.drawString("1 to 9 to select an item from inventory", 20, 160);
		g.drawString("Press ENTER to go back to the menu screen", 20, 200);
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(int k) {
		
	}

}
