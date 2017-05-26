package net.robharding.brickbreaker.states.menu;

import java.awt.Color;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.entities.TextEntity;
import net.robharding.brickbreaker.states.GameState;
import net.robharding.brickbreaker.states.GameStateManager;

public class ListState extends GameState {
	
	private String title;
	private String[] items;
	private TextEntity[] entities;

	public ListState(GameStateManager gsm, String[] items, String title) {
		super(gsm);
		this.items = items;
		this.entities = new TextEntity[items.length];
		this.title = title;
	}

	@Override
	public void init() {
		for(int i = 0; i < items.length; i++) {
			TextEntity e = new TextEntity(20, 100 + 50*i, items[i], 30f, Color.BLACK) ;
			entities[i] = e;
			screen.addEntity(e);
		}
		
		TextEntity titleEntity = new TextEntity(20, 50, title, 50f, Color.RED);
		screen.addEntity(titleEntity);
		
		TextEntity quit = new TextEntity(Game.WIDTH - 210, Game.HEIGHT - 20, "Press ESC to go back.", 20f, Color.BLACK);
		screen.addEntity(quit);
	}

	@Override
	public void update() {
		if(keyboard.esc) {
			gsm.setCurrentState(GameStateManager.MENUSTATE);
		}
	}
}
