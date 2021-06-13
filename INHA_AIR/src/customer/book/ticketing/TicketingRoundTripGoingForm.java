 package customer.book.ticketing;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

import be.main.MainForm;
import be.menu.MenuBar;
import customer.book.BookForm;
import customer.start.MainMenuForm;
//import sun.awt.www.content.image.jpeg;
//import test.TicketingRoundTripComingForm2;



public class TicketingRoundTripGoingForm extends JFrame implements ActionListener {
	private static TicketingRoundTripGoingForm a;
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 예원 - 컴포넌트
	private JButton btnMainMenu;
	// 예원 - Forms
	private MainMenuForm mainMenuForm;
	private TicketingRoundTripComingForm tkRTComForm; //--------->>>>>
	
	
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
	
	private BookForm sel;
	
	
		private JPanel jpSelectedInfo;
	//--가상의 고객이 선택한 정보
//		String SelectDepCode = "CJU";
//		String SelectArrCode = "GMP";
//		String goDay = "20210531";
//		String comeDay = "20210609";
//		private int numAdult = 3;//성인
//		private int numChild= 2;//소아인원
//		private int numInfant = 1;//소아인원
		
		String COMclass;
		String COMscheduleNo;
//		private String ID = "test1" ;
		private int totalPay; //---삭제

		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 Calendar c = Calendar.getInstance();
		 String strToday = sdf.format(c.getTime());
			
		 SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
         Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
         String sdf2 = fourteen_format .format(date_now);
         
//         System.out.println(fourteen_format .format(date_now)); // 기본 포멧으로 출력한다
		 
//		String reserveNum = GoDay.substring(0, 3) + ComeDay.substring(2,5) + ID.substring(0,3) + strToday.substring(3,6) ;
private int TotalPay;

		//reserveNum, GOscheduleNo, COMscheduleNo, ID, adult,child ,infant,pay,GOclass,COMclass
		//reserveNum, GOscheduleNo,COMscheduleNo,ID, AdultP,ChildP ,InfantP,totalPay,selectedSeatGo,COMclass
		
		public void setDepP(String depP) {
			SelectDepCode = depP;
		}

		public void setArrP(String arrP) {
			SelectArrCode = arrP;
		}

		public void setGoDay(String goDay) {
			this.goDay = goDay;
		}

		public void setComeDay(String comeDay) {
			this.comeDay = comeDay;
		}

		public void setAdultP(int adultP) {
			numAdult = adultP;
		}

		public void setChildP(int childP) {
			numChild = childP;
		}

		public void setInfantP(int infantP) {
			numInfant = infantP;
		}
		public void setTotalPay(int totalPay) {
			TotalPay = totalPay;
		}
		
		
		public String getFlightCode() {
			return flightCode;
		}
		
		public String getGOSheduleNo() {
			return GOscheduleNo;
		}
		
		public String getfromTime() {
			return fromTime;
		}
		public String getToTime() {
			return toTime;
		}
		public String getFromDate() {
			return fromDate;
		}
		public String getToDate() {
			return toDate;
		}
		public String getDepP() {
			return SelectDepCode;
		}
		public String getArrP() {
			return SelectArrCode;
		}
		public int getAdultP() {
			return numAdult;
		}
		public int getChildP() { 
			return numChild;
		}
		public int getInfantP() {
			return numInfant;
		}
		public int getTotalPay() {
			return totalPay;
		}
		public String getSelectedSeat() {
			return selectedSeatGo;
		}
		public String getID() {
			return ID;
		}
		public String getGoDay() {
			return goDay;
		}
		public String getComeDay() {
			return comeDay;
		}
		
		public String getAirportD() {
			return airportD;
		}
		
		public String getAirportA() {
			return airportA;
		}
		public String getReserveNum() {
			return reserveNum;
		}
		
		
		private JLabel lblArrow; //왕복 화살표
		private JLabel lblPassenger;// 탑승 인원 정보 (성인인지 유아인지의 정보 + 인원수)
		
