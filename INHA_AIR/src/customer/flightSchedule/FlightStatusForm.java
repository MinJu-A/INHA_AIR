package customer.flightSchedule;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import DataBase.databaseClass;
import be.main.MainForm;
import be.menu.MenuBar;
import customer.start.MainMenuForm;

public class FlightStatusForm extends JFrame implements ActionListener {
	// Title 및 사이즈 설정
	private String title = "INHA AIR";
	private int width = 1120, height = 770;
	
	// 폰트
	Font fontArial30 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
	Font fontNanumGothic15 = new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	Font fontNanumGothic30 = new Font("NanumGothic", Font.BOLD, 30);	// 나눔고딕 30
	
	// 색상
	Color colorLogo = new Color(24, 62, 111);	
	Color colorBtn = new Color(10,90,150);
	Color crPaleblue = new Color(230,240,250);
	Color crGrey = new Color(240,240,240);
	
	//
	private String id;
	
	// 컴포넌트
	private JButton btnMainMenu;
	
	// Forms
	private MainMenuForm mainMenuForm;
	
	// 항공편 조회
	private JPanel jpSchedule, jpSearch, jpTable;
	private JLabel lblFrom, lblTo;
	private JComboBox<String> cbFrom, cbTo;
	private JButton btnOK;
	
	private ArrayList<String> city = new ArrayList<>();
	private ArrayList<String> destination = new ArrayList<>();
	
	// 테이블
	private DefaultTableModel model;
	private CreateTable jtSchedule;
	private String[][] datas = new String[0][0];
	private String[] tableTitle = {"편명", "출발일", "출발시간", "도착일", "도착시간"};
	
	// 예원 - 시작 화면
	public FlightStatusForm(String id) {
		this.id = id;
		
		// DB 정보 - 테스트 소스
		String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
		String dbID="inhaair";
		String dbPassword="1234";
		// 데이터베이스 연결 - 테스트 소스
		databaseClass.connect(dbURL, dbID, dbPassword);
		
		// 공항 정보 가져오기
		setCity();
				
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 배경색
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		
		// 레이아웃 설정
		setLayout(null);
		
		// 시작 버튼
		btnMainMenu = new JButton("INHA AIR");
		btnMainMenu.setSize(200, 50);
		btnMainMenu.setLocation(10, 10);
		btnMainMenu.setFont(fontArial30);
		btnMainMenu.setForeground(colorLogo);
		btnMainMenu.setBorderPainted(false);
		btnMainMenu.setBackground(Color.WHITE);
				
		// 전체 패널
		jpSchedule = new JPanel(null);
		jpSchedule.setSize(1100, 635);
		jpSchedule.setLocation(3, 90);
		jpSchedule.setBackground(Color.WHITE);
		
		// 예매 조회창
		setFindTrips();
		
		// 조회 테이블
		setTable();
		
		// 리스너
		btnMainMenu.addActionListener(this);
		
		// 컴포넌트 붙이기
		add(btnMainMenu);
		
		
		setVisible(true);
	}

