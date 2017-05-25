package net.robharding.brickbreaker.entities.drops;
import java.awt.Graphics2D;
import java.util.Random;

import net.robharding.brickbreaker.entities.Entity;
import net.robharding.brickbreaker.states.Level;

public abstract class Drop extends Entity {
	
	private int yVel = 4;
	private int width, height;
	
	protected Level level;
	
	public Drop(int x, int y, Level level) {
		super(x, y);
		width = 35;
		height = 35;
		this.level = level;
	}
	
	public static Drop chooseRandomDrop(int x, int y, Level level) {
		Random rand = new Random();
		int randNum = rand.nextInt(20);
		switch(randNum) {
		case 0:
		case 1:
		case 2:
		case 3:
			return new Star(x, y, level);
		case 4:
		case 5:
			return new PaddleExtension(x, y, level);
		case 6:
			return new BallsAddition(x, y, level);
		}
		return null;
	}

	@Override
	public void update() {
		y += yVel;
	}

	@Override
	public abstract void draw(Graphics2D g);
	
	abstract public void getCollected();

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
