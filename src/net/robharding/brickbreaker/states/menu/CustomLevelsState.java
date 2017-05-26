package net.robharding.brickbreaker.states.menu;

import net.robharding.brickbreaker.states.GameStateManager;

public class CustomLevelsState extends ListState {

	public CustomLevelsState(GameStateManager gsm, String[] items) {
		super(gsm, items, "Custom Levels");
	}

}
