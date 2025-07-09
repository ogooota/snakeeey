package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Panel;

public class Keyboard implements KeyListener {
	private Panel panel; 
	private char UP, DOWN, LEFT, RIGHT;
	
	
	public Keyboard(Panel panel) {
		this.panel = panel;
		this.UP = 'U';
		this.DOWN = 'D';
		this.LEFT = 'L';
		this.RIGHT = 'R';
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			panel.getPlayer().setDirection(UP);
			break;
		case KeyEvent.VK_A:
			panel.getPlayer().setDirection(LEFT);
			break;
		case KeyEvent.VK_S:
			panel.getPlayer().setDirection(DOWN);
			break;
		case KeyEvent.VK_D:
			panel.getPlayer().setDirection(RIGHT);
			break;
			
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}