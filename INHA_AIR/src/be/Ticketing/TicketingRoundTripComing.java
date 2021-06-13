package be.Ticketing;
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


public class TicketingRoundTripComing extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 폰트 사용 위함
//	private MainForm main;
	
	// 상단 메뉴바
	private be.menu.MenuBar menubar;
	private JPanel jpSelectedInfo;
	//--가상의 고객이 선택한 정보
		private String selctedDepartP = "GMP"; //고객이 선택한 정보 : 
		private String selctedArriveP = "PUS"; // 출발지 GMP 도착지 PUS (P : place)
		private String selctedDepartD = "2021.05.15(토)"; //출발 날짜 : 21.0515- 
		private String selctedArriveD = "2021.06.01(화)";//도착 날짜 : 0601(화)    (D : date)
		private String Adult = "성인";//성인
		private int numP = 1;//1명
		//--
		
		private JLabel lblDepartP; //고객이 선택한 출발지 정보
		private JLabel lblArriveP; //         도착지
		private JLabel lblDepArrD;	//고객이 선택한 출발일 + 도착일
		private JLabel lblArrow; //왕복 화살표
		private JLabel lblAdult; //성인임을 나타냄 유아인 경우 children
		private JLabel lblNumP; //인원 수 
		private JLabel lblPassenger;// 탑승 인원 정보 (성인인지 유아인지의 정보 + 인원수)
		
		private JPanel jpFlight1; // 시간 선택시 비행기1
		private JPanel jpFlight2; // 비행기 2
		private JPanel jpFlight3; // 비행기 3
		
		private JButton btnFir1; //비행기 1의 일등석. 퍼스트석
		private JButton btnBns1;//비행기 1의 비즈니스석
		private JButton btnEco1;// 비행기 1의 이코노미석
		
		private JButton btnEco2; //비행기 2의 이코노미
		private JButton btnBns2; //       비즈니스
		private JButton btnFir2;// 		퍼스트석
		
		private JButton btnEco3;//비행기 3의 이코노미
		private JButton btnBns3; //      비즈니스
		private JButton btnFir3; //      퍼스트
		
		private Color crInfo; //고객이 선택한 정보를 나타내는 바의 색 
		private Color crClass;//좌석 등급 버튼 색
		private JPanel jpTotalPay; // 예상 결제금액 + 버튼 나타내는 패널
		private Component lblTotalPay; //"예상결제금액"문구 나타내는 라벨
		private JLabel lblPayment;// 실제 예상 결제금액이 뜨는 곳
		private JButton btnMember; //회원으로 진행 버튼
		private JButton btnNonMember; //비회원으로 진행 버튼
