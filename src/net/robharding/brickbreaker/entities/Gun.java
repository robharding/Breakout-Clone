package net.robharding.brickbreaker.entities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.math.Vector2f;

public class Gun extends Entity {
	
	float x2;
	float y2;
	private Ball ball;
	private boolean isVisible = false;
	Vector2f unitVecToMouse;
	
	public Gun(int x, int y, Ball ball) {
		super(x, y);
		this.ball = ball;
	}
	
	public void setVisible(boolean visibility) {
		isVisible = visibility;
	}
	
	public Gun(Ball ball) {
		super(Game.WIDTH/2, Game.HEIGHT - 50);
		this.ball = ball;
	}

	@Override
	public void update() {
		float minY = Game.HEIGHT - 50;
		
		float mouseX = Game.WIDTH/2 - Game.mouse.x;
		float mouseY = Game.HEIGHT - 50 - Game.mouse.y;
		
		double magnitudeToMouse = Math.sqrt(Math.pow(mouseX, 2) + Math.pow(mouseY, 2));
		
		unitVecToMouse = new Vector2f((float)(mouseX/magnitudeToMouse), (float)(mouseY/magnitudeToMouse));
		
		float proposedY = Game.HEIGHT - 50 - (unitVecToMouse.getY() * 55);
		
		if(proposedY < minY) {
			this.x2 = x - (unitVecToMouse.getX() * 55);
			this.y2 = proposedY;
		} else {
			this.x2 = (Game.mouse.x > x ? (x) + 55 : (x) - 55);
			this.y2 = minY;
		}
		isVisible = true;
	}
	
	public void shoot() {
		ball.shoot(new Vector2f(-unitVecToMouse.getX() * Ball.SPEED, -unitVecToMouse.getY() * Ball.SPEED));
	}

	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(7));
		g2d.setColor(new Color(44, 62, 80));
		
		if(isVisible) {
			g2d.drawLine(x, y, (int)x2, (int)y2);
		}
	}

}
