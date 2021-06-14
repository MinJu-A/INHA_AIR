package customer.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DataBase.databaseClass;

//연우 - 비밀번호 찾기
public class FindPwForm extends JFrame implements ActionListener{
	
	//창 설정
	private String title = "비밀번호 찾기";
	private int width = 400, height = 300;
	
	//데이터베이스 관련
	static String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
	static String dbID="inhaair";
	static String dbPassword="1234";
	
	// 폰트
	Font fontArial30 = new Font("Arial", Font.BOLD | Font.ITALIC, 30);
	Font fontNanumGothic13 = new Font("NanumGothic", Font.BOLD, 13);
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 18
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 20
	Font fontNanumGothic22 = new Font("NanumGothic", Font.BOLD, 22);	// 나눔고딕 20
	Font fontNanumGothic25 = new Font("NanumGothic", Font.BOLD, 25);	// 나눔고딕 25
	private JPanel jpTitle; //제목패널
	private JLabel lblTitle;	//제목라벨
	private JPanel jpChPw;	//비밀번호변경 패널
	private JLabel lblPw;	//비밀번호 라벨
	private JPasswordField tfPw;	//비밀번호 패스워드필드
	private JLabel lblPwChk;	//비밀번호확인 라벨
	private JPasswordField tfPwChk;	//비밀번호확인 패스워드필드
	private JButton btnChangePw;	//비밀번호 변경
	
	private FindIdPwForm findIdPwForm;
	
	private String id;	//아이디

	
	public FindPwForm(FindIdPwForm findIdPwForm) {
		
		this.findIdPwForm = findIdPwForm;
		
		id = findIdPwForm.getIdFound(); //아이디값 가져오기
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		Container c = getContentPane();
		c.setBackground(Color.white);
		
		setLayout(null);
		//this.setUndecorated(true); //타이틀바 없애기 
		//setFont(fontNanumGothic);
		
		setFindId();
		
		setVisible(true);
	}

	private void setFindId() {
		
		//비밀번호변경 제목패널
		jpTitle = new JPanel();
		jpTitle.setLayout(null);
		jpTitle.setSize(350, 50);
		jpTitle.setLocation(40, 0);
		jpTitle.setBackground(Color.white);
		
		lblTitle = new JLabel("비밀번호 변경"); //제목설정
		lblTitle.setFont(fontNanumGothic18);
		lblTitle.setSize(150, 35);
		lblTitle.setLocation(0, 10);
		jpTitle.add(lblTitle);
		
		//비밀번호 변경 패널
		jpChPw = new JPanel();
		jpChPw.setLayout(null);
		jpChPw.setSize(350, 140);
		jpChPw.setLocation(40, 50);
		jpChPw.setBackground(Color.white);
		
		lblPw= new JLabel("새 비밀번호");	//비밀번호 라벨
		lblPw.setSize(300, 30);
		lblPw.setLocation(0, 0);
		lblPw.setFont(fontNanumGothic15);
		jpChPw.add(lblPw);
		
		tfPw = new JPasswordField(); //비밀번호 패스워드필드
		tfPw.setSize(300, 30);
		tfPw.setLocation(0, 30);
		jpChPw.add(tfPw);
	
		lblPwChk = new JLabel("비밀번호 확인"); //비밀번호확인 라벨
		lblPwChk.setSize(350, 30);
		lblPwChk.setLocation(0, 70);
		lblPwChk.setFont(fontNanumGothic15);
		jpChPw.add(lblPwChk);
		
		tfPwChk = new JPasswordField(); //비밀번호확인 패스워드필드
		tfPwChk.setSize(300, 30);
		tfPwChk.setLocation(0, 100);
		jpChPw.add(tfPwChk);
		
		btnChangePw = new JButton("변경하기"); //변경하기 버튼
		btnChangePw.setBackground(new Color(10,90,150));
		btnChangePw.setSize(100, 30);
		btnChangePw.setLocation(140, 200);
		btnChangePw.setForeground(Color.WHITE);
		btnChangePw.setFont(fontNanumGothic13);
		btnChangePw.addActionListener(this);
		
		add(jpTitle);
		add(jpChPw);
		add(btnChangePw);
		
	}
	
	public static void main(String[] args) {
		databaseClass.connect(dbURL, dbID, dbPassword);
		//new FindIdPwForm();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//비밀번호 변경확인
		int result = JOptionPane.showConfirmDialog(null, "비밀번호를 변경하시겠습니까?.", "비밀번호 변경", JOptionPane.YES_NO_OPTION);
		
		if(result == JOptionPane.YES_OPTION) { //확인누르면	
			
			if(!tfPw.getText().equals(tfPwChk.getText())) {	//비밀번호와 비밀번호확인 일치한지 확인
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "비밀번호 변경 오류", JOptionPane.ERROR_MESSAGE);
			} else {
				//sql문으로 데이터베이스에 비밀번호 update
				String sql = "UPDATE user SET PASSWORD='"+ tfPw.getText() + "' WHERE ID='" + id + "'; ";
				try {
					databaseClass.update(sql);
					JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.", "비밀번호 변경 완료", JOptionPane.ERROR_MESSAGE);
					setVisible(false);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		}
	}
	
}
