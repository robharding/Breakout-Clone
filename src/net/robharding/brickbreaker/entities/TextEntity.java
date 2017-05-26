package net.robharding.brickbreaker.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class TextEntity extends Entity {
	
	private Font f;
	private String text;
	private Color color;
	
	public TextEntity(int x, int y, String text, float fontSize, Color color) {
		super(x, y);
		this.text = text;
		this.color = color;
		
		try {
		   f = Font.createFont(Font.TRUETYPE_FONT, new File("res/LLPIXEL3.ttf")).deriveFont(fontSize);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/LLPIXEL3.ttf")));
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public void update() {		
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(f);
		g.setColor(color);
		//if(text != null)
			g.drawString(text, x, y);
	}

}