		private JPanel jpFlight1; // 시간 선택시 비행기1
		private JPanel jpFlight2; // 비행기 2
		private JPanel jpFlight3; // 비행기 3
		
		private Color crInfo; //고객이 선택한 정보를 나타내는 바의 색 
		private Color crClass;//좌석 등급 버튼 색
		private JPanel jpTotalPay; // 예상 결제금액 + 버튼 나타내는 패널
		private Component lblTotalPay; //"예상결제금액"문구 나타내는 라벨
		private JButton btnNext; //다음(왕복 오는 편 선택창으로 가는) 버튼
		private Color crNext; //다음 버튼 색
		
		Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
		Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);	// 나눔고딕 18
		Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 18
		Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 18
		Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
		Font fontNanumGothic18Plain = new Font("NanumGothic", Font.PLAIN, 18);	// 나눔고딕 18
		Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
		Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
		Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
		Font fontNanumGothic30 = new Font("NanumGothic", Font.BOLD, 30);	// 나눔고딕 25
		Font fontNanumGothic25Plain = new Font("NanumGothic", Font.PLAIN, 25);	// 나눔고딕 25
	
		
		private JLabel lblDepP;
		private JLabel lblArrP;
		private JLabel lblGoComeDate;

		private String scheduleNo;
		private String flightCode;
		private String from;
		private String fromDate;
		private String to;
		private String toDate;
		private String fromTime=" ";
		private String toTime;
		private JLabel lblFliCo;
		private JLabel lblTime;
		private int economyPay;
		private int businessPay;
		private int firstPay;
		private JLabel eco1;
		private JLabel pres1;
		private JLabel fir1;
		private JPanel jpFlightTOP;
		private Color crTop;
		private JLabel lblDate;
		private JLabel lblAirportD;
		private JLabel lblAirportA;
		private JLabel lblEcon;
		private JLabel lblEconPr;
		private int economy;
		private JButton btnEcon;
		private Color crSelect;
		private JLabel lblBus;
		private JLabel lblBusPr;
		private JButton btnBus;
		private int business;
		private JLabel lblFirs;
		private JLabel lblFirsPr;
		private JButton btnFirs;
		private int first;
		private String airportD;
		private String airportA;
		private JLabel lblTotalPayGoing;
		private String GoingPay;
		private JLabel lblGoingPayP;
		private JLabel lblGoingPayF;
		private String selectedSeatGo;
		private String GOscheduleNo;
		private int GoPay;
		private Color crChange;
		private String goDay;
		private String comeDay;
		private int numAdult;
		private int numChild;
		private int numInfant;
		private int numTotal;
		private String SelectDep;
		private String SelectDepCode;
		private String SelectArr;
		private String ID;
		private String reserveNum;
		private String SelectArrCode;

