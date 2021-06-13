package be.Reservation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import be.main.MainForm;
import be.menu.MenuBar;

public class PaymentForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 상단 메뉴바
	private be.menu.MenuBar menubar;
	
	// 폰트
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontNanumGothic30 = new Font("NanumGothic", Font.PLAIN, 30);	// 나눔고딕 25
	
	// 결제 부분
	private JPanel jpPayment, jpLbl, jpBtn;
	private JLabel lblPayment;
	private JButton btnOK, btnCheckRes;
	
	public PaymentForm() {
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
		
		// 결제 화면
		setPayment();
		
		setVisible(true);
	}


	// 결제 화면
	private void setPayment() {
		jpPayment = new JPanel(null);
		jpPayment.setSize(1100, 635);
		jpPayment.setLocation(3, 90);
		jpPayment.setBackground(Color.WHITE);
//		jpPayment.setBackground(Color.yellow);
		
		// 라벨 부분
		jpLbl = new JPanel(new BorderLayout());
		jpLbl.setSize(1000, 400);
		jpLbl.setLocation(50, 10);
//		jpLbl.setBorder(new LineBorder(new Color(10,90,150), 1));
		jpLbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));		// 테두리 설정
		jpLbl.setBackground(Color.WHITE);
		
		lblPayment = new JLabel("결제 되었습니다.");
		lblPayment.setFont(fontNanumGothic30);
		lblPayment.setHorizontalAlignment(JLabel.CENTER);
		jpLbl.add(lblPayment);
		
		// 확인 버튼 부분
		jpBtn = new JPanel(null);
		jpBtn.setSize(1100, 200);
		jpBtn.setLocation(0, 430);
		jpBtn.setBackground(Color.WHITE);
		
		btnOK = new JButton("첫화면으로 이동");
		btnOK.setFont(fontNanumGothic20);
		btnOK.setBackground(new Color(10,90,150));
		btnOK.setForeground(Color.WHITE);
		btnOK.setBorderPainted(false);
		btnOK.setSize(250, 40);
		btnOK.setLocation(280, 20);
		btnOK.setHorizontalAlignment(JButton.CENTER);
		jpBtn.add(btnOK);
		
		btnCheckRes = new JButton("예약 확인페이지로 이동");
		btnCheckRes.setFont(fontNanumGothic20);
		btnCheckRes.setBackground(new Color(150,150,150));
		btnCheckRes.setForeground(Color.WHITE);
		btnCheckRes.setBorderPainted(false);
		btnCheckRes.setSize(250, 40);
		btnCheckRes.setLocation(570, 20);
		btnCheckRes.setHorizontalAlignment(JButton.CENTER);
		jpBtn.add(btnCheckRes);
		
		jpPayment.add(jpLbl);
		jpPayment.add(jpBtn);
		
		add(jpPayment);
	}


	// 상단 메뉴바
	private void setUpMenu() {
		menubar = new MenuBar();
		add(menubar.getPnTOP());
	}


	public static void main(String[] args) {
		new PaymentForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
