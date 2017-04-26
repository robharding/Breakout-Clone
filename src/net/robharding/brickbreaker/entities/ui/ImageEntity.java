package net.robharding.brickbreaker.entities.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import net.robharding.brickbreaker.entities.Entity;

public class ImageEntity extends Entity {

	private BufferedImage image;
	
	public ImageEntity(int x, int y, BufferedImage image) {
		super(x, y);
		this.image = image;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, null);
	}

}
