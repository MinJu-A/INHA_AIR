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

public class MemberInquiryDetailForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	

	// 예원 - 컴포넌트
			private JButton btnMainMenu;
			// 예원 - Forms
			private MainMenuForm mainMenuForm;
			private MemberInquiryDetailForm detailForm;
			
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
	
			int agree;	
			int baggage;
			

			String reserveNum;
			String scheduleNo;
			String nameKOR ;
			String nameENG;
			String sex;
			String passport;	
			String birth = " ";	
			String tel;
			String email;	
			
			String GOflightCode;
			String from;
			String GOfromDate;
			String GOfromTime;
			String to;
			String toDate;
			String GOtoTime;
			
			String GOclass;
			String id;
//------------------------------------------------------------------------------------------------------------------
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
		private JLabel lblInquiryTitle;
		private JLabel lblPassengerDetail;
		private JLabel lblPNum;
		private JLabel lblFlightInfo;
		private JLabel lblFlightName;
		private JLabel lblDepArr;
		private JLabel lblDate;
		private JLabel lblSeat;
		private JButton btnCheckIn;
		private JLabel lblnameKOR;
		private JLabel lblnameENG;
		private JLabel lblSexBirth;
		private JLabel lblTel;
		private JLabel lblEmail;
		private JLabel lblPassportNo;
		private JLabel lblPassport;
		private JLabel lblGOFlightcode;
		private JLabel lblGOScheduleNo;
		private JLabel lblFromToP;
		private Component lblGOfromTime;
		private JLabel lblFromToD;
		private Component lblGOSeatInfo;
		private String seatClass;
		private JLabel lblToFromP;
		private String COMclass;
		private String COMscheduleNo;
		private String GOscheduleNo;
		private String COMflightCode;
		private String COMfromDate;
		private String COMfromTime;
		private String COMtoTime;
		private JLabel lblCOMFlightcode;
		private JLabel lblCOMscheduleNo;
		private JLabel lblToFromD;
		private JLabel lblCOMfromTime;
		private JLabel lblCOMSeatInfo;
		private JLabel lblNumOfP;
		private JLabel lblAdult;
		private JLabel lblInfant;
		private Component lblChild;
		private JLabel lblTotalNum;
		private int adult;
		private int child;
		private int infant;

	public MemberInquiryDetailForm(String id, String reserveNum) {
		this.reserveNum = reserveNum;
		this.id = id;
		
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
		
		lblInquiryTitle = new JLabel("예약 상세"); //큰 제목
		lblInquiryTitle.setBounds(70,85 , 200, 60);
		lblInquiryTitle.setFont(fontNanumGothic22);
		
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
	
		lblPassenger = new JLabel("예매자 정보");
		lblPassenger.setBounds(70, 149, 200, 20);
		lblPassenger.setFont(fontNanumGothic15Plain);
		
		lblFlightInfo = new JLabel("항공편 정보 (가는 편 / 오는 편)");
		lblFlightInfo.setBounds(70, 375, 400, 20);
		lblFlightInfo.setFont(fontNanumGothic15Plain);
		
		lblPName = new JLabel("예매자명");
		lblPName.setBounds(50, 10, 80, 20);
		lblPName.setFont(fontNanumGothic15);
		
		lblPassengerDetail = new JLabel("생년월일 및 성별");
		lblPassengerDetail.setBounds(250, 10, 150, 20);
		lblPassengerDetail.setFont(fontNanumGothic15);
		
		lblPNum = new JLabel("연락처");
		lblPNum.setBounds(460, 10, 80, 20);
		lblPNum.setFont(fontNanumGothic15);
		
		lblPassportNo = new JLabel("여권번호");
		lblPassportNo.setBounds(690, 10, 80, 20);
		lblPassportNo.setFont(fontNanumGothic15);
		
		lblNumOfP = new JLabel("예매 인원");
		lblNumOfP.setBounds(850, 10, 100, 20);
		lblNumOfP.setFont(fontNanumGothic15);
		
		lblFlightName = new JLabel("항공편");
		lblFlightName.setBounds(50, 10, 200, 20);
		lblFlightName.setFont(fontNanumGothic15);
		
		lblDepArr = new JLabel("출발지  -  도착지");
		lblDepArr.setBounds(250, 10, 150, 20);
		lblDepArr.setFont(fontNanumGothic15);
		
		lblDate = new JLabel("날짜 및 시간");
		lblDate.setBounds(460, 10, 220, 20);
		lblDate.setFont(fontNanumGothic15);
		
		lblSeat = new JLabel("좌석정보");
		lblSeat.setBounds(690, 10, 200, 20);
		lblSeat.setFont(fontNanumGothic15);
		
		jpInquiryDetailTop.add(lblPName);
		jpInquiryDetailTop.add(lblPassengerDetail);
		jpInquiryDetailTop.add(lblPNum);
		jpInquiryDetailTop.add(lblPassportNo);
		jpInquiryDetailTop.add(lblNumOfP);
		
		jpInquiryDetailTop2.add(lblFlightName);
		jpInquiryDetailTop2.add(lblDepArr);
		jpInquiryDetailTop2.add(lblDate);
		jpInquiryDetailTop2.add(lblSeat);
		
		add(lblPassenger);
		add(lblFlightInfo);
		
		jpInquiry1 = new JPanel(); 
		jpInquiry1.setLayout(null);
		jpInquiry1.setSize(1000,130);
		jpInquiry1.setLocation(70,227);
		jpInquiry1.setBackground(crInfo);
		
		jpInquiry2 = new JPanel();
		jpInquiry2.setLayout(null);
		jpInquiry2.setSize(1000,180);
		jpInquiry2.setLocation(70,454);
		jpInquiry2.setBackground(crInfo);
		
		add(lblInquiryTitle);
		add(jpInquiryDetailTop);
		add(jpInquiryDetailTop2);
		add(jpInquiry1);
		add(jpInquiry2);

		//---------------------------------------
		//---------------------------------------
		
//		lblnameKOR = new JLabel(nameKOR + " · " + sex); //이름
		lblnameKOR = new JLabel(nameKOR); //이름과 성별
		lblnameKOR.setBounds(50, 20, 80, 20);
		lblnameKOR.setFont(fontNanumGothic15);
		
		lblnameENG = new JLabel("(영문명 : " + nameENG +")");
		lblnameENG.setBounds(47, 45, 150, 20);
		lblnameENG.setFont(fontNanumGothic15Plain);
		
		lblSexBirth = new JLabel(birth + " ·  "+sex);
		lblSexBirth.setBounds(250, 20, 250, 20);
		lblSexBirth.setFont(fontNanumGothic15Plain);
		
		lblTel = new JLabel("☏ tel  :  " + tel.substring(0, 3)+"-"+tel.substring(3,7)+"-"+tel.substring(7,11));
		lblTel.setBounds(460, 20, 300, 20);
		lblTel.setFont(fontNanumGothic15Plain);
		
		lblEmail = new JLabel("☏ email  :  " + email);
		lblEmail.setBounds(460, 45, 300, 20);
		lblEmail.setFont(fontNanumGothic15Plain);
		
		lblPassport = new JLabel(passport);
		lblPassport.setBounds(690, 20, 300, 20);
		lblPassport.setFont(fontNanumGothic15Plain);
		
		lblAdult = new JLabel("성인 " + adult + "명 ");
		lblAdult.setBounds(850, 20, 300, 20);
		lblAdult.setFont(fontNanumGothic15Plain);
		
		lblChild = new JLabel("소아 " + child + "명 ");
		lblChild.setBounds(850, 50, 300, 20);
		lblChild.setFont(fontNanumGothic15Plain);
		
		lblInfant = new JLabel("유아 " + infant + "명 ");
		lblInfant.setBounds(850, 80, 300, 20);
		lblInfant.setFont(fontNanumGothic15Plain);
		
		//---------------------------------------
		
		jpInquiry1.add(lblnameKOR);
		jpInquiry1.add(lblnameENG);
		jpInquiry1.add(lblSexBirth);
		jpInquiry1.add(lblTel);
		jpInquiry1.add(lblEmail);
		jpInquiry1.add(lblPassport);
		jpInquiry1.add(lblAdult);
		jpInquiry1.add(lblChild);
		jpInquiry1.add(lblInfant);
		
		//----------------------------------------
		//----------------------------------------
		
		lblGOFlightcode = new JLabel("항공편명  :  " + GOflightCode);
		lblGOFlightcode.setBounds(50, 20, 150, 20);
		lblGOFlightcode.setFont(fontNanumGothic15Plain);
		
		lblGOScheduleNo = new JLabel("일정명  :  " + GOscheduleNo);
		lblGOScheduleNo.setBounds(50, 50, 150, 20);
		lblGOScheduleNo.setFont(fontNanumGothic15Plain);
		
		lblCOMFlightcode = new JLabel("항공편명  :  " + COMflightCode);
		lblCOMFlightcode.setBounds(50, 100, 150, 20);
		lblCOMFlightcode.setFont(fontNanumGothic15Plain);
		
		lblCOMscheduleNo = new JLabel("일정명  :  " + COMscheduleNo);
		lblCOMscheduleNo.setBounds(50, 130, 150, 20);
		lblCOMscheduleNo.setFont(fontNanumGothic15Plain);
		
		lblFromToP = new JLabel(from + " - " + to);
		lblFromToP.setBounds(270, 20, 150, 20);
		lblFromToP.setFont(fontNanumGothic18);
		
		lblToFromP = new JLabel(to + " - " + from);
		lblToFromP.setBounds(270, 100, 150, 20);
		lblToFromP.setFont(fontNanumGothic18);
		
		lblFromToD = new JLabel(GOfromDate);
		lblFromToD.setBounds(460, 20, 150, 20);
		lblFromToD.setFont(fontNanumGothic15Plain);
		
		lblToFromD = new JLabel(COMfromDate);
		lblToFromD.setBounds(460, 100, 150, 20);
		lblToFromD.setFont(fontNanumGothic15Plain);
		
		lblGOfromTime = new JLabel("출발 "+GOfromTime.substring(0,2)+":" + GOfromTime.substring(3,5) + " - 도착" + GOtoTime.substring(0,2)+":" + GOtoTime.substring(3,5));
		lblGOfromTime.setBounds(460, 50, 200, 20);
		lblGOfromTime.setFont(fontNanumGothic15Plain);
		
		lblCOMfromTime = new JLabel("출발 "+GOfromTime.substring(0,2)+":" + COMfromTime.substring(3,5) + " - 도착" + COMtoTime.substring(0,2)+":" + COMtoTime.substring(3,5));
		lblCOMfromTime.setBounds(460, 130, 200, 20);
		lblCOMfromTime.setFont(fontNanumGothic15Plain);
		
		lblGOSeatInfo = new JLabel("클래스 : " + GOclass );
		lblGOSeatInfo.setBounds(690, 20, 300, 20);
		lblGOSeatInfo.setFont(fontNanumGothic15Plain);
		
		lblCOMSeatInfo = new JLabel("클래스 : " + COMclass );
		lblCOMSeatInfo.setBounds(690, 100, 300, 20);
		lblCOMSeatInfo.setFont(fontNanumGothic15Plain);
		
		//-----------------------------------------
		//-----------------------------------------
		
		jpInquiry2.add(lblGOFlightcode);
		jpInquiry2.add(lblCOMFlightcode);
		
		jpInquiry2.add(lblFromToP);
		jpInquiry2.add(lblToFromP);
		jpInquiry2.add(lblFromToD);
		jpInquiry2.add(lblToFromD);
		jpInquiry2.add(lblGOfromTime);
		jpInquiry2.add(lblCOMfromTime);
		
		jpInquiry2.add(lblGOSeatInfo);
		jpInquiry2.add(lblCOMSeatInfo);
		
		setVisible(true);
	}
