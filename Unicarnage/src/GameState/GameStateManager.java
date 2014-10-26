package GameState;

import Entity.Player;
import HUDs.HUD;
import Utility.ResourceLoader;

public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;

	public static final int NUMGAMESTATES = 6;

	public static final int MENUSTATE = 0;
	public static final int TUTORIALSTATE = 1;
	public static final int LEVEL1STATE = 2;
	public static final int CONTROLSSTATE = 3;
	public static final int TESTSTATE = 4;
	public static final int LOADINGSTATE = 5;

	public Player player;
	public HUD hud;
	public ResourceLoader res;

	public GameStateManager() {

		gameStates = new GameState[NUMGAMESTATES];
		currentState = LOADINGSTATE;
		loadState(currentState);

	}

	private void loadState(int state) {
		switch (state) {
		case LOADINGSTATE:
			gameStates[state] = new LoadingState(this, MENUSTATE);
			break;
		case MENUSTATE:
			gameStates[state] = new MenuState(this);
			break;
		case TUTORIALSTATE:
			gameStates[state] = new TutorialState(this);
			break;
		case LEVEL1STATE:
			gameStates[state] = new Level1State(this);
			break;
		case CONTROLSSTATE:
			gameStates[state] = new ControlsState(this);
			break;
		case TESTSTATE:
			gameStates[state] = new TestLevelState(this);
			break;
		}
	}

	private void unloadState(int state) {
		gameStates[state] = null;
	}

	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		// gameStates[currentState].init();
	}
	
	public void initResources(){
		res = new ResourceLoader();
	}

	public void update() {
		try {
			gameStates[currentState].update();
		} catch (Exception e) {
		}
	}

	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch (Exception e) {
		}
	}

	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}

	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public void saveStats(HUD hud, Player p) {
		player = p;
		this.hud = hud;
	}
}
