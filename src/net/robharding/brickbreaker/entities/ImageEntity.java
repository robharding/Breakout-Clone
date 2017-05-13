package net.robharding.brickbreaker.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.Mouse;

public class ImageEntity extends Entity {

	private BufferedImage image;
	public boolean clicked = false;
	
	public ImageEntity(int x, int y, BufferedImage image) {
		super(x, y);
		this.image = image;
	}

	@Override
	public void update() {
		clicked = false;
		if(wasClicked())
			clicked = true;
	}
	
	public boolean wasClicked() {
		Mouse mouse = Game.mouse;

		if(mouse.rightReleased) {
			if(mouse.x > getX() && mouse.x < getX() + image.getWidth() &&
				mouse.y > getY() + 10 && mouse.y < getY() + image.getHeight() + 20) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, null);
	}

}
