package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import gui.GameMode;
import inputs.Keyboard;
import obj.Apple;
import obj.GoldenApple;
import obj.Player;

public class Panel extends JPanel {
	
	private final int width, height, unit_size, area_px, area_squares, cols, rows;
	private Random rand;
	
	private final int[][] grid;
	
	private Player player;
	private Apple apple;
	private GoldenApple golden_apple;
	private boolean isGolden;
	private int golden_apple_chance;
	
	//configurando modos
	private GameMode mode;
	
	public Panel(GameMode mode) {
		requestFocus();
		this.mode = mode;
		this.unit_size = 25;
		
		if(mode == GameMode.SPEED) {
			
		}
		else if(mode == GameMode.ZEN) {
				
		}
		else if(mode == GameMode.INFERNAL) {
			
		}
		
		this.width = 600;
		this.height = 400;
		this.area_px = width*height;
		this.area_squares = area_px / unit_size;
		this.cols = width / unit_size;
		this.rows = height / unit_size;
		setPanelSize();
		this.grid = new int[cols][rows];
		this.player = new Player(this);
		this.apple = new Apple(this);
		this.golden_apple = new GoldenApple(this);
		addKeyListener(new Keyboard(this));
		this.golden_apple_chance = golden_apple.getRandomChance();
		
	}
	public void setRandomChance() {
		this.golden_apple_chance = golden_apple.getRandomChance();
	}
	
	public void drawGrid(Graphics g) {
		for(int k=0;k<grid.length;k++) {
			for(int i=0;i<grid[0].length;i++) {
				g.setColor(Color.DARK_GRAY);
				g.drawRect(k*unit_size, i*unit_size, unit_size, unit_size);
			}
		}
	}
	
	public void setPanelSize() {
		Dimension d = new Dimension(width, height);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//background color
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		//grid
		drawGrid(g);
		
		//player
		player.draw(g);
		
		//apple
		if(golden_apple_chance <= 10) {
			isGolden = true;
			golden_apple.draw(g);
		}
		else {
			isGolden = false;
			apple.draw(g);
		}
		
		
	}
	
	//getters
	public int getGoldenAppleChance() {
		return golden_apple_chance;
	}
	public boolean isGolden() {
		return this.isGolden;
	}
	public GoldenApple getGoldenApple() {
		return this.golden_apple;
	}
	public int getAreaInSquares() {
		return area_squares;
	}
	public Apple getApple() {
		return this.apple;
	}
	public Player getPlayer() {
		return this.player;
	}
	public int getUnitSize() {
		return this.unit_size;
	}
	public int getCols() {
		return this.cols;
	}
	public int getRows() {
		return this.rows;
	}
}