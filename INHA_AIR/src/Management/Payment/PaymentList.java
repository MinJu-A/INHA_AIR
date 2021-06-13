package Management.Payment;

//미완, 건들지말것
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import DataBase.databaseClass;
import Management.AirPort.AirportList;
import Management.Airplane.AirplaneList;
import Management.Airway.AirwayList;
import Management.Main.MainForm;
import Management.Payment.PaymentList;
import Management.User.UserList;
import be.sign.SignIn;

public class PaymentList extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "Management";
	private int width = 1120, height = 770;
	
	//메뉴
		private JPanel jpTOP, jpMenu;
		private JButton btnLogo, btnUser, btnAirway, btnAirport, btnPay, btnLogout, btnser, btnAirplane;
		private SignIn signIn;
		private UserList userlist, userList;
		private PaymentList paymentlist;
		private AirwayList airwaylist;
		private AirportList airlinelist;
		private AirplaneList airplanelist;
		private MainForm mainform;
		private int result;
		
		
	// 폰트
		Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
		Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
		Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
		Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
		Font fontNanumGothic13 = new Font("NanumGothic", Font.PLAIN, 13);	// 나눔고딕 25
		Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
		Font fontArial36 = new Font("Arial", Font.BOLD | Font.ITALIC, 36);
		Font fontDialog = new Font("Dialog", Font.BOLD, 35);
		Font fontMonospaced = new Font("monospaced", Font.BOLD, 35);
		Font fontSansSerif = new Font("SansSerif", Font.BOLD, 35);
		
	
	//색상
		Color colorLogo = new Color(24, 62, 111);	
		Color colorBtn = new Color(10,90,150);
		Color crPaleblue = new Color(230,240,250);
		Color crGrey = new Color(240,240,240);
		
	
	// 리스트
	private JPanel jpUser;
	
	// 버튼
	private JPanel jpbutton;
	
	
	//테이블
	private JPanel jpTable;
	private DefaultTableModel model;
	private CreateTable jtPay;
	private String[][] datas = new String[0][0];
	private String[] uTableTitle = {"카드", "무통장", "총매출"};
	private DefaultTableCellRenderer Center; //테이블 정렬
	private JTableHeader jtUHeader;
	private JScrollPane sp;
	
	//수정
	private JPanel jpAll, jpBtn, jpEdit, jpNew, jpSer;
	private JButton btnOk, btnBye, btnDel, btnMod;
	private JLabel lblNew, lblId, lblPw, lblName, lblSex, lblPN, lblBir, lblTel, lblEmail, lblserach;
	private JTextField tfId, tfPw, tfName, tfSex, tfPN, tfBir, tfTel, tfEmail, tfSer;
	private AirportList airportlist;
	

	
	

	
	public PaymentList() {
		
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//DB연결
		String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
		String dbID="inhaair";
		String dbPassword="1234";
		databaseClass.connect(dbURL, dbID, dbPassword);
		
		// 레이아웃 설정
		setLayout(null);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		
		// 상단 메뉴
		setUpMenu();
		
		// 제일 큰 패널
		jpUser = new JPanel(null);
		jpUser.setSize(1100, 635);
		jpUser.setLocation(3, 90);
		jpUser.setBackground(Color.WHITE);
		
		add(jpUser);
		
		// 회원 리스트
		setUserlist();
		
		//수정창
		setButtonPannel();
		
		

		
		setVisible(true);
		
	}


	


	private void setButtonPannel() {
		jpEdit = new JPanel(new BorderLayout());
		jpEdit.setSize(200, 40);
		jpEdit.setLocation(125,45);
		jpEdit.setBackground(Color.WHITE);
		//월별 콤보박스 삽입 예정
		btnser = new JButton("매출 조회");
		btnser.setPreferredSize(new Dimension(150,40));
		btnser.setBackground(colorBtn);
		btnser.setForeground(Color.white);
		btnser.setFont(fontNanumGothic25);
		btnser.addActionListener(this);
		
		jpEdit.add(btnser);
		jpUser.add(jpEdit);
	}





	private void setUserlist() {
		jpTable = new JPanel(new BorderLayout());
		jpTable.setSize(600, 520);
		jpTable.setLocation(465,45);
		jpTable.setBackground(Color.WHITE);
		
		model = new DefaultTableModel(datas, uTableTitle);
		
		jtPay = new CreateTable(model);
		jtPay.setFont(fontNanumGothic13);
		jtPay.setRowHeight(20);
		jtPay.setFillsViewportHeight(true); //스크롤팬에 꽉 차서 보이게 하기
		jtPay.setBackground(Color.WHITE);
		
		Center = new DefaultTableCellRenderer(); //테이블 정렬
		Center.setHorizontalAlignment(JLabel.CENTER); //가운데정렬
		jtPay.getColumn("카드").setCellRenderer(Center);
		jtPay.getColumn("무통장").setCellRenderer(Center);
		jtPay.getColumn("총매출").setCellRenderer(Center);
		
		jtUHeader = jtPay.getTableHeader();
		jtUHeader.setReorderingAllowed(false); //컬럼 이동 금지
		jtUHeader.setResizingAllowed(false); //컬럼 크기 변경 금지
		jtUHeader.setBackground(colorBtn);
		jtUHeader.setFont(fontNanumGothic15);
		jtUHeader.setForeground(Color.white);
		jtUHeader.setPreferredSize(new Dimension(0,25));
		
		sp = new JScrollPane(jtPay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		jpTable.add(sp);
		jpUser.add(jpTable);
	}


	// 상단 메뉴바
	private void setUpMenu() {
		jpTOP = new JPanel();		// 상단 판넬 생성
		jpTOP.setLayout(null);		// 상단 판넬 배치관리자 설정
		jpTOP.setSize(1120, 100);	// 상단 판넬 사이즈 설정
		jpTOP.setLocation(0, 0);	// 상단 판넬 위치 설정
		jpTOP.setBackground(Color.WHITE);	// 상단 패널 배경색 설정
		
		//로고
		btnLogo = new JButton("INHA AIR");
		btnLogo.setFont(fontArial36);
		btnLogo.setSize(200, 70);
		btnLogo.setLocation(10, 5);
		btnLogo.addActionListener(this);
		btnLogo.setBackground(Color.WHITE);
		btnLogo.setForeground(new Color(24, 62, 111));	// 글자색 변경
		btnLogo.setBorderPainted(false);
		btnLogo.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLogo.setVerticalAlignment(SwingConstants.BOTTOM);
		
		
		//메뉴 선택 판넬
		jpMenu = new JPanel();
		jpMenu.setLayout(null);
		jpMenu.setSize(1000, 60);
		jpMenu.setLocation(70, 60);
		
		//메뉴
		btnUser = new JButton("회원관리");			
		btnUser.setSize(150, 40);	
		btnUser.setLocation(200, 0);				
		btnUser.addActionListener(this);
		btnUser.setFont(fontNanumGothic18);	
		btnUser.setBorderPainted(false);		
		btnUser.setContentAreaFilled(false);
		
		btnAirway = new JButton("항공편");
		btnAirway.setSize(150, 40);
		btnAirway.setLocation(350, 0);
		btnAirway.addActionListener(this);
		btnAirway.setFont(fontNanumGothic18);
		btnAirway.setBorderPainted(false);
		btnAirway.setContentAreaFilled(false);
		
		btnAirport = new JButton("공항");
		btnAirport.setSize(150, 40);
		btnAirport.setLocation(500, 0);
		btnAirport.addActionListener(this);
		btnAirport.setFont(fontNanumGothic18);
		btnAirport.setBorderPainted(false);
		btnAirport.setContentAreaFilled(false);
		
		btnAirplane = new JButton("비행기");
		btnAirplane.setSize(150, 40);
		btnAirplane.setLocation(650, 0);
		btnAirplane.addActionListener(this);
		btnAirplane.setFont(fontNanumGothic18);
		btnAirplane.setBorderPainted(false);
		btnAirplane.setContentAreaFilled(false);
		
		btnPay = new JButton("매출");
		btnPay.setSize(150, 40);
		btnPay.setLocation(800, 0);
		btnPay.addActionListener(this);
		btnPay.setFont(fontNanumGothic18);
		btnPay.setBorderPainted(false);
		btnPay.setContentAreaFilled(false);
		
		
		// 로그아웃 버튼
		btnLogout = new JButton("<HTML><U>LOGOUT</U></HTML>");
		btnLogout.setSize(80, 50);
		btnLogout.setLocation(970, 5);
		btnLogout.addActionListener(this);
		btnLogout.setFont(fontArial);
		btnLogout.setBorderPainted(false);
		btnLogout.setContentAreaFilled(false);
		
		
		jpTOP.add(btnLogo);
		jpTOP.add(jpMenu);
		
		jpMenu.add(btnUser);
		jpMenu.add(btnAirway);
		jpMenu.add(btnAirport);
		jpMenu.add(btnAirplane);
		jpMenu.add(btnPay);
		
		jpTOP.add(btnLogout);
		add(jpTOP);
	}


	public static void main(String[] args) {
		new PaymentList();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
Object obj = e.getSource();
		
	if(obj == btnLogo) {
		result = JOptionPane.showConfirmDialog(this, "메인으로 돌아가시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "메인으로 돌아갑니다.");
			dispose();
			mainform = new MainForm();
		}else {
			JOptionPane.showMessageDialog(this, "메인으로 돌아가지 않습니다.");
		}
			
		} else if(obj == btnLogout){
			 result = JOptionPane.showConfirmDialog(this, "정말 로그아웃 하시겠습니까?", "로그아웃",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION ) {
				JOptionPane.showMessageDialog(null, "시스템을 종료합니다");
				dispose();
				signIn = new SignIn();
			} else {
				JOptionPane.showMessageDialog(null, "로그아웃을 취소합니다.");
			}
		} else if(obj == btnUser) {
			dispose();
			userList = new UserList();
		} else if(obj == btnAirway) {
			dispose();
			airwaylist = new AirwayList();
		} else if(obj == btnAirport) {
			dispose();
			airportlist = new AirportList();
		} else if(obj == btnPay) {
			dispose();
			paymentlist = new PaymentList();
		} else if(obj == btnBye) {
			 result = JOptionPane.showConfirmDialog(this, "입력을 취소하시겠습니까?", "입력 취소",JOptionPane.YES_NO_OPTION);
			 if(result == JOptionPane.YES_OPTION ) {
					JOptionPane.showMessageDialog(null, "입력이 취소되었습니다");
					tfId.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "계속 입력해주세요");
				}
		} else if(obj == btnAirplane) {
			dispose();
			airplanelist = new AirplaneList();
		}
	}
	// jtable 생성
	class CreateTable extends JTable{
		public CreateTable(DefaultTableModel model) {
			super(model);
		}
		
		@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			
			JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);

			
			if(!isRowSelected(row)) {
				
				// 짝수 행 홀수 행 배경색 다르게 지정
				if(row % 2 == 0) {
					component.setBackground(Color.WHITE);
				} else {
					component.setBackground(crPaleblue);
				}
			}

			return component;
		}

		// 더블클릭 수정 불가
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}
	}
	
