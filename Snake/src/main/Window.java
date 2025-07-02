package main;

import javax.swing.JFrame;

public class Window {
	private Panel panel;
	private JFrame frame;
	private final String title = "The Snake";
	
	public Window(Panel panel) {
		this.panel = panel;
		createWindow();
	}
	
	public void createWindow() {
		frame = new JFrame(title);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
}
