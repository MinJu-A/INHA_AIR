package Management.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import Management.Form.HintTextField;
import Management.Main.MainForm;
import Management.Payment.PaymentList;
import be.sign.SignIn;
import customer.login.LoginForm;

public class UserList extends JFrame implements ActionListener, MouseListener {
	// Title 및 사이즈 설정
	private String title = "Management";
	private int width = 1120, height = 770;
	
	//메뉴
		private JPanel jpTOP, jpMenu;
		private JButton btnLogo, btnUser, btnAirway, btnAirport, btnPay, btnLogout, btnser,btnAirplane;
		private LoginForm signIn;
		private UserList userlist, userList;
		private PaymentList paymentlist;
		private AirwayList airwaylist;
		private AirportList airportlist;
		private AirplaneList airplanelist;
		private MainForm mainform;
		private HintTextField hintTf;
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
	private ArrayList<String> nameKOR = new ArrayList<>();
	
	
	// 버튼
	private JPanel jpbutton;
	
	
	//테이블
	private JPanel jpTable;
	private DefaultTableModel model;
	private CreateTable jtUser;
	private String[][] datas = new String[0][0];
	private String[] uTableTitle = {"아이디", "패스워드","이름","영문이름", "성별", "여권번호", "생년월일","전화번호", "이메일"};
	private DefaultTableCellRenderer Center; //테이블 정렬
	private JTableHeader jtUHeader;
	private JScrollPane sp;
	
	//수정
	private JPanel jpAll, jpBtn, jpEdit, jpNew, jpSer;
	private JButton btnOk, btnBye, btnDel, btnMod;
	private JLabel lblNew, lblId, lblPw, lblName, lblSex, lblPN, lblBir, lblTel, lblEmail, lblserach, lblEName;
	private HintTextField tfId, tfPw, tfName, tfSex, tfPN, tfBir, tfTel, tfEmail, tfSer, tfEName;
	

	
	public UserList() {
		
		
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
		setUserEdit();
		
		//DBset
		setUserTable(0,"");
				

		
		setVisible(true);
		
	}
	
