package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import main.Panel;

public class Player {
	
	private Panel panel;
	private int[] x;
	private int[] y;
	private int body_parts;
	private int score;
	private char direction;
	private Color head_color;
	private Color body_color;
	
	public Player(Panel panel) {
		this.panel = panel;
		this.x = new int[panel.getAreaInSquares()];
		this.y = new int[panel.getAreaInSquares()];
		this.body_parts = 3;
		this.score = 0;
		this.direction = 'R';
		this.head_color = Color.green;
		this.body_color = new Color(0, 100, 0);
	}
	
	public void move() {
		switch(this.direction) {
			case 'U':
				y[0]-=panel.getUnitSize();
				break;
			case 'L':
				x[0]-=panel.getUnitSize();
				break;
			case 'D':
				y[0]+=panel.getUnitSize();
				break;
			case 'R':
				x[0]+=panel.getUnitSize();
				break;
		}
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
				g.fillRect(this.x[i], this.y[i], panel.getUnitSize(), panel.getUnitSize());
			}
			else {
				g.setColor(body_color);
				g.fillRect(this.x[i], this.y[i], panel.getUnitSize(), panel.getUnitSize());				
			}
		}
	}
	public void grow() {
		this.score++;
		this.body_parts++;
	}
	
	public void activateRainbowMode() {
		
		
		ActionListener tarefa = new ActionListener() {	
			int seconds = 0;
			Random rand = new Random();
			Color color, color2;
			public void actionPerformed(ActionEvent e) {
				System.out.println("Modo rainbow ativado! " + seconds);
				for(int i=0;i<panel.getPlayer().getBodyParts();i++) {
					color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
					color2 = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
					setSnakeColor(color, color2);
					if(seconds >= 40000) {
						break;
					}
					seconds+=300;
				
				}		
			}
		};
		
		Timer timer = new Timer(100, tarefa);
		timer.start();
	}
	
	
	
	public boolean checkWallCollisions() {
		if(this.x[0] >= panel.getWidth() || this.x[0] < 0) {
			return true;
		}
		else if(this.y[0] >= panel.getHeight() || this.y[0] < 0) {
			return true;
		}
		return false;
	}
	public boolean checkSelfCollision() {
		for(int i=0;i<body_parts;i++) {
			if(i != 0 && x[0] == x[i] && y[0] == y[i]) {
				return true;
			}
		}
		return false;
	}
	
	//getters
	public int getX(int index) {
		return this.x[index];
	}
	public int getY(int index) {
		return this.y[index];
	}
	public int getScore() {
		return this.score;
	}
	public int getBodyParts() {
		return this.body_parts;
	}
	//setters
	public void setDirection(char dir) {
		if(this.direction == 'U' && dir == 'D' ||
		   this.direction == 'D' && dir == 'U' ||
		   this.direction == 'R' && dir == 'L' ||
		   this.direction == 'L' && dir == 'R') 
		{
			System.out.println("Illegal direction typed.");
			return;
		}
		this.direction = dir;
	}
	public void setSnakeColor(Color color, Color color2) {
		this.head_color = color;
		this.body_color = color2;
	}
}
