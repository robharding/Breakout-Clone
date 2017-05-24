package net.robharding.brickbreaker;

import java.io.File;
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
	
	public void loadHighscores() {
		String scoreData = FileUtils.loadAsString("data/scores.txt");
		if(scoreData == null) {
			System.out.println("Creating scores file");
			File f = new File("data/scores.txt");
		}
			
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
