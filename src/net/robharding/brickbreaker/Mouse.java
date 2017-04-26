package net.robharding.brickbreaker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
	
	public int x, y;
	
	public boolean[] buttons = new boolean[3];
	public boolean rightPressed;
	
	public void update() {
		x = (int)Game.getMousePos().getX();
		y = (int)Game.getMousePos().getY();
		
		rightPressed = buttons[MouseEvent.BUTTON1];
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("q");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
