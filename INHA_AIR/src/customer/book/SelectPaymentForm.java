package customer.book;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DataBase.databaseClass;
import be.main.MainForm;
import be.menu.MenuBar;
import customer.start.MainMenuForm;


public class SelectPaymentForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 예원 - 컴포넌트
	private JButton btnMainMenu;
	// 예원 - Forms
	private MainMenuForm mainMenuForm;
	private PaymentForm paymentForm;
	
	// 예원 - 색상
	Color colorLogo = new Color(24, 62, 111);
	Color colorBtn = new Color(10,90,150);
	Color colorGrayBtn = new Color(150,150,150);
	// 예원 - 폰트
	Font fontArial30 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
	
	
	// 폰트
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontNanumGothic30 = new Font("NanumGothic", Font.BOLD, 30);	// 나눔고딕 25
	
	// 결제 부분
	private JPanel jpPayment, jpLbl, jpBtn;
	private JLabel lblReserveNum, lblGo, lblCom, lblPeople, lblPay;
	private ButtonGroup bgPayment;
	private JRadioButton rbCash, rbCard;
	private JButton btnCancle, btnOK;	// 취소, 결제 버튼
	
	// 데이터 정보
	private String id;
	private String reserveNum = "test001010";			// 예매 번호(테스트값)
	private String GOscheduleNo = "";
	private String COMscheduleNo = "";
	private String GoData = "";
	private String ComData = "";
	private int adult = 0;	// 성인 인원
	private int child = 0;	// 소아 인원
	private int infant = 0;	// 유아 인원
	private int pay = 0;	// 금액
	

	public SelectPaymentForm(String reserveNum, String id) {
		this.reserveNum = reserveNum;
		this.id = id;
		
		// DB 정보 - 테스트 소스
		String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
		String dbID="inhaair";
		String dbPassword="1234";
		// 데이터베이스 연결 - 테스트 소스
		databaseClass.connect(dbURL, dbID, dbPassword);
		
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
		
		// 데이터베이스에서 예매 정보 가져옴
		getData();
		
		// 예원 - 컴포넌트 붙이기
		add(btnMainMenu);
		
		// 결제 화면
		setPayment();
		
		setVisible(true);
	}


	// 데이터정보 select
	private void getData() {
		
		// 인원, 스케쥴번호, 가격 가져오기
		String sqlReservation = "SELECT  GOscheduleNo, COMscheduleNo, adult, child, infant, pay "
				+ "FROM reservation "
				+ "WHERE reserveNum='" + reserveNum + "'";
		
		ResultSet rsReservation = databaseClass.select(sqlReservation);
		try {
			while(rsReservation.next()) {
				GOscheduleNo = rsReservation.getString("GOscheduleNo");
				COMscheduleNo = rsReservation.getString("COMscheduleNo");
				adult = Integer.parseInt(rsReservation.getString("adult"));
				child = Integer.parseInt(rsReservation.getString("child"));
				infant = Integer.parseInt(rsReservation.getString("infant"));
				pay = Integer.parseInt(rsReservation.getString("pay"));
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
		// 가는 날 데이터 가져오기
		String sqlGo = "SELECT flightCode, `from`, fromDate, `to`, toDate "
				+ "FROM inhaair.airSchedule "
				+ "WHERE scheduleNo='" + GOscheduleNo + "'";
		
		ResultSet rsGo = databaseClass.select(sqlGo);
		
		String flightCode = "";
		String from = "";
		String fromDate = "";
		String to = "";
		String toDate = "";
		try {
			while(rsGo.next()) {
				flightCode = rsGo.getString("flightCode");
				from = rsGo.getString("from");
				fromDate = rsGo.getString("fromDate");
				to = rsGo.getString("to");
				toDate = rsGo.getString("toDate");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		GoData = "편명 " + flightCode + "   |   " + from + " -> " + to + "   |   " + fromDate + " ~ " + toDate;
		
		
		// 오는날 데이터 가져오기
		String sqlCom = "SELECT flightCode, `from`, fromDate, `to`, toDate "
				+ "FROM inhaair.airSchedule "
				+ "WHERE scheduleNo='" + COMscheduleNo + "'";
		
		ResultSet rsCom = databaseClass.select(sqlCom);
		
		flightCode = "";
		from = "";
		fromDate = "";
		to = "";
		toDate = "";
		try {
			while(rsCom.next()) {
				flightCode = rsCom.getString("flightCode");
				from = rsCom.getString("from");
				fromDate = rsCom.getString("fromDate");
				to = rsCom.getString("to");
				toDate = rsCom.getString("toDate");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ComData = "편명 " + flightCode + "   |   " + from + " -> " + to + "   |   " + fromDate + " ~ " + toDate;
	}


	// 결제수단 선택 화면
	private void setPayment() {
		jpPayment = new JPanel(null);
		jpPayment.setSize(1100, 635);
		jpPayment.setLocation(3, 90);
		jpPayment.setBackground(Color.WHITE);
		
		// 라벨 부분
		jpLbl = new JPanel(null);
		jpLbl.setSize(1000, 500);
		jpLbl.setLocation(50, 10);
		jpLbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));		// 테두리 설정
		jpLbl.setBackground(Color.WHITE);
		
		// 예매 확인 내용 표시
		setCheck();
		
		// 결제하기 버튼 부분
		jpBtn = new JPanel(new GridLayout(1, 2, 30, 30));
		jpBtn.setBorder(new EmptyBorder(30, 50, 30, 50));
		jpBtn.setSize(1100, 100);
		jpBtn.setLocation(0, 520);
		jpBtn.setBackground(Color.WHITE);
		
		btnCancle = new JButton("취소");
		btnCancle.addActionListener(this);
		btnCancle.setFont(fontNanumGothic20);
		btnCancle.setVerticalAlignment(SwingConstants.CENTER);
		btnCancle.setBackground(colorGrayBtn);
		btnCancle.setForeground(Color.WHITE);
		btnCancle.setBorderPainted(false);
		jpBtn.add(btnCancle);
		
		btnOK = new JButton("결제");
		btnOK.addActionListener(this);
		btnOK.setFont(fontNanumGothic20);
		btnOK.setVerticalAlignment(SwingConstants.CENTER);
		btnOK.setBackground(colorBtn);
		btnOK.setForeground(Color.WHITE);
		btnOK.setBorderPainted(false);
		jpBtn.add(btnOK);

		jpPayment.add(jpLbl);
		jpPayment.add(jpBtn);
		
		add(jpPayment);
	}

	// 예매 확인 내역 표시
	private void setCheck() {
		// 예매확인 라벨
		JLabel lblTitle = new JLabel("예매 확인");
		lblTitle.setFont(fontNanumGothic25);
		lblTitle.setHorizontalAlignment(JLabel.LEFT);
		lblTitle.setSize(900, 50);
		lblTitle.setLocation(50, 20);
		
		// 예매 내역 표시 패널
		JPanel jpCheck = new JPanel(new GridLayout(7, 1, 10, 10));
		jpCheck.setBorder(new EmptyBorder(10, 10, 10, 10));
		jpCheck.setSize(900, 400);
		jpCheck.setLocation(50, 80);
		jpCheck.setBackground(Color.WHITE);
		
		// 예매 번호, 인원 수
		JPanel jpReserveInfo1 = new JPanel(new GridLayout(1, 4, 5, 5));
		jpReserveInfo1.setBackground(Color.WHITE);
		JLabel lbl1 = new JLabel("예매번호");
		lbl1.setFont(fontNanumGothic18);
		lblReserveNum = new JLabel(reserveNum);
		lblReserveNum.setFont(fontNanumGothic15);
		JLabel lbl2 = new JLabel("인원수");
		lbl2.setFont(fontNanumGothic18);
		lblPeople = new JLabel("성인 " + adult + "명 | 소아 " + child + "명 | 유아 " + infant + "명");
		lblPeople.setFont(fontNanumGothic15);
		jpReserveInfo1.add(lbl1);
		jpReserveInfo1.add(lblReserveNum);
		jpReserveInfo1.add(lbl2);
		jpReserveInfo1.add(lblPeople);
		
		// 항공편 정보
		JLabel lbl3 = new JLabel("항공편");
		lbl3.setFont(fontNanumGothic18);
		lblGo = new JLabel(GoData);
		lblGo.setFont(fontNanumGothic15);
		lblCom = new JLabel(ComData);
		lblCom.setFont(fontNanumGothic15);
		
		// 가격
		JPanel jpPay = new JPanel(new GridLayout(1, 2));
		jpPay.setBackground(Color.WHITE);
		JLabel lbl4 = new JLabel("가격");
		lbl4.setFont(fontNanumGothic18);
		lblPay = new JLabel(pay + " 원");
		lblPay.setFont(fontNanumGothic15);
		jpPay.add(lbl4);
		jpPay.add(lblPay);
		
		// 결제 수단
		JLabel lbl5 = new JLabel("결제수단");
		lbl5.setFont(fontNanumGothic18);
		JPanel jpPayment = new JPanel(new GridLayout(1, 2, 10, 10));
		jpPayment.setBackground(Color.WHITE);
		bgPayment = new ButtonGroup();
		rbCash = new JRadioButton("무통장 입금", true);
		rbCash.setFont(fontNanumGothic15);
		rbCash.setBackground(Color.WHITE);
		rbCard = new JRadioButton("신용카드", false);
		rbCard.setFont(fontNanumGothic15);
		rbCard.setBackground(Color.WHITE);
		bgPayment.add(rbCash);
		bgPayment.add(rbCard);
		jpPayment.add(rbCash);
		jpPayment.add(rbCard);
		
		// 컴포넌트 붙이기
		jpCheck.add(jpReserveInfo1);
		jpCheck.add(lbl3);
		jpCheck.add(lblGo);
		jpCheck.add(lblCom);
		jpCheck.add(jpPay);
		jpCheck.add(lbl5);
		jpCheck.add(jpPayment);
		
		jpLbl.add(lblTitle);
		jpLbl.add(jpCheck);
	}


	public static void main(String[] args) {
		new SelectPaymentForm("test001010", "test1");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnMainMenu || obj == btnCancle) {
			clickMain();
			
		}  else if(obj == btnOK) {
			
			int result = JOptionPane.showConfirmDialog(null, "결제를 진행하시겠습니까?", "결제 안내", JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.YES_OPTION) {
				if(rbCash.isSelected()) {
					payment("cash");
				} else if(rbCard.isSelected()) {
					payment("card");
				}
			}

		}
	}

	private void payment(String type) {
		String sql = "INSERT INTO payment(reserveNum, `date`, payable, pay) VALUES('" + reserveNum + "', NOW(), '" + type + "', " + 
				"( SELECT pay FROM reservation WHERE reserveNum='" + reserveNum + "'))";
		
		int rs = databaseClass.insert(sql);
		
		if(rs == 1) {
			
			// 잔여좌석 수 변경
			updateSeat();
			
		} else {
			JOptionPane.showMessageDialog(null, "결제 실패했습니다.", "결제 실패", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	// 잔여좌석 수 변경
	private void updateSeat() {
		
		// 스케쥴번호, 좌석 클래스 정보 select
		String sqlSchedule = "SELECT GOscheduleNo, COMscheduleNo, GOclass, COMclass FROM reservation WHERE reserveNum = '" + reserveNum + "'";
		
		String GOscheduleNo = "";
		String COMscheduleNo = "";
		String GOclass = "";
		String COMclass = "";
		
		ResultSet rsSchedule = databaseClass.select(sqlSchedule);
		try {
			while(rsSchedule.next()) {
				GOscheduleNo = rsSchedule.getString("GOscheduleNo");
				COMscheduleNo = rsSchedule.getString("COMscheduleNo");
				GOclass = rsSchedule.getString("GOclass");
				COMclass = rsSchedule.getString("COMclass");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 잔여 좌석 수 select
		int goSeat = 0;
		int comSeat = 0;
		
		String sqlGoSeat = "SELECT " + GOclass + " FROM seat WHERE scheduleNo='" + GOscheduleNo + "'";
		String sqlComSeat = "SELECT " + COMclass + " FROM seat WHERE scheduleNo='" + COMscheduleNo + "'";
		
		// 가는날 잔여좌석수
		ResultSet rsGoSeat = databaseClass.select(sqlGoSeat);
		try {
			while(rsGoSeat.next()) {
				goSeat = Integer.parseInt(rsGoSeat.getString(1));
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
		// 오는날 잔여 좌석수
		ResultSet rsComSeat = databaseClass.select(sqlComSeat);
		try {
			while(rsComSeat.next()) {
				comSeat = Integer.parseInt(rsComSeat.getString(1));
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
		// 잔여 좌석수 변경
		// 탑승인원 수 계산
		int people = adult + child;
		
		// 잔여 좌석 수 계산
		goSeat = goSeat - people;
		comSeat = comSeat - people;
		
		String sqlUpdate = "UPDATE seat SET " + GOclass + "=" + goSeat + " WHERE scheduleNo='" + GOscheduleNo + "'";
		
		// 가는날 잔여 좌석수 update
		int resultGo = databaseClass.update(sqlUpdate);
		
		if(resultGo == 1) {
			// 가는날 잔여 좌석수 update 성공 시
			sqlUpdate = "UPDATE seat SET " + COMclass + "=" + comSeat + " WHERE scheduleNo='" + COMscheduleNo + "'";
			
			// 오는날 잔여 좌석수 update
			int resultCom = databaseClass.update(sqlUpdate);
			
			if(resultCom == 1) {
				// 오는날 잔여 좌석수 update 성공시 결제완료 창으로 이동
				paymentForm = new PaymentForm(id);
				this.setVisible(false);
			}
		} 
	}


	private void clickMain() {
		// 정보 입력된 승객이 있는지 확인
		String sql = "SELECT COUNT(*) FROM reservationDetail WHERE reserveNum = '" + reserveNum + "'";
		ResultSet rs = databaseClass.select(sql);
					
		int rouCount = 0;
		try {
			while(rs.next()) {
			rouCount = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
					
		System.out.println(rouCount);
		
		int resdel = 0;
		
		if(rouCount == 0) {
			// 정보 입력된 승객이 없으면 
			
			// reservation 삭제
			resdel = delReservation();
			
			// 첫화면으로 이동
			mainMenuForm = new MainMenuForm();
			this.setVisible(false);
						
		} else {
			// 정보 입력된 승객이 있으면 승객 정보 삭제 
			sql = "DELETE FROM reservationDetail WHERE reserveNum = '" + reserveNum + "'";
						
			int result = databaseClass.delete(sql);
			
			if(result == 1) {
				// reservationDetail 테이블에 해당 예매 삭제 성공시
				// reservation 테이블에서 해당 예매 삭제
				resdel = delReservation();
							
				if(resdel == 1) {
					// reservation 테이블에 해당 예매 삭제 성공시
					// 첫 화면으로 이동
					mainMenuForm = new MainMenuForm();
					mainMenuForm.setId(id);
					this.setVisible(false);
				} else {
					// 삭제 실패시 다이얼로그 띄움
					JOptionPane.showMessageDialog(null, "첫 화면으로 이동할 수 없습니다.", "오류 안내", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				// 삭제 실패시 다이얼로그 띄움
				JOptionPane.showMessageDialog(null, "첫 화면으로 이동할 수 없습니다.", "오류 안내", JOptionPane.WARNING_MESSAGE);
			}
		}
					
	}
	
	// reservation 테이블에서 해당 예약 삭제
	private int delReservation() {
		int resdel = 0;
		
		String sql = "DELETE FROM reservation WHERE reserveNum = '" + reserveNum + "'";
		
		resdel = databaseClass.delete(sql);
		
		return resdel;
		
	}

}
