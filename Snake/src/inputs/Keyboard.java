package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Panel;

public class Keyboard implements KeyListener {
	private Panel panel;
	
	public Keyboard(Panel panel) {
		this.panel = panel;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_P) {
			panel.getApple().newApple();
			System.out.println("New apple generated!");
		}
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				panel.getPlayer().setDirection('U');
				break;
			case KeyEvent.VK_A:
				panel.getPlayer().setDirection('L');
				break;
			case KeyEvent.VK_S:
				panel.getPlayer().setDirection('D');
				break;
			case KeyEvent.VK_D:
				panel.getPlayer().setDirection('R');
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
