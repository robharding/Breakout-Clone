package net.robharding.brickbreaker.entities.drops;

import java.awt.Color;
import java.awt.Graphics2D;

import net.robharding.brickbreaker.states.Level;

public class PaddleExtension extends Drop {

	public PaddleExtension(int x, int y, Level level) {
		super(x, y, level);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 20, 20);
	}

	@Override
	public void getCollected() {
		level.extendPaddle();
	}

}
