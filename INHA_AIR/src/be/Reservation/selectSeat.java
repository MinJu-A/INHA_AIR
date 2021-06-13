package be.Reservation;
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
import be.menu.MenuBar;

public class selectSeat extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "좌석";
	private int width = 760, height = 700;
	
	public selectSeat() {
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
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new selectSeat();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
