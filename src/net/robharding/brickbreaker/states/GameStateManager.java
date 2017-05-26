package net.robharding.brickbreaker.states;

import java.util.ArrayList;

import net.robharding.brickbreaker.states.menu.CustomGameOverState;
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
	public static final int CUSTOMGAMEOVERSTATE = 5;
	public static final int CUSTOMLEVELSTATE = 6;
	public static final int PLAYSTATE = 7;
	
	public static int currentLevel = 1;
	
	private GameOverState gos;
	private CustomLevel cls;
	private CustomGameOverState cgos;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		gameStates.add(new MenuState(this));
		gameStates.add(new PauseState(this));
		
		gos = new GameOverState(this);
		
		gameStates.add(gos);
		gameStates.add(new HighScoresState(this, loadHighscores()));
		gameStates.add(new CustomLevelsState(this, FileUtils.getFileNames("levels/custom/")));
		
		cgos = new CustomGameOverState(this);
		
		gameStates.add(cgos);
		
		cls = new CustomLevel(this, FileUtils.loadAsString("levels/level" + currentLevel + ".lvl"), "Custom", cgos); 
		
		gameStates.add(cls);
		gameStates.add(new Level(this, FileUtils.loadAsString("levels/level" + currentLevel + ".lvl"), Integer.toString(currentLevel), gos));
		init();
	}
	
	public GameState getGameState(int state) {
		return gameStates.get(state);
	}
	
	public String[] loadHighscores() {
		String rawHighscoreData = FileUtils.loadAsString("data/scores.txt");
		String[] highscoreLines = rawHighscoreData.split("\\r?\\n");
		String[][] scores = new String[highscoreLines.length][];
		
		for(int i = 0; i < highscoreLines.length; i++) {
			scores[i] = highscoreLines[i].split("\\s+");
			if(scores[i].length > 2) {
				for(int j = 2; j < scores[i].length; j++) {
					scores[i][1] += " " + scores[i][j];
				}
				String[] s = {scores[i][0], scores[i][1]};
				scores[i] = s;
			}
		}
		
		String[] result = new String[scores.length];
		
		if(scores.length > 1) {
		int j;
		boolean flag = true;
		String[] temp = null;
		
		while(flag) {
			flag = false;
			for(j = 0; j < scores.length-1; j++) {
				if(Integer.parseInt(scores[j][0]) < Integer.parseInt(scores[j+1][0])) {
					temp = scores[j];
					scores[j] = scores[j+1];
					scores[j+1] = temp;
					flag = true;
				}
			}
		}
		}
		
		if(scores.length == 1 && scores[0].length == 1) {
			String[] s = {"No results"};
			return s;
		}
		
		for(int i = 0; i < (scores.length < 10 ? scores.length : 10); i++) {
			if(scores[i][1] != null)
				result[i] = scores[i][0] + " " + scores[i][1];
		}
		
		return result;
	}
	
	public void levelUp(int score) {
			cleanUp();
			gameStates.remove(PLAYSTATE);
			currentLevel++;
			gameStates.add(new Level(this, FileUtils.loadAsString("levels/level" + currentLevel + ".lvl"), Integer.toString(currentLevel), gos, score));
			init();
	}
	
	public void setCurrentState(int state) {
		cleanUp();
		
		if(currentState == GAMEOVERSTATE) {
			HighScoresState s = new HighScoresState(this, loadHighscores());
			gameStates.set(HIGHSCORESSTATE, s);
		}
		
		currentState = state;
		if(currentState == MENUSTATE) {
			currentLevel = 1;
			gameStates.remove(PLAYSTATE);
			gameStates.add(new Level(this, FileUtils.loadAsString("levels/level" + currentLevel + ".lvl"), Integer.toString(currentLevel), gos));
		}
		init();
	}
	
	public void setCustomLevelState(String levelSource) {
		cls = new CustomLevel(this, FileUtils.loadAsString(levelSource), "Custom", cgos);
		gameStates.set(CUSTOMLEVELSTATE, cls);
		setCurrentState(CUSTOMLEVELSTATE);
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
