package net.robharding.brickbreaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.robharding.brickbreaker.util.FileUtils;

public class ScoreManager {
	
	private int currentScore;
	private float multiplier = 1.0f;
	private ArrayList<HighScore> highscores;
	
	public ScoreManager() {
		currentScore = 0;
		highscores = new ArrayList<HighScore>();
	}
	
	private void fillHighscores() {
		for(int i = 0; i < 10; i++) {
			highscores.add(new HighScore("", -1));
		}
	}
	
	public void loadHighscores() {
		String scoreData = FileUtils.loadAsString("data/scores.txt");
	}
	
	public int getScore() {
		return currentScore;
	}
	
	public float getMultiplier() {
		return multiplier;
	}
	
	public void blockWasDestroyed() {
		currentScore += 100 * multiplier;
		multiplier += 0.2;
	}

	
	public void resetMultiplier() {
		multiplier = 1.0f;
	}

}
