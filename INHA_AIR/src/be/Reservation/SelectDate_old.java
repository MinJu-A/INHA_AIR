package be.Reservation;
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

public class SelectDate_old extends JFrame implements ActionListener {
	
	// Title 및 사이즈 설정
	private String title = "탑승일 선택";
	private int width = 760, height = 700;
	
	//private MainForm mainForm;

	//폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);	// 나눔고딕 9
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	private JPanel jpTitle, jpSelect, jpBtn, jpCalendarLeft, jpCalendarRight;
	private JLabel lblTitle;
	private JLabel lblCome;
	private JLabel lblGo;
	private JTextField tfGo;
	private JTextField tfCome;
	private JButton btnReselect;
	private JButton btnSelect;
	private static Calendar cal = Calendar.getInstance(); //캘린더 객체 생성;
	private String[] day = new String[] {"S", "M", "T", "W", "T", "F", "S"};
	private int thisYear;
	private int thisMonth;
	private JLabel lblday;
	private JLabel lblYear;
	private JLabel lblMonth;
	private ArrayList<JButton> lstBtn = new ArrayList<JButton>();
	private JButton btnDay;
	private int dayY;
	private int dayX;
	private static int todayYear = cal.get(Calendar.YEAR);
	private static int todayMonth = cal.get(Calendar.MONTH)+1;
	private int setTime = 0;
	private JButton btnLeft;
	private JButton btnRight;
	private int selectindex = 0;
	

	public SelectDate_old() {
			
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

	private void setCalendar(JPanel jpCal, int year, int month, int x, int y) { //달력생성함수. 패널, 년도, 달, x좌표, y좌표 입력
		
		//달력패널 생성 및 설정
		jpCal = new JPanel();
		jpCal.setLayout(null);
		jpCal.setSize(290, 370);
		jpCal.setLocation(x, y);
		jpCal.setBackground(Color.WHITE);
		
		
		thisYear = year; //현재 년
		thisMonth = month; //현재 달

		
		cal.set(thisYear, thisMonth-1, 1); //캘린더 객체에 년도, 달 설정과 Date 1로 설정
		
		int sDayNum = cal.get(Calendar.DAY_OF_WEEK); //1일의 요일 얻어오기
		int endDate = cal.getActualMaximum(Calendar.DATE); //달의 마지막 일 얻기
		
		int intDateNum = 1; //1일부터 날짜 시작
		
		lblYear = new JLabel(thisYear+"년"); //년도 표시 라벨
		lblYear.setFont(fontNanumGothic12);
		lblYear.setSize(150, 40);
		lblYear.setLocation(15, 2);
		lblMonth = new JLabel(thisMonth+"월"); //달 표시 라벨
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
		
		dayX = 0; //날짜버튼 x좌표 기본 0으로 설정
		dayY = 70; //날짜버튼 y좌표 기본 설정
		
		for (int i = 1; intDateNum <= endDate; i++) { //intDateNum이 1일부터 마지막일이 될 때까지 반복 
		
			
			
			if (i<sDayNum) //i가 요일숫자보다 작으면 공백 버튼
			{
				btnDay = new JButton("");
				btnDay.setFont(fontNanumGothic12);
				btnDay.setBorderPainted(false); //버튼 윤곽선 제거
				//btnDay.setBackground(new Color(153, 204, 255)); //버튼 색 설정
				btnDay.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0)); //안쪽 여백 설정
				btnDay.setBackground(Color.white); //버튼 색 설정
				btnDay.setSize(40, 40);
				btnDay.setLocation(dayX, dayY);
				lstBtn.add(btnDay);
				jpCal.add(btnDay);
				
				dayX += 40;
				
			}
			else //date값의 버튼 출력 
			{
				btnDay = new JButton(intDateNum+"");
				btnDay.addActionListener(this);
				btnDay.setFont(fontNanumGothic12);
				btnDay.setBorderPainted(false); //버튼 윤곽선 제거
				//btnDay.setBackground(new Color(153, 204, 255)); //버튼 색 설정
				btnDay.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0)); //안쪽 여백 설정
				btnDay.setBackground(Color.white); //버튼 색 설정
				btnDay.setSize(40, 40);
				btnDay.setLocation(dayX, dayY);
				
				if ((i-8)%7 ==0) {
					btnDay.setForeground(Color.red); //일요일은 빨간색 설정
				} else if (i%7 ==0) {
					btnDay.setForeground(Color.blue); //토요일은 파란색 설정
				} else {
					btnDay.setForeground(Color.black); //나머지 검정색 설정
				}
				
				lstBtn.add(btnDay);
				jpCal.add(btnDay);
				
