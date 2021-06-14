package Management.Airplane;

//이것도 건들지 마세용
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import DataBase.databaseClass;
import Management.AirPort.AirportList;
import Management.Airway.AirwayList;
import Management.Form.HintTextField;
import Management.Main.MainForm;
import Management.Payment.PaymentList;
import Management.User.UserList;
import be.sign.SignIn;
import customer.login.LoginForm;

public class AirplaneList extends JFrame implements ActionListener, MouseListener {
	// Title 및 사이즈 설정
	private String title = "Management";
	private int width = 1120, height = 770;
	
	//메뉴
		private JPanel jpTOP, jpMenu;
		private JButton btnLogo, btnUser, btnAirway, btnAirport, btnPay, btnLogout, btnAirplane;
		private LoginForm signIn;
		private UserList userlist, userList;
		private PaymentList paymentlist;
		private AirwayList airwaylist;
		private AirportList airportlist;
		private MainForm mainform;
		private AirplaneList airplanelist;
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
	
	// 버튼
	private JPanel jpbutton;
	
	
	//테이블
	private JPanel jpTable;
	private DefaultTableModel model;
	private CreateTable jtAirway;
	private String[][] datas = new String[0][0];
	private String[] StableTitle = {"편명", "출발지","도착지","이코노미","비즈니스","퍼스트", "가격-이코노미", "가격-비즈니스","가격-퍼스트"};
	private DefaultTableCellRenderer Center; //테이블 정렬
	private JTableHeader jtAHeader;
	private JScrollPane sp;
	
