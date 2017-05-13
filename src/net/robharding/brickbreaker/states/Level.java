package net.robharding.brickbreaker.states;

import static net.robharding.brickbreaker.math.Intersections.*;

import java.util.ArrayList;
import java.util.Random;

import net.robharding.brickbreaker.entities.Ball;
import net.robharding.brickbreaker.entities.Brick;
import net.robharding.brickbreaker.entities.Drop;
import net.robharding.brickbreaker.entities.Gun;
import net.robharding.brickbreaker.entities.Paddle;
import net.robharding.brickbreaker.math.Vector2f;

public class Level extends GameState {
	
	protected Paddle paddle;
	protected Ball ball;
	protected Gun gun;
	
	private static ArrayList<Brick> bricks;
	public ArrayList<Drop> drops;
	
	private String levelSource;
	
	enum Stage {
		SHOOT,
		PLAY,
		PAUSE
	}
	
	public Stage currentStage;

	public Level(GameStateManager gsm, String levelSource) {
		super(gsm);
		this.levelSource = levelSource;
	}
	
	private void initEntities() {
		paddle = new Paddle();
		ball = new Ball();
		bricks = new ArrayList<Brick>();
		drops = new ArrayList<Drop>();
		gun = new Gun(ball);
	}
	
	private void loadEntitiesToScreen() {
		for(Brick brick: bricks) {
			screen.addEntity(brick);
		}
		screen.addEntity(ball);
		screen.addEntity(gun);
		screen.addEntity(paddle);
	}

	public static void loadLevel(String level) {
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
	
	private boolean testForDrop() {
		Random rand = new Random();
		if(rand.nextFloat() <= 0.5f)
			return true;
		return false;
	}
	
	@Override
	public void update() {
		if(currentStage == Stage.PLAY) {
			paddle.update();
			ball.update();
			
			for(Drop d: drops) {
				d.update();
			}
			
			if(bricks.size() == 0) {
				gsm.levelUp();
				return;
			}
			
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
					
				}
			}
			
			// drop collisions
			for(int i = 0; i < drops.size(); i++) {
				Drop drop = drops.get(i);
				if(AABBIntersect(drop.getX(), drop.getY(), drop.getWidth(), drop.getHeight(),
						paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight())) {
					screen.removeEntity(drop);
					drops.remove(drop);
					System.out.println("BLESSUP");
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
		} if(currentStage == Stage.SHOOT) {
			gun.update();
			if(mouse.rightPressed) {
				currentStage = Stage.PLAY;
				gun.shoot();
				gun.setVisible(false);
			}
		}
		
		// remove bricks when their health <= 0
		for(int i = 0; i < bricks.size(); i++) {
			Brick b = bricks.get(i);
						
			if(b.getHealth() <= 0) {
				// percentage chance to drop a buff
				if(testForDrop()) {
					Drop d = new Drop(b.getX() + (Brick.BRICK_HEIGHT / 2) - 35/2, b.getY());
					drops.add(d);
					screen.addEntity(d);
				}
				screen.removeEntity(b);
				bricks.remove(i);
				System.out.println("Rip");
			}
		}
	}

	@Override
	public void init() {
		initEntities();
		loadLevel(levelSource);
		loadEntitiesToScreen();
		currentStage = Stage.SHOOT;
	}

	@Override
	public void cleanUp() {
		screen.cleanUp();
	}
}
