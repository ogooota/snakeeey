package main;

import javax.swing.JOptionPane;

public class Game implements Runnable {
	private Window window;
	private Panel panel;
	private Thread thread;
	private final int fps_alvo = 120;
	private final int delay = 140;
	private long delayTick = System.currentTimeMillis();
	private boolean isRunning;
	
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
		// TODO Auto-generated method stub
		double interval = 1000000000 / fps_alvo;
		long now = System.nanoTime();
		long lastFrame = System.nanoTime();
		int fps  = 0;
		long lastCheck = System.currentTimeMillis();
		
		while(isRunning) {
			now = System.nanoTime();
			if(now - lastFrame >= interval) {
				if(System.currentTimeMillis() - delayTick >= delay) {
					panel.getPlayer().update();
					panel.getPlayer().move();
					if(panel.isGolden()) {
						panel.getGoldenApple().update();
					}
					else {
						panel.getApple().update();		
					}
					
					if(panel.getPlayer().checkWallCollisions()) {
						JOptionPane.showMessageDialog(null, "You hit a wall! Game over.", "Game over", 0);
						stopGameLoop();
					}
					else if(panel.getPlayer().checkSelfCollision()) {
						JOptionPane.showMessageDialog(null, "You hit yourself! Game over.", "Game over", 0);
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