				intDateNum++; //출력할 date 증가
				dayX += 40; //X좌표 증가로 옆으로 이동
			}
			
			if(i%7 == 0) //i가 7로 나눠지면 다음 줄로 이동
			{
				dayY += 50; //Y좌표 증가로 다음 줄로 이동
				dayX = 0; //X좌표 초기화
			}
		
		}
		
		add(jpCal); //판넬 추가
		
	}
	
	
	private void setCalendarForm() {
		

		//제목판넬
		jpTitle = new JPanel();
		jpTitle.setLayout(null);
		jpTitle.setSize(200, 60);
		jpTitle.setLocation(50, 40);
		jpTitle.setBackground(Color.white);
		//제목라벨
		lblTitle = new JLabel("탑승일 선택");
		lblTitle.setFont(fontNanumGothic20);
		lblTitle.setSize(150, 40);
		lblTitle.setLocation(16, 0);
		jpTitle.add(lblTitle);
		
		
		//가는날, 오는날
		jpSelect = new JPanel(); //날짜표시 판매
		jpSelect.setLayout(null);
		jpSelect.setSize(650, 40);
		jpSelect.setLocation(40, 100);
		jpSelect.setBackground(Color.white);
		
		lblGo = new JLabel("가는 날"); //가는날 라벨
		lblGo.setFont(fontNanumGothic18);
		lblGo.setSize(150, 40);
		lblGo.setLocation(25, 0);
		tfGo = new JTextField(); //가는 날짜 선택 시 확인
		tfGo.setFont(fontNanumGothic18);
		tfGo.setSize(180, 40);
		tfGo.setLocation(100,0);
		
		lblCome = new JLabel("오는 날 "); //오는날 라벨
		lblCome.setFont(fontNanumGothic18);
		lblCome.setSize(150, 40);
		lblCome.setLocation(375, 0);
		tfCome = new JTextField(); //오는 날짜 선택 시 확인
		tfCome.setFont(fontNanumGothic18);
		tfCome.setSize(180, 40);
		tfCome.setLocation(455, 0);
		
		jpSelect.add(lblGo);
		jpSelect.add(tfGo);
		jpSelect.add(lblCome);
		jpSelect.add(tfCome);

		
		//달력추가
		setCalendar(jpCalendarLeft, todayYear+setTime, todayMonth+setTime, 50, 160); //이번달 달력 출력
		setCalendar(jpCalendarRight, todayYear+setTime, todayMonth+setTime+1, 400, 160); //다음달 달력 출력
		
		//하단 버튼 판넬
		jpBtn = new JPanel();
		jpBtn.setLayout(null);
		jpBtn.setSize(650, 80);
		jpBtn.setLocation(50, 550);
		jpBtn.setBackground(Color.white);
		
		//버튼 추가 및 설정
		btnReselect = new JButton("다시 선택");
		btnReselect.setFont(fontNanumGothic18);
		btnReselect.setSize(230, 60);
		btnReselect.setLocation(40, 0);
		btnReselect.setBorderPainted(false); //버튼 윤곽선 제거
		btnReselect.setBackground(new Color(10,90,150)); //버튼 색 설정
		btnReselect.setForeground(Color.white);
		btnReselect.addActionListener(this);
		btnSelect = new JButton("왕복 선택");
		btnSelect.setFont(fontNanumGothic18);
		btnSelect.setSize(230, 60);
		btnSelect.setLocation(360, 0);
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
		btnRight.setLocation(690, 200);
		btnRight.setBorderPainted(false);
		btnRight.setBackground(Color.white);
		btnRight.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
		btnRight.addActionListener(this);
		
		add(btnLeft);
		add(btnRight);
		add(jpTitle);
		add(jpSelect);
		add(jpBtn);
		
	}

	

	public static void main(String[] args) {
		new SelectDate_old();
	}	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String objText = e.getActionCommand();
		
		
		
		
		if (obj == btnSelect) {
			
		} else if (obj == btnReselect) {
			tfGo.setText("");
			tfCome.setText("");
			selectindex = 0;
		} else if (obj == btnLeft) { //이전 달 달력 출력
			setTime -= 1;
			setCalendar(jpCalendarLeft, todayYear+setTime, todayMonth+setTime, 50, 160); 
			setCalendar(jpCalendarRight, todayYear+setTime, todayMonth+setTime+1, 400, 160); //다음달 달력 출력
		} else if (obj == btnRight) {
			setTime += 1;
			setCalendar(jpCalendarLeft, todayYear+setTime, todayMonth+setTime, 50, 160);
			setCalendar(jpCalendarRight, todayYear+setTime, todayMonth+setTime+1, 400, 160); //다음달 달력 출력
		} else {	//일 버튼을 클릭했을 경우
			if (selectindex == 0) {
			tfGo.setText(objText);
			tfCome.setText("");
			selectindex++;
			} else if(selectindex == 1) {
				tfCome.setText(objText);
				selectindex--;
			}
		}
	}
}
