package net.robharding.brickbreaker.states;

import java.util.ArrayList;

import net.robharding.brickbreaker.states.menu.MenuState;
import net.robharding.brickbreaker.states.menu.PauseState;
import net.robharding.brickbreaker.util.FileUtils;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	public int currentState = 0;
	
	public static final int MENUSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int PLAYSTATE = 2;
	
	public static int currentLevel = 1;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		gameStates.add(new MenuState(this));
		gameStates.add(new PauseState(this));
		gameStates.add(new Level(this, FileUtils.loadAsString("levels/level" + currentLevel + ".lvl")));
		init();
	}
	
	public void levelUp() {
			cleanUp();
			gameStates.remove(2);
			currentLevel++;
			gameStates.add(new Level(this, FileUtils.loadAsString("levels/level" + currentLevel + ".lvl")));
			init();
	}
	
	public void setCurrentState(int state) {
		cleanUp();
		currentState = state;
		init();
	}
	
	public void pause() {
		currentState = PAUSESTATE;
		init();
	}
	
	public void unpause() {
		cleanUp();
		currentState = PLAYSTATE;
		gameStates.get(currentState).reInit();
	}
	
	public void init() {
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void cleanUp() {
		gameStates.get(currentState).cleanUp();
	}
	
}
