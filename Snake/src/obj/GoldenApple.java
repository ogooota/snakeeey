 package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.Panel;

public class GoldenApple extends Apple {
	
	public GoldenApple(Panel panel) {
		super(panel);
		// TODO Auto-generated constructor stub
		this.apple_color = new Color(218, 165, 32);
	}
	
	@Override
	public void update() {
		if(this.x == panel.getPlayer().getX(0) && this.y == panel.getPlayer().getY(0)) {
			newApple();
			panel.getPlayer().activateRainbowMode();
			panel.setRandomChance();
			System.out.println("A chance foi de " + panel.getGoldenAppleChance());
			panel.getPlayer().grow();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(apple_color);
		g.fillRect(this.x, this.y, apple_size, apple_size);
	}
	
}