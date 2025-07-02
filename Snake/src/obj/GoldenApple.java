package obj;

import java.awt.Color;
import java.awt.Graphics;

import main.Panel;

public class GoldenApple extends Apple {
	
	public GoldenApple(Panel panel) {
		super(panel);
		
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(255,215,0));
		g.fillRect(this.x, this.y, panel.getUnitSize(), panel.getUnitSize());
	}
}
