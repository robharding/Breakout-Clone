package net.robharding.brickbreaker.states.menu;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.robharding.brickbreaker.entities.ImageEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;
import net.robharding.brickbreaker.util.FileUtils;

public class PauseState extends GameState {

	private BufferedImage titleImage, continueImage, quitImage;
	private List<ImageEntity> entities = new ArrayList<ImageEntity>();
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
		//titleImage = FileUtils.loadImage("res/");
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void cleanUp() {
		
	}

}