//----------------------------------------------------------
//----------------------------------------------------------
//----------------------------------------------------------
	private void Data() {
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			state = conn.createStatement();	

			
			String sql;
			sql = "SELECT * FROM reservation WHERE reserveNum = '"+ reserveNum +"' ";
			//reservation테이블에서 id 이용하여 예매 상세 정보를 검색

			ResultSet rs = state.executeQuery(sql);
			while (rs.next()) {
//				reserveNum = rs.getString("reserveNum");
				GOscheduleNo = rs.getString("GOscheduleNo");
				COMscheduleNo = rs.getString("COMscheduleNo");
				GOclass = rs.getString("GOclass");
				COMclass = rs.getString("COMclass");
				
				adult = rs.getInt("adult");
				child = rs.getInt("child");
				infant = rs.getInt("infant");
				
				this.reserveNum = reserveNum;
				this.GOscheduleNo= GOscheduleNo;
				this.COMscheduleNo = COMscheduleNo;
				this.GOclass = GOclass;
				this.COMclass = COMclass;
				this.adult = adult;
				this.child = child;
				this.infant = infant;
				
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
		
	//-------------------------------------------
	//-------------------------------------------
	try{
		Class.forName(driver);
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		state = conn.createStatement();	

		String sql;
		sql = "SELECT * FROM reservationDetail WHERE `reserveNum` = '"+ reserveNum +"' ";
		//reservationDetail테이블에서 reserveNum 이용하여 예매 상세 정보를 검색

		ResultSet rs = state.executeQuery(sql);
		while (rs.next()) {
			nameKOR = rs.getString("nameKOR");
			nameENG = rs.getString("nameENG");
			sex = rs.getString("sex");
			passport = rs.getString("passport");
			birth = rs.getString("birth");
			tel = rs.getString("tel");
			email = rs.getString("email");
			agree = rs.getInt("agree");
			baggage = rs.getInt("baggage");
			
			this.nameKOR = nameKOR;
			this.nameENG = nameENG;
			this.sex = sex;
			this.passport = passport;
			this.birth = birth;
			this.tel = tel;
			this.email = email;
			this.agree = agree;
			this.baggage = baggage;
			
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

//----------------------------------------
//----------------------------------------
	try{
		Class.forName(driver);
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		state = conn.createStatement();	

		String sql;
		sql = "SELECT * FROM airSchedule WHERE `scheduleNo` = '"+ GOscheduleNo +"' ";
		//airSchedule테이블에서 스케줄번호 이용하여 항공편 상세 정보를 검색

		ResultSet rs = state.executeQuery(sql);
		while (rs.next()) {
			GOflightCode = rs.getString("flightCode");
			from = rs.getString("from");
			GOfromDate = rs.getString("fromDate");
			GOfromTime = rs.getString("fromTime");
			to = rs.getString("to");
			GOtoTime = rs.getString("toTime");
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
//----------------------------------------
//----------------------------------------
	try{
		Class.forName(driver);
		conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		state = conn.createStatement();	
		
		String sql;
		sql = "SELECT * FROM airSchedule WHERE `scheduleNo` = '"+ COMscheduleNo +"' ";
		//airSchedule테이블에서 스케줄번호 이용하여 항공편 상세 정보를 검색

		ResultSet rs = state.executeQuery(sql);
		while (rs.next()) {
			COMflightCode = rs.getString("flightCode");
			COMfromDate = rs.getString("fromDate");
			COMfromTime = rs.getString("fromTime");
			COMtoTime = rs.getString("toTime");

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
	
//----------------------------------------
	public static void main(String[] args) {
//		new MemberInquiryDetailForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnMainMenu) {
			mainMenuForm = new MainMenuForm();
			mainMenuForm.setId(id);
			this.setVisible(false);
			
		}
	}
}

