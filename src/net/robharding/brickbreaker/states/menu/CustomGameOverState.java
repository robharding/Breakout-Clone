package net.robharding.brickbreaker.states.menu;

import net.robharding.brickbreaker.entities.ImageEntity;
import net.robharding.brickbreaker.states.GameStateManager;

public class CustomGameOverState extends GameOverState {

	public CustomGameOverState(GameStateManager gsm) {
		super(gsm);
	}
	
	@Override
	public void init() {
		entities.add(new ImageEntity(95, 73, titleImage));
		screen.addEntity(entities.get(0));
		
		entities.add(new ImageEntity(70, 363, continueImage));
		screen.addEntity(entities.get(1));
		System.out.println("cgos");
	}

}
