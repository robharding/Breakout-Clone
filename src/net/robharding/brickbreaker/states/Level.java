package net.robharding.brickbreaker.states;

import static net.robharding.brickbreaker.math.Intersections.AABBIntersect;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.robharding.brickbreaker.Game;
import net.robharding.brickbreaker.entities.Ball;
import net.robharding.brickbreaker.entities.Brick;
import net.robharding.brickbreaker.entities.Gun;
import net.robharding.brickbreaker.entities.Paddle;
import net.robharding.brickbreaker.entities.TextEntity;
import net.robharding.brickbreaker.entities.drops.Drop;
import net.robharding.brickbreaker.math.Vector2f;
import net.robharding.brickbreaker.states.menu.GameOverState;

public class Level extends GameState {
	
	protected Paddle paddle;
	protected Gun gun;
	protected TextEntity scoreLabel, score, levelLabel, level;
	
	private static ArrayList<Brick> bricks;
	private static ArrayList<Ball> balls;
	public ArrayList<Drop> drops;
	
	protected String levelNum;
	
	private String levelSource;
	
	enum Stage {
		SHOOT,
		PLAY,
		PAUSE
	}
	
	public Stage currentStage;
	
	public int scoreNum;
	
	private GameOverState gameOverState;

	public Level(GameStateManager gsm, String levelSource, String levelNum, GameOverState gameOverState) {
		super(gsm);
		this.levelSource = levelSource;
		this.levelNum = levelNum;
		scoreNum = 0;
		this.gameOverState = gameOverState;
	}
	
	public Level(GameStateManager gsm, String levelSource, String levelNum, GameOverState gameOverState, int scoreNum) {
		super(gsm);
		this.levelSource = levelSource;
		this.levelNum = levelNum;
		this.scoreNum = scoreNum;
		this.gameOverState = gameOverState;
	}
	
	protected void initEntities() {
		paddle = new Paddle();
		bricks = new ArrayList<Brick>();
		drops = new ArrayList<Drop>();
		balls = new ArrayList<Ball>();
		balls.add(new Ball());
		gun = new Gun(balls.get(0));
		scoreLabel = new TextEntity(10, 40, "Score:", 36f, Color.BLACK);
		score = new TextEntity(130, 45, Integer.toString(scoreNum), 48f, Color.RED);
		levelLabel = new TextEntity(Game.WIDTH - 150, 40,"Level:", 36f, Color.BLACK);
		level = new TextEntity(Game.WIDTH - 40, 40, levelNum, 36f, Color.BLACK);
	}
	
	public void loadEntitiesToScreen() {
		for(Brick brick: bricks) {
			screen.addEntity(brick);
		}
		screen.addEntity(balls.get(0));
		screen.addEntity(gun);
		screen.addEntity(paddle);
		screen.addEntity(scoreLabel);
		screen.addEntity(score);
		screen.addEntity(levelLabel);
		screen.addEntity(level);
	}

	public void loadLevel(String level) {
		String[] rows = level.split("\\r?\\n");
		for(int i = 0; i < rows.length; i++) {
			String row = rows[i];
			for(int j = 0; j < row.length(); j++) {
				int symbol = Integer.parseInt(String.valueOf(row.charAt(j)));
			
				switch(symbol) {
				case 0:
					break;
				case 1:
					bricks.add(new Brick(20 + (j * Brick.BRICK_WIDTH), 20 + (i * Brick.BRICK_HEIGHT), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT, 1));
					break;
				case 2:
					bricks.add(new Brick(20 + (j * Brick.BRICK_WIDTH), 20 + (i * Brick.BRICK_HEIGHT), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT, 2));
					break;
				case 3:
					bricks.add(new Brick(20 + (j * Brick.BRICK_WIDTH), 20 + (i * Brick.BRICK_HEIGHT), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT, 3));
					break;
				case 4:
					bricks.add(new Brick(20 + (j * Brick.BRICK_WIDTH), 20 + (i * Brick.BRICK_HEIGHT), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT, 4));
					break;
				case 5:
					bricks.add(new Brick(20 + (j * Brick.BRICK_WIDTH), 20 + (i * Brick.BRICK_HEIGHT), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT, 5));
					break;
				}
			}
		}
	}
	
	void levelWin() {
		scoreNum += 100;
		gsm.levelUp(scoreNum);
		return;
	}
	
	void levelLose() {
		gameOverState.setScore(scoreNum);
		gsm.setCurrentState(GameStateManager.GAMEOVERSTATE);
		scoreNum = 0;
	}
	
