package be.Reservation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.Inquiry.NonMemberInquiryDetail;
import be.sign.SignIn;

public class ReservationForNonmember extends JFrame implements ActionListener {

	// Title 및 사이즈 설정
	private String title = "비회원 예약 조회";
	private int width = 360, height = 450;
	
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);		// 나눔고딕 9
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
	Font fontNanumGothic13 = new Font("NanumGothic", Font.BOLD, 13);	// 나눔고딕 12
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	
	private JPanel jpTitle;
	private Container lblTitle;
	private JButton btnSearch;
	private JPanel jpInput;
	private JLabel lblNum;
	private JTextField tfNum;
	private Component lblLastName;
	private Component tfLastName;
	private Component lblFirstName;
	private JTextField tfFirstName;
	private JLabel lblExplanation;
	private JButton btnLogin;

	// 액션
	private SignIn singIn;
	private NonMemberInquiryDetail nonMemberInquiryDetail;
	
	public ReservationForNonmember() {
		//this.mainForm = mainForm;
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		Container c = getContentPane();
		c.setBackground(Color.white);
		setLayout(null);
		
		setReservation();
		
		setVisible(true);
	}

	
	
	
	private void setReservation() {
		//제목, 로그인 설명 패널
		jpTitle = new JPanel();
		jpTitle.setLayout(null);
		jpTitle.setSize(400, 80);
		jpTitle.setLocation(20, 5);
		jpTitle.setBackground(Color.white);
		lblTitle = new JLabel("예약 조회");		//제목라벨
		lblTitle.setFont(fontNanumGothic20);
		lblTitle.setSize(150, 40);
		lblTitle.setLocation(0, 10);
		lblExplanation = new JLabel("로그인하시면 예약 목록을 확인하실 수 있습니다.");
		lblExplanation.setFont(fontNanumGothic12);
		lblExplanation.setSize(300, 40);
		lblExplanation.setLocation(0, 30);
		btnLogin = new JButton("<HTML><U>회원 로그인 하기 ></U></HTML>");
		btnLogin.setForeground(new Color(000, 102, 255));
		btnLogin.addActionListener(this);
		btnLogin.setBorderPainted(false); //버튼 윤곽선 제거
		btnLogin.setContentAreaFilled(false); //버튼배경 제거
		btnLogin.setFont(fontNanumGothic12);
		btnLogin.setSize(150, 40);
		btnLogin.setLocation(-23, 48);
		jpTitle.add(lblTitle);
		jpTitle.add(lblExplanation);
		jpTitle.add(btnLogin);
		
		//정보입력 패널
		jpInput = new JPanel();
		jpInput.setLayout(null);
		jpInput.setSize(320, 200);
		jpInput.setLocation(20, 100);
		jpInput.setBackground(Color.white);
		
		lblNum = new JLabel("예약번호");
		lblNum.setSize(200, 30);
		lblNum.setLocation(0, 0);
		lblNum.setFont(fontNanumGothic13);
		tfNum = new JTextField();
//		tfNum = new JTextField("예) A1B2C3 또는 180-항공권 번호");
		tfNum.setSize(300, 30);
		tfNum.setLocation(0, 30);
//		tfNum.setPreferredSize(tfNum.getPreferredSize());
//		tfNum.setText("");
		jpInput.add(lblNum);
		jpInput.add(tfNum);
		
		lblLastName = new JLabel("승객 성)");
		lblLastName.setSize(200, 30);
		lblLastName.setLocation(0, 70);
		lblLastName.setFont(fontNanumGothic13);
		tfLastName = new JTextField();
		tfLastName.setSize(300, 30);
		tfLastName.setLocation(0, 100);
		jpInput.add(lblLastName);
		jpInput.add(tfLastName);
		
		lblFirstName = new JLabel("승객 이름");
		lblFirstName.setSize(200, 30);
		lblFirstName.setLocation(0, 140);
		lblFirstName.setFont(fontNanumGothic13);
		tfFirstName = new JTextField();
		tfFirstName.setSize(300, 30);
		tfFirstName.setLocation(0, 170);
		jpInput.add(lblFirstName);
		jpInput.add(tfFirstName);
		
		//조회버튼
		btnSearch = new JButton("조회");
		btnSearch.setFont(fontNanumGothic15);
		btnSearch.setForeground(Color.white);
		btnSearch.setSize(100, 40);
		btnSearch.setLocation(120, 320);
		btnSearch.setBorderPainted(false); //버튼 윤곽선 제거
		btnSearch.setBackground(new Color(10,90,150));
		btnSearch.addActionListener(this);
		
		add(jpTitle);
		add(jpInput);
		add(btnSearch);
		
	}

	
	public static void main(String[] args) {
		new ReservationForNonmember();
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnLogin) {
			singIn = new SignIn();
		} else if(obj == btnSearch) {
			nonMemberInquiryDetail = new NonMemberInquiryDetail();
		}
	}
}
