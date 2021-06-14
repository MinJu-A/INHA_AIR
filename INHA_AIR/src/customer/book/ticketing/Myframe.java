package customer.book.ticketing;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import customer.start.MainMenuForm;


public class Myframe extends JFrame implements ActionListener{
		private JLabel a;
		private String title = "INHA AIR";
		private int width = 300, height = 200;
		
		Font fontNanumGothic15= new Font("NanumGothic", Font.PLAIN, 15);	// 나눔고딕 18
		private JButton btnOK;

		public Myframe() {
		setTitle(title);
		setSize(width, height);
		setLocation(800,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(this);

		setLayout(null);
		
		a = new JLabel("가는 편을 선택하세요.");
		a.setBounds(75, 0, 500, 100);
		a.setFont(fontNanumGothic15);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(115, 80, 60, 30);
		btnOK.addActionListener(this);
		
//		btnOK.setLocation(150, 40);
		
		add(a);
		add(btnOK);
		
		setVisible(true);
		}
		
	public static void main(String[] args) {
		new Myframe();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == btnOK) {
			
			this.setVisible(false);
			
		} 		
	}

}
