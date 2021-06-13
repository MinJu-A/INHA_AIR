package customer.findTrips;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import be.main.MainForm;
import be.menu.MenuBar;
import customer.start.MainMenuForm;



public class FindTripsForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 예원 - 컴포넌트
		private JButton btnMainMenu;
		private String id;
		// 예원 - Forms
		private MainMenuForm mainMenuForm;
		private MemberInquiryDetailForm2 detailForm;
		
		// 예원 - 색상
		Color colorLogo = new Color(24, 62, 111);
		// 예원 - 폰트
		Font fontArial30 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
		
		String driver = "com.mysql.cj.jdbc.Driver"; //드라이버
		String dbURL = "jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false"; //접속할 DB 서버
		String dbID = "inhaair"; //DB에 접속할 사용자 이름을 상수로 정의
		String dbPassword = "1234"; //사용자의 비밀번호를 상수로 정의
			
		Connection conn = null; 
		Statement state = null; 
	
		public String getReserveNum() {
			return reserveNum;
		}
		
		
	//---------------------가상의 고객이 선택한 정보
	//-------------------------------------
//		String reserveNum = "test001010";
//		String scheduleNo = "AAAA-12";
//		String nameKOR = "민보현";
//		String from = "CJU";
//		String fromDate ="20210521";
//		String to = "GMP";
//		String ID = "test1";
	//-------------------------------------
		private String reserveNum;
		private String nameKOR;		
		private String scheduleNo;		
		private String from;		
		private String to;		
		private String fromDate;	
//		String ID;
	//-------------------------------------	
	//-------------------------------------	
