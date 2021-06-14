package customer.book;
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
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//연우 - 탑승일 선택 작성

public class SelectDate extends JFrame implements ActionListener {
	
	// Title 및 사이즈 설정
	private String title = "탑승일 선택";
	private int width = 800, height = 550;
	
	//private MainForm mainForm;

	//폰트
	Font fontGothic = new Font("Gothic", Font.BOLD, 20);				// 고딕
	Font fontNanumGothic9 = new Font("NanumGothic", Font.BOLD, 9);		// 나눔고딕 9
	Font fontNanumGothic12 = new Font("NanumGothic", Font.BOLD, 12);	// 나눔고딕 12
	Font fontNanumGothic15 = new Font("NanumGothic", Font.BOLD, 15);	// 나눔고딕 15
	Font fontNanumGothic18 = new Font("NanumGothic", Font.BOLD, 18);	// 나눔고딕 18
	Font fontNanumGothic20 = new Font("NanumGothic", Font.BOLD, 20);	// 나눔고딕 25
	Font fontArial = new Font("Arial", Font.PLAIN, 12);					// 영어
	
	private JPanel jpTitle, jpSelect, jpBtn, jpCal, jpDay; //제목패널, 선택패널, 버튼패널, 달력패널, 일 패널
	private JLabel lblTitle; //제목
	private JLabel lblCome; //오는날
	private JLabel lblGo; //가는날
	private JTextField tfGo; //가는날 텍스트필드
	private JTextField tfCome; //오는날 텍스트필드
	private JButton btnReselect; //다시선택
	private JButton btnSelect; //선택하기
	private static Calendar toDaycal = Calendar.getInstance(); //캘린더 객체 생성;
	private static Calendar cal = Calendar.getInstance(); //캘린더 객체 생성;
	
