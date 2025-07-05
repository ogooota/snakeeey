package main;

import javax.swing.JFrame;

public class Window {
	private JFrame frame;
	private Panel panel;
	private final String title = "Snake";
	
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