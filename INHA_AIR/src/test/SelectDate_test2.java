package test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

//연우 - 탑승일 선택 작성

public class SelectDate_test2 extends JFrame implements ActionListener {
	
	// Title 및 사이즈 설정
	private String title = "탑승일 선택";
	private int width = 430, height = 700;
	
	//private MainForm mainForm;

	//폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);	// 나눔고딕 9
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	private JPanel jpTitle, jpSelect, jpBtn, jpCal;
	private JLabel lblTitle;
	private JLabel lblCome;
	private JLabel lblGo;
	private JTextField tfGo;
	private JTextField tfCome;
	private JButton btnReselect;
	private JButton btnSelect;
	private static Calendar cal = Calendar.getInstance(); //캘린더 객체 생성;
	private String[] day = new String[] {"S", "M", "T", "W", "T", "F", "S"};
	private JLabel lblday;
	private JLabel lblYear;
	private JLabel lblMonth;
	private ArrayList<JButton> lstBtn = new ArrayList<JButton>();
	private JButton btnDay;
	private int todayYear = cal.get(Calendar.YEAR);
	private int todayMonth = cal.get(Calendar.MONTH)+1;
	private JButton btnLeft;
	private JButton btnRight;
	private int selectindex = 0;
	private int sDayNum;
	private int endDate;
	private int intDateNum;
	private String lblstringYear;
	private String lblstringMonth;
	private JPanel jpDay;
	private int day1;
	private int day2;
	
	

	public SelectDate_test2() {
			
		//this.mainForm = mainForm;
		
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		Container c = getContentPane();
		c.setBackground(Color.white);
		setLayout(null);
		
		
		setCalendarForm();
		
		setVisible(true);
		
	}
	
	private void setCalendarForm() {
		

		//제목패널
		jpTitle = new JPanel();
		jpTitle.setLayout(null);
		jpTitle.setSize(300, 40);
		jpTitle.setLocation(60, 40);
		jpTitle.setBackground(Color.white);
		//제목라벨
		lblTitle = new JLabel("탑승일 선택");
		lblTitle.setFont(fontNanumGothic20);
		lblTitle.setSize(150, 40);
		lblTitle.setLocation(15, 0);
		jpTitle.add(lblTitle);
		//450, 250
		
		//가는날, 오는날
		jpSelect = new JPanel(); //날짜표시 판매
		jpSelect.setLayout(null);
		jpSelect.setSize(300, 80);
		jpSelect.setLocation(60, 80);
		jpSelect.setBackground(Color.white);
		
		lblGo = new JLabel("가는 날"); //가는날 라벨
		lblGo.setFont(fontNanumGothic15);
		lblGo.setSize(150, 40);
		lblGo.setLocation(15, 0);
		tfGo = new JTextField(); //가는 날짜 선택 시 확인
		tfGo.setFont(fontNanumGothic15);
		tfGo.setSize(180, 30);
		tfGo.setLocation(100,5);
		
		lblCome = new JLabel("오는 날 "); //오는날 라벨
		lblCome.setFont(fontNanumGothic15);
		lblCome.setSize(150, 40);
		lblCome.setLocation(15, 35);
		tfCome = new JTextField(); //오는 날짜 선택 시 확인
		tfCome.setFont(fontNanumGothic15);
		tfCome.setSize(180, 30);
		tfCome.setLocation(100, 40);
		
		jpSelect.add(lblGo);
		jpSelect.add(tfGo);
		jpSelect.add(lblCome);
		jpSelect.add(tfCome);

	
		//달력패널 생성 및 설정
		jpCal = new JPanel();
		jpCal.setLayout(null);
		jpCal.setSize(300, 370);
		jpCal.setLocation(60, 160);
		jpCal.setBackground(Color.white);
		
		todayYear = cal.get(Calendar.YEAR);
		todayMonth = cal.get(Calendar.MONTH)+1;
		cal.set(todayYear, todayMonth-1, 1); //캘린더 객체에 년도, 달 설정과 Date 1로 설정
		
		
		sDayNum = cal.get(Calendar.DAY_OF_WEEK); //1일의 요일 얻어오기
		endDate = cal.getActualMaximum(Calendar.DATE); //달의 마지막 일 얻기
		
		intDateNum = 1; //1일부터 날짜 시작
		
		lblYear = new JLabel(todayYear+"년"); //년도 표시 라벨
		lblYear.setFont(fontNanumGothic12);
		lblYear.setSize(150, 40);
		lblYear.setLocation(15, 2);
		lblMonth = new JLabel(todayMonth+"월"); //달 표시 라벨
		lblMonth.setFont(fontNanumGothic18);
		lblMonth.setSize(80, 40);
		lblMonth.setLocation(65, 0);
		jpCal.add(lblYear);
		jpCal.add(lblMonth);
		
		for (int i = 0; i<7; i++) { //요일 추가
			lblday = new JLabel(day[i]); //day[i]로 요일 배열 추가
			lblday.setFont(fontNanumGothic15);
			lblday.setSize(100, 40);
			lblday.setLocation(40*i + 16, 30); //위치 설정
			jpCal.add(lblday);
			
		}

		//달력 일 패널
		jpDay = new JPanel();
		jpDay.setLayout(new GridLayout(6,7));
		jpDay.setSize(280, 300);
		jpDay.setLocation(0, 70);
		jpDay.setBackground(Color.white);
		
		
		
		for (int i = 0; i < 42; i++) { //달력에 42(6*7)개의 버튼 추가 
			
			lstBtn.add(new JButton());
			lstBtn.get(i).setBackground(Color.white);
			lstBtn.get(i).addActionListener(this);
			lstBtn.get(i).setFont(fontNanumGothic12);
			lstBtn.get(i).setBorderPainted(false); //버튼 윤곽선 제거
			//lstBtn.get(i).setBackground(new Color(153, 204, 255)); //버튼 색 설정
			lstBtn.get(i).setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0)); //안쪽 여백 설정
			lstBtn.get(i).setBackground(Color.white); //버튼 색 설정
			lstBtn.get(i).setSize(40, 40);
			
