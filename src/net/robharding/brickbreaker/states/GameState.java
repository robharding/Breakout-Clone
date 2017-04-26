package net.robharding.brickbreaker.states;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.Mouse;
import net.robharding.brickbreaker.Screen;

public abstract class GameState {
	protected GameStateManager gsm;
	
	protected Screen screen;
	protected Mouse mouse;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		screen = Game.screen;
		mouse = Game.mouse;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void cleanUp();

}