	//유저 조회 테이블
		private void setUserTable(int i, String codename) {
			
			//전체 사용자 조회
			String sql = "SELECT ID, password, nameKOR, nameENG, sex, passport, birth, tel, email\r\n"
					+ "FROM user\r\n"
					+ "ORDER BY nameKOR";
			
			if(i == 0) {
				sql = "SELECT ID, password, nameKOR, nameENG, sex, passport, birth, tel, email\r\n"
						+ "FROM user\r\n"
						+ "ORDER BY nameKOR";
			}
			else if(i == 1) {
				sql = "SELECT ID, password, nameKOR, nameENG, sex, passport, birth, tel, email\r\n"
						+ "FROM user\r\n"
						+ "WHERE ID = '" + codename + "' \r\n"
						+ "ORDER BY nameKOR";
			}
			
			
			//테이블 초기화
			model.setNumRows(0);
			
			//테이블 
			ResultSet rs = databaseClass.select(sql);
			try {
				while(rs.next()) {
					String ID = rs.getString("ID");
					String password = rs.getString("password");
					String nameKOR = rs.getString("nameKOR");
					String nameENG = rs.getString("nameENG");
					String sex = rs.getString("sex");
					String passport = rs.getString("passport");
					String birth = rs.getString("birth");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					
					String[] User = {ID, password, nameKOR, nameENG, sex, passport, birth, tel, email};
					model.addRow(User);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}


	private void setUserEdit() {
		jpEdit = new JPanel();
		jpEdit.setSize(400, 635);
		jpEdit.setLocation(35,45);
		jpEdit.setBackground(Color.WHITE);
		
		//검색 패널
		jpSer = new JPanel();
		jpSer.setBackground(Color.WHITE);
		jpSer.setPreferredSize(new Dimension(400, 30));
		
		//검색 라벨
		lblserach = new JLabel("Id 검색  ");
		lblserach.setFont(fontNanumGothic15);
		lblserach.setHorizontalAlignment(JLabel.CENTER);
		
		//검색 텍스트필드
		tfSer = new HintTextField("ex)japboss");
		tfSer.setPreferredSize(new Dimension(200, 25));	
				
		//검색 버튼
		btnser = new JButton("검색");
		btnser.setFont(fontNanumGothic13);
		btnser.setBackground(colorBtn);
		btnser.setForeground(Color.white);
		btnser.addActionListener(this);
				
		jpSer.add(lblserach);
		jpSer.add(tfSer);
		jpSer.add(btnser);
		
		jpEdit.add(jpSer);
		
	 	//폼 패널
	 	jpNew  = new JPanel();	
	 	jpNew.setLayout(new GridLayout(9, 2));
	 	jpNew.setBackground(Color.WHITE);
	 	jpNew.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	 	jpNew.setPreferredSize(new Dimension(400, 300));
	 	
	 	//폼 라벨
	 	lblId = new JLabel("아이디  ");
	 	lblId.setFont(fontNanumGothic15);
	 	lblId.setHorizontalAlignment(JLabel.CENTER);
	 	lblPw = new JLabel("비밀번호  ");
	 	lblPw.setFont(fontNanumGothic15);
	 	lblPw.setHorizontalAlignment(JLabel.CENTER);
	 	lblName = new JLabel("이름  ");
	 	lblName.setFont(fontNanumGothic15);
	 	lblName.setHorizontalAlignment(JLabel.CENTER);
	 	lblEName = new JLabel("영문이름  ");
	 	lblEName.setFont(fontNanumGothic15);
	 	lblEName.setHorizontalAlignment(JLabel.CENTER);
	 	lblSex = new JLabel("성별  ");
	 	lblSex.setFont(fontNanumGothic15);
	 	lblSex.setHorizontalAlignment(JLabel.CENTER);
	 	lblPN = new JLabel("여권번호  ");
	 	lblPN.setFont(fontNanumGothic15);
	 	lblPN.setHorizontalAlignment(JLabel.CENTER);
	 	lblBir = new JLabel("생년월일  ");
	 	lblBir.setFont(fontNanumGothic15);
	 	lblBir.setHorizontalAlignment(JLabel.CENTER);
	 	lblTel = new JLabel("전화번호  ");
	 	lblTel.setFont(fontNanumGothic15);
	 	lblTel.setHorizontalAlignment(JLabel.CENTER);
	 	lblEmail = new JLabel("이메일  ");
	 	lblEmail.setFont(fontNanumGothic15);
	 	lblEmail.setHorizontalAlignment(JLabel.CENTER);
	 			
	 	//폼 텍스트필드 
	 	tfId = new HintTextField("ex)japboss");
	 	tfPw = new HintTextField("ex)1234");
	 	tfName = new HintTextField("ex)김민주");
	 	tfEName = new HintTextField("ex)KIMMIMJU");
	 	tfSex = new HintTextField("ex)여");
	 	tfPN = new HintTextField("ex)jap981222");
	 	tfBir = new HintTextField("ex)19981222");
	 	tfTel = new HintTextField("ex)01019981222");
	 	tfEmail = new HintTextField("ex)japboss@naver.com");
	 	
	 	//붙이기
	 	jpNew.add(lblId);
	 	jpNew.add(tfId);
	 	jpNew.add(lblPw);
	 	jpNew.add(tfPw);
	 	jpNew.add(lblName);
	 	jpNew.add(tfName);
	 	jpNew.add(lblEName);
	 	jpNew.add(tfEName);
	 	jpNew.add(lblSex);
	 	jpNew.add(tfSex);
	 	jpNew.add(lblPN);
	 	jpNew.add(tfPN);
	 	jpNew.add(lblBir);
	 	jpNew.add(tfBir);
	 	jpNew.add(lblTel);
	 	jpNew.add(tfTel);
	 	jpNew.add(lblEmail);
	 	jpNew.add(tfEmail);
	 	
	 	jpEdit.add(jpNew);
	 	
	 	//버튼 패널
		jpBtn = new JPanel(new GridLayout(1,4,5,5));
		jpBtn.setBorder(BorderFactory.createEmptyBorder(10,10,10,20));
		jpBtn.setBackground(Color.WHITE);
			
		//등록 버튼
		btnOk = new JButton("등록");
		btnOk.setFont(fontNanumGothic18);
		btnOk.setBackground(colorBtn);
		btnOk.setForeground(Color.white);
		btnOk.setPreferredSize(new Dimension(80, 30));
		btnOk.addActionListener(this);
	 			
	 			
		//삭제 버튼
    	btnDel = new JButton("삭제");
    	btnDel.setFont(fontNanumGothic18);
    	btnDel.setBackground(Color.LIGHT_GRAY);
    	btnDel.setPreferredSize(new Dimension(80, 30));
    	btnDel.addActionListener(this);
	 			
		//확인 버튼
		btnMod = new JButton("수정");
		btnMod.setFont(fontNanumGothic18);
		btnMod.setBackground(colorBtn);
		btnMod.setForeground(Color.white);
		btnMod.setPreferredSize(new Dimension(80, 30));
		btnMod.addActionListener(this);
		
		//취소 버튼
    	btnBye = new JButton("취소");
		btnBye.setFont(fontNanumGothic18);
		btnBye.setBackground(Color.LIGHT_GRAY);
		btnBye.setPreferredSize(new Dimension(80, 30));
		btnBye.addActionListener(this);
	 			
		jpBtn.add(btnOk);
		jpBtn.add(btnDel);
		jpBtn.add(btnMod);
		jpBtn.add(btnBye);
	 			
		jpEdit.add(jpBtn);
	 	
		
		
				
		
		jpUser.add(jpEdit);

		
		
	}





	private void setUserlist() {
		jpTable = new JPanel(new BorderLayout());
		jpTable.setSize(600, 520);
		jpTable.setLocation(465,45);
		jpTable.setBackground(Color.WHITE);
		
		model = new DefaultTableModel(datas, uTableTitle);
		
		jtUser = new CreateTable(model);
		jtUser.setFont(fontNanumGothic13);
		jtUser.setRowHeight(20);
		jtUser.setFillsViewportHeight(true); //스크롤팬에 꽉 차서 보이게 하기
		jtUser.setBackground(Color.WHITE);
		jtUser.addMouseListener(this);
		
		Center = new DefaultTableCellRenderer(); //테이블 정렬
		Center.setHorizontalAlignment(JLabel.CENTER); //가운데정렬
		jtUser.getColumn("아이디").setCellRenderer(Center);
		jtUser.getColumn("패스워드").setCellRenderer(Center);
		jtUser.getColumn("이름").setCellRenderer(Center);
		jtUser.getColumn("영문이름").setCellRenderer(Center);
		jtUser.getColumn("성별").setCellRenderer(Center);
		jtUser.getColumn("여권번호").setCellRenderer(Center);
		jtUser.getColumn("생년월일").setCellRenderer(Center);
		jtUser.getColumn("전화번호").setCellRenderer(Center);
		jtUser.getColumn("이메일").setCellRenderer(Center);
		
		jtUHeader = jtUser.getTableHeader();
		jtUHeader.setReorderingAllowed(false); //컬럼 이동 금지
		jtUHeader.setResizingAllowed(false); //컬럼 크기 변경 금지
		jtUHeader.setBackground(colorBtn);
		jtUHeader.setFont(fontNanumGothic15);
		jtUHeader.setForeground(Color.white);
		jtUHeader.setPreferredSize(new Dimension(0,25));
		
		sp = new JScrollPane(jtUser, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
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
		btnLogo.setLocation(10, 25);
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
		new UserList();
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
			int result = JOptionPane.showConfirmDialog(this, "정말 로그아웃 하시겠습니까?", "로그아웃",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION ) {
				JOptionPane.showMessageDialog(null, "시스템을 종료합니다");
				dispose();
				signIn = new LoginForm();
			} else {
				JOptionPane.showMessageDialog(null, "로그아웃을 취소합니다.");
			}
		}  else if(obj == btnUser) {
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
					tfSer.setText("");
					tfId.setText("");
					tfPw.setText("");
					tfName.setText("");
					tfEName.setText("");
					tfSex.setText("");
					tfPN.setText("");
					tfBir.setText("");
					tfTel.setText("");
					tfEmail.setText("");
					
				} else {
					JOptionPane.showMessageDialog(null, "계속 입력해주세요");
				}
		} else if(obj == btnAirplane) {
			dispose();
			airplanelist = new AirplaneList();
		} else if(obj == btnOk) {
			//등록
			if(tfId.getText().equals("")||tfId.getText().equals("ex)japboss")){
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
			}else if(tfPw.getText().equals("")||tfPw.getText().equals("ex)1234")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요");
			}else if(tfName.getText().equals("")||tfName.getText().equals("ex)김민주")) {
				JOptionPane.showMessageDialog(null, "이름를 입력하세요");
			}else if(tfEName.getText().equals("")||tfEName.getText().equals("ex)KIMMINJU")) {
				JOptionPane.showMessageDialog(null, "영문이름를 입력하세요");
			}else if(tfSex.getText().equals("")||tfSex.getText().equals("ex)여")) {
				JOptionPane.showMessageDialog(null, "성별을 입력하세요");
			}else if(tfPN.getText().equals("")||tfPN.getText().equals("ex)jap-981222")) {
				JOptionPane.showMessageDialog(null, "여권번호를 입력하세요");
			}else if(tfBir.getText().equals("")||tfBir.getText().equals("ex)1998-12-22")) {
				JOptionPane.showMessageDialog(null, "생년월일을 입력하세요");
			}else if(tfTel.getText().equals("")||tfTel.getText().equals("ex)010-1998-1222")) {
				JOptionPane.showMessageDialog(null, "전화번호를 입력하세요");
			}else if(tfEmail.getText().equals("")||tfEmail.getText().equals("ex)japboss@naver.com")) {
				JOptionPane.showMessageDialog(null, "이메일를 입력하세요");
			}
			
			String ID = tfId.getText();
			String password = tfPw.getText();
			String nameKOR = tfName.getText();
			String nameENG = tfEName.getText();
			String sex = tfSex.getText();;
			String passport = tfPN.getText();
			String birth = tfBir.getText();
			String tel = tfTel.getText();
			String email = tfEmail.getText();
			
			String sql = "INSERT INTO user(ID, password, nameKOR, nameENG, sex, passport, birth, tel, email)\r\n"
					+ "VALUES('" + ID +"','" + password +"','" + nameKOR +"','" + nameENG +"','" + sex + "','" + passport + "','" + birth + "','" + tel + "', '" +email + "')";
			
			System.out.println(sql);
			
			int rs = databaseClass.insert(sql);
			if(rs ==1) {
				JOptionPane.showMessageDialog(this, "등록 되었습니다.");
				setUserTable(0, "");
			}else if(rs == 0) {
				JOptionPane.showMessageDialog(this, "등록 실패했습니다.");
			}
			tfSer.setText("");
			tfId.setText("");
			tfPw.setText("");
			tfName.setText("");
			tfEName.setText("");
			tfSex.setText("");
			tfPN.setText("");
			tfBir.setText("");
			tfTel.setText("");
			tfEmail.setText("");
			
			
			
		}else if(obj == btnser) {
			//검색
			String code = tfSer.getText();
			
			if(code.equals("")||code.equals("ex)japboss")) {
				setUserTable(0, "");
			}else {
				setUserTable(1, code);
			}
			
		}else if(obj == btnDel) {
			//삭제
			String ID = tfId.getText();
			String password = tfPw.getText();
			String nameKOR = tfName.getText();
			String nameENG = tfEName.getText();
			String sex = tfSex.getText();;
			String passport = tfPN.getText();
			String birth = tfBir.getText();
			String tel = tfTel.getText();
			String email = tfEmail.getText();
			
			String sql = "DELETE FROM user\r\n"
					+ "WHERE ID='" + ID + "' AND password = '" + password + "' AND nameKOR = '" + nameKOR +"' AND nameENG = '" + nameENG
					+ "' AND sex = '" + sex + "' AND passport = '" + passport + "' AND birth = '" + birth + "'AND tel = '" + tel + "'AND email = '" + email + "'";
			
			int rs = databaseClass.delete(sql);
			if(rs ==1) {
				JOptionPane.showMessageDialog(this, "삭제 되었습니다.");
				setUserTable(0, "");
			}else if(rs == 0) {
				JOptionPane.showMessageDialog(this, "삭제 실패했습니다.");
			}
			
			tfId.setText("");
			tfPw.setText("");
			tfName.setText("");
			tfEName.setText("");
			tfSex.setText("");
			tfPN.setText("");
			tfBir.setText("");
			tfTel.setText("");
			tfEmail.setText("");
			
		}else if(obj == btnMod) {
			//수정
			String ID = tfId.getText();
			String password = tfPw.getText();
			String nameKOR = tfName.getText();
			String nameENG = tfEName.getText();
			String sex = tfSex.getText();;
			String passport = tfPN.getText();
			String birth = tfBir.getText();
			String tel = tfTel.getText();
			String email = tfEmail.getText();
			
			String sql = "UPDATE  user\r\n"
					+ "SET ID='" + ID + "' , password = '" + password + "' , nameKOR = '" + nameKOR +"' , nameENG = '" + nameENG
					+ "' , sex = '" + sex + "' , passport = '" + passport + "' , birth = '" + birth + "', tel = '" + tel + "', email = '" + email + "'"
					+ "WHERE ID = '" + ID + "'";
			
			System.out.println(sql);
			
			int rs = databaseClass.update(sql);
			if(rs ==1) {
				JOptionPane.showMessageDialog(this, "수정 되었습니다.");
				setUserTable(0, "");
			}else if(rs == 0) {
				JOptionPane.showMessageDialog(this, "수정 실패했습니다.");
			}
			tfSer.setText("");
			tfId.setText("");
			tfPw.setText("");
			tfName.setText("");
			tfEName.setText("");
			tfSex.setText("");
			tfPN.setText("");
			tfBir.setText("");
			tfTel.setText("");
			tfEmail.setText("");
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



	@Override
	public void mouseClicked(MouseEvent e) {
		//선택한 값 텍스트필드에 표시
		int row = jtUser.getSelectedRow();	
		int col = jtUser.getSelectedColumn();
		
		tfId.setText((String)jtUser.getValueAt(row, 0));
		tfPw.setText((String)jtUser.getValueAt(row, 1));
		tfName.setText((String)jtUser.getValueAt(row, 2));
		tfEName.setText((String)jtUser.getValueAt(row, 3));
		tfSex.setText((String)jtUser.getValueAt(row, 4));
		tfPN.setText((String)jtUser.getValueAt(row, 5));
		tfBir.setText((String)jtUser.getValueAt(row, 6));
		tfTel.setText((String)jtUser.getValueAt(row, 7));
		tfEmail.setText((String)jtUser.getValueAt(row, 8));
	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	}
	