			jpDay.add(lstBtn.get(i));
		}
		
		
		intDateNum = 1; //1일부터 날짜 시작
		sDayNum = cal.get(Calendar.DAY_OF_WEEK); //1일의 요일 얻어오기 (일(1), 월(2)~토(7)
		endDate = cal.getActualMaximum(Calendar.DATE); //달의 마지막 일 얻기
		
		for (int i = 0; intDateNum <= endDate; i++) { //intDateNum이 1일부터 마지막일이 될 때까지 반복 
			
			if (i<sDayNum-1) //i가 요일숫자보다 작으면 공백 버튼
			{
				lstBtn.get(i).setText("");
			}
			if ((i<sDayNum-1)==false) //date값의 버튼 출력 
			{
				
				
				if (i%7 ==0) {
					lstBtn.get(i).setForeground(Color.red); //일요일은 빨간색 설정
				} else if ((i-6)%7 ==0) {
					lstBtn.get(i).setForeground(Color.blue); //토요일은 파란색 설정
				} else {
					lstBtn.get(i).setForeground(Color.black); //나머지 검정색 설정
				}
				
				lstBtn.get(i).setText(intDateNum+"");
				
				intDateNum++; //출력할 date 증가
			}
		
		}
		
		jpCal.add(jpDay);	
		
		//하단 버튼 판넬
		jpBtn = new JPanel();
		jpBtn.setLayout(null);
		jpBtn.setSize(300, 80);
		jpBtn.setLocation(60, 550);
		jpBtn.setBackground(Color.white);
		
		//버튼 추가 및 설정
		btnReselect = new JButton("다시 선택");
		btnReselect.setFont(fontNanumGothic18);
		btnReselect.setSize(140, 40);
		btnReselect.setLocation(0, 0);
		btnReselect.setBorderPainted(false); //버튼 윤곽선 제거
		btnReselect.setBackground(new Color(10,90,150)); //버튼 색 설정
		btnReselect.setForeground(Color.white);
		btnReselect.addActionListener(this);
		btnSelect = new JButton("편도 선택");
		btnSelect.setFont(fontNanumGothic18);
		btnSelect.setSize(140, 40);
		btnSelect.setLocation(160, 0);
		btnSelect.setBorderPainted(false); //버튼 윤곽선 제거
		btnSelect.setBackground(new Color(10,90,150)); //버튼 색 설정
		btnSelect.setForeground(Color.white);
		btnSelect.addActionListener(this);
		
		jpBtn.add(btnReselect);
		jpBtn.add(btnSelect);
		
		//캘린더 변경 버튼
		btnLeft = new JButton("<");
		btnLeft.setFont(fontNanumGothic12);
		btnLeft.setSize(50, 300);
		btnLeft.setLocation(5, 200);
		btnLeft.setBorderPainted(false);
		btnLeft.setBackground(Color.white);
		btnLeft.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
		btnLeft.addActionListener(this);
		btnRight = new JButton(">");
		btnRight.setFont(fontNanumGothic12);
		btnRight.setSize(50, 300);
		btnRight.setLocation(360, 200);
		btnRight.setBorderPainted(false);
		btnRight.setBackground(Color.white);
		btnRight.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
		btnRight.addActionListener(this);
		
		add(btnLeft);
		add(btnRight);
		add(jpTitle);
		add(jpSelect);
		add(jpBtn);
		add(jpCal);
		
	}

	

	public static void main(String[] args) {
		new SelectDate_test2();
	}	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String objText = e.getActionCommand();

		cal.set(todayYear, todayMonth-1, 1); //캘린더 객체에 년도, 달 설정과 Date 1로 설정
		
		if (obj == btnSelect) {
			
		} 
		else if (obj == btnReselect) {
			tfGo.setText("");
			tfCome.setText("");
			selectindex  = 0;
		} else if (obj == btnDay) {
			System.out.println(btnDay.getText());
		}
		
		else if ((obj == btnLeft) || (obj==btnRight)) {
			
			if (obj == btnLeft) { //이전 달 달력 설정
				
				todayMonth--; //이전달로
				
				 if(todayMonth<1) { //1월 이전 달로 넘길 때 이전년도 12월로 설정
					 todayMonth=12;
					todayYear--;
				}
			} else if (obj == btnRight) { //다음 달 달력 설정
					
					todayMonth++; //다음달로
					
					if(todayMonth>12) { //12월 다음 달로 넘길 때 다음년도 1월로 설정
						todayMonth=1;
						todayYear++;
					}
			}
			
			cal.set(todayYear, todayMonth-1, 1); //캘린더 객체에 년도, 달 설정과 Date 1로 설정
			
			lblMonth.setText(todayMonth+"월");
			lblYear.setText(todayYear+"년");
			
			intDateNum = 1; //1일부터 날짜 시작
			sDayNum = cal.get(Calendar.DAY_OF_WEEK); //1일의 요일 얻어오기 (일(1), 월(2)~토(7)
			endDate = cal.getActualMaximum(Calendar.DATE); //달의 마지막 일 얻기
			
			for (int i = 0; i < 42; i++) { //intDateNum이 1일부터 마지막일이 될 때까지 반복 
				
				if (i<sDayNum-1) //i가 요일숫자보다 작으면 공백 버튼
				{
					lstBtn.get(i).setText("");
				}
				else if ((i>sDayNum-2) && (intDateNum<endDate+1)) //date값의 버튼 출력 
				{
					if (i%7==0) {
						lstBtn.get(i).setForeground(Color.red); //일요일은 빨간색 설정
					} else if ((i-6)%7 ==0) {
						lstBtn.get(i).setForeground(Color.blue); //토요일은 파란색 설정
					} else {
						lstBtn.get(i).setForeground(Color.black); //나머지 검정색 설정
					}
					
					lstBtn.get(i).setText(intDateNum+"");
					
					intDateNum++; //출력할 date 증가
				} else {
					lstBtn.get(i).setText("");
				}
				
			}
			
		} else {	//일 버튼을 클릭했을 경우
			
			lblstringYear = lblYear.getText().substring(0,4);
			lblstringMonth = lblMonth.getText().substring(0,lblMonth.getText().length()-1);
			
			if (objText.isEmpty()) {
				
			} else {
				
				if(Integer.parseInt(lblstringMonth)<10)
					lblstringMonth = 0 + lblstringMonth;
				if(Integer.parseInt(objText)<10)
					objText = 0 + objText;
				
				if (selectindex == 0) {
					day1 = Integer.parseInt(lblstringYear+lblstringMonth+objText);
					tfGo.setText(lblstringYear + "/" + lblstringMonth + "/" + objText);
					tfCome.setText("");
					btnSelect.setText("편도 선택");
					selectindex++;
				} else if(selectindex == 1) {
					day2 = Integer.parseInt(lblstringYear+lblstringMonth+objText);
					if(day1<day2) {
						tfCome.setText(lblstringYear + "/" + lblstringMonth + "/" + objText);
						btnSelect.setText("왕복 선택");

						selectindex--;
					} else {
						day1 = Integer.parseInt(lblstringYear+lblstringMonth+objText);
						tfGo.setText(lblstringYear + "/" + lblstringMonth + "/" + objText);
						tfCome.setText("");
						btnSelect.setText("편도 선택");
					}
				}
			}
		}
	}
}
