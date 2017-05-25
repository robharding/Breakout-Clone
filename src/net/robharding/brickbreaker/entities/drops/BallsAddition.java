package net.robharding.brickbreaker.entities.drops;

import net.robharding.brickbreaker.states.Level;

public class BallsAddition extends Drop {

	public BallsAddition(int x, int y, Level level) {
		super(x, y, level, "res/balls.png");
	}
	
	@Override
	public void getCollected() {
		level.shootExtraBall();
	}

}
