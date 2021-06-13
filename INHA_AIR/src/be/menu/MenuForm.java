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
public class MenuForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 700, height = 550;
	
	// 폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothicPlain18 = new Font("NanumGothic", Font.PLAIN, 18);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 22
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 25);					// 영어
	
	// 닫기 버튼 관련
	private JPanel jpClose;
	private JButton btnClose;
	
	// 메뉴 관련
	private JPanel jpBook, jpAirport, jpInFight;
	
	// 예매 Book
	private JButton btnBook,				// 예매
					btnKoreaDomestic,		// 국내선
					btnInternational,		// 국제선
					btnRefund;				// 환불
	
	// 공항 Airport
	private JButton btnAirport;				// 공항
	private JLabel lblBaggage;				// 수화물
	private JButton	btnBaggageInformation,	// 수화물 안내
					btnRestrictedItems,		// 운송제한물품
					btnDamagedOrLost;		// 파손 및 유실물
	
	// 기내 InFight
	private JButton btnInFight;				// 기내
	private JLabel	lblInFlightService;		// 기내 서비스
	private JButton	btnInFlightMeals,		// 기내식
					btnDutyFree,			// 면세품
					btnGuide;				// 가이드
	private JLabel	lblFleet;				// 항공기 안내
	private JButton btnFleetInformation;	// 항공기 정보
	
	
	// 메뉴폼
	public MenuForm() {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true); //타이틀바 없애기 
		
		// 레이아웃 설정
		setLayout(null);
		
		// 배경색
//		Container c = getContentPane();
//		c.setBackground(Color.WHITE);
		
		// 닫기 버튼
		setClose();
		
		// 예매 메뉴
		setBook();
		
		// 공항 메뉴
		setAirport();
		
		// 기내 메뉴
		setInFight();
		
		setVisible(true);
	}

	
	private void setBook() {
		// 예매 메뉴 패널
		jpBook = new JPanel();
		jpBook.setLayout(null);
		jpBook.setSize(170, 480);
		jpBook.setLocation(50, 60);
//		jpBook.setBackground(Color.yellow);
		
		btnBook = new JButton("예매");
		btnBook.setSize(170, 50);
		btnBook.setLocation(0, 5);
		btnBook.setHorizontalAlignment(JButton.LEFT);
		btnBook.setFont(fontNanumGothic25);
		btnBook.setBorderPainted(false);
		btnBook.setContentAreaFilled(false);
		
		btnKoreaDomestic = new JButton("국제선");
		btnKoreaDomestic.setSize(170, 40);
		btnKoreaDomestic.setLocation(0, 60);
		btnKoreaDomestic.setHorizontalAlignment(JButton.LEFT);
		btnKoreaDomestic.setFont(fontNanumGothicPlain18);
		btnKoreaDomestic.setBorderPainted(false);
		btnKoreaDomestic.setContentAreaFilled(false);
		
		
		btnInternational = new JButton("국내선");
		btnInternational.setSize(170, 40);
		btnInternational.setLocation(0, 105);
		btnInternational.setHorizontalAlignment(JButton.LEFT);
		btnInternational.setFont(fontNanumGothicPlain18);
		btnInternational.setBorderPainted(false);
		btnInternational.setContentAreaFilled(false);
		
		btnRefund = new JButton("환불");
		btnRefund.setSize(170, 40);
		btnRefund.setLocation(0, 150);
		btnRefund.setHorizontalAlignment(JButton.LEFT);
		btnRefund.setFont(fontNanumGothicPlain18);
		btnRefund.setBorderPainted(false);
		btnRefund.setContentAreaFilled(false);
		
		
		jpBook.add(btnBook);
		jpBook.add(btnKoreaDomestic);
		jpBook.add(btnInternational);
		jpBook.add(btnRefund);
		
		add(jpBook);
	}

	
	private void setAirport() {
		// 공항 메뉴 패널
		jpAirport = new JPanel();
		jpAirport.setSize(210, 480);
		jpAirport.setLocation(240, 60);
		jpAirport.setBackground(Color.blue);	
		
		
		add(jpAirport);
	}

	
	private void setInFight() {
		// 기내 메뉴 패널
		jpInFight = new JPanel();
		jpInFight.setSize(210, 480);
		jpInFight.setLocation(470, 60);
		jpInFight.setBackground(Color.green);
		
		
		
		add(jpInFight);
	}


	private void setClose() {
		jpClose = new JPanel();
		jpClose.setSize(700, 50);
		jpClose.setLocation(0, 5);
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
		new MenuForm();
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
