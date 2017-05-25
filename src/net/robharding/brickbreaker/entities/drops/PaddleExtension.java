package net.robharding.brickbreaker.entities.drops;

import net.robharding.brickbreaker.states.Level;

public class PaddleExtension extends Drop {

	public PaddleExtension(int x, int y, Level level) {
		super(x, y, level, "res/mushroom.png");
	}
	
	@Override
	public void getCollected() {
		level.extendPaddle();
	}

}