public TicketingRoundTripGoingForm(BookForm sel) {
		
	this.sel =sel;
	
	this.goDay = sel.getGoDay();		//가는날
	this.comeDay = sel.getComeDay();	//오는날
//	private int roundTrip = 0;		//편도와 왕복 (편도 0, 왕복 1)
	
	//승객 인원 값
	this.numAdult = sel.getNumAdult();		//성인 수
	this.numChild = sel.getNumChild();		//소아 수
	this.numInfant = sel.getNumInfant();		//유아 수
	this.numTotal = sel.getNumTotal();		//총 인원 수
	
	//출발지 값
	this.SelectDep = "";		//선택 출발지
	this.SelectDepCode= sel.getSelectDepCode();	//선택 출발지 코드
	
	//도착지 값
	this.SelectArr= "";		//선택 도착지
	this.SelectArrCode= sel.getSelectArrCode();	//선택 도착지 코드
	this.ID = sel.getId();
	
//	reserveNum = goDay.substring(0, 3) + comeDay.substring(2,5) + ID.substring(0,3) + strToday.substring(3,6) + "00";
	reserveNum = goDay.substring(0, 3) + comeDay.substring(2,5) + ID + strToday.substring(3,6) + sdf2.substring(11, 14);

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
	
	crInfo = new Color(240,240,240);//고객이 선택한 정보를 나타내는 바의 색 
	crTop = new Color(230,230,235);//고객이 선택한 정보를 나타내는 바의 색 
	crClass = new Color(213, 230, 250);//좌석 등급 선택 버튼의 색
	crSelect = new Color(120,180,250);
	crNext = new Color(10,90,150); //다음 버튼 색깔
	crChange = new Color(200,200,200); //다음 버튼 색깔
	
	jpSelectedInfo = new JPanel(); //고객이 선택한 정보를 표시하는 바
	jpSelectedInfo.setLayout(null);
	jpSelectedInfo.setSize(1000,60);
	jpSelectedInfo.setLocation(70,100);
	jpSelectedInfo.setBackground(crInfo);
	
	lblArrow = new JLabel("→"); 
	lblArrow.setFont(fontNanumGothic30);
	lblArrow.setBounds(135, -20, 200, 100);
	
	lblPassenger = new JLabel("    성인  " + numAdult + "명   " +"  |  "+ "  소아  "+ numChild + "명"); //고객이 선택한 탑승자 정보
	lblPassenger.setFont(fontNanumGothic18Plain);
	lblPassenger.setBounds(640, -20, 500, 100);
	
	jpFlightTOP = new JPanel(); //3개의 시간표 중 선택 1
	jpFlightTOP.setLayout(null);
	jpFlightTOP.setSize(1000,100);
	jpFlightTOP.setLocation(70,185);
	jpFlightTOP.setBackground(crTop);
	
	jpFlight1 = new JPanel(); //3개의 시간표 중 선택 1
	jpFlight1.setLayout(null);
	jpFlight1.setSize(1000,70);
	jpFlight1.setLocation(70,310);
	jpFlight1.setBackground(crInfo);
	
	jpFlight2 = new JPanel();//선택 2
	jpFlight2.setLayout(null);
	jpFlight2.setSize(1000,70);
	jpFlight2.setLocation(70,400);
	jpFlight2.setBackground(crInfo);
	
	jpFlight3 = new JPanel();// 선택 3
	jpFlight3.setLayout(null);
	jpFlight3.setSize(1000,70);
	jpFlight3.setLocation(70,490);
	jpFlight3.setBackground(crInfo);
	
	jpSelectedInfo.add(lblArrow); // 화살표
	jpSelectedInfo.add(lblPassenger); //탑승 인원 정보(성인인지 유아인지 + 인원수)
	
	jpTotalPay = new JPanel();//예상 결제 금액 + 버튼 라벨나타내는 패널
	jpTotalPay.setLayout(null);
	jpTotalPay.setSize(1120,130);
	jpTotalPay.setLocation(00,590);
	jpTotalPay.setBackground(Color.white);
	jpTotalPay.setBorder(new LineBorder(Color.black,1));
	
	lblTotalPay = new JLabel("예상 결제 금액"); // "예상 결제 금액" 라벨
	lblTotalPay.setFont(fontNanumGothic20);
	lblTotalPay.setBounds(50,0,200,100);
	
	lblTotalPayGoing = new JLabel("pay");
	lblTotalPayGoing.setFont(fontNanumGothic20);
	lblTotalPayGoing.setBounds(500,0,200,100);
	
	
	
	jpTotalPay.add(lblTotalPayGoing);
	
	btnNext = new JButton("다음"); // 회원 진행 버튼
	btnNext.setFont(fontNanumGothic20);
	btnNext.setBackground(crNext);
	btnNext.setForeground(Color.white);
	btnNext.setBounds(905, 0, 200, 100);
	btnNext.addActionListener(this);
	
	jpTotalPay.add(lblTotalPay);
	jpTotalPay.add(btnNext);
	
	add(jpSelectedInfo);
	add(jpFlightTOP);
	add(jpFlight1);
	add(jpFlight2);
	add(jpFlight3);
	
	
	add(jpTotalPay);
	
	Find();
	
	
	lblEcon = new JLabel("이코노미 클래스");
	lblEcon.setFont(fontNanumGothic25);
	lblEcon.setBounds(60, -15, 200, 100);
	
	lblEconPr = new JLabel(economyPay + " 원          " + " 현재 잔여           "+ economy + "석" );
	lblEconPr.setFont(fontNanumGothic20);
	lblEconPr.setBounds(350, -15,400, 100);
	
	btnEcon = new JButton("선택");
	btnEcon.setFont(fontNanumGothic18Plain);
	btnEcon.setBounds(810, 20,100, 30);
	btnEcon.setBackground(crSelect);
	btnEcon.setForeground(Color.white);
	btnEcon.addActionListener(this);
	
	
	jpFlight1.add(lblEcon);
	jpFlight1.add(lblEconPr);
	jpFlight1.add(btnEcon);
	
	
	
	lblBus = new JLabel("비즈니스 클래스");
	lblBus.setFont(fontNanumGothic25);
	lblBus.setBounds(60, -15, 400, 100);
	
	lblBusPr = new JLabel(businessPay + " 원          " + " 현재 잔여           "+ business + "석" );
	lblBusPr.setFont(fontNanumGothic20);
	lblBusPr.setBounds(350, -15,400, 100);
	
	btnBus = new JButton("선택");
	btnBus.setFont(fontNanumGothic18Plain);
	btnBus.setBounds(810, 20,100, 30);
	btnBus.setBackground(crSelect);
	btnBus.setForeground(Color.white);
	btnBus.addActionListener(this);
	
	
	jpFlight2.add(lblBus);
	jpFlight2.add(lblBusPr);
	jpFlight2.add(btnBus);
	
	lblFirs = new JLabel("퍼스트 클래스");
	lblFirs.setFont(fontNanumGothic25);
	lblFirs.setBounds(60, -15, 400, 100);
	
	lblFirsPr = new JLabel(firstPay + " 원          " + " 현재 잔여           "+ first + "석" );
	lblFirsPr.setFont(fontNanumGothic20);
	lblFirsPr.setBounds(350, -15,400, 100);
	
	btnFirs = new JButton("선택");
	btnFirs.setFont(fontNanumGothic18Plain);
	btnFirs.setBounds(810, 20,100, 30);
	btnFirs.setBackground(crSelect);
	btnFirs.setForeground(Color.white);
	btnFirs.addActionListener(this);

	
	jpFlight3.add(lblFirs);
	jpFlight3.add(lblFirsPr);
	jpFlight3.add(btnFirs);
	
	
	lblDepP = new JLabel(from);
	lblDepP.setFont(fontNanumGothic25);
	lblDepP.setBounds(50, -20, 200, 100);
	
	lblArrP = new JLabel(to);
	lblArrP.setFont(fontNanumGothic25);
	lblArrP.setBounds(180, -20, 200, 100);
	
//	lblDate = new JLabel("<html>"+fromDate + "&nbsp;&nbsp;&nbsp; ~ &nbsp;&nbsp;&nbsp;" + toDate+"</html>");
	
//	lblGoComeDate = new JLabel(GoDay + "~" + ComeDay);
	lblGoComeDate = new JLabel(goDay.substring(0, 4)+"-"+goDay.substring(4, 6)+"-"+goDay.substring(6, 8) + "~" 
+ comeDay.substring(0, 4)+"-"+comeDay.substring(4, 6)+"-"+comeDay.substring(6, 8));
	lblGoComeDate.setFont(fontNanumGothic18Plain);
	lblGoComeDate.setBounds(300, -20, 400, 100);

	lblFliCo = new JLabel(flightCode);
	lblFliCo.setFont(fontNanumGothic30);
	lblFliCo.setBounds(50, 0, 300, 100);
	
	
	lblDate = new JLabel(fromDate);
	lblDate.setFont(fontNanumGothic18Plain);
	lblDate.setBounds(190, 0, 300, 100);
	
	lblTime = new JLabel("출발 "+ fromTime.substring(0, 5)+"   -  "+" 도착 "+toTime.substring(0, 5));
	lblTime.setFont(fontNanumGothic18Plain);
	lblTime.setBounds(535, 0, 300, 100);
	
	lblAirportD = new JLabel(airportD);
	lblAirportD.setFont(fontNanumGothic20);
	lblAirportD.setBounds(320, 0, 200, 100);
	
	lblAirportA = new JLabel(airportA);
	lblAirportA.setFont(fontNanumGothic20);
	lblAirportA.setBounds(780, 0, 200, 100);
	
	
	jpSelectedInfo.add(lblDepP);
	jpSelectedInfo.add(lblArrP);
	jpSelectedInfo.add(lblGoComeDate);
	
	jpFlightTOP.add(lblFliCo);
	jpFlightTOP.add(lblDate);
	jpFlightTOP.add(lblTime);
	jpFlightTOP.add(lblAirportD);
	jpFlightTOP.add(lblAirportA);
	
	setVisible(true);

		
	}
	private void Insert() {
		
		System.out.println("버튼");
		
		try{
			Class.forName(driver);
		}catch (ClassNotFoundException e) {
		}
		try {
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
		java.sql.PreparedStatement ps = null;
		try {
		String sql = "INSERT INTO reservation(reserveNum, GOscheduleNo, COMscheduleNo, ID, adult, child, infant, pay, GOclass, COMclass) VALUES (?,?,?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1,reserveNum);
		ps.setString(2,GOscheduleNo);
		ps.setString(3,null);
		ps.setString(4,ID);
		ps.setInt(5, numAdult);
		ps.setInt(6,numInfant);
		ps.setInt(7,numChild);
		ps.setInt(8,0);
		ps.setString(9,selectedSeatGo);
		ps.setString(10,null);
		 int res = ps.executeUpdate();
		 if(res>0) {
			 System.out.println(sql);
		}else {
			 System.out.println("XXX");
		 }
		}catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
    }

	private void Find() {	
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
				state = conn.createStatement();
//				System.out.println("oo");
				
//				String DepP = "CJU";
//				String ArrP = "GMP";
				
				String sql;
				sql = "SELECT * FROM airport WHERE `code` = '"+ SelectDepCode +"' ";
				
				ResultSet rs = state.executeQuery(sql);
				while (rs.next()) {
					airportD = rs.getString("airport");
						
//					System.out.println(airportD);
					
//					DepAP = airportD;
				}
				rs.close();
				state.close();
				conn.close();
				
	    }
	    catch (Exception e) {
		}finally {
			try {
				if(state!=null) 
					state.close();
			}catch (SQLException ex1) {
			}
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException ex2) {
			}
		}
		
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
				state = conn.createStatement();
