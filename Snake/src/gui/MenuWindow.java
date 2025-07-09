package gui;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuWindow {
	
	private JFrame frame;
	private final String title, btn_start_text, btn_options_text, btn_exit_text;
	private final int menu_width, menu_height;

	
	private int button_size_x, button_size_y;
	private JButton btn_start, btn_options, btn_exit;
	private ModeMenuWindow mode;
	
	private JLabel label;
	
	public MenuWindow() {
		this.title = "Snake Menu";
		this.btn_start_text = "Start Game";
		this.btn_options_text = "Options";
		this.btn_exit_text = "Exit to Desktop";
		this.menu_width = 400;
		this.menu_height = 500;
		this.button_size_x = 112;
		this.button_size_y = 100;

		createWindow();
	}
	
	public void createTitle() {
		label = new JLabel("The Snake");
	}
	
	public void createButtons() {
		
		btn_start = new JButton(this.btn_start_text);
		btn_options = new JButton(this.btn_options_text);
		btn_exit = new JButton(this.btn_exit_text);

		btn_start.setBounds(button_size_x, button_size_y, 160, 40);
		btn_options.setBounds(button_size_x, button_size_y*2, 160, 40);
		btn_exit.setBounds(button_size_x, button_size_y*3, 160, 40);
		
		frame.add(btn_start);
		frame.add(btn_options);
		frame.add(btn_exit);
		
		btn_start.addActionListener(e -> chooseMode());
		btn_options.addActionListener(e -> options());
		btn_exit.addActionListener(e -> System.exit(0));
		
	}
	
	public void createWindow() {
		frame = new JFrame(title);
		frame.pack();
		frame.setSize(menu_width, menu_height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBackground(Color.black);
		frame.setLayout(null);
		createTitle();
		createButtons();
	}
	
	//button functions
	
	//options
	void options() {
		JFrame frame = new JFrame("Options");
		frame.setSize(300,300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		
		
	}
	
	//choosing the mode part
	
	void chooseMode() {
		mode = new ModeMenuWindow(this);
	}
	
	
	//getter desnecessário
	public int getButtonSizeX() {
		return this.button_size_x;
	}
	public int getButtonSizeY() {
		return this.button_size_y;
	}
}

//classe para ajudar (provavelmente meio inútil)
class ModeMenuWindow {
	
	protected JFrame mode_frame;
	protected JButton btn_start, btn_options, btn_exit, btn_normal, btn_zen, btn_speed, btn_infernal, btn_return;
	protected String btn_normal_text, btn_zen_text, btn_speed_text, btn_infernal_text, btn_return_text;
	protected MenuWindow menu;
	
	public ModeMenuWindow(MenuWindow menu) {
		this.menu = menu;
		this.btn_normal_text = "Normal Mode";
		this.btn_zen_text = "Zen Mode";
		this.btn_speed_text = "Speed Mode";
		this.btn_infernal_text = "Infernal Mode";
		this.btn_return_text = "Return";
		createWindow();
		chooseMode();
		
	}
	
	void createWindow() {
		mode_frame = new JFrame("Choose mode");
		mode_frame.setSize(200,200);
		mode_frame.setVisible(true);
		mode_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mode_frame.setLocationRelativeTo(null);
		mode_frame.setResizable(false);
		mode_frame.setLayout(null);
		
		
	}
	void chooseMode() {
		
		btn_normal = new JButton(btn_normal_text);
		btn_zen = new JButton(btn_zen_text);
		btn_speed = new JButton(btn_speed_text);
		btn_infernal = new JButton(btn_infernal_text);
		btn_return = new JButton(btn_return_text);
		
		btn_normal.setBounds(menu.getButtonSizeX()-80, menu.getButtonSizeY()-90, 120, 20);
		btn_zen.setBounds(menu.getButtonSizeX()-80, menu.getButtonSizeY()-60, 120, 20);
		btn_speed.setBounds(menu.getButtonSizeX()-80, menu.getButtonSizeY()-30, 120, 20);
		btn_infernal.setBounds(menu.getButtonSizeX()-80, menu.getButtonSizeY(), 120, 20);
		btn_return.setBounds(menu.getButtonSizeX()-80, menu.getButtonSizeY()+30, 120, 20);
		
		mode_frame.add(btn_normal);
		mode_frame.add(btn_zen);
		mode_frame.add(btn_speed);
		mode_frame.add(btn_infernal);
		mode_frame.add(btn_return);
		
		btn_normal.addActionListener(e -> startGame(GameMode.NORMAL));
		btn_zen.addActionListener(e -> startGame(GameMode.ZEN));
		btn_speed.addActionListener(e -> startGame(GameMode.SPEED));
		btn_infernal.addActionListener(e -> startGame(GameMode.INFERNAL));
		btn_return.addActionListener(e -> mode_frame.dispose());
	}
	
	void startGame(GameMode mode) {
		switch(mode) {
		case GameMode.NORMAL:
			new main.Game(GameMode.NORMAL);
			mode_frame.dispose();
			break;
		case GameMode.ZEN:
			new main.Game(GameMode.ZEN);
			mode_frame.dispose();
			break;
		case GameMode.SPEED:
			new main.Game(GameMode.SPEED);
			mode_frame.dispose();
			break;
		case GameMode.INFERNAL:
			mode_frame.dispose();
			new main.Game(GameMode.INFERNAL);
			break;
		}
	}
}