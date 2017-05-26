package net.robharding.brickbreaker.states.menu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import net.robharding.brickbreaker.entities.Entity;
import net.robharding.brickbreaker.entities.ImageEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;
import net.robharding.brickbreaker.util.FileUtils;

public class GameOverState extends GameState {

	protected BufferedImage titleImage;
	protected BufferedImage continueImage;
	protected List<ImageEntity> entities = new ArrayList<ImageEntity>();
	private int score;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
		titleImage = FileUtils.loadImage("res/gameover.png");
		continueImage = FileUtils.loadImage("res/continue.png");
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void init() {
		entities.add(new ImageEntity(95, 73, titleImage));
		screen.addEntity(entities.get(0));
		
		entities.add(new ImageEntity(70, 363, continueImage));
		screen.addEntity(entities.get(1));
		
		String name = JOptionPane.showInputDialog(null, "Your score was " + score +"! What is your name?");
		
		if(name == null || name.length() == 0) {
			name = "No Name";
		}
		
		FileUtils.appendToFile("data/scores.txt", score + " " + name);
	}

	@Override
	public void update() {
		for(Entity e: entities) {
			e.update();
		}
		
		if(keyboard.space) {
			gsm.setCurrentState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void cleanUp() {
		screen.cleanUp();
	}

}
