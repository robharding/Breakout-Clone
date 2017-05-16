package net.robharding.brickbreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[300];
	
	public boolean left, right, esc, space;
	
	public void update() {
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		esc = false;
		space = false;
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			esc = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			space = true;
		}

	}
	
	public void keyTyped(KeyEvent e) {
		
	}
}
