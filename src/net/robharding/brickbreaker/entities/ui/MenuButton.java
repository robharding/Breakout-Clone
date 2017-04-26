package net.robharding.brickbreaker.entities.ui;

import java.awt.Graphics2D;

import net.robharding.brickbreaker.entities.Entity;

public class MenuButton extends Entity {

	String text;
	int state;
	
	public MenuButton(String text, int state, int x, int y) {
		super(x, y);
		this.text = text;
		this.state = state;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		
	}

}