//		String reserveNum;
//		String nameKOR;		
//		String secheduleNo;		
//		String from;		
//		String to;		
//		String fromDate =;	
//		String ID = getID();
//-------------------------------------	
//-------------------------------------	
		
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
		Font fontNanumGothic15Plain= new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 18
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
		private JLabel lblName;
		private JLabel lblReserveNum;
		private JLabel lblDate;
		private JLabel lblDepP;
		private Component lblArrP;

	public FindTripsForm(String id) {
//		this.mainMenuForm=mainMenuForm;
		
//		this.id = mainMenuForm.getId();
		this.id = id;
		
		
//		this.id ="test2";
//		
//		this.nameKOR = nameKOR;		
//		this.scheduleNo = scheduleNo;		
//		this.from = from;		
//		this.to = to;		
//		this.fromDate = fromDate;	
//		this.reserveNum = reserveNum;	
//		
//		this.nameKOR = " ";		
//		this.scheduleNo =" ";		
//		this.from =" ";		
//		this.to = " ";			
//		this.fromDate = " ";	
//		this.reserveNum = " ";		
		
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
		
		// 예원 - 메인메뉴로 돌아가기 버튼
				btnMainMenu = new JButton("INHA AIR");
				btnMainMenu.setSize(200, 50);
				btnMainMenu.setLocation(10, 10);
				btnMainMenu.setFont(fontArial30);
				btnMainMenu.setForeground(colorLogo);
				btnMainMenu.setBorderPainted(false);
				btnMainMenu.setBackground(Color.WHITE);
				
				// 예원 - 리스너
				btnMainMenu.addActionListener(this);
				
				// 예원 - 컴포넌트 붙이기
				add(btnMainMenu);
						
				Data();
		
		jpInquiryTop = new JPanel(); //고객이 선택한 정보를 표시하는 바
		jpInquiryTop.setLayout(null);
		jpInquiryTop.setSize(1000,42);
		jpInquiryTop.setLocation(70,100);
		jpInquiryTop.setBackground(crGrey);
		
		lblRNum = new JLabel("예약 번호");
		lblRNum.setBounds(80, 10, 80, 20);
		lblRNum.setFont(fontNanumGothic15);
		
		lblRName = new JLabel("예약자명");
		lblRName.setBounds(240, 10, 80, 20);
		lblRName.setFont(fontNanumGothic15);
		
		lblRDate = new JLabel("날짜");
		lblRDate.setBounds(420, 10, 80, 20);
		lblRDate.setFont(fontNanumGothic15);
		
		lblRDep = new JLabel("출발");
		lblRDep.setBounds(560, 10, 80, 20);
		lblRDep.setFont(fontNanumGothic15);
		
		lblRArr = new JLabel("도착");
		lblRArr.setBounds(700, 10, 80, 20);
		lblRArr.setFont(fontNanumGothic15);
		
//		lblRSeat = new JLabel("좌석");
//		lblRSeat.setBounds(770, 10, 80, 20);
//		lblRSeat.setFont(fontNanumGothic15);
			
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
//		jpInquiryTop.add(lblRSeat);
		
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
//		add(jpInquiry2);
//		add(jpInquiry3);
//		add(jpInquiry4);

		//------------------------------------------
		//------------------------------------------
		lblReserveNum = new JLabel(reserveNum);
		lblReserveNum.setBounds(55, 40, 150, 20);
		lblReserveNum.setFont(fontNanumGothic15Plain);
		
		lblName = new JLabel(nameKOR);
		lblName.setBounds(245, 40, 80, 20);
		lblName.setFont(fontNanumGothic15Plain);
		
//		lblDate = new JLabel(fromDate.substring(0,4)+"년 " + fromDate.substring(4,6) + "월 " + fromDate.substring(6,8) + "일");
		lblDate = new JLabel(fromDate);
		lblDate.setBounds(370, 40, 200, 20);
		lblDate.setFont(fontNanumGothic15Plain);
		
		lblDepP = new JLabel(from);
		lblDepP.setBounds(560, 40, 200, 20);
		lblDepP.setFont(fontNanumGothic18Plain);
		
		lblArrP = new JLabel(to);
		lblArrP.setBounds(695, 40, 200, 20);
		lblArrP.setFont(fontNanumGothic18Plain);
		
		//------------------------------------------
		//------------------------------------------
		jpInquiry1.add(lblName);
		jpInquiry1.add(lblReserveNum);
		jpInquiry1.add(lblDate);
		jpInquiry1.add(lblDepP);
		jpInquiry1.add(lblArrP);
		
		System.out.println(id);
		
		setVisible(true);
	}
	private void Data() {
		
		
		//-------------------------------------------
		//-------------------------------------------
		try{
			Class.forName(driver);
			
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			state = conn.createStatement();	

			String sql;
			sql = "SELECT * FROM reservation WHERE `ID` = '"+ id +"' ";
			
			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
				reserveNum = rs.getString("reserveNum");
				
				this.reserveNum = reserveNum;
				System.out.println(reserveNum);
			}
//			rs.close();
//			state.close();
//			conn.close();
		}
		catch (Exception e) {
		}finally {try {if(state!=null)state.close();}
		catch (SQLException ex1) {}
		try {if(conn!=null)conn.close();}
		catch (SQLException ex2) {}
			}
		
	//-------------------------------------------
	//-------------------------------------------
	try{
		Class.forName(driver);
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		state = conn.createStatement();	

		String sql;
		sql = "SELECT * FROM reservationDetail WHERE `reserveNum` = '"+ reserveNum +"' ";
		
		ResultSet rs = state.executeQuery(sql);
		while (rs.next()) {
			
			scheduleNo = rs.getString("scheduleNo");
			nameKOR = rs.getString("nameKOR");
			
			this.scheduleNo = scheduleNo;
			this.nameKOR = nameKOR;
			System.out.println(nameKOR);
			
		}
//		rs.close();
//		state.close();
//		conn.close();
	}
	catch (Exception e) {
	}finally {try {if(state!=null)state.close();}
	catch (SQLException ex1) {}
	try {if(conn!=null)conn.close();}
	catch (SQLException ex2) {}
	}

//----------------------------------------
//----------------------------------------
	try{
		Class.forName(driver);
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		state = conn.createStatement();	

		String sql;
		sql = "SELECT * FROM airSchedule WHERE `scheduleNo` = '"+ scheduleNo +"' ";
		
		ResultSet rs = state.executeQuery(sql);
		while (rs.next()) {
			from = rs.getString("from");
			fromDate = rs.getString("fromDate");
			to = rs.getString("to");
			this.from = from;
			this.to = to;
			this.fromDate=fromDate;
					System.out.println(from);

			
		}
		rs.close();
		state.close();
		conn.close();
	}
	catch (Exception e) {
	}finally {try {if(state!=null)state.close();}
	catch (SQLException ex1) {}
	try {if(conn!=null)conn.close();}
	catch (SQLException ex2) {}
	}
}
	
	// 상단 
	public static void main(String[] args) {
//		new FindTripsForm(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnMainMenu) {
			mainMenuForm = new MainMenuForm();
			mainMenuForm.setId(id);
			this.setVisible(false);
			
		} else if(obj == btnDetail1) {
			detailForm = new MemberInquiryDetailForm2();
			this.setVisible(false);
		}
	}
}

