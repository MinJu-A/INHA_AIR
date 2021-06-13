

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MinuReserve extends JFrame{
	
	Font fontNanumGothic = new Font("NanumGothic", Font.BOLD, 20);

	private String [] seat = {"일등석", "비즈니스석", "이코노미석"};
	private JPanel jp1, jp2, jp3, jpdep, jpswap, jparr;
	private ImageIcon imgairport, imgdate, imgpeople, imgseat, imgswap;
	private JLabel lbdate, lbpeople, lbseat, lbdep, lbarr;
	private JButton btnsearch, btnpeople, btndate, btndep, btnarr, btnswap;

	private JComboBox<String> cbseat;
	


	public MinuReserve(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocation(900, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//출발지 도착지 
		jp1 = new JPanel();
		jpdep = new JPanel();
		jpdep.setLayout(new GridLayout(0,1));
		lbdep = new JLabel("출발", SwingUtilities.CENTER);
		btndep = new JButton();
		btndep.setText("<HTML><body><h2><center>From</center></h2></body></HTML>");
		btndep.setText("<HTML><body style ='text-align:center;'>From<br>출발지</body></HTML>");
		btndep.setBackground(Color.white);
		btndep.setBorderPainted(false);
		jpdep.add(lbdep);
		jpdep.add(btndep);
		
		
		jpswap = new JPanel();
		imgswap = new ImageIcon("image/swap.png");
		btnswap = new JButton(imgswap);
		btnswap.setBorderPainted(false);
		btnswap.setBackground(Color.white);
		//btnswap.setContentAreaFilled(false); 버튼 색 투명
		jpswap.add(btnswap);
		
		jparr = new JPanel();
		jparr.setLayout(new GridLayout(0,1));
		lbarr = new JLabel("도착", SwingUtilities.CENTER);
		btnarr = new JButton();
		btnarr.setText("<HTML><body><h2><center>To</center></h2></body></HTML>");
		btnarr.setText("<HTML><body style = 'text-align:center;'>To<br>도착지</body></HTML>");
		btnarr.setBackground(Color.white);
		btnarr.setBorderPainted(false);
		jparr.add(lbarr);
		jparr.add(btnarr);
		
		
		jp1.add(jpdep, BorderLayout.WEST);
		jp1.add(jpswap, BorderLayout.CENTER);
		jp1.add(jparr, BorderLayout.EAST);
		
		//날짜, 인원, 좌석 등급
		jp2 = new JPanel();
		jp2.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
		jp2.setLayout(new GridLayout(3, 2, 20,30));
		
		imgdate = new ImageIcon("image/calender.png");
		lbdate = new JLabel("탑승일", imgdate, SwingUtilities.LEFT); //라벨에 이미지 삽입, 위치 주기
		btndate = new JButton("");
		btndate.setBackground(Color.white);
		
		
		imgpeople = new ImageIcon("image/people.png");
		lbpeople = new JLabel("탑승인원", imgpeople, SwingUtilities.LEFT);
		btnpeople = new JButton("");
		btnpeople.setBackground(Color.white);
		
		
		imgseat = new ImageIcon("image/seat.png");
		lbseat = new JLabel("좌석", imgseat, SwingUtilities.LEFT);
		cbseat = new JComboBox<String>(seat);
		cbseat.setBackground(Color.white);
		
		jp2.add(lbdate);
		jp2.add(btndate);
		jp2.add(lbpeople);
		jp2.add(btnpeople);
		jp2.add(lbseat);
		jp2.add(cbseat);
		
		//조회 버튼
		jp3 = new JPanel();
		jp3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		imgairport = new ImageIcon("image/airport.png");
		btnsearch = new JButton("조회", imgairport); //버튼에 이미지, 라벨 삽입
		btnsearch.setBackground(new Color(135,206,250)); //버튼 배경색 RGB코드값으로 주기
		btnsearch.setForeground(Color.white); //버튼 폰트 색 변경
		btnsearch.setPreferredSize(new Dimension(300, 35));
		btnsearch.setBorderPainted(false); //버튼 윤곽선 제거
		jp3.add(btnsearch);
		
		
		add(jp1, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		add(jp3, BorderLayout.SOUTH);
		setVisible(true);
		
	}


	public static void main(String[] args) {
		new MinuReserve("INHA AIR", 400, 400);
	}

}
