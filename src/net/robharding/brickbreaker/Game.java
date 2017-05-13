package net.robharding.brickbreaker;

import java.awt.Point;

import javax.swing.JFrame;

import net.robharding.brickbreaker.states.GameStateManager;

public class Game extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	// Window Constants
	public static final int WIDTH = 600;
	public static final int HEIGHT = 700;
	public static final String TITLE = "Brick Breaker";
	
	public static Keyboard keyBoard;
	public static Mouse mouse;
	
	private GameStateManager gsm;
	
	private Thread thread;
	private boolean isRunning = false;
	
	public static Screen screen;
	
	public static Game game;
	
	private static int mousex = 0, mousey = 0;
	
	public Game() {
		super(TITLE);
		start();
	}
	
	private void initFrame() {
		add(screen);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public synchronized void start() {
		screen = new Screen(WIDTH, HEIGHT);
		
		keyBoard = new Keyboard();
		addKeyListener(keyBoard);
		screen.addKeyListener(keyBoard);
		
		mouse = new Mouse();
		addMouseListener(mouse);
		screen.addMouseListener(mouse);
		
		initFrame();
		
		gsm = new GameStateManager();

		isRunning = true;
		
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		double ns = 1000000000.0 / 60.0;
		double delta = 0;

		long lastTime = System.nanoTime();
		
		while(isRunning) {
			long now = System.nanoTime();
			
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				delta--;
			}
			
			render();
		}
		
		stop();
	}
	
	private void update() {
		gsm.update();
		keyBoard.update();
		mouse.update();
	}
	
	private void render() {
		screen.render();
	}
	
	public static void main(String[] args) {
		game = new Game();
	}
	
	public static Point getMousePos() {
		Point position = game.getMousePosition();
		if(position == null) {
			return new Point(mousex, mousey);
		}
		
		mousex = (int)position.getX();
		mousey = (int)position.getY();
		return position;
	}

}
