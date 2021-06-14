package Management.Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Management.AirPort.AirportList;
import Management.Airplane.AirplaneList;
import Management.Airway.AirwayList;
import Management.Payment.PaymentList;
import Management.User.UserList;
import be.sign.SignIn;
import customer.login.LoginForm;

public class MainForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "Management";
	private int width = 1120, height = 770;
	
	//변수
	private JPanel jpTOP, jpMenu;
	private JButton btnLogo, btnUser, btnAirway, btnAirline, btnAirport, btnLogout, btnPay, btnAirplane;
	private LoginForm signIn;
	private UserList userlist;
	private PaymentList paymentlist;
	private AirwayList airwaylist;
	private AirportList airportlist;
	private AirplaneList airplanelist;
	private int result;
	
// 폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	Font fontArial36 = new Font("Arial", Font.BOLD | Font.ITALIC, 36);
	Font fontDialog = new Font("Dialog", Font.BOLD, 35);
	Font fontMonospaced = new Font("monospaced", Font.BOLD, 35);
	Font fontSansSerif = new Font("SansSerif", Font.BOLD, 35);
	
	// 리스트 부문
	private JPanel jpAirway;
	private MainForm mainform;
	
	
	public MainForm() {
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
		
		// 회원 리스트
		setUserlist();
		
		setVisible(true);
	}


	private void setUserlist() {
		
		jpAirway = new JPanel();
		jpAirway.setSize(1100, 635);
		jpAirway.setLocation(3, 90);
		jpAirway.setBackground(Color.WHITE);
//		jpUser.setBorder(new EtchedBorder(EtchedBorder.RAISED)); 선 설정
	}


	// 상단 메뉴바
	private void setUpMenu() {
		jpTOP = new JPanel();		// 상단 판넬 생성
		jpTOP.setLayout(null);		// 상단 판넬 배치관리자 설정
		jpTOP.setSize(1120, 80);	// 상단 판넬 사이즈 설정
		jpTOP.setLocation(0, 0);	// 상단 판넬 위치 설정
		jpTOP.setBackground(Color.WHITE);	// 상단 패널 배경색 설정
		
		//로고
		btnLogo = new JButton("INHA AIR");
		btnLogo.setFont(fontArial36);
		btnLogo.setSize(200, 70);
		btnLogo.setLocation(10, 5);
		btnLogo.addActionListener(this);
		btnLogo.setBackground(Color.WHITE);
		btnLogo.setForeground(new Color(24, 62, 111));	// 글자색 변경
		btnLogo.setBorderPainted(false);
		btnLogo.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLogo.setVerticalAlignment(SwingConstants.BOTTOM);
		
		
		//메뉴 선택 판넬
		jpMenu = new JPanel();
		jpMenu.setLayout(null);
		jpMenu.setSize(1000, 50);
		jpMenu.setLocation(70, 40);
		
		//메뉴
		btnUser = new JButton("회원관리");			
		btnUser.setSize(150, 40);	
		btnUser.setLocation(200, 0);				
		btnUser.addActionListener(this);
		btnUser.setFont(fontNanumGothic18);	
		btnUser.setBorderPainted(false);		
		btnUser.setContentAreaFilled(false);
		
		btnAirway = new JButton("항공편");
		btnAirway.setSize(150, 40);
		btnAirway.setLocation(350, 0);
		btnAirway.addActionListener(this);
		btnAirway.setFont(fontNanumGothic18);
		btnAirway.setBorderPainted(false);
		btnAirway.setContentAreaFilled(false);
		
		btnAirport = new JButton("공항");
		btnAirport.setSize(150, 40);
		btnAirport.setLocation(500, 0);
		btnAirport.addActionListener(this);
		btnAirport.setFont(fontNanumGothic18);
		btnAirport.setBorderPainted(false);
		btnAirport.setContentAreaFilled(false);
		
		btnAirplane = new JButton("비행기");
		btnAirplane.setSize(150, 40);
		btnAirplane.setLocation(650, 0);
		btnAirplane.addActionListener(this);
		btnAirplane.setFont(fontNanumGothic18);
		btnAirplane.setBorderPainted(false);
		btnAirplane.setContentAreaFilled(false);
		
		btnPay = new JButton("매출");
		btnPay.setSize(150, 40);
		btnPay.setLocation(800, 0);
		btnPay.addActionListener(this);
		btnPay.setFont(fontNanumGothic18);
		btnPay.setBorderPainted(false);
		btnPay.setContentAreaFilled(false);
		
		
		// 로그아웃
		btnLogout = new JButton("<HTML><U>LOGOUT</U></HTML>");
		btnLogout.setSize(80, 50);
		btnLogout.setLocation(970, 5);
		btnLogout.addActionListener(this);
		btnLogout.setFont(fontArial);
		btnLogout.setBorderPainted(false);
		btnLogout.setContentAreaFilled(false);
		
		
		jpTOP.add(btnLogo);
		jpTOP.add(jpMenu);
		
		jpMenu.add(btnUser);
		jpMenu.add(btnAirway);
		jpMenu.add(btnAirport);
		jpMenu.add(btnAirplane);
		jpMenu.add(btnPay);
		
		jpTOP.add(btnLogout);
		add(jpTOP);
	}


	public static void main(String[] args) {
		new MainForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	Object obj = e.getSource();
		
		if(obj == btnLogo) {
			result = JOptionPane.showConfirmDialog(this, "메인으로 돌아가시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(this, "메인으로 돌아갑니다.");
				dispose();
				mainform = new MainForm();
			}else {
				JOptionPane.showMessageDialog(this, "메인으로 돌아가지 않습니다.");
			}
			
		} else if(obj == btnLogout){
			result = JOptionPane.showConfirmDialog(this, "정말 로그아웃 하시겠습니까?", "알림",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION ) {
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
				dispose();
				signIn = new LoginForm();
			} else {
				JOptionPane.showMessageDialog(null, "로그아웃을 취소합니다.");
			}
		} else if(obj == btnUser) {
			dispose();
			userlist = new UserList();
		} else if(obj == btnAirway) {
			dispose();
			airwaylist = new AirwayList();
		} else if(obj == btnAirport) {
			dispose();
			airportlist = new AirportList();
		} else if(obj == btnPay) {
			dispose();
			paymentlist = new PaymentList();
		} else if(obj == btnAirplane) {
			dispose();
			airplanelist = new AirplaneList();
		}

	}
}
