package net.robharding.brickbreaker.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.math.Vector2f;

public class Ball extends Entity {
	
	private Vector2f velocity;
	private int radius;
	
	private Brick lastHit;
	
	public static final int SPEED = 8;
	
	public Ball(int x, int y, int radius) {
		super(x, y);
		velocity = new Vector2f(0f, 0f);
		this.radius = radius;
	}
	
	public void setLastHit(Brick brick) {
		lastHit = brick;
	}
	
	public Brick getLastHit() {
		return lastHit;
	}
	
	public Ball() {
		super((Game.WIDTH - 8)/2, Game.HEIGHT - 50-16);
		this.radius = 16;
	}
	
	public void shoot(Vector2f direction) {
		this.setVelocity(direction);
	}
	
	@Override
	public void update() {
		
		this.x += velocity.getX();
		this.y += velocity.getY();

		if(this.x < 0) {
			this.velocity = new Vector2f(-this.velocity.getX(), this.velocity.getY());
			this.x = 1;
			lastHit = null;
		}
		
		if(this.x + this.radius > Game.WIDTH) {
			this.velocity = new Vector2f(-this.velocity.getX(), this.velocity.getY());
			this.x = Game.WIDTH - radius;
			lastHit = null;
		}
		
		if(this.y < 0) {
			this.velocity = new Vector2f(this.velocity.getX(), -this.velocity.getY());
			lastHit = null;
		}
	}
	
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
	public Vector2f getVelocity() {
		return this.velocity;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(44, 62, 80));
		g.fillOval(x, y, radius, radius);
	}

	public int getDiameter() {
		return radius;
	}
	
}
