package be.nonLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.Utilities;

public class NonInf extends JFrame{

	private JLabel lblName, lblSex, lblNum, lblNonUser;
	private JTextField tfName, tfNum;
	private JPanel jpInf, jpButton;
	private ButtonGroup rg;
	private JRadioButton rbWoman, rbMan;
	private JButton btnOK;
	
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);
	
	public NonInf(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jpInf = new JPanel();
		jpInf.setLayout(null);
		jpInf.setBackground(Color.white);
		
		lblNonUser = new JLabel("비회원 예매");
		lblNonUser.setFont(fontNanumGothic20);
		lblNonUser.setBackground(Color.white);
		lblNonUser.setLocation(10, 10);
		lblNonUser.setSize(new Dimension(300, 30));
		
		lblName = new JLabel("승객 이름");
		lblName.setFont(fontNanumGothic12);
		lblName.setBackground(Color.white);
		lblName.setLocation(10, 50);
		lblName.setSize(new Dimension(300, 15));
		
		tfName = new JTextField("승객 이름을 입력해주세요", 50);
		tfName.setFont(fontNanumGothic12);
		tfName.setBackground(Color.white);
		tfName.setLocation(10, 75);
		tfName.setSize(new Dimension(350, 30));
		
		lblSex = new JLabel("성별");
		lblSex.setFont(fontNanumGothic12);
		lblSex.setBackground(Color.white);
		lblSex.setLocation(10, 130);
		lblSex.setSize(new Dimension(300, 15));
		
		rg = new ButtonGroup();
		rbWoman = new JRadioButton("여자");
		rbWoman.setFont(fontNanumGothic12);
		rbWoman.setBackground(Color.white);
		rbWoman.setLocation(10, 155);
		rbWoman.setSize(new Dimension(70, 15));
		
		rbMan = new JRadioButton("남자");
		rbMan.setFont(fontNanumGothic12);
		rbMan.setBackground(Color.white);
		rbMan.setLocation(100, 155);
		rbMan.setSize(new Dimension(70, 15));
		
		rg.add(rbWoman);
		rg.add(rbMan);
		
		
		lblNum = new JLabel("여권 번호");
		lblNum.setFont(fontNanumGothic12);
		lblNum.setBackground(Color.white);
		lblNum.setLocation(10, 190);
		lblNum.setSize(new Dimension(300, 15));
		
		tfNum = new JTextField("ex)여권번호양식정하면수정할거임!", 50);
		tfNum.setFont(fontNanumGothic12);
		tfNum.setBackground(Color.white);
		tfNum.setLocation(10, 210);
		tfNum.setSize(new Dimension(350, 30));
		
		jpInf.add(lblNonUser);
		jpInf.add(lblName);
		jpInf.add(tfName);
		jpInf.add(lblSex);
		jpInf.add(rbWoman);
		jpInf.add(rbMan);
		jpInf.add(lblNum);
		jpInf.add(tfNum);
		
		jpButton = new JPanel();
		jpButton.setBackground(Color.white);
		jpButton.setBorder(BorderFactory.createEmptyBorder(0, 400, 30, 20));
		btnOK = new JButton("다음");
		btnOK.setFont(fontNanumGothic12);
		btnOK.setPreferredSize(new Dimension(70, 30));
		btnOK.setBackground(new Color(000,000,205));
		btnOK.setForeground(new Color(255,255,255));
		jpButton.add(btnOK);
		
		
		add(jpInf);
		add(jpButton, BorderLayout.SOUTH);
		setVisible(true);
		
		
		
	}


	public static void main(String[] args) {
		new NonInf("My Frame", 500, 350);
	}

}
