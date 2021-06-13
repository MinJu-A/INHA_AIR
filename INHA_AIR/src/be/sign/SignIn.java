package be.sign;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//연우-로그인 화면 생성
public class SignIn extends JFrame implements ActionListener {

	private String title = "Sign In";
	private int width = 400, height = 500;
	
	
	Font fontNanumGothic12 = new Font("NanumGothic", Font.PLAIN , 12);	// 나눔고딕 12
	Font fontNanumGothic15 = new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 15
	Font fontNanumGothic16 = new Font("NanumGothic", Font.BOLD, 16);	// 나눔고딕 16
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 25
	
	
	private be.main.MainForm mainForm;
	
	private JPanel jpTop, jpCenter, jpBottom;
	private JLabel lblId, lblPw;
	private JTextField tfId, tfPw;
	private JButton btnLogin, btnFindIdPw, btnSignUp;
	//private JButton btnClose;
	
	
	// 액션
	private SignUp signUp;
	
	public SignIn() {
		
		this.mainForm = mainForm;
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		Container c = getContentPane();
		c.setBackground(Color.white);
		setLayout(new BorderLayout());
		//this.setUndecorated(true); //타이틀바 없애기 
		//setFont(fontNanumGothic);
		
		setLogin();
		
		setVisible(true);
		
	}
	
	private void setLogin() {
		
		//상단판넬
		jpTop = new JPanel();
		jpTop.setBackground(Color.white);
		jpTop.setPreferredSize(new Dimension(400, 100)); //판넬사이즈 설정
		jpTop.setLayout(new BorderLayout());
		JLabel login = new JLabel("로그인"); //로그인 라벨
		login.setFont(fontNanumGothic20);
		login.setHorizontalAlignment(JLabel.CENTER);
		//btnClose = new JButton();
		jpTop.add(login);
		add(jpTop, BorderLayout.NORTH);
		
		//중앙판넬
		jpCenter = new JPanel();
		jpCenter.setLayout(new GridLayout(5, 1, 10, 10));
		jpCenter.setBackground(Color.white);
		lblId = new JLabel("아이디"); //아이디 라벨
		tfId = new JTextField(); //아이디 입력
		lblPw = new JLabel("비밀번호"); //비밀번호 라벨
		tfPw = new JTextField(); //비밀번호 입력
		lblId.setFont(fontNanumGothic15);
		lblPw.setFont(fontNanumGothic15);
		tfId.setFont(fontNanumGothic15);
		tfPw.setFont(fontNanumGothic15);
		
		
		btnLogin = new JButton("로그인"); //로그인 버튼
		btnLogin.setFont(fontNanumGothic16);
		btnLogin.setBorderPainted(false); //버튼 윤곽선 제거
		btnLogin.setBackground(new Color(10,90,150)); //버튼 색 설정
		jpCenter.add(lblId);
		jpCenter.add(tfId);
		jpCenter.add(lblPw);
		jpCenter.add(tfPw);
		jpCenter.add(btnLogin);
		add(jpCenter, BorderLayout.CENTER);
		
		
		//하단판넬
		jpBottom = new JPanel();
		jpBottom.setPreferredSize(new Dimension(400, 100));
		jpBottom.setLayout(new GridLayout(3,1));
		jpBottom.setBackground(Color.white);
		jpBottom.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
		btnFindIdPw = new JButton("<HTML><U>아이디/비밀번호찾기</U></HTML>"); //아이디/비밀번호 찾기 버튼 추가
		btnFindIdPw.setForeground(new Color(000, 102, 255));
		btnFindIdPw.addActionListener(this);
		btnFindIdPw.setBorderPainted(false); //버튼 윤곽선 제거
		btnFindIdPw.setContentAreaFilled(false); //버튼배경 제거
		btnSignUp = new JButton("<HTML><U>회원가입</U></HTML>"); //회원가입 버튼 추가
		btnSignUp.setForeground(new Color(000, 102, 255));
		btnSignUp.addActionListener(this);
		btnSignUp.setBorderPainted(false); //버튼 윤곽선 제거
		btnSignUp.setContentAreaFilled(false); //버튼배경 제거
		btnFindIdPw.setFont(fontNanumGothic12);
		btnSignUp.setFont(fontNanumGothic12);
		
		jpBottom.add(btnFindIdPw);
		jpBottom.add(btnSignUp);
		
		add(jpBottom, BorderLayout.SOUTH);
		
		
		//좌우판넬
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(50, 500));
		west.setBackground(Color.WHITE);
		add(west, BorderLayout.WEST);
		JPanel east = new JPanel();
		east.setPreferredSize(new Dimension(50, 500));
		east.setBackground(Color.WHITE);
		add(east, BorderLayout.EAST);
		
		
		
	}
	
	public static void main(String[] args) {
		new SignIn();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnSignUp) {
			signUp = new SignUp();
			this.setVisible(false);
		}
	}
	
}
