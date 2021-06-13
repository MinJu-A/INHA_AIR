package be.Inquiry;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import be.main.MainForm;
import be.menu.MenuBar;


public class MemberInquiryDetail extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	
	// 상단 메뉴바
	private be.menu.MenuBar menubar;
	//--가상의 고객이 선택한 정보
		
		private JPanel jpInquiry1; // 시간 선택시 비행기1
		private JPanel jpInquiry2; // 비행기 2
		
		private Color crInfo = new Color(240,240,240);//고객이 선택한 정보를 나타내는 바의 색 
		private Color crGrey = new Color(230,240,250);
		private Color crPaleblue = new Color(230,240,250);
		private Color crNavy = new Color(30,30,170); // 수하물 유의사항 색

		Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
		Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);	// 나눔고딕 18
		Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 18
		Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 18
		Font fontNanumGothic15Plain= new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 18
		Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
		Font fontNanumGothic18Plain = new Font("NanumGothic", Font.PLAIN, 18);	// 나눔고딕 18
		Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
		Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
		Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
		Font fontNanumGothic30 = new Font("NanumGothic", Font.BOLD, 30);	// 나눔고딕 30
		private JPanel jpInquiryDetailTop;
		private JPanel jpInquiryDetailTop2;
		private JLabel lblPassenger;
		private JLabel lblPName;
		private Component lblInquiryTitle;
		private JLabel lblRNum;
		private JLabel lblPNum;
		private Component lblFlightInfo;
		private JLabel lblFlightName;
		private JLabel lblDepArr;
		private Component lblDate;
		private Component lblClass;
		private Component lblSeat;
		private JButton btnCheckIn;

	public MemberInquiryDetail() {
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
		
		lblInquiryTitle = new JLabel("예약 상세"); //큰 제목
		lblInquiryTitle.setBounds(70,85 , 200, 60);
		lblInquiryTitle.setFont(fontNanumGothic22);
//		lblInquiryTitle.setForeground(crNavy);;
		
		jpInquiryDetailTop = new JPanel(); //고객이 선택한 정보를 표시하는 바
		jpInquiryDetailTop.setLayout(null);
		jpInquiryDetailTop.setSize(1000,42);
		jpInquiryDetailTop.setLocation(70,175);
		jpInquiryDetailTop.setBackground(crGrey);
		
		jpInquiryDetailTop2 = new JPanel(); //고객이 선택한 정보를 표시하는 바
		jpInquiryDetailTop2.setLayout(null);
		jpInquiryDetailTop2.setSize(1000,42);
		jpInquiryDetailTop2.setLocation(70,402);
		jpInquiryDetailTop2.setBackground(crGrey);
	
		lblPassenger = new JLabel("승객 정보");
		lblPassenger.setBounds(70, 149, 200, 20);
		lblPassenger.setFont(fontNanumGothic15Plain);
		
		lblFlightInfo = new JLabel("항공권 정보");
		lblFlightInfo.setBounds(70, 375, 180, 20);
		lblFlightInfo.setFont(fontNanumGothic15Plain);
		
		lblPName = new JLabel("승객명");
		lblPName.setBounds(50, 10, 80, 20);
		lblPName.setFont(fontNanumGothic15);
		
		lblRNum = new JLabel("예약번호");
		lblRNum.setBounds(250, 10, 80, 20);
		lblRNum.setFont(fontNanumGothic15);
		
		lblPNum = new JLabel("전화번호");
		lblPNum.setBounds(450, 10, 80, 20);
		lblPNum.setFont(fontNanumGothic15);
		
		lblFlightName = new JLabel("항공편명");
		lblFlightName.setBounds(50, 10, 80, 20);
		lblFlightName.setFont(fontNanumGothic15);
		
		lblDepArr = new JLabel("출발-도착");
		lblDepArr.setBounds(250, 10, 80, 20);
		lblDepArr.setFont(fontNanumGothic15);
		
		lblDate = new JLabel("날짜");
		lblDate.setBounds(450, 10, 80, 20);
		lblDate.setFont(fontNanumGothic15);
		
		lblClass = new JLabel("클래스");
		lblClass.setBounds(610, 10, 80, 20);
		lblClass.setFont(fontNanumGothic15);
		
		lblSeat = new JLabel("좌석");
		lblSeat.setBounds(770, 10, 80, 20);
		lblSeat.setFont(fontNanumGothic15);
		
		btnCheckIn = new JButton("체크인");
		btnCheckIn.setBounds(880, 70, 90, 35);
		btnCheckIn.setFont(fontNanumGothic12);
		btnCheckIn.setBackground(crPaleblue);
		
		jpInquiryDetailTop.add(lblPName);
		jpInquiryDetailTop.add(lblRNum);
		jpInquiryDetailTop.add(lblPNum);
		
		jpInquiryDetailTop2.add(lblFlightName);
		jpInquiryDetailTop2.add(lblDepArr);
		jpInquiryDetailTop2.add(lblDate);
		jpInquiryDetailTop2.add(lblClass);
		jpInquiryDetailTop2.add(lblSeat);
		
		
		
		add(lblPassenger);
		add(lblFlightInfo);
		
		jpInquiry1 = new JPanel(); //3개의 시간표 중 선택 1
		jpInquiry1.setLayout(null);
		jpInquiry1.setSize(1000,130);
		jpInquiry1.setLocation(70,227);
		jpInquiry1.setBackground(crInfo);
		
		jpInquiry2 = new JPanel();//선택 2
		jpInquiry2.setLayout(null);
		jpInquiry2.setSize(1000,210);
		jpInquiry2.setLocation(70,454);
		jpInquiry2.setBackground(crInfo);
		jpInquiry2.add(btnCheckIn);
		
		add(lblInquiryTitle);
		add(jpInquiryDetailTop);
		add(jpInquiryDetailTop2);
		add(jpInquiry1);
		add(jpInquiry2);


		setVisible(true);
	}

	// 상단 메뉴바
	private void setUpMenu() {
		menubar = new MenuBar();
		add(menubar.getPnTOP());
	}

	public static void main(String[] args) {
		new MemberInquiryDetail();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

