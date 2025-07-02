package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.Panel;

public class Apple {
	
	protected Random rand;
	protected Panel panel;
	protected int x;
	protected int y;
	protected int chance;
	
	public Apple(Panel panel) {
		this.panel = panel;
		this.rand = new Random();
		newApple();
	}
	
	public void newApple() {
		chance = rand.nextInt(100);
		this.x = rand.nextInt(panel.getAmountOfSquaresX()) * panel.getUnitSize();
		this.y = rand.nextInt(panel.getAmountOfSquaresY()) * panel.getUnitSize();
	}
	
	public void update() {
		if(panel.getPlayer().getX(0) == this.x && panel.getPlayer().getY(0) == this.y) {
			panel.getPlayer().grow();
			newApple();
			for(int i=0;i<panel.getPlayer().getBodyParts();i++) {
				if(this.x == panel.getPlayer().getX(i) && this.y == panel.getPlayer().getY(i)) {
					newApple();
					break;
				}
			}
			System.out.println("Score: " + panel.getPlayer().getScore());
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.x, this.y, panel.getUnitSize(), panel.getUnitSize());
	}
	
	//getters
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getChance( ) {
		return this.chance;
	}
}
