package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.IllegalFormatWidthException;

import main.Panel;

public class Player {
	
	private Panel panel;
	private int[] x;
	private int[] y;
	private int body_parts;
	private int score;
	private char direction;
	
	public Player(Panel panel) {
		this.panel = panel;
		this.x = new int[panel.getAreaInSquares()];
		this.y = new int[panel.getAreaInSquares()];
		this.body_parts = 3;
		this.score = 0;
		this.direction = 'R';
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
				g.setColor(Color.green);
				g.fillRect(this.x[i], this.y[i], panel.getUnitSize(), panel.getUnitSize());
			}
			else {
				g.setColor(new Color(0,100,0));
				g.fillRect(this.x[i], this.y[i], panel.getUnitSize(), panel.getUnitSize());				
			}
		}
	}
	public void grow() {
		this.score++;
		this.body_parts++;
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
}
