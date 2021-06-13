package be.sign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindIdPw extends JFrame implements ActionListener {

	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);		// 나눔고딕 9
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 25
	
	private String title = "FindIdPw";
	private int width = 600, height = 400;
	private JPanel jpIdTitle, jpIdFind;
	private JLabel lblTitle;
	private JLabel lblIdName;
	private JTextField tfIdName;
	private JButton btnFindId;
	private JLabel lblIdPhoneNum;
	private JTextField tfIdPhoneNum;
	private Container jpPwFind;
	private JLabel lblPwName;
	private JLabel lblPwPhoneNum;
	private JTextField tfPwName;
	private Container tfPwPhoneNum;
	private JButton btnFindPw;
	private JLabel lblId;
	private Container tfId;
	private JPanel jpPwTitle;
	private JLabel lblPwTitle;
	
	
	public FindIdPw() {
		
		
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
		lblTitle = new JLabel("아이디 찾기"); 		//제목라벨
		lblTitle.setFont(fontNanumGothic20);
		lblTitle.setSize(150, 40);
		lblTitle.setLocation(20, 10);
		jpIdTitle.add(lblTitle);
		
		jpIdFind = new JPanel();
		jpIdFind.setLayout(null);
		jpIdFind.setSize(300, 300);
		jpIdFind.setLocation(0, 50);
		jpIdFind.setBackground(Color.white);
		lblIdName = new JLabel("영문이름"); 		//제목라벨
		lblIdName.setFont(fontNanumGothic15);
		lblIdName.setSize(150, 40);
		lblIdName.setLocation(20, 10);
		tfIdName = new JTextField();
		tfIdName.setFont(fontNanumGothic15);
		tfIdName.setSize(250, 40);
		tfIdName.setLocation(15, 45);
		lblIdPhoneNum = new JLabel("핸드폰번호"); 		//제목라벨
		lblIdPhoneNum.setFont(fontNanumGothic15);
		lblIdPhoneNum.setSize(150, 40);
		lblIdPhoneNum.setLocation(20, 80);
		tfIdPhoneNum = new JTextField();
		tfIdPhoneNum.setFont(fontNanumGothic15);
		tfIdPhoneNum.setSize(250, 40);
		tfIdPhoneNum.setLocation(15, 115);
		btnFindId = new JButton("아이디 찾기");
		btnFindId.setFont(fontNanumGothic15);
		btnFindId.setForeground(Color.white);
		btnFindId.setSize(100, 40);
		btnFindId.setLocation(100, 240);
		btnFindId.setBorderPainted(false); //버튼 윤곽선 제거
		btnFindId.setBackground(new Color(10,90,150));
		jpIdFind.add(lblIdName);
		jpIdFind.add(tfIdName);
		jpIdFind.add(lblIdPhoneNum);
		jpIdFind.add(tfIdPhoneNum);
		jpIdFind.add(btnFindId);
		
		//비밀번호찾기 제목패널
		jpPwTitle = new JPanel();
		jpPwTitle.setLayout(null);
		jpPwTitle.setSize(200, 60);
		jpPwTitle.setLocation(300, 0);
		jpPwTitle.setBackground(Color.white);
		lblPwTitle = new JLabel("비밀번호 찾기"); 		//제목라벨
		lblPwTitle.setFont(fontNanumGothic20);
		lblPwTitle.setSize(150, 40);
		lblPwTitle.setLocation(20, 10);
		jpPwTitle.add(lblPwTitle);

		jpPwFind = new JPanel();
		jpPwFind.setLayout(null);
		jpPwFind.setSize(300, 300);
		jpPwFind.setLocation(300, 50);
		jpPwFind.setBackground(Color.white);
		lblPwName = new JLabel("영문이름"); 		//제목라벨
		lblPwName.setFont(fontNanumGothic15);
		lblPwName.setSize(150, 40);
		lblPwName.setLocation(20, 10);
		tfPwName = new JTextField();
		tfPwName.setFont(fontNanumGothic15);
		tfPwName.setSize(250, 40);
		tfPwName.setLocation(15, 45);
		lblPwPhoneNum = new JLabel("핸드폰번호"); 		//제목라벨
		lblPwPhoneNum.setFont(fontNanumGothic15);
		lblPwPhoneNum.setSize(150, 40);
		lblPwPhoneNum.setLocation(20, 80);
		tfPwPhoneNum = new JTextField();
		tfPwPhoneNum.setFont(fontNanumGothic15);
		tfPwPhoneNum.setSize(250, 40);
		tfPwPhoneNum.setLocation(15, 115);
		lblId = new JLabel("아이디"); 		//제목라벨
		lblId.setFont(fontNanumGothic15);
		lblId.setSize(150, 40);
		lblId.setLocation(20, 150);
		tfId = new JTextField();
		tfId.setFont(fontNanumGothic15);
		tfId.setSize(250, 40);
		tfId.setLocation(15, 185);
		btnFindPw = new JButton("비밀번호 찾기");
		btnFindPw.setFont(fontNanumGothic15);
		btnFindPw.setForeground(Color.white);
		btnFindPw.setSize(100, 40);
		btnFindPw.setLocation(100, 240);
		btnFindPw.setBorderPainted(false); //버튼 윤곽선 제거
		btnFindPw.setBackground(new Color(10,90,150));
		jpPwFind.add(lblPwName);
		jpPwFind.add(tfPwName);
		jpPwFind.add(lblPwPhoneNum);
		jpPwFind.add(tfPwPhoneNum);
		jpPwFind.add(lblId);
		jpPwFind.add(tfId);
		jpPwFind.add(btnFindPw);
		
		add(jpIdTitle);
		add(jpIdFind);
		add(jpPwTitle);
		add(jpPwFind);
		
	}

	
	public static void main(String[] args) {
		new FindIdPw();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
