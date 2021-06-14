package customer.start;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import be.main.MainForm;
import be.menu.MenuBar;
import customer.book.BookForm;
import customer.findTrips.FindTripsForm;
import customer.flightSchedule.FlightStatusForm;
import customer.login.LoginForm;

public class MainMenuForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;

	// 폰트
	Font fontArial45 = new Font("Arial", Font.BOLD, 45);
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	
	// 색상
	Color colorLogo = new Color(24, 62, 111);	
	Color colorBtn = new Color(10,90,150);
	
	// ID 값 저장
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	// 컴포넌트
	private JLabel lblLogo;
	private JPanel jpMenu;
	private JButton btnBook, btnFindTrips, btnFilghtStatus;
	
	private LoginForm loginF;
	
	// Forms
	private BookForm bookForm;
	private FlightStatusForm flightStatusForm;
	private FindTripsForm findTripsForm;
	private JButton btnSignOut;
	
	public MainMenuForm() {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		
		
		// 레이아웃 설정
		setLayout(null);
		
		// 로고
		lblLogo = new JLabel("INHA AIR");
		lblLogo.setFont(fontArial45);
		lblLogo.setForeground(colorLogo);
		lblLogo.setHorizontalAlignment(JLabel.CENTER);
		lblLogo.setSize(1000, 50);
		lblLogo.setLocation(50, 130);
		lblLogo.setBackground(Color.YELLOW);
		
		// 메뉴 패널
		setMenu();
		
		// 컴포넌트 붙이기
		add(lblLogo);
		add(jpMenu);
		
		setVisible(true);
	}

	// 예원 - 메뉴 패널
	private void setMenu() {
		
		jpMenu = new JPanel(new GridLayout(4, 1, 30, 30));
		jpMenu.setSize(400, 400);
		jpMenu.setLocation(355, 180);
		jpMenu.setBorder(new EmptyBorder(50, 50, 50, 50));
		jpMenu.setBackground(Color.WHITE);
		
		btnBook = new JButton("예매");
		btnBook.setFont(fontNanumGothic20);
		btnBook.setBackground(colorBtn);
		btnBook.setForeground(Color.WHITE);
//		btnBook.setBorder(new EtchedBorder(colorLogo, colorBtn));		// 테두리
		btnBook.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnFindTrips = new JButton("예매 조회");
		btnFindTrips.setFont(fontNanumGothic20);
		btnFindTrips.setBackground(colorBtn);
		btnFindTrips.setForeground(Color.WHITE);
		btnFindTrips.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnFilghtStatus = new JButton("항공편 현황");
		btnFilghtStatus.setFont(fontNanumGothic20);
		btnFilghtStatus.setBackground(colorBtn);
		btnFilghtStatus.setForeground(Color.WHITE);
		btnFilghtStatus.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//연우 - 로그아웃 버튼 추가
		btnSignOut = new JButton("로그아웃");
		btnSignOut.setFont(fontNanumGothic20);
		btnSignOut.setBackground(colorBtn);
		btnSignOut.setForeground(Color.WHITE);
		btnSignOut.setHorizontalTextPosition(SwingConstants.CENTER);
		
		// 리스너
		btnBook.addActionListener(this);
		btnFindTrips.addActionListener(this);
		btnFilghtStatus.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		// 컴포넌트 붙이기
		jpMenu.add(btnBook);
		jpMenu.add(btnFindTrips);
		jpMenu.add(btnFilghtStatus);
		jpMenu.add(btnSignOut);
		
		
	}

	public static void main(String[] args) {
		new MainMenuForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnBook) {
			bookForm = new BookForm(id);
			this.setVisible(false);
			
		} else if(obj == btnFindTrips) {
			findTripsForm = new FindTripsForm(id);
			this.setVisible(false);
			
		} else if(obj == btnFilghtStatus) {
			flightStatusForm = new FlightStatusForm(id);
			this.setVisible(false);
			
		} else if (obj == btnSignOut) {
			id = "";
			loginF = new LoginForm();
			this.setVisible(false);
		}
	}
}
