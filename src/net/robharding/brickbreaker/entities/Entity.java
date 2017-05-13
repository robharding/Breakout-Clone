package net.robharding.brickbreaker.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Entity {
	
	protected int x, y;
	
	Color lightGrey = Color.decode("#ecf0f1");
	Color darkGrey = Color.decode("#bdc3c7");
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g);

}
