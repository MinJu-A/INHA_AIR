package customer.start;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataBase.databaseClass;
import be.main.MainForm;
import be.menu.MenuBar;
import customer.login.LoginForm;

public class StartForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;

	// 폰트
	Font fontArial60 = new Font("Arial", Font.BOLD | Font.ITALIC, 60);
	
	// 색상
	Color colorLogo = new Color(24, 62, 111);	
	
	// 버튼
	private ImageIcon icon = new ImageIcon("imgs/main/inhaair.png");
	private JButton btnStart;
	
	// Forms
	private LoginForm loginF;
	
	// DB 정보
	private String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
	private String dbID="inhaair";
	private String dbPassword="1234";
	
	// 예원 - 시작 화면
	public StartForm() {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);

		setLayout(null);
		
		
		Image image1 = icon.getImage();
		Image image2 = image1.getScaledInstance(600, 330, Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(image2);
		
		// 시작 버튼
//		btnStart = new JButton("INHA AIR");
		btnStart = new JButton(icon2);
		btnStart.setSize(600, 330);
		btnStart.setLocation(240, 180);
		btnStart.setFont(fontArial60);
		btnStart.setForeground(colorLogo);
		btnStart.setBorderPainted(false);
		btnStart.setBackground(Color.WHITE);
		
		// 리스너
		btnStart.addActionListener(this);
		
		// 컴포넌트 붙이기
		add(btnStart);
		
		
		setVisible(true);
		
		// 데이터베이스 연결
		databaseClass.connect(dbURL, dbID, dbPassword);
	}

	public static void main(String[] args) {
		new StartForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnStart) {
			loginF = new LoginForm();
			setVisible(false);
		}
	}
}
