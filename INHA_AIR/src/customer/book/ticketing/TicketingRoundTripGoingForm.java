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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

import be.main.MainForm;
import be.menu.MenuBar;
import customer.book.BookForm;
import customer.start.MainMenuForm;

public class TicketingRoundTripGoingForm extends JFrame implements ActionListener {
	private static TicketingRoundTripGoingForm a;
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 예원 - 컴포넌트
	private JButton btnMainMenu;
	// 예원 - Forms
	private MainMenuForm mainMenuForm; //mainmenuform 객체생성
	private TicketingRoundTripComingForm tkRTComForm; //TicketingRoundTripComingForm 객체생성
	
	
	// 예원 - 색상
	Color colorLogo = new Color(24, 62, 111);
	// 예원 - 폰트
	Font fontArial30 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
	
	
	String driver = "com.mysql.cj.jdbc.Driver"; //드라이버
	String dbURL = "jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false"; //접속할 DB 서버
	String dbID = "inhaair"; //DB에 접속할 사용자 이름을 상수로 정의
	String dbPassword = "1234"; //사용자의 비밀번호를 상수로 정의

    Connection conn = null; // 연결을 해주는 객체를 생성, 일단 초기값은 null로
	Statement state = null; 
	
	private BookForm sel; //bookform 객체 생성
	
	
		private JPanel jpSelectedInfo;
		String COMclass; // 오는 편의 좌석정보
		String COMscheduleNo; //오는 편의 스케줄 번호
		private double totalPay; // 가는 편의 총 티켓 가격을 double형으로 선언

		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		 Calendar c = Calendar.getInstance();
		 String strToday = sdf.format(c.getTime()); //현재 날짜와 시간을 구하기 위해 SimpleDateFormat 클래스에서 sdf라는 이름의 새로운 객체를 생성
			