//				System.out.println("oo");
				
//				String ArrP = "GMP";
				
				String sql;
				sql = "SELECT * FROM airport WHERE `code` = '"+ SelectArrCode +"' ";
				
				ResultSet rs = state.executeQuery(sql);
				while (rs.next()) {
					airportA = rs.getString("airport");
						
//					System.out.println(airportA);
					
					
//					ArrAP = airportA;
				}
				rs.close();
				state.close();
				conn.close();
				
	    }
	    catch (Exception e) {
		}finally {
			try {
				if(state!=null) 
					state.close();
			}catch (SQLException ex1) {
			}
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException ex2) {
			}
		}
			try{
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			state = conn.createStatement();	
				
					String sql;
					sql = "SELECT * FROM airSchedule WHERE `from` = '"+ SelectDepCode +"' and fromDate = " + goDay +" and `to` = '" + SelectArrCode +"'";
//					sql = "SELECT * FROM airSchedule WHERE `from` = '"+ DepP +"' and fromDate = " + GoDay +" and `to` = '" + ArrP +"'and toDate = " + ComeDay +"";
					
					ResultSet rs = state.executeQuery(sql);
					while (rs.next()) {
						scheduleNo = rs.getString("scheduleNo");
						flightCode = rs.getString("flightCode");
						
						from = rs.getString("from");
						fromDate = rs.getString("fromDate");
						fromTime = rs.getString("fromTime");

						to = rs.getString("to");
//						toDate = rs.getString("toDate");
						toTime = rs.getString("toTime");
						
						GOscheduleNo = scheduleNo;
						flightCode = flightCode;
					}
						
					
//					rs.close();
//					state.close();
//					conn.close();
		    }
		    catch (Exception e) {
			}finally {
				try {
					if(state!=null) 
						state.close();
				}catch (SQLException ex1) {
				}
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException ex2) {
				} 
			}
			
			 try{
					Class.forName(driver);
					conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
					state = conn.createStatement();
					
//					String SelectedFliCo = "IH8985";
					
					String sql;
					sql = "SELECT * FROM airplane WHERE `flightCode` = '"+ flightCode +"' ";
					
					ResultSet rs = state.executeQuery(sql);
					while (rs.next()) {
						economyPay = rs.getInt("economyPay");
						businessPay = rs.getInt("businessPay");
						firstPay = rs.getInt("firstPay");
							
					}
					rs.close();
					state.close();
					conn.close();
					
		    }
		    catch (Exception e) {
			}finally {
				try {
					if(state!=null) 
						state.close();
				}catch (SQLException ex1) {
				}
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException ex2) {
				}
			}
			 try{
					Class.forName(driver);
					conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
					state = conn.createStatement();
					System.out.println("oo");
					
//					String DepP = "CJU";
//					String ArrP = "GMP";
					
					String sql;
					sql = "SELECT * FROM seat WHERE `scheduleNo` = '"+ scheduleNo +"' ";
					
					ResultSet rs = state.executeQuery(sql);
					while (rs.next()) {
						economy = rs.getInt("economy");
						business = rs.getInt("business");
						first = rs.getInt("first");
							
//						System.out.println(airportD);
						
//						DepAP = airportD;
					}
					rs.close();
					state.close();
					conn.close();
					
		    }
		    catch (Exception e) {
			}finally {
				try {
					if(state!=null) 
						state.close();
				}catch (SQLException ex1) {
				}
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException ex2) {
				}
			} 
	}
	
	public static void main(String[] args) {
//		new TicketingRoundTripGoingForm();
//		a = new TicketingRoundTripGoingForm();
//		a.roundtrip();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnMainMenu) {
			mainMenuForm = new MainMenuForm();
			this.setVisible(false);
			
		} else if(obj == btnNext)
		{
			tkRTComForm= new customer.book.ticketing.TicketingRoundTripComingForm(this);
			this.setVisible(false);
			
			Insert();
		}
		else if(obj == btnEcon)
		{
			totalPay = economyPay;
			lblTotalPayGoing.setText(totalPay + "원");
			
			selectedSeatGo = "economy";
			setTotalPay(totalPay);
			
//			GoPay = totalPay;
			
//			btnEcon.setBackground(crNext);
			
			jpFlight1.setBackground(crChange);
		}
		else if(obj == btnBus)
		{
			totalPay = businessPay;
			lblTotalPayGoing.setText(totalPay + "원");	
			selectedSeatGo = "business";
			jpFlight2.setBackground(crChange);
//			GoPay = totalPay;
			
//			btnBus.setBackground(crNext);

			}
		else if(obj == btnFirs)
		{
			totalPay = firstPay;
			lblTotalPayGoing.setText(totalPay + "원");	
			
			selectedSeatGo = "first";
			jpFlight3.setBackground(crChange);
//			GoPay = totalPay;
			
//			btnFirs.setBackground(crNext);

			}
		
	}
	
}