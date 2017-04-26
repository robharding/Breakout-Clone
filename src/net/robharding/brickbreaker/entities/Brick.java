package net.robharding.brickbreaker.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends Entity {
	
	public static int BRICK_WIDTH = 80;
	public static int BRICK_HEIGHT = 40;
	
	public int health;
	
	private Color primaryColor, secondaryColor;
	private int width, height;

	public Brick(int x, int y, int width, int height, int health) {
		super(x, y);
		this.health = health;
		this.width = width;
		this.height = height;
		
		updateColor();
	}
	
	private void updateColor() {
		switch(health) {
		case 5:
			this.primaryColor = new Color(231, 76, 60);
			this.secondaryColor = new Color(192, 57, 43);
			break;
		case 4:
			this.primaryColor = new Color(201, 46, 30);
			this.secondaryColor = new Color(162, 27, 13);
			break;
		case 3:
			this.primaryColor = new Color(181, 26, 10);
			this.secondaryColor = new Color(142, 7, 0);
			break;
		case 2:
			this.primaryColor = new Color(161, 6, 0);
			this.secondaryColor = new Color(122, 0, 0);
			break;
		case 1:
			this.primaryColor = new Color(141, 0, 0);
			this.secondaryColor = new Color(102, 0, 0);
			break;
		}
	}
	
	public void takeDamage() {
		health--;
		updateColor();
	}
	
	public int getHealth() {
		return health;
	}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(primaryColor);
		g.fillRect(x, y, width, height);
		g.setColor(secondaryColor);
		g.fillRect(x + 5, y + 5, width - 10, height - 10);
		g.drawRect(x, y, width, height);
	}

}
