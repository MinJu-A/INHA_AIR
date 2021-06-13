package be.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import be.main.MainForm;
import be.sign.SignIn;

// 예원 - 메뉴바
public class MenuBar extends JFrame implements ActionListener {
	
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
	private JButton btnLogo, btnBook, btnAirport, btnInFight, btnMyPage, btnLogin;
		
	public JPanel getPnTOP() {
		return jpTOP;
	}

	public void setPnTOP(JPanel pnTOP) {
		this.jpTOP = pnTOP;
	}
	
	// 액션
	private SignIn singIn;

	public MenuBar() {
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
		btnBook = new JButton("예매");			// 예매 버튼 생성
		btnBook.setSize(150, 40);				// 버튼 사이즈 지정
//		btnBook.setLocation(10, 0);				// 버튼 위치 지정
		btnBook.setLocation(200, 0);				// 버튼 위치 지정
		btnBook.addActionListener(this);
		btnBook.setFont(fontNanumGothic18);		
//		btnBook.setBackground(Color.LIGHT_GRAY);	// 버튼 배경색 설정
		btnBook.setBorderPainted(false);		// 버튼 테두리 제거
		btnBook.setContentAreaFilled(false);	// 버튼 내용영역 채우기 제거
//		btnBook.setFocusPainted(false);			// 버튼 포커스 시 테두리 제거
				
		btnAirport = new JButton("공항");
		btnAirport.setSize(150, 40);
//		btnAirport.setLocation(180, 0);
		btnAirport.setLocation(350, 0);
		btnAirport.addActionListener(this);
		btnAirport.setFont(fontNanumGothic18);
		btnAirport.setBorderPainted(false);
		btnAirport.setContentAreaFilled(false);
		
		btnInFight = new JButton("기내");
		btnInFight.setSize(150, 40);
//		btnInFight.setLocation(350, 0);
		btnInFight.setLocation(500, 0);
		btnInFight.addActionListener(this);
		btnInFight.setFont(fontNanumGothic18);
		btnInFight.setBorderPainted(false);
		btnInFight.setContentAreaFilled(false);
			
		btnMyPage = new JButton("마이페이지");
		btnMyPage.setSize(150, 40);
//		btnMyPage.setLocation(520, 0);
		btnMyPage.setLocation(650, 0);
		btnMyPage.addActionListener(this);
		btnMyPage.setFont(fontNanumGothic18);
		btnMyPage.setBorderPainted(false);
		btnMyPage.setContentAreaFilled(false);
				
		// 로그인 버튼
		btnLogin = new JButton("<HTML><U>LOGIN</U></HTML>");
		btnLogin.setSize(80, 50);
//		btnLogin.setLocation(970, 35);
		btnLogin.setLocation(970, 5);
		btnLogin.addActionListener(this);
		btnLogin.setFont(fontArial);
		btnLogin.setBorderPainted(false);
		btnLogin.setContentAreaFilled(false);
				
		jpTOP.add(btnLogo);
		jpTOP.add(jpMenu);
		
		jpMenu.add(btnBook);
		jpMenu.add(btnAirport);
		jpMenu.add(btnInFight);
//		jpMenu.add(btnMyPage);
			
		jpTOP.add(btnLogin);
				
		add(jpTOP);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnLogo) {
			
		} else if(obj == btnLogin){
			singIn = new SignIn();
		} else if(obj == btnMyPage) {
			// 마이페이지
		} else
		{
			// 하위 메뉴
		}
	}
}
