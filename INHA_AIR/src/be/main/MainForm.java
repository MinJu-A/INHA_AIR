package be.main;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.Reservation.ReservationForNonmember;
import be.Reservation.SelectDate_old;
import be.Ticketing.TicketingRoundTripGoing;
import be.menu.MenuBar;
import be.menu.MenuPanel;

// 예원 - 메인 화면 작성
public class MainForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);	// 나눔고딕 9
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
	Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	
	// 센터 관련
	private JPanel jpCENTER;
	private JLabel lblMain;
	
//	private ImageIcon icon = new ImageIcon("imgs/main/fly.jpg");
	private ImageIcon icon = new ImageIcon("imgs/main/plane.jpg");
	private Image img = icon.getImage();
	private Image changeimg = img.getScaledInstance(1000, 430, Image.SCALE_SMOOTH);
	private ImageIcon iconMain = new ImageIcon(changeimg);
	
	// 하위 메뉴 관련
	private JPanel jpBOTTOM; 
	private JButton btnFindTrips, btnCheckIn, btnFilghtStatus;
	private ImageIcon imgFindTrips, imgCheckIn, imgFilghtStatus;
	
	// 로그인 프레임
//	private LoginForm login;
	
	// 상단 메뉴
	private be.menu.MenuBar menubar;
	
	// 예약창
	private ReservePanel res;
	
	// 액션
	private TicketingRoundTripGoing ticketingRoundTripGoing;
	private ReservationForNonmember reservationForNonmember;
	
	public MainForm() {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(null);	// 배치관리자 제거
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		
		// 예약창
		res = new ReservePanel(400, 475, this);
		res.setJp(650, 150);
		add(res.getJp());
		
		// 상위 메뉴 판넬
//		setUpMenu();
		menubar = new MenuBar();
		add(menubar.getPnTOP());
		
		// 센터 이미지 판넬
		setCenter();
		
		// 하위 메뉴 판넬
		setDownMenu();
		
		setVisible(true);
	}


	private void setCenter() {
		// 중간 판넬
		jpCENTER = new JPanel();
		jpCENTER.setLayout(null);
		jpCENTER.setSize(1000, 430);
		jpCENTER.setLocation(70, 100);
		jpCENTER.setBackground(Color.WHITE);
//		jpCENTER.setBackground(new Color(135,206,250));
		
		lblMain = new JLabel(iconMain);
		lblMain.setSize(1000, 430);
		lblMain.setLocation(0, 0);
		
		jpCENTER.add(lblMain);
		
		add(jpCENTER);
	}


	private void setDownMenu() {
		// 하위 판넬
		jpBOTTOM = new JPanel();
//		pnBOTTOM.setLayout(null);
	//	jpBOTTOM.setLayout(new FlowLayout(FlowLayout.LEFT));
		jpBOTTOM.setSize(1120, 100);
		jpBOTTOM.setLocation(0, 550);
		jpBOTTOM.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 10));
//		pnBOTTOM.setBorder(BorderFactory.createEmptyBorder(25, 50, 0, 0));
		jpBOTTOM.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		
//		pnBOTTOM.setBackground(Color.black);
		
		// 이미지 아이콘
		imgFindTrips = new ImageIcon("imgs/findTrips.png");
		imgCheckIn = new ImageIcon("imgs/checkIn.png");
		imgFilghtStatus = new ImageIcon("imgs/filghtStatus.png");
		
		// 이미지 버튼
//		btnFindTrips = new JButton("예약조회", imgFindTrips);
		btnFindTrips = new JButton("예약조회");
		btnFindTrips.setFont(fontNanumGothic20);
		btnFindTrips.setBorderPainted(false);
		btnFindTrips.setContentAreaFilled(false);
		btnFindTrips.addActionListener(this);
		
//		btnCheckIn = new JButton("체크인", imgCheckIn);
		btnCheckIn = new JButton("체크인");
		btnCheckIn.setFont(fontNanumGothic20);
		btnCheckIn.setBorderPainted(false);
		btnCheckIn.setContentAreaFilled(false);
		btnCheckIn.addActionListener(this);
		
//		btnFilghtStatus = new JButton("항공편 현황", imgFilghtStatus);
		btnFilghtStatus = new JButton("항공편 현황");
		btnFilghtStatus.setFont(fontNanumGothic20);
		btnFilghtStatus.setBorderPainted(false);
		btnFilghtStatus.setContentAreaFilled(false);
		btnFilghtStatus.addActionListener(this);
				
		jpBOTTOM.add(btnFindTrips);
		jpBOTTOM.add(btnCheckIn);
		jpBOTTOM.add(btnFilghtStatus);
				
		add(jpBOTTOM);
		
	}

	public static void main(String[] args) {
		new MainForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnFindTrips) {
			reservationForNonmember = new ReservationForNonmember();
		}
			
	}
}