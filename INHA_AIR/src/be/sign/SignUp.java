package be.sign;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SignUp extends JFrame{
	
	private String title = "INHA AIR";
	private int width = 500, height = 800;

	Font fontGothic = new Font("Gothic", Font.BOLD, 13);
	Font fontNanumGothic = new Font("NanumGothic", Font.BOLD, 20);
	
	private JPanel jpInf;
	private JCheckBox chkNewsLetter, chkPromotion, chkSMS;
	private JButton btnchk, btnBirth, btnBack, btnOk;
	private JLabel lblPerInf,lblAdd,lblLNKor, lblFNKor,lblLNEng,lblFNEng,lblID, lblPW, lblPWchk, lblBirth, lblSex, lblEmail, lblPhone;
	private JTextField tfLNKor, tfFNKor, tfLNEng, tfFNEng, tfID, tfEmail, tfPhone;
	private JPasswordField tfPW, tfPWchk;
	private ButtonGroup rg;
	private JRadioButton rbMan, rbWoman;


	public SignUp() {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jpInf = new JPanel();
		jpInf.setLayout(null);
		jpInf.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//jp1.setBackground(Color.WHITE);
		
		lblPerInf = new JLabel("회원정보(필수)");
		lblPerInf.setSize(300, 50);
		lblPerInf.setLocation(10, 5);
		lblPerInf.setFont(fontNanumGothic);
		jpInf.add(lblPerInf);
				
		lblLNKor= new JLabel("한글 이름");
		lblLNKor.setSize(300, 30);
		lblLNKor.setLocation(10, 40);
		lblLNKor.setFont(fontGothic);
		jpInf.add(lblLNKor);
		
		tfLNKor = new JTextField();
		tfLNKor.setSize(300, 30);
		tfLNKor.setLocation(10, 80);
		jpInf.add(tfLNKor);
	
		lblLNEng = new JLabel("영문 이름");
		lblLNEng.setSize(300, 30);
		lblLNEng.setLocation(10, 110);
		lblLNEng.setFont(fontGothic);
		jpInf.add(lblLNEng);
		
		tfLNEng = new JTextField();
		tfLNEng.setSize(300, 30);
		tfLNEng.setLocation(10, 140);
		jpInf.add(tfLNEng);
		
		lblID = new JLabel("회원 아이디");
		lblID.setSize(300, 50);
		lblID.setLocation(10, 160);
		lblID.setFont(fontGothic);
		jpInf.add(lblID);
		
		tfID = new JTextField();
		tfID.setSize(300, 30);
		tfID.setLocation(10, 200);
		jpInf.add(tfID);
		
		btnchk = new JButton("중복체크");
		btnchk.setSize(100, 30);
		btnchk.setLocation(315, 200);
		btnchk.setBackground(new Color(220,220,220));
		jpInf.add(btnchk);
		
		lblPW = new JLabel("비밀번호");
		lblPW.setSize(300, 50);
		lblPW.setLocation(10, 220);
		lblPW.setFont(fontGothic);
		jpInf.add(lblPW);
		
		tfPW = new JPasswordField();
		tfPW.setSize(300, 30);
		tfPW.setLocation(10, 260);
		jpInf.add(tfPW);
		
		lblPWchk = new JLabel("비밀번호 확인");
		lblPWchk.setSize(300, 50);
		lblPWchk.setLocation(10, 285);
		lblPWchk.setFont(fontGothic);
		jpInf.add(lblPWchk);
		
		tfPWchk = new JPasswordField();
		tfPWchk.setSize(300, 30);
		tfPWchk.setLocation(10, 320);
		jpInf.add(tfPWchk);
		
		lblBirth = new JLabel("생년월일");
		lblBirth.setSize(300, 50);
		lblBirth.setLocation(10, 340);
		lblBirth.setFont(fontGothic);
		jpInf.add(lblBirth);
		
		btnBirth = new JButton("");
		btnBirth.setSize(300, 30);
		btnBirth.setLocation(10, 375);
		btnBirth.setBackground(Color.white);
		jpInf.add(btnBirth);
		
		lblSex = new JLabel("성별");
		lblSex.setSize(300, 50);
		lblSex.setLocation(10, 395);
		lblSex.setFont(fontGothic);
		jpInf.add(lblSex);
		
		rg = new ButtonGroup();
		rbMan = new JRadioButton("남자");
		rbMan.setSize(50, 30);
		rbMan.setLocation(10, 430);
		rbWoman = new JRadioButton("여자");
		rbWoman.setSize(100, 30);
		rbWoman.setLocation(80, 430);
		rg.add(rbMan);
		rg.add(rbWoman);
		jpInf.add(rbMan);
		jpInf.add(rbWoman);
		
		lblEmail = new JLabel("이메일 주소");
		lblEmail.setSize(300, 50);
		lblEmail.setLocation(10, 455);
		lblEmail.setFont(fontGothic);
		jpInf.add(lblEmail);
		
		tfEmail = new JTextField("예)javaboss@inhaair.com");
		tfEmail.setSize(300, 30);
		tfEmail.setLocation(10, 490);
		jpInf.add(tfEmail);
		
		lblPhone = new JLabel("휴대전화 번호");
		lblPhone.setSize(300, 50);
		lblPhone.setLocation(10, 510);
		lblPhone.setFont(fontGothic);
		jpInf.add(lblPhone);
		
		tfPhone = new JTextField("예)01019981222");
		tfPhone.setSize(300, 30);
		tfPhone.setLocation(10, 550);
		jpInf.add(tfPhone);
		
		lblAdd = new JLabel("마케팅 및 광고 활용 동의");
		lblAdd.setSize(300, 50);
		lblAdd.setLocation(10, 580);
		lblAdd.setFont(fontGothic);
		jpInf.add(lblAdd);
		
		chkNewsLetter = new JCheckBox("뉴스레터(회원 소식지 등)");
		chkNewsLetter.setSize(300, 30);
		chkNewsLetter.setLocation(10, 620);
		jpInf.add(chkNewsLetter);
		
		chkPromotion = new JCheckBox("프로모션 정보(이벤트, 할인 쿠폰)");
		chkPromotion.setSize(300, 30);
		chkPromotion.setLocation(10, 650);
		jpInf.add(chkPromotion);

		chkSMS = new JCheckBox("SMS 수신 동의");
		chkSMS.setSize(300, 30);
		chkSMS.setLocation(10, 680);
		jpInf.add(chkSMS);
		
		btnBack = new JButton("이전");
		btnBack.setBackground(Color.white);
		btnBack.setSize(200, 30);
		btnBack.setLocation(10, 720);
		jpInf.add(btnBack);
		
		btnOk = new JButton("확인");
		btnOk.setBackground(Color.BLUE);
		btnOk.setSize(200, 30);
		btnOk.setLocation(220, 720);
		btnOk.setForeground(Color.WHITE);
		jpInf.add(btnOk);
	
		add(jpInf);
		
		setVisible(true);
	}


	public static void main(String[] args) {
		new SignUp();
	}

}