	private String[] day = new String[] {"S", "M", "T", "W", "T", "F", "S"}; //일요일 ~ 토요일
	private JLabel lblday; //요일 라벨
	private JLabel lblYear; //년도 라벨
	private JLabel lblMonth; //월 라벨
	private ArrayList<JButton> lstBtn = new ArrayList<JButton>(); //일 버튼리스트
	private int calDayYear; //달력년도
	private int calDayMonth; //달력월
	private JButton btnLeft; //왼쪽버튼
	private JButton btnRight; //오른쪽버튼
	private int selectindex = 0; //날짜선택 (0이면 선택안한상태, 1이면 선택된상태)
	private int sDayNum; //1일의 요일 얻어오기
	private int endDate; //달의 마지막 일 받아오기
	private int intDateNum; //1일부터 시작
	private String lblstringYear; //년도라벨 값 받아오기
	private String lblstringMonth; //월 라벨 값 받아오기
	private String goDay = ""; //가는날 값
	private String comeDay = ""; //오는날 값
	private int roundTrip; //왕복 편도 값
	private String objText = ""; //선택된 버튼의 텍스트값
//	private JLabel lblEx;
	private BookForm bookForm;
	private int todayDate = toDaycal.get(Calendar.DATE); //오늘 일 받아오기
	private int todayYear = toDaycal.get(Calendar.YEAR); //오늘 년도 받아오기
	private int todayMonth = toDaycal.get(Calendar.MONTH)+1; //오늘 달 받아오기
	//cal.set(todayYear, todayMonth, todayDate);
	private String stTodayMonth; //이번 달 문자열
	private String stTodayDate; //이번 일 문자열
	
	
	
	
	
	
	
	
	public SelectDate(BookForm bookForm) {
			
		//this.mainForm = mainForm;
		this.bookForm = bookForm; //bookForm에 대한 정보
		
		//다시 열 때 값 받아오기
		this.goDay = bookForm.getGoDay();
		this.comeDay = bookForm.getComeDay();
		this.roundTrip = bookForm.getRoundTrip();
		
		//창 설정
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(this);
		Container c = getContentPane();
		c.setBackground(Color.white);
		setLayout(null);
		
		
		setCalendarForm();

		tfGo.setText(goDay);
		tfCome.setText(comeDay);
		
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
		
		//가는날, 오는날
		jpSelect = new JPanel(); //날짜표시 판매
		jpSelect.setLayout(null);
		jpSelect.setSize(300, 150);
		jpSelect.setLocation(450, 160);
		jpSelect.setBackground(Color.white);
		
//		lblEx = new JLabel("예) 2002/02/14");
//		lblEx.setFont(fontNanumGothic12);
//		lblEx.setSize(100, 15);
//		lblEx.setLocation(105, 0);
		
		lblGo = new JLabel("가는 날"); //가는날 라벨
		lblGo.setFont(fontNanumGothic18);
		lblGo.setSize(150, 35);
		lblGo.setLocation(15, 15);
		tfGo = new JTextField(); //가는 날짜 선택 시 확인
		tfGo.setFont(fontNanumGothic15);
		tfGo.setSize(180, 30);
		tfGo.setLocation(100,20);
		tfGo.setEditable(false);	//입력제한
		
		lblCome = new JLabel("오는 날 "); //오는날 라벨
		lblCome.setFont(fontNanumGothic18);
		lblCome.setSize(150, 40);
		lblCome.setLocation(15, 80);
		tfCome = new JTextField(); //오는 날짜 선택 시 확인
		tfCome.setFont(fontNanumGothic15);
		tfCome.setSize(180, 30);
		tfCome.setLocation(100, 85);
		tfCome.setEditable(false);	//입력제한
		
		//추가하기
		jpSelect.add(lblGo);
		jpSelect.add(tfGo);
		jpSelect.add(lblCome);
		jpSelect.add(tfCome);

	
		//달력패널 생성 및 설정
		jpCal = new JPanel();
		jpCal.setLayout(null);
		jpCal.setSize(300, 370);
		jpCal.setLocation(60, 80);
		jpCal.setBackground(Color.white);
		
		
		calDayYear = cal.get(Calendar.YEAR); //오늘 년도 받아오기
		calDayMonth = cal.get(Calendar.MONTH)+1; //오늘 달 받아오기 (자바는 달이 0부터 시작이라 1추가해서 오늘 달로 만들어주기)
		cal.set(calDayYear, calDayMonth-1, 1); //캘린더 객체에 년도, 달 설정과 Date 1로 설정
		
		
		sDayNum = cal.get(Calendar.DAY_OF_WEEK); //1일의 요일 얻어오기
		endDate = cal.getActualMaximum(Calendar.DATE); //달의 마지막 일 얻기
		
		intDateNum = 1; //1일부터 날짜 시작
		
		lblYear = new JLabel(calDayYear+"년"); //년도 표시 라벨
		lblYear.setFont(fontNanumGothic12);
		lblYear.setSize(150, 40);
		lblYear.setLocation(15, 2);
		lblMonth = new JLabel(calDayMonth+"월"); //달 표시 라벨
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
		jpBtn.setSize(300, 40);
		jpBtn.setLocation(450, 310);
		jpBtn.setBackground(Color.white);
		
		//버튼 추가 및 설정
		btnReselect = new JButton("다시 선택");
		btnReselect.setFont(fontNanumGothic18);
		btnReselect.setSize(130, 40);
		btnReselect.setLocation(10, 0);
		btnReselect.setBorderPainted(false); //버튼 윤곽선 제거
		btnReselect.setBackground(new Color(10,90,150)); //버튼 색 설정
		btnReselect.setForeground(Color.white);
		btnReselect.addActionListener(this);
		
		btnSelect = new JButton("");	//선택버튼
		if (goDay.isEmpty()) {
			btnSelect.setText("날짜 선택");
		} else {
//			if(comeDay.isEmpty()) {
//				btnSelect.setText("편도 선택");
//			} else {
//				btnSelect.setText("왕복 선택");
//			}
			
			btnSelect.setText("왕복 선택");
			
		}
		btnSelect.setFont(fontNanumGothic18);
		btnSelect.setSize(130, 40);
		btnSelect.setLocation(150, 0);
		btnSelect.setBorderPainted(false); //버튼 윤곽선 제거
		btnSelect.setBackground(new Color(10,90,150)); //버튼 색 설정
		btnSelect.setForeground(Color.white);
		btnSelect.addActionListener(this);
		
		jpBtn.add(btnReselect);
		jpBtn.add(btnSelect);
		
		//캘린더 변경 버튼
		btnLeft = new JButton("<");		//왼쪽으로 페이지 변경 버튼
		btnLeft.setFont(fontNanumGothic12);
		btnLeft.setSize(50, 250);
		btnLeft.setLocation(10, 150);
		btnLeft.setBorderPainted(false);
		btnLeft.setBackground(Color.white);
		btnLeft.setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
		btnLeft.addActionListener(this);
		
		btnRight = new JButton(">"); //오늘쪽으로 페이지 변경 버튼
		btnRight.setFont(fontNanumGothic12);
		btnRight.setSize(50, 250);
		btnRight.setLocation(340, 150);
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
		//new SelectDate();
	}	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		objText = e.getActionCommand();
		
