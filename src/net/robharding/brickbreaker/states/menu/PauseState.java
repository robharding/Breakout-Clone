package net.robharding.brickbreaker.states.menu;

import java.awt.image.BufferedImage;

import net.robharding.brickbreaker.entities.ImageEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;
import net.robharding.brickbreaker.util.FileUtils;

public class PauseState extends GameState {

	private BufferedImage titleImage, resumeImage, menuImage, quitImage, resumeImage2, menuImage2, quitImage2;
	private ImageEntity titleEntity, resumeEntity, resumeEntity2, menuEntity, menuEntity2, quitEntity, quitEntity2;
	
	private int currentSelected = 1;
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
		titleImage = FileUtils.loadImage("res/paused.png");
		resumeImage = FileUtils.loadImage("res/resume.png");
		menuImage = FileUtils.loadImage("res/mainmenu.png");
		quitImage = FileUtils.loadImage("res/pausedquit.png");
		resumeImage2 = FileUtils.loadImage("res/resume2.png");
		quitImage2 = FileUtils.loadImage("res/pausedquit2.png");
		menuImage2 = FileUtils.loadImage("res/mainmenu2.png");
	}

	@Override
	public void init() {
		titleEntity = new ImageEntity(135, 119, titleImage);
		screen.addEntity(titleEntity);
		
		resumeEntity = new ImageEntity(204, 290, resumeImage);
		resumeEntity2 = new ImageEntity(204, 290, resumeImage2);
		screen.addEntity(resumeEntity);
		
		menuEntity = new ImageEntity(183, 349, menuImage);
		menuEntity2 = new ImageEntity(183, 349, menuImage2);
		screen.addEntity(menuEntity2);
		
		quitEntity = new ImageEntity(243, 408, quitImage);
		quitEntity2 = new ImageEntity(243, 408, quitImage2);
		screen.addEntity(quitEntity2);
	}

	@Override
	public void update() {
		if(keyboard.down && currentSelected < 3) {
			currentSelected ++;
		} else if(keyboard.up && currentSelected > 1) {
			currentSelected--;
		}
		
		if(currentSelected == 1) {
			if(!screen.containsEntity(resumeEntity)) {
				screen.removeEntity(resumeEntity2);
				screen.addEntity(resumeEntity);
			}
			if(!screen.containsEntity(menuEntity2)) {
				screen.removeEntity(menuEntity);
				screen.addEntity(menuEntity2);
			}
		} else if(currentSelected == 2) {
			if(!screen.containsEntity(resumeEntity2)) {
				screen.removeEntity(resumeEntity);
				screen.addEntity(resumeEntity2);
			}
			if(!screen.containsEntity(menuEntity)) {
				screen.removeEntity(menuEntity2);
				screen.addEntity(menuEntity);
			}
			if(!screen.containsEntity(quitEntity2)) {
				screen.removeEntity(quitEntity);
				screen.addEntity(quitEntity2);
			}
		} else if(currentSelected == 3) {
			if(!screen.containsEntity(menuEntity2)) {
				screen.removeEntity(menuEntity);
				screen.addEntity(menuEntity2);
			}
			if(!screen.containsEntity(quitEntity)) {
				screen.removeEntity(quitEntity2);
				screen.addEntity(quitEntity);
			}
		}
		
		if(keyboard.esc) {
			gsm.unpause();
		}
		
		if(keyboard.space) {
			switch(currentSelected) {
			case 1:
				gsm.unpause();
				break;
			case 2:
				gsm.setCurrentState(GameStateManager.MENUSTATE);
				currentSelected = 1;
				break;
			case 3:
				System.exit(0);
				break;
			}
		}
	}

	@Override
	public void cleanUp() {
		screen.cleanUp();
	}

}
