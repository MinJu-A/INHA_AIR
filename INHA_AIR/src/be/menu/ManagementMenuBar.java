package be.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Management.AirPort.AirportList;
import Management.Airway.AirwayList;
import Management.Payment.PaymentList;
import Management.User.UserList;
import be.main.MainForm;
import be.sign.SignIn;


// 예원 - 메뉴바관리자용으로 수정
public class ManagementMenuBar extends JFrame implements ActionListener {
	
	// 폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	Font fontArial36 = new Font("Arial", Font.BOLD | Font.ITALIC, 36);
	Font fontDialog = new Font("Dialog", Font.BOLD, 35);
	Font fontMonospaced = new Font("monospaced", Font.BOLD, 35);
	Font fontSansSerif = new Font("SansSerif", Font.BOLD, 35);
	
	// 폰트 사용 위함
//	private MainForm main;
	
	// 상위 메뉴 관련 
	private JPanel jpTOP, jpMenu;
	private JButton btnLogo, btnUser, btnAirway, btnAirline, btnAirport, btnLogout, btnPay;
	private SignIn singIn;
	private UserList userlist;
	private PaymentList paymentlist;
	private AirwayList airwaylist;
	private AirportList airportlist;
	private AirportList airlinelist;
		
	public JPanel getPnTOP() {
		return jpTOP;
	}

	public void setPnTOP(JPanel pnTOP) {
		this.jpTOP = pnTOP;
	}
	
	

	public ManagementMenuBar() {
		// 상단 판넬
		jpTOP = new JPanel();		// 상단 판넬 생성
		jpTOP.setLayout(null);		// 상단 판넬 배치관리자 설정
		jpTOP.setSize(1120, 80);	// 상단 판넬 사이즈 설정
		jpTOP.setLocation(0, 0);	// 상단 판넬 위치 설정
		jpTOP.setBackground(Color.WHITE);	// 상단 패널 배경색 설정
			
		// 로고 버튼
		btnLogo = new JButton("INHA AIR");
		btnLogo.setFont(fontArial36);
		btnLogo.setSize(200, 70);
		btnLogo.setLocation(10, 5);
		btnLogo.addActionListener(this);
		btnLogo.setBackground(Color.WHITE);
//		btnLogo.setForeground(new Color(25, 25, 112));	// 글자색 변경
//		btnLogo.setForeground(new Color(0, 12, 61));	// 글자색 변경
		btnLogo.setForeground(new Color(24, 62, 111));	// 글자색 변경
		btnLogo.setBorderPainted(false);
//		btnLogo.setContentAreaFilled(false);
		btnLogo.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLogo.setVerticalAlignment(SwingConstants.BOTTOM);
		
		// 메뉴 선택 판넬
		jpMenu = new JPanel();
		jpMenu.setLayout(null);
//		pnMenu.setSize(700, 70);
		jpMenu.setSize(1000, 50);
//		pnMenu.setLocation(250, 5);
		jpMenu.setLocation(70, 40);
//		pnMenu.setBackground(Color.LIGHT_GRAY);
		
		// 메뉴 버튼
		btnUser = new JButton("회원관리");			// 예매 버튼 생성
		btnUser.setSize(150, 40);				// 버튼 사이즈 지정
//		btnBook.setLocation(10, 0);				// 버튼 위치 지정
		btnUser.setLocation(200, 0);				// 버튼 위치 지정
		btnUser.addActionListener(this);
		btnUser.setFont(fontNanumGothic18);		
//		btnBook.setBackground(Color.LIGHT_GRAY);	// 버튼 배경색 설정
		btnUser.setBorderPainted(false);		// 버튼 테두리 제거
		btnUser.setContentAreaFilled(false);	// 버튼 내용영역 채우기 제거
//		btnBook.setFocusPainted(false);			// 버튼 포커스 시 테두리 제거
				
		btnAirway = new JButton("항공편");
		btnAirway.setSize(150, 40);
//		btnAirport.setLocation(180, 0);
		btnAirway.setLocation(350, 0);
		btnAirway.addActionListener(this);
		btnAirway.setFont(fontNanumGothic18);
		btnAirway.setBorderPainted(false);
		btnAirway.setContentAreaFilled(false);
		
		btnAirline = new JButton("항공사");
		btnAirline.setSize(150, 40);
//		btnInFight.setLocation(350, 0);
		btnAirline.setLocation(500, 0);
		btnAirline.addActionListener(this);
		btnAirline.setFont(fontNanumGothic18);
		btnAirline.setBorderPainted(false);
		btnAirline.setContentAreaFilled(false);
			
		btnAirport = new JButton("공항");
		btnAirport.setSize(150, 40);
//		btnMyPage.setLocation(520, 0);
		btnAirport.setLocation(650, 0);
		btnAirport.addActionListener(this);
		btnAirport.setFont(fontNanumGothic18);
		btnAirport.setBorderPainted(false);
		btnAirport.setContentAreaFilled(false);
		
		btnPay = new JButton("결제");
		btnPay.setSize(150,40);
		btnPay.setLocation(800, 0);
		btnPay.addActionListener(this);
		btnPay.setFont(fontNanumGothic18);
		btnPay.setBorderPainted(false);
		btnPay.setContentAreaFilled(false);
		
		
				
		// 로그인 버튼
		btnLogout = new JButton("<HTML><U>LOGOUT</U></HTML>");
		btnLogout.setSize(80, 50);
//		btnLogin.setLocation(970, 35);
		btnLogout.setLocation(970, 5);
		btnLogout.addActionListener(this);
		btnLogout.setFont(fontArial);
		btnLogout.setBorderPainted(false);
		btnLogout.setContentAreaFilled(false);
				
		jpTOP.add(btnLogo);
		jpTOP.add(jpMenu);
		
		jpMenu.add(btnUser);
		jpMenu.add(btnAirway);
		jpMenu.add(btnAirline);
		jpMenu.add(btnAirport);
		jpMenu.add(btnPay);
			
		jpTOP.add(btnLogout);
				
		add(jpTOP);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnLogo) {
			
		} else if(obj == btnLogout){
			int result = JOptionPane.showConfirmDialog(this, "정말 로그아웃 하시겠습니까?", "로그아웃",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION ) {
				JOptionPane.showMessageDialog(null, "시스템을 종료합니다");
				System.exit(0);
			} else {
				JOptionPane.showMessageDialog(null, "로그아웃을 취소합니다.");
			}
		} else if(obj == btnUser) {
			dispose();
			userlist = new UserList();
		} else if(obj == btnAirway) {
			dispose();
			airwaylist = new AirwayList();
		} else if(obj == btnAirline) {
			this.dispose();
			airlinelist = new AirportList();
		} else if(obj == btnAirport) {
			this.dispose();
			airportlist = new AirportList();
		} else if(obj == btnPay) {
			this.dispose();
			paymentlist = new PaymentList();
		} 
	}
}