		cal.set(calDayYear, calDayMonth-1, 1); //캘린더 객체에 년도, 달 설정과 Date 1로 설정
		
		
		if (obj == btnSelect) //날짜선택버튼 클릭
		{
			
			if (objText.equals("날짜 선택")) { //버튼 텍스트가 날짜선택이면 날짜 선택 안 한 것이기 때문에 팝업창 띄우기
				
				JOptionPane.showMessageDialog(null, "날짜를 선택해주세요.", "탑승일 선택", JOptionPane.OK_CANCEL_OPTION);
				
			}
			if (objText.equals("왕복 선택")) { //버튼이 왕복선택이면 날짜선택 완료한 것이므로 실행
				
				int result = JOptionPane.showConfirmDialog(null, "가는날 - " + goDay + "     오는 날 -" + comeDay + "으로 왕복 선택되었습니다.", "왕복 선택", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION) {
					
					//goday comeday roundTrip
					goDay = goDay+"";
					comeDay = comeDay+"";
					roundTrip = 1;
					
					bookForm.setGoDay(goDay);
					bookForm.setComeDay(comeDay);
					bookForm.setRoundTrip(roundTrip);
					bookForm.setDate();
					
					setVisible(false);
					
				}
			}
			
			//System.out.println("선택되었습니다.");
			
		} 
		
		
		
		else if (obj == btnReselect) { //모두 다시선택, 값 초기화
			
			btnSelect.setText("날짜 선택");
			tfGo.setText("");
			tfCome.setText("");
			selectindex  = 0;
			
			
		}
		
		else if ((obj == btnLeft) || (obj==btnRight)) { //달력 왼쪽으로 바꾸거나 오른쪽으로 바꾸기
			
			if (obj == btnLeft) { //이전 달 달력 값 설정
				
				calDayMonth--; //이전달로
				
				 if(calDayMonth<1) { //1월 이전 달로 넘길 때 이전년도 12월로 설정
					 calDayMonth=12;
					 calDayYear--;
				}
			} else if (obj == btnRight) { //다음 달 달력 값 설정
					
					calDayMonth++; //다음달로
					
					if(calDayMonth>12) { //12월 다음 달로 넘길 때 다음년도 1월로 설정
						calDayMonth=1;
						calDayYear++;
					}
			}
			
			//달력 값에 따른 버튼 설정
			cal.set(calDayYear, calDayMonth-1, 1); //캘린더 객체에 년도, 달 설정과 Date 1로 설정
			
			lblMonth.setText(calDayMonth+"월");
			lblYear.setText(calDayYear+"년");
			
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
			

			if (objText.isEmpty()) { //버튼 텍스트가 비어있으면 날짜 없으니 액션 안 넣기
				
			} else { //버튼에 텍스트가 있으면 실행
				
				if(Integer.parseInt(lblstringMonth)<10)     //달 값이 10보다 작으면, 해당 페이지의 달 값 설정
					lblstringMonth = 0 + lblstringMonth;	//한 자리수를 두 자리수로 설정
				if(Integer.parseInt(objText)<10)		    //일 값도 10보다 값 작으면 설정
					objText = 0 + objText;
				
				if(todayMonth < 10) //10보다 작으면 두자리수 설정
					stTodayMonth = "0" + todayMonth;
				else
					stTodayMonth = String.valueOf(todayMonth);
				if(todayDate < 10) //10보다 작으면 두자리수 설정
					stTodayDate = "0" + todayDate;
				else
					stTodayDate = String.valueOf(todayDate);
				
				
				//이번달 전의 날이면 선택 못하게 하기
				int day1 = Integer.parseInt(String.valueOf(lblstringYear+lblstringMonth+objText)); //선택한 날짜
				int day2 = Integer.parseInt(String.valueOf(toDaycal.get(Calendar.YEAR)) + stTodayMonth + stTodayDate); //오늘 날짜
				
				if (day1 - day2 < 0) {
					JOptionPane.showMessageDialog(null, "오늘 이전의 날은 선택할 수 없습니다.", "날짜선택", JOptionPane.OK_CANCEL_OPTION);
				} else {
					if (selectindex == 0) { //선택 안 했으면 goDay에 값 설정
						
						goDay = lblstringYear+lblstringMonth+objText;
						tfGo.setText(goDay);
						tfCome.setText("");
						btnSelect.setText("날짜 선택");
						selectindex++;
											
						
						
					} else if(selectindex == 1) { //선택했을 때
						comeDay = lblstringYear+lblstringMonth+objText;
						
						if(Integer.parseInt(goDay)<Integer.parseInt(comeDay)) { //goDay보다 값이 크면 comeDay에 설정
							//tfCome.setText(lblstringYear + "/" + lblstringMonth + "/" + objText);
							tfCome.setText(comeDay);
							btnSelect.setText("왕복 선택");
							selectindex--;
							
							
						} else { //goDay보다 값이 작으면 goDay로 다시 설정
							goDay = lblstringYear+lblstringMonth+objText;
							btnSelect.setText("날짜 선택");
							tfGo.setText(goDay);
						}
					}
				
				}
			}
		}
	}
}
