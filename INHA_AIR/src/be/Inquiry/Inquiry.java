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


public class Inquiry extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	
	// 상단 메뉴바
	private MenuBar menubar;
	//--가상의 고객이 선택한 정보
		
		private JPanel jpInquiry1; // 시간 선택시 비행기1
		private JPanel jpInquiry2; // 비행기 2
		private JPanel jpInquiry3; // 비행기 3
		
		private Color crInfo = new Color(240,240,240);//고객이 선택한 정보를 나타내는 바의 색 
		private Color crGrey = new Color(230,240,250);
		private Color crPaleblue = new Color(230,240,250);
		
		Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
		Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);	// 나눔고딕 18
		Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 18
		Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 18
		Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
		Font fontNanumGothic18Plain = new Font("NanumGothic", Font.PLAIN, 18);	// 나눔고딕 18
		Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
		Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
		Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
		Font fontNanumGothic30 = new Font("NanumGothic", Font.BOLD, 30);	// 나눔고딕 30
		private JPanel jpInquiryTop;
		private JPanel jpInquiry4;
		private JLabel lblRNum;
		private JLabel lblRName;
		private Component lblRDate;
		private JLabel lblRDep;
		private Component lblRArr;
		private Component lblRSeat;
		private JButton btnDetail1;
		private Component btnDetail2;
		private JButton btnDetail3;
		private Component btnDetail4;

	public Inquiry() {
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
		
		jpInquiryTop = new JPanel(); //고객이 선택한 정보를 표시하는 바
		jpInquiryTop.setLayout(null);
		jpInquiryTop.setSize(1000,42);
		jpInquiryTop.setLocation(70,100);
		jpInquiryTop.setBackground(crGrey);
		
		lblRNum = new JLabel("예약 번호");
		lblRNum.setBounds(80, 10, 80, 20);
		lblRNum.setFont(fontNanumGothic15);
		
		lblRName = new JLabel("예약자명");
		lblRName.setBounds(230, 10, 80, 20);
		lblRName.setFont(fontNanumGothic15);
		
		lblRDate = new JLabel("가는 날");
		lblRDate.setBounds(380, 10, 80, 20);
		lblRDate.setFont(fontNanumGothic15);
		
		lblRDep = new JLabel("출발");
		lblRDep.setBounds(530, 10, 80, 20);
		lblRDep.setFont(fontNanumGothic15);
		
		lblRArr = new JLabel("도착");
		lblRArr.setBounds(650, 10, 80, 20);
		lblRArr.setFont(fontNanumGothic15);
		
		lblRSeat = new JLabel("좌석");
		lblRSeat.setBounds(770, 10, 80, 20);
		lblRSeat.setFont(fontNanumGothic15);
			
		btnDetail1 = new JButton("예약 상세");
		btnDetail1.setBounds(880, 40, 90, 35);
		btnDetail1.setFont(fontNanumGothic12);
		btnDetail1.setBackground(crPaleblue);
		btnDetail1.addActionListener(this);
		
		btnDetail2 = new JButton("예약 상세");
		btnDetail2.setBounds(880, 40, 90, 35);
		btnDetail2.setFont(fontNanumGothic12);
		btnDetail2.setBackground(crPaleblue);
		
		btnDetail3 = new JButton("예약 상세");
		btnDetail3.setBounds(880, 40, 90, 35);
		btnDetail3.setFont(fontNanumGothic12);
		btnDetail3.setBackground(crPaleblue);
		
		btnDetail4 = new JButton("예약 상세");
		btnDetail4.setBounds(880, 40, 90, 35);
		btnDetail4.setFont(fontNanumGothic12);
		btnDetail4.setBackground(crPaleblue);
		
		jpInquiryTop.add(lblRNum);
		jpInquiryTop.add(lblRName);
		jpInquiryTop.add(lblRDate);
		jpInquiryTop.add(lblRDep);
		jpInquiryTop.add(lblRArr);
		jpInquiryTop.add(lblRSeat);
		
		jpInquiry1 = new JPanel(); //3개의 시간표 중 선택 1
		jpInquiry1.setLayout(null);
		jpInquiry1.setSize(1000,110);
		jpInquiry1.setLocation(70,152);
		jpInquiry1.setBackground(crInfo);
		
		jpInquiry2 = new JPanel();//선택 2
		jpInquiry2.setLayout(null);
		jpInquiry2.setSize(1000,110);
		jpInquiry2.setLocation(70,274);
		jpInquiry2.setBackground(crInfo);
		
		jpInquiry3 = new JPanel();// 선택 3
		jpInquiry3.setLayout(null);
		jpInquiry3.setSize(1000,110);
		jpInquiry3.setLocation(70,396);
		jpInquiry3.setBackground(crInfo);
		
		jpInquiry4 = new JPanel();// 선택 3
		jpInquiry4.setLayout(null);
		jpInquiry4.setSize(1000,110);
		jpInquiry4.setLocation(70,518);
		jpInquiry4.setBackground(crInfo);
		
		jpInquiry1.add(btnDetail1);
		jpInquiry2.add(btnDetail2);
		jpInquiry3.add(btnDetail3);
		jpInquiry4.add(btnDetail4);
		
		add(jpInquiryTop);
		add(jpInquiry1);
		add(jpInquiry2);
		add(jpInquiry3);
		add(jpInquiry4);

		setVisible(true);
	}

	// 상단 메뉴바
	private void setUpMenu() {
		menubar = new MenuBar();
		add(menubar.getPnTOP());
	}

	public static void main(String[] args) {
		new Inquiry();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnDetail1) {
			MemberInquiryDetail Mid = new MemberInquiryDetail();
			
		}
	}
}

