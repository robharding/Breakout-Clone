package net.robharding.brickbreaker.states.menu;

import java.awt.image.BufferedImage;

import net.robharding.brickbreaker.entities.ImageEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;
import net.robharding.brickbreaker.util.FileUtils;

public class MenuState extends GameState {

	private BufferedImage titleImage, playImage, quitImage, highscoresImage, playImage2, quitImage2, highscoresImage2, customLevelsImage, customLevelsImage2;
	private ImageEntity titleEntity, playEntity, playEntity2, quitEntity, quitEntity2, highscoresEntity, highscoresEntity2, customLevelsEntity, customLevelsEntity2;
	
	private int currentSelected = 1;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		titleImage = FileUtils.loadImage("res/title.png");
		playImage = FileUtils.loadImage("res/play.png");
		quitImage = FileUtils.loadImage("res/quit.png");
		highscoresImage = FileUtils.loadImage("res/highscores.png");
		playImage2 = FileUtils.loadImage("res/play2.png");
		quitImage2 = FileUtils.loadImage("res/quit2.png");
		highscoresImage2 = FileUtils.loadImage("res/highscores2.png");
		customLevelsImage = FileUtils.loadImage("res/customlevels.png");
		customLevelsImage2 = FileUtils.loadImage("res/customlevels2.png");
	}
	
	@Override
	public void init() {
		titleEntity = new ImageEntity(28, 120, titleImage);
		screen.addEntity(titleEntity);
		
		playEntity = new ImageEntity(205, 263, playImage);
		playEntity2 = new ImageEntity(205, 263, playImage2);
		screen.addEntity(playEntity);
		
		highscoresEntity = new ImageEntity(107, 350, highscoresImage);
		highscoresEntity2 = new ImageEntity(107, 350, highscoresImage2);
		screen.addEntity(highscoresEntity2);
		
		quitEntity = new ImageEntity(205, 490, quitImage);
		quitEntity2 = new ImageEntity(205, 490, quitImage2);
		screen.addEntity(quitEntity2);
		
		customLevelsEntity = new ImageEntity(100, 425, customLevelsImage);
		customLevelsEntity2 = new ImageEntity(100, 425, customLevelsImage2);
		screen.addEntity(customLevelsEntity2);
	}

	@Override
	public void update() {
		
		if(keyboard.down && currentSelected < 4) {
			currentSelected ++;
		} else if(keyboard.up && currentSelected > 1) {
			currentSelected--;
		}
		
		if(currentSelected == 1) {
			if(!screen.containsEntity(playEntity)) {
				screen.removeEntity(playEntity2);
				screen.addEntity(playEntity);
			}
			if(!screen.containsEntity(highscoresEntity2)) {
				screen.removeEntity(highscoresEntity);
				screen.addEntity(highscoresEntity2);
			}
		} else if(currentSelected == 2) {
			if(!screen.containsEntity(playEntity2)) {
				screen.removeEntity(playEntity);
				screen.addEntity(playEntity2);
			}
			if(!screen.containsEntity(highscoresEntity)) {
				screen.removeEntity(highscoresEntity2);
				screen.addEntity(highscoresEntity);
			}
			if(!screen.containsEntity(customLevelsEntity2)) {
				screen.removeEntity(customLevelsEntity);
				screen.addEntity(customLevelsEntity2);
			}
		} else if(currentSelected == 3) {
			if(!screen.containsEntity(highscoresEntity2)) {
				screen.removeEntity(highscoresEntity);
				screen.addEntity(highscoresEntity2);
			}
			if(!screen.containsEntity(customLevelsEntity)) {
				screen.removeEntity(customLevelsEntity2);
				screen.addEntity(customLevelsEntity);
			}
			if(!screen.containsEntity(quitEntity2)) {
				screen.removeEntity(quitEntity);
				screen.addEntity(quitEntity2);
			}
		} else if(currentSelected == 4) {
			if(!screen.containsEntity(customLevelsEntity2)) {
				screen.removeEntity(customLevelsEntity);
				screen.addEntity(customLevelsEntity2);
			}
			if(!screen.containsEntity(quitEntity)) {
				screen.removeEntity(quitEntity2);
				screen.addEntity(quitEntity);
			}
		}
		
		if(keyboard.space) {
			switch(currentSelected) {
			case 1:
				gsm.setCurrentState(GameStateManager.PLAYSTATE);
				break;
			case 2:
				gsm.setCurrentState(GameStateManager.HIGHSCORESSTATE);
				break;
			case 3:
				gsm.setCurrentState(GameStateManager.CUSTOMLEVELSSTATE);
				break;
			case 4:
				System.exit(0);
				break;
			}
		}
	}

}
