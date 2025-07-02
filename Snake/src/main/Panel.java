package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import inputs.Keyboard;
import obj.Apple;
import obj.GoldenApple;
import obj.Player;

public class Panel extends JPanel {
	
	private final int width = 600;
	private final int height = 400;
	private final int unit_size = 25;
	private final int area_px = width*height;
	private final int area_squares = area_px / unit_size;
	private final int amount_of_squares_x = width / unit_size;
	private final int amount_of_squares_y = height / unit_size;
	
	
	
	private final int[][] grid = new int[amount_of_squares_x][amount_of_squares_y];
	
	protected Player player;
	protected Apple apple;
	protected GoldenApple golden_apple;
	protected Random rand;
	protected int chance;
	
	public Panel() {
		setPanelSize();
		addKeyListener(new Keyboard(this));
		setFocusable(true);
		
		player = new Player(this);
		apple = new Apple(this);
		golden_apple = new GoldenApple(this);
		rand = new Random();
		chance = rand.nextInt(100);
	}
	

	public void setPanelSize() {
		Dimension d = new Dimension(width,height);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
	}

	public void drawGrid(Graphics g) {
		for(int k=0;k<grid.length;k++) {
			for(int i=0;i<grid[0].length;i++) {
				g.setColor(Color.DARK_GRAY);
				g.drawRect(k*unit_size, i*unit_size, unit_size, unit_size);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//background color
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		//grid
		drawGrid(g);
		
		//apple
		if(chance <= 75) {
			golden_apple.update();
			golden_apple.draw(g);
		}
		else {
			apple.update();
			apple.draw(g);
		}
		
		//player
		player.draw(g);
		
	}
	
	//getters
	public int getUnitSize() {
		return unit_size;
	}
	public int getAreaInSquares() {
		return area_squares;
	}
	public int getAmountOfSquaresX() {
		return amount_of_squares_x;
	}
	public int getAmountOfSquaresY() {
		return amount_of_squares_y;
	}
	public Player getPlayer() {
		return player;
	}
	public Apple getApple() {
		return apple;
	}
	
}
