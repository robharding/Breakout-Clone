package net.robharding.brickbreaker.entities.drops;

import java.awt.Color;
import java.awt.Graphics2D;

import net.robharding.brickbreaker.states.Level;

public class BallsAddition extends Drop {

	public BallsAddition(int x, int y, Level level) {
		super(x, y, level);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 35, 35);
	}
	
	@Override
	public void getCollected() {
		level.shootExtraBall();
	}

}
