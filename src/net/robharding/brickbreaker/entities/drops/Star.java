package net.robharding.brickbreaker.entities.drops;

import net.robharding.brickbreaker.states.Level;

public class Star extends Drop{

	public Star(int x, int y, Level level) {
		super(x, y, level, "res/star.png");
	}
	
	@Override
	public void getCollected() {
		level.collectStar();
	}
}