//		private ImageIcon imgArrow;
//		private ImageIcon imgAeroPlane;
		private Color crMember; //회원 버튼 색
		private Color crNonMember;// 비회원 버튼 색
		
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
		
	public TicketingRoundTripComing() {
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
		
		crInfo = new Color(240,240,240);//고객이 선택한 정보를 나타내는 바의 색 
		crClass = new Color(213, 230, 250);//좌석 등급 선택 버튼의 색
		
		crMember = new Color(10,90,150); //회원 버튼 색깔
		crNonMember = new Color(150,150,150); //비회원 버튼 색깔
		
		jpSelectedInfo = new JPanel(); //고객이 선택한 정보를 표시하는 바
		jpSelectedInfo.setLayout(null);
		jpSelectedInfo.setSize(1000,60);
		jpSelectedInfo.setLocation(70,100);
		jpSelectedInfo.setBackground(crInfo);
		
		lblDepartP = new JLabel(selctedArriveP);//고객이 선택한 도착지 정보를 상단의 바에 띄움 . 왕복 돌아오는 편이기 때문에 도착지를 먼저 적음
		lblDepartP.setFont(fontNanumGothic25);
		lblDepartP.setBounds(50, -20, 200, 100);
		
		lblArriveP = new JLabel(selctedDepartP);//고객이 선택한 출발지 정보를 상단의 바에 띄움
		lblArriveP.setFont(fontNanumGothic25);
		lblArriveP.setBounds(190, -20, 200, 100);
//		
//		imgAeroPlane = new ImageIcon("images/aeroplane.png");
//		imgArrow = new ImageIcon("images/arrow.png");
		
		lblArrow = new JLabel("→"); //문자로 넣을지 그림으로 넣을지 ..
		lblArrow.setFont(fontNanumGothic30);
		lblArrow.setBounds(135, -20, 200, 100);

		lblDepArrD = new JLabel(selctedDepartD + "  ~  " + selctedArriveD ); //왕복 출발일과 도착일 함께 표시
		lblDepArrD.setFont(fontNanumGothic18Plain);
		lblDepArrD.setBounds(295, -20, 300, 100);

		
		lblPassenger = new JLabel(Adult + " " + numP + "명"); //고객이 선택한 탑승자 정보
		lblPassenger.setFont(fontNanumGothic18Plain);
		lblPassenger.setBounds(640, -20, 500, 100);
		
		jpFlight1 = new JPanel(); //3개의 시간표 중 선택 1
		jpFlight1.setLayout(null);
		jpFlight1.setSize(1000,110);
		jpFlight1.setLocation(70,190);
		jpFlight1.setBackground(crInfo);
		
		jpFlight2 = new JPanel();//선택 2
		jpFlight2.setLayout(null);
		jpFlight2.setSize(1000,110);
		jpFlight2.setLocation(70,320);
		jpFlight2.setBackground(crInfo);
		
		jpFlight3 = new JPanel();// 선택 3
		jpFlight3.setLayout(null);
		jpFlight3.setSize(1000,110);
		jpFlight3.setLocation(70,450);
		jpFlight3.setBackground(crInfo);
		
		btnEco1 = new JButton();//비행기 1의 이코노미 좌석 
		btnEco1.setLayout(null);
		btnEco1.setSize(180,110);
		btnEco1.setLocation(458,0);
		btnEco1.setBackground(crClass);
		
		btnEco2 = new JButton(); //비행기 2의 이코노미 좌석
		btnEco2.setLayout(null);
		btnEco2.setSize(180,110);
		btnEco2.setLocation(458,0);
		btnEco2.setBackground(crClass);
		
		btnEco3 = new JButton(); //비행기 3의 이코노미 좌석
		btnEco3.setLayout(null);
		btnEco3.setSize(180,110);
		btnEco3.setLocation(458,0);
		btnEco3.setBackground(crClass);
		
		btnBns1 = new JButton();//비행기 1의 비즈니스 좌석
		btnBns1.setLayout(null);
		btnBns1.setSize(180,110);
		btnBns1.setLocation(639,0);
		btnBns1.setBackground(crClass);
		
		btnBns2 = new JButton();// 비행기 2의 비즈니스 좌석
		btnBns2.setLayout(null);
		btnBns2.setSize(180,110);
		btnBns2.setLocation(639,0);
		btnBns2.setBackground(crClass);
		
		btnBns3 = new JButton();// 비행기 3의 비즈니스 좌석
		btnBns3.setLayout(null);
		btnBns3.setSize(180,110);
		btnBns3.setLocation(639,0);
		btnBns3.setBackground(crClass);
		
		btnFir1 = new JButton(); // 비행기1 의 퍼스트 좌석
		btnFir1.setLayout(null);
		btnFir1.setSize(180,110);
		btnFir1.setLocation(820,0);
		btnFir1.setBackground(crClass);
		
		btnFir2 = new JButton(); // 비행기 2의 퍼스트 좌석
		btnFir2.setLayout(null);
		btnFir2.setSize(180,110);
		btnFir2.setLocation(820,0);
		btnFir2.setBackground(crClass);
		
		btnFir3 = new JButton();// 비행기 3의 퍼스트 좌석
		btnFir3.setLayout(null);
		btnFir3.setSize(180,110);
		btnFir3.setLocation(820,0);
		btnFir3.setBackground(crClass);
		
		jpSelectedInfo.add(lblDepartP); // 상단의 고객이 선택한 정보를 나타내는 바에 출발지 추가
		jpSelectedInfo.add(lblArriveP);// 도착지 추가
		jpSelectedInfo.add(lblArrow); // 화살표
		jpSelectedInfo.add(lblDepArrD); //출발 ~ 도착 날짜 추가 // 왕복이기 때문에 이렇게 표시
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
		

//		lblPayment = new JLabel(""); // 진짜 예상 결제 금액 나타내는 라벨
		
		
		btnMember = new JButton("회원으로 진행"); // 회원 진행 버튼
		btnMember.setFont(fontNanumGothic20);
		btnMember.setBackground(crMember);
		btnMember.setForeground(Color.white);
		btnMember.setBounds(905, 0, 200, 100);
		
		btnNonMember = new JButton("비회원으로 진행"); // 회원 진행 버튼
		btnNonMember.setFont(fontNanumGothic20);
		btnNonMember.setBackground(crNonMember);
		btnNonMember.setForeground(Color.white);
		btnNonMember.setBounds(705, 0, 200, 100);
		
		jpFlight1.add(btnEco1);
		jpFlight2.add(btnEco2);
		jpFlight3.add(btnEco3);
		
		jpFlight1.add(btnBns1);
		jpFlight2.add(btnBns2);
		jpFlight3.add(btnBns3);
		
		jpFlight1.add(btnFir1);
		jpFlight2.add(btnFir2);
		jpFlight3.add(btnFir3);
		
		jpTotalPay.add(lblTotalPay);
//		jpTotalPay.add(lblPayment);
		jpTotalPay.add(btnMember);
		jpTotalPay.add(btnNonMember);
		
		add(jpSelectedInfo);
		add(jpFlight1);
		add(jpFlight2);
		add(jpFlight3);
		
		
		add(jpTotalPay);
		
		
		
		setVisible(true);
	}


	// 상단 메뉴바
	private void setUpMenu() {
		menubar = new MenuBar();
		add(menubar.getPnTOP());
	}


	public static void main(String[] args) {
		new TicketingRoundTripComing();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

