package be.main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import be.Reservation.SelectDate_old;
import be.Reservation.SelectPassenger;
import be.Ticketing.TicketingRoundTripComing;
import be.Ticketing.TicketingRoundTripGoing;

public class ReservePanel extends JFrame implements ActionListener{
	
	// 예원 - 폰트 설정
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 18
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25

	// 예원 - 틀 패널 생성
	private JPanel jpSet;
	
	public JPanel getJp() {
		return jpSet;
	}

	public void setJp(int x, int y) {
		jpSet.setLocation(x, y);
	}
	//
	
	private String [] seat = {"일등석", "비즈니스석", "이코노미석"};
	private JPanel jpTOP, jpCENTER, jpBOTTOM, jpDep, jpSwap, jpArr;
	private ImageIcon imgAirport, imgDate, imgPeople, imgSeat, imgSwap;
	private JLabel lblDate, lblPeople, lblSeat, lblDep, lblArr;
	private JButton btnSearch, btnPeople, btnDate, btnDep, btnArr, btnSwap;

	private JComboBox<String> cbSeat;
	

	// 예원 - 액션
	private MainForm main;
	private SelectDate_old selectDate;				// 출발일
	private SelectPassenger selectPassenger;	// 탑승인원
	private TicketingRoundTripGoing ticketingRoundTripGoing;		// 조회 - 왕복 티켓 - 가는 행

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public ReservePanel(int width, int height, MainForm main) {
		this.main = main;
		
		// 예원 - 틀 패널 생성
		jpSet = new JPanel();
		jpSet.setLayout(new BorderLayout());

		jpSet.setSize(width, height);
		jpSet.setLocation(100, 100);
		jpSet.setBackground(Color.WHITE);
		
		jpSet.setBorder(new LineBorder(Color.WHITE, 2));	// 패널 테두리 설정
		//
		
		//출발지 도착지 
		jpTOP = new JPanel();
		jpTOP.setBackground(Color.WHITE);
		jpTOP.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		jpDep = new JPanel();
		jpDep.setBackground(Color.WHITE);
		jpDep.setLayout(new GridLayout(0,1));
		lblDep = new JLabel("출발", SwingUtilities.CENTER);
		lblDep.setFont(fontNanumGothic20);
		btnDep = new JButton();
		btnDep.setText("<HTML><body><h2><center>From</center></h2></body></HTML>");
		btnDep.setText("<HTML><body style ='text-align:center;'>From<br>출발지</body></HTML>");
		btnDep.setFont(fontNanumGothic15);
		btnDep.setBackground(Color.white);
		btnDep.setBorderPainted(false);
		btnDep.addActionListener(this);
		jpDep.add(lblDep);
		jpDep.add(btnDep);
		
		
		jpSwap = new JPanel();
		jpSwap.setBackground(Color.WHITE);
		Image img = new ImageIcon("imgs/swap.png").getImage();
		Image changeimg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imgSwap = new ImageIcon(changeimg);
		btnSwap = new JButton(imgSwap);
		btnSwap.setBorderPainted(false);
		btnSwap.setBackground(Color.white);
//		btnswap.setContentAreaFilled(false); //버튼 색 투명
		jpSwap.add(btnSwap);
		
		jpArr = new JPanel();
		jpArr.setBackground(Color.WHITE);
		jpArr.setLayout(new GridLayout(0,1));
		lblArr = new JLabel("도착", SwingUtilities.CENTER);
		lblArr.setFont(fontNanumGothic20);
		btnArr = new JButton();
		btnArr.setText("<HTML><body><h2><center>To</center></h2></body></HTML>");
		btnArr.setText("<HTML><body style = 'text-align:center;'>To<br>도착지</body></HTML>");
		btnArr.setFont(fontNanumGothic15);
		btnArr.setBackground(Color.white);
		btnArr.setBorderPainted(false);
		btnArr.addActionListener(this);
		jpArr.add(lblArr);
		jpArr.add(btnArr);
		
		
		jpTOP.add(jpDep, BorderLayout.WEST);
		jpTOP.add(jpSwap, BorderLayout.CENTER);
		jpTOP.add(jpArr, BorderLayout.EAST);
		
		//날짜, 인원, 좌석 등급
		jpCENTER = new JPanel();
		jpCENTER.setBackground(Color.WHITE);
//		jp2.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
		jpCENTER.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		jpCENTER.setLayout(new GridLayout(3, 2, 20,30));
		
		imgDate = new ImageIcon("image/calender.png");
		lblDate = new JLabel("출발일", imgDate, SwingUtilities.LEFT); //라벨에 이미지 삽입, 위치 주기
		lblDate.setFont(fontNanumGothic18);
		btnDate = new JButton("");
		btnDate.setBackground(Color.white);
		btnDate.addActionListener(this);
		
		
		imgPeople = new ImageIcon("image/people.png");
		lblPeople = new JLabel("탑승인원", imgPeople, SwingUtilities.LEFT);
		lblPeople.setFont(fontNanumGothic18);
		btnPeople = new JButton("");
		btnPeople.setBackground(Color.white);
		btnPeople.addActionListener(this);
		
		
		imgSeat = new ImageIcon("image/seat.png");
		lblSeat = new JLabel("좌석", imgSeat, SwingUtilities.LEFT);
		lblSeat.setFont(fontNanumGothic18);
		cbSeat = new JComboBox<String>(seat);
		cbSeat.setFont(fontNanumGothic15);
		cbSeat.setBackground(Color.white);
		
		jpCENTER.add(lblDate);
		jpCENTER.add(btnDate);
		jpCENTER.add(lblPeople);
		jpCENTER.add(btnPeople);
		jpCENTER.add(lblSeat);
		jpCENTER.add(cbSeat);
		
		//조회 버튼
		jpBOTTOM = new JPanel();
		jpBOTTOM.setBackground(Color.WHITE);
		jpBOTTOM.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		imgAirport = new ImageIcon("image/airport.png");
		btnSearch = new JButton("조회", imgAirport); //버튼에 이미지, 라벨 삽입
		btnSearch.setFont(fontNanumGothic20);
//		btnSearch.setBackground(new Color(135,206,250)); //버튼 배경색 RGB코드값으로 주기
//		btnSearch.setBackground(new Color(153, 204, 255)); //버튼 배경색 RGB코드값으로 주기
		btnSearch.setBackground(new Color(10,90,150)); //버튼 배경색 RGB코드값으로 주기
		btnSearch.setForeground(Color.white); //버튼 폰트 색 변경
		btnSearch.setPreferredSize(new Dimension(300, 35));
		btnSearch.setBorderPainted(false); //버튼 윤곽선 제거
		btnSearch.addActionListener(this);
		jpBOTTOM.add(btnSearch);
		
		
		jpSet.add(jpTOP, BorderLayout.NORTH);
		jpSet.add(jpCENTER, BorderLayout.CENTER);
		jpSet.add(jpBOTTOM, BorderLayout.SOUTH);
//		setVisible(true);
		
	}


	public static void main(String[] args) {
//		new ReservePanel(400, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnDate) {
			selectDate = new SelectDate_old();
		} else if(obj == btnPeople) {
			selectPassenger = new SelectPassenger();
		} 
		else if(obj == btnSearch) {
			ticketingRoundTripGoing = new TicketingRoundTripGoing();
			main.setVisible(false);
		}
	}

}
