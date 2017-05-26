package net.robharding.brickbreaker.states;

import java.awt.Color;

import javax.swing.JOptionPane;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.entities.TextEntity;
import net.robharding.brickbreaker.states.menu.GameOverState;

public class CustomLevel extends Level {

	public CustomLevel(GameStateManager gsm, String levelSource, String levelNum, GameOverState gameOverState) {
		super(gsm, levelSource, levelNum, null);
	}
	
	@Override
	protected void initEntities() {
		super.initEntities();
		levelLabel = new TextEntity(Game.WIDTH - 270, 40,"Level: ", 36f, Color.BLACK);
		level = new TextEntity(Game.WIDTH - 150, 40, levelNum, 36f, Color.BLACK);
	}
	
	@Override
	void levelWin() {
		gsm.setCurrentState(GameStateManager.MENUSTATE);
		JOptionPane.showMessageDialog(null, "You Win!");
	}
	
	@Override
	void levelLose() {
		gsm.setCurrentState(GameStateManager.CUSTOMGAMEOVERSTATE);
		scoreNum = 0;
	}
	
}
