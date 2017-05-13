package net.robharding.brickbreaker;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import net.robharding.brickbreaker.entities.Entity;

public class Screen extends Canvas {
	private static final long serialVersionUID = 1L;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	int width, height;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public void cleanUp() {
		entities = new ArrayList<Entity>();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(new Color(236, 240, 241));
		g.fillRect(0, 0, width+20, height+20);
		
		for(Entity e: entities) {
			e.draw(g2d);
		}
		
		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}

}
