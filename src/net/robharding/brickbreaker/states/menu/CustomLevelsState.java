package net.robharding.brickbreaker.states.menu;

import java.awt.Color;
import java.util.Arrays;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.entities.TextEntity;
import net.robharding.brickbreaker.states.GameStateManager;

public class CustomLevelsState extends ListState {

	int currentSelected = 0;
	
	public CustomLevelsState(GameStateManager gsm, String[] items) {
		super(gsm, items, "Custom Levels");
	}
	
	@Override
	public void init() {
		for(int i = 0; i < items.length; i++) {
			TextEntity e = new TextEntity(20, 100 + 50*i, new String(Arrays.copyOf(items[i].toCharArray(), items[i].length()-4)), 30f, Color.BLACK) ;
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
		super.update();
		entities[currentSelected].setColor(Color.BLACK);
		for(int i = 0; i < entities.length; i++) {
			if(i == currentSelected)
				continue;
			entities[i].setColor(new Color(100, 100, 100));
		}
		
		if(keyboard.up) {
			if(currentSelected != 0)
				currentSelected--;
		} else if(keyboard.down) {
			if(currentSelected < entities.length-1)
				currentSelected++;
		} else if(keyboard.space) {
			gsm.setCustomLevelState("levels/custom/" + entities[currentSelected].getText() + ".lvl");
		}
	}

}
