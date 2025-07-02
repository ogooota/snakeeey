package main;

import javax.swing.JOptionPane;

public class Game implements Runnable {
	
	private Panel panel;
	private Window window;
	private Thread thread;
	private boolean isRunning;
	private final int fps_alvo = 120;
	private final int DELAY = 140;
	private long delayTick = System.currentTimeMillis();
	
	public Game() {
		panel = new Panel();
		window = new Window(panel);
		panel.requestFocus();
		startGameLoop();
	}
	
	public void startGameLoop() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stopGameLoop() {
		isRunning = false;
	}
	
	@Override
	public void run() {
		double interval = 1000000000 / fps_alvo;
		long now = System.nanoTime();
		long lastFrame = System.nanoTime();
		int fps = 0;
		long lastCheck = System.currentTimeMillis();
		
		while(isRunning) {
			now = System.nanoTime();
			if(now - lastFrame >= interval) {
				if(System.currentTimeMillis() - delayTick >= DELAY) {
					this.panel.getPlayer().update();
					this.panel.getPlayer().move();
					if(this.panel.getPlayer().checkSelfCollision()) {
						JOptionPane.showMessageDialog(panel, "You hit yourself! Game over.", "Game over.", 0);
						stopGameLoop();
					}
					if(this.panel.getPlayer().checkWallCollisions()) {
						JOptionPane.showMessageDialog(panel, "You hit a wall! Game over.", "Game over.", 0);
						stopGameLoop();
					}
					
					delayTick = System.currentTimeMillis();
				}
				panel.repaint();
				lastFrame = now;
				fps++;
			}
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				System.out.println("FPS: " + fps);
				lastCheck = System.currentTimeMillis();
				fps = 0;
			}
		}
	}
}
