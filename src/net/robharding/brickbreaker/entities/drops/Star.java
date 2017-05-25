package net.robharding.brickbreaker.entities.drops;

import java.awt.Color;
import java.awt.Graphics2D;

import net.robharding.brickbreaker.states.Level;

public class Star extends Drop{

	public Star(int x, int y, Level level) {
		super(x, y, level);
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 35, 35);
	}
	
	@Override
	public void getCollected() {
		level.collectStar();
	}
}
