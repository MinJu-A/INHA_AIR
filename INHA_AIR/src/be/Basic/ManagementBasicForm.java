package be.Basic;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.main.MainForm;
import be.menu.ManagementMenuBar;
import be.menu.MenuBar;

public class ManagementBasicForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 폰트 사용 위함
//	private MainForm main;
	
	// 상단 메뉴바
	private be.menu.ManagementMenuBar menubar;
	
	
	public ManagementBasicForm() {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 레이아웃 설정
		setLayout(null);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		
		// 상단 메뉴바
		setUpMenu();
		
		setVisible(true);
	}


	// 상단 메뉴바
	private void setUpMenu() {
		menubar = new ManagementMenuBar();
		add(menubar.getPnTOP());
	}


	public static void main(String[] args) {
		new ManagementBasicForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
