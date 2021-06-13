package be.menu;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.menu.MenuBar;

// 예원 - 메뉴 화면
public class MenuPanel2 extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 800, height = 300;
	
	// 폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothicPlain18 = new Font("NanumGothic", Font.PLAIN, 18);	// 나눔고딕 기본 18
	Font fontNanumGothicPlain20 = new Font("NanumGothic", Font.PLAIN, 20);	// 나눔고딕 기본 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 22
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 25);					// 영어
	
	// 닫기 버튼 관련
	private JPanel jpClose;
	private JButton btnClose;
	
	// 메뉴 관련
	private JPanel jpMenu, jpBook, jpAirport, jpInFight;
		
	// 예매 Book
	private JButton btnKoreaDomestic,		// 국내선
					btnInternational,		// 국제선
					btnRefund;				// 환불
		
	// 공항 Airport
	private JLabel lblBaggage;				// 수화물
	private JButton	btnBaggageInformation,	// 수화물 안내
					btnRestrictedItems,		// 운송제한물품
					btnDamagedOrLost;		// 파손 및 유실물
		
	// 기내 InFight
	private JLabel	lblInFlightService;		// 기내 서비스
	private JButton	btnInFlightMeals,		// 기내식
					btnDutyFree,			// 면세품
					btnGuide;				// 가이드
	private JLabel	lblFleet;				// 항공기 안내
	private JButton btnFleetInformation;	// 항공기 정보
		
	
	public JPanel getJpMenu() {
		
		return jpMenu;
	}


	public void setJpMenu(int x, int y) {
		jpMenu.setLocation(x, y);
	}

	
	// 메뉴폼
	public MenuPanel2() {
		setTitle(title);
		setSize(width, height);
//		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setUndecorated(true); //타이틀바 없애기 
		
		// 레이아웃 설정
		setLayout(null);
		
		// 배경색
//		Container c = getContentPane();
//		c.setBackground(Color.WHITE);
		
//		// 닫기 버튼
//		setClose();
		
		// 예매 메뉴
		setMenuPop(5, 5);
		
		add(jpMenu);
		
		setVisible(true);
	}



	private void setMenuPop(int x, int y) {
		// 닫기 버튼
//		setClose();
		
		jpMenu = new JPanel();
		jpMenu.setLayout(null);
//		jpMenu.setSize(775, 200);
//		jpMenu.setLocation(5, 55);
		jpMenu.setSize(775, 255);
		jpMenu.setLocation(x, y);
//		jpMenu.setBackground(Color.YELLOW);
		
		// 예매 메뉴 
		setBookMenu();
		
		// 공항 메뉴
		setAirportMenu();
		
		// 기내 메뉴
		setInFightMenu();
		
	}

	private void setBookMenu() {
		
		jpBook = new JPanel();
		jpBook.setLayout(null);
		jpBook.setSize(150, 240);
		jpBook.setLocation(5, 5);
//		jpBook.setBackground(Color.LIGHT_GRAY);
		
		btnKoreaDomestic = new JButton("국내선");
		btnKoreaDomestic.setFont(fontNanumGothicPlain18);
		btnKoreaDomestic.setHorizontalAlignment(JButton.LEFT);
		btnKoreaDomestic.setSize(150, 35);
		btnKoreaDomestic.setLocation(0, 5);
		btnKoreaDomestic.setBorderPainted(false);
		btnKoreaDomestic.setContentAreaFilled(false);
		
		btnInternational = new JButton("국제선");
		btnInternational.setFont(fontNanumGothicPlain18);
		btnInternational.setHorizontalAlignment(JButton.LEFT);
		btnInternational.setSize(150, 35);
		btnInternational.setLocation(0, 45);
		btnInternational.setBorderPainted(false);
		btnInternational.setContentAreaFilled(false);
		
		btnRefund = new JButton("환불");
		btnRefund.setFont(fontNanumGothicPlain18);
		btnRefund.setHorizontalAlignment(JButton.LEFT);
		btnRefund.setSize(150, 35);
		btnRefund.setLocation(0, 85);
		btnRefund.setBorderPainted(false);
		btnRefund.setContentAreaFilled(false);
		
		jpBook.add(btnKoreaDomestic);
		jpBook.add(btnInternational);
		jpBook.add(btnRefund);
		jpMenu.add(jpBook);
	}


	private void setAirportMenu() {
	
		jpAirport = new JPanel();
		jpAirport.setLayout(null);
		jpAirport.setSize(150, 240);
		jpAirport.setLocation(165, 5);
//		jpAirport.setBackground(Color.LIGHT_GRAY);
		
		lblBaggage = new JLabel("수하물");
		lblBaggage.setFont(fontNanumGothic18);
		lblBaggage.setHorizontalAlignment(JButton.LEFT);
		lblBaggage.setSize(150, 35);
		lblBaggage.setLocation(15, 5);
		
		btnBaggageInformation = new JButton("수하물 안내");
		btnBaggageInformation.setFont(fontNanumGothicPlain18);
		btnBaggageInformation.setHorizontalAlignment(JButton.LEFT);
		btnBaggageInformation.setSize(150, 35);
		btnBaggageInformation.setLocation(0, 45);
		btnBaggageInformation.setBorderPainted(false);
		btnBaggageInformation.setContentAreaFilled(false);
		
		btnRestrictedItems = new JButton("운송제한물품");
		btnRestrictedItems.setFont(fontNanumGothicPlain18);
		btnRestrictedItems.setHorizontalAlignment(JButton.LEFT);
		btnRestrictedItems.setSize(150, 35);
		btnRestrictedItems.setLocation(0, 85);
		btnRestrictedItems.setBorderPainted(false);
		btnRestrictedItems.setContentAreaFilled(false);
		
		btnDamagedOrLost = new JButton("파손 및 유실물");
		btnDamagedOrLost.setFont(fontNanumGothicPlain18);
		btnDamagedOrLost.setHorizontalAlignment(JButton.LEFT);
		btnDamagedOrLost.setSize(160, 35);
		btnDamagedOrLost.setLocation(0, 125);
		btnDamagedOrLost.setBorderPainted(false);
		btnDamagedOrLost.setContentAreaFilled(false);
		
		jpAirport.add(lblBaggage);
		jpAirport.add(btnBaggageInformation);
		jpAirport.add(btnRestrictedItems);
		jpAirport.add(btnDamagedOrLost);
		
		jpMenu.add(jpAirport);
	}


	private void setInFightMenu() {
		
		jpInFight = new JPanel();
		jpInFight.setLayout(null);
		jpInFight.setSize(320, 240);
//		jpInFight.setLocation(360, 5);
		jpInFight.setLocation(325, 5);
//		jpInFight.setBackground(Color.LIGHT_GRAY);
		
		lblInFlightService = new JLabel("기내 서비스");
		lblInFlightService.setFont(fontNanumGothic18);
		lblInFlightService.setHorizontalAlignment(JButton.LEFT);
		lblInFlightService.setSize(150, 35);
		lblInFlightService.setLocation(15, 5);
		
		btnInFlightMeals = new JButton("기내식");
		btnInFlightMeals.setFont(fontNanumGothicPlain18);
		btnInFlightMeals.setHorizontalAlignment(JButton.LEFT);
		btnInFlightMeals.setSize(150, 35);
		btnInFlightMeals.setLocation(0, 45);
		btnInFlightMeals.setBorderPainted(false);
		btnInFlightMeals.setContentAreaFilled(false);
		
		btnDutyFree = new JButton("면세품");
		btnDutyFree.setFont(fontNanumGothicPlain18);
		btnDutyFree.setHorizontalAlignment(JButton.LEFT);
		btnDutyFree.setSize(150, 35);
		btnDutyFree.setLocation(0, 85);
		btnDutyFree.setBorderPainted(false);
		btnDutyFree.setContentAreaFilled(false);
		
		btnGuide = new JButton("가이드");
		btnGuide.setFont(fontNanumGothicPlain18);
		btnGuide.setHorizontalAlignment(JButton.LEFT);
		btnGuide.setSize(160, 35);
		btnGuide.setLocation(0, 125);
		btnGuide.setBorderPainted(false);
		btnGuide.setContentAreaFilled(false);
		
		lblFleet = new JLabel("항공기 안내");
		lblFleet.setFont(fontNanumGothic18);
		lblFleet.setHorizontalAlignment(JButton.LEFT);
		lblFleet.setSize(150, 35);
		lblFleet.setLocation(150, 5);
		
		btnFleetInformation = new JButton("항공기 정보");
		btnFleetInformation.setFont(fontNanumGothicPlain18);
		btnFleetInformation.setHorizontalAlignment(JButton.LEFT);
		btnFleetInformation.setSize(150, 35);
		btnFleetInformation.setLocation(135, 45);
		btnFleetInformation.setBorderPainted(false);
		btnFleetInformation.setContentAreaFilled(false);
		
		jpInFight.add(lblInFlightService);
		jpInFight.add(btnInFlightMeals);
		jpInFight.add(btnDutyFree);
		jpInFight.add(btnGuide);
		jpInFight.add(lblFleet);
		jpInFight.add(btnFleetInformation);
		jpMenu.add(jpInFight);
	}

	// 닫기 버튼
	private void setClose() {
		jpClose = new JPanel();
		jpClose.setSize(790, 50);
		jpClose.setLocation(0, 0);
		jpClose.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		jpClose.setBackground(Color.black);
		
		btnClose = new JButton("X");
		btnClose.addActionListener(this);
		btnClose.setFont(fontArial);
		btnClose.setBorderPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setFocusPainted(false);
		
		jpClose.add(btnClose);
		add(jpClose);
	}

	public static void main(String[] args) {
		new MenuPanel2();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnClose)
		{
			this.dispose(); //해당 창만 끄게 하기
		}
	}
}
