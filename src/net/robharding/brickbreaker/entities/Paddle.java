package net.robharding.brickbreaker.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import net.robharding.brickbreaker.Game;
public class Paddle extends Entity {
	
	private int width, height;
	
	public Paddle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public Paddle() {
		super((Game.WIDTH - 120) / 2, Game.HEIGHT - 50);
		this.width = 120;
		this.height = 20;
	}
	
	@Override
	public void update() {
		if(Game.keyboard.left) {
			x-=8;
		}
		
		if(Game.keyboard.right) {
			x+=8;
		}
		
		if(x < 5) {
			x += 8;
		}
		
		if(x + width > Game.WIDTH - 5) {
			x -= 8;
		}
	}
	
	public void extend() {
		x -= 10;
		width += 20;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(52, 73, 94));
		g.fillRect(x, y, width, height);
		g.setColor(new Color(44, 62, 80));
		g.fillRect(x, y, width/2, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
