package be.NonUserReserve;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Agreement extends JFrame{

	private JLabel lblAgree, lblArgreego;
	private JCheckBox ckAllAgree,ckPrivacy, ckOutbound;
	private JPanel jpCheck, jpButton;
	private JButton btnNext, btnBack, btnPrivacyShow, btnOutboundShow;
	
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);


	public Agreement(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jpCheck = new JPanel();
		jpCheck.setLayout(null);
		jpCheck.setBackground(Color.white);
		
		lblAgree = new JLabel("비회원 예매 동의");
		lblAgree.setFont(fontNanumGothic20);
		lblAgree.setBackground(Color.white);
		lblAgree.setLocation(10, 10);
		lblAgree.setSize(new Dimension(300, 20));
		
		lblArgreego = new JLabel("개인정보 수집 이용 및 국외 이전에 동의해 주세요");
		lblArgreego.setFont(fontNanumGothic12);
		lblArgreego.setBackground(Color.white);
		lblArgreego.setLocation(10, 35);
		lblArgreego.setSize(new Dimension(300, 15));
		
		ckAllAgree = new JCheckBox("<html><body><b>전체동의</b></body></html>");
		ckAllAgree.setFont(fontNanumGothic12);
		ckAllAgree.setBackground(Color.white);
		ckAllAgree.setLocation(10, 65);
		ckAllAgree.setSize(new Dimension(300, 15));
		
		ckPrivacy = new JCheckBox("[필수] 개인정보 수집 및 이용 동의");
		ckPrivacy.setFont(fontNanumGothic12);
		ckPrivacy.setBackground(Color.white);
		ckPrivacy.setLocation(10, 105);
		ckPrivacy.setSize(new Dimension(300, 15));
		
		btnPrivacyShow = new JButton("보기");
		btnPrivacyShow.setFont(fontNanumGothic12);
		btnPrivacyShow.setLocation(310, 90);
		btnPrivacyShow.setSize(new Dimension(60, 40));
		btnPrivacyShow.setBorderPainted(false);
		btnPrivacyShow.setContentAreaFilled(false);
		
		ckOutbound = new JCheckBox("[필수] 개인정보 국외 이전에 관한 동의");
		ckOutbound.setFont(fontNanumGothic12);
		ckOutbound.setBackground(Color.white);
		ckOutbound.setLocation(10, 145);
		ckOutbound.setSize(new Dimension(300, 15));
		
		btnOutboundShow = new JButton("보기");
		btnOutboundShow.setFont(fontNanumGothic12);
		btnOutboundShow.setLocation(310, 135);
		btnOutboundShow.setSize(new Dimension(60, 40));
		btnOutboundShow.setBorderPainted(false);
		btnOutboundShow.setContentAreaFilled(false);
		
		
		jpCheck.add(lblAgree);
		jpCheck.add(lblArgreego);
		jpCheck.add(ckAllAgree);
		jpCheck.add(ckPrivacy);
		jpCheck.add(btnPrivacyShow);
		jpCheck.add(ckOutbound);
		jpCheck.add(btnOutboundShow);
		
		jpButton = new JPanel();
		jpButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jpButton.setBackground(Color.white);
		btnNext = new JButton("다음"); // 다음 창으로 넘어가는 액션
		btnNext.setFont(fontNanumGothic12);
		btnNext.setBackground(new Color(000,000,205));
		btnNext.setForeground(new Color(255,255,255));
		//btnNext.setBorderPainted(false);
		btnNext.setPreferredSize(new Dimension(100, 30));
		
		btnBack = new JButton("이전"); // 이전으로 돌아가는 액션
		btnBack.setFont(fontNanumGothic12);
		btnBack.setBackground(new Color(255,255,255));
		btnBack.setForeground(new Color(000,000,205));
		//btnBack.setBorderPainted(false);
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		jpButton.add(btnBack);
		jpButton.add(btnNext);
		
		
		add(jpCheck);
		add(jpButton, BorderLayout.SOUTH);
		setVisible(true);
		
	}


	public static void main(String[] args) {
		new Agreement("My Frame", 500, 280);
	}

}
