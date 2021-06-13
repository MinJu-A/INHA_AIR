package be.nonLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class NonTel extends JFrame{
	
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);
	Font fontNanumGothic8 = new Font("NanumGothic", Font.BOLD, 8);
	
	private JLabel lblPhoneNum, lblEmail, lblReserveNum, lblPWgo, lblPw, lblPWcheck, lblPwWrite, lblPwWrite2;
	private JPanel jpInf, jpPw, jpButton, jpMain;
	private JTextField tfPhoneNum, tfEmail, tfReserve;
	private JPasswordField pfPw, pfPWcheck;
	private JButton btnOK, btnSeat, btnLaug, btnPay;
	private JCheckBox ckAgree;

	public NonTel(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jpMain = new JPanel();
		jpMain.setBackground(Color.white);
		
		jpInf = new JPanel();
		jpInf.setBackground(Color.white);
		jpInf.setPreferredSize(new Dimension(500, 230));
		jpInf.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jpInf.setLayout(null);
		
		
		lblPhoneNum = new JLabel("연락처");
		lblPhoneNum.setFont(fontNanumGothic12);
		lblPhoneNum.setBackground(Color.white);
		lblPhoneNum.setLocation(20, 10);
		lblPhoneNum.setSize(new Dimension(300, 20));
		
		tfPhoneNum = new JTextField("ex)0101998122", 20);
		tfPhoneNum.setFont(fontNanumGothic12);
		tfPhoneNum.setBackground(Color.white);
		tfPhoneNum.setLocation(20, 30);
		tfPhoneNum.setSize(new Dimension(300, 20));
		
		lblEmail = new JLabel("이메일");
		lblEmail.setFont(fontNanumGothic12);
		lblEmail.setBackground(Color.white);
		lblEmail.setLocation(20, 70);
		lblEmail.setSize(new Dimension(300, 20));
		
		tfEmail = new JTextField("ex)pinokoiandmj@naver.com", 20);
		tfEmail.setFont(fontNanumGothic12);
		tfEmail.setBackground(Color.white);
		tfEmail.setLocation(20, 100);
		tfEmail.setSize(new Dimension(300, 20));
		
		lblReserveNum = new JLabel("선호 예약 번호");//양식 정하면 수정
		lblReserveNum.setFont(fontNanumGothic12);
		lblReserveNum.setBackground(Color.white);
		lblReserveNum.setLocation(20, 130);
		lblReserveNum.setSize(new Dimension(300, 20));
		
		tfReserve = new JTextField("ex)양식을정해봅시다", 20);
		tfReserve.setFont(fontNanumGothic12);
		tfReserve.setBackground(Color.white);
		tfReserve.setLocation(20, 160);
		tfReserve.setSize(new Dimension(300, 20));
		
		ckAgree = new JCheckBox("예약 여정의 정보를 이메일과 SMS로 수신하는 것에 동의합니다.");
		ckAgree.setFont(fontNanumGothic12);
		ckAgree.setBackground(Color.white);
		ckAgree.setLocation(20, 190);
		ckAgree.setSize(new Dimension(500, 20));
		
		
		jpInf.add(lblPhoneNum);
		jpInf.add(tfPhoneNum);
		jpInf.add(lblEmail);
		jpInf.add(tfEmail);
		
		jpInf.add(lblReserveNum);
		jpInf.add(tfReserve);
		jpInf.add(ckAgree);
		
		jpPw = new JPanel();
		jpPw.setLayout(new GridLayout(0, 2,20,0));
		jpPw.setPreferredSize(new Dimension(500, 100));
		jpPw.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		//jpPw.setLocation(0, 230);
		jpPw.setBackground(Color .white);
		
		lblPw = new JLabel("비밀번호");
		lblPw.setFont(fontNanumGothic12);
		
		lblPWcheck = new JLabel("비밀번호 확인");
		lblPWcheck.setFont(fontNanumGothic12);
		
		lblPwWrite = new JLabel("6~8자리 문자 또는 숫자 입력");
		lblPwWrite.setForeground(new Color(128,128,128));
		lblPwWrite.setFont(fontNanumGothic8);
		
		lblPwWrite2 = new JLabel("6~8자리 문자 또는 숫자 입력");
		lblPwWrite2.setForeground(new Color(128,128,128));
		lblPwWrite2.setFont(fontNanumGothic8);
		
		pfPw = new JPasswordField(10);
		pfPWcheck = new JPasswordField(10);
		
		jpButton = new JPanel();
		jpButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		jpButton.setPreferredSize(new Dimension(500, 100));
		jpButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
		jpButton.setBackground(Color.white);
		
		btnSeat = new JButton("좌석배정");
		btnSeat.setBounds(10, 10, 40, 30);
		btnSeat.setBackground(Color.blue);
		btnSeat.setForeground(Color.WHITE);
		//btnSeat.setContentAreaFilled(false);
		
		
		btnLaug = new JButton("초과수화물");
		//btnLaug.setBackground(Color.blue);
		//btnLaug.setForeground(Color.WHITE);
		
		btnPay = new JButton("결제수단선택");
		btnPay.setBackground(Color.blue);
		btnPay.setForeground(Color.WHITE);
		
		
		jpButton.add(btnSeat);
		jpButton.add(btnLaug);
		jpButton.add(btnPay);
		
		jpPw.add(lblPw);
		jpPw.add(lblPWcheck);
		jpPw.add(lblPwWrite);
		jpPw.add(lblPwWrite2);
		jpPw.add(pfPw);
		jpPw.add(pfPWcheck);
		//jpPw.add(jpButton );
		
		
		
		jpMain.add(jpInf);
		jpMain.add(jpPw);
		jpMain.add(jpButton);
		
		add(jpMain);
		
		setVisible(true);
		
	}


	public static void main(String[] args) {
		new NonTel("My Frame", 500, 450);
	}

}