		 SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
         Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
         String sdf2 = fourteen_format .format(date_now);
         
		 
         private double TotalPay;

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
		public void setTotalPay(double totalPay) {
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
		public double getTotalPay() {
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
	this.numChild = sel.getNumInfant();		//소아 수
	this.numInfant = sel.getNumChild();		//유아 수
	this.numTotal = sel.getNumTotal();		//총 인원 수
	
	//출발지 값
	this.SelectDep = "";		//선택 출발지
	this.SelectDepCode= sel.getSelectDepCode();	//선택 출발지 코드
	
	//도착지 값
	this.SelectArr= "";		//선택 도착지
	this.SelectArrCode= sel.getSelectArrCode();	//선택 도착지 코드
	this.ID = sel.getId();
	
	reserveNum = goDay.substring(0, 3) + comeDay.substring(2,5) + ID.substring(0, 2) + sdf2.substring(11, 13) +"-" +strToday.substring(4, 6); //예매 번호 생성하는 방법

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
	
	jpFlightTOP = new JPanel(); //항공편 정보를 나타냄
	jpFlightTOP.setLayout(null);
	jpFlightTOP.setSize(1000,100);
	jpFlightTOP.setLocation(70,185);
	jpFlightTOP.setBackground(crTop);
	
	jpFlight1 = new JPanel(); //3개의 좌석등급 중 선택 1
	jpFlight1.setLayout(null);
	jpFlight1.setSize(1000,70);
	jpFlight1.setLocation(70,310);
	jpFlight1.setBackground(crInfo);
	
	jpFlight2 = new JPanel();//3개의 좌석등급 중 선택 1
	jpFlight2.setLayout(null);
	jpFlight2.setSize(1000,70);
	jpFlight2.setLocation(70,400);
	jpFlight2.setBackground(crInfo);
	
	jpFlight3 = new JPanel();// 3개의 좌석등급 중 선택 1
	jpFlight3.setLayout(null);
	jpFlight3.setSize(1000,70);
	jpFlight3.setLocation(70,490);
	jpFlight3.setBackground(crInfo);
	
	jpSelectedInfo.add(lblArrow); // 화살표
	jpSelectedInfo.add(lblPassenger); //탑승 인원 정보(인원수)
	
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
	
	btnNext = new JButton("다음"); //오는 편의 항공편을 선택하는 창으로 이동하는 버튼
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
	
	Find(); //필요한 정보를 검색하는 메소드
	
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
	
	lblGoComeDate = new JLabel(goDay.substring(0, 4)+"-"+goDay.substring(4, 6)+"-"+goDay.substring(6, 8) + "~" 
			+ comeDay.substring(0, 4)+"-"+comeDay.substring(4, 6)+"-"+comeDay.substring(6, 8)); //가는 날짜와 오는 날짜
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
		
			String sql = "INSERT INTO reservation(`date`, reserveNum, GOscheduleNo, COMscheduleNo, ID, adult, child, infant, pay, GOclass, COMclass) VALUES (NOW(),?,?,?,?,?,?,?,?,?,?)";
			//선택한 정보를 예매 테이블에 삽입 한다.
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,reserveNum);
			ps.setString(2,GOscheduleNo);
			ps.setString(3,null);
			ps.setString(4,ID);
			ps.setInt(5, numAdult);
			ps.setInt(6,numChild);
			ps.setInt(7,numInfant);
			ps.setInt(8,0);
			ps.setString(9,selectedSeatGo);
			ps.setString(10,null);
			//매개변수 값 대입 + 매개변수 유효화 처리.
			
		 int res = ps.executeUpdate();
		 // 쿼리 문장이 insert, delete, update로 시작을 하면
		 // 실행하는 메소드는 executeUpdate()를 사용하고 그 결과는 int형이다.
		 // 쿼리 문장이 select로 시작을 하면
		 // 실행하는 메소드는 executeQuery()를 사용하고 그 결과는 ResultSet형이다.
		 
		 if(res>0) {
		}else {
		 }
		}catch (SQLException e) {
			e.printStackTrace();// 예외 결과를 화면에 출력
		}
    }

	private void Find() {	
			try{
				Class.forName(driver);
				conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
				state = conn.createStatement();
				
				String sql;
				sql = "SELECT * FROM airport WHERE `code` = '"+ SelectDepCode +"' ";
				//공항 코드를 이용하여 공항이름을 검색하여 변수 저장
				
				ResultSet rs = state.executeQuery(sql);
				while (rs.next()) {
					airportD = rs.getString("airport");
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
				sql = "SELECT * FROM airport WHERE `code` = '"+ SelectArrCode +"' ";
				
				ResultSet rs = state.executeQuery(sql);
				while (rs.next()) {
					airportA = rs.getString("airport");
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
					//airSchedule 테이블에서 데이터를 검색하여 변수로 저장
					
					ResultSet rs = state.executeQuery(sql);
					// 쿼리 문장이 select로 시작을 하면
					// 실행하는 메소드는 executeQuery()를 사용하고 그 결과는 ResultSet형이다.
					
					while (rs.next()) {
						scheduleNo = rs.getString("scheduleNo");
						flightCode = rs.getString("flightCode");
						
						from = rs.getString("from");
						fromDate = rs.getString("fromDate");
						fromTime = rs.getString("fromTime");

						to = rs.getString("to");
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
					
					String sql;
					sql = "SELECT * FROM airplane WHERE `flightCode` = '"+ flightCode +"' ";
					//airplane 테이블에서 항공기 코드를 이용하여 가격정보를 검색
					
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
					
					String sql;
					sql = "SELECT * FROM seat WHERE `scheduleNo` = '"+ scheduleNo +"' ";
					//seat 테이블에서 스케줄 번호를 이용하여 좌석 수를 검색
					
					ResultSet rs = state.executeQuery(sql);
					while (rs.next()) {
						economy = rs.getInt("economy");
						business = rs.getInt("business");
						first = rs.getInt("first");
							
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
		
	}
	

@Override
public void actionPerformed(ActionEvent e) {
	Object obj = e.getSource();
	
	if(obj == btnMainMenu) {
		mainMenuForm = new MainMenuForm();
		this.setVisible(false);
		
	} 
	else if(obj == btnEcon)
	{
		totalPay = Math.round(economyPay * (numAdult + numChild*(0.8)));
		
		String pay = Double.toString(totalPay);
		pay = pay.substring(0, pay.length()-2);
		lblTotalPayGoing.setText(pay + "원");
		
		selectedSeatGo = "economy";
		setTotalPay(totalPay);
		
	}
	else if(obj == btnBus)
	{
		totalPay = Math.round(businessPay * (numAdult + numChild*(0.8)));
		
		String pay = Double.toString(totalPay);
		pay = pay.substring(0, pay.length()-2);
		lblTotalPayGoing.setText(pay + "원");
		selectedSeatGo = "business";
		
	}
	else if(obj == btnFirs)
	{
		totalPay = Math.round(firstPay * (numAdult + numChild*(0.8)));
		String pay = Double.toString(totalPay);
		pay = pay.substring(0, pay.length()-2);
		lblTotalPayGoing.setText(pay + "원");	
		
		selectedSeatGo = "first";
		
	}else if(obj == btnNext)
	{
		if(totalPay == 0) {
			JOptionPane.showMessageDialog(null, "가는 편을 선택하세요", "알림", JOptionPane.WARNING_MESSAGE);

		}
		else {
		tkRTComForm= new customer.book.ticketing.TicketingRoundTripComingForm(this);
		this.setVisible(false);
		
		Insert(); //선택한 정보를 테이블에 삽입하는 메소드
		}
	}
	
}

}