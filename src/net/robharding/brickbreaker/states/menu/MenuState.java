package net.robharding.brickbreaker.states.menu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.robharding.brickbreaker.entities.Entity;
import net.robharding.brickbreaker.entities.ImageEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;
import net.robharding.brickbreaker.util.FileUtils;

public class MenuState extends GameState {

	private BufferedImage titleImage, playImage, quitImage, highscoresImage;
	private List<ImageEntity> entities = new ArrayList<ImageEntity>();
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		titleImage = FileUtils.loadImage("res/title.png");
		playImage = FileUtils.loadImage("res/play.png");
		quitImage = FileUtils.loadImage("res/quit.png");
		highscoresImage = FileUtils.loadImage("res/highscores.png");
	}
	
	@Override
	public void init() {
		
		entities.add(new ImageEntity(28, 120, titleImage));
		screen.addEntity(entities.get(0));
		
		entities.add(new ImageEntity(205, 263, playImage));
		screen.addEntity(entities.get(1));
		
		entities.add(new ImageEntity(107, 350, highscoresImage));
		screen.addEntity(entities.get(2)); 
		
		entities.add(new ImageEntity(205, 417, quitImage));
		screen.addEntity(entities.get(3));
	}

	@Override
	public void update() {
		for(Entity e: entities) {
			e.update();
		}
		
		if(entities.get(1).clicked) {
			gsm.setCurrentState(GameStateManager.PLAYSTATE);
		} else if(entities.get(3).clicked) {
			System.exit(0);
		}
	}

	@Override
	public void cleanUp() {
		screen.cleanUp();
	}

}
