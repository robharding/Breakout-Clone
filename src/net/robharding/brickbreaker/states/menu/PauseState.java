package net.robharding.brickbreaker.states.menu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.robharding.brickbreaker.entities.Entity;
import net.robharding.brickbreaker.entities.ImageEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;
import net.robharding.brickbreaker.util.FileUtils;

public class PauseState extends GameState {

	private BufferedImage titleImage, resumeImage, menuImage, quitImage;
	private List<ImageEntity> entities = new ArrayList<ImageEntity>();
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
		titleImage = FileUtils.loadImage("res/paused.png");
		resumeImage = FileUtils.loadImage("res/resume.png");
		menuImage = FileUtils.loadImage("res/mainmenu.png");
		quitImage = FileUtils.loadImage("res/pausedquit.png");
	}

	@Override
	public void init() {
		entities.add(new ImageEntity(135, 119, titleImage));
		screen.addEntity(entities.get(0));
		
		entities.add(new ImageEntity(204, 290, resumeImage));
		screen.addEntity(entities.get(1));
		
		entities.add(new ImageEntity(183, 349, menuImage));
		screen.addEntity(entities.get(2));
		
		entities.add(new ImageEntity(243, 408, quitImage));
		screen.addEntity(entities.get(3));
	}

	@Override
	public void update() {
		for(Entity e: entities) {
			e.update();
		}
		
		if(entities.get(1).clicked) {
			gsm.unpause();
		} else if(entities.get(2).clicked) {
			gsm.setCurrentState(GameStateManager.MENUSTATE);
		} else if(entities.get(3).clicked) {
			System.exit(0);
		}
		
		if(keyboard.esc) {
			gsm.unpause();
		}
	}

	@Override
	public void cleanUp() {
		screen.cleanUp();
	}

}
