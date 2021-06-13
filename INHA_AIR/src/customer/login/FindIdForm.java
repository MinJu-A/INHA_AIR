package customer.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//연우 - 아이디찾기
public class FindIdForm extends JFrame implements ActionListener {
	
	private String title = "아이디 찾기";
	private int width = 400, height = 300;
	private JLabel lblId;
	
	private FindIdPwForm findIdPwForm;
	private String id;
	
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	private JButton btnExit;
	private JLabel lblIdValue;
	
	public FindIdForm(FindIdPwForm findIdPwForm) {
		
		this.findIdPwForm = findIdPwForm;
		
		id = findIdPwForm.getIdFound();
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		
		
		Container c = getContentPane();
		c.setBackground(Color.white);
		setLayout(null);
//		this.setUndecorated(true); //타이틀바 없애기 

		setFindPw();
		
		setVisible(true);
	}

	private void setFindPw() {
		
		lblId= new JLabel("회원님의 아이디");
		lblId.setSize(300, 30);
		lblId.setLocation(10, 30);
		lblId.setFont(fontNanumGothic18);
		add(lblId);
		
		lblIdValue = new JLabel(id);
		lblIdValue.setSize(300, 60);
		lblIdValue.setLocation(10, 40);
		lblIdValue.setFont(fontNanumGothic18);
		add(lblIdValue);
		
//		btnExit = new JButton("닫기");
//		btnExit.setSize(100, 30);
//		btnExit.setLocation(150, 200);
//		btnExit.setBackground(new Color(10,90,150));
//		btnExit.setForeground(Color.white); //버튼 폰트 색 변경
//		btnExit.setFont(fontNanumGothic18);
//		btnExit.addActionListener(this);
//		add(btnExit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		setVisible(false);
	}
	
}
