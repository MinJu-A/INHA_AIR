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
	
	//화면설정
	private String title = "아이디 찾기";
	private int width = 400, height = 300;
	private JLabel lblId;	//아이디 라벨
	
	private FindIdPwForm findIdPwForm;
	private String id;	//아이디값
	
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	private JLabel lblIdValue;	//아이디값 라벨
	
	public FindIdForm(FindIdPwForm findIdPwForm) {
		
		this.findIdPwForm = findIdPwForm;
		
		id = findIdPwForm.getIdFound();	//아이디값 가져오기
		
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
		
		lblId= new JLabel("회원님의 아이디");	//회원아이디 설명 라벨
		lblId.setSize(300, 30);
		lblId.setLocation(10, 30);
		lblId.setFont(fontNanumGothic18);
		add(lblId);
		
		lblIdValue = new JLabel(id);		//회원아이디 값 라벨
		lblIdValue.setSize(300, 60);
		lblIdValue.setLocation(10, 40);
		lblIdValue.setFont(fontNanumGothic18);
		add(lblIdValue);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		setVisible(false);
	}
	
}
