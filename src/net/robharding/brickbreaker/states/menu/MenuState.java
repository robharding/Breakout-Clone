package net.robharding.brickbreaker.states.menu;

import java.awt.image.BufferedImage;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.entities.ui.ImageEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;
import net.robharding.brickbreaker.util.FileUtils;

public class MenuState extends GameState {

	private BufferedImage titleImage, playImage, quitImage, highscoresImage;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		titleImage = FileUtils.loadImage("res/title.png");
		playImage = FileUtils.loadImage("res/play.png");
		quitImage = FileUtils.loadImage("res/quit.png");
		highscoresImage = FileUtils.loadImage("res/highscores.png");
	}
	
	@Override
	public void init() {
		Game.screen.addEntity(new ImageEntity(0, 0, titleImage));
		Game.screen.addEntity(new ImageEntity(150, 250, playImage));
		Game.screen.addEntity(new ImageEntity(150, 350, quitImage));
		Game.screen.addEntity(new ImageEntity(150, 450, highscoresImage));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void cleanUp() {
		
	}

}
