package customer.book;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import be.main.MainForm;
import be.menu.MenuBar;


public class SelectBaggageForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "초과수하물";
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
	
	// 초과수하물 가능 개수
	private String[] addCount = {"1", "2", "3"};
	
	// 컴포넌트
	private JPanel jpBaggage;
	private JCheckBox chName;
	private JLabel lblBaggage, lblFree, lblf10kg, lblAddHBaggage, lbla10kga, lbla10kgb;
	private JTextField tfFree;					// 무료 수하물 개수
	private JComboBox<String> cbAddBaggagea, cbAddBaggageb;		// 초과 수하물 선택
	private JButton btnOK;
	
	// 
	private ReservationDetailForm informationF;
	
	// 승객 이름
	private String name;
	
	
	public SelectBaggageForm(ReservationDetailForm informationF, String name) {
		this.informationF = informationF;
		this.name = name;
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		// 레이아웃 설정
		setLayout(null);
		
		
		// 안내 레이블
		lblBaggage = new JLabel("초과수하물 선택");
		lblBaggage.setFont(fontNanumGothic30);
//		lblHydrate.setForeground(new Color(10,90,150));
		lblBaggage.setHorizontalAlignment(JLabel.CENTER);
		lblBaggage.setSize(680, 40);
		lblBaggage.setLocation(10, 40);
		add(lblBaggage);

		// 초과수하물 선택 패널 생성
		addBaggage();
		
		
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

	// 초과 수하물 선택 패널 생성
	private void addBaggage() {
		
		jpBaggage = new JPanel(null);
		jpBaggage.setSize(645, 200);
		jpBaggage.setLocation(20, 110);
		jpBaggage.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		jpBaggage.setBackground(Color.WHITE);
		
		// 이름 체크 박스
//		String name = "승객1";
		chName = new JCheckBox(name);
		chName.setSize(550, 50);
		chName.setLocation(40, 15);
		chName.setFont(fontNanumGothic20);
		chName.setBackground(Color.WHITE);
		chName.addActionListener(this);
		
		JPanel jpSelectBaggage = new JPanel();
//		jpSelectBaggage.setLayout(new GridLayout(3, 3, 10, 10));
		jpSelectBaggage.setLayout(new GridLayout(2, 3, 10, 10));
		jpSelectBaggage.setBorder(new EmptyBorder(20, 5, 20, 5));
		jpSelectBaggage.setSize(550, 130);
		jpSelectBaggage.setLocation(40, 55);
		jpSelectBaggage.setBackground(Color.WHITE);
		
		lblFree = new JLabel("무료");
		lblFree.setFont(fontNanumGothic18);
		lblFree.setEnabled(false);
		
		lblf10kg = new JLabel("10kg x");
		lblf10kg.setFont(fontNanumGothic18);
		lblf10kg.setEnabled(false);
		
		tfFree = new JTextField("1", 20);
		tfFree.setFont(fontNanumGothic15);
		tfFree.setEnabled(false);
		tfFree.setEditable(false);	// textfield 입력 불가 설정
		
		lblAddHBaggage = new JLabel("초과수하물");
		lblAddHBaggage.setFont(fontNanumGothic18);
		lblAddHBaggage.setEnabled(false);
		
		lbla10kga = new JLabel("10kg x");
		lbla10kga.setFont(fontNanumGothic18);
		lbla10kga.setEnabled(false);
		
		cbAddBaggagea = new JComboBox<String>(addCount);
		cbAddBaggagea.setFont(fontNanumGothic15);
		cbAddBaggagea.setEnabled(false);
		
		lbla10kgb = new JLabel("10kg x");
		lbla10kgb.setFont(fontNanumGothic18);
		lbla10kgb.setEnabled(false);
		
		cbAddBaggageb = new JComboBox<String>(addCount);
		cbAddBaggageb.setFont(fontNanumGothic15);
		cbAddBaggageb.setEnabled(false);
		
		jpSelectBaggage.add(lblFree);
		jpSelectBaggage.add(lblf10kg);
		jpSelectBaggage.add(tfFree);
		jpSelectBaggage.add(lblAddHBaggage);
		jpSelectBaggage.add(lbla10kga);
		jpSelectBaggage.add(cbAddBaggagea);
//		jpSelectBaggage.add(lbla10kgb);
//		jpSelectBaggage.add(cbAddBaggageb);
		
		jpBaggage.add(chName, BorderLayout.NORTH);
		jpBaggage.add(jpSelectBaggage);
		
		add(jpBaggage);
		
	}

	public static void main(String[] args) {
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnOK) {
			
			if(chName.isSelected()) {
				String addHydrateA = cbAddBaggagea.getSelectedItem().toString();
//				String addHydrateB = cbAddBaggageb.getSelectedItem().toString();
				
//				informationF.setAddHydrate(addHydrateA, addHydrateB);
				informationF.setAddHydrate(addHydrateA);
				
			}

			this.setVisible(false);		// 창 종료
			
		} else if(obj == chName) {
			if(chName.isSelected()) {
				// 체크박스 체크시 초과수하물 입력 가능
				lblFree.setEnabled(true);
				lblf10kg.setEnabled(true);
				tfFree.setEnabled(true);
				lblAddHBaggage.setEnabled(true);
				lbla10kga.setEnabled(true);
				cbAddBaggagea.setEnabled(true);
				lbla10kgb.setEnabled(true);
				cbAddBaggageb.setEnabled(true);
			} else {
				// 체크박스 해제시 초과수하물 입력 불가능
				lblFree.setEnabled(false);
				lblf10kg.setEnabled(false);
				tfFree.setEnabled(false);
				lblAddHBaggage.setEnabled(false);
				lbla10kga.setEnabled(false);
				cbAddBaggagea.setEnabled(false);
				lbla10kgb.setEnabled(false);
				cbAddBaggagea.setEnabled(false);
			}
		}
	}
}
