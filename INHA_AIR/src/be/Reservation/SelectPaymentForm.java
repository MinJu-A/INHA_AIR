package be.Reservation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import be.main.MainForm;
import be.menu.MenuBar;

public class SelectPaymentForm extends JFrame implements ActionListener {
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
	Font fontNanumGothic30 = new Font("NanumGothic", Font.BOLD, 30);	// 나눔고딕 25
	
	// 결제 부분
	private JPanel jpPayment, jpLbl, jpBtn;
	private JLabel lblPyment;
	private ButtonGroup bgPayment;		// 결제수단 버튼 그룹
	private JButton btnCash, btnCard;	// 결제수단 - 무통장입금, 카드결제
	private JButton btnOK;
	
	public SelectPaymentForm() {
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


	// 결제수단 선택 화면
	private void setPayment() {
		jpPayment = new JPanel(null);
		jpPayment.setSize(1100, 635);
		jpPayment.setLocation(3, 90);
		jpPayment.setBackground(Color.WHITE);
//		jpPayment.setBackground(Color.yellow);
		
		// 라벨 부분
		jpLbl = new JPanel(null);
		jpLbl.setSize(1000, 500);
		jpLbl.setLocation(50, 10);
//		jpLbl.setBorder(new LineBorder(new Color(10,90,150), 1));
		jpLbl.setBorder(new EtchedBorder(EtchedBorder.RAISED));		// 테두리 설정
		jpLbl.setBackground(Color.WHITE);
		
		lblPyment = new JLabel("결제수단 선택");
		lblPyment.setFont(fontNanumGothic30);
		lblPyment.setHorizontalAlignment(JLabel.CENTER);
		lblPyment.setSize(1000, 50);
		lblPyment.setLocation(10, 100);
		jpLbl.add(lblPyment);
		
		// 결제 수단 버튼 그룹
		setBtnGroup();
		
		// 결제하기 버튼 부분
		jpBtn = new JPanel(null);
//		jpBtn = new JPanel();
		jpBtn.setSize(1100, 200);
		jpBtn.setLocation(0, 520);
		jpBtn.setBackground(Color.WHITE);

		btnOK = new JButton("결제하기");
		btnOK.setFont(fontNanumGothic20);
		btnOK.setBackground(new Color(10,90,150));
		btnOK.setForeground(Color.WHITE);
		btnOK.setBorderPainted(false);
		btnOK.setSize(250, 40);
		btnOK.setLocation(425, 20);
		btnOK.setHorizontalAlignment(JButton.CENTER);
		jpBtn.add(btnOK);
		
		jpPayment.add(jpLbl);
		jpPayment.add(jpBtn);
		
		add(jpPayment);
	}

	// 결제 수단 버튼 그룹
	private void setBtnGroup() {
		
		bgPayment = new ButtonGroup();
		
		btnCash = new JButton("무통장 입금");
		btnCash.setFont(fontNanumGothic20);
		btnCash.setSize(175, 200);
		btnCash.setLocation(295, 200);
//		btnCash.setBackground(Color.WHITE);
//		btnCash.setBackground(new Color(10,90,150));
		btnCash.setBackground(new Color(150,150,150));
		btnCash.setForeground(Color.WHITE);
		btnCash.setSelected(true);
//		btnCash.setBorderPainted(false);
		
		btnCard = new JButton("카드 결제");
		btnCard.setFont(fontNanumGothic20);
		btnCard.setSize(175, 200);
		btnCard.setLocation(535, 200);
		btnCard.setBackground(Color.WHITE);
		btnCard.setForeground(new Color(10,90,150));
//		btnCard.setBorderPainted(false);
		
		bgPayment.add(btnCash);
		bgPayment.add(btnCard);
		
		jpLbl.add(btnCash);
		jpLbl.add(btnCard);
	}


	// 상단 메뉴바
	private void setUpMenu() {
		menubar = new MenuBar();
		add(menubar.getPnTOP());
	}


	public static void main(String[] args) {
		new SelectPaymentForm();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
