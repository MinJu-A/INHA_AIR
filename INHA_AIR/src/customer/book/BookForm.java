package customer.book;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import be.Reservation.SelectDate_old;
import be.Reservation.SelectPassenger;
import be.main.MainForm;
import be.menu.MenuBar;
import customer.book.ticketing.TicketingRoundTripGoingForm;
import customer.start.MainMenuForm;


// 연우 - 예매창 설정

public class BookForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR - 예매하기";
	private int width = 1120, height = 770;
	
	//infant유아 child소아
	private ImageIcon imgAirport, imgDate, imgPeople, imgSeat, imgSwap;
	
	// 폰트
	Font fontArial30 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 18
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	
	// 색상
	Color colorLogo = new Color(24, 62, 111);
	Color colorBtn = new Color(10,90,150);
	
	// 컴포넌트
	private JButton btnMainMenu;
	private JPanel jpBook;
	
	//id값 받아오기
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	private JPanel jpCountry, jpCalNum, jpDep, jpSwap, jpArr; //나라, 출발지, 도착지, 승객인원, 교환
//	private ImageIcon imgAirport, imgDate, imgPeople, imgSeat, imgSwap; //이미지
	private JLabel lblDate, lblPeople, lblSeat, lblDep, lblArr; //날짜, 인원, 탑승일, 도착지, 선택지 등
	private JButton btnSearch, btnPeople, btnDate, btnDep, btnArr, btnSwap; //찾기, 인원, 날짜, 출발지, 도착지, 교환버튼
	private JComboBox<String> cbSeat;
	private String swapText;
	private String swapCode;
	
	// Forms
	private MainMenuForm mainMenuForm;
	private TicketingRoundTripGoingForm tkRTGoForm;
	
	
	//연우 - 탑승일 값 받아오기
	private String goDay = "";
	private String comeDay = "";
	private int roundTrip = 0;
	
	//연우 - 승객 인원 값 받아오기
	private int numAdult = 0;
	private int numInfant = 0;
	private int numChild = 0;
	private int numTotal = 0;
	
	//연우 - 출발지 값 받아오기
	private String SelectDep = "";
	private String SelectDepCode = "";
	
	//연우 - 도착지 값 받아오기
	private String SelectArr = "";
	private String SelectArrCode = "";
	private JButton btnReset;
	Image img = new ImageIcon("imgs/swap.png").getImage();
	
	//도착지 값 받아오기
	public String getSelectArr() {
		return SelectArr;
	}

	public void setSelectArr(String selectArr) {
		SelectArr = selectArr;
	}

	public String getSelectArrCode() {
		return SelectArrCode;
	}

	public void setSelectArrCode(String selectArrCode) {
		SelectArrCode = selectArrCode;
	}
	
	public void setArr() {
		//btnArr.setText(SelectArr);
		btnArr.setText(SelectArr);
	}

	//출발지 값 받아오기
	public String getSelectDep() {
		return SelectDep;
	}

	public void setSelectDep(String selectDep) {
		SelectDep = selectDep;
	}

	public String getSelectDepCode() {
		return SelectDepCode;
	}

	public void setSelectDepCode(String selectDepCode) {
		SelectDepCode = selectDepCode;
	}

	public void setDep() {
		//btnDep.setText(SelectDep);
		btnDep.setText(SelectDep);
	}
	
	
	//승객 인원 값
	public int getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}

	
	public int getNumAdult() {
		return numAdult;
	}

	public void setNumAdult(int numAdult) {
		this.numAdult = numAdult;
	}

	public int getNumInfant() {
		return numInfant;
	}

	public void setNumInfant(int numInfant) {
		this.numInfant = numInfant;
	}

	public int getNumChild() {
		return numChild;
	}

	public void setNumChild(int numChild) {
		this.numChild = numChild;
	}

	
	//탑승일 값
	public String getGoDay() {
		return goDay;
	}

	public void setGoDay(String goDay) {
		this.goDay = goDay;
	}

	public String getComeDay() {
		return comeDay;
	}

	public void setComeDay(String comeDay) {
		this.comeDay = comeDay;
	}

	public int getRoundTrip() {
		return roundTrip;
	}

	public void setRoundTrip(int roundTrip) {
		this.roundTrip = roundTrip;
	}

	public void setDate() {	//날짜 값 설정
		btnDate.setText(goDay+" ~ "+comeDay);
	}
	
	public void setPassenger() {
		btnPeople.setText("<HTML>성인 "+ numAdult + "명 / 소아 " + numInfant + "명<br>유아 " + numChild + "명 / 총 "+ numTotal + "명</HTML>");
	}
	
	

	public BookForm(String id) {
		this.id = id;
		
		//창 설정
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		setLayout(null);
		
		// 시작 버튼
		btnMainMenu = new JButton("INHA AIR");
		btnMainMenu.setLayout(null);
		btnMainMenu.setSize(200, 50);
		btnMainMenu.setLocation(10, 10);
		btnMainMenu.setFont(fontArial30);
		btnMainMenu.setForeground(colorLogo);
		btnMainMenu.setBorderPainted(false);
		btnMainMenu.setBackground(Color.WHITE);
		btnMainMenu.addActionListener(this);
		
		
		// 예매 레이아웃
		setBook();
		
		
		// 컴포넌트 붙이기
		add(btnMainMenu);
		
		setVisible(true);
	}

	private void setBook() {
		
		// 예매창 패널
		jpBook = new JPanel();
		jpBook.setLayout(null);
		jpBook.setSize(1000, 600);
		jpBook.setLocation(50, 80);
		jpBook.setBackground(Color.white);
		
		
		//출발지 도착지
		jpCountry = new JPanel();
		jpCountry.setLayout(null);
		jpCountry.setBackground(Color.green);
		jpCountry.setSize(500, 200);
		jpCountry.setLocation(250, 50);
		
		//출발지
		jpDep = new JPanel();
		jpDep.setBackground(Color.white);
		jpDep.setLayout(null);
		jpDep.setSize(200, 200);
		jpDep.setLocation(0, 0);
		
		lblDep = new JLabel("출발"); //출발라벨
		lblDep.setFont(fontNanumGothic25);
		lblDep.setSize(150, 35);
		lblDep.setLocation(75, 0);
		
		btnDep = new JButton();	//출발버튼
		//btnDep.setText("<HTML><body><h2><center>From</center></h2></body></HTML>");
		btnDep.setLayout(null);
		btnDep.setSize(150, 100);
		btnDep.setLocation(25, 40);
		btnDep.setText("<HTML><body style ='text-align:center;'>From<br>출발지</body></HTML>");
		btnDep.setFont(fontNanumGothic20);
		btnDep.setBackground(Color.white);
		//btnDep.setBorderPainted(false);
		btnDep.addActionListener(this);
		jpDep.add(lblDep);
		jpDep.add(btnDep);
		
		
		jpSwap = new JPanel(); //출발지 도착지 교환 패널
		jpSwap.setBackground(Color.white);
		jpSwap.setLayout(null);
		jpSwap.setSize(100, 200);
		jpSwap.setLocation(200, 0);
		
		Image img = new ImageIcon("imgs/swap.png").getImage(); //이미지 생성
		Image changeimg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imgSwap = new ImageIcon(changeimg);
		
		btnSwap = new JButton(imgSwap); //출발지 도착지 교환 버튼
		btnSwap.setLayout(null);
		btnSwap.setSize(100, 100);
		btnSwap.setLocation(0, 40);
		btnSwap.setBorderPainted(false);
		btnSwap.setBackground(Color.white);
		btnSwap.addActionListener(this);
//		btnswap.setContentAreaFilled(false); //버튼 색 투명
		jpSwap.add(btnSwap);
		
		jpArr = new JPanel();	//도착지 패널
		jpArr.setLayout(null);
		jpArr.setBackground(Color.white);
		jpArr.setSize(200, 200);
		jpArr.setLocation(300, 0);
		
		lblArr = new JLabel("도착");	//도착지 라벨
		lblArr.setFont(fontNanumGothic25);
		lblArr.setSize(150, 35);
		lblArr.setLocation(75, 0);
		
		btnArr = new JButton();	//도착지 버튼
		btnArr.setLayout(null);
		//btnArr.setText("<HTML><body><h2><center>To</center></h2></body></HTML>");
		btnArr.setText("<HTML><body style = 'text-align:center;'>To<br>도착지</body></HTML>");
		btnArr.setSize(150, 100);
		btnArr.setLocation(25, 40);
		btnArr.setFont(fontNanumGothic20);
		btnArr.setBackground(Color.white);
		//btnArr.setBorderPainted(false);
		btnArr.addActionListener(this);
		jpArr.add(lblArr);
		jpArr.add(btnArr);
		
		
		jpCountry.add(jpDep);
		jpCountry.add(jpSwap);
		jpCountry.add(jpArr);
		
		//날짜, 인원, 좌석 등급
		jpCalNum = new JPanel();	//날짜 패널
		jpCalNum.setBackground(Color.white);
		jpCalNum.setLayout(null);
		jpCalNum.setSize(500, 180);
		jpCalNum.setLocation(250, 250);
		
		imgDate = new ImageIcon("image/calender.png");	
		lblDate = new JLabel("여행일정", imgDate, SwingUtilities.LEFT); //라벨에 이미지 삽입, 위치 주기
		lblDate.setSize(150, 35);
		lblDate.setLocation(20, 15);
		lblDate.setFont(fontNanumGothic25);
		btnDate = new JButton("");
		btnDate.setBackground(Color.white);
		btnDate.addActionListener(this);
		btnDate.setSize(300, 60);
		btnDate.setLocation(175, 0);
		btnDate.setFont(fontNanumGothic15);
		
		
		imgPeople = new ImageIcon("image/people.png");
		lblPeople = new JLabel("탑승인원", imgPeople, SwingUtilities.LEFT);
		lblPeople.setSize(150, 35);
		lblPeople.setLocation(20, 110);
		lblPeople.setFont(fontNanumGothic25);
		btnPeople = new JButton("");
		btnPeople.setBackground(Color.white);
		btnPeople.addActionListener(this);
		btnPeople.setSize(300, 60);
		btnPeople.setLocation(175, 100);
		btnPeople.setFont(fontNanumGothic15);
		
		
		imgSeat = new ImageIcon("image/seat.png");
		lblSeat = new JLabel("좌석", imgSeat, SwingUtilities.LEFT);
		lblSeat.setFont(fontNanumGothic18);
		
		
		jpCalNum.add(lblDate);
		jpCalNum.add(btnDate);
		jpCalNum.add(lblPeople);
		jpCalNum.add(btnPeople);
//		jpCENTER.add(lblSeat);
//		jpCENTER.add(cbSeat);
		
		//다시선택 버튼
		btnReset = new JButton("다시선택", imgAirport); //버튼에 이미지, 라벨 삽입
		btnReset.setFont(fontNanumGothic25);
//					btnSearch.setBackground(new Color(135,206,250)); //버튼 배경색 RGB코드값으로 주기
//					btnSearch.setBackground(new Color(153, 204, 255)); //버튼 배경색 RGB코드값으로 주기
		btnReset.setBackground(new Color(10,90,150)); //버튼 배경색 RGB코드값으로 주기
		btnReset.setSize(220, 50);
		btnReset.setLocation(270, 480);
		btnReset.setForeground(Color.white); //버튼 폰트 색 변경
		btnReset.setPreferredSize(new Dimension(300, 35));
		btnReset.setBorderPainted(false); //버튼 윤곽선 제거
		btnReset.addActionListener(this);
		
		//조회 버튼
		imgAirport = new ImageIcon("image/airport.png");
		btnSearch = new JButton("조회", imgAirport); //버튼에 이미지, 라벨 삽입
		btnSearch.setFont(fontNanumGothic25);
//					btnSearch.setBackground(new Color(135,206,250)); //버튼 배경색 RGB코드값으로 주기
//					btnSearch.setBackground(new Color(153, 204, 255)); //버튼 배경색 RGB코드값으로 주기
		btnSearch.setBackground(new Color(10,90,150)); //버튼 배경색 RGB코드값으로 주기
		btnSearch.setSize(220, 50);
		btnSearch.setLocation(505, 480);
		btnSearch.setForeground(Color.white); //버튼 폰트 색 변경
		btnSearch.setPreferredSize(new Dimension(300, 35));
		btnSearch.setBorderPainted(false); //버튼 윤곽선 제거
		btnSearch.addActionListener(this);
		
		
		jpBook.add(jpCountry);
		jpBook.add(jpCalNum);
		jpBook.add(btnSearch);
		jpBook.add(btnReset);
		add(jpBook);
	}	
	
	public static void main(String[] args) {
		new BookForm("test1");
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnMainMenu) {
			mainMenuForm = new MainMenuForm(); //메인버튼 누르면 메인화면 띄우기
			mainMenuForm.setId(id);
			this.setVisible(false);
		} else if(obj == btnSearch) { //조회버튼 눌렀을 때
			
			if(goDay.isEmpty() && numTotal==0 && SelectDepCode.isEmpty()) { //값이 비어있는지 확인하기
				JOptionPane.showMessageDialog(null, "탑승지, 탑승일, 인원을 선택해주세요.", "조회", JOptionPane.OK_CANCEL_OPTION);
			} else if (goDay.isEmpty() && numTotal == 0) {
				JOptionPane.showMessageDialog(null, "탑승일과 인원을 선택해주세요", "조회", JOptionPane.OK_CANCEL_OPTION);
			} else if (goDay.isEmpty() && SelectDepCode.isEmpty()) {
				JOptionPane.showMessageDialog(null, "탑승일과 탑승지를 선택해주세요", "조회", JOptionPane.OK_CANCEL_OPTION);
			} else if (numTotal == 0 && SelectDepCode.isEmpty()) {
				JOptionPane.showMessageDialog(null, "탑승지와 인원을 선택해주세요", "조회", JOptionPane.OK_CANCEL_OPTION);
			} else if (goDay.isEmpty()) {
				JOptionPane.showMessageDialog(null, "탑승일을 선택해주세요", "조회", JOptionPane.OK_CANCEL_OPTION);
			} else if (numTotal == 0) {
				JOptionPane.showMessageDialog(null, "인원을 1명 이상 선택해주세요", "조회", JOptionPane.OK_CANCEL_OPTION);
			} else if (SelectDepCode.isEmpty()) {
				JOptionPane.showMessageDialog(null, "탑승지를 선택해주세요", "조회", JOptionPane.OK_CANCEL_OPTION);
			} else { //조회해보기
				try {
					tkRTGoForm = new TicketingRoundTripGoingForm(this);
					this.setVisible(false);
				} catch (StringIndexOutOfBoundsException e2) { //만약 데이터베이스에 항공편현황이 없을 경우 띄우기
					JOptionPane.showMessageDialog(null, "여행 일정이 없습니다. 항공편 현황을 확인해주세요.", "예매하기", JOptionPane.OK_CANCEL_OPTION);
				}
			}
			
		} else if (obj == btnReset) { //다시선택
			
			
			//텍스트 설정, 값 설정
			btnDep.setText("<HTML><body style ='text-align:center;'>From<br>출발지</body></HTML>");
			btnArr.setText("<HTML><body style = 'text-align:center;'>To<br>도착지</body></HTML>");
			btnDate.setText("");
			btnPeople.setText("");
			
			goDay = "";
			comeDay = "";
			roundTrip = 0;
			
			numAdult = 0;
			numInfant = 0;
			numChild = 0;
			numTotal = 0;
			
			SelectDep = "";
			SelectDepCode = "";
			
			SelectArr = "";
			SelectArrCode = "";
			
			//창 띄우기
		} else if(obj == btnDate) { 
			new customer.book.SelectDate(this);
		} else if(obj == btnPeople) {
			new customer.book.SelectPassenger(this);
		} else if (obj == btnDep) {		
			new customer.book.SelectDep(this);
		} else if (obj == btnArr) {		
			new customer.book.SelectArrived(this);
		} else if (obj == btnSwap) { //교환버튼 눌렀을 때	
			if (SelectDep.isEmpty() && SelectArr.isEmpty()) { //선택확인
				JOptionPane.showMessageDialog(null, "출발지와 도착지를 선택해주세요.", "탑승지 교환", JOptionPane.OK_CANCEL_OPTION);
			} else if (SelectDep.isEmpty()) {
				JOptionPane.showMessageDialog(null, "출발지를 선택해주세요.", "탑승지 교환", JOptionPane.OK_CANCEL_OPTION);
			} else if (SelectArr.isEmpty()) {
				JOptionPane.showMessageDialog(null, "도착지를 선택해주세요.", "탑승지 교환", JOptionPane.OK_CANCEL_OPTION);
			} else { //모두 선택했으면 교환하기
				swapText = SelectDep;
				SelectDep = SelectArr;
				SelectArr = swapText;
				
				swapCode = SelectDepCode;
				SelectDepCode = SelectArrCode;
				SelectArrCode = swapCode;
				
				btnDep.setText(SelectDep);
				btnArr.setText(SelectArr);
				
			}	
		}
		
		
		
	}
}






