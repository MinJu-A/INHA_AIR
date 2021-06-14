package customer.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import DataBase.databaseClass;
import customer.start.MainMenuForm;

//연우 - 아이디 비밀번호 찾기
public class FindIdPwForm extends JFrame implements ActionListener {

	//데이터베이스 관련
	static String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
	static String dbID="inhaair";
	static String dbPassword="1234";
	
	//폰트값
	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);		// 나눔고딕 9
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
	Font fontNanumGothic11 = new Font("NanumGothic", Font.BOLD, 11);	// 나눔고딕 12
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 25
	
	private String title = "FindIdPw"; //제목
	private int width = 600, height = 400; //화면크기
	private JPanel jpIdTitle, jpIdFind, jpPwFind, jpPwTitle; //아이디제목패널, 아이디찾기패널, 비밀번호찾기패널, 비밀번호제목패널
	private JLabel lblTitle, lblIdName, lblId, lblPwName, lblIdPhoneNum, lblPwPhoneNum, lblPwTitle; //아이디제목, 아이디이름, 아이디, 비밀번호이름, 아이디핸드폰번호, 비밀번호핸드폰번호, 비밀번호제목 라벨
	private JTextField tfIdName, tfPwName, tfId; //아이디이름, 비밀번호이름, 비밀번호의 아이디 이볅 패널
	private JFormattedTextField tfIdPhoneNum, tfPwPhoneNum; //핸드폰번호 포맷필드
	private JButton btnFindId, btnFindPw; //아이디찾기, 비밀번호찾기
	private JLabel lblPWPhoneEx, lblIDPhoneEx; //핸드폰번호 예시
	private String idFound = ""; //아이디 값
	
	public String getIdFound() {	//아이디 값 받아오기
		return idFound;
	}


	public FindIdPwForm() {
		
		//화면설정
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		Container c = getContentPane();
		c.setBackground(Color.white);
		setLayout(null);
		//this.setUndecorated(true); //타이틀바 없애기 
		//setFont(fontNanumGothic);
		
		setFindIdPw();
		
		setVisible(true);
		
	}
	
	
	private void setFindIdPw() {
		
		//아이디찾기 제목패널
		jpIdTitle = new JPanel();
		jpIdTitle.setLayout(null);
		jpIdTitle.setSize(200, 60);
		jpIdTitle.setLocation(0, 0);
		jpIdTitle.setBackground(Color.white);
		
		lblTitle = new JLabel("아이디 찾기"); 		//아이디찾기 제목라벨
		lblTitle.setFont(fontNanumGothic20);
		lblTitle.setSize(150, 40);
		lblTitle.setLocation(20, 10);
		jpIdTitle.add(lblTitle);
		
		jpIdFind = new JPanel();	//아이디찾기 패널
		jpIdFind.setLayout(null);
		jpIdFind.setSize(300, 300);
		jpIdFind.setLocation(0, 50);
		jpIdFind.setBackground(Color.white);
		
		lblIdName = new JLabel("영문이름"); 		//영문이름 라벨
		lblIdName.setFont(fontNanumGothic15);
		lblIdName.setSize(150, 40);
		lblIdName.setLocation(15, 10);
		
		tfIdName = new JTextField();	//아이디찾기의 이름텍스트필드 설장
		tfIdName.setFont(fontNanumGothic15);
		tfIdName.setSize(250, 40);
		tfIdName.setLocation(15, 45);
		
		lblIdPhoneNum = new JLabel("핸드폰번호"); 		//아이디찾기의 핸드폰번호 라벨
		lblIdPhoneNum.setFont(fontNanumGothic15);
		lblIdPhoneNum.setSize(150, 40);
		lblIdPhoneNum.setLocation(15, 80);
		
		try {
			MaskFormatter format = new MaskFormatter("###########"); //아이디찾기의 핸드폰번호 포맷설정
			tfIdPhoneNum = new JFormattedTextField(format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lblIDPhoneEx = new JLabel("예) 01012345678"); //핸드폰번호 예시
		lblIDPhoneEx.setSize(300, 30);
		lblIDPhoneEx.setLocation(100, 85);
		lblIDPhoneEx.setFont(fontNanumGothic11);
		lblIDPhoneEx.setForeground(Color.gray);
			
		tfIdPhoneNum.setFont(fontNanumGothic15);	//핸드폰번호 필드
		tfIdPhoneNum.setSize(250, 40);
		tfIdPhoneNum.setLocation(15, 115);
		
		btnFindId = new JButton("아이디 찾기");	//아이디찾기 버튼
		btnFindId.setFont(fontNanumGothic15);
		btnFindId.setForeground(Color.white);
		btnFindId.setSize(250, 40);
		btnFindId.setLocation(15, 240);
		btnFindId.setBorderPainted(false); //버튼 윤곽선 제거
		btnFindId.setBackground(new Color(10,90,150));
		btnFindId.addActionListener(this);
		
		//패널에 값 모두 추가
		jpIdFind.add(lblIdName);
		jpIdFind.add(tfIdName);
		jpIdFind.add(lblIdPhoneNum);
		jpIdFind.add(tfIdPhoneNum);
		jpIdFind.add(lblIDPhoneEx);
		jpIdFind.add(btnFindId);
		
		jpPwTitle = new JPanel(); //비밀번호찾기 제목패널
		jpPwTitle.setLayout(null);
		jpPwTitle.setSize(200, 60);
		jpPwTitle.setLocation(300, 0);
		jpPwTitle.setBackground(Color.white);
		
		lblPwTitle = new JLabel("비밀번호 찾기"); 		//비밀번호 찾기 제목라벨
		lblPwTitle.setFont(fontNanumGothic20);
		lblPwTitle.setSize(150, 40);
		lblPwTitle.setLocation(20, 10);
		jpPwTitle.add(lblPwTitle);

		jpPwFind = new JPanel();	//비밀번호찾기 패널 생성
		jpPwFind.setLayout(null);
		jpPwFind.setSize(300, 300);
		jpPwFind.setLocation(300, 50);
		jpPwFind.setBackground(Color.white);
		
		lblPwName = new JLabel("영문이름"); 		//영문이름 라벨
		lblPwName.setFont(fontNanumGothic15);
		lblPwName.setSize(150, 40);
		lblPwName.setLocation(15, 10);
		
		tfPwName = new JTextField();	//비밀번호찾기의 영문이름 텍스트필드
		tfPwName.setFont(fontNanumGothic15);
		tfPwName.setSize(250, 40);
		tfPwName.setLocation(15, 45);
		
		lblPwPhoneNum = new JLabel("핸드폰번호"); 		//핸드폰번호 라벨
		lblPwPhoneNum.setFont(fontNanumGothic15);
		lblPwPhoneNum.setSize(150, 40);
		lblPwPhoneNum.setLocation(15, 80);
		
		lblPWPhoneEx = new JLabel("예) 01012345678");	//핸드폰번호 예시라벨
		lblPWPhoneEx.setSize(300, 30);
		lblPWPhoneEx.setLocation(100, 85);
		lblPWPhoneEx.setFont(fontNanumGothic11);
		lblPWPhoneEx.setForeground(Color.gray);
		
		try {
			MaskFormatter format = new MaskFormatter("###########"); //핸드폰번호라벨 포맷설정
			tfPwPhoneNum = new JFormattedTextField(format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfPwPhoneNum.setFont(fontNanumGothic15);
		tfPwPhoneNum.setSize(250, 40);
		tfPwPhoneNum.setLocation(15, 115);
		
		lblId = new JLabel("아이디"); 		//아이디 라벨
		lblId.setFont(fontNanumGothic15);
		lblId.setSize(150, 40);
		lblId.setLocation(15, 150);
		
		tfId = new JTextField();	//아이디 텍스트필드
		tfId.setFont(fontNanumGothic15);
		tfId.setSize(250, 40);
		tfId.setLocation(15, 185);
		
		btnFindPw = new JButton("비밀번호 찾기"); //비밀번호찾기 버튼
		btnFindPw.setFont(fontNanumGothic15);
		btnFindPw.setForeground(Color.white);
		btnFindPw.setSize(250, 40);
		btnFindPw.setLocation(15, 240);
		btnFindPw.setBorderPainted(false); //버튼 윤곽선 제거
		btnFindPw.setBackground(new Color(10,90,150));
		btnFindPw.addActionListener(this);
		
		//패널에 값 모두 붙이기
		jpPwFind.add(lblPwName);
		jpPwFind.add(tfPwName);
		jpPwFind.add(lblPwPhoneNum);
		jpPwFind.add(tfPwPhoneNum);
		jpPwFind.add(lblId);
		jpPwFind.add(tfId);
		jpPwFind.add(btnFindPw);
		jpPwFind.add(lblPWPhoneEx);
		
		add(jpIdTitle);
		add(jpIdFind);
		add(jpPwTitle);
		add(jpPwFind);
		
	}

	
	public static void main(String[] args) {
		databaseClass.connect(dbURL, dbID, dbPassword);
		new FindIdPwForm();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//new customer.login.FindIdForm(this);
		//new customer.login.FindPwForm(this);
		
		
		if(obj == btnFindId) {	//아이디찾기 버튼일 경우
			String name = tfIdName.getText();
			String tel = tfIdPhoneNum.getText();
			
			if (name.isEmpty() || tel.isEmpty()) {	//값이 비어있으면 입력하라고 뜸
				JOptionPane.showMessageDialog(null, "영문이름과 핸드폰번호를 입력해주세요.", "아이디 찾기 실패", JOptionPane.OK_CANCEL_OPTION); //INFORMATION_MESSAGE, QUESTION_MESSAGE, WARNING_MESSAGE, ERROR_MESSAGE
			} else if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "영문이름을 입력해주세요.", "아이디 찾기 실패", JOptionPane.OK_CANCEL_OPTION);
			} else if (tel.isEmpty()){
				JOptionPane.showMessageDialog(null, "핸드폰번호를 입력해주세요.", "아이디 찾기 실패", JOptionPane.OK_CANCEL_OPTION);
			} else { //값이 비어있지 않으면 
				boolean check = checkNameNum(name, tel); //데이터베이스에 값 확인
				if(check) {	//값이 있으면 화면 띄우기
					new customer.login.FindIdForm(this);
				} else {	//값이 없으면 팝업알림
					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.", "아이디 찾기 실패", JOptionPane.OK_CANCEL_OPTION);
				}
			}
			
			
		} else if (obj == btnFindPw) {	//비밀번호 찾기버튼의 경우
			String name = tfPwName.getText();
			String tel = tfPwPhoneNum.getText();
			String id = tfId.getText();
			
			if (name.isEmpty() || tel.isEmpty() || id.isEmpty()) {	//값이 비어있으면 입력하라고 띄우기
				JOptionPane.showMessageDialog(null, "영문이름, 핸드폰번호, 아이디를 입력해주세요.", "비밀번호 찾기 실패", JOptionPane.OK_CANCEL_OPTION); //INFORMATION_MESSAGE, QUESTION_MESSAGE, WARNING_MESSAGE, ERROR_MESSAGE
			} else if (name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "영문이름을 입력해주세요.", "비밀번호 찾기 실패", JOptionPane.OK_CANCEL_OPTION);
			} else if (tel.isEmpty()){
				JOptionPane.showMessageDialog(null, "핸드폰번호를 입력해주세요.", "비밀번호 찾기 실패", JOptionPane.OK_CANCEL_OPTION);
			} else if (id.isEmpty()){
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.", "비밀번호 찾기 실패", JOptionPane.OK_CANCEL_OPTION);
			} else {	//값이 비어있지 않으면
				boolean check = checkNameNumID(name, tel, id);	//데이터베이스에 값 확인
				if(check) {
					idFound = id;
					new customer.login.FindPwForm(this); //비밀번호 변경 폼 띄우기
				} else {
					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.", "비밀번호 찾기 실패", JOptionPane.OK_CANCEL_OPTION);
				}
			}
			
			
		}
	}


	private boolean checkNameNumID(String name, String tel, String id) { //비밀번호 값 있는지 sql문으로 확인하기
		boolean check = false;
		
		String sql = "SELECT * FROM user WHERE nameENG IN('"+ name +"') AND tel IN('" + tel + "') AND id IN('" + id + "')";
		ResultSet rs = databaseClass.select(sql);
		
		try {
			if(rs.next()) {	//내용이 있을 때
				return check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return check;
	}


	private boolean checkNameNum(String name, String tel) { //아이디 값 있는지 sql문으로 확인하기
		boolean check = false;
		
		String sql = "SELECT * FROM user WHERE nameENG IN('"+ name +"') AND tel IN('" + tel + "')";
		ResultSet rs = databaseClass.select(sql);

		try {
			if(rs.next()) {	//내용이 있을 때
				idFound = rs.getString(1);
				return check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return check = false;
	}

}
