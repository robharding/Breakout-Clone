package net.robharding.brickbreaker.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Drop extends Entity {
	
	private int yVel = 4;
	private int width, height;
	
	public Drop(int x, int y) {
		super(x, y);
		width = 35;
		height = 35;
	}

	@Override
	public void update() {
		y += yVel;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
