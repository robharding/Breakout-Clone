package net.robharding.brickbreaker.states.menu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.robharding.brickbreaker.entities.Entity;
import net.robharding.brickbreaker.entities.ImageEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;
import net.robharding.brickbreaker.util.FileUtils;

public class GameOverState extends GameState {

	private BufferedImage titleImage, continueImage;
	private List<ImageEntity> entities = new ArrayList<ImageEntity>();
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
		titleImage = FileUtils.loadImage("res/gameover.png");
		continueImage = FileUtils.loadImage("res/continue.png");
	}

	@Override
	public void init() {
		entities.add(new ImageEntity(95, 73, titleImage));
		screen.addEntity(entities.get(0));
		
		entities.add(new ImageEntity(70, 363, continueImage));
		screen.addEntity(entities.get(1));
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
