package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.Panel;

public class Apple {
	protected int x, y;
	protected Color apple_color;
	protected Random rand;
	protected Panel panel;
	protected int apple_size;
	
	public Apple(Panel panel) {
		this.panel = panel;
		this.rand = new Random();
		this.apple_size = panel.getUnitSize();
		this.apple_color = Color.red;
		newApple();
	}
	
	public void newApple() {
		this.x = rand.nextInt(panel.getCols()) * panel.getUnitSize();
		this.y = rand.nextInt(panel.getRows()) * panel.getUnitSize();
	}
	
	public void update() {
		if(this.x == panel.getPlayer().getX(0) && this.y == panel.getPlayer().getY(0)) {
			newApple();
			for(int i=0;i<panel.getPlayer().getBodyParts();i++) {
				if(this.x == panel.getPlayer().getX(i) && this.y == panel.getPlayer().getY(i)) {
					newApple();
				}
			}
			panel.setRandomChance();
			System.out.println("A chance foi de " + panel.getGoldenAppleChance());
			panel.getPlayer().grow();
		}
	}
	public void draw(Graphics g) {
		g.setColor(apple_color);
		g.fillRect(this.x, this.y, apple_size, apple_size);
	}
	
	public int getRandomChance() {
		return rand.nextInt(100);
	}
}