package net.robharding.brickbreaker;

public class HighScore {
	private int score;
	private String name;
	
	public HighScore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
}
