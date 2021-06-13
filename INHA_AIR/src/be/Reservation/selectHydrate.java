package be.Reservation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import be.main.MainForm;
import be.menu.MenuBar;

public class selectHydrate extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "초과수화물";
	private int width = 700, height = 480;
	
	// 폰트
		Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
		Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);		// 나눔고딕 9
		Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
		Font fontNanumGothic15= new Font("NanumGothic", Font.BOLD, 15);		// 나눔고딕 15
		Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
		Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
		Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
		Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
		Font fontNanumGothic30 = new Font("NanumGothic", Font.BOLD, 30);	// 나눔고딕 25
		Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	
	// 초과수화물 가능 개수
	private String[] addCount = {"1", "2", "3"};
	
	// 컴포넌트
	private JPanel jpHydrate;
	private JCheckBox chName;
	private JLabel lblHydrate, lblFree, lblAddHydrate;
	private JTextField tfFree;					// 무료 수화물 개수
	private JComboBox<String> cbAddHydrate;		// 초과 수화물 선택
	private JButton btnOK;
	
	
	public selectHydrate() {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 레이아웃 설정
		setLayout(null);
		
		
		// 안내 레이블
		lblHydrate = new JLabel("초과수화물 선택");
		lblHydrate.setFont(fontNanumGothic30);
//		lblHydrate.setForeground(new Color(10,90,150));
		lblHydrate.setHorizontalAlignment(JLabel.CENTER);
		lblHydrate.setSize(680, 40);
		lblHydrate.setLocation(10, 40);
		add(lblHydrate);

		// 초과수화물 선택 패널 생성
		addHydrate();
		
		
		// 확인 버튼
		btnOK = new JButton("확인");
		btnOK.setFont(fontNanumGothic20);
		btnOK.setSize(400, 50);
		btnOK.setLocation(145, 350);
		btnOK.setBackground(new Color(10,90,150));
		btnOK.setForeground(Color.WHITE);
		btnOK.setBorderPainted(false);
		btnOK.addActionListener(this);
		add(btnOK);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		
		setVisible(true);
	}

	// 초과 소화물 선택 패널 생성
	private void addHydrate() {
		
//		jpHydrate = new JPanel(new BorderLayout(50, 50));
//		jpHydrate = new JPanel(new BorderLayout());
//		jpHydrate.setBorder(new EmptyBorder(30, 30, 30, 30));
//		jpHydrate.setSize(645, 350);
		jpHydrate = new JPanel(null);
		jpHydrate.setSize(645, 200);
		jpHydrate.setLocation(20, 110);
		jpHydrate.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		jpHydrate.setBackground(Color.WHITE);
		
		// 이름 체크 박스
		String name = "승객1";
		chName = new JCheckBox(name);
		chName.setSize(550, 50);
		chName.setLocation(40, 15);
		chName.setFont(fontNanumGothic20);
		chName.setBackground(Color.WHITE);
		
		JPanel jpSelectHydrate = new JPanel();
		jpSelectHydrate.setLayout(new GridLayout(2, 3, 20, 20));
//		jpSelectHydrate.setBorder(new EmptyBorder(50, 70, 50, 20));
		jpSelectHydrate.setBorder(new EmptyBorder(20, 5, 20, 5));
		jpSelectHydrate.setSize(550, 130);
//		jpSelectHydrate.setSize(625, 150);
//		jpSelectHydrate.setSize(550, 150);
		jpSelectHydrate.setLocation(40, 55);
		jpSelectHydrate.setBackground(Color.WHITE);
		
		lblFree = new JLabel("무료");
		lblFree.setFont(fontNanumGothic18);
		
		JLabel lblf23kg = new JLabel("23kg x");
		lblf23kg.setFont(fontNanumGothic18);
		lblf23kg.setHorizontalTextPosition(JLabel.RIGHT);
		
		tfFree = new JTextField("1", 20);
		tfFree.setFont(fontNanumGothic15);
		
		lblAddHydrate = new JLabel("초과수화물");
		lblAddHydrate.setFont(fontNanumGothic18);
		
		JLabel lbla23kg = new JLabel("23kg x");
		lbla23kg.setFont(fontNanumGothic18);
		lbla23kg.setHorizontalTextPosition(JLabel.RIGHT);
		
		cbAddHydrate = new JComboBox<String>(addCount);
		cbAddHydrate.setFont(fontNanumGothic15);
		
		jpSelectHydrate.add(lblFree);
		jpSelectHydrate.add(lblf23kg);
		jpSelectHydrate.add(tfFree);
		jpSelectHydrate.add(lblAddHydrate);
		jpSelectHydrate.add(lbla23kg);
		jpSelectHydrate.add(cbAddHydrate);
		
		jpHydrate.add(chName, BorderLayout.NORTH);
		jpHydrate.add(jpSelectHydrate);
		
		add(jpHydrate);
		
	}

	public static void main(String[] args) {
		new selectHydrate();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
