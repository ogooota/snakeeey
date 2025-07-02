package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import main.Panel;


public class GoldenApple extends Apple {
	
	private Color golden_apple_color = new Color(255,215,0);
	
	public GoldenApple(Panel panel) {
		super(panel);
	}
	
	
	@Override
	public void update() {
		if(panel.getPlayer().getX(0) == this.x && panel.getPlayer().getY(0) == this.y) {
			panel.getPlayer().grow();
			panel.getPlayer().activateRainbowMode();
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
	
	@Override
	public void draw(Graphics g) {
		g.setColor(golden_apple_color);
		g.fillRect(this.x, this.y, panel.getUnitSize(), panel.getUnitSize());
	}
}