	@Override
	public void update() {
		
		List<Ball> ballsToRemove = new ArrayList<Ball>();
		
		for(Ball ball: balls) {
			if(ball.getY() > Game.HEIGHT + 30) {
				ballsToRemove.add(ball);
			}
		}
		
		balls.removeAll(ballsToRemove);
		if(balls.size() == 0) {
			levelLose();
		}
		
		if(keyboard.esc) {
			gsm.pause();
		}
		
		if(currentStage == Stage.PLAY) {
			paddle.update();
			
			if(bricks.size() == 0) {
				levelWin();
			}
			
			// drop collisions
			for(int i = 0; i < drops.size(); i++) {
				Drop drop = drops.get(i);
				if(AABBIntersect(drop.getX(), drop.getY(), drop.getWidth(), drop.getHeight(),
						paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight())) {
					screen.removeEntity(drop);
					drops.remove(drop);
					drop.getCollected();
				}
			}
			
			// remove bricks when their health <= 0
			for(int i = 0; i < bricks.size(); i++) {
				Brick b = bricks.get(i);
							
				if(b.getHealth() <= 0) {
					scoreNum += 10;
					score.setText(Integer.toString(scoreNum));
					
					// percentage chance to drop a buff
					Drop d = Drop.chooseRandomDrop(b.getX() + (Brick.BRICK_HEIGHT / 2) - 35/2, b.getY(), this);
					
					if(d != null) {
						drops.add(d);
						screen.addEntity(d);
					}
					screen.removeEntity(b);
					bricks.remove(i);
				}
			}
			
			for(Ball ball: balls) {
				ball.update();
			
				// brick collisions
				for(Brick brick: bricks) {
					if(AABBIntersect(ball.getX(), ball.getY(), ball.getDiameter(),
							ball.getDiameter(), brick.getX(), brick.getY(), 
							Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT)) {
					
						if(brick == ball.getLastHit()) {
							continue;
						}else {
							brick.takeDamage();
						}
					
						if((ball.getX() < brick.getX() && ball.getX() + ball.getDiameter() > brick.getX()) && ball.getY() + (ball.getDiameter() / 2) > brick.getY() || (ball.getX() + ball.getDiameter() > brick.getX() + Brick.BRICK_WIDTH && ball.getX() < brick.getX() + Brick.BRICK_WIDTH) && ball.getY() + (ball.getDiameter() / 2) < brick.getY() + Brick.BRICK_HEIGHT && ball.getY() + (ball.getDiameter() / 2) > brick.getY()) {
							ball.setVelocity(new Vector2f(-ball.getVelocity().getX(), ball.getVelocity().getY()));
						} else {
							ball.setVelocity(new Vector2f(ball.getVelocity().getX(), -ball.getVelocity().getY()));
						}
					
						ball.setLastHit(brick);
						break;
					
					}
				}
			
				// paddle collisions
				if(AABBIntersect(ball.getX(), ball.getY(), ball.getDiameter(),
						ball.getDiameter(), paddle.getX(), paddle.getY(),
						paddle.getWidth(), paddle.getHeight())) {
					ball.setLastHit(null);
				
					ball.setY(paddle.getY() - ball.getDiameter());
				
					ball.setVelocity(new Vector2f(ball.getVelocity().getX(), -ball.getVelocity().getY()));
				}
			}
			for(Drop d: drops) {
				d.update();
			}
		} if(currentStage == Stage.SHOOT) {
			gun.update();
			if(mouse.rightPressed || keyboard.space) {
				currentStage = Stage.PLAY;
				gun.shoot();
				gun.setVisible(false);
			}
		}
	}
	
	public void extendPaddle() {
		paddle.extend();
	}
	
	public void collectStar() {
		scoreNum += 20;
		score.setText(Integer.toString(scoreNum));
	}
	
	public void shootExtraBall() {
		Ball b = new Ball(paddle.getX() + (paddle.getWidth() / 2) - 8, paddle.getY(), 16);
		gun = new Gun(paddle.getX() + (paddle.getWidth() / 2), paddle.getY(), b);
		screen.addEntity(gun);
		balls.add(b);
		screen.addEntity(b);
		currentStage = Stage.SHOOT;
	}

	@Override
	public void init() {
		initEntities();
		loadLevel(levelSource);
		loadEntitiesToScreen();
		currentStage = Stage.SHOOT;
	}
	
	@Override
	public void reInit() {
		loadEntitiesToScreen();
	}
}
