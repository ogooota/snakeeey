package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import main.Panel;

public class Player {
	
	private int x[], y[];
	private int body_parts;
	private Panel panel;
	private int score;
	private Color head_color;
	private Color body_color;
	private int speed;
	private int snake_size;
	private char direction;
	private Random rand;
	private boolean isTimerActive;
	
	public Player(Panel panel) {
		this.rand = new Random();
		this.panel = panel;
		this.x = new int[panel.getAreaInSquares()];
		this.y = new int[panel.getAreaInSquares()];
		this.body_parts = 3;
		this.score = 0;
		this.speed = panel.getUnitSize();
		this.snake_size = panel.getUnitSize();
		this.head_color = Color.green;
		this.body_color = new Color(0, 100, 0);
		this.direction = 'R';
	}
	
	public void activateRainbowMode() {
		final int interval = 1000;
		final int total_seconds = 4000;
		final int[] elapsed_time = {0};
		
		Timer timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				elapsed_time[0]+=interval;
				isTimerActive = true;
				
				for(int i=1;i<256;i++) {
					head_color = new Color(rand.nextInt(i), rand.nextInt(i), rand.nextInt(i));
					body_color = new Color(rand.nextInt(i), rand.nextInt(i), rand.nextInt(i));
				}
				
				System.out.println("Tick: " + (elapsed_time[0] / 10000) + "s");
				
				if (elapsed_time[0] >= total_seconds*10) {
                    ((Timer) e.getSource()).stop();
                    System.out.println("Timer finalizado após 4 segundos.");
                    head_color = Color.green;
    				body_color = new Color(0, 100, 0);
    				isTimerActive = false;
                }
			}
			
		});
		timer.start();
		
	}
	
	public boolean checkWallCollisions() {
		
		if(isTimerActive) {
			if(x[0] > panel.getWidth()) {
				x[0] = 0;
			}
			else if(x[0] < 0) {
				x[0] = panel.getWidth();
			}
			else if(y[0] > panel.getHeight()) {
				y[0] = 0;
			}
			else if(y[0] < 0) {
				y[0] = panel.getHeight();
			}
			return false;
		}
		else {
			if(this.x[0] >= panel.getWidth() || this.y[0] >= panel.getHeight()) {
				return true;
			}
			else if(this.x[0] < 0 || this.y[0] < 0) {
				return true;
			}
			return false;
			
		}
	}
	
	public boolean checkSelfCollision() {
		if(isTimerActive) {
			return false;
		}
		for(int i=1;i<body_parts;i++) {
			if(this.x[0] == this.x[i] && this.y[0] == this.y[i]) {
				return true;
			}
		}
		return false;
	}
	
	public void move() {
		switch(this.direction) {
			case 'U':
				this.y[0]-=speed;
				break;
			case 'L':
				this.x[0]-=speed;
				break;
			case 'D':
				this.y[0]+=speed;
				break;
			case 'R':
				this.x[0]+=speed;
				break;
		}
	}
	public void grow() {
		this.body_parts++;
		this.score++;
		System.out.println("Score: " + this.score);
	}
	public void update() {
		for(int i=body_parts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
	}
	
	public void draw(Graphics g) {
		for(int i=0;i<body_parts;i++) {
			if(i == 0) {
				g.setColor(head_color);
				g.fillRect(this.x[i], this.y[i], snake_size, snake_size);
			}
			else {
				g.setColor(body_color);
				g.fillRect(this.x[i], this.y[i], snake_size, snake_size);
			}
		}
	}
	
	//getters
	public int getBodyParts() {
		return this.body_parts;
	}
	public int getX(int index) {
		return this.x[index];
	}
	public int getY(int index) {
		return this.y[index];
	}
	
	//setters
	public void setDirection(char dir) {
		if(this.direction == 'U' && dir == 'D' || 
		   this.direction == 'D' && dir == 'U' ||
		   this.direction == 'R' && dir == 'L' ||
		   this.direction == 'L' && dir == 'R') {
			System.out.println("Invalid direction");
			return;
		}
		this.direction = dir;
	}
}