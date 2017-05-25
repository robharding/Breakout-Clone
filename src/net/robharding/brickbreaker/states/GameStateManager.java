package net.robharding.brickbreaker.states;

import java.util.ArrayList;

import net.robharding.brickbreaker.states.menu.CustomLevelsState;
import net.robharding.brickbreaker.states.menu.GameOverState;
import net.robharding.brickbreaker.states.menu.HighScoresState;
import net.robharding.brickbreaker.states.menu.MenuState;
import net.robharding.brickbreaker.states.menu.PauseState;
import net.robharding.brickbreaker.util.FileUtils;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	public int currentState = 0;
	
	public static final int MENUSTATE = 0;
	public static final int PAUSESTATE = 1;
	public static final int GAMEOVERSTATE = 2;
	public static final int HIGHSCORESSTATE = 3;
	public static final int CUSTOMLEVELSSTATE = 4;
	public static final int PLAYSTATE = 5;
	
	public static int currentLevel = 1;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		gameStates.add(new MenuState(this));
		gameStates.add(new PauseState(this));
		gameStates.add(new GameOverState(this));
		gameStates.add(new HighScoresState(this));
		gameStates.add(new CustomLevelsState(this));
		gameStates.add(new Level(this, FileUtils.loadAsString("levels/level" + currentLevel + ".lvl")));
		init();
	}
	
	public void levelUp() {
			cleanUp();
			gameStates.remove(PLAYSTATE);
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