	//수정
	private JPanel jpAll, jpBtn, jpEdit, jpNew, jpSer;
	private JButton btnOk, btnBye, btnDel, btnMod, btnser;
	private JLabel lblNew, lblPland, lbleconomy, lblbesiness, lblfirst, lblDepTime, lblArr, lblArrDay, lblArrTime, lblserach,
	lblDep,lblPeconomy, lblPbesiness, lblPfirst;
	private HintTextField tfPlane, tfeconomy, tfBusiness, tffirst, tfDepTime, tfArr, tfArrDay, tffArrTime, tfSer,
	tfDep, tfPeconomy, tfPBusiness, tfPfirst;
	

	
	

	
	public AirplaneList() {
		
		
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
		
		// 좌석테이블
		setSeatlist();
		
		
		//수정창
		setUserEdit();
		
		//검색
		setPlaneTable(0,"");

		
		setVisible(true);
		
	}

	
	//유저 조회 테이블
	private void setPlaneTable(int i, String codename) {
		
		//전체 사용자 조회
		String sql = "SELECT *\r\n"
				+ "FROM airplane\r\n"
				+ "ORDER BY flightCode";
		
		//테이블 초기화
		model.setNumRows(0);
		
		if(i==0) {
			sql = "SELECT *\r\n"
					+ "FROM airplane\r\n"
					+ "ORDER BY flightCode";
			System.out.println(sql);
		} else if(i==1) {
			sql = "SELECT *\r\n"
					+ "FROM airplane\r\n"
					+ "WHERE flightCode = '" +codename+ "'\r\n"
					+ "ORDER BY flightCode";
			System.out.println(sql);
		}
		
		//결제 금액 검색
		ResultSet rs = databaseClass.select(sql);
		try {
			while(rs.next()) {
				String flightCode = rs.getString("flightCode");
				String Dep = rs.getString("from");
				String Arr = rs.getString("to");
				String economy = rs.getString("economy");
				String business = rs.getString("business");
				String first = rs.getString("first");
				String Peconomy = rs.getString("economyPay");
				String pbusiness = rs.getString("businessPay");
				String Pfirst = rs.getString("firstPay");
				
				String[] Airline = {flightCode, Dep, Arr, economy, business, first, Peconomy, pbusiness, Pfirst};
				model.addRow(Airline);
				
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
		lblserach = new JLabel("편명 검색  ");
		lblserach.setFont(fontNanumGothic15);
		lblserach.setHorizontalAlignment(JLabel.CENTER);
		
		//검색 텍스트필드
		tfSer = new HintTextField("ex)IH1222");
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
	 	lblPland = new JLabel("편명No.  ");
	 	lblPland.setFont(fontNanumGothic15);
	 	lblPland.setHorizontalAlignment(JLabel.CENTER);
	 	lblDep = new JLabel("출발지  ");
	 	lblDep.setFont(fontNanumGothic15);
	 	lblDep.setHorizontalAlignment(JLabel.CENTER);
	 	lblArr = new JLabel("도착지  ");
	 	lblArr.setFont(fontNanumGothic15);
	 	lblArr.setHorizontalAlignment(JLabel.CENTER);
	 	lbleconomy = new JLabel("이코노미  ");
	 	lbleconomy.setFont(fontNanumGothic15);
	 	lbleconomy.setHorizontalAlignment(JLabel.CENTER);
	 	lblbesiness = new JLabel("비즈니스  ");
	 	lblbesiness.setFont(fontNanumGothic15);
	 	lblbesiness.setHorizontalAlignment(JLabel.CENTER);
	 	lblfirst = new JLabel("퍼스트  ");
	 	lblfirst.setFont(fontNanumGothic15);
	 	lblfirst.setHorizontalAlignment(JLabel.CENTER);
	 	lblPeconomy = new JLabel("가격-이코노미  ");
	 	lblPeconomy.setFont(fontNanumGothic15);
	 	lblPeconomy.setHorizontalAlignment(JLabel.CENTER);
	 	lblPbesiness = new JLabel("가격-비즈니스  ");
	 	lblPbesiness.setFont(fontNanumGothic15);
	 	lblPbesiness.setHorizontalAlignment(JLabel.CENTER);
	 	lblPfirst = new JLabel("가격-퍼스트  ");
	 	lblPfirst.setFont(fontNanumGothic15);
	 	lblPfirst.setHorizontalAlignment(JLabel.CENTER);
	 			
	 	
	 	//폼 텍스트필드 
	 	tfPlane = new HintTextField("ex)IH1222");
	 	tfDep = new HintTextField("ex)AKL");
	 	tfArr = new HintTextField("ex)AKL");
	 	tfeconomy = new HintTextField("좌석수만입력해주세요");
	 	tfBusiness = new HintTextField("좌석수만입력해주세요");
	 	tffirst = new HintTextField("좌석수만입력해주세요");
	 	tfPeconomy = new HintTextField("금액만 입력해주세요");
	 	tfPBusiness = new HintTextField("금액만 입력해주세요");
	 	tfPfirst = new HintTextField("금액만 입력해주세요");
	 	
	 	//붙이기
	 	jpNew.add(lblPland);
	 	jpNew.add(tfPlane);
	 	jpNew.add(lblDep);
	 	jpNew.add(tfDep);
	 	jpNew.add(lblArr);
	 	jpNew.add(tfArr);
	 	jpNew.add(lbleconomy);
	 	jpNew.add(tfeconomy);
	 	jpNew.add(lblbesiness);
	 	jpNew.add(tfBusiness);
	 	jpNew.add(lblfirst);
	 	jpNew.add(tffirst);
	 	jpNew.add(lblPeconomy);
	 	jpNew.add(tfPeconomy);
	 	jpNew.add(lblPbesiness);
	 	jpNew.add(tfPBusiness);
	 	jpNew.add(lblPfirst);
	 	jpNew.add(tfPfirst);
	 	
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





	private void setSeatlist() {
		jpTable = new JPanel(new BorderLayout());
		jpTable.setSize(600, 520);
		jpTable.setLocation(465,45);
		jpTable.setBackground(Color.WHITE);
		
		model = new DefaultTableModel(datas, StableTitle);
		
		jtAirway = new CreateTable(model);
		jtAirway.setFont(fontNanumGothic13);
		jtAirway.setRowHeight(20);
		jtAirway.setFillsViewportHeight(true); //스크롤팬에 꽉 차서 보이게 하기
		jtAirway.setBackground(Color.WHITE);
		jtAirway.addMouseListener(this);
		
		Center = new DefaultTableCellRenderer(); //테이블 정렬
		Center.setHorizontalAlignment(JLabel.CENTER); //가운데정렬
		jtAirway.getColumn("편명").setCellRenderer(Center);
		jtAirway.getColumn("출발지").setCellRenderer(Center);
		jtAirway.getColumn("도착지").setCellRenderer(Center);
		jtAirway.getColumn("이코노미").setCellRenderer(Center);
		jtAirway.getColumn("비즈니스").setCellRenderer(Center);
		jtAirway.getColumn("퍼스트").setCellRenderer(Center);
		jtAirway.getColumn("가격-이코노미").setCellRenderer(Center);
		jtAirway.getColumn("가격-비즈니스").setCellRenderer(Center);
		jtAirway.getColumn("가격-퍼스트").setCellRenderer(Center);
		
		jtAHeader = jtAirway.getTableHeader();
		jtAHeader.setReorderingAllowed(false); //컬럼 이동 금지
		jtAHeader.setResizingAllowed(false); //컬럼 크기 변경 금지
		jtAHeader.setBackground(colorBtn);
		jtAHeader.setFont(fontNanumGothic15);
		jtAHeader.setForeground(Color.white);
		jtAHeader.setPreferredSize(new Dimension(0,25));
		
		sp = new JScrollPane(jtAirway, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
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
		new AirplaneList();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
Object obj = e.getSource();
		
	if(obj == btnLogo) {
		//메인으로 돌아가기
		result = JOptionPane.showConfirmDialog(this, "메인으로 돌아가시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "메인으로 돌아갑니다.");
			dispose();
			userList = new UserList();
		}else {
			JOptionPane.showMessageDialog(this, "메인으로 돌아가지 않습니다.");
		}
			
		} else if(obj == btnLogout){
		 //로그아웃
			int result = JOptionPane.showConfirmDialog(this, "정말 로그아웃 하시겠습니까?", "로그아웃",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION ) {
				JOptionPane.showMessageDialog(null, "시스템을 종료합니다");
				dispose();
				signIn = new LoginForm();
			} else {
				JOptionPane.showMessageDialog(null, "로그아웃을 취소합니다.");
			}
		}  else if(obj == btnUser) {
			//유저창
			dispose();
			userList = new UserList();
		} else if(obj == btnAirway) {
			//항공편
			dispose();
			airwaylist = new AirwayList();
		} else if(obj == btnAirport) {
			//공항
			dispose();
			airportlist = new AirportList();
		} else if(obj == btnPay) {
			//매출
			dispose();
			paymentlist = new PaymentList();
		} else if(obj == btnAirplane) {
			//비행기
			dispose();
			airplanelist = new AirplaneList();
		} else if(obj == btnBye) {
			//취소
			result = JOptionPane.showConfirmDialog(this, "입력을 취소하시겠습니까?", "입력 취소",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "입력이 취소되었습니다.");
				tfSer.setText("");
				tfPlane.setText("");
				tfDep.setText("");
				tfArr.setText("");
				tfeconomy.setText("");
				tfBusiness.setText("");
				tffirst.setText("");
				tfPeconomy.setText("");
				tfPBusiness.setText("");
				tfPfirst.setText("");
			 	
			 	
			} else {
				JOptionPane.showMessageDialog(null, "계속 입력해주세요");
			}
		}else if(obj == btnser) {
			//검색
			String code = tfSer.getText();
			
			if(code.equals("")||code.equals("ex)IH1222")) {
				setPlaneTable(0, "");
			}else {
				setPlaneTable(1, code);
			}
		}else if(obj == btnDel) {
			//삭제
			String plane = tfPlane.getText();
			String Dep = tfDep.getText();
			String Arr = tfArr.getText();
			String economy = tfeconomy.getText();
			String business = tfBusiness.getText();
			String first = tffirst.getText();
			String Peconomy = tfPeconomy.getText();
			String Pbusiness = tfPBusiness.getText();
			String Pfirst = tfPfirst.getText();
			
			String sql = "DELETE FROM airplane\r\n"
					+ "WHERE  flightCode = '" + plane + "' AND `from` = '" + Dep + "' AND `to` = '" + Arr +"' AND economy = '" + economy
					+ "' AND business = '" + business + "' AND first = '" + first + "' AND economyPay = '" + Peconomy + "'AND businessPay = '" + Pbusiness + "'AND firstPay = '" + Pfirst + "'";

			System.out.println(sql);
			
			int rs = databaseClass.delete(sql);
			if(rs ==1) {
				JOptionPane.showMessageDialog(this, "삭제 되었습니다.");
				setPlaneTable(0, "");
				
			} else if(rs ==0) {
				JOptionPane.showMessageDialog(this, "삭제 실패했습니다.");
			}
			
			tfSer.setText("");
			tfPlane.setText("");
			tfDep.setText("");
			tfArr.setText("");
			tfeconomy.setText("");
			tfBusiness.setText("");
			tffirst.setText("");
			tfPeconomy.setText("");
			tfPBusiness.setText("");
			tfPfirst.setText("");
		
		 	
		}else if(obj==btnMod) {
			//수정
			String plane = tfPlane.getText();
			String Dep = tfDep.getText();
			String Arr = tfArr.getText();
			String economy = tfeconomy.getText();
			String business = tfBusiness.getText();
			String first = tffirst.getText();
			String Peconomy = tfPeconomy.getText();
			String Pbusiness = tfPBusiness.getText();
			String Pfirst = tfPfirst.getText();
			
			
			String sql = " UPDATE airplane\r\n"
					+ "SET  flightCode = '" + plane + "' , `from` = '" + Dep + "' , `to` = '" + Arr +"' , economy = '" + economy
					+ "' , business = '" + business + "' , first = " + first + " , economyPay = " + Peconomy + ", businessPay = " + Pbusiness + ", firstPay = " + Pfirst + ""
					+" WHERE  flightCode ='" +plane+"'";
			System.out.println(sql);
			
			int rs = databaseClass.update(sql);
			if(rs ==1) {
				JOptionPane.showMessageDialog(this, "수정 되었습니다.");
				setPlaneTable(0, "");
				
			} else if(rs ==0) {
				JOptionPane.showMessageDialog(this, "수정 실패했습니다.");
			}
			
			tfSer.setText("");
			tfPlane.setText("");
			tfDep.setText("");
			tfArr.setText("");
			tfeconomy.setText("");
			tfBusiness.setText("");
			tffirst.setText("");
			tfPeconomy.setText("");
			tfPBusiness.setText("");
			tfPfirst.setText("");
		} else if(obj == btnOk) {
			//등록
			if(tfPlane.getText().equals("")||tfPlane.getText().equals("ex)IH1222")){
			JOptionPane.showMessageDialog(null, "편명을 입력하세요");
			}else if(tfDep.getText().equals("")||tfDep.getText().equals("ex)IH1222")){
			JOptionPane.showMessageDialog(null, "출발지를 입력하세요");
			}else if(tfArr.getText().equals("")||tfArr.getText().equals("ex)IH1222")){
			JOptionPane.showMessageDialog(null, "도착지를 입력하세요");
			}else if(tfeconomy.getText().equals("")||tfeconomy.getText().equals("ex)IH1222")){
			JOptionPane.showMessageDialog(null, "좌석수를 입력하세요");
			}else if(tfBusiness.getText().equals("")||tfBusiness.getText().equals("ex)IH1222")) {
			JOptionPane.showMessageDialog(null, "좌석수를 입력하세요");
			}else if(tffirst.getText().equals("")||tffirst.getText().equals("ex)IH1222")) {
			JOptionPane.showMessageDialog(null, "좌석수를 입력하세요");
			}else if(tfPeconomy.getText().equals("")||tfPeconomy.getText().equals("ex)IH1222")) {
			JOptionPane.showMessageDialog(null, "금액을 입력하세요");
			}else if(tfPBusiness.getText().equals("")||tfPBusiness.getText().equals("ex)IH1222")) {
			JOptionPane.showMessageDialog(null, "금액을 입력하세요");
			}else if(tfPfirst.getText().equals("")||tfPfirst.getText().equals("ex)IH1222")) {
			JOptionPane.showMessageDialog(null, "금액을 입력하세요");
			}
			
			String plane = tfPlane.getText();
			String Dep = tfDep.getText();
			String Arr = tfArr.getText();
			String economy = tfeconomy.getText();
			String business = tfBusiness.getText();
			String first = tffirst.getText();
			String Peconomy = tfPeconomy.getText();
			String Pbusiness = tfPBusiness.getText();
			String Pfirst = tfPfirst.getText();
			
			String sql = "INSERT INTO airplane(flightCode, `from`, `to`, economy, business, first, economyPay, businessPay, firstPay )\r\n"
					+ "VALUES('" + plane +"','" + Dep +"','" + Arr +"','" + economy +"','" + business + "','" + first + "','" + Peconomy + "','" + Pbusiness + "', '" +Pfirst + "')";
			System.out.println(sql);
			
			int rs = databaseClass.insert(sql);
			if(rs==1) {
				JOptionPane.showMessageDialog(this, "등록 되었습니다.");
				setPlaneTable(0, "");
			} else if(rs==0) {
				JOptionPane.showMessageDialog(this, "등록 실패했습니다.");
			}
			tfSer.setText("");
			tfPlane.setText("");
			tfDep.setText("");
			tfArr.setText("");
			tfeconomy.setText("");
			tfBusiness.setText("");
			tffirst.setText("");
			tfPeconomy.setText("");
			tfPBusiness.setText("");
			tfPfirst.setText("");
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
	 int row = jtAirway.getSelectedRow();	
	 int col = jtAirway.getSelectedColumn();	
	
	 tfPlane.setText((String)jtAirway.getValueAt(row, 0));
	 tfDep.setText((String)jtAirway.getValueAt(row, 1));
	 tfArr.setText((String)jtAirway.getValueAt(row, 2));
	 tfeconomy.setText((String)jtAirway.getValueAt(row, 3));
	 tfBusiness.setText((String)jtAirway.getValueAt(row, 4));
	 tffirst.setText((String)jtAirway.getValueAt(row, 5));
	 tfPeconomy.setText((String)jtAirway.getValueAt(row, 6));
	 tfPBusiness.setText((String)jtAirway.getValueAt(row, 7));
	 tfPfirst.setText((String)jtAirway.getValueAt(row, 8));
	 
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
	