	// 공항 정보 가져오기 - 운행되고 있는 공항만 가져옴
	private void setCity() {
		// 국내 공항 먼저
		String sqlIn = "SELECT code, country, city FROM airport \r\n"
				+ "WHERE terminal='국내' AND code IN (SELECT DISTINCT `from` FROM airplane)\r\n"
				+ "ORDER BY city";
		
		// 국내 공항 정보 가져와서 콤보박스에 추가
		ResultSet rsIn = databaseClass.select(sqlIn);
		try {
			while(rsIn.next()) {
				city.add(rsIn.getString("city") + "/" + rsIn.getString("code"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// 국내 뒤에 국제 공항 정보 나오도록
		String sqlOut = "SELECT code, country, city FROM airport \r\n"
				+ "WHERE terminal='국제' AND code IN (SELECT DISTINCT `from` FROM airplane)\r\n"
				+ "ORDER BY country, city";
		
		// 국제 공항 정보 가져와서 콤보박스에 추가
		ResultSet rsOut = databaseClass.select(sqlOut);
		try {
			while(rsOut.next()) {
				city.add(rsOut.getString("city") + "/" + rsOut.getString("code"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 출발지 가장 첫번째 값을 기준으로 도착지 정보 가져옴
		String dept = city.get(0);
		dept = dept.substring(dept.lastIndexOf("/")+1);
		String sqlDesn = "SELECT code, country, city FROM airport WHERE code IN (SELECT `to` "
				+ "FROM airplane "
				+ "WHERE `from`='" + dept + "')";
		
		ResultSet rsDesn = databaseClass.select(sqlDesn);
		try {
			while(rsDesn.next()) {
				destination.add(rsDesn.getString("city") + "/" + rsDesn.getString("code"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 예매 조회창
	private void setFindTrips() {
		// 검색 조건 선택
		jpSearch = new JPanel(null);
		jpSearch.setBorder(new EmptyBorder(30, 40, 30, 120));
		jpSearch.setSize(1000, 70);
		jpSearch.setLocation(50, 5);
		jpSearch.setBackground(crGrey);
		
		lblFrom = new JLabel("출발지");
		lblFrom.setFont(fontNanumGothic20);
		lblFrom.setHorizontalAlignment(JLabel.RIGHT);
		lblFrom.setSize(100, 40);
		lblFrom.setLocation(10, 18);
		
		cbFrom = new JComboBox<String>(city.toArray(new String[city.size()]));
		cbFrom.setFont(fontNanumGothic20);
		cbFrom.setSize(250, 40);
		cbFrom.setLocation(140, 18);
		cbFrom.setBackground(Color.WHITE);
		cbFrom.addActionListener(this);
		
		lblTo = new JLabel("도착지");
		lblTo.setFont(fontNanumGothic20);
		lblTo.setHorizontalAlignment(JLabel.RIGHT);
		lblTo.setSize(100, 40);
		lblTo.setLocation(400, 18);
		
		cbTo = new JComboBox<String>(destination.toArray(new String[destination.size()]));
		cbTo.setFont(fontNanumGothic20);
		cbTo.setSize(250, 40);
		cbTo.setLocation(530, 18);
		cbTo.setBackground(Color.WHITE);
		cbTo.addActionListener(this);
		
		btnOK = new JButton("조회");
		btnOK.setFont(fontNanumGothic20);
		btnOK.addActionListener(this);
		btnOK.setBackground(colorBtn);
		btnOK.setForeground(Color.WHITE);
		btnOK.setSize(150, 40);
		btnOK.setLocation(820, 18);
		btnOK.setBorder(null);
		
		jpSearch.add(lblFrom);
		jpSearch.add(cbFrom);
		jpSearch.add(lblTo);
		jpSearch.add(cbTo);
		jpSearch.add(btnOK);
		
		jpSchedule.add(jpSearch);
		
		add(jpSchedule);
	}
	
	
	// 항공편 테이블
	private void setTable() {
		jpTable = new JPanel(new BorderLayout());
		jpTable.setSize(1000, 520);
		jpTable.setLocation(50, 95);
		jpTable.setBackground(Color.WHITE);
		
		model = new DefaultTableModel(datas, tableTitle);
		
		jtSchedule = new CreateTable(model);
		jtSchedule.setFont(fontNanumGothic15);
		jtSchedule.setRowHeight(30);
		jtSchedule.setFillsViewportHeight(true);	// 배경색 변경 위함
		jtSchedule.setBackground(Color.WHITE);
		
		DefaultTableCellRenderer rowCenter = new DefaultTableCellRenderer();
		rowCenter.setHorizontalAlignment(JLabel.CENTER);
		jtSchedule.getColumn("편명").setCellRenderer(rowCenter);
		jtSchedule.getColumn("출발일").setCellRenderer(rowCenter);
		jtSchedule.getColumn("출발시간").setCellRenderer(rowCenter);
		jtSchedule.getColumn("도착일").setCellRenderer(rowCenter);
		jtSchedule.getColumn("도착시간").setCellRenderer(rowCenter);
		
		// 헤더 디자인 변경
		JTableHeader jtHeader = jtSchedule.getTableHeader();
		jtHeader.setReorderingAllowed(false);	// 컬럼 이동 금지
		jtHeader.setResizingAllowed(false);		// 칼럼 크기 조절 불가
		jtHeader.setBackground(colorBtn);
		jtHeader.setFont(fontNanumGothic18);
		jtHeader.setForeground(Color.WHITE);
		jtHeader.setPreferredSize(new Dimension(0, 30));
		
		JScrollPane scrollPane = new JScrollPane(jtSchedule);
		
		jpTable.add(scrollPane);

		jpSchedule.add(jpTable);
	}

	public static void main(String[] args) {
		new FlightStatusForm("test1");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnMainMenu) {
			// 메뉴로 이동
			mainMenuForm = new MainMenuForm();
			mainMenuForm.setId(id);
			this.setVisible(false);
			
		} else if(obj == cbFrom) {
			// 출발지 가져오기
			String selected = cbFrom.getSelectedItem().toString();
			String dept = selected.substring(selected.lastIndexOf("/")+1);
			
			// 도착지 콤보박스 설정
			setDestination(dept);
		} else if(obj == btnOK) {
			String dept = cbFrom.getSelectedItem().toString();
			String desn = cbTo.getSelectedItem().toString();
			
			dept = dept.substring(dept.lastIndexOf("/")+1);
			desn = desn.substring(desn.lastIndexOf("/")+1);
			
			// 항공편 조회
			flightStatus(dept, desn);
		}
	}

	// 도착지 콤보박스 설정 - 출발지에서 편항이 있는 도착지만 표시
	private void setDestination(String dept) {
		
		String sql = "SELECT code, country, city FROM airport WHERE code IN (SELECT `to` "
				+ "FROM airplane "
				+ "WHERE `from`='" + dept + "')";
		
		destination.clear();
		
		ResultSet rs = databaseClass.select(sql);
		try {
			while(rs.next()) {
				destination.add(rs.getString("city") + "/" + rs.getString("code"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cbTo.removeAllItems();
		
		for(String des : destination) {
			cbTo.addItem(des);
		}
	}
	
	// 항공편 조회
	private void flightStatus(String dept, String desn) {
		
		// 현재 날짜 이후 항공편 검색
		String sql = "SELECT flightCode, fromDate, fromTime, toDate, toTime\r\n"
				+ "FROM inhaair.airSchedule\r\n"
				+ "WHERE `from` = '" + dept + "' AND `to` = '" + desn + "' AND DATE(fromDate) > DATE(NOW())\r\n"
				+ "ORDER BY fromDate, flightCode";
		
		// 테이블 초기화
		model.setNumRows(0);
		
		// 검색한 항공편 테이블 추가
		ResultSet rs = databaseClass.select(sql);
		try {
			while(rs.next()) {
				String flightCode = rs.getString("flightCode");
				String fromDate = rs.getString("fromDate");
				String fromTime = rs.getString("fromTime");
				String toDate = rs.getString("toDate");
				String toTime = rs.getString("toTime");
				
				String[] sechdule = {flightCode, fromDate, fromTime, toDate, toTime};
				model.addRow(sechdule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// jtable 생성
	class CreateTable extends JTable {
		public CreateTable(DefaultTableModel model) {
			super(model);
		}

		@Override
		public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			
			JComponent component = (JComponent) super.prepareRenderer(renderer, row, column);

			// 선택된 행 제외하고
			if(!isRowSelected(row)) {
				
				// 짝수 행 홀수 행 배경색 다르게 지정
				if(row % 2 == 0) {
					component.setBackground(Color.WHITE);
				} else {
					component.setBackground(crGrey);
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
